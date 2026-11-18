package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	
	private Village village;
	private Chef abraracourcix;
	private ControlEmmenager controlEmmenager;

	@BeforeEach
	public void setUp() {
		System.out.println("setUp");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);

	}

	@Test
	void testControlEmmenager() {
		controlEmmenager = new ControlEmmenager(village);
		assertNotNull(controlEmmenager, "Constructeur ne renvoie pas null");
	}

	@Test
	void testIsHabitant() {
		controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"));
		assertFalse(controlEmmenager.isHabitant("Asterix"), "Existe pas");
		controlEmmenager.ajouterDruide("Panoramix", 3, 5, 9);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
	}

	@Test
	void testAjouterDruide() {
		controlEmmenager = new ControlEmmenager(village);
		assertFalse(controlEmmenager.isHabitant("Panoramix"));
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
		assertFalse(controlEmmenager.isHabitant("Asterix"));
	}

	@Test
	void testAjouterGaulois() {
		controlEmmenager = new ControlEmmenager(village);
		assertFalse(controlEmmenager.isHabitant("Bonemine"));
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"));
		assertFalse(controlEmmenager.isHabitant("Asterix"));
	}

	@Test
	void testAjouterBeaucoupDeGaulois() {
		fail("Not yet implemented");
	}

}
