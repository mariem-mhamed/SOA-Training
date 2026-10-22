
package business;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {

    private static final List<Module> modules = new ArrayList<>();

    private final UniteEnseignementBusiness uniteEnseignementBusiness =
            new UniteEnseignementBusiness();

    // Initialisation des données de test
    public ModuleBusiness() {
        if (modules.isEmpty()) {
            modules.add(new Module(
                    "M101", "Algorithmique", 3, 30,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)
            ));

            modules.add(new Module(
                    "M102", "Base de données", 2, 20,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)
            ));

            modules.add(new Module(
                    "M201", "Communication", 1, 15,
                    Module.TypeModule.TRANSVERSAL,
                    uniteEnseignementBusiness.getUEByCode(2)
            ));
        }
    }

    // 1. Récupérer tous les modules
    public List<Module> getAllModules() {
        return modules;
    }

    // 2. Ajouter un module
    public boolean addModule(Module module) {
        if (module == null ||
                module.getMatricule() == null ||
                module.getUniteEnseignement() == null) {
            return false;
        }

        if (getModuleByMatricule(module.getMatricule()) != null) {
            return false;
        }

        int code = module.getUniteEnseignement().getCode();

        UniteEnseignement ue =
                uniteEnseignementBusiness.getUEByCode(code);

        if (ue != null) {
            module.setUniteEnseignement(ue);
            return modules.add(module);
        }

        return false;
    }

    // 3. Modifier un module
    public boolean updateModule(String matricule, Module updatedModule) {
        if (matricule == null || updatedModule == null ||
                updatedModule.getUniteEnseignement() == null) {
            return false;
        }

        int code = updatedModule.getUniteEnseignement().getCode();

        UniteEnseignement ue =
                uniteEnseignementBusiness.getUEByCode(code);

        if (ue == null) {
            return false;
        }

        updatedModule.setUniteEnseignement(ue);

        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getMatricule().equals(matricule)) {
                if (!matricule.equals(updatedModule.getMatricule()) &&
                        getModuleByMatricule(
                                updatedModule.getMatricule()) != null) {
                    return false;
                }

                modules.set(i, updatedModule);
                return true;
            }
        }

        return false;
    }

    // 4. Récupérer les modules par type
    public List<Module> getModulesByType(Module.TypeModule type) {
        List<Module> result = new ArrayList<>();

        for (Module m : modules) {
            if (m.getType() == type) {
                result.add(m);
            }
        }

        return result;
    }

    // 5. Récupérer un module par matricule
    public Module getModuleByMatricule(String matricule) {
        if (matricule == null) {
            return null;
        }

        for (Module m : modules) {
            if (matricule.equals(m.getMatricule())) {
                return m;
            }
        }

        return null;
    }

    // 6. Supprimer un module
    public boolean deleteModule(String matricule) {
        Iterator<Module> iterator = modules.iterator();

        while (iterator.hasNext()) {
            Module m = iterator.next();

            if (matricule != null &&
                    matricule.equals(m.getMatricule())) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    // Récupérer les modules d'une UE spécifique
    public List<Module> getModulesByUE(UniteEnseignement ue) {
        List<Module> result = new ArrayList<>();

        if (ue == null) {
            return result;
        }

        for (Module m : modules) {
            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == ue.getCode()) {
                result.add(m);
            }
        }

        return result;
    }
}
