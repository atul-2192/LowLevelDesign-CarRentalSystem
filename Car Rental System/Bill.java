
public class Bill {

   private Reservation reservation;
   private double totalBillAmount;
   private boolean isBillPaid;

    public double getTotalBillAmount() {
    return totalBillAmount;
}

public boolean isBillPaid() {
    return isBillPaid;
}

    public void setBillPaid(boolean isBillPaid) {
    this.isBillPaid = isBillPaid;
}

    Bill(Reservation reservation) {
        this.reservation = reservation;
        this.totalBillAmount = computeBillAmount();
        System.out.println("Bill is generated for booking  of "+ reservation.vehicle.getModelName()+ " of amount: "+ totalBillAmount);
        isBillPaid = false;
    }

    public Reservation getReservation() {
        return reservation;
    }

    private double computeBillAmount(){

       int hourlyRentalCost=reservation.vehicle.getHourlyRentalCost();
       int dailyRentalCost=reservation.vehicle.getDailyRentalCost();
       int hoursForRent=reservation.hoursForRent;
       int daysForRent=reservation.daysForRent;
       double totalBillAmount=(1.0*hourlyRentalCost*hoursForRent)+(1.0*dailyRentalCost*daysForRent) ;
       return totalBillAmount;
    }

}
