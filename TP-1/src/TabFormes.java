/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					XXXXXXXXX					
Professeur : Rita Noumeir
Nom du fichier : TabFormes.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/

import java.awt.Graphics;
import java.awt.geom.RectangularShape;
import java.util.StringTokenizer;


/**
 * Gère une liste de formes et permet de les afficher.
 * 
 * @author Colin Reid-Lapierre, Julien Monette
 *
 */
public class TabFormes {
	
	static private final int TABLE_LENGTH = 10;
	
	private int size = 0;
	private int i=0; // Position de la prochaine forme à ajouter au tableau
	private Forme  tab[] = new Forme[TABLE_LENGTH];
	
	
	/**
	 * Ajoute une forme au tableau à partir d'une string provenant de ServeurForme
	 * @param string : String recu de ServeurForme
	 */
	public void add(String string) {
		int x1=0;
		int x2=0;
		int y1=0;
		int y2=0;
		
		Forme forme;
		String seq;
		String typeForme;
		StringTokenizer line = new StringTokenizer(string);	
		
		seq = line.nextToken();
		typeForme = line.nextToken();
							
		// On reccueil les informations de la forme à partir du string de ServeurForme
		x1 = Integer.parseInt(line.nextToken());
		x2 = Integer.parseInt(line.nextToken());
		y1 = Integer.parseInt(line.nextToken());
		if(!typeForme.equals("<CERCLE>")) {	y2 = Integer.parseInt(line.nextToken());}		
				
		
		if(typeForme.equals("<CARRE>")) {
			forme = new Carre(x1,x2,y1,y2);
		}
		else if (typeForme.equals("<CERCLE>")) {
			forme = new Cercle(x1,x2,y1);
		}
		else if (typeForme.equals("<RECTANGLE>")) {
			forme = new Rectangle(x1,x2,y1,y2);
		}
		else if (typeForme.equals("<LIGNE>")) {
			forme = new Ligne(x1,x2,y1,y2);
		}
		else {
			forme = new Ovale(x1,x2,y1,y2);
		}					
		
		add(forme);
	}
	
	/**
	 * Ajout une forme au tableau de forme
	 * @param forme : Forme à ajouter
	 */
	private void add(Forme forme) {
		tab[i] = forme;
		i++;
		if(i == TABLE_LENGTH) {
			i = 0;
		}
		
		if(size < TABLE_LENGTH) {
			size++;
		}		
	}
	
	/**
	 * Retourne la forme du tableau de forme se trouvant à l'indice i
	 * @param i
	 * @return
	 */
	public Forme getForme(int i) {
		return this.tab[i];
	}
	
	/**
	 * Retourne le nombre de forme du tableau de forme
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Affiche toutes les formes du tableau de formes.
	 * @param g
	 */
	public void dessinerTabForme(Graphics g) {
		
		int j;
		int IndexFormeAffichee = i;
		
		/*
		 * Ici, index forme à ajouter sert à gèrer l'ordre dans lequel les formes seront affichées, 
		 * laquelle sera en premier plan. On désire que la dernière forme reçu soit celle en premier plan
		 * par rapport aux autre.
		 */
		for( j = 0; j < size; j++) {
			
			if( IndexFormeAffichee == size) {
				IndexFormeAffichee = 0;
			}
			tab[IndexFormeAffichee].dessine(g);
			IndexFormeAffichee++;
		}
	}

}
