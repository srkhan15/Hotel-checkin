package assigntment2ex;
public class Room {
  
  private int roomNo;
  private String type;
  private double pricePerDay;
  private boolean availability = true;
  static int nextId = 1;
  
  public Room() {}
  
  public Room (String type) {
    this.type = type;
    roomNo = nextId++; 
  }
  public int getRoomNo () {
    return roomNo;
  }
  
  public String getType () {
    return type;
  }
  
  public double getPricePerDay () {
    return pricePerDay;
  }
  
  public boolean getAvailability () {
    return availability;
  }
   
  public void setType (String type) {
    this.type = type;
  }
  
  public void setPricePerDay (String type) {
    if(type.equalsIgnoreCase("single"))
        pricePerDay = 125;
    else if(type.equalsIgnoreCase("double"))
        pricePerDay = 256;
    else if(type.equalsIgnoreCase("luxury")) 
        pricePerDay =359;
    else 
         pricePerDay =0.0;     
  }
  
  public void setAvailability (boolean availability) {
    this.availability = availability;
  }
  
  public String toString () {
    return "Room #: " + roomNo + ", type: " + type + ", price per day: " + pricePerDay + ", available?  " + availability;
  }
  
}