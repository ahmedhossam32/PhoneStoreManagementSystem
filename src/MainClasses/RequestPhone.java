package MainClasses;

import java.time.LocalDateTime;

public class RequestPhone {
    private int requestID;
    private Client client;
    private String modelRequested;
    private LocalDateTime requestDate;
    private boolean isNotified;

   
    

    public RequestPhone(Client client, String modelRequested, LocalDateTime requestDate) {
        
        this.client = client;
        this.modelRequested = modelRequested;
        this.requestDate = requestDate;
        this.isNotified = false;
    }
      public RequestPhone(int requestID, Client client, String modelRequested, LocalDateTime requestDate, boolean isNotified) {
        this.requestID = requestID;
        this.client = client;
        this.modelRequested = modelRequested;
        this.requestDate = requestDate;
        this.isNotified = isNotified;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
      
      

    public Client getClient() {
        return client;
    }

    public String getModelRequested() {
        return modelRequested;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public boolean isNotified() {
        return isNotified;
    }

  
    public void markAsNotified() {
        this.isNotified = true;
    }
}
