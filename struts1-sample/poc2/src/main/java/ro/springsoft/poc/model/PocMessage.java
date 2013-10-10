package ro.springsoft.poc.model;

import com.google.gson.Gson;

/**
 * Entity for persist JMS messages
 * this class maps POCMESSAGE table
 * 
 * @author Miroslav MARKO
 */
public class PocMessage {
    private float ballance;
    private String account;
    private long timestamp;

    public PocMessage() {
    }

    public PocMessage(float ballance, String account) {
        this.ballance = ballance;
        this.account = account;
    }

    public float getBallance() {
        return ballance;
    }

    public void setBallance(float ballance) {
        this.ballance = ballance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public static PocMessage parseMessage(String message){
        return new Gson().fromJson(message, PocMessage.class);
    }
    
    
    
}
