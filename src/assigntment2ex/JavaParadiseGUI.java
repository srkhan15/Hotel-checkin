package assigntment2ex;
/*JavaParadiseGUI.java will have list of rooms, customers, and bookings
 *It will be organized as tabbed pane
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.time.*;
import java.io.*;

public class JavaParadiseGUI extends JFrame implements ActionListener{
  //declarations
  FileInputStream fis;
  ObjectInputStream ois;
  FileOutputStream fos;
  ObjectOutputStream oos;
  ArrayList<Room> rooms ;
  ArrayList<Customer> customers ;
  ArrayList<Booking> bookings ;
   //for roomsTab
       JButton btnRList, btnRClear;
       JTextArea jtaR;
    // for customersTab
       JButton btnCAdd, btnCList, btnCClear; 
       JTextArea jtaC;
       JLabel lblName; 
       JTextField txtName; 
    //for bookingsTab
    JLabel lblSearchRoom, lblDate, lblDays, lblSearchCustomer, lblSelectedRoom, lblSelectedCustomer;
    JTextField txtSearchRoom, txtDate, txtDays, txtSearchCustomer;
    JButton btnBSearch, btnBAdd, btnBList, btnBClear, btnBSave, btnBRead, 
    btnBSearchCustomer, btnBSortBookings, btnRSearch;
    JTextArea jtaB;
    //for checkoutTab
    JRadioButton payments[] = new JRadioButton[4];
    String paymentsLabels[] = {"Visa", "Master card", "Cash", "Later"};
    JPanel pnlOSearch = new JPanel();
    JLabel lblOSearchCustomer = new JLabel("Enter customer id");
    JTextField txtOSearchCustomer = new JTextField(10);
    JButton btnOSearch = new JButton("Search bookings by customer");
    JLabel lblDisplayCustomer = new JLabel();
    JTextArea jtaO = new JTextArea(10, 50);
    JCheckBox chkBreakfast = new JCheckBox("Breakfast:");
    JButton btnPay = new JButton("Pay amount due");
    //JLabel lblTotalAmount = new JLabel("Amount to pay");
    JTextField txtTotalAmount = new JTextField(10);
    JPanel pnlOPayment = new JPanel();
    JButton btnCalculateTotalAmount =  new JButton("Pay amount due");  
    JLabel lblIsPaid = new JLabel();
    JPanel pnlODisplay = new JPanel();
    Customer cfound, cfoundb = null;
    Room rfound = null;
    Booking bfound = null;
 //constructor
    public  JavaParadiseGUI(){
       rooms = new ArrayList<Room>();
       customers = new ArrayList<Customer>();   
       bookings = new ArrayList<Booking>();
      
  //components for tabbed panel
  JTabbedPane tabs = new JTabbedPane();
  JPanel roomsTab = new JPanel();
  JPanel customersTab = new JPanel();
  JPanel bookingsTab = new JPanel();
  JPanel checkoutTab = new JPanel();
  //components for roomsTab
  //__________________________________________
  JPanel displayRPanel = new JPanel();
  JPanel buttonRPanel = new JPanel();
   //for roomsTab display
  jtaR = new JTextArea(30, 50);
  JScrollPane scrollR = new JScrollPane(jtaR, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
  //for roomsTab button panel
  btnRList = new JButton("List rooms");
  btnRClear = new JButton("Clear display");
  //_________________________________________
  //components for customerTab
  JPanel inputCPanel = new JPanel();
  JPanel displayCPanel = new JPanel();
  JPanel buttonCPanel = new JPanel();
  inputCPanel.setLayout(new GridLayout(2,1));
  inputCPanel.setBorder(new EmptyBorder(30,30,30,30));
  lblName = new JLabel("      Enter name");
  txtName = new JTextField(10);
  btnCAdd = new JButton("Add customer");
  btnCList = new JButton("List customers");
  btnCClear = new JButton("Clear");
  jtaC = new JTextArea(30, 50);
  jtaC.setBorder(new TitledBorder("List of customers")); 
  //______________________________________________
  //components for bookingsTab
  JPanel inputBPanel = new JPanel();
  lblSearchRoom = new JLabel("Enter room type");
  txtSearchRoom = new JTextField(10);
  lblDate = new JLabel("Enter date (yyyy-mm-dd)");
  txtDate = new JTextField(10);
  lblDays = new JLabel("Enter days");
  txtDays = new JTextField(10);
  lblSearchCustomer = new JLabel("Enter customer id    ");
  txtSearchCustomer = new JTextField(10);
  btnRSearch = new JButton("Search room by type");
  btnBAdd = new JButton("Add");
  btnBList = new JButton("List");
  btnBClear = new JButton("Clear");
  btnBSave = new JButton("Save");
  btnBRead = new JButton("Read");
  btnBSearchCustomer = new JButton("Search for customer");
  btnBSortBookings = new JButton("Sort by date");
  jtaB = new JTextArea(8, 40);
  
  
   //build tabs
   //add tabbed pane to center
    add(tabs, BorderLayout.CENTER);
    //add tab panels to it 
    tabs.addTab("Rooms", roomsTab);
    tabs.addTab("Customers", customersTab);
    tabs.addTab("Bookings", bookingsTab);
    tabs.addTab("Checkout", checkoutTab);
    
    //_______________________________________
    //build roomsTab
    roomsTab.setLayout(new BorderLayout());
    roomsTab.add(displayRPanel, BorderLayout.CENTER);
    roomsTab.add(buttonRPanel, BorderLayout.SOUTH);
    jtaR.setBorder(new TitledBorder("List of rooms"));  
    btnRList.addActionListener(this);
    btnRClear.addActionListener(this);
    buttonRPanel.add(btnRList);
    buttonRPanel.add(btnRClear); 
    displayRPanel.add(scrollR);
   //_________________________________________________
    //build customersTab
    customersTab.setLayout(new BorderLayout());
    customersTab.add(inputCPanel, BorderLayout.NORTH);
    customersTab.add(displayCPanel, BorderLayout.CENTER);
    customersTab.add(buttonCPanel, BorderLayout.SOUTH);
    inputCPanel.setLayout(new GridLayout(1, 2));
    inputCPanel.setBorder(new EmptyBorder(30,30,30,30));
    inputCPanel.add(lblName);
    inputCPanel.add(txtName);
     //build button panel
    btnCAdd.addActionListener(this);
    btnCList.addActionListener(this);
    btnCClear.addActionListener(this);
    buttonCPanel.add(btnCAdd);
    buttonCPanel.add(btnCList);
    buttonCPanel.add(btnCClear); 
    displayCPanel.add(jtaC);
   //______________________________________________
  //build bookingsTab
    JPanel displayBPanel = new JPanel();
    JPanel buttonBPanel = new JPanel();
    //buttonBPanel.setLayout(new GridLayout(1,2, 20, 20));
    bookingsTab.setLayout(new BorderLayout());
    bookingsTab.add(inputBPanel, BorderLayout.NORTH);
    bookingsTab.add(displayBPanel, BorderLayout.CENTER);
    bookingsTab.add(buttonBPanel, BorderLayout.SOUTH);
    
    inputBPanel.setBorder(new EmptyBorder(30,50,50,30));
    inputBPanel.setLayout(new GridLayout(6,2,20,10));
    inputBPanel.add(lblSearchRoom);
    inputBPanel.add(txtSearchRoom);
    inputBPanel.add(btnRSearch);
    lblSelectedRoom = new JLabel();
    inputBPanel.add(lblSelectedRoom);
    inputBPanel.add(lblSearchCustomer);
    inputBPanel.add(txtSearchCustomer);
    inputBPanel.add(btnBSearchCustomer);
    lblSelectedCustomer = new JLabel();
    inputBPanel.add(lblSelectedCustomer );
    inputBPanel.add(lblDate);
    inputBPanel.add(txtDate);
    inputBPanel.add(lblDays);
    inputBPanel.add(txtDays);
    JScrollPane scrollB = new JScrollPane(jtaB, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    
    displayBPanel.add(scrollB);
     
    btnBAdd.addActionListener(this);
    btnBList.addActionListener(this);
    btnBSortBookings.addActionListener(this);
    btnBSearchCustomer.addActionListener(this);
    btnBSave.addActionListener(this);
    btnRSearch.addActionListener(this);
    btnBRead.addActionListener(this);
    btnBClear.addActionListener(this);
    buttonBPanel.add(btnBAdd);
    buttonBPanel.add(btnBList);
    buttonBPanel.add(btnBSortBookings);
    buttonBPanel.add(btnBSave);
    buttonBPanel.add(btnBRead);
    buttonBPanel.add(btnBClear);
    
   //build checkoutTab
    checkoutTab.setLayout(new BorderLayout());
    checkoutTab.add(pnlOSearch, BorderLayout.NORTH);
    checkoutTab.add(pnlODisplay, BorderLayout.CENTER);
    checkoutTab.add(pnlOPayment, BorderLayout.SOUTH);
    
    //pnlSearch
     //pnlOSearch.setLayout(new GridLayout(2,2,30,30));
     pnlOSearch.setBorder(new EmptyBorder(30,30,30,30));
     lblOSearchCustomer = new JLabel("Enter customer id");
     txtOSearchCustomer = new JTextField(10);
     btnOSearch = new JButton("Search bookings by customer");
     btnOSearch.addActionListener(this);
     pnlOSearch.add(lblOSearchCustomer);
     pnlOSearch.add(txtOSearchCustomer);
     pnlOSearch.add(btnOSearch);
     pnlOSearch.add(lblDisplayCustomer);
     //pnlDisplay
     JScrollPane scrollO = new JScrollPane(jtaO, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
     pnlODisplay.add(scrollO);
     pnlODisplay.setBorder(new TitledBorder("Booking for checkout"));
     pnlOPayment.setBorder(new TitledBorder("Payment"));
     pnlOPayment.setLayout(new GridLayout(1,2,20,20));
     //pnlPaymentOpt
     JPanel pnlPaymentOpt = new JPanel();
     pnlPaymentOpt.setBorder(new TitledBorder("Payment options"));
     pnlPaymentOpt.setLayout(new GridLayout(4,1));
     //button group  
     ButtonGroup group = new ButtonGroup();
     for(int i = 0; i < 4; i++){
     payments[i]= new JRadioButton(paymentsLabels[i]);
     group.add(payments[i]);
     pnlPaymentOpt.add(payments[i]);
     }
     JPanel pnlTotalAmount = new JPanel();
     pnlTotalAmount.setBorder(new TitledBorder("Process payment"));
     pnlTotalAmount.add(chkBreakfast);
     //pnlTotalAmount.add(lblTotalAmount);
     btnPay.addActionListener(this);
     pnlTotalAmount.add(btnPay);  
     pnlTotalAmount.add(txtTotalAmount);  
     pnlTotalAmount.add(lblIsPaid);
   
     pnlOPayment.add(pnlTotalAmount);
     pnlOPayment.add(pnlPaymentOpt);                         
    
    
  }
  public void populateLists(){
           Room[] arrOfRooms = {new Room("Single"), new Room("Double"), 
           new Room("Single"),new Room("Double")};
           Customer[] arrOfCustomers = {new Customer("Natasha"), 
           new Customer("Jeff"), new Customer("Sam")};
           
           for(int i = 0; i < arrOfRooms.length; i++){
            arrOfRooms[i].setPricePerDay(arrOfRooms[i].getType()); 
            rooms.add(arrOfRooms[i]);
           }
          for(int i = 0; i < arrOfCustomers.length; i++)
             customers.add(arrOfCustomers[i]);
           //System.out.println(rooms.toString());
          bookings.add(new Booking(100, 5));
          
          bookings.get(0). setDate("2018-10-10");
          bookings.get(0).setRoomNo(1);
          rfound = searchRoomsByRoomNo(1);
          rfound.setAvailability(false);
          bookings.add(new Booking(101,5));
          bookings.get(1). setDate("2018-10-01");
          bookings.get(1).setRoomNo(2);
          rfound = searchRoomsByRoomNo(2);
          rfound.setAvailability(false);
 }
   public static void main(String[] args){
    JavaParadiseGUI jp = new JavaParadiseGUI();
    jp.populateLists();
    //set up frame
    jp.setSize(600, 550);
    jp.setLocationRelativeTo(null);
    jp.setVisible(true);
   
  }
   public void displayAllRooms() {
      for (Room r:rooms) {
      System.out.println(r);
    }
  }
 
   public void displayAvailableRooms() {
      for (Room r:rooms) {
       if (r.getAvailability())
          System.out.println(r);
    }
  }
   
  public void addCustomer() {
    String name = txtName.getText();
    Customer c = new Customer(name);
    customers.add(c);
  }
  
  public void listCustomers() {
      for (Customer c:customers) {
      System.out.println(c);
    }
  }
  public void listBookings() {
     for(Booking b: bookings)
        jtaB.append(b.toString() + "\n");
    
  }
  public Customer searchCustomerById(int id) {
    
    for (Customer c:customers) {
      if (c.getCustId() == id)
      return c;
    }
    return null;
  }
  public void addBooking() {
    //Scanner input = new Scanner(System.in);
    //System.out.print("Customer ID? ");
    int custId = cfound.getCustId();
    int dur = Integer.parseInt(txtDays.getText());
    //System.out.print("Date in format yyyy-mm-dd? ");
    String dateStr = txtDate.getText();
      LocalDate date = null;
     try{
       date = LocalDate.parse(dateStr);
     }
     catch(Exception e){}
      
     Booking booking = new Booking(custId, date, dur);
     rfound.setAvailability(false);
     booking.setRoomNo(rfound.getRoomNo());
     bookings.add(booking);
    } 
   
  public void removeBookingById () {
    listBookings();
    Scanner input = new Scanner(System.in);
    System.out.print("Booking ID? ");
    int id = input.nextInt();
    for (int i = 0; i < bookings.size(); i++) {
      Booking b = bookings.get(i);
      if (b.getBookingId() == id){
        bookings.remove(b);
      }
    }
   }
  
  public Booking searchBookingsByCustId(int custId) {
      for (Booking b: bookings) {
      if (b.getCustId() == custId)
       return b;
     }
    return null;
  }
  
  public void displayUnpaidBills(){
    
    for (Booking b: bookings) {
      if (!b.getIsPaid())
      System.out.println("Customer " + b.getCustId() + ". Amount due " + b.getTotalCost()+ "$");
    
  }
  }
  public Room searchRoomsByType(String type){
    for(Room r: rooms){
      if(r.getType().equalsIgnoreCase(type)&&r.getAvailability())
        return r;
    }
    return null;
   }
  
 public void checkOut(int custID){
        Booking b = searchBookingsByCustId(custID);
        if(b!=null)
        jtaO.append(b.toString() +"\n");
        Room r = searchRoomsByRoomNo(b.getRoomNo());
        if(r!=null){
        r.setAvailability(true);
        double totalCost = calculateTotalCost(b);
        }
   }
 public double calculateTotalCost(Booking b){
     int duration = b.getDuration();
     Room r = searchRoomsByRoomNo(b.getRoomNo());
     double totalCost = duration * r.getPricePerDay();
        if(chkBreakfast.isSelected())
           totalCost +=b.getBreakfast()*duration;
       b.setTotalCost(totalCost);
       txtTotalAmount.setText(" " + totalCost);
       bookings.remove(b);
       for(int i = 0; i < 3; i++){
         if(payments[i].isSelected()){
           lblIsPaid.setText("Paid by " + payments[i].getText());
           b.setIsPaid(true);
         }
       if(payments[3].isSelected()){ 
           lblIsPaid.setText("Customer will pay later");
           b.setIsPaid(false);
         }
 }
       return totalCost;
 }         
  public void saveBookings() {
    try {
      fos = new FileOutputStream("bookings.dat");
      oos = new ObjectOutputStream(fos);
      for (Booking b:bookings) {
        oos.writeObject(b);
      }
      fos.close();
      oos.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
   public Room searchRoomsByRoomNo(int roomNo){
    for(Room r: rooms){
      if(r.getRoomNo() == roomNo)
        return r;
    }
    return null;
   }
   public void sortByEndDate(){
     jtaB.setText("");
     Collections.sort(bookings);
     
   }
   
  public void readBookings() {
   bookings.clear();
    try {
      fis = new FileInputStream("bookings.dat");
      ois = new ObjectInputStream(fis);
      
      while (true) {
        try {
          Object object = ois.readObject();
          Booking b = (Booking)object;
          //update room status
           int roomNo = b.getRoomNo();
          Room r = searchRoomsByRoomNo(roomNo);
          r.setAvailability(false);
          //add to array list
          bookings.add(b);
          System.out.println(b);
        } catch (EOFException eof) {
          fis.close();
          ois.close();
          break;
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }
  
  public void actionPerformed(ActionEvent e){
  
    if(e.getSource()==btnRList){
         jtaR.setText("");
         for(Room r: rooms){
           System.out.println(r);
          jtaR.append("\n" + r.toString() + "\n");
      }
    }
    if(e.getSource() == btnCList){
      jtaC.setText("");
       for(Customer c: customers){
        jtaC.append("\n"+ c.toString() + "\n");
      }
    }
    
    if(e.getSource()==btnRClear){
      jtaR.setText("");
    }
     if(e.getSource() == btnCClear){
      jtaC.setText("");
    
    }
    if(e.getSource()==btnCAdd){
     addCustomer(); 
    }
    if(e.getSource()==btnRSearch){
      String type = txtSearchRoom.getText();
      System.out.println(type);
      rfound = searchRoomsByType(type);
      System.out.println(rfound);
      lblSelectedRoom.setText("RoomNo "+ rfound.getRoomNo() + ", " + rfound.getType() + ", " + rfound.getPricePerDay());
      txtSearchRoom.setText("");
     }
     if(e.getSource() == btnBSearchCustomer){
      int custId = Integer.parseInt(txtSearchCustomer.getText());
      cfound = searchCustomerById(custId);
      bfound = searchBookingsByCustId(custId);
      lblSelectedCustomer.setText(cfound.getCustId() + ", " + cfound.getName());
      txtSearchCustomer.setText("");
    }
    if(e.getSource()==btnBAdd){
      addBooking();
    }
    if(e.getSource()==btnBList){
      listBookings();
    }
    if(e.getSource() == btnBClear){
      jtaB.setText("");
    }
    if(e.getSource() == btnOSearch){
      int id = Integer.parseInt(txtOSearchCustomer.getText());
      bfound = searchBookingsByCustId(id);
      if(bfound!=null)
       jtaO.append(bfound.toString()+ "\n");
    }
    if(e.getSource() == btnPay){
          calculateTotalCost(bfound);
     }
    if(e.getSource() == btnBSortBookings){
      sortByEndDate();
      listBookings();
    }
    if(e.getSource() == btnBSave){
       saveBookings();
    }
    if(e.getSource()==btnBRead){
      readBookings();
   }
  }
}
  
 
