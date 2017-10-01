/******************************************************
 Cours :             LOGnnn
 Session :           Saison (�t�, automne, hiver) 200X
 Groupe :            n
 Projet :            Laboratoire #n
 �tudiant(e)(s) :    Marcel Marceau
                     Edith Piaf
 Code(s) perm. :     MARM987341987
                     PIAE324398724
 Professeur :        Groucho Marx
 Date cr��e :        2002-05-28
 Date dern. modif. : 2005-05-01
 
*******************************************************
 Historique des modifications
*******************************************************
  2002-05-28	Cris Fuhrman : Version initiale
  
  2004-03-07	Cris Fuhrman : Int�gration de SwingWorker 
                requierant la classe additionnelle 
                SwingWorker.java, utilisation des variables 
                constantes, formatage de code source, 
                organisation des imports, etc.

  2005-05-01	Cris Fuhrman : Int�gration de ApplicationSupport
  				requierant la classe additionnelle
  				ApplicationSupport.java et les fichiers
  				prefs.properties, app_xx.properties (o� xx est le
  				code de la langue, p. ex. fr = fran�ais, en = anglais).
  				Suppression de l'interface Shape.

 La distribution originale se trouve � 
 https://cours.ele.etsmtl.ca/academique/log120/notesdecours/exemples/lab/lab1/SqueletteSwingApplication.zip
********************************************************/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 * <code>SqueletteSwingApplication</code> est un exemple d'une
 * application en Java qui fournit une interface Swing, avec un simple
 * menu et un dessin.
 *
 * <h4>References</h4> 
 * <ul> 
 *
 * <li>C. Fuhrman, &quot;Notes de cours de LOG120,&quot; &Eacute;cole
 * de technologie sup&eacute;rieure, Montr&eacute;al, Qu&eacute;bec,
 * Canada, 2002
 *
 * <li>Xemacs (for generation of the initial template), <a target="_top" 
 * href="http://www.xemacs.org">www.xemacs.org</a>, 2002 
 *
 * <li><a target="_top" 
 * href="http://java.sun.com/docs/books/tutorial/uiswing/painting/overview.html">Overview
 * of Custom Painting</a>, une partie du tutoriel Java de Sun, 2002.
 *
 * <li>Java Software, <a target="_top" 
 * href="http://java.sun.com/j2se/javadoc/writingdoccomments/index.html">&quot;How
 * to Write Doc Comments for the Javadoc<sup>TM</sup> Tool,&quot;</a>
 * 2002
 *
 * </ul>
 *
 * Distribution originale &agrave; partir du 
 * <a target="_top" href="https://cours.ele.etsmtl.ca/academique/log120/">site Web</a>
 * du cours LOG120.
 * 
 * Created: Tue May 28 11:31:18 2002
 *
 * @author <a href="mailto:christopher.fuhrman@etsmtl.ca">Christopher Fuhrman</a>
 *
 * @version 1.1
 * 
 * 
 */

public class SqueletteSwingApplication extends JFrame implements ActionListener
{

	/**
	 * <code>theColor</code> est un exemple d'une variable dans la
	 * portee de la classe principale qui sera egalement visible a
	 * partir de la classe CustomCanvas (qui est une "inner" classe)
	 * plus bas.
	 *
	 */
	private Color theColor = new Color(0x00FF00); // 0xRRVVBB, rouge vert bleu

	/*
	 * Il vaut mieux d�clarer les variables constantes (static final) pour les
	 * cha�nes de caract�re ou les valeurs importantes. Le code est plus lisible
	 * puisqu'on comprend le sens de l'information. Le code est plus facile � 
	 * maintenir : pour effectuer un changement, l'information est centralis�e.
	 * 
	 * Voir les fichiers prefs.properties et app_xx.properties (o� xx d�finit les
	 * cha�nes pour chaque langue)
	 */

	private static final String 
	    TITRE_APPLICATION = "app.frame.title",
		MENU_FICHIER_TITRE = "app.frame.menus.file.title",
		MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
		MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
		MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
		MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
		MENU_AIDE_TITRE = "app.frame.menus.help.title",
		MENU_AIDE_PROPOS = "app.frame.menus.help.about";

	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final int MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;

	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int MENU_DESSIN_DEMARRER_TOUCHE_MASK =	ActionEvent.CTRL_MASK;

	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC = KeyEvent.VK_A;
	private static final int MENU_DESSIN_ARRETER_TOUCHE_MASK = ActionEvent.CTRL_MASK;

