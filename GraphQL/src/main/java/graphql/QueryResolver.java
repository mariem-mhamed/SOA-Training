
package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class QueryResolver implements GraphQLRootResolver {

    private final UniteEnseignementBusiness helper;
    private final ModuleBusiness moduleBusiness;

    public QueryResolver() {
        helper = new UniteEnseignementBusiness();
        moduleBusiness = new ModuleBusiness();
    }

    // Récupérer toutes les UE (code existant)
    public List<UniteEnseignement> allUEs() {
        return helper.getListeUE();
    }

    // 1. Récupérer tous les modules
    public List<Module> allModules() {
        return moduleBusiness.getAllModules();
    }

    // 4. Récupérer les modules par type
    public List<Module> modulesByType(Module.TypeModule type) {
        return moduleBusiness.getModulesByType(type);
    }

    // 5. Récupérer un module par matricule
    public Module moduleByMatricule(String matricule) {
        return moduleBusiness.getModuleByMatricule(matricule);
    }
}
