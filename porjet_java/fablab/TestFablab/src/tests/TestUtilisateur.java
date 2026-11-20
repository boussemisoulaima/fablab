package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;

import fablab.EmailValidator;
import fablab.InformationPersonnelle;
import fablab.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link fablab.Utilisateur Utilisateur}.
 *
 * @author Assia Simonnin
 * @see fablab.Utilisateur
 */

class TestUtilisateur {

	/**
	 * Les utilisateurs de l'application fablab
	 */
	private Utilisateur user1;
	private Utilisateur user2; 
	
	/** 
	 * Information personnelle de l'utilisateur user1
	 */
	private InformationPersonnelle info; 
	
	/**
	 * Informations personnelles de l'utilisateur user2 pas bien précisée
	 */
	private InformationPersonnelle info2; 
	private InformationPersonnelle info3; 
	private InformationPersonnelle info4; 
	private InformationPersonnelle info5; 
	
	/**
	 * Email de l'utilisateur
	 */
	final String EMAIL = "demir_yaman@gmail.com"; 
	
	/**
	 * Email de l'utilisateur invalide
	 */
	final String EMAIL_2 = "demir_yaman.@gmail.com."; 
	
	/**
	 * Mot de passe de l'utilisateur
	 */
	final String MDP = "DemiR54_uS"; 

	/**
	 * Instancie un utilisateur sans information.
	 * Instancie une information pour un test d'inscription à l'application
	 * fablab.
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@BeforeEach
	void setUp() throws Exception {
		user1 = new Utilisateur();
		user2 = new Utilisateur(); 
		info = new InformationPersonnelle("Yaman", "Demir", EMAIL, 30);
		info2 = new InformationPersonnelle("", "Demir");
		info3 = new InformationPersonnelle("Yaman", "");
		info4 = new InformationPersonnelle("",""); 
		info5 = new InformationPersonnelle("Yaman", "Demir", "tala@gmail.com", 30);
	}

	/**
	 * Ne fait rien après les tests : à modifier au besoin.
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Vérifie que l'on peut s'inscrire à l'application 
	 */
	@Test
	void testInscriptionValide() { 
		assertTrue(user1.inscription(EMAIL, MDP, info) == 0);
	}
	
	/**
	 * Vérifie que l'on ne peut pas s'inscrire à l'application 
	 * car l'email est déjà utilisé
	 */
	@Test
	void testInscriptionInvalide1() { 
		user1.inscription(EMAIL, MDP, info);
		assertTrue(user2.inscription(EMAIL, MDP, info) == -1); 
	}
	
	/**
	 * Vérifie que l'on ne peut pas s'inscrire à l'application 
	 * car l'email ou le mot de passe est vide.
	 */
	@Test
	void testInscriptionInvalide2() { 
		assertTrue(user2.inscription("", MDP, info) == -2); 
		assertTrue(user2.inscription(EMAIL, "", info) == -2); 
		assertTrue(user2.inscription("", "", info) == -2); 
	}
	
	/**
	 * Vérifie que l'on ne peut pas s'inscrire à l'application 
	 * car l'email ou le mot de passe est vide.
	 */
	@Test
	void testInscriptionInvalide3() { 
		assertTrue(user2.inscription(EMAIL, MDP, info2) == -3); 
		assertTrue(user2.inscription(EMAIL, MDP, info3) == -3); 
		assertTrue(user2.inscription(EMAIL, MDP, info4) == -3); 
		assertTrue(user2.inscription(EMAIL, MDP, info5) == -3); 
	}
	
	/**
	 * Vérifie que l'on ne peut pas s'inscrire à l'application 
	 * car l'email n'est pas valide.
	 */
	@Test
	void testInscriptionInvalide4() { 
		assertTrue(user2.inscription(EMAIL_2, MDP, info) == -4); 
	}
	
	/**
	 * Vérifie que l'on récupère l'information personnelle correctement après 
	 * l'inscription de l'utilisateur
	 */
	@Test
	void testgetInfo() {
		user1.inscription(EMAIL, MDP, info);
		assertEquals(user1.getInfo(), info); 
	}
	
	/**
	 * Vérifie que l'on récupère le mot de passe correctement après 
	 * l'inscription de l'utilisateur
	 */
	@Test
	void testgetMdp() {
		user1.inscription(EMAIL, MDP, info);
		assertEquals(user1.getMdp(), MDP);
	}
	
	/**
	 * Vérifie que l'on récupère l'email correctement après 
	 * l'inscription de l'utilisateur
	 */
	@Test
	void testgetEmail() {
		user1.inscription(EMAIL, MDP, info);
		assertEquals(user1.getEmail(), EMAIL);
	}

	/**
	 * Vérifie que l'on se connecte à l'application correctement
	 */
	@Test
	void testConnexion() {
		user1.inscription(EMAIL, MDP, info);
		assertTrue(user1.connexion(EMAIL, MDP)); 
	}
	
	/**
	 * Vérifie que la connexion à l'application échoue si l'email ou le mot de passe
	 * ne correspondent pas à ceux enregistrés dans info (information personnelle 
	 * renséignée sur l'utilisateur).
	 * 
	 */
	@Test
	void testConnexionEchoue() {
		assertFalse(user1.connexion(EMAIL_2, MDP));
		assertFalse(user1.connexion(EMAIL, "mdpasse11")); 
	}

	/**
	 * Vérifie que les paramètres des constructeurs sont correctement gérés.
	 */
	@Test
	void testConstructeur() {
		Utilisateur user3 = new Utilisateur(); 
		assertEquals(user3.getEmail(), ""); 
		assertEquals(user3.getMdp(), "");
		assertEquals(user3.getInfo().getNom(), "");
		assertEquals(user3.getInfo().getPrenom(), "");
	}

}