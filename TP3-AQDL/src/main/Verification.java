package main;
public class Verification {

	
	public Verification() {
		
	}
	
	public boolean presencePlat( Plats[] tab, String chaine, int qte ) {
		
		boolean presenceTableau = false;
		
		for (int i = 0 ; i < qte ; i++) {
				if (tab[i].getPlat().equals(chaine)) {
					presenceTableau = true;
					break;
			}
		}
		return presenceTableau;
	}
	
	public boolean presenceClient( NomClient[] tab, String chaine, int qte ) {
			
		boolean presenceTableau = false;;
		
		for (int i = 0 ; i < qte ; i++) {
			
			if (tab[i].getNom().equals(chaine)) {
				presenceTableau = true;
				break;
			}
		}
		return presenceTableau;
	}
	
	public boolean prixPlatFonctionne(String prix) {
		
		boolean conversionReussi = false;
		
		try {
			
			Double.parseDouble(prix);
			conversionReussi = true;
			
		} catch (Exception e){
			
			conversionReussi = false;
		}
		
		return conversionReussi;
	}
	
	public boolean quantitePlatFonctionne(String qte) {
		
		boolean qteOK = false;
		
		try {
			
			Integer.parseInt( qte );
			qteOK = true;
		
		} catch (Exception e) {
			
			qteOK = false;
		}
		
		return qteOK;
	}
	
	public boolean formatPlatCorrect(String plat) {
		
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
	
	public boolean formatClientCorrect(String client) {
		
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

	public boolean formatTableCorrect(String string, int max) {
		boolean TableOK = false;
		
		try {
			
			int test=Integer.parseInt( string );
			if(test <=max){
			TableOK = true;
			}
		} catch (Exception e) {
			
			TableOK = false;
		}
		
		return TableOK;
}
}

















