package si.um.feri.jee.sample.jsf.observers;

import si.um.feri.jee.sample.jsf.email.MailSender;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import javax.naming.NamingException;

public class ObserverNewDoc implements Observer{

    private Pacient pacient;

    private MailSender mailSender = new MailSender();
    @Override
    public void alert() {
        mailSender.sendingMail(pacient.getEmail(),"PLEASE WORK","PLEASE WORK AGAIN");
    }

    public ObserverNewDoc (Pacient pacient) throws NamingException {
        this.pacient = pacient;
//        pacient.addObserver(this);
    }
}
