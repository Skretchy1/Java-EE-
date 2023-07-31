package si.um.feri.jee.sample.jsf.Daos;


import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static java.nio.file.Files.delete;

@Stateless
@Local(ZdravnikDao.class)
public class ZdravnikMemoryDao implements ZdravnikDao{

    @PersistenceContext
    EntityManager em;
    private ZdravnikMemoryDao () {
        log.info("ZA SINGLETON");
    }

//    private static ZdravnikMemoryDao instancaZdravnika = new ZdravnikMemoryDao();
//
//    public static ZdravnikMemoryDao getInstanca() {
//        return instancaZdravnika;
//    }


    Logger log = Logger.getLogger(PacientMemoryDao.class.toString());

//    private List<Zdravnik> zdravniki= Collections.synchronizedList(new ArrayList<Zdravnik>());


    @Override
    public List<Zdravnik> getAll(){
        log.info("DAO: get all");
        return em.createQuery("select z from Zdravnik z").getResultList();
    }
//    @Override
//    public Zdravnik find(String email)  {
//        log.info("DAO: finding "+email);
//        for (Zdravnik o : zdravniki)
//            if (o.getEmail().equals(email))
//                return o;
//        return null;
//    }

    @Override
    public Zdravnik findByEmail(String email) {
        log.info("Finding Doctor with Email: " + email);
//        for(Zdravnik p : zdravniki)
//            if(p.getEmail().equals(email))
//                return p;
//        return null;
        return (Zdravnik) em.createQuery("select z from Zdravnik z where z.email = ?1").setParameter(1, email).getSingleResult();
    }

    @Override
    public void save(Zdravnik o)  {
        log.info("DAO: saving "+o);
//        if(find(o.getEmail())!=null) {
//            log.info("DAO: editing "+o);
//            delete(o.getEmail());
//        }
//        zdravniki.add(o);
        em.persist(o);
    }

    @Override
    public void delete(Zdravnik z) {
        log.info("DAO: deleting " + z);
//        for (Iterator<Zdravnik> i = zdravniki.iterator(); i.hasNext();) {
//            if (i.next().getEmail().equals(email))
//                i.remove();
//        }
        em.remove(z);
    }



}
