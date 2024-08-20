import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Product.Status;
import Product.Vehicle;
import Product.VehicleType;

public class Store {

    static int storeIndex = 0;
    private int storeId;
    private static VehicleInventoryManagement inventoryManagement;
    private Location storeLocation;
    private List<Reservation> reservations = new ArrayList<>();

    public Store(Location storeLocation) {
        storeIndex++;
        storeId = storeIndex;

        this.storeLocation = storeLocation;
    }

    public Location getStoreLocation() {
        return storeLocation;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setinventoryManagement(List<Vehicle> vehiclesList) {
        List<Vehicle> filteredVehicles = vehiclesList.stream()
                .filter((vehicle) -> storeLocation.city.equals(vehicle.getCity())).collect(Collectors.toList());
        inventoryManagement = new VehicleInventoryManagement(filteredVehicles);
    }

    public List<Vehicle> selectedVehicleList(VehicleType vehicleType) {
        return inventoryManagement.getFilteredVehicles(vehicleType);
    }

    public Reservation createReservation(Vehicle vehicle, User user, int daysForRent, int hoursForRent,
            Date dateBookedFrom) {

        ReservationType reservationType = daysForRent > 0 && hoursForRent > 0 ? ReservationType.Mixed
                : daysForRent > 0 ? ReservationType.DAILY : ReservationType.HOURLY;
        Reservation reservation = new Reservation(user, vehicle, new Date(), dateBookedFrom, daysForRent, hoursForRent,
                reservationType, ReservationStatus.INPROGRESS, storeLocation);
        reservations.add(reservation);
        return reservation;
    }

    public boolean completeReservation(Reservation reservation, Vehicle selectedVehicle) {
        reservations.remove(reservation);
        inventoryManagement.changeStatusofVehicle(selectedVehicle, Status.AVAILABLE);
        System.out.println("Thanks for booking " + selectedVehicle.getModelName() + " Your Journey completed.");
        return true;
    }
}
