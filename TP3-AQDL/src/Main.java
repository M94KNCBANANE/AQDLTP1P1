import java.io.IOException;

public class Main {
	
	public static String erreur = "Le fichier ne respecta pas le format demandé !";
	public static NomClient[] tabClient;
	public static Plats[] tabPlat;
	public static Commande[] tabCommande;
	
	
	public static void main( String[] args ) throws IOException {
		
		int test;
		String[] texte = Lecture.lectureFichier();
		
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
		
		if( test == tabCommande.length ){
			
			afficherFacture();
			
		}else{
			
			System.out.println( erreur );
			
		}
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

	public static int Verifier( String[] texte ) {
		
		int indiceTexte = 0;
		int indiceTab = 0;
		String[] plat;	
		int test = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Plats :" ) ){
			
			tabClient[indiceTab++].setNom( texte[indiceTexte++] );
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Commandes :" ) ){
			
			plat = texte[indiceTexte++].split( " " );
			
			tabPlat[indiceTab].setPlat( plat[0] );			
			tabPlat[indiceTab++].setPrix( Double.parseDouble( plat[1] ) );
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Fin" ) ){
			
			plat = texte[indiceTexte++].split( " " );
			
			if( plat.length == 3 ){
				
				for( int i = 0;i < tabClient.length;i++ ){
					
					if( plat[0].equals( tabClient[i].getNom() ) ){
						
						for( int j = 0;j < tabPlat.length;j++ ){
							
							if( plat[1].equals( tabPlat[j].getPlat() ) ){
								
								try{
									
									tabCommande[indiceTab].setNom( plat[0] );
									tabCommande[indiceTab].setPlat( plat[1] );
									tabCommande[indiceTab++].setQuantite( Integer.parseInt( plat[2] ) );
									test++;
									
								}catch( Exception e ){
									System.out.println( "Pas une valeur Int possible" );
								}
							}
						}
					}
				}
			}
		}
		return test;
	}
}
