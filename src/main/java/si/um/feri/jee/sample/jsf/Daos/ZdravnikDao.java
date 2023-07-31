package si.um.feri.jee.sample.jsf.Daos;

import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;


public interface ZdravnikDao {
    List<Zdravnik> getAll();
//    Zdravnik find(String email);
    Zdravnik findByEmail(String email);
    void save(Zdravnik o);
    void delete(Zdravnik z);
}
