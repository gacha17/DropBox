package Server;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileManager {
	
	private String sourceFilePath = "MyDropBox1.zip";
	private byte[] fileBytes;

	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public byte[] getFileBytes(){
		AppZip appZip = new AppZip();
       	appZip.zipFolder();
       	readFileBytes();
      	//deleteFile();
       	return fileBytes;
	}
	
	public void writeFile(byte[] fileByteToWrite,String clientID){
		writeByteToFile(fileByteToWrite); //create zip file
		UnZip unZipFile = new UnZip();//unzip file to respective folder
		unZipFile.unZipFile(sourceFilePath,clientID);
		deleteFile();
	}

	private void readFileBytes(){
        File file = new File(sourceFilePath);
        if (file.isFile()) {
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("path specified is not pointing to a file");
        }
	}
	//saves the zip file from  client
	public void writeByteToFile(byte[] fileByteToWrite){
		
		try{
			
			File dstFile = new File(sourceFilePath);
			if(dstFile.exists())
			{
				dstFile.delete();
			}
	        FileOutputStream fileOutputStream = new FileOutputStream(dstFile);
	        fileOutputStream.write(fileByteToWrite);
	        fileOutputStream.flush();
	        fileOutputStream.close();
	        System.out.println("Output file : " + sourceFilePath + " is successfully saved ");
		}catch(Exception exe){
			exe.printStackTrace();
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
}
