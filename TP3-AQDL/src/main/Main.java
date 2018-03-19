package main;
import java.io.IOException;

public class Main {
	
	public static String erreur = "";
	public static NomClient[] tabClient;
	public static Plats[] tabPlat;
	public static Commande[] tabCommande;
	public static Verification verif = new Verification();
	
	
	public static void main( String[] args ) throws IOException {
		String[] texte = Lecture.lectureFichier();
		
		importationFichierVariables(texte);
	}
	
	public static boolean importationFichierVariables(String[] texte) {
		
		int test;
		boolean importOK = false;
		
		tabClient = new NomClient[Integer.parseInt( texte[texte.length-3] )];
		
		for( int i = 0;i < tabClient.length;i++ ){
			
			tabClient[i] = new NomClient();
			
		}
		
		tabPlat = new Plats[Integer.parseInt( texte[texte.length-2] )];
		
		for( int i = 0;i < tabPlat.length;i++ ){
			
			tabPlat[i] = new Plats();
			
		}
		
		tabCommande = new Commande[Integer.parseInt( texte[texte.length-1] )];
		
		for( int i = 0;i < tabCommande.length;i++ ){
			
			tabCommande[i] = new Commande();
			
		}
		
		test = Verifier(texte);
		System.out.println(erreur);
		if( test == tabCommande.length ){
			
			importOK = true;
			afficherFacture();
			
		}else{
			
			importOK = false;
			
		}
		
		return importOK;
	}
	
	private static void afficherFacture() {
		
		double[] prix = new double[tabClient.length];
		
		//indice client
		for( int i = 0;i < tabClient.length;i++ ){
			
			prix[i] = 0;
			
			//indice commande
			for( int j = 0;j < tabCommande.length;j++ ){
				
				if( tabClient[i].getNom().equals( tabCommande[j].getNom() ) ){
					
					//indice plats
					for( int x = 0; x < tabPlat.length;x++ ){
						
						if( tabPlat[x].getPlat().equals( tabCommande[j].getPlat() ) ){
							
							prix[i] += ( tabPlat[x].getPrix() * tabCommande[j].getQuantite() );
							
						}
					}
				}
			}
			System.out.println( tabClient[i].getNom() + " : " + prix[i] );
		}
	}

	public static double calculerTPS(double prix) {
		
		final double TPS = 0.05;
		
		return prix * TPS;
	}
	
	public static double calculerTVQ(double prix) {
		
		final double TVQ = 0.10;
		
		return prix * TVQ;
	}
	
	public static int Verifier( String[] texte ) {
		
		int indiceTexte = 0;
		int indiceTab = 0;
		String[] plat;	
		int test = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Plats :" ) ){
			if(verif.formatClientCorrect(texte[indiceTexte])){
			tabClient[indiceTab++].setNom( texte[indiceTexte++] );
			}else{
				 erreur += "Le nom du client suivant "+ texte[indiceTexte++] + " ne respecte pas le format demand� \n";
			}
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Commandes :" ) ){
			
			plat = texte[indiceTexte++].split( " " );
			if(verif.formatPlatCorrect(plat[0]) && verif.prixPlatFonctionne(plat[1])){
			tabPlat[indiceTab].setPlat( plat[0] );			
			tabPlat[indiceTab++].setPrix( Double.parseDouble( plat[1] ) );
			}else if(!verif.formatPlatCorrect(plat[0])){
				erreur +="Le plat suivant "+plat[0]+" ne respecte pas le format demand�\n";
			}else{
				erreur +="Le prix "+plat[1] + " du plat suivant "+plat[0]+" ne respecte pas le format demand�\n";
			}
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Fin" ) ){
			
			plat = texte[indiceTexte].split( " " );
			
			if( plat.length == 3 ){
				
				if(verif.presenceClient(tabClient,plat[0]) && verif.presencePlat(tabPlat,plat[1]) && verif.quantitePlatFonctionne(plat[2])){
					tabCommande[indiceTab].setNom( plat[0] );
					tabCommande[indiceTab].setPlat( plat[1] );
					tabCommande[indiceTab++].setQuantite( Integer.parseInt( plat[2] ) );
					test++;
				}else{
					if(!verif.presenceClient(tabClient,plat[0])){
					erreur += "Le client suivant "+plat[0]+" de la commande "+texte[indiceTexte]+ " n'existe pas\n";
				}
				if(!verif.presencePlat(tabPlat,plat[1])){
					erreur += "Le plat suivant "+plat[1]+" de la commande "+texte[indiceTexte]+ " n'existe pas\n";
				}
				if(!verif.quantitePlatFonctionne(plat[2])){
					erreur += "La quantit� "+plat[2]+" de la commande "+texte[indiceTexte]+ " n'est pas valide\n";
				}
				}
								
				}
			indiceTexte++;
					
				
			}
		
		return test;
	}
}
