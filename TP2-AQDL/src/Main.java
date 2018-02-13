
public class Main {
	
	public String erreur ="Le fichier ne respecta pas le format demand� !";
	public NomClient[] tabClient;
	public Plats[] tabPlat;
	public Commande[] tabCommande;
	
	
	public void main(String[] args) {
		boolean test;
		String[] texte;
		texte = new Lecture();
		tabClient = new NomClient[Integer.parseInt(texte[texte.length-3])];
		tabPlat = new Plats[Integer.parseInt(texte[texte.length-2])];
		tabCommande = new Commande[Integer.parseInt(texte[texte.length-1])];
		test=Verifier(texte);
		if(test){
			afficherFacture();
		}else{
			System.out.println(erreur);
		}
		
		
	}
	
	private void afficherFacture() {
		double[] prix = new double[tabClient.length];
		//indice client
		for(int i=0;i<tabClient.length;i++){
			prix[i]=0;
			//indice commande
			for(int j=0;j<tabCommande.length;j++){
			if(tabClient[i].getNom() == tabCommande[j].getNom()){
				//indice plats
				for(int x = 0; x<tabPlat.length;x++){
					if(tabPlat[x].getPlat() == tabCommande[j].getPlat()){
						prix[i]= (tabPlat[x].getPrix() * tabCommande[j].getQuantite());
					}
				}
				break;
			}
			}
			System.out.println(tabClient[i].getNom() +" : "+prix[i]);
		}
		
		
	}

	public boolean Verifier(String[] texte){
	int indiceTexte=0;
	int indiceTab=0;
	String[] plat;
	
	boolean test=true;
		if(texte[indiceTexte++] != "Clients :"){
			test=false;	
		}else{
			while(texte[indiceTexte]!= "Plats :"){
			tabClient[indiceTab++].setNom(texte[indiceTexte++]);		
	}
		indiceTab = 0;
		while(texte[indiceTexte]!= "Commandes :"){ //Verifier le String
			plat = texte[indiceTexte++].split(" ");
			tabPlat[indiceTab].setPlat(plat[0]);
			tabPlat[indiceTab].setPrix(Double.parseDouble(plat[1]));
	}
		indiceTab = 0;
		while(texte[indiceTexte]!= "Fin"){
			plat=texte[indiceTexte++].split(" ");
			if(plat.length!=3){
			for(int i=0;i<tabClient.length;i++ ){
				if(plat[0] == tabClient[i].getNom()){
					for(int j=0;j<tabPlat.length;i++ ){
						if(plat[1] == tabPlat[j].getPlat()){
						
							try{
							tabCommande[indiceTab].setNom(plat[0]);
							tabCommande[indiceTab].setPlat(plat[1]);
							tabCommande[indiceTab++].setQuantite(Integer.parseInt(plat[2]));
							test=true;
							break;
							}catch(Exception e){
							System.out.println("Pas une valeur Int possible");
							break;
							}
						}else{
							test=false;
						}
						
					}
					break;
					}else{
						test=false;
					}
					
				}
			}else{
				test=false;
			}
			}
		}
		return test;
}
	}
