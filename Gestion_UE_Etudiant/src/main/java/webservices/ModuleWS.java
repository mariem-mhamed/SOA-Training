package webservices;

import entities.Module;
import metiers.ModuleBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleWS {

    static ModuleBusiness helper = new ModuleBusiness();

    // B.2 - GET /module/list
    @Path("/list")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAll() {
        List<Module> list = helper.getAllModules();
        if (list == null || list.isEmpty()) return Response.status(404).entity("Vide").build();
        StringBuilder sb = new StringBuilder();
        for (Module m : list) sb.append(m.getMatricule()).append(",").append(m.getNom()).append("|");
        return Response.ok(sb.toString()).build();
    }

    // B.1 - POST /module/add
    @Path("/add")
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {
        if (helper.addModule(module)) return Response.status(201).entity("Module added").build();
        return Response.status(400).entity("Module not added").build();
    }

    // B.3 - GET /module/{matricule}
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module m = helper.getModuleByMatricule(matricule);
        if (m == null) return Response.status(404).entity("Non trouve").build();
        return Response.ok(m.getMatricule() + "," + m.getNom()).build();
    }

    // B.5 - PUT /module/update/{matricule}
    @PUT
    @Path("/update/{matricule}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        if (updatedModule == null) return Response.status(400).entity("Invalide").build();
        if (helper.updateModule(matricule, updatedModule)) return Response.ok("object updated").build();
        return Response.status(404).entity("not found").build();
    }

    // B.4 - DELETE /module/delete/{matricule}
    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) return Response.ok("object deleted").build();
        return Response.status(404).entity("not found").build();
    }

    // B.6 - GET /module/ue?codeUE=X
    @GET
    @Path("/ue")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getModulesByUE(@QueryParam("codeUE") int codeUE) {
        List<Module> list = helper.getModulesByUE(codeUE);
        if (list == null || list.isEmpty()) return Response.status(404).entity("Vide").build();
        StringBuilder sb = new StringBuilder();
        for (Module m : list) sb.append(m.getMatricule()).append(",");
        return Response.ok(sb.toString()).build();
    }
}