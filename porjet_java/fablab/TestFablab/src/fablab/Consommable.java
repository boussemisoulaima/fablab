package fablab;

import java.util.Set;
import java.util.HashMap;



/**
 * Représente un type de consommable.
 */
public class Consommable {
	

    // Attributs
    private String nom;
    
    private String description;
    
    private double prix_exemplaire;
    
    private int quantite_exemplaire;
    
    private boolean secable;
    
    private String unite; 
    

    // Constructeur
    public Consommable(String nom, String description, double prix_exemplaire,
                       int quantite_exemplaire, String unite,
                       boolean secable) {
    	
    	 this.nom = nom;
    	 
         this.description = description;
         
         
         if (prix_exemplaire < 0) {
        	    throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        	}
         this.prix_exemplaire = prix_exemplaire;
         
         
         if (quantite_exemplaire < 0) {
     	    throw new IllegalArgumentException("La quantité ne peut pas être négative.");
     	}
         this.quantite_exemplaire = quantite_exemplaire;
         
         this.secable = secable;
         
         this.unite = unite;

     }

     // Getters
     public String getNom() {
         return nom;
     }

     public String getDescription() {
         return description;
     }

     public double getPrixExemplaire() {
         return prix_exemplaire;
     }

     public int getQuantiteExemplaire() {
    	 
         return quantite_exemplaire;
     }

     public String getUnite() {
         return unite;
     }
     
     public boolean getSecable() {
         return secable;
     }
     
     
     
     
     
     //setters
     
     public void setDescription(String description) {
    	    this.description = description;
    	}
     
     
     public void setNom(String nom) {
    	    this.nom = nom;
    	}
     
     
     public void setPrix_exemplaire(double prix_exemplaire) {
    	 
    	 if (prix_exemplaire < 0) {
     	    throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
     	}
    	 
 	    this.prix_exemplaire = prix_exemplaire;
 	}
     
     
     public void setQuantite_exemplaire(int quantite_exemplaire) {
    	 if (quantite_exemplaire < 0) {
      	    throw new IllegalArgumentException("La quantité ne peut pas être négative.");
      	}
   	    this.quantite_exemplaire = quantite_exemplaire;
   	}
     
     public void setUnite(String unite) {
 	    this.unite = unite;
 	}
      
     
     
     
}












