package main;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;

import outilsjava.OutilsFichier;

public class Lecture {
	
	static String ligne;
	static BufferedReader ficLecture;
	static String[] lignesFichier;
	static int grosseur;
	static int[] nbChaque = new int[3];
	static int compteur = 0;
	
	public Lecture() throws IOException {
	}
	
	public static String[] lectureFichier(String nomFichier, JTextArea zoneTexte) throws IOException {
		
		nbChaque[0] = -1;
		nbChaque[1] = -1;
		nbChaque[2] = -1;

		
		ficLecture = OutilsFichier.ouvrirFicTexteLecture( nomFichier, zoneTexte );
		
		if (ficLecture != null) {
			grosseur = getGrosseurFichier( ficLecture );
			lignesFichier = new String[grosseur+3];
			
			OutilsFichier.fermerFicTexteLecture( ficLecture, nomFichier );
			ficLecture = OutilsFichier.ouvrirFicTexteLecture( nomFichier, zoneTexte );
			
			for ( int i = 0;( ligne = ficLecture.readLine() ) != null ;i++ ){
				
				lignesFichier[i] = ligne;
				
				switch ( compteur ) {
					
				case 1:
					nbChaque[0]++;
					break;
				case 2:
					nbChaque[1]++;
					break;
				case 3:
					nbChaque[2]++;
					break;
				}
				
				if ( ligne.equals( "Clients :" ) ){
					
					compteur = 1;
					
				} else if ( ligne.equals( "Plats :" ) ){
					
					compteur = 2;
					
				} else if ( ligne.equals( "Commandes :" ) ){
					
					compteur = 3;
					
				} else if ( ligne.equals( "Fin" ) ){
					
					compteur = 4;
					
				}
			}
			
			lignesFichier[lignesFichier.length-3] = "" + nbChaque[0];
			lignesFichier[lignesFichier.length-2] = "" + nbChaque[1];
			lignesFichier[lignesFichier.length-1] = "" + nbChaque[2];
			OutilsFichier.fermerFicTexteLecture( ficLecture, nomFichier );
			
		} else {
			lignesFichier = null;
			Refactoring.ecrireEcran(zoneTexte, "\nErreur, le fichier " + nomFichier
						+ " n'existe pas.");
		}
		
		return lignesFichier;
	}
	
	public static int getGrosseurFichier(BufferedReader ficLecture) throws IOException {
		
		int i = 0;
		
		while ( (ligne = ficLecture.readLine()) != null ){
			
			i++;
			
		}
		
		return i;
	}
}
