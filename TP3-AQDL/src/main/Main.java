package main;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import outilsjava.OutilsAffichage;
import outilsjava.OutilsFichier;

public class Main {
	
	public static String erreur = "";
	public static NomClient[] tabClient;
	public static Plats[] tabPlat;
	public static Commande[] tabCommande;
	public static Verification verif = new Verification();
	public static int qteClients = 0;
	public static int qtePlats = 0;
	public static int qteCommandes = 0;
	
	
	public static void main( String[] args ) throws IOException {
		String[] texte = Lecture.lectureFichier();
		
		importationFichierVariables(texte);
	}
	
	public static void importationFichierVariables(String[] texte) throws IOException {
		
		tabClient = new NomClient[Integer.parseInt( texte[texte.length-3] )];
		
		for( int i = 0;i < tabClient.length;i++ ){
			
			tabClient[i] = new NomClient();
			qteClients++;
		}
		
		tabPlat = new Plats[Integer.parseInt( texte[texte.length-2] )];
		
		for( int i = 0;i < tabPlat.length;i++ ){
			
			tabPlat[i] = new Plats();
			qtePlats++;
		}
		
		tabCommande = new Commande[Integer.parseInt( texte[texte.length-1] )];
		
		for( int i = 0;i < tabCommande.length;i++ ){
			
			tabCommande[i] = new Commande();
			qteCommandes++;
		}
		
		Verifier(texte);
		System.out.println(erreur);
		
		
		afficherEcrireFacture();
	}
	
	private static void afficherEcrireFacture() throws IOException {
		
		BufferedWriter ficEcriture;
		DateFormat df = new SimpleDateFormat("dd-MM-yy-HH.mm");
		DateFormat dff = new SimpleDateFormat("dd-MM-yy HH:mm");
		Date dateobj = new Date();
		String nomFichier = "Facture-du-" + df.format(dateobj) + ".txt";
				
		ficEcriture = OutilsFichier.ouvrirFicTexteEcriture(nomFichier);
		
		double[] prix = new double[tabClient.length];
		
		//indice client
		
		System.out.println("Facture du: " + dff.format(dateobj) + "\n");
		ficEcriture.write("Facture du: " + dff.format(dateobj) + "\r\n");
		ficEcriture.write(erreur + "\r\n\r\n");
		for( int i = 0;i < qteClients;i++ ){
			
			prix[i] = 0;
			
			//indice commande
			
			for( int j = 0;j < qteCommandes;j++ ){
				
				if( tabClient[i].getNom().equals( tabCommande[j].getNom() ) ){
					
					//indice plats
					for( int x = 0; x < qtePlats ;x++ ){
						
						if( tabPlat[x].getPlat().equals( tabCommande[j].getPlat() ) ){
							
							prix[i] += ( tabPlat[x].getPrix() * tabCommande[j].getQuantite() );
							
						}
					}
				}
			}
			
			if (prix[i] != 0.0) {
				System.out.println( tabClient[i].getNom() + " : " + OutilsAffichage.formaterMonetaire((prix[i] + calculerTPS(prix[i]) + calculerTVQ(prix[i])), 2) );
				ficEcriture.write( tabClient[i].getNom() + " : " + OutilsAffichage.formaterMonetaire((prix[i] + calculerTPS(prix[i]) + calculerTVQ(prix[i])), 2) + "\r\n");
				
			}
			
		}
		OutilsFichier.fermerFicTexteEcriture(ficEcriture, nomFichier);
	}

	public static double calculerTPS(double prix) {
		
		final double TPS = 0.05;
		
		return prix * TPS;
	}
	
	public static double calculerTVQ(double prix) {
		
		final double TVQ = 0.10;
		
		return prix * TVQ;
	}
	
	public static void Verifier( String[] texte ) {
		
		int indiceTexte = 0;
		int indiceTab = 0;
		String[] plat;	
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Plats :" ) ){
			if(verif.formatClientCorrect(texte[indiceTexte])){
			tabClient[indiceTab++].setNom( texte[indiceTexte++] );
			}else{
				 erreur += "Le nom du client suivant "+ texte[indiceTexte++] + " ne respecte pas le format demandé \n";
				 qteClients--;
			}
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Commandes :" ) ){
			
			plat = texte[indiceTexte++].split( " " );
			if(verif.formatPlatCorrect(plat[0]) && verif.prixPlatFonctionne(plat[1])){
			tabPlat[indiceTab].setPlat( plat[0] );			
			tabPlat[indiceTab++].setPrix( Double.parseDouble( plat[1] ) );
			}else {
				if(!verif.formatPlatCorrect(plat[0])){
					erreur +="Le plat suivant "+plat[0]+" ne respecte pas le format demandé\n";
				}else{
					erreur +="Le prix "+plat[1] + " du plat suivant "+plat[0]+" ne respecte pas le format demandé\n";
			}
				qtePlats--;
		}
		}
		
		indiceTab = 0;
		indiceTexte++;
		
		while( !texte[indiceTexte].equals( "Fin" ) ){
			
			plat = texte[indiceTexte].split( " " );
			
			if( plat.length == 3 ){
				
				if(verif.presenceClient(tabClient,plat[0], qteCommandes) && verif.presencePlat(tabPlat,plat[1], qtePlats) && verif.quantitePlatFonctionne(plat[2])){
					tabCommande[indiceTab].setNom( plat[0] );
					tabCommande[indiceTab].setPlat( plat[1] );
					tabCommande[indiceTab++].setQuantite( Integer.parseInt( plat[2] ) );
				}else{
					if(!verif.presenceClient(tabClient,plat[0], qteCommandes)){
					erreur += "Le client suivant "+plat[0]+" de la commande "+texte[indiceTexte]+ " n'existe pas\n";
				}
				if(!verif.presencePlat(tabPlat,plat[1], qtePlats)){
					erreur += "Le plat suivant "+plat[1]+" de la commande "+texte[indiceTexte]+ " n'existe pas\n";
				}
				if(!verif.quantitePlatFonctionne(plat[2])){
					erreur += "La quantité "+plat[2]+" de la commande "+texte[indiceTexte]+ " n'est pas valide\n";
				}
					qteCommandes--;
					
				}
								
				}
			indiceTexte++;
					
				
			}
		
	}
}
