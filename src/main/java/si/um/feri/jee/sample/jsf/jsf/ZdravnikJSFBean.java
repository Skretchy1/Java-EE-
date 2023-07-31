package si.um.feri.jee.sample.jsf.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import si.um.feri.jee.sample.jsf.Daos.*;
import si.um.feri.jee.sample.jsf.email.LocalSender;
import si.um.feri.jee.sample.jsf.email.MailSender;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import javax.naming.NamingException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named("parttwo")
@SessionScoped
public class ZdravnikJSFBean implements Serializable {

    private static final long serialVersionUID = 8921711242052520843L;

    Logger log=Logger.getLogger(PacientJSFBean.class.toString());

//    private ZdravnikDao dao = ZdravnikMemoryDao.getInstanca();
//    private PacientDao pacDao = PacientMemoryDao.getInstancaPacienta();
    @EJB
    private ZdravnikDao dao;

    @EJB
    private PacientDao pacDao;

    @EJB
    private ObiskDao obiskDao;

    private Zdravnik selectedZdravnik = new Zdravnik();

    private String selectedEmail;
    private String noviPacientEmail;
    private String noviZdravnikEmail;

    public ZdravnikJSFBean() throws NamingException {
    }

    public Zdravnik getSelectedZdravnik() {
        return selectedZdravnik;
    }

    public void setSelectedZdravnik(Zdravnik selectedZdravnik) {
        this.selectedZdravnik = selectedZdravnik;
    }

    public String getNoviPacientEmail() {
        return noviPacientEmail;
    }

    public void setNoviPacientEmail(String noviPacientEmail) {
        this.noviPacientEmail = noviPacientEmail;
    }

    public String getNoviZdravnikEmail() {
        return noviZdravnikEmail;
    }

    public void setNoviZdravnikEmail(String noviZdravnikEmail) {
        this.noviZdravnikEmail = noviZdravnikEmail;
    }

    ////////////////////////////

//    private PacientDao pacientDao = new PacientMemoryDao();
    private Pacient pacient = new Pacient();

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public List<Pacient> getSeznamPacienta() throws Exception{
        return pacDao.getAll();
    }

    public void addPacient() throws NamingException {
        pacDao.save(pacient);
        pacient = new Pacient();
    }


    public String savePactient() throws Exception {
        pacDao.save(pacient);
//        return "all";
        return null;
    }

    public void deletePerson(Pacient o) throws Exception {
        pacDao.delete(o);
    }
    private String selectedEmailNaPacienta;

    public void setSelectedEmailNaPacienta(String email) throws Exception {
        selectedEmailNaPacienta = email;
        pacient = pacDao.findByEmail(email);
        if(pacient == null) pacient = new Pacient();
    }

    public String getSelectedEmailNaPacienta() {
        return selectedEmailNaPacienta;
    }

    public Pacient getSelectedPacient() {
        return pacient;
    }

    public void setSelectedPacient(Pacient selectedPacient) {
        this.pacient = selectedPacient;
    }

    List<Pacient> pacBrezZdravniki = new ArrayList<Pacient>();

    public void vsiBrezZdravnika() throws Exception {
        for(Pacient pac : getSeznamPacienta()){
            if (pac.getIzbranZdravnik() == null) {
                pacBrezZdravniki.add(pac);
            }
        }
    }

    public List<Pacient> getPacBrezZdravniki() {
        return pacBrezZdravniki;
    }

    public void setPacBrezZdravniki(List<Pacient> pacBrezZdravniki) {
        this.pacBrezZdravniki = pacBrezZdravniki;
    }


    List<Integer> stevilo = new ArrayList<Integer>();

    int stevilo1 = 0;
    public void vsiPacSZdravnikom() throws Exception {
        stevilo1 = 0;
        for(Pacient neki : getSeznamPacienta()){
            if (neki.getIzbranZdravnik() != null){
                stevilo.clear();
                stevilo1 += 1;
                stevilo.add(stevilo1);
            }
        }
    }

    public List<Integer> getStevilo() {
        return stevilo;
    }

