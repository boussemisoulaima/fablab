package fablab;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Classe repérsentant un validateur de mail
 * 
 * Code inspiré de
 * https://medium.com/@python-javascript-php-html-css/top-methods-and-libraries-for-email-validation-in-java-496869085b72
 *
 * @author Assia Simonnin
 *
 */

public class EmailValidator {

	/**
	 * regexPattern l'expression régulière qui définit le format de mail à
	 * respecter. selon cette expression le mail peut contenir:
	 * <ul>
	 * <li>des chiffres de 0 à 9</li>
	 * <li>des lettres de a à z en miniscule et/ou majuscule</li>
	 * <li>les caractères specitiaux comme le tiret bas '_', le trait d'union '-' et
	 * le point '.'</li>
	 * </ul>
	 *
	 * L'expression interdit de commencer le mail par un point et finir par un point
	 * dans la partie du domaine (apres @). L'expression interdit également d'avoir
	 * des points consécutifs dans le mail.
	 */
	private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"; 

	/**
	 * Le pattern compilé de l'expression régulière
	 */
	private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

	/**
	 * Crée un validateur de mail avec l'attribut valide instancié à
	 * <code>false</code>.
	 */
	public EmailValidator() {
		super();
	}

	/**
	 * Test de la validation d'un mail suite à la comparaison avec un expression
	 * régulière. Si le mail est valide l'attribut valide se met à jour
	 * <code>true</code> sinon reste <code>false</code>.
	 *
	 * @param email l'adresse email de l'utilisateur
	 *
	 * @return <code>true</code> si le format de l'email est correcte / valide
	 * 		   <code>false</code> sinon.
	 */

	public static boolean isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
