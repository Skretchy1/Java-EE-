package si.um.feri.jee.sample.jsf.vao;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Zdravnik {

    public Zdravnik () {
        this("", "","","","",0);
        this.steviloPacienti = new ArrayList<Pacient>();
        steviloPacienti.size();
    }
    public Zdravnik(String ime, String priimek, String opisDijagnoze, String predpisanaZdravila,String email, int kvota) {
        this.ime = ime;
        Priimek = priimek;
        datumObiska =new Date();
        this.opisDijagnoze = opisDijagnoze;
        this.predpisanaZdravila = predpisanaZdravila;
        this.email = email;
        this.steviloPacienti = new ArrayList<Pacient>();
        this.kvota = kvota;
    }


    private String ime;
    private String Priimek;
    private String email;
    private Date datumObiska;
    private String opisDijagnoze;
    private String predpisanaZdravila;
    private List<Pacient> steviloPacienti;

    private int kvota;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return Priimek;
    }

    public void setPriimek(String priimek) {
        Priimek = priimek;
    }



    public String getOpisDijagnoze() {
        return opisDijagnoze;
    }

    public void setOpisDijagnoze(String opisDijagnoze) {
        this.opisDijagnoze = opisDijagnoze;
    }

    public String getPredpisanaZdravila() {
        return predpisanaZdravila;
    }

    public void setPredpisanaZdravila(String predpisanaZdravila) {
        this.predpisanaZdravila = predpisanaZdravila;
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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="izbranZdravnik")
    public List<Pacient> getSteviloPacienti() {
        return steviloPacienti;
    }

    public void setSteviloPacienti(List<Pacient> steviloPacienti) {
        this.steviloPacienti = steviloPacienti;
    }

    public int getKvota() {
        return kvota;
    }

    public void setKvota(int kvota) {
        this.kvota = kvota;
    }




//    @Override
//    public String toString() {
//        return "Zdravnik{" +
//                "ime='" + ime + '\'' +
//                ", Priimek='" + Priimek + '\'' +
//                ", email='" + email + '\'' +
//                ", datumObiska=" + datumObiska +
//                ", opisDijagnoze='" + opisDijagnoze + '\'' +
//                ", predpisanaZdravila='" + predpisanaZdravila + '\'' +
//                ", steviloPacienti=" + steviloPacienti +
//                '}';
//    }
}
