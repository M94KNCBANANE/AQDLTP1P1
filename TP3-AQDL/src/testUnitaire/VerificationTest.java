package testUnitaire;

import main.Verification;

import org.junit.Assert;
import org.junit.Test;

public class VerificationTest {

	String[] tab = {"Manon", "Richard", "Bobby", "Fred"};
	
	@Test
	public void testPresenceTableauVrai() {
		
		boolean test = Verification.presenceTableau(tab, "Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPresenceTableauFaux() {
		boolean test = Verification.presenceTableau(tab, "toto");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testPrixPlatFonctionneVrai() {
		boolean test = Verification.prixPlatFonctionne("10.75");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testPrixPlatFonctionneFaux() {
		boolean test = Verification.prixPlatFonctionne("allo");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneVrai() {
		boolean test = Verification.quantitePlatFonctionne("2");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testQuantitePlatFonctionneFaux() {
		boolean test = Verification.quantitePlatFonctionne("test");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectVrai() {
		boolean test = Verification.formatPlatCorrect("Repas_Poulet");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxEspace() {
		boolean test = Verification.formatPlatCorrect("Repas Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxPoint() {
		boolean test = Verification.formatPlatCorrect("Repas.Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxTiret() {
		boolean test = Verification.formatPlatCorrect("Repas-Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatPlatCorrectFauxNombre() {
		boolean test = Verification.formatPlatCorrect("Repas_Poulet3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectVrai() {
		boolean test = Verification.formatClientCorrect("Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxNombre() {
		boolean test = Verification.formatClientCorrect("Manon3");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectFauxEspace() {
		boolean test = Verification.formatClientCorrect("Manon Ginette");
		
		Assert.assertFalse(test);
	}
	

}
