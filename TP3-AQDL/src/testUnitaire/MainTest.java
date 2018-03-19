package testUnitaire;

import org.junit.Assert;
import org.junit.Test;

import main.Main;

public class MainTest {

	@Test
	public void testCalculerTPS() {
		double montantDeLaTPS = Main.calculerTPS(100);
		
		Assert.assertEquals(5, montantDeLaTPS, 0);
	}
	
	@Test
	public void testCalculerTVQ() {
		double montantDeLaTVQ = Main.calculerTVQ(100);
		
		Assert.assertEquals(10, montantDeLaTVQ, 0);
	}

}
