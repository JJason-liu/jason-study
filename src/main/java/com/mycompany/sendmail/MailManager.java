package com.mycompany.sendmail;

import java.io.FileReader;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 有泪的北极星 qq:765798166
 * @date 2018-1-22 16:54:36
 */
/**
 *
 * jar： https://github.com/javaee/javamail/releases
 */
public class MailManager {

    private static final Logger logger = LoggerFactory.getLogger(MailManager.class);
    public static MailManager instance;

    public static MailManager getInstance() {
        if (instance == null) {
            instance = new MailManager();
        }
        return instance;
    }

    public void sendMail(String receiveAddress, String receiveName, String subject, String content) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\main\\java\\com\\mycompany\\sendmail\\properties\\Mail.properties"));
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        Transport transport = session.getTransport();
        Mail mail = createMail(session, properties.getProperty("myEmailAccount"), properties.getProperty("senderName"), receiveAddress, receiveName, subject, content);
        transport.connect(properties.getProperty("myEmailAccount"), properties.getProperty("myEmailPassword"));
        transport.addTransportListener(new TransportListener() {
            @Override
            public void messageDelivered(TransportEvent te) {
                logger.debug("send success!!!");
            }

            @Override
            public void messageNotDelivered(TransportEvent te) {
                logger.debug("send failure!!!");
            }

            @Override
            public void messagePartiallyDelivered(TransportEvent te) {
                logger.debug("send part success!!!");
            }
        });
        transport.sendMessage(mail, mail.getAllRecipients());
        transport.close();
    }

    public Mail createMail(Session session, String sendAddress, String senderName, String receiveAddress, String receiveName, String subject, String content) throws Exception {
        //1.create
        Mail mail = new Mail(session);
        //2.sender
        mail.setFrom(new InternetAddress(sendAddress, senderName, "UTF-8"));
        //3.receive
        mail.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress, receiveName, "UTF-8"));
        //4.subject
        mail.setSubject(subject, "UTF-8");
        //5.Content
        mail.setContent(content, "text/html;charset=UTF-8");
        // 6. save
        mail.saveChanges();
        return mail;
    }
}
