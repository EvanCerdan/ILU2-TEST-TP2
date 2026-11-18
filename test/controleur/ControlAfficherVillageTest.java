package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	
	private Village village;
	private Chef abraracourcix;
	private ControlAfficherVillage controlAfficherVillage;

	@BeforeEach
	public void setUp() {
		System.out.println("setUp");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}
	
	@Test
	void testControlAfficherVillageTest() {
		controlAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null, l'objet est bien crée");
	}
	
	@Test
    void testDonnerNomsVillageois() {
		controlAfficherVillage = new ControlAfficherVillage(village);
		
        Gaulois assurancetourix = new Gaulois("Assurancetourix", 5);
        Druide panoramix = new Druide("Panoramix", 10, 2, 5);

        village.ajouterHabitant(assurancetourix);
        village.ajouterHabitant(panoramix);

        String[] noms = controlAfficherVillage.donnerNomsVillageois();

        assertNotNull(noms, "Le tableau des villageois ne doit pas être null");

        assertEquals(3, noms.length, "Le tableau doit contenir 3 villageois");

        assertEquals("Abraracourcix", noms[0], "Le premier nom doit être celui du chef");

        assertEquals("Assurancetourix", noms[1], "Le deuxième nom doit être celui du villageois");

        assertEquals("le druide Panoramix", noms[2], "Le druide doit être précédé de 'le druide'");
    }
	
	
	@Test
	void testDonnerNomVillage() {
	    controlAfficherVillage = new ControlAfficherVillage(village);

	    assertEquals("le village des irréductibles", controlAfficherVillage.donnerNomVillage());
	}
	
	@Test
	void testDonnerNbEtals() {
	    controlAfficherVillage = new ControlAfficherVillage(village);

	    assertEquals(5, controlAfficherVillage.donnerNbEtals());
	}

}
