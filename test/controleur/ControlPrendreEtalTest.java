
package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {

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
    void testControlPrendreEtal() {

        ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
        ControlPrendreEtal controlPrendreEtal =  new ControlPrendreEtal(controlVerifierIdentite, village);
        assertNotNull(controlPrendreEtal, "Le contrôleur ne doit pas être null");
    }

    @Test
    void testResteEtals() {
        ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
        ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);

        assertTrue(controlPrendreEtal.resteEtals(), "Il doit rester des étals disponibles");
    }

    @Test
    void testPrendreEtal() {

        ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);

        ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);

        // Le chef prend un étal
        int numeroEtal = controlPrendreEtal.prendreEtal("Abraracourcix", "Poisson", 10);
        assertEquals(0, numeroEtal, "Le premier étal disponible doit être le numéro 0");
    }

    @Test
    void testVerifierIdentite() {

        ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
        ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);

        assertTrue(controlPrendreEtal.verifierIdentite("Abraracourcix"),"L'identité du chef doit être vérifiée");
    }
    
    @Test
    void testResteEtalsMarchePlein() {

        ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(new ControlVerifierIdentite(village), village);

        for (int i = 0; i < 5; i++) {
            Gaulois vendeur = new Gaulois("Vendeur" + i, 5);
            village.ajouterHabitant(vendeur);
            village.installerVendeur(vendeur, "Poisson", 10);
        }

        assertFalse(controlPrendreEtal.resteEtals(), "Il ne doit plus rester d'étals disponibles");
    }
    
    @Test
    void testPrendrePlusieursEtals() {

        ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(new ControlVerifierIdentite(village), village);

        Gaulois obelix = new Gaulois("Obelix", 15);
        Gaulois assurancetourix = new Gaulois("Assurancetourix", 5);

        village.ajouterHabitant(obelix);
        village.ajouterHabitant(assurancetourix);

        int etal1 = controlPrendreEtal.prendreEtal("Obelix", "Menhirs", 20);

        int etal2 = controlPrendreEtal.prendreEtal("Assurancetourix", "Lyres", 5);

        assertEquals(0, etal1);
        assertEquals(1, etal2);
        assertNotEquals(etal1, etal2);
    }
}
