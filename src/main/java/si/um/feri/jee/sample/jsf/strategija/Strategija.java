package si.um.feri.jee.sample.jsf.strategija;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

public interface Strategija {
    public void metoda(Zdravnik z, Pacient p, String povej);
}
