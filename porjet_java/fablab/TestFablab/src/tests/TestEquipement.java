
package tests;
/**
 * Représente un test équipement.
 * * @author Soulaima boussemi
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fablab.Equipement;
import fablab.CategorieEquipement;

public class TestEquipement {

    @Test
    public void testConstructeur() {  

        Equipement eq = new Equipement(
            "Imprimante 3D",
            "Imprimante à résine",
            CategorieEquipement.IMPRIMANTE_RESINE,
            10.0,
            false
        );

        assertEquals("Imprimante 3D", eq.getNom());
        assertEquals("Imprimante à résine", eq.getDescription());
        assertEquals(CategorieEquipement.IMPRIMANTE_RESINE, eq.getType());
        assertEquals(10.0, eq.getCoutHoraire());
        assertEquals("", eq.getImage());
        assertFalse(eq.isReserve());

        assertNotNull(eq.getConsommables());
        assertTrue(eq.getConsommables().isEmpty());
    }
}
