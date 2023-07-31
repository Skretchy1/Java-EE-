package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import si.um.feri.jee.sample.jsf.Daos.PacientDao;
import si.um.feri.jee.sample.jsf.Daos.PacientMemoryDao;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("jovan")
@SessionScoped
public class PacientJSFBean implements Serializable {
    private static final long serialVersionUID = 8921711242052520843L;

    Logger log=Logger.getLogger(PacientJSFBean.class.toString());

//    private PacientDao dao= PacientMemoryDao.getInstancaPacienta();

    @EJB
    private PacientDao dao;


    private Pacient selectedPacient=new Pacient();

    private String selectedEmail;

    public PacientJSFBean() throws NamingException {
    }

    public List<Pacient> getAllPeople() throws Exception {
        return dao.getAll();
    }

    public String savePerson() throws Exception {
        dao.save(selectedPacient);
//        return "all";
        return null;
    }

    public void deletePerson(Pacient o) throws Exception {
        dao.delete(o);
    }

    public void setSelectedEmail(String email) throws Exception {

            selectedEmail = email;
            selectedPacient = dao.findByEmail(email);
            if (selectedPacient == null) selectedPacient = new Pacient();

    }

    public String getSelectedEmail() {
        return selectedEmail;
    }

    public Pacient getSelectedPerson() {
        return selectedPacient;
    }

    public void setSelectedPerson(Pacient selectedPerson) {
        this.selectedPacient = selectedPerson;
    }
}
