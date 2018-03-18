package testUnitaire;

import org.junit.Assert;
import org.junit.Test;

public class classeTest {

	String[] tab = {"Manon", "Richard", "Bobby", "Fred"};
	
	@Test
	public void testPresenceTableauVrai() {
		
		boolean test = VerificationTest.presenceTableau(tab, "Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPresenceTableauFaux() {
		boolean test = VerificationTest.presenceTableau(tab, "toto");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testPrixPlatFonctionneVrai() {
		boolean test = VerificationTest.prixPlatFonctionne("10.75");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPrixPlatFonctionneFaux() {
		boolean test = VerificationTest.prixPlatFonctionne("allo");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneVrai() {
		boolean test = VerificationTest.quantitePlatFonctionne("2");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneFaux() {
		boolean test = VerificationTest.quantitePlatFonctionne("test");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectVrai() {
		boolean test = VerificationTest.formatPlatCorrect("Repas_Poulet");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxEspace() {
		boolean test = VerificationTest.formatPlatCorrect("Repas Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxPoint() {
		boolean test = VerificationTest.formatPlatCorrect("Repas.Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxTiret() {
		boolean test = VerificationTest.formatPlatCorrect("Repas-Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxNombre() {
		boolean test = VerificationTest.formatPlatCorrect("Repas_Poulet3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectVrai() {
		boolean test = VerificationTest.formatClientCorrect("Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxNombre() {
		boolean test = VerificationTest.formatClientCorrect("Manon3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxEspace() {
		boolean test = VerificationTest.formatClientCorrect("Manon Ginette");
		
		Assert.assertFalse(test);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCalculerTPS() {
		double montantDeLaTPS = MainTest.calculerTPS(100);
		
		Assert.assertEquals(5, montantDeLaTPS, 0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCalculerTVQ() {
		double montantDeLaTVQ = MainTest.calculerTVQ(100);
		
		Assert.assertEquals(10, montantDeLaTVQ, 0);
	}
}
