import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerManager {
    private static final String FILENAME = "customers.txt";

    // Write a list of Customer objects to a file
    public static void writeToFile(List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            // Iterate over each Customer in the list
            for (Customer customer : customers) {
                // Write each Customer as a comma-separated line to the file
                bw.write(customer.id + "," + customer.name + "," + customer.phoneNo + "," + customer.age + "," + customer.accompanying + "," + customer.roomNo + "," + customer.days + "," + customer.totalAmt);
                bw.newLine(); // Add a newline character after each line
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }
    }

    // Read Customer objects from a file and return them as a list
    public static List<Customer> readFromFile() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Split the line into an array of Strings based on commas
                String[] values = line.split(",");
                // Parse each String value into the appropriate field of a new Customer object
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String phoneNo = values[2];
                int age = Integer.parseInt(values[3]);
                int accompanying = Integer.parseInt(values[4]);
                int roomNo = Integer.parseInt(values[5]);
                int days = Integer.parseInt(values[6]);
                double totalAmt = Double.parseDouble(values[7]);

                Customer customer = new Customer(age, accompanying, name, phoneNo, roomNo, days, totalAmt);
                customer.id = id;
                // Add the new Customer object to the list
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }
        return customers; // Return the list of Customer objects
    }
    // Sorts a list of Customer objects by their id field using the QuickSort algorithm.
    public static void quickSortById(List<Customer> customers) {
        // Call the quickSortById method with the input list, starting index 0, and ending index size-1.
        quickSortById(customers, 0, customers.size() - 1);
    }

    private static void quickSortById(List<Customer> customers, int left, int right) {
        // Check if the sub-list to sort has more than one element.

        if (left < right) {
            // Partition the sub-list around a pivot element.
            int pivotIndex = partitionById(customers, left, right);
            // Recursively sort the sub-list to the left of the pivot.
            quickSortById(customers, left, pivotIndex - 1);
            // Recursively sort the sub-list to the right of the pivot.
            quickSortById(customers, pivotIndex + 1, right);
        }
    }

    private static int partitionById(List<Customer> customers, int left, int right) {
        // Select the pivot element as the last element in the sub-list.
        Customer pivot = customers.get(right);
        // Initialize the index i to the left of the sub-list.
        int i = left - 1;
        // Loop through the sub-list.
        for (int j = left; j < right; j++) {
            // If an element's id value is less than the pivot's id value, swap it with the element at index i+1 and increment i.
            if (customers.get(j).id < pivot.id) {
                i++;
                Collections.swap(customers, i, j);
            }
        }
        // Swap the pivot with the element at index i+1
        Collections.swap(customers, i + 1, right);
        // Return the index of the pivot.
        return i + 1;
    }
}
