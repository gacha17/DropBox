import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public String clientID;
    public String eventOccured;
    public String filePath;
    public Date timeStamp;
    public String flag;

    public Message(String clientId, String eventOccurd, String filePath, Date timeStamp, String flag) {
        this.clientID = clientId;
        this.eventOccured = eventOccurd;
        this.filePath = filePath;
        this.timeStamp = timeStamp;
        this.flag = flag;
    }
}