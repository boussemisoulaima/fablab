package fablab;

/**
 * Représente un équipement.
 * * @author Soulaima boussemi
 */
import java.util.Set;
import java.util.HashSet;

public class Equipement  {

	private String nom;
	private String description;
	private CategorieEquipement type;
	private double coutHoraire;
	private String image;
	private Set<Consommable> consommables;
	private boolean reserve;
	private static Set<Equipement> equipements = new HashSet<>();

	// Constructeur avec paramètres
	public Equipement(String nom,String description, CategorieEquipement type, double coutHoraire,boolean reserve) {

		this.nom = nom;
		this.description=description;
		this.type = type;
		this.coutHoraire = coutHoraire;
		this.image = "";
		this.consommables = new HashSet<>();
		this.reserve = reserve;
		
	}

	

	/**
	 * Renvoie l'ensemble des �quipements pr�sents dans le fablab.
	 *
	 * @return tous les �quipements du fablab
	 */

	public Set<Equipement> tousLesEquipements() {
		return this.equipements;
	}

	/**
	 * Renvoie l'ensemble des �quipements d'une certaine cat�gorie pr�sents dans le
	 * fablab.
	 *
	 * @param categorie la cat�gorie d'�quipement cherch�e
	 * @return tous les �quipements du fablab
	 */
	public Set<Equipement> equipementsCategorie(CategorieEquipement categorie) {
		Set<Equipement> res = new HashSet<>();
		
		for (Equipement eq : this.equipements) {
			if (categorie == eq.type) {
				res.add(eq);
			}
		}
		return res;
	}

	/**
	 * Renvoie un �quipement � partir de son nom.
	 *
	 * @param nom le nom de l'�quipement cherch�
	 * @return l'�quipement cherch� ou <code>null</code> si aucun �quipement ne
	 *         porte ce nom
	 */
	public Equipement equipementNom(String nom) {
		for (Equipement eq : this.equipements) {
			if (eq.nom.equals(nom)) {
				return eq;
			}
		}
		return null;
	}

	// On calcule le coût
	// Le 0.5 correspond à la demi-heure de préparation et de nettoyage qui n'est
	// pas facturée
	public double calculCout(double duree) {
		double res = coutHoraire * (duree);
		return res;
	}

	// Getters et Setters

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public CategorieEquipement getType() {
		return type;
	}

	public void setType(CategorieEquipement type) {
		this.type = type;
	}

	public double getCoutHoraire() {
		return coutHoraire;
	}

	public void setCoutHoraire(double coutHoraire) {
		this.coutHoraire = coutHoraire;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Consommable> getConsommables() {
	    return consommables;
	}

	public void setConsommables(Set<Consommable> consommables) {
	    this.consommables = consommables;
	}

	public boolean isReserve() {
		return reserve;
	}

	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}
	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

}
