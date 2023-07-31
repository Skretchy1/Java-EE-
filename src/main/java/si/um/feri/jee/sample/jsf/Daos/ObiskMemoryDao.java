package si.um.feri.jee.sample.jsf.Daos;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.jsf.vao.Obisk;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Local(ObiskDao.class)
public class ObiskMemoryDao implements ObiskDao{

    Logger log= Logger.getLogger(PacientMemoryDao.class.toString());

    @PersistenceContext
    EntityManager em;

//    private List<Obisk> obiski = Collections.synchronizedList(new ArrayList<Obisk>());




    @Override
    public List<Obisk> getAll(){
        log.info("DAO: get all");
//        return obiski;
        return em.createQuery("select o from Obisk o").getResultList();
    }
    @Override
    public Obisk find(Long id)  {
        log.info("DAO: finding "+id);
//        for (Obisk o : obiski)
//            if (o.getEmail().equals(email))
//                return o;
        return em.find(Obisk.class, id);
    }

    @Override
    public void save(Obisk o)  {
        log.info("DAO: saving "+o);
//        if(find(o.getEmail())!=null) {
//            log.info("DAO: editing "+o);
//            delete(o.getEmail());
//        }
//        obiski.add(o);
        em.persist(o);
    }
    @Override
    public void delete(Obisk o) {
        log.info("DAO: deleting ");
//        for (Iterator<Pacient> i = obiski.iterator(); i.hasNext();) {
//            if (i.next().getEmail().equals(email))
//                i.remove();
//        }
        em.remove(o);
    }
}
