package Product;

public enum VehicleType {
    CAR,
    BIKE;
    public void displayVehicleTypes()
    {
        for (VehicleType vehicleType : VehicleType.values()) {
            System.out.println(vehicleType.name());
        }
    }
}

