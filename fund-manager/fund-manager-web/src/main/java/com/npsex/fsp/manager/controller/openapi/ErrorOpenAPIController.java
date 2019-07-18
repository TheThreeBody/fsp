package com.npsex.fsp.manager.controller.openapi;

import com.npsex.fsp.commons.utils.EmailUtil;
import com.npsex.fsp.commons.utils.JsonUtils;
import com.npsex.fsp.manager.support.shiro.filter.ShiroUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.AuthenticationFailedException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dongwen on 2017/8/17.
 */

@Controller
@Scope("prototype")
@RequestMapping(ShiroUtils.OPENAPI + "/error")
public class ErrorOpenAPIController {

    private static final Logger logger = LogManager.getLogger(ErrorOpenAPIController.class);

    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    @ResponseBody
    public Object errorNoticeEmail(@RequestBody String json) throws AuthenticationFailedException, IOException {
        String result ="{\"status\":true,\"msg\":\"成功\",\"content\":\"null\"}";
        Map resultMap = JsonUtils.Json2Map(result);

        Map receiveMap = JsonUtils.Json2Map(json);

        String subject = (String)receiveMap.get("subject");
        String textMsg = (String)receiveMap.get("textMsg");
        boolean hasSendA = emailUtil.send126MailStrings("piwenqiang@os.vcredit.com;lizushan@vcredit.com",subject,textMsg);

        if(hasSendA) {
            String content = "已发送邮件";
            resultMap.put("msg","成功");
            resultMap.put("content",content);
        }

        return result;
    }

}
