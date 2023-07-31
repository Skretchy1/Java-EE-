
package si.um.feri.jee.sample.jsf.email;

import java.io.Serializable;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


import javax.naming.InitialContext;
import javax.naming.NamingException;



public class MailSender implements Serializable {

    private static final long serialVersionUID = 1544680932114626710L;

    InitialContext ic = new InitialContext();
    private Session mySession = (Session)ic.lookup("java:jboss/mail/jovan");

    private String to;

    private String from;

    private String subject;

    private String body;

    public MailSender() throws NamingException {
    }

    public Session getMySession() {
        return mySession;
    }

    public void setMySession(Session mySession) {
        this.mySession = mySession;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


//    public void sendingMail() {
//        this.to = "tryout453@gmail.com";
//        this.subject = "Text";
//        this.body = "Textt";
//        this.from = "tryout453@gmail.com"; //Toj sto praka
//        send();
//    }
    public void sendingMail(String to,String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.from = "tryout453@gmail.com"; //Toj sto praka
        send();
    }
    public void send() {
        try {
            Message message = new MimeMessage(mySession);
            message.setFrom(new InternetAddress(from));
            Address toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setContent(body, "text/plain");
            Transport.send(message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}