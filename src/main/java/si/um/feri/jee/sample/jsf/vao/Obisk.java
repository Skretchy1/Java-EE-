package si.um.feri.jee.sample.jsf.vao;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import si.um.feri.jee.sample.jsf.strategija.DrugaStrategija;
import si.um.feri.jee.sample.jsf.strategija.PrvaStrategija;
import si.um.feri.jee.sample.jsf.strategija.Strategija;

import javax.naming.NamingException;
import java.util.Date;

@Entity
public class Obisk {

    @JsonbTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Zdravnik zdravnik;
    @OneToOne(fetch = FetchType.EAGER)
    private Pacient pacient;
    private Date datum;
    private String posebnosti;
    private String zdravila;

    public Obisk(){}
    public Obisk(Long id, Zdravnik zdravnik, Pacient pacient, Date datum, String posebnosti, String zdravila) {
        this.id = id;
        this.zdravnik = zdravnik;
        this.pacient = pacient;
        this.datum = datum;
        this.posebnosti = posebnosti;
        this.zdravila = zdravila;
    }

    public Obisk(Date datum, Zdravnik zdravnik, Pacient pacient, String posebnosti, String zdravila) throws NamingException {
        this.zdravnik = zdravnik;
        this.pacient = pacient;
        this.datum = datum;
        this.posebnosti = posebnosti;
        this.zdravila = zdravila;
        String podatek = "";
        Strategija s = null;
        if (posebnosti != "" && posebnosti != null){
            s = new PrvaStrategija();
            podatek = posebnosti;
        }
        if(zdravila != "" && zdravila != null){
            s = new DrugaStrategija();
            podatek = zdravila;
        }
        s.metoda(zdravnik, pacient, podatek);
    }
    public Zdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(Zdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getPosebnosti() {
        return posebnosti;
    }

    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }

    public String getZdravila() {
        return zdravila;
    }

    public void setZdravila(String zdravila) {
        this.zdravila = zdravila;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
