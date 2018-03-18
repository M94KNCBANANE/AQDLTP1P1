package testUnitaire;

public class MainTest {

	public static double calculerTPS(double prix) {
		
		final double TPS = 0.05;
		
		return prix * TPS;
	}
	
	public static double calculerTVQ(double prix) {
		
		final double TVQ = 0.10;
		
		return prix * TVQ;
	}
}
