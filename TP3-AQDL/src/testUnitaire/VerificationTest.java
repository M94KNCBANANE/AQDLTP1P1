package testUnitaire;

import main.NomClient;
import main.Plats;
import main.Verification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VerificationTest {

	NomClient[] tabClient = new NomClient[4];
	Plats[] tabPlats = new Plats[4];
	Verification verif;
	
	@Before
	public void AvantChaqueTest() {
		verif = new Verification();
		
		for( int i = 0 ; i < tabClient.length ; i++) {
			tabClient[i] = new NomClient();
			tabPlats[i] = new Plats();
		}
		
		tabClient[0].setNom("Manon");
		tabClient[1].setNom("Gerard");
		tabClient[2].setNom("Alex");
		tabClient[3].setNom("Fred");
		tabPlats[0].setPlat("Frite");
		tabPlats[1].setPlat("Repas_Poulet");
		tabPlats[2].setPlat("Patate");
		tabPlats[3].setPlat("Poutine");
	}
	
	@Test
	public void testPresenceClientVrai() {
		
		boolean test = verif.presenceClient(tabClient, "Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPresenceClientFaux() {
		boolean test = verif.presenceClient(tabClient, "toto");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testPresencePlatVrai() {
		
		boolean test = verif.presencePlat(tabPlats, "Frite");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPresencePlatFaux() {
		boolean test = verif.presencePlat(tabPlats, "Manger");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testPrixPlatFonctionneVrai() {
		boolean test = verif.prixPlatFonctionne("10.75");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPrixPlatFonctionneFaux() {
		boolean test = verif.prixPlatFonctionne("allo");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneVrai() {
		boolean test = verif.quantitePlatFonctionne("2");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneFaux() {
		boolean test = verif.quantitePlatFonctionne("test");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectVrai() {
		boolean test = verif.formatPlatCorrect("Repas_Poulet");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxEspace() {
		boolean test = verif.formatPlatCorrect("Repas Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxPoint() {
		boolean test = verif.formatPlatCorrect("Repas.Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxTiret() {
		boolean test = verif.formatPlatCorrect("Repas-Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxNombre() {
		boolean test = verif.formatPlatCorrect("Repas_Poulet3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectVrai() {
		boolean test = verif.formatClientCorrect("Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxNombre() {
		boolean test = verif.formatClientCorrect("Manon3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxEspace() {
		boolean test = verif.formatClientCorrect("Manon Ginette");
		
		Assert.assertFalse(test);
	}
	

}
