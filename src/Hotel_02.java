import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
public class Hotel_02 extends Application  {
    //Initializes the list of customers here
    public Hotel_02() {
        customers = CustomerManager.readFromFile(); // fills the array list here with the list returned from "readFromFile"
    }
    public  List<Customer> customers;
    //method to add the customer to the array list
    public void addCustomer(Customer customer) {
        customers.add(customer);
        CustomerManager.writeToFile(customers);
    }
    //create an object here of the "RoomManager" to fill the rooms Array List with the array that we return from there
    RoomManager listRoom = new RoomManager();
    ArrayList<Room> rooms = (ArrayList<Room>) listRoom.getRooms();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // we create the first stage that we see first
        primaryStage.setTitle("The Grand Budapest Hotel");
        // added an image here
        Image image = new Image("FinalHotel.jpg");
        ImageView imageView = new ImageView(image);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        Button menuButton = new Button("MENU");
        // create a new button here that calls the display menu window here
        menuButton.setStyle("-fx-font-family: verdana; -fx-font-weight: bold; -fx-font-size: 22; -fx-background-color: #eecfd4; -fx-alignment: center;");
        menuButton.setOnAction(e -> {
            // display the window with the options when the button is clicked
            displayMenuWindow();
            primaryStage.close(); //close the first stage to open the stage displaying menu options here
        });

