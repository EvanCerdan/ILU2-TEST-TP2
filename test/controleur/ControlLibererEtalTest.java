
package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {

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
    void testControlLibererEtal() {

        ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);

        ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);

        assertNotNull(controlLibererEtal, "Le contrôleur ne doit pas être null");
    }

    @Test
    void testIsVendeurVrai() {

        ControlLibererEtal controlLibererEtal =  new ControlLibererEtal( new ControlTrouverEtalVendeur(village));

        village.installerVendeur(abraracourcix, "Poisson", 10);

        assertTrue(controlLibererEtal.isVendeur("Abraracourcix"),"Le vendeur doit être reconnu");
    }

    @Test
    void testIsVendeurFaux() {

        ControlLibererEtal controlLibererEtal = new ControlLibererEtal( new ControlTrouverEtalVendeur(village));

        assertFalse(controlLibererEtal.isVendeur("Obelix"), "Un vendeur sans étal ne doit pas être reconnu");
    }

    @Test
    void testLibererEtal() {

        ControlLibererEtal controlLibererEtal = new ControlLibererEtal( new ControlTrouverEtalVendeur(village));

        village.installerVendeur(abraracourcix, "Poisson", 10);

        String[] infos = controlLibererEtal.libererEtal("Abraracourcix");

        assertNotNull(infos, "Les informations de l'étal ne doivent pas être null");

        assertEquals(5, infos.length, "Le tableau doit contenir 5 informations");

        assertEquals("true", infos[0]);
        assertEquals("Abraracourcix", infos[1]);
        assertEquals("Poisson", infos[2]);
        assertEquals("10", infos[3]);
    }

    @Test
    void testLibererEtalVendeurInconnu() {

        ControlLibererEtal controlLibererEtal = new ControlLibererEtal( new ControlTrouverEtalVendeur(village));

        String[] infos = controlLibererEtal.libererEtal("Inconnu");

        assertNull(infos, "La méthode doit retourner null si le vendeur est inconnu");
    }

    @Test
    void testLibererEtalDisponible() {

        ControlLibererEtal controlLibererEtal = new ControlLibererEtal( new ControlTrouverEtalVendeur(village));

        village.installerVendeur(abraracourcix, "Poisson", 10);

        controlLibererEtal.libererEtal("Abraracourcix");

        assertTrue(village.rechercherEtalVide(), "Un étal doit être disponible après la libération");
    }

}
