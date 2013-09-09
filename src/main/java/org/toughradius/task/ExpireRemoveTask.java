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
package org.toughradius.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.toughradius.annotation.Inject;
import org.toughradius.common.DateTimeUtil;
import org.toughradius.components.BaseService;
import org.toughradius.components.UserService;
import org.toughradius.constant.Constant;
import org.toughradius.constant.OptionNames;
import org.toughradius.model.RadOption;
import org.toughradius.model.RadUser;
import org.toughradius.model.RadUserExample;
import org.toughradius.model.RadUserMeta;

/**
 * @author jamiesun
 * 
 */
@Inject
public class ExpireRemoveTask implements Runnable {

    private static Log log = LogFactory.getLog(ExpireRemoveTask.class);
    private UserService userServ;
    private BaseService baseServ;

    public void setBaseServ(BaseService baseServ) {
        this.baseServ = baseServ;
    }

    public void setUserServ(UserService userServ) {
        this.userServ = userServ;
    }

    @Override
    public void run() {
        log.info("开始执行过期用户清理任务");
        try {
            RadOption edayOption = baseServ.getOption(OptionNames.USER_EXPIRED_KEEP.value());
            int eday = edayOption != null ? Integer.valueOf(edayOption.getOptionValue()) : -1;
            if (eday == -1)
                return;

            List<RadUser> users = userServ.getUsers();
            int i = 0;
            for (RadUser user : users) {
                RadUserMeta attr = userServ.getUserMeta(user.getUserName(), Constant.USER_EXPIRE.value());
                String expire = attr != null ? attr.getValue() : null;
                if (expire == null)
                    continue;
                String today = DateTimeUtil.getDateString();
                if (DateTimeUtil.compareDay(today, expire) > eday) {
                    userServ.deleteUser(user.getUserName());
                    i++;
                }
            }
            log.info("共清理" + i + "个过期用户");
        } catch (Exception e) {
            log.error("清理过期用户时错误", e);
        }

    }

}
