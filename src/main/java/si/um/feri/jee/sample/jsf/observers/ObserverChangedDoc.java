package si.um.feri.jee.sample.jsf.observers;

import si.um.feri.jee.sample.jsf.email.MailSender;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;

public class ObserverChangedDoc implements Observer{
    private Pacient pacient;

    private MailSender mailSender = new MailSender();

    private Zdravnik stariZdravnik = null;

    public ObserverChangedDoc(Pacient pacient) throws NamingException {
        this.pacient = pacient;
    }

    @Override
    public void alert() {

        if (stariZdravnik != null){
            mailSender.sendingMail(pacient.getEmail(),"","");

        }
        stariZdravnik = pacient.getIzbranZdravnik();
    }
}
