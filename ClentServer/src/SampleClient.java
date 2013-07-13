import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class SampleClient
{
/**initialize main method and get the InetAddress and port information*/
   public static void main(String args[])throws Exception
   {
      String hostname="localhost";
      int port =5555;

      System.out.println("About to connect to:"+hostname +":at port:"+port);

     InetSocketAddress socAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);

	try{

      Socket clientSoc= new Socket(socAddress.getAddress(),port);

      if(clientSoc!=null) // implies connection is established
      {
           //get output stream 
			PrintWriter writer =new PrintWriter(clientSoc.getOutputStream());
        	SocketChannel socChannel = clientSoc.getChannel();
			String msg=null;
			while((msg= new BufferedReader(new InputStreamReader(System.in)).readLine()) != null)
			{

			   if (msg.startsWith("RES"))
			   {
				 //do nothing
			   }
			   else{
			   System.out.println("sent msg to Server:"+msg);
			   writer.println(msg);
			   writer.flush();

			 }
			}

      	}
		}
		catch(IOException ioex)
		{
			ioex.printStackTrace();
		}

   }
}