package Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class AppZip
{
    List<String> fileList;
    private static final String OUTPUT_ZIP_FILE = "C:\\ganesh\\MyDropBox1.zip";
   // private static final String SOURCE_FOLDER = "myDropBox";
 
    AppZip(){
	fileList = new ArrayList<String>();
    }
 
    public void zipFolder(String pathtoZip){
    	System.out.println("path to file "+pathtoZip);
    	generateFileList(new File(pathtoZip),pathtoZip);
       	zipIt(OUTPUT_ZIP_FILE,pathtoZip);
    }
 
    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    public void zipIt(String zipFile,String pathtoZip){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	System.out.println("Output to Zip : " + zipFile);
 
    	for(String file : this.fileList){
 
    		System.out.println("File Added : " + file);
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(pathtoZip + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
 
    	System.out.println("Done");
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
 
    /**
     * Traverse a directory and get all files,
     * and add the file into fileList  
     * @param node file or directory
     */
    public void generateFileList(File node,String pathtoZip){
 
    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString(),pathtoZip));
	}
 
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename),pathtoZip);
		}
	}
 
    }
 
    /**
     * Format the file path for zip
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file,String pathtoZip){
    	return file.substring(pathtoZip.length()+1, file.length());
    }
}