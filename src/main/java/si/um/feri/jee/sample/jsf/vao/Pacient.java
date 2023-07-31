package si.um.feri.jee.sample.jsf.vao;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import si.um.feri.jee.sample.jsf.observers.Observer;
import si.um.feri.jee.sample.jsf.observers.ObserverChangedDoc;
import si.um.feri.jee.sample.jsf.observers.ObserverNewDoc;

import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
public class Pacient {

    public Pacient() throws NamingException {
//        this("", "","","",1,"","","",zdravnik,"");

        this.observers = new ArrayList<Observer>();
        new ObserverNewDoc(this);
        new ObserverChangedDoc(this);
    }

    public Pacient(String ime, String priimek, String naslov, String spol, int kontakt, String alergija, String kronicneBolezni, String pretekleBolezni, Zdravnik izbranZdravnik, String email) throws NamingException {
        this.ime = ime;
        this.priimek = priimek;
        this.naslov = naslov;
        this.spol = spol;
        this.kontakt = kontakt;
        this.alergija = alergija;
        this.kronicneBolezni = kronicneBolezni;
        this.pretekleBolezni = pretekleBolezni;
        this.izbranZdravnik = izbranZdravnik;
        this.email = email;
        datumRojstva = new Date();
        this.observers = new ArrayList<Observer>();

        new ObserverNewDoc(this);
        new ObserverChangedDoc(this);
    }

    private String ime;
    private String priimek;
    private String email;
    private String naslov;
    private Date datumRojstva;
    private String spol;
    private int kontakt;
    private String alergija;
    private String kronicneBolezni;
    private String pretekleBolezni;
    private Zdravnik izbranZdravnik;

    @JsonbTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Date getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(Date datumRojstva) {
        this.datumRojstva = datumRojstva;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public int getKontakt() {
        return kontakt;
    }

    public void setKontakt(int kontakt) {
        this.kontakt = kontakt;
    }

    public String getAlergija() {
        return alergija;
    }

    public void setAlergija(String alergija) {
        this.alergija = alergija;
    }

    public String getKronicneBolezni() {
        return kronicneBolezni;
    }

    public void setKronicneBolezni(String kronicneBolezni) {
        this.kronicneBolezni = kronicneBolezni;
    }

    public String getPretekleBolezni() {
        return pretekleBolezni;
    }

    public void setPretekleBolezni(String pretekleBolezni) {
        this.pretekleBolezni = pretekleBolezni;
    }



    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    public Zdravnik getIzbranZdravnik() {
        return izbranZdravnik;
    }

    public void setIzbranZdravnik(Zdravnik izbranZdravnik) {
        this.izbranZdravnik = izbranZdravnik;
//        alertAllObservers();
    }

    @JsonbTransient
    @Id
    @GeneratedValue
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public static SimpleDateFormat getSdf() {
//        return sdf;
//    }
//
//    public static void setSdf(SimpleDateFormat sdf) {
//        Pacient.sdf = sdf;
//    }


//    Zdravnik zdravnik = new Zdravnik();

    @JsonbTransient
    @Transient
    private List<Observer> observers;


//    public void addObserver(Observer observer) {
//        observers.add(observer);
//    }
//
//    public void alertAllObservers () {
//        for (Observer o : observers){
//            o.alert();
//        }
//    }



//    @Override
//    public String toString() {
//        return "Pacient{" +
//                "ime='" + ime + '\'' +
//                ", priimek='" + priimek + '\'' +
//                ", email='" + email + '\'' +
//                ", naslov='" + naslov + '\'' +
//                ", datumRojstva=" + datumRojstva +
//                ", spol='" + spol + '\'' +
//                ", kontakt=" + kontakt +
//                ", alergija='" + alergija + '\'' +
//                ", kronicneBolezni='" + kronicneBolezni + '\'' +
//                ", pretekleBolezni='" + pretekleBolezni + '\'' +
//                ", izbranZdravnik=" + izbranZdravnik +
//                '}';
//    }


}
