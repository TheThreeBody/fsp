package com.npsex.fsp.commons.utils;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import javax.mail.AuthenticationFailedException;

/**
 * 
 * @ClassName: EmailUtil
 * @Description: 邮件发送工具类,封装了jodd的mail工具类
 * @date 2016年7月12日 下午4:22:12
 *
 */
@Component
public class EmailUtil {

    @Value("${mail.vcredit.username}")
    private String USER_126;

    @Value("${mail.vcredit.password}")
    private String PASSWORD_126;


    /**
     * 发送126邮箱
     *
     * @param toMails
     * @param subject
     * @param text
     * @return
     */
    @SuppressWarnings("rawtypes")
    public boolean send126Mail(String[] toMails, String subject, String text) throws AuthenticationFailedException,MailException {

        Email email = Email.create().from(USER_126).to(toMails)
                .subject(subject).addText(text);
//        SmtpServer smtpServer = SmtpServer.create("smtp.126.com")
//                .authenticateWith(USER_126, PASSWORD_126);
        SmtpServer smtpServer = SmtpServer.create("smtp.vcredit.com")
                .authenticateWith(USER_126, PASSWORD_126);

        SendMailSession session = smtpServer.createSession();
        session.open();
        session.sendMail(email);
        session.close();
        return true;
    }

    public boolean send126MailStrings(String target, String subject, String text) throws AuthenticationFailedException,MailException {
        String[] toMails  = target.split(";");
        return send126Mail(toMails, subject, text);
    }

    public boolean send126Mail(String target, String subject, String text) throws AuthenticationFailedException,MailException {
        String[] toMail = {target};
        return send126Mail(toMail,subject,text);
    }
}
