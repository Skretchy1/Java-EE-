package si.um.feri.jee.sample.jsf.email;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.jee.sample.jsf.Daos.PacientDao;
import si.um.feri.jee.sample.jsf.Daos.ZdravnikDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MailManagerBean implements RemoteSender,LocalSender{


    @EJB
    private ZdravnikDao dao;

    @EJB
    private PacientDao pacDao;


    MailSender mailSender = new MailSender();



    List<Integer> listaKvote = new ArrayList<Integer>();
    int kvota1 = 0;

    public MailManagerBean() throws NamingException {
    }

    public void dodajNoviZdravnik(String noviZdravnikEmail,String pacientEmail) {
        Zdravnik novi = dao.findByEmail(noviZdravnikEmail);
        Pacient pacient = pacDao.findByEmail(pacientEmail);
        if (novi.getKvota() <= 10) {
            pacient.setIzbranZdravnik(novi);
            novi.getSteviloPacienti().add(pacient);
            kvota1 = novi.getKvota();
            kvota1 = kvota1 + 1;
            listaKvote.add(kvota1);
            try {
                mailSender.sendingMail(novi.getEmail(), "Opozorilo","Uspesno ste izbrali zdravnika.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            //Poslja meil na pacientot deka zdravnikot e full
            try {
                mailSender.sendingMail(pacient.getEmail(), "Opozorilo","Zdravnik je dosegel maksimalno število pacientov.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
