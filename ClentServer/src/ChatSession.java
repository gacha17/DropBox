import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class ChatSession extends Thread
{
   private Socket client =null;;
   private int cid=0;
   public ChatSession(Socket client, int id)
   {
      this.client = client;
      this.cid = id;
     }
//new thread of handling clients
   public void run()
   {
      if (client != null)
      {

		try{
		String msg=null;
      	while((msg = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine()) != null)
      	{
       //print message from the particular client in the server.
      	  System.out.println("Received message from client id:"+ cid+": and Message is:"+ msg);

      	  // send this message to all other clients - get all clients connected.

      	  ArrayList<ChatSession> clients = ChatRoomBroadcaster.clients;

      	  for(int i=0;i<clients.size();i++)
      	  {
			  msg="RES"+msg;

			  ChatSession client = (ChatSession)clients.get(i);
			  //skipping the client which initiated the chat.
			  if(client.getID() != cid) 
			  {
				  //write to clients
				  PrintWriter writer =new PrintWriter(client.getSocket().getOutputStream());
				  writer.println(msg);
				  writer.flush();

		 	  }
		  }

      	 }
	 }catch (IOException ioex)
	 {
		 ioex.printStackTrace();
	 }
      }
   }


   public Socket getSocket()
   {
	   return client;
   }
   public int getID()
   {
	   return cid;
   }
}