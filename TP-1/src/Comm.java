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



public class Comm {

	BufferedReader bread ;
	BufferedWriter bwrite;
	Socket socket;
	InetAddress localHost;
	
	public void startConnection(){
		
		try{
		
			localHost = InetAddress.getLocalHost();		
			try {	
				socket = new Socket(localHost, 10000);
				bread = new BufferedReader(new InputStreamReader( new BufferedInputStream(socket.getInputStream())));
				bwrite = new BufferedWriter(new OutputStreamWriter( new BufferedOutputStream(socket.getOutputStream())));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
	}
	
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
