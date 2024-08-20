
import java.util.List;
import java.util.stream.Collectors;
import Product.Status;
import Product.Vehicle;
import Product.VehicleType;

public class VehicleInventoryManagement {

    List<Vehicle> vehicles;

    VehicleInventoryManagement(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getFilteredVehicles(VehicleType vehicleType) {
        List<Vehicle> filteredVehicles = vehicles.stream()
                .filter((vehicle) -> vehicleType == vehicle.getVehicleType() && vehicle.getStatus() == Status.AVAILABLE)
                .collect(Collectors.toList());
        return filteredVehicles;
    }
    public void changeStatusofVehicle(Vehicle selectedVehicle,Status status )
    {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleID() == selectedVehicle.getVehicleID()) {
                vehicle.setStatus(Status.AVAILABLE);
            }
        }
    }
}
