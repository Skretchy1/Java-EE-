package si.um.feri.jee.sample.jsf.Daos;

import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.List;


public interface PacientDao {
    List<Pacient> getAll();
//    Pacient find(String email);
    Pacient findByEmail(String email);
    void save(Pacient o);

    void delete(Pacient p);
}
