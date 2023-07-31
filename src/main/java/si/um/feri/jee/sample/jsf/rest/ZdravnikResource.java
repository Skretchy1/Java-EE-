package si.um.feri.jee.sample.jsf.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.jee.sample.jsf.Daos.PacientDao;
import si.um.feri.jee.sample.jsf.Daos.ZdravnikDao;
import si.um.feri.jee.sample.jsf.vao.Zdravnik;

import java.util.List;

@Path("/zdravnik")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZdravnikResource{

    @EJB
    ZdravnikDao zdravnikDao;

    @GET
    public List<Zdravnik> getAll(){
        return zdravnikDao.getAll();
    }

    @GET
    @Path("/{email}")
    public Zdravnik getByEmail(@PathParam("email") String email){
        return zdravnikDao.findByEmail(email);
    }

    @POST
    public void addDoc(Zdravnik z){
        zdravnikDao.save(z);
    }
}
