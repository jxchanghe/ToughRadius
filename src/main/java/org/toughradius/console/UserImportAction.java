/**
 * Copyright (c) 2013, jamiesun, All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *     Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 * 
 *     Redistributions in binary form must reproduce the above copyright notice, this
 *     list of conditions and the following disclaimer in the documentation and/or
 *     other materials provided with the distribution.
 * 
 *     Neither the name of the {organization} nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.toughradius.console;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.toughradius.annotation.AuthAdmin;
import org.toughradius.common.CsvReader;
import org.toughradius.common.DateTimeUtil;
import org.toughradius.common.ValidateUtil;
import org.toughradius.constant.Constant;
import org.toughradius.data.RadUserMapper;
import org.toughradius.data.RadUserMetaMapper;
import org.toughradius.model.RadUser;
import org.toughradius.model.RadUserMeta;
import org.toughradius.model.RadUserMetaExample;
import org.xlightweb.BadMessageException;
import org.xlightweb.BodyDataSource;
import org.xlightweb.IBodyCompleteListener;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;
import org.xlightweb.IPart;
import org.xlightweb.IPartHandler;
import org.xlightweb.Mapping;
import org.xlightweb.NonBlockingBodyDataSource;

/**
 * @author jamiesun
 * 用户批量导入
 */
@AuthAdmin
@Mapping( { "/user/import" })
public class UserImportAction extends FliterAction{

    private static final Log log = LogFactory.getLog(UserImportAction.class);
    public void doGet(IHttpExchange http) throws IOException, BadMessageException {
        http.send(freemaker.render(http, "import"));
    }

