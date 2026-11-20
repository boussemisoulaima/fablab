package fablab;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface des services offerts au gestionnaire du fablab.
 */
public interface InterGestionnaire {

  /**
   * Création d'un nouvel équipement. Il ne doit pas déjà exister un équipement
   * avec le même nom. La création de l'équipement par ce service ajoute
   * l'équipement dans l'ensemble des équipements du fablab (un équipement qui
   * n'est pas dans cet ensemble est considéré comme non valide ou non
   * utilisable pour exécuter des tâches).
   *
   * @param nom le nom unique de l'équipement
   * @param description la description de l'équipement
   * @param categorie la catégorie de l'équipement
   * @param prixHoraire le coût horaire d'utilisation de l'équipement
   * @return le nouvel équipement ou <code>null</code> en cas d'erreur
   *         (équipement déjà existant avec le même nom ou certains paramètres
   *         ne sont pas valides).
   */
  Equipement creerEquipement(String nom, String description,
      CategorieEquipement categorie, double prixHoraire);

  /**
   * Ajoute une photo à un équipement. Cet ajout est optionnel, l'équipement est
   * utilisable sans photo.
   *
   * @param equipement l'équipement auquel on ajoute la photo
   * @param fichier le fichier qui contient la photo de l'équipement
   * @return <code>true</code> si la photo a été correctement ajoutée,
   *         <code>false</code> en cas de problème (l'équipement n'est pas
   *         valide, mauvais chemin du fichier, fichier qui ne contient pas une
   *         image...)
   */
  boolean ajouterPhotoEquipement(Equipement equipement, String fichier);

  /**
   * Renvoie l'ensemble des équipements disponibles dans le fablab.
   *
   * @return l'ensemble des équipements
   */
  Set<Equipement> tousLesEquipements();

  /**
   * Renvoie l'ensemble des équipements d'une certaine catégorie disponibles
   * dans le fablab.
   *
   * @param categorie la catégorie des équipements
   * @return l'ensemble des équipements de cette catégorie
   */
  Set<Equipement> equipementsCategorie(CategorieEquipement categorie);

  /**
   * Récupère un équipement à partir de son nom.
   *
   * @param nom le nom de l'équipement
   * @return l'équipement ou <code>null</code> s'il n'existe aucun équipement
   *         avec ce nom
   */
  Equipement equipementNom(String nom);

  /**
   * Création d'un nouveau type de consommable. Il ne doit pas déjà exister un
   * consommable avec ce même nom. La création du type de consommable par ce
   * service l'ajoute dans l'ensemble des types de consommable du fablab (un
   * type de consommable qui n'est pas dans cet ensemble est considéré comme non
   * valide ou non utilisable pour exécuter des tâches ou configurer des
   * équipements du fablab).
   *
   * @param nom le nom unique du consommable
   * @param description la description du consommable
   * @param prixExemplaire le prix d'un exemplaire du consommable
   * @param quantiteInitiale la quantité initiale dans un exemplaire du
   *        consommable
   * @param unite l'unité pour mesurer la quantité (grammes, centilitres, ...)
   * @param secable <code>true</code> si un exemplaire du consommable peut être
   *        consommé en plusieurs fois, <code>false</code> sinon. Par exemple,
   *        sur une bobine de filament de 586 grammes, on pourra consommer 120
   *        grammes et le reste pourra être utilisé par une autre tâche. Par
   *        contre, pour un panneau en bois à fraiser, il est utilisé en entier
   *        et n'est donc pas sécable.
   * @return le consommable créé ou <code>null</code> si des paramètres
   *         n'étaient pas valides
   */
  Consommable creerConsommable(String nom, String description,
      double prixExemplaire, int quantiteInitiale, String unite,
      boolean secable);

  /**
   * Ajoute un type de consommable utilisable pour un équipement donné.
   *
   * @param equipement l'équipement à configurer pour ce consommable
   * @param consommable le type de consommable autorisé pour cet équipement
   * @return <code>true</code> si l'ajout a été correctement effectué,
   *         <code>false</code> en cas d'erreur (l'équipement ou le consommable
   *         n'est pas valide)
   */
  boolean ajouterConsommableAutorise(Equipement equipement,
      Consommable consommable);

