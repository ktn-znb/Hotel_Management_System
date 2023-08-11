public class Customer {
    // Customer class that has all the attributes related to a customer
    String name, phoneNo;
    int id, age, accompanying, roomNo, days;
    static int instanceCounter = 0; // static integer "instanceCounter" to increase the customer id by one each time
    double totalAmt;
    //creating a constructor and initializing the instance variables using the constructor
    Customer(int age, int accompanying, String name, String phoneNo, int roomNo, int days, double amount){//room, price, id, days, name, company, phone
        instanceCounter++ ;
        this.id= instanceCounter;
        this.age = age;
        this.accompanying = accompanying;
        this.name = name;
        this.roomNo = roomNo;
        this.phoneNo = phoneNo;
        this.days = days;
        this.totalAmt = amount;
    }

    // the following get methods help to populate the table of customers in the main class
    public int getAge(){return age;}
    public int getDays() {return days;}
    public int getId() {return id;}
    public String getName() {return name;}
    public int getAccompanying() {return accompanying;}
    public String getPhoneNo() {return phoneNo;}

    public double getTotalAmt(){
        return totalAmt;
    }// a return method to get the total amount

    public int getRoomNo() {
        return roomNo;
    }// a return method to get the room number
    // a print method for printing all the details
}
