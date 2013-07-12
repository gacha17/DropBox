package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer { 
	
	 public static void main(String[] args) throws IOException,ClassNotFoundException {
		 
	    ServerSocket myServerSocket = null; 
	    
	    /**
	     * initialize server socket
	     */
	    try { 
	         myServerSocket = new ServerSocket(5678); 
	    } catch (IOException e) { 
	         System.err.println("Unable to listen to port 5678! Exiting!!!"); 
	         System.exit(1);
	    } 
	int id=0;
	    Socket myClientSocket = null; 
	    System.out.println ("Waiting for client to connect....");
	    
	    /**
	     * initialize client socket to be accepted
	     */
	    try { 
	    while(true)
	      {
	           myClientSocket = myServerSocket.accept(); 
	           ClientServiceThread cst = new ClientServiceThread(myClientSocket,id++);
	           cst.start();
	       
	      }
	    } catch (IOException e) { 
	          System.err.println("Unable to accept client...."); 
	          System.exit(1); 
	      }finally
	      {
	    	  myServerSocket.close();
	      }
	   
	
	   
	   } 
} 