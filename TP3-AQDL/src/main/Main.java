package main;
import java.io.IOException;

public class Main {
	
	public static NomClient[] tabClient;
	public static Plats[] tabPlat;
	public static Commande[] tabCommande;
	private static Refactoring ref = new Refactoring();
	
	public static void main( String[] args ) throws IOException {
		String[] texte = Lecture.lectureFichier();
		tabClient = new NomClient[Integer.parseInt( texte[texte.length-3] )];
		ref.initialiserClient(tabClient);
		tabPlat = new Plats[Integer.parseInt( texte[texte.length-2] )];
		ref.initialiserPlats(tabPlat);
		tabCommande = new Commande[Integer.parseInt( texte[texte.length-1] )];
		ref.initialiserCommande(tabCommande);
		ref.insererDonnes(texte, tabClient, tabPlat, tabCommande);		
		ref.afficherEcrireFacture(tabClient, tabPlat, tabCommande);

		}
	
	

	}
