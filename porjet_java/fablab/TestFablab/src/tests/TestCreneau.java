package tests;
/**
 * Représente de test creneau.
 * * @author Soulaima boussemi
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import fablab.Creneau;
import fablab.Equipement;
import fablab.CategorieEquipement;

public class TestCreneau {

    @Test
    public void testConstructeur() {  

        LocalDate debut = LocalDate.of(2026, 11, 19);
        LocalDate fin = LocalDate.of(2026, 11, 20);

        Equipement eq = new Equipement(
            "Imprimante 3D",
            "Imprimante à résine",
            CategorieEquipement.IMPRIMANTE_RESINE,
            10.0,
            false
        );

        Creneau c = new Creneau(debut, fin, eq);

        assertEquals(debut, c.getDebut());
        assertEquals(fin, c.getFin());
        assertEquals(eq, c.getEquipement());
    }
}