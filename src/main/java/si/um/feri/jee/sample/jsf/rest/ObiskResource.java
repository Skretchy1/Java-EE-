package si.um.feri.jee.sample.jsf.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.jee.sample.jsf.Daos.ObiskDao;
import si.um.feri.jee.sample.jsf.vao.Obisk;

import java.util.List;

@Path("/obisk")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObiskResource {

    @EJB
    ObiskDao dao;

    @GET
    public List<Obisk> getAll(){
        return dao.getAll();
    }

    @GET
    @Path("/{id}")
    public Obisk getByEmail(@PathParam("id") Long id){
        return dao.find(id);
    }

    @POST
    public void addPat(Obisk o){
        dao.save(o);
    }

}
