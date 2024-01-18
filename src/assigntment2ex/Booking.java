package assigntment2ex;
import java.io.*;
import java.time.*;

public class Booking implements Serializable, Comparable<Booking> {
  private int bookingId;
  private int custId;
  private int roomNo;
  private LocalDate startDate;
  private int duration;
  private double cost;
  private double breakfast = 34; 
  private boolean isPaid;
  static int nextID = 10;
  public Booking () {
   bookingId = nextID++;
  }
  
  public Booking (int custId, int roomNo, LocalDate startDate, int duration) {
    this.custId = custId;
    this.roomNo = roomNo;
    this.startDate = startDate;
    this.duration = duration;
    bookingId = nextID++;
  }
   public Booking (int custId, LocalDate startDate, int duration) {
    this.custId = custId;
    this.startDate = startDate;
    this.duration = duration;
    bookingId= nextID++;
  }
   
    public Booking (int custId,  int duration) {
    this.custId = custId;
    this.duration = duration;
    bookingId = nextID++;
  }
  public int getBookingId () {
    return bookingId;
  }
  
  public int getCustId () {
    return custId;
  }
  
  public LocalDate getDate () {
    return startDate;
  }
  
  public int getDuration () {
    return duration;
  }
   
  public int getRoomNo () {
    return roomNo;
  }
  public double getBreakfast(){
    return breakfast;
  } 
  public void setTotalCost(double cost){
    this.cost = cost;
  }
  public boolean getIsPaid(){
    return isPaid;
  }
  public void setDate (String dateStr) {
    startDate = LocalDate.parse(dateStr);
  }
  
  public void setBreakfast(double breakfast){
    this.breakfast = breakfast;
  }
  public void setDuration (int duration){ 
        this.duration = duration;
  }
  
 public void setRoomNo (int roomNo) {
    this.roomNo = roomNo;
  }
 public void setIsPaid(boolean isPaid){
   this.isPaid = isPaid;
 }
 public LocalDate getEndDate(){
   return startDate.plusDays(duration);
 }
 public double getTotalCost(){
   return cost;
 }
 public int compareTo(Booking b){
   LocalDate endDate = this.getEndDate();
   LocalDate endDate1 = b.getEndDate();
   return endDate.compareTo(endDate1);
 }
 
 
 
  public String toString () {
    return "Booking ID: " + bookingId+ ", customer ID: " + custId + ", start date: " + startDate + ", duration: " + duration +
            "\nRoom #: " + roomNo + "\n";
  }
  
}