    public void setStevilo(List<Integer> stevilo) {
        this.stevilo = stevilo;
    }


    public void confirm() {
        addMessage("Potrdeno", "Sprejeli ste");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    ////////////////////////////

    public List<Zdravnik> getAllPeople() throws Exception {
        return dao.getAll();
    }

    public String savePerson() throws Exception {
        dao.save(selectedZdravnik);
//        return "all";
        return null;
    }

    public void deletePerson(Zdravnik o) throws Exception {
        dao.delete(o);
    }



    public void setSelectedEmail(String email) throws Exception {

            selectedEmail = email;
            selectedZdravnik = dao.findByEmail(email);
            if (selectedZdravnik == null) selectedZdravnik = new Zdravnik();

    }

    public String getSelectedEmail() {
        return selectedEmail;
    }

    public Zdravnik getSelectedPerson() {
        return selectedZdravnik;
    }

    public void setSelectedPerson(Zdravnik selectedPerson) {
        this.selectedZdravnik = selectedPerson;
    }


    // Za izbira pacientov

    public void addTestPacient() throws NamingException {
        Pacient novi = new Pacient("SomeHuman", "Sick", "Maribor", "M", 123456789, "nekaAlergija", "kronicneBolezni", "pretekleBolezni", selectedZdravnik,"NekateriEmail");
        selectedZdravnik.getSteviloPacienti().add(novi);
    }

    public void dodajNoviPacient(){
        Pacient novi = pacDao.findByEmail(noviPacientEmail);
        novi.setIzbranZdravnik(selectedZdravnik);
        selectedZdravnik.getSteviloPacienti().add(novi);
    }

    MailSender mailSender = new MailSender();



    List<Integer> listaKvote = new ArrayList<Integer>();
    int kvota1 = 0;
//    public void dodajNoviZdravnik1() {
//        Zdravnik novi = dao.findByEmail(noviZdravnikEmail);
//        if (novi.getKvota() <= 10) {
//            pacient.setIzbranZdravnik(novi);
//            novi.getSteviloPacienti().add(pacient);
//            kvota1 = novi.getKvota();
//            kvota1 = kvota1 + 1;
//            listaKvote.add(kvota1);
//            try {
//                mailSender.sendingMail(novi.getEmail(), "Opozorilo","Uspesno ste izbrali zdravnika.");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            //Poslja meil na pacientot deka zdravnikot e full
//            try {
//                mailSender.sendingMail(pacient.getEmail(), "Opozorilo","Zdravnik je dosegel maksimalno število pacientov.");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    @EJB
    private LocalSender sender;

    public void dodajNoviZdravnik1 () {
        Zdravnik d = dao.findByEmail(noviZdravnikEmail);
        sender.dodajNoviZdravnik(d.getEmail(), pacient.getEmail());
    }


    private String zdrNaObisk;
    private String pacNaObsik;

    private String noviPosebnosti;
    private String noviZdravila;

    public String getZdrNaObisk() {
        return zdrNaObisk;
    }

    public void setZdrNaObisk(String zdrNaObisk) {
        this.zdrNaObisk = zdrNaObisk;
        this.selectedZdravnik = dao.findByEmail(zdrNaObisk);
    }

    public String getPacNaObsik() {
        return pacNaObsik;
    }

    public void setPacNaObsik(String pacNaObsik) {
        this.pacNaObsik = pacNaObsik;
        this.pacient = pacDao.findByEmail(pacNaObsik);
    }

    public String getNoviPosebnosti() {
        return noviPosebnosti;
    }

    public void setNoviPosebnosti(String noviPosebnosti) {
        this.noviPosebnosti = noviPosebnosti;
    }

    public String getNoviZdravila() {
        return noviZdravila;
    }

    public void setNoviZdravila(String noviZdravila) {
        this.noviZdravila = noviZdravila;
    }

    public void kreirajObisk() throws NamingException {
        Obisk novObisk = new Obisk(new Date(System.currentTimeMillis()), selectedZdravnik, pacient, noviPosebnosti, noviZdravila);
        obiskDao.save(novObisk);
    }
}
