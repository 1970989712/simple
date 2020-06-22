package com.zjl.comp.message;

import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

/**
 * @author zhoujl
 * @date 2020/4/2
 */
@Component
public class sendMessage{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;
    
    public void process(Map map) {
        String mssage =  "您的账号于"+DateUtil.format(new Date())+"在"+map.get("addr")+"登录,请注意是否本人操作";
        logger.info("收到消息:"+mssage);
        if(null==(String) map.get("mailbox")) throw new MyException("用户邮箱不存在！");
        try {
            StringBuffer messageText=new StringBuffer();
            messageText.append("<div>"+mssage+"</div>");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper hleper = new MimeMessageHelper(message,true);
            hleper.setFrom("zjl_simple@qq.com","simple-用户管理中心");
            hleper.setTo((String) map.get("mailbox"));
            hleper.setSubject("simple-系统提示您");
            hleper.setText(messageText.toString(),true);
            mailSender.send(message);
        } catch (Exception e) {
            logger.error(map.get("mailbox")+":发送邮件时发生异常!", e);
        }
    }

}



