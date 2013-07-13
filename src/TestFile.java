import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@SuppressWarnings("unchecked")
public class TestFile {
	
		
	
	public static void main(String args[]) throws IOException
	{
		
		WatchService watcher = FileSystems.getDefault().newWatchService();
		WatchKey key;
		
		File file = new File("C:\\test");
		
		Path dir = file.toPath();
		System.out.println(dir.getFileName());
		try {
		     key = dir.register(watcher,
		                           ENTRY_CREATE,
		                           ENTRY_DELETE,
		                           ENTRY_MODIFY);
		} 
		catch (IOException x) {
		    System.err.println(x);
		}
	
	for (;;) {

	    // wait for key to be signaled
	   
	    try {
	        key = watcher.take();
	    } catch (InterruptedException x) {
	        return;
	    }

	    for (WatchEvent<?> event: key.pollEvents()) {
	        WatchEvent.Kind<?> kind = event.kind();

	        // This key is registered only
	        // for ENTRY_CREATE events,
	        // but an OVERFLOW event can
	        // occur regardless if events
	        // are lost or discarded.
	        if (kind == ENTRY_DELETE) {
	        	System.err.println("File has been deleted");
	       //     continue;
	       }
	        if(kind == ENTRY_MODIFY){
	        	System.err.println("File has been modified");
	        }
	        if(kind == ENTRY_CREATE){
	        	System.err.println("A File has been Created");
	        }
	        // The filename is the
	        // context of the event.
	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
	        Path filename = ev.context();

	        // Verify that the new
	        //  file is a text file.
	        try {
	            // Resolve the filename against the directory.
	            // If the filename is "test" and the directory is "foo",
	            // the resolved name is "test/foo".
	            Path child = dir.resolve(filename);
	            if (Files.probeContentType(child).equals("text/plain")) {
	                System.err.format("New file '%s'" +
	                    " is a plain text file.%n", filename);
	                continue;
	            }
	        } catch (IOException x) {
	            System.err.println(x);
	            continue;
	        }

	        // Email the file to the
	        //  specified email alias.
	        System.out.format("Emailing file %s%n", filename);
	        //Details left to reader....
	    }

	    // Reset the key -- this step is critical if you want to
	    // receive further watch events.  If the key is no longer valid,
	    // the directory is inaccessible so exit the loop.
	    boolean valid = key.reset();
	    if (!valid) {
	        break;
	    }
	}
	}
	
}