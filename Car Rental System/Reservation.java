
import java.util.Date;

import Product.Vehicle;

public class Reservation {
  

    private static int reservationIndex = 1000;
    int reservationId;
    User user;
    Vehicle vehicle;
    Date bookingDate;
    Date dateBookedFrom;
    int daysForRent;
    int hoursForRent;

    ReservationType reservationType;
    ReservationStatus reservationStatus;
    Location location;

    public Reservation(User user, Vehicle vehicle, Date bookingDate, Date dateBookedFrom, int daysForRent,
            int hoursForRent, ReservationType reservationType, ReservationStatus reservationStatus, Location location) {
                reservationIndex++;
                reservationId=reservationIndex;
        this.user = user;
        this.vehicle = vehicle;
        this.bookingDate = bookingDate;
        this.dateBookedFrom = dateBookedFrom;
        this.daysForRent = daysForRent;
        this.hoursForRent = hoursForRent;
        this.reservationType = reservationType;
        this.reservationStatus = reservationStatus;
        this.location = location;
    }
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }


  

}
