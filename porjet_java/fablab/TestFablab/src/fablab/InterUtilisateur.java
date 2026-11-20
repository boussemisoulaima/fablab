package fablab;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface des services offerts à l'utilisateur pour utiliser le fablab.
 *
 * @author Eric Cariou
 */
public interface InterUtilisateur {

  /**
   * Inscription d'un utilisateur. L'email choisi ne doit pas déjà exister parmi
   * les utilisateurs déjà inscrits.
   *
   * @param email l'adresse email (unique) de l'utilisateur
   * @param mdp le mot de passe de l'utilisateur (ne doit pas être vide ou
   *        <code>null</code>)
   * @param info les informations personnelles sur l'utilisateur
   * @return 0 si l'inscription s'est bien déroulée, une valeur négative en cas
   *         d'erreur :
   *         <ul>
   *         <li>-1 si l'email est déjà utilisé</li>
   *         <li>-2 si l'email ou le mot de passe est vide</li>
   *         <li>-3 si les informations personnelles ne sont pas bien
   *         précisée,</li>
   *         <li>-4 si l'adresse email n'est pas bien formée</li>
   *         </ul>
   */
  int inscription(String email, String mdp, InformationPersonnelle info);

  /**
   * Connexion d'un utilisateur. Une fois connecté, l'utilisateur pourra créer
   * et gérer ses tâches.
   *
   * @param email l'email de l'utilisateur
   * @param mdp le mot de passe de l'utilisateur
   * @return <code>true</code> si la connexion s'est bien déroulée,
   *         <code>false</code> en cas de couple email/mot de passe invalide
   */
  boolean connexion(String email, String mdp);

  /**
   * Déconnecte l'utilisateur actuellement connecté.
   *
   * @throws NonConnecteException si aucun utilisateur n'est connecté.
   */
  void deconnexion() throws NonConnecteException;

  /**
   * Renvoie l'ensemble des équipements présents dans le fablab.
   *
   * @return tous les équipements du fablab
   */
  Set<Equipement> tousLesEquipements();

  /**
   * Renvoie l'ensemble des équipements d'une certaine catégorie présents dans
   * le fablab.
   *
   * @param categorie la catégorie d'équipement cherchée
   * @return tous les équipements du fablab
   */
  Set<Equipement> equipementsCategorie(CategorieEquipement categorie);

  /**
   * Renvoie un équipement à partir de son nom.
   *
   * @param nom le nom de l'équipement cherché
   * @return l'équipement cherché ou <code>null</code> si aucun équipement ne
   *         porte ce nom
   */
  Equipement equipementNom(String nom);

  /**
   * Renvoie l'ensemble des consommables utilisables dans le fablab. Il s'agit
   * des types de consommables, sans précision du nombre d'exemplaires ou de la
   * quantité disponible dans le stock pour ces consommables.
   *
   * @return tous les consommables utilisables
   */
  Set<Consommable> tousLesConsommables();

  /**
   * Renvoie l'ensemble des consommables utilisables pour un certain équipement.
   * Il s'agit des types de consommables, sans précision du nombre d'exemplaires
   * ou de la quantité disponible pour ce consommable.
   *
   * @param equipement l'équipement dont on veut les consommables utilisables
   * @return tous les consommables utilisables ou <code>null</code> si
   *         l'équipement n'existe pas
   */
  Set<Consommable> consommablesEquipement(Equipement equipement);

  /**
   * Retourne les périodes de disponibilité d'un équipement sur une période
   * donnée.
   *
   * @param equipement l'équipement dont on veut la disponibilité
   * @param debut le jour de début de la période
   * @param fin le jour de fin de la période
   * @return une map contenant tous les créneaux disponibles sur la période
   *         considérée. La clé est le jour et l'heure du début du créneau et la
   *         valeur est la durée en minutes de ce créneau. Si la valeur
   *         <code>null</code> est renvoyée, c'est que les paramètres étaient
   *         erronés (équipement inexistant ou dates incohérentes)
   */
  Map<LocalDateTime, Integer> disponibiliteEquipement(Equipement equipement,
      LocalDate debut, LocalDate fin);

