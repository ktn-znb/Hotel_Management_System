public class Room {
    // Room class with its related attributes
    int roomNo, customerId;
    boolean isAvailable;
    double roomPrice ;
    String roomType;
    // a simple constructor that will be used to create instances in the hotel class
    Room (int roomNo, String roomType, double roomPrice){
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isAvailable = true;
    }
    // the next four methods are there to return room type, whether the room is available,
    // returns the room number, and returns the room price respectively
    public String getType(){
        return this.roomType ;
    }
    public boolean getIsAvailable(){
        return this.isAvailable ;
    }
    public int getRoomNo(){
        return this.roomNo ;
    }
    public double getRoomPrice() {
        return roomPrice;
    }
    public void bookRoom (int customerId){ //makes the room unavailable and gives the customer an id
        this.isAvailable = false;
        this.customerId =  customerId;
    }
    public void leaveRoom (){
        // gets called to make the room available again
        this.isAvailable = true;
    }

}
