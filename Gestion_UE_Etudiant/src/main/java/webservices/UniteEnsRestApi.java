package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UE")
public class UniteEnsRestApi {

    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUE(@QueryParam("semestre") Integer semestre,
                          @QueryParam("code") Integer code) {
        if (code != null) {
            UniteEnseignement ue = helper.getUEByCode(code);
            if (ue == null) return Response.status(404).entity("Non trouve").build();
            String result = ue.getCode() + "," + ue.getDomaine() + "," +
                    ue.getResponsable() + "," + ue.getCredits() + "," + ue.getSemestre();
            return Response.ok(result).build();
        }

        if (semestre != null) {
            List<UniteEnseignement> list = helper.getUEBySemestre(semestre);
            StringBuilder sb = new StringBuilder();
            for (UniteEnseignement u : list) {
                sb.append(u.getCode()).append(",").append(u.getDomaine()).append("|");
            }
            return Response.ok(sb.toString()).build();
        }

        List<UniteEnseignement> list = helper.getListeUE();
        StringBuilder sb = new StringBuilder();
        for (UniteEnseignement u : list) {
            sb.append(u.getCode()).append(",").append(u.getDomaine()).append("|");
        }
        return Response.ok(sb.toString()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUE(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("success").build();
        }
        return Response.status(400).entity("Erreur").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
        if (updatedUE == null) {
            return Response.status(400).entity("Données invalides").build();
        }
        if (helper.updateUniteEnseignement(code, updatedUE)) {
            updatedUE.setCode(code);
            return Response.ok("object updated").build();
        }
        return Response.status(404).entity("not found").build();
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.ok("object deleted").build();
        }
        return Response.status(404).entity("not found").build();
    }
}