
package metiers;

import entities.UniteEnseignement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UniteEnseignementBusiness {

    private static List<UniteEnseignement> unitesEnseignement;

    public UniteEnseignementBusiness() {
        unitesEnseignement = new ArrayList<>();

        unitesEnseignement.add(new UniteEnseignement(1, "Informatique", "Ahmed", 5, 1));
        unitesEnseignement.add(new UniteEnseignement(2, "Mathematiques", "Sami", 4, 2));
        unitesEnseignement.add(new UniteEnseignement(3, "Physique", "Ali", 3, 1));
        unitesEnseignement.add(new UniteEnseignement(4, "Infographie", "Amira", 2, 2));
        unitesEnseignement.add(new UniteEnseignement(5, "Chimie", "Mouna", 4, 3));
    }

    public UniteEnseignement getUEByCode(int code) {
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getCode() == code) {
                return ue;
            }
        }
        return null;
    }

    public boolean addUniteEnseignement(UniteEnseignement ue) {
        if (getUEByCode(ue.getCode()) != null) {
            return false;
        }
        return unitesEnseignement.add(ue);
    }

    public List<UniteEnseignement> getUEByDomaine(String domaine) {
        List<UniteEnseignement> resultat = new ArrayList<>();

        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getDomaine().equalsIgnoreCase(domaine)) {
                resultat.add(ue);
            }
        }
        return resultat;
    }

    public List<UniteEnseignement> getUEBySemestre(int semestre) {
        List<UniteEnseignement> resultat = new ArrayList<>();

        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getSemestre() == semestre) {
                resultat.add(ue);
            }
        }
        return resultat;
    }

    public boolean deleteUniteEnseignement(int code) {
        Iterator<UniteEnseignement> iterator = unitesEnseignement.iterator();

        while (iterator.hasNext()) {
            UniteEnseignement ue = iterator.next();

            if (ue.getCode() == code) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateUniteEnseignement(int code, UniteEnseignement updatedUE) {
        for (int i = 0; i < unitesEnseignement.size(); i++) {
            if (unitesEnseignement.get(i).getCode() == code) {
                updatedUE.setCode(code);
                unitesEnseignement.set(i, updatedUE);
                return true;
            }
        }
        return false;
    }

    public List<UniteEnseignement> getListeUE() {
        return unitesEnseignement;
    }

    public void setListeUE(List<UniteEnseignement> liste) {
        unitesEnseignement = liste;
    }
}
