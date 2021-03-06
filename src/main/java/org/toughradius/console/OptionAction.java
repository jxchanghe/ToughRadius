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

import java.io.IOException;

import org.toughradius.annotation.AuthAdmin;
import org.toughradius.common.ValidateUtil;
import org.toughradius.model.RadOption;
import org.xlightweb.BadMessageException;
import org.xlightweb.IHttpExchange;
import org.xlightweb.IHttpRequest;
import org.xlightweb.Mapping;

@AuthAdmin
@Mapping( { "/option" })
public class OptionAction  extends FliterAction {
    
    public void doGet(IHttpExchange http) throws IOException, BadMessageException {
        IHttpRequest req = http.getRequest();
        req.setAttribute("options",baseServ.getOptions());
        http.send(freemaker.render(http, "option"));
        
    }

    public void doPost(IHttpExchange http) throws IOException, BadMessageException {
        IHttpRequest req = http.getRequest();
        String oname = req.getParameter("optionName");
        String ovalue = req.getParameter("optionValue");
        if(ValidateUtil.isEmpty(oname)||ValidateUtil.isEmpty(ovalue))
        {
            http.send(freemaker.renderWithAlert(http, "error","无效的参数值"));
            return;
        }
        
        RadOption option = baseServ.getOption(oname);
        if(option==null)
        {
            http.send(freemaker.renderWithAlert(http, "error","无效的参数值"));
            return;
        }
        option.setOptionValue(ovalue);
        baseServ.updateOption(option);
        http.sendRedirect("/option");
        
    }
}
