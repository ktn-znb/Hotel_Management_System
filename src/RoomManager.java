import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class RoomManager {
    private static ArrayList<Room> roomsList;
    // Declare an ArrayList to store Room objects

    // Constructor that initializes the roomsList and reads rooms from a file
    public RoomManager() {
        roomsList = new ArrayList<>();
        readRoomsFromFile();
    }
    // Method to read rooms from a file and add them to the roomsList
    private void readRoomsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("rooms.txt"))) {
            String line;
            // Read each line of the file until there are no more lines
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Split the line into parts using a comma delimiter
                int roomNo = Integer.parseInt(parts[0]);
                // Convert the first part to an integer for the room number
                String roomType = parts[1];
                // Convert the second part to a string for the room type
                double roomPrice = Double.parseDouble(parts[2]);
                // Convert the third part to a double for the room price
                Room room = new Room(roomNo, roomType, roomPrice);
                // Create a new Room object using the room number, type, and price
                // Add the Room object to the roomsList
                roomsList.add(room);
            }
        } catch (IOException e) {
            System.err.println("Failed to read rooms from file.");
            // If there is an error reading from the file, print an error message
        }
    }
    public List<Room> getRooms() {
        return roomsList;
    }
    // Method to get the roomsList

    public void addRoom(Room room)
    // Method to add a new room to the roomsList and the "rooms.txt" file
    {
        roomsList.add(room);
        // Add the new Room object to the roomsList

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rooms.txt", true))) {
            // Write the room details to the "rooms.txt" file using a comma delimiter
            String roomDetails = room.getRoomNo() + "," + room.getType() + "," + room.getRoomPrice();
            writer.write(roomDetails);
            writer.newLine();
        } catch (IOException e) {
            // If there is an error writing to the file, print an error message
            System.err.println("Failed to write room to file.");
        }
    }

}