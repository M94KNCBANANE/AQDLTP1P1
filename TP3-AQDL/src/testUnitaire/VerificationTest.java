package testUnitaire;

public class VerificationTest {

	public static boolean presenceTableau( String[] tab, String chaine ) {
			
		boolean presenceTableau = false;;
		
		for (int i = 0 ; i < tab.length ; i++) {
			if (tab[i].equals(chaine)) {
				presenceTableau = true;
				break;
			}
		}
		return presenceTableau;
	}
	
	public static boolean prixPlatFonctionne(String prix) {
		
		boolean conversionReussi = false;
		
		try {
			
			Double.parseDouble(prix);
			conversionReussi = true;
			
		} catch (Exception e){
			
			conversionReussi = false;
		}
		
		return conversionReussi;
	}
	
	public static boolean quantitePlatFonctionne(String qte) {
		
		boolean qteOK = false;
		
		try {
			
			Integer.parseInt( qte );
			qteOK = true;
		
		} catch (Exception e) {
			
			qteOK = false;
		}
		
		return qteOK;
	}
	
	public static boolean formatPlatCorrect(String plat) {
		
		boolean platOK = false;
		char[] tab = plat.toCharArray();
		
		for (int i = 0 ; i < tab.length ; i++) {
			
			if ( Character.isLetter(tab[i]) || tab[i] == '_' ) {
				platOK = true;
			} else {
				platOK = false;
				break;
			}
		}
		
		return platOK;
	}
	
	public static boolean formatClientCorrect(String client) {
		
		boolean clientOK = false;
		char[] tab = client.toCharArray();
		
		for (int i = 0 ; i < tab.length ; i++) {
			
			if ( Character.isLetter(tab[i]) || tab[i] == '-' ) {
				clientOK = true;
			} else {
				clientOK = false;
				break;
			}
		}
		
		return clientOK;
	}
}

















