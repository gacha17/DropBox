package Client;

import java.io.File;
import java.io.IOException;

 
public class DeleteDirectory{
 
    public void deleteContents(String SRC_FOLDER){	
 
    	File directory = new File(SRC_FOLDER);
 
    	//make sure directory exists
    	if(!directory.exists()){
           System.out.println("Directory does not exist.");
           System.exit(0);
        }else{
           try{
               if(directory.list().length > 0){
            	   String files[] = directory.list();
            	   
            	   for (String temp : files) {
            	      //construct the file structure
            	      File fileDelete = new File(directory, temp);
            	      //recursive delete
            	     delete(fileDelete);
            	   }
               }
           }catch(IOException e){
               e.printStackTrace();
               System.exit(0);
           }
        }
    	System.out.println("Deleted contents successfully!");
    }
 
    /**
     * Deletes all contents and directory itself
     * @param file
     * @throws IOException
     */
    public void delete(File file) throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
    		   file.delete();
    		   //System.out.println("Directory is deleted : " + file.getAbsolutePath());
    		}else{
    		   //list all the directory contents
        	   String files[] = file.list();
 
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
        	      //recursive delete
        	     delete(fileDelete);
        	   }
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     //System.out.println("Directory is deleted : " + file.getAbsolutePath());
        	   }
    		}
    	}else{
    		//if file, then delete it
    		file.delete();
    		//System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
}