  /**
   * Renvoie l'ensemble des types de consommables existants.
   *
   * @return l'ensemble des types de consommables
   */
  Set<Consommable> recupererTousConsommables();

  /**
   * Renvoie l'ensemble des types de consommables autorisés pour un certain
   * équipement.
   *
   * @param equipement l'équipement dont on veut les consommables autorisés
   * @return l'ensemble des types de consommables autorisés pour l'équipement ou
   *         <code>null</code> si l'équipement n'est pas valide
   */
  Set<Consommable> recupererConsommablesEquipement(Equipement equipement);

  /**
   * Commande un certain nombre d'exemplaires pour un consommable. Les
   * exemplaires sont placés dans le stock.
   *
   * @param consommable le consommable
   * @param nbExemplaires le nombre d'exemplaires de ce consommable à commander
   * @return <code>true</code> si les exemplaires ont été commandés et placés
   *         dans le stock, <code>false</code> en cas de problème (le
   *         consommable n'est pas valide, le nombre d'exemplaires est nul ou
   *         négatif...)
   */
  boolean commanderExemplairesConsommable(Consommable consommable,
      int nbExemplaires);

  /**
   * Retourne l'inventaire du stock disponible. Pour un type de consommable, il
   * donne l'ensemble des quantités disponibles des exemplaires de ce type.
   *
   * @return une map qui associe à chaque type consommable (clé) l'ensemble des
   *         quantités disponibles de tous ses exemplaires présents dans le
   *         stock (valeur)
   */
  Map<Consommable, List<Integer>> inventaireTousConsommables();

  /**
   * Retourne la quantité disponible pour chaque exemplaire d'un certain type de
   * consommable.
   *
   * @param consommable le type de consommable
   * @return l'ensemble des quantités disponibles pour ce type de consommable ou
   *         <code>null</code> si le consommable n'est pas valide
   */
  List<Integer> quantitesConsommable(Consommable consommable);

  /**
   * Récupère toutes les tâches de tous les utilisateurs ou d'un utilisateur en
   * particulier.
   *
   * @param emailUtilisateur l'email de l'utilisateur dont on veut récupérer les
   *        tâches. Pour récupérer les tâches de tous les utilisateurs, on passe
   *        une chaine vide ou <code>null</code>
   * @return toutes les tâches de l'utilisateur ou de tous les utilisateurs ou
   *         <code>null</code> si aucun utilisateur n'existe avec l'email
   *         précisé
   */
  Set<Tache> recupererToutesTaches(String emailUtilisateur);

  /**
   * Récupère toutes les tâches de tous les utilisateurs ou d'un utilisateur en
   * particulier, sur une certaine période.
   *
   * @param emailUtilisateur l'email de l'utilisateur dont on veut récupérer les
   *        tâches. Pour récupérer les tâches de tous les utilisateurs, on passe
   *        une chaine vide ou <code>null</code>
   * @param debut le jour de début de la période
   * @param fin le jour de fin de la péridode
   * @return toutes les tâches de l'utilisateur ou de tous les utilisateurs ou
   *         <code>null</code> si aucun utilisateur n'existe avec l'email
   *         précisé ou que la période n'est pas valide
   */
  Set<Tache> recupererToutesTachesPeriode(String emailUtilisateur,
      LocalDateTime debut, LocalDateTime fin);

