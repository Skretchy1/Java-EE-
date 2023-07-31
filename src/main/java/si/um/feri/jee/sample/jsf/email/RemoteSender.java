package si.um.feri.jee.sample.jsf.email;

import jakarta.ejb.Remote;
import si.um.feri.jee.sample.jsf.vao.Pacient;

@Remote
public interface RemoteSender {

    public void dodajNoviZdravnik(String noviZdravnikEmail,String pacientEmail);
}
