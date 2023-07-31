package si.um.feri.jee.sample.jsf.Daos;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.jsf.vao.Pacient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Local(PacientDao.class)
public class PacientMemoryDao implements PacientDao{
    Logger log= Logger.getLogger(PacientMemoryDao.class.toString());
//    private List<Pacient> pacienti= Collections.synchronizedList(new ArrayList<Pacient>());

    @PersistenceContext
    EntityManager em;

    private PacientMemoryDao () {
        log.info("ZA SINGLETON PACIENT");
    }

    private static PacientMemoryDao instancaPacienta = new PacientMemoryDao();

    public static PacientMemoryDao getInstancaPacienta() {
        return instancaPacienta;
    }

    @Override
    public List<Pacient> getAll(){
        log.info("DAO: get all");
//        return pacienti;
        return em.createQuery("select p from Pacient p").getResultList();
    }
//    @Override
//    public Pacient find(String email)  {
//        log.info("Finding Patient with Email: "+email);
////        for (Pacient o : pacienti)
////            if (o.getEmail().equals(email))
////                return o;
////        return null;
//        return (Pacient) em.createQuery("select p from Pacient p where p.email = ?1").setParameter(1, email).getSingleResult();
//    }

    @Override
    public void save(Pacient o)  {
        log.info("DAO: saving "+o);
//        if(find(o.getEmail())!=null) {
//            log.info("DAO: editing "+o);
//            delete(o.getEmail());
//        }
//        pacienti.add(o);
        em.persist(o);
    }

    @Override
    public void delete(Pacient p) {
        log.info("DAO: deleting ");
//        for (Iterator<Pacient> i = pacienti.iterator(); i.hasNext();) {
//            if (i.next().getEmail().equals(email))
//                i.remove();
//        }
        em.remove(p);
    }
    @Override
    public Pacient findByEmail(String email) {
        log.info("Finding Patient with Email: " + email);
//        for(Pacient p : pacienti)
//            if(p.getEmail().equals(email))
//                return p;
//        return null;
        return (Pacient) em.createQuery("select p from Pacient p where p.email = ?1").setParameter(1, email).getSingleResult();
    }

}
