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
package org.toughradius.components;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.toughradius.annotation.Inject;
import org.toughradius.data.RadAdminMapper;
import org.toughradius.data.RadClientMapper;
import org.toughradius.data.RadOptionMapper;
import org.toughradius.model.RadAdmin;
import org.toughradius.model.RadClient;
import org.toughradius.model.RadOption;
@Inject
public class BaseService
{
    private static Log logger = LogFactory.getLog(BaseService.class);
    private DBService dbservice;
    private CacheService cacheService;
    public void setDbservice(DBService dbservice)
    {
        this.dbservice = dbservice;
    }
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
    
    /**
     * 查询单个选项
     * @param optionName
     * @return
     */
    public RadOption getOption(String optionName)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadOptionMapper mapper = session.getMapper(RadOptionMapper.class);
            RadOption ro = mapper.selectByPrimaryKey(optionName);
            return ro;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 查询选项集合
     * @return
     */
    public List<RadOption> getOptions()
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadOptionMapper mapper = session.getMapper(RadOptionMapper.class);
            List<RadOption> ros = mapper.selectByExample(null);
            return ros;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 更新选项
     * @param option
     */
    public void updateOption(RadOption option)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadOptionMapper mapper = session.getMapper(RadOptionMapper.class);
            mapper.updateByPrimaryKey(option);
            session.commit();
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 查询单个客户端
     * @param address
     * @return
     */
    public RadClient getClient(String address)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            RadClient rc = mapper.selectByPrimaryKey(address);
            return rc;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 新增客户端
     * @param client
     */
    public void addClient(RadClient client)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            mapper.insert(client);
            session.commit();
            cacheService.updateClient(client);
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 更新客户端
     * @param client
     */
    public void updateClient(RadClient client)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            mapper.updateByPrimaryKey(client);
            session.commit();
            cacheService.updateClient(client);
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 删除客户端
     * @param address
     */
    public void deleteClient(String address)
    {
        if(address==null || "".equals(address))
            return;
        SqlSession session = dbservice.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            mapper.deleteByPrimaryKey(address);
            session.commit();
            cacheService.removeClient(address);
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 查询客户端集合
     * @return
     */
    public List<RadClient> getClients()
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadClientMapper mapper = session.getMapper(RadClientMapper.class);
            List<RadClient> cs= mapper.selectByExample(null);
            return cs;
        }
        finally
        {
            session.close();
        }
    }
    

    /**
     * 查询管理员
     * @param loginName
     * @return
     */
    public RadAdmin getAdmin(String loginName)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadAdminMapper mapper = session.getMapper(RadAdminMapper.class);
            RadAdmin admin = mapper.selectByPrimaryKey(loginName);
            return admin;
        }
        finally
        {
            session.close();
        }
    }
    
    /**
     * 修改管理员
     * @param admin
     */
    public void updateAdmin(RadAdmin admin)
    {
        SqlSession session = dbservice.openSession();
        try
        {
            RadAdminMapper mapper = session.getMapper(RadAdminMapper.class);
            mapper.updateByPrimaryKey(admin);
            session.commit();
        }
        finally
        {
            session.close();
        }
    }
}
