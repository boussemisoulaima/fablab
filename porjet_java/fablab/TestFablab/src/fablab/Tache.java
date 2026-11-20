package fablab;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Représente une tâche d'un utilisateur.
 */
public class Tache {

	  public enum Etat{
		     /** Tâche créée, pas encore planifiée. */
		    EN_COURS,
		    /** Tâche planifiée sur un créneau. */
		    AFAIRE,
		    /** Tâche payée. */
		    PAYEE,
		    /** Tâche exécutée. */
		    FINIE,
		    /** Tâche abandonnée. */
		    ABANDONNEE
		  }
	  
		  private static int compteur;
		  private final  int id ;

		  InterUtilisateur utilisateur;
		  private String description;
		  private String image;
		  private  final Map<Consommable, Double> besoins ;
		  private final Set<Consommable> reserves; 
		  private double debut; /*debut de crenaeau */
		  private double duree;
		  private Equipement equipement;
		  private Etat etat = Etat.EN_COURS;
		  
		  
		  
		  public Tache(InterUtilisateur utilisateur,Equipement equipement,String description) {
			  	this.utilisateur=utilisateur;
			  	this.equipement=equipement;
			    this.id = compteur ++;
			    this.description = description;
			   
			    this.besoins = new HashMap<>();
			    this.reserves = new HashSet<>();
			}
		  public int getId() {
			  return this.id;
		  }
		 

}