  /**
   * Récupère toutes les tâches de tous les utilisateurs ou d'un utilisateur en
   * particulier, en appliquant des filtres. Les critères de filtre sont, en
   * fonction de l'état d'une tâche : en préparation (tâche créée mais ses
   * consommables ne sont pas encore tous configurés ou réservés), validée (et
   * payée), planifiée ou executée. Le principe d'un filtre est que si sa valeur
   * est à vrai, alors on ne conserve que les tâches respectant ce critère,
   * sinon le filtre sur ce critère n'est pas appliqué. Toutes les combinaisons
   * de filtres ne sont pas valides entre elles et peuvent aboutir à un ensemble
   * vide.
   *
   * @param emailUtilisateur l'email de l'utilisateur dont on veut récupérer les
   *        tâches. Pour récupérer les tâches de tous les utilisateurs, on passe
   *        une chaine vide ou <code>null</code>
   * @param preparationEnCours si <code>true</code>, ne conserve que les tâches
   *        qui ont été créées mais ne sont pas encore validées, ni planifiées,
   *        ni exécutées
   * @param planifiees si <code>true</code>, ne conserve que les tâches qui ont
   *        été planifiées
   * @param validees si <code>true</code>, ne conserve que les tâches qui ont
   *        été payées et validées
   * @param executees si <code>true</code>, ne conserve que les tâches qui ont
   *        été exécutées
   * @return l'ensemble des tâches correspondant aux filtres ou
   *         <code>null</code> si les filtres ne sont pas valides car
   *         contradictoires entre eux
   */
  Set<Tache> recupererFiltresTache(String emailUtilisateur,
      boolean preparationEnCours, boolean planifiees, boolean validees,
      boolean executees);

  /**
   * Récupère toutes les tâches de tous les utilisateurs ou d'un utilisateur en
   * particulier sur une certaine période, en appliquant des filtres. Les
   * critères de filtre sont, en fonction de l'état d'une tâche : en préparation
   * (tâche créée mais ses consommables ne sont pas encore tous configurés ou
   * réservés), validée (et payée), planifiée ou executée. Le principe d'un
   * filtre est que si sa valeur est à vrai, alors on ne conserve que les tâches
   * respectant ce critère, sinon le filtre sur ce critère n'est pas appliqué.
   * Toutes les combinaisons de filtres ne sont pas valides entre elles et
   * peuvent aboutir à un ensemble vide.
   *
   * @param emailUtilisateur l'email de l'utilisateur dont on veut récupérer les
   *        tâches. Pour récupérer les tâches de tous les utilisateurs, on passe
   *        une chaine vide ou <code>null</code>
   * @param preparationEnCours si <code>true</code>, ne conserve que les tâches
   *        qui ont été créées mais ne sont pas encore validées, ni planifiées,
   *        ni exécutées
   * @param planifiees si <code>true</code>, ne conserve que les tâches qui ont
   *        été planifiées
   * @param validees si <code>true</code>, ne conserve que les tâches qui ont
   *        été payées et validées
   * @param executees si <code>true</code>, ne conserve que les tâches qui ont
   *        été exécutées
   * @param debut le jour de début de la période
   * @param fin le jour de fin de la péridode
   * @return l'ensemble des tâches correspondant aux filtres ou
   *         <code>null</code> si les filtres ou la période ne sont pas valides
   *         car contradictoires entre eux
   */
  Set<Tache> recupererFiltresTachePeriode(String emailUtilisateur,
      boolean preparationEnCours, boolean planifiees, boolean validees,
      boolean executees, LocalDateTime debut, LocalDateTime fin);

  /**
   * Retourne l'ensemble des tâches qui sont planifiées aujourd'hui. Cela peut
   * inclure des tâches qui n'ont pas encore été payées et validées et ne
   * peuvent donc pas être exécutées.
   *
   * @return l'ensemble des tâches de la journée courante
   */
  Set<Tache> recupererTachesAujourdhui();

  /**
   * Exécute une tâche. Il est possible d'exécuter une tâche si l'heure de début
   * de la tâche est moins de 5 minutes avant ou après l'heure courante. Par
   * exemple, si la tâche est planifiée à 15h00, il sera possible de l'exécuter
   * si l'heure courante est entre 14h55 (inclus) et 15h05 (inclus). Une fois la
   * tâche exécutée, un message est envoyé automatiquement à l'utilisateur qui a
   * créé la tâche pour l'informer. On simplifiera le fonctionnement de
   * l'exécution en supposant qu'une fois l'exécution lancée, elle se termine
   * sans avoir besoin de simuler le changement d'heure correspondant à la fin
   * de la durée de la tâche. Une fois la tâche exécutée, les quantités de
   * consommables réservées pour la tâche sont définitivement retirés du stock.
   *
   * @param tache l'identifiant de la tâche à exécuter
   * @return 0 si l'exécution s'est bien passée, -1 si l'heure et la date
   *         courante ne permettent pas d'exécuter la tâche et -2 si la tâche
   *         n'était pas encore finalisée et n'a donc pas pu être exécutée
   *         (configuration non terminée, consommables pas tous réservés...), -3
   *         si la tache n'existe pas ou qu'elle a déjà été réalisée
   */
  int executerTache(int tache);

