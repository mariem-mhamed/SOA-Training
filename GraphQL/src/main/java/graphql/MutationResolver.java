
package graphql;

import business.ModuleBusiness;
import business.UniteEnseignementBusiness;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import entities.Module;
import entities.UniteEnseignement;

public class MutationResolver implements GraphQLRootResolver {

    private final UniteEnseignementBusiness helper;
    private final ModuleBusiness moduleBusiness;

    public MutationResolver() {
        helper = new UniteEnseignementBusiness();
        moduleBusiness = new ModuleBusiness();
    }

    // Ajout d'une UE (code existant)
    public boolean addUniteEnseignement(
            int code,
            String domaine,
            String responsable,
            int credits,
            int semestre) {

        UniteEnseignement ue = new UniteEnseignement(
                code, domaine, responsable, credits, semestre
        );

        return helper.addUniteEnseignement(ue);
    }

    // 2. Ajouter un module
    public boolean addModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            int codeUE,
            Module.TypeModule type) {

        UniteEnseignement ue = helper.getUEByCode(codeUE);

        if (ue == null) {
            return false;
        }

        Module module = new Module(
                matricule,
                nom,
                coefficient,
                volumeHoraire,
                type,
                ue
        );

        return moduleBusiness.addModule(module);
    }

    // 3. Modifier un module
    public Module updateModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            int codeUE,
            Module.TypeModule type) {

        Module existingModule =
                moduleBusiness.getModuleByMatricule(matricule);

        UniteEnseignement ue = helper.getUEByCode(codeUE);

        if (existingModule == null || ue == null) {
            return null;
        }

        Module updatedModule = new Module(
                matricule,
                nom,
                coefficient,
                volumeHoraire,
                type,
                ue
        );

        boolean updated = moduleBusiness.updateModule(
                matricule, updatedModule
        );

        return updated ? updatedModule : null;
    }

    // 6. Supprimer un module
    public String deleteModule(String matricule) {

        boolean deleted = moduleBusiness.deleteModule(matricule);

        if (deleted) {
            return "Module supprime avec succes";
        }

        return "Module introuvable";
    }
}
