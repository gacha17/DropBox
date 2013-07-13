import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer { 
	  private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	 public static void main(String[] args) throws IOException,ClassNotFoundException {
		 
	    ServerSocket myServerSocket = null; 
	    
	    /**
	     * initialize server socket
	     */
	    try { 
	         myServerSocket = new ServerSocket(11223); 
	    } catch (IOException e) { 
	         System.err.println("Unable to listen to port 112233! Exiting!!!"); 
	         System.exit(1); 
	    } 
	
	    Socket myClientSocket = null; 
	    System.out.println ("Waiting for client to connect....");
	    
	    /**
	     * initialize client socket to be accepted
	     */
	    try { 
	         myClientSocket = myServerSocket.accept(); 
	    } catch (IOException e) { 
	         System.err.println("Unable to accept client...."); 
	         System.exit(1); 
	    } 
	
	    System.out.println ("Client Accepted... \nAwaiting input from client...");
	
	    PrintWriter outStream = new PrintWriter(myClientSocket.getOutputStream(), true); 
	    
	    BufferedReader inStream = new BufferedReader( new InputStreamReader( myClientSocket.getInputStream()));
	    
	    ObjectInputStream in  = new ObjectInputStream(myClientSocket.getInputStream());
	    ObjectOutputStream out  = new ObjectOutputStream(myClientSocket.getOutputStream());
	    Message msgFromClient;
	    threadPool.execute(new Runnable(){
        	public void run(){
        		
        	}
        });
	    
	    /**
	     * accepting input from client
	     */
	    while ((msgFromClient = (Message) in.readObject()) != null) { 
	         System.out.println ("Message from client: " + msgFromClient.eventOccured); 
	         out.writeObject(msgFromClient);
	         System.out.println("delete meeigigy");
	    } 
	    
	    /**
	     * close socket and streams
	     */
	    outStream.close(); 
	    inStream.close(); 
	    myClientSocket.close(); 
	    myServerSocket.close(); 
	   } 
} 