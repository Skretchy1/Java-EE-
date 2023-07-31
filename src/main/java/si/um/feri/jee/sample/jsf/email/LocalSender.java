package si.um.feri.jee.sample.jsf.email;

import jakarta.ejb.Local;
import si.um.feri.jee.sample.jsf.vao.Pacient;

@Local
public interface LocalSender {
    public void dodajNoviZdravnik(String noviZdravnikEmail,String pacientEmail);
}
