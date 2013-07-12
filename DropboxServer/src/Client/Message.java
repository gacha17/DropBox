package Client;
import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String clientID;
    private String eventOccured;
    private String filePath;
    private Date timeStamp;
    private String flag;
    private byte[] fileData;

    public Message(String clientId, String eventOccurd, String filePath, Date timeStamp, String flag) {
        this.clientID = clientId;
        this.eventOccured = eventOccurd;
        this.filePath = filePath;
        this.timeStamp = timeStamp;
        this.flag = flag;
    }

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getEventOccured() {
		return eventOccured;
	}

	public void setEventOccured(String eventOccured) {
		this.eventOccured = eventOccured;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
}