  /**
   * Retourne les périodes d'indisponibilités d'un équipement sur une période
   * donnée, c'est-à-dire les créneaux où il est déjà réservé.
   *
   * @param equipement l'équipement dont on veut l'indisponibilité
   * @param debut le jour de début de la période
   * @param fin le jour de fin de la période
   * @return une map contenant tous les créneaux indisponibles sur la période
   *         considérée. La clé est le jour et l'heure du début du créneau et la
   *         valeur est la durée en minutes de ce créneau. Si la valeur
   *         <code>null</code> est renvoyée, c'est que les paramètres étaient
   *         erronés (équipement inexistant ou dates incohérentes)
   */
  Map<LocalDateTime, Integer> indisponibiliteEquipement(Equipement equipement,
      LocalDate debut, LocalDate fin);

  /**
   * Création d'une nouvelle tâche pour l'utilisateur connecté avec un certain
   * équipement.
   *
   * @param equipement l'équipement que la tâche va utiliser
   * @param description la description textuelle de la tâche (<code>null</code>
   *        si la tâche n'a pas de description)
   * @return le numéro unique de la tâche créée ou -1 si la tâche n'a pas été
   *         créée parce que l'équipement n'était pas valide.
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int creerTache(Equipement equipement, String description)
      throws NonConnecteException;

  /**
   * Ajoute un type de consommable attendu pour une tâche de l'utilisateur
   * connecté. S'il y avait déjà une quantité attendue pour ce même type de
   * consommable, les quantités demandées sont additionnées.
   *
   * @param tache l'identifiant de la tâche
   * @param cons le type de consommable
   * @param quantite la quantité requise pour ce type de consommable
   * @return 0 si l'ajout s'est bien passé, une valeur négative en cas de
   *         problème :
   *         <ul>
   *         <li>-1 : la tâche n'est pas valide (inexistante, déjà validée, déjà
   *         réalisée ou non créée par l'utilisateur connecté)</li>
   *         <li>-2 : le type de consommable n'est pas compatible avec
   *         l'équipement réservé</li>
   *         <li>-3 : la quantité n'est pas compatible avec le type de
   *         consommable</li>
   *         </ul>
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int ajouterConsommableAttenduTache(int tache, Consommable cons, int quantite)
      throws NonConnecteException;

  /**
   * Réserve un consommable dans le stock pour une tâche de l'utilisateur
   * connecté. La quantité doit être inférieure ou égale à la quantité requise
   * pour ce type de consommable.
   *
   * @param tache l'identifiant de la tâche
   * @param cons le type de consommable
   * @param quantite la quantité requise qui est prise dans le stock
   * @return 0 si la réservation s'est bien passée, une valeur négative en cas
   *         de problème :
   *         <ul>
   *         <li>-1 : la tâche n'est pas valide (inexistante, déjà validée, déjà
   *         réalisée ou non créée par l'utilisateur connecté)</li>
   *         <li>-2 : le type de consommable n'est pas compatible avec
   *         l'équipement réservé</li>
   *         <li>-3 : la quantité demandée n'est pas disponible dans le
   *         stock</li>
   *         <li>-4 : la quantité demandée est supérieure à ce qui est
   *         nécessaire</li>
   *         </ul>
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int ajouterConsommableDuStockTache(int tache, Consommable cons, int quantite)
      throws NonConnecteException;

  /**
   * Calcule le coût d'une tâche de l'utilisateur connecté en fonction du temps
   * de réservation de l'équipement et du coût des consommables. Il est donc
   * nécessaire d'avoir planifié la tâche avant cela car c'est cette opération
   * qui fixe la durée de la tâche. Le coût est exprimé en € avec une précision
   * en centimes (deux chiffres après la virgule avec arrondi au centime
   * supérieur).
   *
   * @param tache l'identifiant de la tâche dont on veut le coût
   * @return le coût de la tâche ou -1.0 si la tâche n'était pas valide
   *         (inexistante, non planifiée ou non créée par l'utilisateur
   *         connecté)
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  double calculerCoutTache(int tache) throws NonConnecteException;

  /**
   * Planifie une tâche de l'utilisateur connecté à une certaine date et heure,
   * et pour une certaine durée. La durée passée en paramètre inclut le temps de
   * préparation et le temps de nettoyage, de 15 mn chacun, en plus du temps
   * d'exécution de la tâche. La durée est donc strictement supérieure à 30 mn.
   * Si la tâche avait déjà été planifiée, la nouvelle planification remplace la
   * précédente. Cela permet notamment de reporter à plus tard la réalisation
   * d'une tâche si tous les consommables n'ont pas pu être réservés à temps.
   *
   * @param tache l'identifiant de la tâche à planifier
   * @param debut le jour et l'heure de début de la tâche
   * @param duree la durée de la tâche
   * @return 0 si la planification a été correctement faite ou une valeur
   *         négative en cas de problème :
   *         <ul>
   *         <li>-1 : la tâche n'est pas valide (inexistante, déjà validée, déjà
   *         réalisée ou non créée par l'utilisateur connecté)</li>
   *         <li>-2 : l'équipement n'est pas disponible sur ce créneau</li>
   *         <li>-3 : le jour n'est pas correct (dans le passé ou jour non
   *         ouvré)</li>
   *         <li>-4 : le créneau n'est pas inclus dans l'intervalle 9h -
   *         18h</li>
   *         <li>-5 : la durée est inférieure ou égale à 30 mn</li>
   *         </ul>
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int planifierTache(int tache, LocalDateTime debut, int duree)
      throws NonConnecteException;

  /**
   * Vérifie que tous les consommables requis ont bien été réservés pour une
   * certaine tâche de l'utilisateur connecté.
   *
   * @param tache l'identifiant de la tâche à vérifier
   * @return <code>true</code> si tous les consommables ont bien été réservés,
   *         <code>false</code> sinon ou si la tâche n'était pas valide
   *         (inexistante, déjà validée, déjà réalisée ou non créée par
   *         l'utilisateur connecté)
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  boolean verifierTousConsommablesReserves(int tache)
      throws NonConnecteException;

  /**
   * Retourne l'ensemble des consommables attendus pour une tâche de
   * l'utilisateur connecté. Il s'agit des types de consommables ajoutés pendant
   * la préparation de la tâche.
   *
   * @param tache l'identifiant de la tâche dont on veut la liste des
   *        consommables attendus
   * @return une map qui associe chaque consommable attendu (clé) avec la
   *         quantité associée (valeur). Retourne <code>null</code> si la tâche
   *         n'existe pas ou n'a pas été créée par l'utilisateur connecté.
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  Map<Consommable, Integer> listeConsommablesAttendusTache(int tache)
      throws NonConnecteException;

  /**
   * Retourne l'ensemble des consommables déjà réservés dans le stock pour une
   * tâche de l'utilisateur connecté.
   *
   * @param tache l'identifiant de la tâche dont on veut la liste des
   *        consommables réservés
   * @return une map qui associe chaque consommable attendu (clé) avec la
   *         quantité associée (valeur). Retourne <code>null</code> si la tâche
   *         n'existe pas ou n'a pas été créée par l'utilisateur connecté.
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  Map<Consommable, Integer> listeConsommableReservesTache(int tache)
      throws NonConnecteException;

  /**
   * Retourne l'ensemble des consommables restant à réserver pour une tâche de
   * l'utilisateur connecté.
   *
   * @param tache l'identifiant de la tâche dont on veut la liste des
   *        consommables restant à réserver
   * @return une map qui associe chaque consommable attendu (clé) avec la
   *         quantité associée (valeur). Retourne <code>null</code> si la tâche
   *         n'existe pas ou n'a pas été créée par l'utilisateur connecté.
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  Map<Consommable, Integer> consommablesNonReservesTache(int tache)
      throws NonConnecteException;

  /**
   * Ajoute une image à une tâche de l'utilisateur connecté. Si l'image existait
   * déjà, elle est remplacée par la nouvelle. Cet ajout est optionnel, la tâche
   * est valide sans image. L'ajout d'image peut être fait à n'importe quel
   * moment, y compris si la tâche est déjà réalisée.
   *
   * @param tache l'identifiant de la tâche à laquelle ajouter une image
   * @param fichier le nom du fichier qui contient l'image
   * @return 0 si l'image a bien été ajoutée ou une valeur négative en cas de
   *         problème :
   *         <ul>
   *         <li>-1 : la tâche n'est pas valide (inexistante ou non créée par
   *         l'utilisateur connecté)</li>
   *         <li>-2 : si le fichier n'est pas lisible</li>
   *         <li>-3 : si le fichier ne contient pas une image</li>
   *         </ul>
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int ajouterImageTache(int tache, String fichier) throws NonConnecteException;

  /**
   * Annule une tâche de l'utilisateur connecté. Ne peut être fait que si la
   * tâche n'a pas déjà été réalisée ni payée. Une tâche annulée n'apparait plus
   * dans les tâches d'un utilisateur, elle est définitivement supprimée. Les
   * consommables réservés pour la tâche sont à nouveau disponibles dans le
   * stock.
   *
   * @param tache l'identifiant de la tâche de l'utilisateur connecté à annuler
   * @return <code>true</code> si la tâche a été annulée, <code>false</code> en
   *         cas de problème (tâche déjà réalisée, déjà payée ou qui
   *         n'appartient pas à l'utilisateur connecté)
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  boolean annulerTache(int tache) throws NonConnecteException;

  /**
   * Valide une tâche de l'utilisateur connecté en payant le prix qu'elle coûte.
   * Une tâche ne peut être validée que si tous les consommables nécessaires
   * sont réservés, qu'elle a été planifiée à une date valide et que le prix
   * payé est celui qui est attendu. Une fois validée, une tâche ne peut plus
   * être modifiée (à l'exception de l'image associée qui peut être ajoutée ou
   * remplacée à tout moment).
   *
   * @param tache l'identifiant de la tâche de l'utilisateur connecté à valider
   * @param prix le prix que l'utilisateur paye
   * @return 0 si la validation a été faite ou une valeur négative en cas de
   *         problème :
   *         <ul>
   *         <li>-1 : la tâche n'est pas valide (inexistante, pas encore
   *         planifiée, déjà validée, déjà réalisée ou non créée par
   *         l'utilisateur connecté)</li>
   *         <li>-2 : la somme payée est différente du coût de la tâche</li>
   *         </ul>
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  int validerEtPayerTache(int tache, double prix) throws NonConnecteException;

  /**
   * Récupère les tâches de l'utilisateur connecté : soit celles déjà réalisées,
   * soit celles à venir ou en cours de préparation.
   *
   * @param realisees choix entre les tâches réalisées ou les autres (créées, en
   *        cours de préparation, validées)
   * @return les tâches réalisées si le paramètre est <code>true</code> sinon
   *         retourne les tâches non encore réalisées
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  Set<Tache> recupererTachesUtilisateur(boolean realisees)
      throws NonConnecteException;

  /**
   * Récupère une tâche de l'utilisateur connecté à partir de l'identifiant de
   * la tâche.
   *
   * @param tache l'identifiant de la tâche à récupérer
   * @return la tâche cherchée ou <code>null</code> si l'identifiant de la tâche
   *         n'existe pas ou ne correspond pas à une tâche de l'utilisateur
   *         connecté
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  Tache recupererTache(int tache) throws NonConnecteException;

  /**
   * Envoi d'un message au gestionnaire de la part de l'utilisateur connecté.
   *
   * @param message le message à envoyer au gestionnaire (si le contenu est
   *        <code>null</code>, aucun message n'est envoyé)
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  void envoyerMessageGestionnaire(String message) throws NonConnecteException;

  /**
   * Récupère les messages non lus que l'utilisateur connecté a reçu de la part
   * du gestionnaire. Les messages retournés sont alors considérés comme lus.
   * Les messages sont ordonnés par date d'envoi : le premier de la liste est le
   * plus ancien.
   *
   * @return les messages non lus de l'utilisateur connecté
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  List<Message> recupererMessagesNonLus() throws NonConnecteException;

  /**
   * Récupère les messages déjà lus que l'utilisateur connecté a reçu de la part
   * du gestionnaire. Les messages sont ordonnés par date d'envoi : le premier
   * de la liste est le plus ancien.
   *
   * @return les messages déjà lus de l'utilisateur connecté
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  List<Message> recupererMessagesLus() throws NonConnecteException;
}

