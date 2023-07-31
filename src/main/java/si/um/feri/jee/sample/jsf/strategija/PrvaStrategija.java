package si.um.feri.jee.sample.jsf.strategija;

import si.um.feri.jee.sample.jsf.email.MailSender;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;

public class PrvaStrategija implements Strategija{

    private MailSender mailSender = new MailSender();

    public PrvaStrategija() throws NamingException {
    }

    @Override
    public void metoda(Zdravnik z, Pacient p, String povej) {
        String body = "Dr. " + z.getIme() + " " + p.getPriimek() + " je vam predpisal posebnosti: " + povej;

        mailSender.sendingMail(p.getEmail(), "Novi Obisk", body);
    }
}
