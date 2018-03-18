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
	public void testFormatPlatCorrectFaux() {
		boolean test = VerificationTest.formatPlatCorrect("Repas Poulet");
		
		Assert.assertFalse(test);
	}
	
	@Test
	public void testFormatClientCorrectVrai() {
		boolean test = VerificationTest.formatClientCorrect("Manon");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testFormatClientCorrectFaux() {
		boolean test = VerificationTest.formatClientCorrect("Manon3");
		
		Assert.assertFalse(test);
	}
}
