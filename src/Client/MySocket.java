package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.Message;

public class MySocket {
	
		String myServer = new String ("localhost");
	
	    Socket mySocket = null;
	    ObjectOutputStream outStream = null;
	    ObjectInputStream inStream = null;
	    Message msgFromServer;
	    FileManager fileManager = new FileManager();
	    
	public void openConnetion(){
		try {
			System.out.println ("Connecting to dropbox server : " +myServer + ". Port : 5678.");
	            mySocket = new Socket(myServer, 5678);
	            outStream = new ObjectOutputStream(mySocket.getOutputStream());
	            inStream  = new ObjectInputStream(mySocket.getInputStream());
		} catch (UnknownHostException e) {
	            System.err.println("Unknown server: " + myServer);
	            System.exit(1);
	    } catch (IOException e) {
	            System.err.println("I/O exception to the server: " + myServer);
	            System.exit(1);
	    }
	}
	    
	
    public void sendMessage(Message msgToBeSent) throws IOException,ClassNotFoundException {
    	//FileManager fm = new FileManager();
    	outStream.writeObject(msgToBeSent);
    	msgFromServer = (Message) inStream.readObject();
    	System.out.println("Message from server: "+msgFromServer.getClientID()+" - "+msgFromServer.getEventOccured());
    	// fm.deleteFile();
    	if(!msgFromServer.getEventOccured().equalsIgnoreCase("AckFromServer")){
    		System.out.println("inside if. going rogue");
    		fileManager.writeFile(msgFromServer.getFileData());
    	}
    }
    
    public void closeConnection() throws IOException{
    	outStream.close();
    	mySocket.close();
    }
}
