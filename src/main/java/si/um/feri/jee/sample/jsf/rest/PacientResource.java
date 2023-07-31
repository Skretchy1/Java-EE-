package si.um.feri.jee.sample.jsf.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.jee.sample.jsf.Daos.PacientDao;
import si.um.feri.jee.sample.jsf.email.LocalSender;
import si.um.feri.jee.sample.jsf.email.MailManagerBean;
import si.um.feri.jee.sample.jsf.email.RemoteSender;
import si.um.feri.jee.sample.jsf.vao.Pacient;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

@Path("/pacient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientResource {

    @EJB
    PacientDao pacientDao;
    @EJB
    RemoteSender manager;

    @GET
    public List<Pacient> getAll(){
        return pacientDao.getAll();
    }

    @GET
    @Path("/{email}")
    public Pacient getByEmail(@PathParam("email") String email){
        return pacientDao.findByEmail(email);
    }

    @POST
    public void addPat(Pacient p){
        pacientDao.save(p);
    }

    @PUT
    @Path("/{patEmail}")
    public void izberiZdravnik(@PathParam("patEmail") String p, Zdravnik z){
        Pacient pat = pacientDao.findByEmail(p);
        manager.dodajNoviZdravnik(z.getEmail(), p);
    }

}
