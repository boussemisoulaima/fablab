package fablab;

/**
 * Représente de creneau.
 * * @author Soulaima boussemi
 */
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Creneau {
	
	private LocalDate debut;
	private LocalDate fin;
	private Equipement equipement;
	
	
	
	public Creneau (LocalDate debut, LocalDate fin , Equipement equipement) {
		
		this.debut = debut ;
		this.fin = fin ;
		this.equipement = equipement;
		
	}
	public LocalDate getDebut() {
	    return debut;
	}

	public void setDebut(LocalDate debut) {
	    this.debut = debut;
	}

	public LocalDate getFin() {
	    return fin;
	}

	public void setFin(LocalDate fin) {
	    this.fin = fin;
	}

	public Equipement getEquipement() {
	    return equipement;
	}

	public void setEquipement(Equipement equipement) {
	    this.equipement = equipement;
	}
	
	
	/**
	 * Retourne les périodes de disponibilité d'un équipement sur une période
	 * donnée.
	 *
	 * @param equipement l'équipement dont on veut la disponibilité
	 * @param debut      le jour de début de la période
	 * @param fin        le jour de fin de la période
	 * @return une map contenant tous les créneaux disponibles sur la période
	 *         considérée. La clé est le jour et l'heure du début du créneau et la
	 *         valeur est la durée en minutes de ce créneau. Si la valeur
	 *         <code>null</code> est renvoyée, c'est que les paramètres étaient
	 *         erronés (équipement inexistant ou dates incohérentes)
	 */
	public Map<LocalDateTime, Integer> disponibiliteEquipement(Equipement equipement, LocalDate debut, LocalDate fin)
	
	         {

			    if (equipement == null || debut == null || fin == null || debut.isAfter(fin)) {
			        return null;
			    }
		
			    Map<LocalDateTime, Integer> res = new HashMap<>();
		
			    for (LocalDate jour = debut; !jour.isAfter(fin); jour = jour.plusDays(1)) {
			    	 // Vérifier que le jour est compris entre lundi et vendredi
			        if (jour.getDayOfWeek().getValue() < 6) {
			        	// Ajouter un créneau de 9h à 18h (540 minutes)
			        	//9 h et 0 min 
			            res.put(jour.atTime(9, 0), 540);
			        }
			    }

			    return res;
			}

	/**
	 * Retourne les périodes d'indisponibilités d'un équipement sur une période
	 * donnée, c'est-à-dire les créneaux où il est déjà réservé.
	 *
	 * @param equipement l'équipement dont on veut l'indisponibilité
	 * @param debut      le jour de début de la période
	 * @param fin        le jour de fin de la période
	 * @return une map contenant tous les créneaux indisponibles sur la période
	 *         considérée. La clé est le jour et l'heure du début du créneau et la
	 *         valeur est la durée en minutes de ce créneau. Si la valeur
	 *         <code>null</code> est renvoyée, c'est que les paramètres étaient
	 *         erronés (équipement inexistant ou dates incohérentes)
	 */
	public Map<LocalDateTime, Integer> indisponibiliteEquipement(
	        Equipement equipement, LocalDate debut, LocalDate fin) {

	    // Vérifier les paramètres
	    if (equipement == null || debut == null || fin == null || debut.isAfter(fin)) {
	        return null;
	    }

	    // Créer une Map pour stocker les créneaux indisponibles
	    Map<LocalDateTime, Integer> res = new HashMap<>();

	    return res;
	}
	
	
	
	
}