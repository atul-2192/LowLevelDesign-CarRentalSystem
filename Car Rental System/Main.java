import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Product.Status;
import Product.Vehicle;
import Product.VehicleType;

public class Main {

    public static void main(String args[]) {

        try (Scanner sc = new Scanner(System.in)) {
            List<User> users = addUsers();
            List<Vehicle> vehicles = addVehicles();
            List<Store> stores = addStores(vehicles);

            VehicleRentalSystem rentalSystem = new VehicleRentalSystem(stores, users);
            System.out.println("------------------------------Welcome to Car Rental Application!!------------------------------");
            // 0. User comes
            System.out.println("Enter your UserId");
            int userId = sc.nextInt();
            User userSelected = getUser(userId, users);

            // 1. user search store based on location
            System.out.println("-------------------------------Enter your current Location--------------------------------------");
            System.out.println("Enter PinCode: ");
            int pincode=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter City: ");
            String city=sc.nextLine();
            System.out.println("Enter State: ");
            String state=sc.nextLine();
            System.out.println("Enter Country: ");
            String country=sc.nextLine();
            Location location = new Location(pincode, city, state, country);
            Store store = rentalSystem.getStore(location);
            if (store == null) {
                System.out.println("OOPS! Store is not available at your location.");

            } else {

                // 2. get All vehicles you are interested in (based upon different filters)
                System.out.println("----------------------------Please Select a Vehicle Type: ------------------------------------");
            
                String vehicleTypeInpuString=sc.nextLine().toUpperCase(); 
                VehicleType vehicleType=VehicleType.valueOf(vehicleTypeInpuString);
                List<Vehicle> storeVehicles = store.selectedVehicleList(vehicleType);
        
                if(storeVehicles.size()==0)
                {
                    System.out.println("Sorry! No Vehicle Found.");
                    System.exit(1);
                }
                for (Vehicle vehicle : storeVehicles) {
                    displayVehicleDetails(vehicle);
                }
                System.out.println("Select the Id of Vehicle to Book");
                int vehicleID = sc.nextInt();
                Vehicle selectedVehicle = getSelectedVehicle(vehicleID, storeVehicles);
                System.out.println("Vehicle Selected: ");
                displayVehicleDetails(selectedVehicle);

                // 3.reserving the particular vehicle
              
                System.out.println("Enter number of days you want to rent the selected Vehicle: ");
                int daysForRent = sc.nextInt();
                System.out.println("Enter number of hours you want to rent the selected Vehicle: ");
                int hoursForRent = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter the booking date (dd/MM/yyyy): ");
                String dateInput = sc.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date bookedFrom;
                try {
                    bookedFrom = dateFormat.parse(dateInput);
                    Reservation reservation = store.createReservation(selectedVehicle, userSelected, daysForRent,
                            hoursForRent, bookedFrom);
                    // 4. generate the bill
                    Bill bill = new Bill(reservation);

                    System.out.println("Total Bill for your booking is: " + bill.getTotalBillAmount());
                    // 5. make payment
                    System.out.println("Select Mode of Payment: CASH|ONLINE");
                    String paymentModeSelected = sc.nextLine().toUpperCase();
                    PaymentMode paymentMode = PaymentMode.valueOf(paymentModeSelected);
                    System.out.println("Payment Mode selected: " + paymentMode.name());
                    Payment payment = new Payment();
                    payment.payBill(bill, paymentMode);

                    // 6. trip completed, submit the vehicle and close the reservation
                    store.completeReservation(reservation, selectedVehicle);
                } catch (ParseException e) {
                   
                    e.printStackTrace();
                }

            }
        }

    }


    @SuppressWarnings("deprecation")
    public static List<Vehicle> addVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();
        Date date1 = new Date(120, 5, 15);
        Date date2 = new Date(118, 10, 20);
        Date date3 = new Date(119, 3, 5);
        Date date4 = new Date(121, 1, 10);
        Date date5 = new Date(122, 7, 22);

