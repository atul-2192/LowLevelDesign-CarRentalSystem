package Product;

import java.util.Date;

public class Car extends Vehicle{

    public Car(int vehicleNumber, VehicleType vehicleType, String companyName, String modelName, int kmDriven,
            Date manufacturingDate, int average, int cc, int dailyRentalCost, int hourlyRentalCost, int noOfSeat, String city,
            Status status) {
        super(vehicleNumber, vehicleType, companyName, modelName, kmDriven, manufacturingDate, average, cc, dailyRentalCost,
                hourlyRentalCost, noOfSeat,city,status);
      
    }

}

