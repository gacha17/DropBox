import java.nio.*;
import java.net.*;
import java.util.*;
import java.io.*;
//chat room server
public class ChatRoomBroadcaster
{
	static ArrayList<ChatSession> clients = new ArrayList<ChatSession>();

	public static void main(String args[])
	{
		System.out.println("Starting chat room");
		try{
		ServerSocket srs = new ServerSocket (5555);

		int count=1;
		//connect atmost 5 clients
		while (count <=5)
		{
			try{
			// blocking read for client to connect.
			System.out.println("Waiting for clients to connect");
			Socket client = srs.accept();
			System.out.println("Accepted connection from client:" + count);
			//invoke seperate session for the client
			ChatSession clientSession = new ChatSession (client, count);
			clients.add(clientSession);
			clientSession.start();
			// now messages are exchanged between these sessions
			count++;
			}catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		}
		 catch (IOException ioex)
		 {
			 ioex.printStackTrace();
			 
		 }
		System.out.println("Max clients reached, No more connections accepted but conservation can still continue in existing sessions");

	}

// Getter method to get the handle of all clients

  public ArrayList getClient()
  {
	  return clients;
  }
}