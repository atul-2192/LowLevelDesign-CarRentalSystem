public class Payment {
    private static int paymentIndex=2000;
    private int paymentId;
    private PaymentDetails paymentDetails;
    Payment ()
    {
        paymentIndex++;
        paymentId=paymentIndex;
    }
    public static int getPaymentIndex() {
        return paymentIndex;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
    public void payBill(Bill bill,PaymentMode paymentMode) {
       paymentDetails= new PaymentDetails(bill.getTotalBillAmount(),paymentMode );
       
       
       bill.getReservation().setReservationStatus(ReservationStatus.SCHEDULED);
       bill.setBillPaid(true);
       System.out.println("Your Payment of amount "+ bill.getTotalBillAmount()+ " is successfull !");
    }
}

 