    public void doPost(IHttpExchange http) throws IOException, BadMessageException {

        final IHttpRequest req = http.getRequest();

        final NonBlockingBodyDataSource bds = req.getNonBlockingBody();
        bds.setBodyDataReceiveTimeoutMillis(5000);
        
        try {
            if(bds.isMultipart())
            {
                if(bds.available()!=-1)
                    bds.readStringByDelimiter("\r\n\r\n");

                String data = null;
                if(bds.available()!=-1)
                {
                    data =  bds.readStringByDelimiter("\r\n\r\n"); 
                }
                if(data==null)
                {
                    http.send(freemaker.renderWithAlert(http, "import","没有解析到任何上传数据"));
                    return;
                }
                importUsers(data);
            }

        } catch (Exception e) {
            log.error("导入用户失败",e);
            http.send(freemaker.renderWithAlert(http, "import","操作失败失败了，"+e.getMessage()));
            return;
        } 
        http.send(freemaker.renderWithAlert(http, "import","用户导入完成"));
    }
    
    
    private void importUsers(String data) throws Exception {
        
        CsvReader users = null;
        List<String[]> ulist = new ArrayList<String[]>(); 
        try {
            int row = 0;
            users = new CsvReader(new StringReader(data));
            users.readHeaders();
            while(users.readRecord())
            {
                row++;
                String userName = users.get("user_name");
                String password = users.get("password");
                String groupName = users.get("group_name");
                String status = users.get("status");
                String expire = users.get("expire");
                String credit = users.get("credit");
                String period = users.get("period");
                String bindMac = users.get("bind_mac");
                String concurNumber = users.get("concur_number");
                
                if(!ValidateUtil.isByteLen(userName, 1, 32))
                {
                    throw new Exception("第"+row+"行:"+"[user_name]用户名长度不能超过32个字符;");
                }
                
                if(!ValidateUtil.isByteLen(password, 6, 32))
                {
                    throw new Exception("第"+row+"行:"+"[password]密码长度为6-32个字符;");
                }
                
                if(ValidateUtil.isEmpty(groupName))
                {
                    throw new Exception("第"+row+"行:"+"[group_name]用户组不能为空");
                }
                
                if(status!=null&&!ValidateUtil.isInteger(status))
                {
                    throw new Exception("第"+row+"行:"+"[status]状态必须是数字");
                }
                
                if(credit!=null&&!ValidateUtil.isInteger(credit))
                {
                    throw new Exception("第"+row+"行:"+"[credit]余额必须是数字");
                }
                
                if(bindMac!=null&&!ValidateUtil.isInteger(bindMac))
                {
                    throw new Exception("第"+row+"行:"+"[bindMac]是否绑定MAC必须是数字");
                }
                
                if(concurNumber!=null&&!ValidateUtil.isInteger(concurNumber))
                {
                    throw new Exception("第"+row+"行:"+"[concur_number]并发数必须是数字");
                }
                
                if(period!=null&&!ValidateUtil.isRegExp(period, "\\d{2}:\\d{2}-\\d{2}:\\d{2}"))
                {
                    throw new Exception("第"+row+"行:"+"[period]时段格式必须为 hh:ss-hh:ss");
                }
                
                if(expire!=null&&!ValidateUtil.isDate(expire))
                {
                    throw new Exception("第"+row+"行:"+"[expire]过期时间格式必须为yyyy-MM-dd");
                }
                
                if(cacheServ.getUser(userName) != null)
                {
                    throw new Exception("第"+row+"行:"+userName+"用户已经存在");
                }
                
                if(cacheServ.getGroup(groupName) == null)
                {
                    throw new Exception("第"+row+"行:"+groupName+"用户组不存在");
                }
                
                String[] uarray = new String[]
                        { 
                        userName, password, 
                        groupName, status,
                        expire, credit, period,
                        bindMac, concurNumber 
                        };
                ulist.add(uarray);
            } 
        } finally {
            users.close();
        }
        
        SqlSession session = dbServ.openSession();
        try
        {
            RadUserMapper mapper = session.getMapper(RadUserMapper.class);
            RadUserMetaMapper rmMapper = session.getMapper(RadUserMetaMapper.class);
            for (String[] usrArray : ulist)
            {
                String userName = usrArray[0];
                String password = usrArray[1];
                String groupName = usrArray[2];
                String status = usrArray[3];
                String expire = usrArray[4];
                String credit = usrArray[5];
                String period = usrArray[6];
                String bindMac = usrArray[7];
                String concurNumber = usrArray[8];
                RadUser user = new RadUser();
                user.setUserName(userName);
                user.setPassword(password);
                user.setGroupName(groupName);
                mapper.insert(user);
                
                //delete meta
                RadUserMetaExample example = new RadUserMetaExample();
                example.createCriteria().andUserNameEqualTo(userName);
                rmMapper.deleteByExample(example);
                
                //status
                if(status!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_STATUS.value());
                    meta.setValue(status);
                    meta.setMetaDesc(Constant.USER_STATUS.desc());
                    rmMapper.insert(meta);
                }
                
                //expire
                if(expire!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_EXPIRE.value());
                    meta.setValue(expire);
                    meta.setMetaDesc(Constant.USER_EXPIRE.desc());
                    rmMapper.insert(meta);
                }
                
                //credit
                if(credit!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_CREDIT.value());
                    meta.setValue(credit);
                    meta.setMetaDesc(Constant.USER_CREDIT.desc());
                    rmMapper.insert(meta);
                }
                
                //period
                if(period!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_PERIOD.value());
                    meta.setValue(period);
                    meta.setMetaDesc(Constant.USER_PERIOD.desc());
                    rmMapper.insert(meta);
                }
                
                //bindMac
                if(bindMac!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_BIND_MAC.value());
                    meta.setValue(bindMac);
                    meta.setMetaDesc(Constant.USER_BIND_MAC.desc());
                    rmMapper.insert(meta);
                }
                
                //concurNumber
                if(concurNumber!=null)
                {
                    RadUserMeta meta = new RadUserMeta();
                    meta.setUserName(userName);
                    meta.setName(Constant.USER_CONCUR_NUMBER.value());
                    meta.setValue(concurNumber);
                    meta.setMetaDesc(Constant.USER_CONCUR_NUMBER.desc());
                    rmMapper.insert(meta);
                }
            }

            session.commit();
            cacheServ.reload();

        }
        catch(Exception e) {
            session.rollback();
            throw new Exception("导入数据库时发生错误；"+e.getMessage());
        }
        finally
        {
            session.close();
        }
        


    }

}
