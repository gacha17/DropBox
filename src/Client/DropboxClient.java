package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DropboxClient {
	public static String user;
  	public static String pass;
  	public static String DPdirectory=null;
  	private static boolean loggedIn = false;

    public static void main(String[] args) {
    	
        DropboxClient dbclient = new DropboxClient(); 	
      
      	
      	Properties prop = new Properties();
      	 
      	try {
                 //load a properties file
      		prop.load(new FileInputStream("client.properties"));
   
                 //get the property value and print it out
     if(prop.getProperty("username")!=null && prop.getProperty("password")!=null && prop.getProperty("folderLocation")!=null)
     {
          	user = prop.getProperty("username");  
      		pass = prop.getProperty("password");
      		DPdirectory = prop.getProperty("folderLocation");
      		System.out.println("Drop box folder location"+prop.getProperty("folderLocation"));
             loggedIn = dbclient.authenticateUser(prop.getProperty("username"),prop.getProperty("password"));
     }      
             else{
          	    System.out.println("Welcome to Drop box");
          	    System.out.println("Please enter your username");
          	    Scanner sc = new Scanner(System.in);
          	    user = sc.nextLine();
          	    System.out.println("Please enter your password");
          	    pass = sc.nextLine();
          	    System.out.println("Enter the location of your Drop box directory\n" +
          	    		"Please note that the directory once specified cannot be changed later");
          	    DPdirectory = sc.nextLine();
          	    if(DPdirectory.contains("/"))
          	    {
          	    DPdirectory = DPdirectory.replaceAll("/", "\\\\");
          	    }
          	    sc.close();
          	    loggedIn = dbclient.authenticateUser(user,pass);
          	if(loggedIn)
              	{
          	    prop.setProperty("username", user);
          	    System.out.println("Accepted username"+prop.getProperty("username"));
          		prop.setProperty("password", pass);
          		System.out.println("Accepted password"+prop.getProperty("password"));
          		prop.setProperty("folderLocation", DPdirectory);
          		System.out.println("Accepted folder"+prop.getProperty("folderLocation"));
       
          		//save properties to project root folder
          		prop.store(new FileOutputStream("client.properties"), null);
          	    }
          	   
             }
   
      	} 
      	catch(FileNotFoundException e){
      	      System.err.println("client.properties could not be found on working directory");
      	    }
      	catch (IOException ex) {
      		ex.printStackTrace();
          }

      	if(loggedIn && DPdirectory!=null)
      		{    
        //define a folder root
      		
        Path myDir = Paths.get(DPdirectory);       
        MySocket mySocket = new MySocket();
        
        try {
        	mySocket.openConnetion();
        	boolean isEventOccured = false;
        	String eventType = "";
        	FileManager manageFile = new FileManager();
        	
        	WatchKey watckKey;
        	WatchService watcher = myDir.getFileSystem().newWatchService();
        	watckKey = myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
        				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            
        	while(true) {
	        	   isEventOccured = false;
		           watckKey = watcher.take();
		
		           List<WatchEvent<?>> events = watckKey.pollEvents();
		           
		           for (WatchEvent<?> event : events) {
		                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
		                	isEventOccured = true;
		                    eventType = "Created: " + event.context().toString();
		                }else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
		                	isEventOccured = true;
		                	eventType = "Delete: " + event.context().toString();
		                }else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
		                	isEventOccured = true;
		                	eventType = "Modify: " + event.context().toString();
		                }
		           } 
		           if(isEventOccured){
		        	   Message myMsg = new Message(user, "login", "root", new Date(), "firstTime");
		        	   //send message to server
		        	   myMsg.setEventOccured(eventType);
		        	   myMsg.setFileData(manageFile.getFileBytes(DPdirectory,user));
		        	   mySocket.sendMessage(myMsg);
		           }
		           
		           boolean valid = watckKey.reset();
			   	    if (!valid) {
			   	    	mySocket.closeConnection();
			   	        break;
			   	    }
        	}
           
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
      		}else{
      			System.err.println("The folder has a problem or Authentication failed.. Please contact your system adminstrator");
      		}
    }
    
    private boolean authenticateUser(String user,String pass)
    {
    	return true;
    }
}