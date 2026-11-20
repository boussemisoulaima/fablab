package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fablab.Consommable;

class TestConsommable {

    private Consommable consommable;

    @BeforeEach
    void setUp() {
        consommable = new Consommable(
            "PLA rouge",
            "Filament pour imprimante 3D",
            20.0,
            586,
            "grammes",
            true
        );
    }

    // Test du constructeur et des getters
    @Test
    void testConstructeurEtGetters() {
        assertEquals("PLA rouge", consommable.getNom());
        assertEquals("Filament pour imprimante 3D", consommable.getDescription());
        assertEquals(20.0, consommable.getPrixExemplaire());
        assertEquals(586, consommable.getQuantiteExemplaire());
        assertEquals("grammes", consommable.getUnite());
        assertTrue(consommable.getSecable());
    }

    // Test du constructeur avec un prix négatif
    @Test
    void testConstructeurPrixNegatif() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Consommable("PLA", "Filament", -10.0, 586, "grammes", true);
        });
    }

    // Test du constructeur avec une quantité négative
    @Test
    void testConstructeurQuantiteNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Consommable("PLA", "Filament", 20.0, -5, "grammes", true);
        });
    }

    // Test du setter du nom
    @Test
    void testSetNom() {
        consommable.setNom("PLA bleu");
        assertEquals("PLA bleu", consommable.getNom());
    }

    // Test du setter de la description
    @Test
    void testSetDescription() {
        consommable.setDescription("Nouveau filament");
        assertEquals("Nouveau filament", consommable.getDescription());
    }

    // Test du setter du prix
    @Test
    void testSetPrixExemplaire() {
        consommable.setPrix_exemplaire(30.0);
        assertEquals(30.0, consommable.getPrixExemplaire());
    }

    // Test du setter du prix avec une valeur négative
    @Test
    void testSetPrixNegatif() {
        assertThrows(IllegalArgumentException.class, () -> {
            consommable.setPrix_exemplaire(-5.0);
        });
    }

    // Test du setter de la quantité
    @Test
    void testSetQuantiteExemplaire() {
        consommable.setQuantite_exemplaire(300);
        assertEquals(300, consommable.getQuantiteExemplaire());
    }

    // Test du setter de la quantité avec une valeur négative
    @Test
    void testSetQuantiteNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            consommable.setQuantite_exemplaire(-10);
        });
    }

    // Test du setter de l'unité
    @Test
    void testSetUnite() {
        consommable.setUnite("kilogrammes");
        assertEquals("kilogrammes", consommable.getUnite());
    }
}

