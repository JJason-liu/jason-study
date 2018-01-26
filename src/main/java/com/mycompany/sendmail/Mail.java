/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sendmail;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import javax.mail.Session;

/**
 *
 * @author 有泪的北极星 qq:765798166
 * @date 2018-1-22 17:36:18
 */
public class Mail extends MimeMessage {

    public Mail(Session session) {
        super(session);
        System.out.println("com.mycompany.sendmail.Mail.<init>()");
    }

    public Mail createMail(Session session) throws Exception {
        Mail mail = new Mail(session);
        mail.setSentDate(new Date());
        return mail;
    }
}