        layout.getChildren().addAll(imageView, menuButton); // add our two elements here to this vertical box
        layout.setStyle("-fx-background-color: WHITE;");
        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void displayMenuWindow() {
        Stage menuStage = new Stage();
        menuStage.setTitle("Menu");

        Label label = new Label(" Select an option: ");
        label.setStyle("-fx-font-family: verdana; -fx-font-weight: bold; -fx-font-size: 22; -fx-alignment: center;");

        //create a button here that calls the window to register the customer here
        Button registerButton = new Button("1. Register a customer and book room");
        registerButton.setStyle("-fx-font-family: verdana; -fx-font-size: 15; -fx-background-color: #eecfd4; -fx-alignment: center;");
        registerButton.setOnAction(e -> {
            // display the window for registering a customer and booking a room
            displayRegisterWindow();
        });

        Button checkoutButton = new Button("2. Check-out and pay the bill");
        // create a checkout button here that will call the checkout window here
        checkoutButton.setStyle("-fx-font-family: verdana; -fx-font-size: 15; -fx-background-color: #eecfd4; -fx-alignment: center;");
        checkoutButton.setOnAction(e -> displayCheckoutWindow());

        Button allCustomersButton = new Button("3. See all customers");
        //  create a button here that will display the list of customers here
        allCustomersButton.setStyle("-fx-font-family: verdana; -fx-font-size: 15; -fx-background-color: #eecfd4; -fx-alignment: center;");
        allCustomersButton.setOnAction(e -> {
            // display the window for showing all customers
            displayAllCustomersWindow();
        });

        Button addRoomButton = new Button("4. Add a room");
        // create a button here to allow the user to add room here
        addRoomButton.setStyle("-fx-font-family: verdana; -fx-font-size: 15; -fx-background-color: #eecfd4; -fx-alignment: center;");
        addRoomButton.setOnAction(e -> {
            // display the window for adding a room
            displayAddRoomWindow();
        });

        Button exitButton = new Button("5. Exit");
        // exit button that simply closes the menu stage and  thus ending the programme here
        exitButton.setStyle("-fx-font-family: verdana; -fx-font-size: 15; -fx-background-color: #eecfd4; -fx-alignment: center;");
        exitButton.setOnAction(e -> menuStage.close());

        VBox buttonsLayout = new VBox();
        // create vertical box so we can added all our menu buttons
        buttonsLayout.getChildren().addAll(label, registerButton, checkoutButton, allCustomersButton, addRoomButton, exitButton);
        buttonsLayout.setStyle("-fx-background-color: LIGHTBLUE;");
        buttonsLayout.setSpacing(20);
        buttonsLayout.setPadding(new Insets(20));

        ImageView welcomeView = new ImageView(new Image("Lobby_boy.png"));
        // adding an image here to adjacent to all the buttons
        welcomeView.setFitWidth(200);
        welcomeView.setPreserveRatio(true);
        welcomeView.setSmooth(true);
        welcomeView.setCache(true);

        HBox layout = new HBox();
        layout.getChildren().addAll( buttonsLayout, welcomeView);
        //adding the two vertical box and the image here so they are adjacent to each other
        layout.setStyle("-fx-background-color: LIGHTBLUE;");
        layout.setSpacing(20);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 620, 330);
        menuStage.setScene(scene);
        menuStage.show();
    }
    Room selectedRoom = null ; // creating a variable to hold the selected room
    String[] userRoomType = {""}; // an array of user room type to hold the user room type decided by button clicked
    double roomPrice;
    private void displayRegisterWindow() {
        Hotel_02 myHotel_02 = new Hotel_02();

        Stage registrationStage = new Stage();
        // displaying the registration form here
        registrationStage.setTitle("Registering the Customer");

        Label label = new Label("""
                Welcome!
                """);
        label.setStyle("-fx-font-family: verdana; -fx-font-weight: bold;-fx-background-color: #c7b8ea; -fx-font-size: 22; -fx-alignment: center;");

        // creating the name, age, people accompanying, phone, duration labels and the fields for taking their input

        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-family: Verdana;-fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField nameField = new TextField ();
        nameField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox nameHbox = new HBox();
        nameHbox.getChildren().addAll( nameLabel, nameField);
        nameHbox.setStyle("-fx-spacing: 10px;");

        Label ageLabel = new Label("Age:");
        ageLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField ageField = new TextField ();
        ageField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox ageHbox = new HBox();
        ageHbox.getChildren().addAll(ageLabel, ageField);
        ageHbox.setStyle("-fx-spacing: 10px;");

        Label companyLabel = new Label("Number of people with you:");
        companyLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField companyField = new TextField ();
        companyField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox companyHbox = new HBox();
        companyHbox.getChildren().addAll(companyLabel, companyField);
        companyHbox.setStyle("-fx-spacing: 10px;");

        Label phoneLabel = new Label("Phone no :");
        phoneLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField phoneField = new TextField ();
        phoneField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox phoneHbox = new HBox();
        phoneHbox.getChildren().addAll(phoneLabel, phoneField);
        phoneHbox.setStyle("-fx-spacing: 10px;");

        Label daysLabel = new Label("Number of days you will be staying:");
        daysLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField daysField = new TextField ();
        daysField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox daysHbox = new HBox();
        daysHbox.getChildren().addAll(daysLabel, daysField);
        daysHbox.setStyle("-fx-spacing: 10px;");

        Label roomLabel = new Label("Choose what room you want :");
        roomLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #c7b8ea;");

        // creating four buttons that on clicking will set the variable userRoomType[0] to the corresponding room type

        Button singleButton = new Button("Single");
        singleButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4;");
        singleButton.setOnAction(e -> userRoomType[0] = "Single");

        Button doubleButton = new Button("Double");
        doubleButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4;");
        doubleButton.setOnAction(e -> userRoomType[0] = "Double");

        Button suiteButton = new Button("Suite");
        suiteButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4;");
        suiteButton.setOnAction(e -> userRoomType[0] = "Suite");

        Button studioButton = new Button("Studio");
        studioButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4");
        studioButton.setOnAction(e -> userRoomType[0] = "Studio");

        Button showAvailableRoomsButton = new Button("Show Available Rooms");
        showAvailableRoomsButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4");
        showAvailableRoomsButton.setOnAction(e -> {
            Stage listStage = new Stage();
            // created a stage here to list all the available rooms
            listRoom.getRooms();

            // To display the rooms we put the new list available rooms in the observable rooms
            ObservableList<Room> availableRooms = FXCollections.observableArrayList();
            ListView<Room> roomListView = new ListView<>(availableRooms);
            roomListView.setStyle("-fx-font-family: Verdana;-fx-font-size: 14pt; -fx-text-fill: #333333;");

            boolean roomFound = false;
            for (Room room : rooms) {
 // go through our rooms array list and check if that room is available and then add it into the availableRooms list
                if (room.getType().equalsIgnoreCase(userRoomType[0]) && room.getIsAvailable()) {
                    availableRooms.add(room);
                    roomFound = true;
                }
            }

            roomListView.setCellFactory(param -> new ListCell<>() {
                @Override
// We arrange the room List view to display all the available rooms in a certain format
                protected void updateItem(Room item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText("Room no. " + item.getRoomNo() + " with price " + item.getRoomPrice());
                    }
                }
            });
            if (!roomFound) { // if there are no room found we simply close the stage
                listStage.close();
            }
            roomListView.setOnMouseClicked(event -> {
                // the variable selected room is defined when the room is clicked on in the room list view
                selectedRoom = roomListView.getSelectionModel().getSelectedItem();
                // book room method will make the boolean isAvailable false
                selectedRoom.bookRoom(myHotel_02.customers.size());
                //the room clicked on here in the room list view is removed from viewing
                availableRooms.remove(selectedRoom);
            });

            Button okButton = new Button("ok");
            okButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4");
            okButton.setOnAction(event -> {
            // we get the value of the text fields entered by the user as strings, integers
                String userName = nameField.getText();

                String userPhoneNo = phoneField.getText();

                String age = ageField.getText();
                int userAge = 0;
                if (!age.isEmpty()) {userAge = Integer.parseInt(age);}

                String days = daysField.getText();
                int userDays = 0;
                if (!days.isEmpty()) {userDays = Integer.parseInt(days);}

                String company = companyField.getText();
                int userComp =0 ;
                if (!company.isEmpty()) {userComp = Integer.parseInt(company);}

                // we get the room price from the rooms array list and multiply by user days to get total price the
                // customer will have to play
                roomPrice = rooms.get(selectedRoom.roomNo - 1).getRoomPrice() * userDays;

                // sorting the array customers here just in case it had been manually put out of order
                // by calling the quick sort method from the customer manager class
                List<Customer> customers = CustomerManager.readFromFile();
                CustomerManager.quickSortById(customers);
                CustomerManager.writeToFile(customers);
                // add all the customer details into customer object created below so we can add those into the array list customers
                Customer obj01 = new Customer(userAge, userComp, userName, userPhoneNo, selectedRoom.roomNo, userDays, roomPrice);
                myHotel_02.addCustomer(obj01); // call the add customer method here to add the customer object

                listStage.close();
                registrationStage.close();
            });
// creating a vertical box here to add the label, list of rooms and the ok button here
            VBox List = new VBox(new Label("Available Rooms:"), roomListView, okButton);
            List.setStyle("-fx-font-family: Verdana;-fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #c7b8ea;");
            List.setAlignment(Pos.CENTER);
            List.setSpacing(20);
            List.setPadding(new Insets(20));
            Scene scene = new Scene(List, 400, 379);
            // Set the title of the window and show it
            listStage.setTitle("Available Rooms Display");
            listStage.setScene(scene);
            listStage.show();

        });

        HBox roomTypeHbox = new HBox();
        // creating a horizontal box to add all the elements of the room type buttons
        roomTypeHbox.getChildren().addAll(singleButton, doubleButton, suiteButton, studioButton);
        roomTypeHbox.setSpacing(10);

        VBox allBox = new VBox();
        // adding all the elements of the registration form
        allBox.getChildren().addAll(label, nameHbox, ageHbox, companyHbox, phoneHbox, daysHbox, roomLabel, roomTypeHbox, showAvailableRoomsButton);
        allBox.setSpacing(10);
        allBox.setPadding(new Insets(10));
        allBox.setStyle("-fx-background-color: #c7b8ea");


        Scene scene = new Scene(allBox, 557, 379);
        registrationStage.setScene(scene);
        registrationStage.show();

    }
    String[] bill = {""};// to later use to print the bill in a label.
    private void displayCheckoutWindow() {
        listRoom.getRooms();
        Hotel_02 myHotel_02 = new Hotel_02();

        Stage goodbyeStage = new Stage();

        // set the label and text field to get the customer id so we can pull the total amount the customer has to give from arraylist

        Label custIdLabel = new Label("Please enter your customer's Id: ");
        custIdLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField custIdField = new TextField ();
        custIdField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox idHbox = new HBox();
        idHbox.getChildren().addAll(custIdLabel, custIdField);
        idHbox.setStyle("-fx-spacing: 10px;");

        Stage proceedStage = new Stage();
        Button proceedButton = new Button("Proceed to Payment");
        proceedButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4");
        proceedButton.setOnAction(event -> {
            Image image = new Image("farewell.png");
            // creating an image that will pop up after the customer is shown the bill
            ImageView farewell_image = new ImageView(image);

            // get the csutomer id from the text field and parse it into an integer her
            String id = custIdField.getText();
            int custId = 0;
            if (!id.isEmpty()) {custId = Integer.parseInt(id);}

            if (!myHotel_02.customers.isEmpty() && custId > 0 && custId <= myHotel_02.customers.size()) {
                double priceCalculated = myHotel_02.customers.get(custId - 1).getTotalAmt();
                bill[0] = " The total bill is "+priceCalculated+" ";
                // we make the string bill so that we can put this in a label later on
                rooms.get(myHotel_02.customers.get(custId - 1).getRoomNo() - 1).leaveRoom();
                // we get the room the user resided in so we can make it available again

        } else {
            goodbyeStage.close();
        }
            Button goodbyeButton = new Button("Goodbye");
            // a goodbye button that will simply close both the stages and go back to the menu stage
            goodbyeButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4");
            goodbyeButton.setOnAction(e -> {
                proceedStage.close();
                goodbyeStage.close();
            });
            // creating and filling the label with the bill string that I filled earlier
            Label displayBillLabel = new Label( bill[0] );
            displayBillLabel.setStyle("-fx-font-family: verdana; -fx-font-weight: bold;-fx-background-color: #c7b8ea; -fx-font-size: 22; -fx-alignment: center;");
            VBox proceedBox = new VBox();
            proceedBox.getChildren().addAll(displayBillLabel, farewell_image,goodbyeButton);
            Scene scene = new Scene(proceedBox, 500,500);

            proceedStage.setScene(scene);
            proceedStage.show();
            proceedBox.setAlignment(Pos.CENTER);
            proceedBox.setSpacing(20);
            proceedBox.setPadding(new Insets(20));
            proceedBox.setStyle("-fx-background-color: LIGHTBLUE;");

        });

        VBox seeoutBox = new VBox();
        seeoutBox.getChildren().addAll(idHbox, proceedButton );
        seeoutBox.setAlignment(Pos.CENTER);
        seeoutBox.setSpacing(20);
        seeoutBox.setPadding(new Insets(20));
        seeoutBox.setStyle("-fx-background-color: LIGHTBLUE;");
        Scene scene = new Scene(seeoutBox, 620, 100);
        goodbyeStage.setScene(scene);
        goodbyeStage.show();
    }
    private void displayAllCustomersWindow() {
        // this displays all the customers form the customers array list
        Stage customerStage = new Stage();

        TableView<Customer> customerTableView = new TableView<>();

        // creating a table here and defining each column to add the values from the customers array list

        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");

        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");

        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        phoneColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");

        TableColumn<Customer, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");

        TableColumn<Customer, Integer> accompanying = new TableColumn<>("Company");
        accompanying.setCellValueFactory(new PropertyValueFactory<>("accompanying"));
        accompanying.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");
        accompanying.setPrefWidth(100);

        TableColumn<Customer, Integer> roomNo = new TableColumn<>("Room No");
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        roomNo.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");
        roomNo.setPrefWidth(100);

        TableColumn<Customer, Integer> duration = new TableColumn<>("Duration");
        duration.setCellValueFactory(new PropertyValueFactory<>("days"));
        duration.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");
        duration.setPrefWidth(100);

        TableColumn<Customer, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmt"));
        totalPriceColumn.setPrefWidth(100);
        totalPriceColumn.setStyle("-fx-alignment: CENTER; -fx-background-color: #eecfd4; -fx-font-size: 14px; -fx-font-family: Verdana;");

        customerTableView.getColumns().addAll(idColumn,nameColumn,phoneColumn,ageColumn,accompanying,roomNo,duration,totalPriceColumn);

        // here we insert the values of the columns from the customers array list
        // making sure the values that we add match the order of the constructor we have in the Customer class
        ObservableList<Customer> observableList = FXCollections.observableArrayList(customers);
        // adding the customers array list in the
        customerTableView.setItems(observableList);

        VBox tableBox = new VBox();
        tableBox.getChildren().add(customerTableView);
        tableBox.setSpacing(10);
        tableBox.setPadding(new Insets(10));
        tableBox.setStyle("-fx-background-color: #c7b8ea");

        Scene scene = new Scene(tableBox, 726,445);
        customerStage.setScene(scene);
        customerStage.show();

    }
    private void displayAddRoomWindow() {
        Stage addRoomStage = new Stage();

        listRoom.getRooms();

        // following three are labels and thei text field to enter the room type, room number, room price for a day
        // so we can add them to the rooms array list

        Label roomTypeLabel = new Label("Enter the room type: ");
        roomTypeLabel.setStyle("-fx-font-family: Verdana;-fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField roomTypeField = new TextField ();
        roomTypeField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox roomTypeHbox = new HBox();
        roomTypeHbox.getChildren().addAll( roomTypeLabel, roomTypeField);
        roomTypeHbox.setStyle("-fx-spacing: 10px;");

        Label roomNumberLabel = new Label("Enter Room Number:");
        roomNumberLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField roomNumberField = new TextField ();
        roomNumberField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox roomNumberHbox = new HBox();
        roomNumberHbox.getChildren().addAll(roomNumberLabel, roomNumberField);
        roomNumberHbox.setStyle("-fx-spacing: 10px;");

        Label roomPriceLabel = new Label("Enter the room price for a day:");
        roomPriceLabel.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;");
        TextField roomPriceField = new TextField ();
        roomPriceField.setStyle("-fx-font-family: Verdana; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");
        HBox roomPriceHbox = new HBox();
        roomPriceHbox.getChildren().addAll(roomPriceLabel, roomPriceField);
        roomPriceHbox.setStyle("-fx-spacing: 10px;");

        Button addRoomButton = new Button("Add");
        // add button that will simply add the room to the array list
        addRoomButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 14pt; -fx-text-fill: #333333;-fx-background-color: #eecfd4;");
        addRoomButton.setOnAction(actionEvent -> {
            // get the room type, price , number from the text field
            String roomType = roomTypeField.getText();

            String roomPrice = roomPriceField.getText();
            double newRoomPrice = 0;
            if (!roomPrice.isEmpty()) {newRoomPrice = Double.parseDouble(roomPrice);}

            String roomNum = roomNumberField.getText();
            int roomNumber = 0;
            if (!roomNum.isEmpty()) {roomNumber = Integer.parseInt(roomNum);}

            // making a room object so we can add the room attributes to it
            Room newRoom = new Room(roomNumber, roomType, newRoomPrice);
            RoomManager roomManager = new RoomManager();
            roomManager.addRoom(newRoom); //  adding the object to the array list

            addRoomStage.close();
        });
        VBox addRoomBox = new VBox();
        addRoomBox.getChildren().addAll(roomTypeHbox, roomNumberHbox, roomPriceHbox, addRoomButton);
        addRoomBox.setSpacing(10);
        addRoomBox.setPadding(new Insets(10));
        addRoomBox.setStyle("-fx-background-color: #c7b8ea");
        addRoomBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(addRoomBox, 500,196);
        addRoomStage.setScene(scene);
        addRoomStage.show();
    }
}