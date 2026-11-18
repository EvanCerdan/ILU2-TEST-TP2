package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	
	private Village village;
	private Chef abraracourcix;
	private ControlAfficherMarche controlAfficherMarche;

	@BeforeEach
	public void setUp() {
		System.out.println("setUp");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}
	
	@Test
	void testControlAfficherMarche() {
		controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null, l'objet est bien crée");
	}

	@Test
	void testControlDonnerInfosMarche() {
	    controlAfficherMarche = new ControlAfficherMarche(village);

	    String[] infos = controlAfficherMarche.donnerInfosMarche();

	    assertNotNull(infos, "Le tableau ne doit pas être null");
	    assertEquals(0, infos.length, "Le tableau doit être vide car aucun vendeur n'est installé");
	}
	
	@Test
	void testControlDonnerInfosMarcheAvecPlusieursVendeurs() {

	    controlAfficherMarche = new ControlAfficherMarche(village);

	    // Création de deux villageois
	    Gaulois obelix = new Gaulois("Obelix", 15);
	    Gaulois assurancetourix = new Gaulois("Assurancetourix", 5);

	    // Installation des vendeurs sur le marché
	    village.installerVendeur(abraracourcix, "Poisson", 10);
	    village.installerVendeur(obelix, "Menhirs", 20);
	    village.installerVendeur(assurancetourix, "Lyres", 5);

	    // Récupération des informations du marché
	    String[] infos = controlAfficherMarche.donnerInfosMarche();

	    // Vérifications
	    assertNotNull(infos);
	    assertEquals(9, infos.length, "3 étals occupés doivent donner 9 informations");

	    // Premier étal
	    assertEquals("Abraracourcix", infos[0]);
	    assertEquals("10", infos[1]);
	    assertEquals("Poisson", infos[2]);

	    // Deuxième étal
	    assertEquals("Obelix", infos[3]);
	    assertEquals("20", infos[4]);
	    assertEquals("Menhirs", infos[5]);

	    // Troisième étal
	    assertEquals("Assurancetourix", infos[6]);
	    assertEquals("5", infos[7]);
	    assertEquals("Lyres", infos[8]);
	}
	
}
