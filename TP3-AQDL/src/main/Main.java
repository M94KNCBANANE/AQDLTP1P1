package main;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import outilsjava.OutilsFichier;

public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static NomClient[] tabClient;
	public static Plats[] tabPlat;
	public static Commande[] tabCommande;
	private static Refactoring ref = new Refactoring();
	String[] texte;
	
	private Listener listener = new Listener();
	private JButton lireFich;
	private JButton prodFact;
	private JTextField nomFich;
	private JPanel pan;
	JTextArea multiLignes = new JTextArea();
	JScrollPane zoneTexte;
	
	private JScrollPane ConstruireZoneTexte(JTextArea multiLignes) {
		multiLignes.setLineWrap(true);
		multiLignes.setWrapStyleWord(true);
		
		JScrollPane multilignesScrollPane = new JScrollPane(multiLignes);
        multilignesScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
        
		return multilignesScrollPane;
	}
	
	public Main() throws IOException {
		super("Logiciel de facture");
		setSize(600, 700);
		
		pan = new JPanel();
		
		nomFich = new JTextField("");
		nomFich.setColumns(10);
		lireFich = new JButton("Lire fichier de commande");
		prodFact = new JButton("Produire le fichier de facture");
		zoneTexte = ConstruireZoneTexte( multiLignes );
		lireFich.addActionListener(listener);
		prodFact.addActionListener(listener);
		
		pan.add(lireFich, BorderLayout.WEST);
		pan.add(nomFich, BorderLayout.WEST);
		pan.add(prodFact, BorderLayout.EAST);
		
		add(pan, BorderLayout.NORTH);
		add(zoneTexte);
		prodFact.setEnabled(false);
		multiLignes.setText("Pour produire une facture, vous devez d'abord charger un fichier de commande.");
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){ 
				
					int choix = JOptionPane.showConfirmDialog( null, "Voulez-vous vraiment quitter l'application?","Quitter", 
							JOptionPane.YES_NO_OPTION );
					
					if (choix == JOptionPane.YES_OPTION) {
						System.exit( 0 );
					}
				}
		});
	}
	
	
	
	public class Listener implements ActionListener {

		@Override
		public void actionPerformed( ActionEvent e ) {
			
			if (e.getSource() == lireFich) {
					try {
						texte = null;
						texte = Lecture.lectureFichier(nomFich.getText(), multiLignes);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(texte != null){
						
						multiLignes.setText("");
						tabClient = new NomClient[Integer.parseInt( texte[texte.length-3] )];
						ref.initialiserClient(tabClient);
						tabPlat = new Plats[Integer.parseInt( texte[texte.length-2] )];
						ref.initialiserPlats(tabPlat);
						tabCommande = new Commande[Integer.parseInt( texte[texte.length-1] )];
						ref.initialiserCommande(tabCommande);
						ref.insererDonnes(texte, tabClient, tabPlat, tabCommande);
						ref.trierClient(tabClient);
						ref.afficherFacture(tabClient, tabPlat, tabCommande, multiLignes);
						prodFact.setEnabled(true);
						
					} else {
						prodFact.setEnabled(false);
						multiLignes.setText("Le fichier n'existe pas, veuillez entrer un nom valide.");
					}
									
				} else if (e.getSource() == prodFact) {
					BufferedWriter ficEcriture;

					DateFormat df = new SimpleDateFormat("dd-MM-yy-HH.mm.ss");
					Date dateobj = new Date();
					String nomFichier = "Facture-du-" + df.format(dateobj) + ".txt";

					ficEcriture = OutilsFichier.ouvrirFicTexteEcriture(nomFichier);
					
					try {
							String[] lines = multiLignes.getText().split("\\n");
							for(int i = 0 ; i< lines.length; i++) {
					            ficEcriture.write(lines[i] + "\r\n");
						}
						
							Refactoring.ecrireEcran(multiLignes, "\n\nLe fichier de facture a bien été enregistré!");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					OutilsFichier.fermerFicTexteEcriture(ficEcriture, nomFichier);
				}
			}
		}
}
