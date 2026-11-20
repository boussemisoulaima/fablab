package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fablab.EmailValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link fablab.EmailValidator EmailValidator}.
 *
 * @author Assia Simonnin
 * @see fablab.EmailValidator
 */

class TestEmailValidator {

	/**
	 * Un validateur d'un email
	 */
	private EmailValidator validator; 
	
	/**
	 * Des emails valides correspondants aux formats acceptés par l'expression
	 * régulière de EmailValidator
	 */
    
	private final String EMAIL_1 = "assia@yahoo.fr"; 
	private final String EMAIL_2 = "Manar.selmi@gmail.com";
	private final String EMAIL_3 = "lisa-name@outlook.fr";
	private final String EMAIL_4 = "monMail@yahoo.co.in";
	private final String EMAIL_5 = "soulaima_boussemi@gmail.com";
	
	
	/**
	 * Des emails invalides ne correspondants pas aux formats acceptés par l'expression
	 * régulière de EmailValidator
	 */
    
	private final String INVALID_1 = "lisa.@gmail.com"; 
	private final String INVALID_2 = ".Manar.selmi@gmail.com";
	private final String INVALID_3 = "lisa-name@outlook.fr.";
	private final String INVALID_4 = "monMail@.co.in";
	private final String INVALID_5 = "soulaima_boussemi@.com";

	/**
	 * Instancie le validateur d'un email
	 *
	 * @throws Exception ne peut pas être levée ici
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.validator = new EmailValidator();
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
	 * Vérifie que le format de mail "nom@domaine.com" est accepté.
	 */
	@Test
	void testFormatAccepte1() {
		assertTrue(EmailValidator.isValidEmail(EMAIL_1)); 
	}

	/**
	 * Vérifie que le format de mail "nom.prenom@domaine.com" est accepté.
	 */
	@Test
	void testFormatAccepte2() {
		assertTrue(EmailValidator.isValidEmail(EMAIL_2));
	}

	/**
	 * Vérifie que le format de mail "nom-prenom@domaine.com" est accepté.
	 */
	@Test
	void testFormatAccepte3() {
		assertTrue(EmailValidator.isValidEmail(EMAIL_3));
	}

	/**
	 * Vérifie que le format de mail "nomprenom@domaine.co.in" est accepté.
	 */
	@Test
	void testFormatAccepte4() {
		assertTrue(EmailValidator.isValidEmail(EMAIL_4));
	}

	/**
	 * Vérifie que le format de mail "nom_prenom@domaine.com" est accepté.
	 */
	@Test
	void testFormatAccepte5() {
		assertTrue(EmailValidator.isValidEmail(EMAIL_5));
	}

	/**
	 * Vérifie que le format de mail "nom.@domaine.com" est refusé.
	 */
	@Test
	void testFormatRefuse1() {
		assertFalse(EmailValidator.isValidEmail(INVALID_1));
	}
	
	/**
	 * Vérifie que le format de mail ".nom.prenom@domaine.com" est refusé.
	 */
	@Test
	void testFormatRefuse2() {
		assertFalse(EmailValidator.isValidEmail(INVALID_2));
	}
	
	/**
	 * Vérifie que le format de mail "nom-prenom@domaine.com." est refusé.
	 */
	@Test
	void testFormatRefuse3() {
		assertFalse(EmailValidator.isValidEmail(INVALID_3));
	}
	 
	/**
	 * Vérifie que le format de mail "nom-prenom@.co.in" est refusé.
	 */
	@Test
	void testFormatRefuse4() {
		assertFalse(EmailValidator.isValidEmail(INVALID_4));
	}
	
	/**
	 * Vérifie que le format de mail "nomprenom@.com" est refusé.
	 */
	@Test
	void testFormatRefuse5() {
		assertFalse(EmailValidator.isValidEmail(INVALID_5));
	}

}