        // Creating 25 demo Vehicle objects
        vehicles.add(new Vehicle(101, VehicleType.CAR, "Toyota", "Camry", 50000, date1, 30, 2500, 100, 10, 5, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(102, VehicleType.BIKE, "Honda", "CBR", 15000, date2, 40, 1000, 50, 5, 2, "Chennai", Status.RENTED));
        vehicles.add(new Vehicle(103, VehicleType.CAR, "Ford", "F-150", 80000, date3, 20, 5000, 200, 20, 3, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(104, VehicleType.CAR, "BMW", "X5", 40000, date4, 35, 3000, 150, 15, 5, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(105, VehicleType.BIKE, "Ducati", "Panigale", 12000, date5, 45, 1100, 60, 6, 2, "Chennai", Status.RENTED));

        vehicles.add(new Vehicle(106, VehicleType.CAR, "Audi", "Q7", 60000, date1, 32, 2700, 120, 12, 5, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(107, VehicleType.BIKE, "Yamaha", "R15", 18000, date2, 38, 950, 55, 7, 2, "Bangalore", Status.RENTED));
        vehicles.add(new Vehicle(108, VehicleType.CAR, "Chevrolet", "Silverado", 70000, date3, 25, 5200, 220, 25, 3, "Chennai", Status.AVAILABLE));
        vehicles.add(new Vehicle(109, VehicleType.CAR, "Mercedes", "GLA", 45000, date4, 33, 3100, 130, 13, 5, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(110, VehicleType.BIKE, "Kawasaki", "Ninja", 17000, date5, 42, 1200, 65, 8, 2, "Bangalore", Status.RENTED));

        vehicles.add(new Vehicle(111, VehicleType.CAR, "Volvo", "XC90", 55000, date1, 31, 2900, 110, 11, 5, "Chennai", Status.AVAILABLE));
        vehicles.add(new Vehicle(112, VehicleType.BIKE, "Harley-Davidson", "Street 750", 16000, date2, 36, 900, 45, 4, 2, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(113, VehicleType.CAR, "Ram", "1500", 85000, date3, 28, 5300, 230, 30, 3, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(114, VehicleType.CAR, "Lexus", "RX", 47000, date4, 37, 3200, 140, 14, 5, "Chennai", Status.AVAILABLE));
        vehicles.add(new Vehicle(115, VehicleType.BIKE, "Suzuki", "GSX-R1000", 19000, date5, 44, 1150, 70, 9, 2, "Pune", Status.AVAILABLE));

        vehicles.add(new Vehicle(116, VehicleType.CAR, "Hyundai", "Santa Fe", 52000, date1, 34, 2800, 115, 13, 5, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(117, VehicleType.BIKE, "Triumph", "Street Triple", 14000, date2, 41, 980, 53, 6, 2, "Chennai", Status.AVAILABLE));
        vehicles.add(new Vehicle(118, VehicleType.CAR, "Nissan", "Titan", 75000, date3, 29, 5100, 210, 22, 3, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(119, VehicleType.CAR, "Acura", "MDX", 43000, date4, 30, 2600, 105, 10, 5, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(120, VehicleType.BIKE, "BMW", "S1000RR", 20000, date5, 39, 1080, 68, 7, 2, "Chennai", Status.RENTED));

        vehicles.add(new Vehicle(121, VehicleType.CAR, "Tesla", "Model X", 50000, date1, 31, 2800, 120, 12, 5, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(122, VehicleType.BIKE, "Aprilia", "RSV4", 13000, date2, 43, 1000, 60, 8, 2, "Bangalore", Status.AVAILABLE));
        vehicles.add(new Vehicle(123, VehicleType.CAR, "GMC", "Sierra", 81000, date3, 22, 4900, 200, 20, 3, "Chennai", Status.AVAILABLE));
        vehicles.add(new Vehicle(124, VehicleType.CAR, "Jeep", "Grand Cherokee", 48000, date4, 36, 3000, 125, 15, 5, "Pune", Status.AVAILABLE));
        vehicles.add(new Vehicle(125, VehicleType.BIKE, "MV Agusta", "Brutale", 21000, date5, 46, 1200, 75, 9, 2, "Bangalore", Status.AVAILABLE));

       

        return vehicles;
    }

    public static List<User> addUsers() {

        List<User> users = new ArrayList<>();
        User user1 = new User("Atul Singh", "asdfghjk");
        User user2 = new User(" Ankur Anand", "148651684");
        User user3 = new User("Vaishali ", "kuyfg");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        for (User user : users) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("User Name: " + user.getUserName());
            System.out.println("Driving License: " + user.getDrivingLicense());
            System.out.println();
        }
        return users;
    }

    public static List<Store> addStores(List<Vehicle> vehicles) {

        List<Store> stores = new ArrayList<>();
        Store store1 = new Store(new Location(560001, "Bangalore", "Karnataka", "India"));
        Store store2 = new Store(new Location(10001, "Chennai", "Tamil Nadu", "India"));
        Store store3 = new Store(new Location(75001, "Pune", "Maharashtra", "India"));

        store1.setinventoryManagement(vehicles);
        stores.add(store1);
        store2.setinventoryManagement(vehicles);
        stores.add(store2);
        store3.setinventoryManagement(vehicles);
        stores.add(store3);
        return stores;
    }

    public static void displayVehicleDetails(Vehicle vehicle) {
        System.out.println("Vehicle ID: " + vehicle.getVehicleID());
        System.out.println("Vehicle Type: " + vehicle.getVehicleType());
        System.out.println("Company Name: " + vehicle.getCompanyName());
        System.out.println("Model Name: " + vehicle.getModelName());
        System.out.println("Daily Rental Cost: $" + vehicle.getDailyRentalCost());
        System.out.println("Hourly Rental Cost: $" + vehicle.getHourlyRentalCost());
        System.out.println("Number of Seats: " + vehicle.getNoOfSeat());
        System.out.println();
    }

    private static Vehicle getSelectedVehicle(int vehicleID, List<Vehicle> storeVehicles) {
        return storeVehicles.stream()
                .filter(vehicle -> vehicle.getVehicleID() == vehicleID)
                .findFirst()
                .orElse(null);
    }

    private static User getUser(int userId, List<User> usersList) {
        return usersList.stream()
                .filter(user -> userId == user.getUserId())
                .findFirst()
                .orElse(null);
    }

}
