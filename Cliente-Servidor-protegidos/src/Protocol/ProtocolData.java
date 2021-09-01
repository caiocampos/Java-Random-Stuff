package Protocol;

import java.io.Serializable;

public class ProtocolData implements Serializable {

    private static final long serialVersionUID = 1L;
    private byte[] bytes;
    private int status;
    private String message;   
    private String user;
    private boolean finished;
    private byte[] ver;
    private boolean encripted;

    public ProtocolData() {
	super();
	status = 0;
	finished = false;
	encripted = false;
    }

    public ProtocolData(byte[] bytes) {
        super();
        this.bytes = bytes;
	status = 0;
	finished = false;
	encripted = false;
    }

    public ProtocolData(String data) {
        super();
        this.bytes = data.getBytes();
	status = 0;
	finished = false;
	encripted = false;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isFinished() {
	return finished;
    }

    public void setFinished(boolean finished) {
	this.finished = finished;
    }

    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public byte[] getVer() {
	return ver;
    }

    public void setVer(byte[] ver) {
	this.ver = ver;
    }
    
    public boolean isEncripted() {
	return encripted;
    }

    public void setEncripted(boolean encripted) {
	this.encripted = encripted;
    }
}
