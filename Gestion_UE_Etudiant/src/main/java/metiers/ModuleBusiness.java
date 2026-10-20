
package metiers;

import entities.Module;
import java.util.ArrayList;
import java.util.List;

public class ModuleBusiness {

    private static List<Module> modules = new ArrayList<Module>();

    public ModuleBusiness() {
        // Initialisation avec quelques exemples
        if (modules.isEmpty()) {
            // Ajoute ici les modules de ton atelier si nécessaire
        }
    }

    // 1. Ajouter un module
    public boolean addModule(Module module) {
        return modules.add(module);
    }

    // 2. Récupérer tous les modules
    public List<Module> getAllModules() {
        return modules;
    }

    // 3. Récupérer un module par matricule
    public Module getModuleByMatricule(String matricule) {
        for (Module module : modules) {
            if (module.getMatricule().equals(matricule)) {
                return module;
            }
        }
        return null;
    }

    // 4. Supprimer un module par matricule
    public boolean deleteModule(String matricule) {
        for (Module module : modules) {
            if (module.getMatricule().equals(matricule)) {
                modules.remove(module);
                return true;
            }
        }
        return false;
    }

    // 5. Modifier un module
    public boolean updateModule(String matricule, Module updatedModule) {
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getMatricule().equals(matricule)) {
                updatedModule.setMatricule(matricule);
                modules.set(i, updatedModule);
                return true;
            }
        }
        return false;
    }

    // 6. Récupérer les modules d'une UE
    public List<Module> getModulesByUE(int codeUE) {
        List<Module> liste = new ArrayList<Module>();

        for (Module module : modules) {
            if (module.getUniteEnseignement() != null
                    && module.getUniteEnseignement().getCode() == codeUE) {
                liste.add(module);
            }
        }
        return liste;
    }
}