	private static final String 
		MESSAGE_DIALOGUE_NO_DE_FORMES = "app.frame.dialog.shapeCount",
		MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";

	private static final int 
	    NOMBRE_DE_FORMES = 150,
		DELAI_ENTRE_FORMES_MSEC = 1000,
		DELAI_QUITTER_MSEC = 200;

	private static final int 
	    CANEVAS_LARGEUR = 500,
		CANEVAS_HAUTEUR = 500,
		MARGE_H = 50,
		MARGE_V = 60,
		FORME_MAX_LARGEUR = 200,
		FORME_MAX_HAUTEUR = 200;

	/*
	 * Attribut pour le nombre de formes � dessiner de suite, initisialis� � la 
	 * valeur constante, plut�t qu'un chiffre.
	 */
	private int nombreDeFormes = NOMBRE_DE_FORMES;

	/*
	 * Attribut qui repr�sente une seule forme
	 */
	private Ellipse2D.Double forme;

	/*
	 * Ces attributs sont utilis�s pour g�rer l'�tat des articles de menu
	 * qui permettent de d�marrer et d'arreter un SwingWorker
	 */
	private boolean workerActive;
	private JMenuItem demarrerMenuItem;
	private JMenuItem arreterMenuItem;

	/**
	 * <code>CustomCanvas</code> est une "inner" classe qui permet de dessiner
	 * des objets dans l'interface Swing. 
	 *
	 * On utilise une inner classe pour faciliter la visibilites des
	 * variables dans la classe exterieure.
	 *
	 * Voir 
	 * <a href="http://java.sun.com/docs/books/tutorial/uiswing/painting/overview.html">Overview of Custom Painting</a>, 
	 * une partie du tutoriel Java de Sun.
	 *
	 */
	class CustomCanvas extends JPanel
	{

		public CustomCanvas()
		{
			setSize(getPreferredSize());
			setMinimumSize(getPreferredSize());
			CustomCanvas.this.setBackground(Color.white);
		}

		/**
		 * <code>getPreferredSize</code> retourne la dimension du JPanel
		 *
		 * @return a <code>Dimension</code> value
		 */
		public Dimension getPreferredSize()
		{
			return new Dimension(CANEVAS_LARGEUR, CANEVAS_HAUTEUR);
		}

		/**
		 * <code>paintComponent</code> contient le code pour le dessin
		 * "fait sur commande"
		 *
		 * @param g a <code>Graphics</code> value
		 */
		public void paintComponent(Graphics g)
		{
			/* dessiner le fonds (background) -- obligatoire */
			super.paintComponent(g);

			/*
			 * Si la forme (attribut de la classe principale ici) n'est pas 
			 * nulle, on la dessine
			 */
			if (forme != null)
			{
				// faire un cast (transtypage) en Graphics2D depour avoir plus de fonctionnalit�
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
				// theColor = une variable de la classe principale
				g2d.setColor(theColor);
				//g2d.translate(10, 10);
				g2d.draw(forme);
				g2d.fill(forme);
			}
		}
	}

	/**
	 * Constructeur de la classe <code>SqueletteSwingApplication</code>.
	 * Cette methode cree une nouvelle instance (nouvel objet) de la
	 * classe.
	 */
	public SqueletteSwingApplication()
	{
		/*
		 * Creer un objet CustomCanvas (JPanel) et mettre un scrollpane autour,
		 * puis placer le tout dans le contenu du JFrame (SqueletteSwingApplication)
		 */
		getContentPane().add(new JScrollPane(new CustomCanvas()));
	}

	/**
	 * Describe <code>main</code> method here.
	 *
	 * @param args a <code>String[]</code> value
	 */
	public static void main(String[] args)
	{
		SqueletteSwingApplication frame = new SqueletteSwingApplication();

		frame.makeFileMenu();
		frame.makeDrawMenu();
		frame.makeHelpMenu();

		/* mettre � jour les articles dans le menu */
		frame.updateMenus();

		ApplicationSupport.launch(
			frame,
			ApplicationSupport.getResource("app.frame.title"),
			0,
			0,
			CANEVAS_LARGEUR + MARGE_H,
			CANEVAS_HAUTEUR + MARGE_V);

		// pour centrer la fen�tre ?
		frame.setLocationRelativeTo(null);

		/* 
		 * Si jamais on en a besoin, on peut ajouter un listener pour recevoir 
		 * un evenement gener� lorsque le JFrame est ferme par l'usager ou le syst�me
		 */
		// frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// frame.addWindowListener(new WindowAdapter()
		// {
		// public void windowClosing(WindowEvent e)
		// {
		// 		// faire du nettoyage, etc., si n�cessaire...
		// 		
		// 		// quitter 
		// 		System.exit(0);
		// 	}
		// 	public void windowOpened(WindowEvent e)
		// 	{
		// 	}
		// });

	}

