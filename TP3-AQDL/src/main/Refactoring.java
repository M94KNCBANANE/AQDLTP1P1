package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;
import outilsjava.OutilsAffichage;


public class Refactoring {

	private int qteClients = 0;
	private int qtePlats = 0;
	private int qteCommandes = 0;
	private String erreur = "";
	private final int NOMBRE_TABLE = 10;
	
	public Refactoring() {
		
	}

	public void initialiserClient(NomClient[] tab) {
		qteClients=0;
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new NomClient();
			qteClients++;
		}
	}

	public void initialiserPlats(Plats[] tab) {
		qtePlats=0;
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Plats();
			qtePlats++;
		}
	}

	public void initialiserCommande(Commande[] tab) {
		qteCommandes=0;
		erreur="";
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Commande();
			qteCommandes++;
		}
	}

	public double[] trouverPrix(NomClient[] tabClient, Plats[] tabPlat,
			Commande[] tabCommande) {
		double[] prix = new double[tabClient.length];
		
		for (int i = 0; i < qteClients; i++) {
			
			prix[i] = 0;

			// indice commande

			for (int j = 0; j < qteCommandes; j++) {

				if (tabClient[i].getNom().equals(tabCommande[j].getNom())) {
					
					// indice plats
					for (int x = 0; x < qtePlats; x++) {

						if (tabPlat[x].getPlat().equals(
								tabCommande[j].getPlat())) {

							prix[i] += (tabPlat[x].getPrix() * tabCommande[j]
									.getQuantite());

						}
					}
				}
			}

		}
		return prix;
	}

	public double calculerTPS(double prix) {

		final double TPS = 0.05;

		return prix * TPS;
	}

	public double calculerTVQ(double prix) {

		final double TVQ = 0.10;

		return prix * TVQ;
	}

	public void insererDonnes(String[] texte, NomClient[] tabClient,
			Plats[] tabPlat, Commande[] tabCommande) {
		Verification verif = new Verification();
		int indiceTexte = 0;
		int indiceTab = 0;
		String[] valeur;
		indiceTexte++;

		while (!texte[indiceTexte].equals("Plats :")) {
			valeur = texte[indiceTexte++].split(" ");
			if (verif.formatClientCorrect(valeur[0]) && verif.formatTableCorrect(valeur[1],NOMBRE_TABLE)) {
				tabClient[indiceTab].setNom(valeur[0]);
				tabClient[indiceTab++].setNumeroTable(Integer.parseInt(valeur[1]));
			} else if(!verif.formatClientCorrect(valeur[0])){
				erreur += "Le nom du client suivant " + valeur[0]
						+ " ne respecte pas le format demandé \n";
				qteClients--;
			}else{
				erreur += "La table suivante " + valeur[1]
						+ " ne respecte pas le format demandé \n";
				qteClients--;
			}
		}

		indiceTab = 0;
		indiceTexte++;

		while (!texte[indiceTexte].equals("Commandes :")) {

			valeur = texte[indiceTexte++].split(" ");
			if (verif.formatPlatCorrect(valeur[0])
					&& verif.prixPlatFonctionne(valeur[1])) {
				tabPlat[indiceTab].setPlat(valeur[0]);
				tabPlat[indiceTab++].setPrix(Double.parseDouble(valeur[1]));
			} else {
				if (!verif.formatPlatCorrect(valeur[0])) {
					erreur += "Le plat suivant " + valeur[0]
							+ " ne respecte pas le format demandé\n";
				} else {
					erreur += "Le prix " + valeur[1] + " du plat suivant "
							+ valeur[0] + " ne respecte pas le format demandé\n";
				}
				qtePlats--;
			}
		}

		indiceTab = 0;
		indiceTexte++;

		while (!texte[indiceTexte].equals("Fin")) {

			valeur = texte[indiceTexte].split(" ");

			if (valeur.length == 3) {

				if (verif.presenceClient(tabClient, valeur[0], qteClients)
						&& verif.presencePlat(tabPlat, valeur[1], qtePlats)
						&& verif.quantitePlatFonctionne(valeur[2])) {
					tabCommande[indiceTab].setNom(valeur[0]);
					tabCommande[indiceTab].setPlat(valeur[1]);
					tabCommande[indiceTab++].setQuantite(Integer
							.parseInt(valeur[2]));
				} else {
					if (!verif.presenceClient(tabClient, valeur[0], qteClients)) {
						erreur += "Le client suivant " + valeur[0]
								+ " de la commande " + texte[indiceTexte]
								+ " n'existe pas\n";
					}
					if (!verif.presencePlat(tabPlat, valeur[1], qtePlats)) {
						erreur += "Le plat suivant " + valeur[1]
								+ " de la commande " + texte[indiceTexte]
								+ " n'existe pas\n";
					}
					if (!verif.quantitePlatFonctionne(valeur[2])) {
						erreur += "La quantité " + valeur[2] + " de la commande "
								+ texte[indiceTexte] + " n'est pas valide\n";
					}
					qteCommandes--;

				}
			}
			indiceTexte++;
		}
	}

	public static void ecrireEcran(JTextArea zoneTexte, String string) {
		zoneTexte.setText(zoneTexte.getText() + "\n" + string);
	}
	
	public void afficherFacture(NomClient[] tabClient,Plats[] tabPlat,Commande[] tabCommande, JTextArea zoneTexte){

		DateFormat dff = new SimpleDateFormat("dd-MM-yy HH:mm");
		Date dateobj = new Date();

		double[] prixClient = new double[tabClient.length];

		// indice client

		ecrireEcran(zoneTexte, "Facture du: " + dff.format(dateobj));
		ecrireEcran(zoneTexte, erreur);

		prixClient = trouverPrix(tabClient, tabPlat, tabCommande);
		double[] prixTable = new double[NOMBRE_TABLE];
		
		for(int x=0;x<prixTable.length;x++){
			prixTable[x] = 0.00;
		}
		int table = 1;
		int clientCompteur=0;
		for (int i = 0; i < prixClient.length; i++) {
			
			if(table != tabClient[i].getNumeroTable()){
				if (prixTable[table] != 0.0) {
					if(clientCompteur >=3 || prixTable[table] >= 100){
						prixTable[table] *= 1.15;
					}
					prixTable[table] += calculerTPS(prixTable[table]) + calculerTVQ(prixTable[table]);
					ecrireEcran(zoneTexte, "Table numéro " + table +  " : "
							+ OutilsAffichage.formaterMonetaire(prixTable[table], 2));
			}
				table = tabClient[i].getNumeroTable();
			}
			if(prixClient[i] != 0.00){
			prixTable[table] += prixClient[i];	
			ecrireEcran(zoneTexte, tabClient[i].getNom());
			clientCompteur++;
			}
		}
			
	}

	public void trierClient(NomClient[] tabClient) {
		NomClient temp;

		for(int j=0; j<qteClients -1;j++){
		for(int i=0;i<qteClients-1;i++){
			if(tabClient[i].getNumeroTable()> tabClient[i+1].getNumeroTable()){
				temp = tabClient[i];
				tabClient[i] = tabClient[i+1];
				tabClient[i+1] = temp;
			}
		}
		}
		
	}

}
