
package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;
import villagegaulois.Etal;

class ControlTrouverEtalVendeurTest {

    private Village village;
    private Chef abraracourcix;

    @BeforeEach
    public void setUp() {
        System.out.println("setUp");
        village = new Village("le village des irréductibles", 10, 5);
        abraracourcix = new Chef("Abraracourcix", 10, village);
        village.setChef(abraracourcix);
    }

    @Test
    void testControlTrouverEtalVendeur() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur =
                new ControlTrouverEtalVendeur(village);

        assertNotNull(controlTrouverEtalVendeur,
                "Le contrôleur ne doit pas être null");
    }

    @Test
    void testTrouverEtalVendeurExiste() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur =
                new ControlTrouverEtalVendeur(village);

        village.installerVendeur(abraracourcix, "Poisson", 10);

        Etal etal = controlTrouverEtalVendeur
                .trouverEtalVendeur("Abraracourcix");

        assertNotNull(etal,
                "L'étal du vendeur doit être retrouvé");
    }

    @Test
    void testTrouverEtalVendeurSansEtal() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur =
                new ControlTrouverEtalVendeur(village);

        assertNull(controlTrouverEtalVendeur
                .trouverEtalVendeur("Abraracourcix"),
                "Le vendeur ne possède pas encore d'étal");
    }

    @Test
    void testTrouverEtalVendeurInconnu() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur =
                new ControlTrouverEtalVendeur(village);

        assertNull(controlTrouverEtalVendeur
                .trouverEtalVendeur("Obelix"),
                "Un vendeur inconnu doit retourner null");
    }

    @Test
    void testTrouverEtalVendeurBonVendeur() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur =
                new ControlTrouverEtalVendeur(village);

        Gaulois obelix = new Gaulois("Obelix", 15);
        village.ajouterHabitant(obelix);

        village.installerVendeur(abraracourcix, "Poisson", 10);
        village.installerVendeur(obelix, "Menhirs", 20);

        Etal etal = controlTrouverEtalVendeur
                .trouverEtalVendeur("Obelix");

        assertNotNull(etal,
                "L'étal d'Obelix doit être retrouvé");

        assertEquals("Obelix", etal.getVendeur().getNom(),
                "L'étal retrouvé doit appartenir à Obelix");
    }
}