	/*
	 * Cr�er des menus avec ApplicationSupport.addMenu()
	 */

	// menu fichier
	private JMenu makeFileMenu()
	{
		JMenu fileMenu =
			ApplicationSupport.addMenu(
				this,
				MENU_FICHIER_TITRE,
				new String[] { MENU_FICHIER_QUITTER });

		// ajouter un listener pour quand l'usager termine l'application
		fileMenu.getItem(0).addActionListener(this);
		// touche raccourci
		fileMenu.getItem(0).setAccelerator(
			KeyStroke.getKeyStroke(
				MENU_FICHIER_QUITTER_TOUCHE_RACC,
				MENU_FICHIER_QUITTER_TOUCHE_MASK));

		return fileMenu;
	}

	// menu dessiner
	private JMenu makeDrawMenu()
	{
		JMenu drawMenu =
			ApplicationSupport.addMenu(
				this,
				MENU_DESSIN_TITRE,
				new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });

		// ajouter un listener pour d�marrer (item 0)
		this.demarrerMenuItem = drawMenu.getItem(0);
		this.demarrerMenuItem.addActionListener(this);
		// touche raccourci
		this.demarrerMenuItem.setAccelerator(
			KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		// ajouter un listener pour arr�ter (item 1)
		this.arreterMenuItem = drawMenu.getItem(1);
		this.arreterMenuItem.addActionListener(this);
		// touche raccourci
		this.arreterMenuItem.setAccelerator(
			KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));

