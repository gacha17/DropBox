package Client;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileManager {
	
	private String sourceFilePath = "MyDropBox1.zip";
	private byte[] fileBytes;

	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public byte[] getFileBytes(String pathtoZip,String user){
		AppZip appZip = new AppZip();
       	appZip.zipFolder(pathtoZip);
       	readFileBytes();
       
       	return fileBytes;
	}

	public void writeFile(byte[] fileByteToWrite){
		writeByteToFile(fileByteToWrite); //create zip file
		//DeleteDirectory delDir = new DeleteDirectory();
		//delDir.deleteContents("C:/");
		UnZip unZipFile = new UnZip();//unzip file to respective folder
		unZipFile.unZipFile();
		deleteFile();
	}
	
	private void readFileBytes(){
		try {
			
        File file = new File(sourceFilePath);
   
        if (file.isFile()) {
            
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read,
                        fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }
                diStream.close();
            
        } else {
            System.out.println("path specified is not pointing to a file");
        }
		} catch (IOException e) {
          System.err.println("file compression failed with the message"+e.getMessage());
        }
	}
	
	public void deleteFile(){
		try{
    		File file = new File(sourceFilePath);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
public void writeByteToFile(byte[] fileByteToWrite){
		
		try{
			File dstFile = new File(sourceFilePath);
	        FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
	        fileOutputStream.write(fileByteToWrite);
	        fileOutputStream.flush();
	        fileOutputStream.close();
	        System.out.println("Output file : " + sourceFilePath + " is successfully saved ");
		}catch(Exception exe){
			exe.printStackTrace();
		}
		
	}
}