  /**
   * Change la date et l'heure courantes.
   *
   * @param nouvelle la nouvelle date et la nouvelle heure
   * @return <code>true</code> si la date et l'heure ont été modifiées,
   *         <code>false</code> sinon
   */
  boolean changerDateHeure(LocalDateTime nouvelle);

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
   * Retourne les périodes d'indisponibités d'un équipement sur une période
   * donnée, c'est-à-dire les créneaux où il est déjà réservé.
   *
   * @param equipement l'équipement dont on veut l'indisponibilité
   * @param debut le jour de début de la période
   * @param fin le jour de fin de la péridode
   * @return une map contenant tous les créneaux indisponibles sur la période
   *         considérée. La clé est le jour et l'heure du début du créneau et la
   *         valeur est la durée en minutes de ce créneau. Si la valeur
   *         <code>null</code> est renvoyée, c'est que les paramètres étaient
   *         erronés (équipement inexistant ou dates incohérentes)
   */
  Map<LocalDateTime, Integer> indisponibiliteEquipement(Equipement equipement,
      LocalDate debut, LocalDate fin);

  /**
   * Envoi d'un message à un utilisateur.
   *
   * @param message le message à envoyer à l'utilisateur
   * @param emailUtilisateur l'email de l'utilisateur destinataire du message
   * @return <code>true</code> si le message a bien été envoyé,
   *         <code>false</code> en cas de problème (email invalide ou message
   *         vide)
   */
  boolean envoyerMessage(String message, String emailUtilisateur);

  /**
   * Diffuse un message à tous les utilisateurs.
   *
   * @param message le message à diffuser
   * @return <code>true</code> si le message a bien été envoyé,
   *         <code>false</code> en cas de problème (message vide)
   */
  boolean diffuserMessage(String message);

  /**
   * Récupère les messages non lus que le gestionnaire a reçu. Les messages
   * retournés sont alors considérés comme lus. Les messages sont ordonnés par
   * date d'envoi : le premier de la liste est le plus ancien.
   *
   * @return les messages non lus
   */
  List<Message> recupererMessagesNonLus();

  /**
   * Récupère les messages déjà lus que le gestionnaire a reçu. Les messages
   * sont ordonnés par date d'envoi : le premier de la liste est le plus ancien.
   *
   * @return les messages déjà lus
   */
  List<Message> recupererMessagesLus();

  /**
   * Retourne le taux d'occupation moyen d'un équipement sur une période donnée.
   * La valeur retournée est le pourcentage du temps où l'équipement est utilisé
   * par rapport au temps total de réservation possible sur le nombre de jours
   * ouvrés sur la période et entre 9h et 18h.
   *
   * @param equipement l'équipement dont on veut le taux d'occupation moyen
   * @param debut le début de la période considérée
   * @param fin la fin de la période considérée
   * @return le pourcentage moyen d'occupation sur la période (arrondi à
   *         l'entier le plus proche) ou -1 en cas de problème (équipement
   *         inexistant, dates invalides)
   */
  int tauxOccupationMoyen(Equipement equipement, LocalDate debut,
      LocalDate fin);

  /**
   * Retourne le nombre de tâches exécutées par un équipement sur une période
   * donnée.
   *
   * @param equipement l'équipement dont on veut le nombre de tâches
   * @param debut le début de la période considérée
   * @param fin la fin de la période considérée
   * @return le nombre de tâches exécutées sur la période ou -1 en cas de
   *         problème (équipement inexistant, dates invalides)
   */
  int nombreTachesExecutees(Equipement equipement, LocalDate debut,
      LocalDate fin);
}