		return drawMenu;
	}

	// menu aide
	private JMenu makeHelpMenu()
	{
		JMenu helpMenu =
			ApplicationSupport.addMenu(
				this,
				MENU_AIDE_TITRE,
				new String[] { MENU_AIDE_PROPOS });

		// ajouter un listener pour item 0 (� propos de)
		helpMenu.getItem(0).addActionListener(this);
		// aucune touche raccourci

		return helpMenu;
	}

	/*
	 * Cette m�thode va activer ou d�sactiver les articles de menu selon l'�tat
	 */
	private void updateMenus()
	{
		demarrerMenuItem.setEnabled(!this.workerActive);
		arreterMenuItem.setEnabled(this.workerActive);
	}

	/* 
	 * Toutes les actions des menus sont effectu�es ici, gr�ce aux 
	 * EventListeners
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0)
	{
		JMenuItem source = (JMenuItem) (arg0.getSource());
		// enlever les commentaires ci-dessous pour voir la dynamique de cette m�thode
		//		String s =
		//			"Action event detected."
		//				+ "\n"
		//				+ "    Event source: "
		//				+ source.getText();
		//		System.out.print(s + "\n");

		/*
		 * Puisque tous les articles de menu sont dirig�s vers cette m�me m�thode,
		 * il faut identifier la source de l'ActionEvent, puis on peut effectuer 
		 * l'action selon cette source.
		 */

		// ici, on traite l'article "D�marrer"
		if (source
			.getText()
			.equals(ApplicationSupport.getResource(MENU_DESSIN_DEMARRER))
			&& !this.workerActive)
		{
			/*
			 * Demander � l'usager combien de fois il veut que la forme change
			 */
			JOptionPane.setDefaultLocale(ApplicationSupport.getLocale());
			String stringTemp =
				JOptionPane.showInputDialog(
					ApplicationSupport.getResource(
						MESSAGE_DIALOGUE_NO_DE_FORMES),
					Integer.toString(NOMBRE_DE_FORMES));
			if (stringTemp!= null) {
				this.nombreDeFormes = Integer.parseInt(stringTemp); 
	
				/*
				 * On "dessine" ensuite les formes. C'est une activit� passive 
				 * plut�t qu'active, car il y a une forme definie dans le programme, mais
				 * elle va �voluer dans le temps, en se dessinant avec chaque changement.
				 * Ainsi, c'est une sorte d'animation qui prendra un certain temps.
				 * 
				 * Avec Java Swing, le Thread qui traite les
				 * appels � la m�thode actionPerformed est le m�me qui r�pond � tous les
				 * �venements de la GUI. Si on lui donnait � faire ensuite un traitement
				 * long (l'animation), il ne pourrait plus r�pondre aux �venements. 
				 * Pour �viter ce probl�me, on d�l�gue le travail long � un thread 
				 * special selon une strat�gie propos�e par Sun. C'est le SwingWorker.
				 * Pour plus d'informations sur cette approche, voir
				 * http://java.sun.com/products/jfc/tsc/articles/threads/threads2.html
				 * ou chercher "SwingWorker" dans le tutorial Java de Sun.
				 */
				final SwingWorker worker = new SwingWorker()
				{
					public Object construct()
					{
						//...code that might take a while to execute is here...
		                dessinerFormes();
						workerActive = false;
						updateMenus();
						return new Integer(0);
					}
				};
				worker.start(); //required for SwingWorker 3
				this.workerActive = true;
			}
		} else
			/*
			 * Traiter l'article de menu qui arr�te le SwingWorker
			 * On met � false la valeur de l'attribut "workerActive" car la m�thode 
			 * dessinerFormes() va le tester dans sa boucle 
			 */
			if (source
				.getText()
				.equals(ApplicationSupport.getResource(MENU_DESSIN_ARRETER))
				&& this.workerActive)
			{
				this.workerActive = false;
			} else
				/*
				 * Traiter l'article "A propos de ..." ici
				 */
				if (source
					.getText()
					.equals(ApplicationSupport.getResource(MENU_AIDE_PROPOS)))
				{
					JOptionPane.showMessageDialog(
						this,
						ApplicationSupport.getResource(
							MESSAGE_DIALOGUE_A_PROPOS),
						ApplicationSupport.getResource(MENU_AIDE_PROPOS),
						JOptionPane.INFORMATION_MESSAGE);
				} else
					/*
					 * Traiter l'article "Quitter" ici
					 * On devrait arr�ter le SwingWorker par principe, s'il est activ�
					 */
					if (source
						.getText()
						.equals(
							ApplicationSupport.getResource(
								MENU_FICHIER_QUITTER)))
					{
						if (this.workerActive)
						{
							this.workerActive = false;
							try
							{
								// pause courte pour permettre le worker de terminer
								Thread.sleep(DELAI_QUITTER_MSEC);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
						}
						System.exit(0);
					}

		updateMenus(); // changer l'�tat des menus selon les fanions

	}

	/*
	 * Cette m�thode fait varier la forme � dessiner. D'abord l'objet qui
	 * repr�sente la forme est chang�e, puis on force le syst�me � la redessiner, 
	 * puis on attend un court d�lai.
	 * C'est ici o� la logique de l'animation est centr�e. 
	 * Le tout est r�p�t� un certain nombre de fois.
	 */
	protected void dessinerFormes()
	{
		// note: on teste �galement l'attribut "workerActive" afin d'arreter si jamais
		for (int i = 0; i < this.nombreDeFormes && this.workerActive; i++)
		{
			this.forme =
				new Ellipse2D.Double(
					Math.random() * CANEVAS_LARGEUR,
					Math.random() * CANEVAS_HAUTEUR,
					Math.random() * FORME_MAX_LARGEUR,
					Math.random() * FORME_MAX_HAUTEUR);
			/* 
			 * L'instruction suivante demande au syst�me de redessiner le JFrame
			 * (notre SqueletteSwingApplication). Puisqu'elle est compos�e d'un
			 * JPanel (notre CustomCanvas, la classe interne), la m�thode 
			 * "paintComponent()" de ce dernier sera appel�e par le syst�me. 
			 * � l'int�rieur de paintComponent, l'objet "forme" sera utilis� 
			 * pour faire le dessin.
			 * Le dessin se fait d'une mani�re indirecte -- ceci est une 
			 * caract�ristique de pratiquement tout syst�me avec les fen�tres.
			 */
			this.repaint();
			try
			{
				// pause de N millisecondes
				Thread.sleep(DELAI_ENTRE_FORMES_MSEC);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

} // SqueletteSwingApplication
