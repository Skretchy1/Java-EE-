package si.um.feri.jee.sample.jsf.Daos;

import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

public interface ObiskDao {
    List<Obisk> getAll();
    Obisk find(Long id);
    void save(Obisk o);
    void delete(Obisk o);
}
