package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Client.Message;

public class ClientServiceThread extends Thread {
	
	Socket myClientSocket;
	int clientId =-1;
	boolean running = true;
	PrintWriter outStream;
	BufferedReader inStream;
	
	public ClientServiceThread(Socket s,int i) {
		myClientSocket = s;
		clientId =i;		
	}
	public void run()
	{
		System.out.println("Accepted Client : ID - " + clientId + " : Address - "
		        + myClientSocket.getInetAddress().getHostName());
		try
		{
		  outStream = new PrintWriter(myClientSocket.getOutputStream(), true); 
		    
		   inStream = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream()));
		    
		    ObjectInputStream in  = new ObjectInputStream(myClientSocket.getInputStream());
		    ObjectOutputStream out  = new ObjectOutputStream(myClientSocket.getOutputStream());
		    Message msgFromClient = null;
		    FileManager manageFile = new FileManager();
		   	   
		    while ((msgFromClient = (Message) in.readObject()) != null) { 
		         System.out.println ("Message from client: " + msgFromClient.getEventOccured());
		         manageFile.writeFile(msgFromClient.getFileData(),msgFromClient.getClientID()); 
		         out.writeObject(msgFromClient);
		    } 
		}catch(IOException ioe)
		{
			System.err.println("Error occured in I/O. Please recheck");
		}catch(ClassNotFoundException cnfe)
		{
			System.err.println("Error occured on the server");
		}
		finally
		{
			try{
		    outStream.close(); 
		    inStream.close(); 
		    myClientSocket.close(); 
			}catch(IOException ioe)
			{
				System.err.println("System error while closing the socket");
			}
		    
		}
		    //myServerSocket.close(); 
	}

}
