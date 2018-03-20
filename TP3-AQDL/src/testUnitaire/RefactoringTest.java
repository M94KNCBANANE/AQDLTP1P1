package testUnitaire;

import org.junit.Assert;
import org.junit.Test;

import main.Refactoring;

public class RefactoringTest {
	
	Refactoring refact = new Refactoring();

	@Test
	public void testCalculerTPS() {
		double montantDeLaTPS = refact.calculerTPS(100);
		
		Assert.assertEquals(5, montantDeLaTPS, 0);
	}
	
	@Test
	public void testCalculerTVQ() {
		double montantDeLaTVQ = refact.calculerTVQ(100);
		
		Assert.assertEquals(10, montantDeLaTVQ, 0);
	}

}
