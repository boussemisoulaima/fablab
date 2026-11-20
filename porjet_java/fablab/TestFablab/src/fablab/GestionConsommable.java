package fablab;

import java.util.Set;
import java.util.HashSet;

public class GestionConsommable {

    private Set<Consommable> consommables;

    public GestionConsommable() {
        this.consommables = new HashSet<>();
    }

    public void ajouterConsommable(Consommable consommable) {
        if (consommable != null) {
            consommables.add(consommable);
        }
    }

    public void supprimerConsommable(Consommable consommable) {
        if (consommable != null) {
            consommables.remove(consommable);
        }
    }

    public Set<Consommable> getConsommables() {
        return consommables;
    }

    public Consommable rechercherConsommable(String nom) {
        for (Consommable c : consommables) {
            if (c.getNom().equals(nom)) {
                return c;
            }
        }
        return null;
    }

    public Set<Consommable> tousLesConsommables() {
        return new HashSet<>(consommables);
    }

    public Set<Consommable> consommablesEquipement(Equipement equipement) {

        if (equipement == null) {
            return null;
        }

        Set<Consommable> resultat = new HashSet<>();

        String[] nomsConsommables = equipement.getConsommables();

        for (String nom : nomsConsommables) {
            Consommable c = rechercherConsommable(nom);

            if (c != null) {
                resultat.add(c);
            }
        }

        return resultat;
    }
}