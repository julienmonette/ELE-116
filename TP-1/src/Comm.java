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
Nom du fichier : Comm.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/


import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;


/** Permet de se connecter et d'envoyer des commandes au serveur
 * 
 * @author Colin Reid-Lapierre, Julien Monette
 *
 */
public class Comm {

	BufferedReader bread ;
	BufferedWriter bwrite;
	Socket socket;
	InetAddress localHost;
	
	/**
	 * Établie la connect avec le serveur
	 * 
	 * @param portNumber     Numero de port pour la connection
	 * @throws IOException	 Renvoie une exception dans le cas où le connection ne peut pas être établie
	 */
	public void startConnection (int portNumber) throws IOException {
		
		try{	
			localHost = InetAddress.getLocalHost();		
			try {	
				socket = new Socket(localHost, portNumber);
				bread = new BufferedReader(new InputStreamReader( new BufferedInputStream(socket.getInputStream())));
				bwrite = new BufferedWriter(new OutputStreamWriter( new BufferedOutputStream(socket.getOutputStream())));
			}
			catch (IOException e) {
				throw e;
			}
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Envoie une connmande au serveur
	 * 
	 * @param string Command à envoyer au serveur
	 */
	public void sendString(String string) {
				
		try {
			bwrite.write(string);
			bwrite.newLine();
			bwrite.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne la String envoyée par ServeurForme
	 * 
	 * @return String envoyée par ServeurForme
	 */
	public String getString() {
		
		String string = "";
		try {		
			bread.readLine();
		    string = bread.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			
		return string;
	}
	
	/**
	 * Envoie "END" au serveur pour terminer la connection 
	 */
	public void endConnection() {
					
		try{
			bwrite.write("END");
			bwrite.newLine();
			bwrite.flush();
			socket.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
