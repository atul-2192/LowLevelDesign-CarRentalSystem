import java.util.Date;

public class PaymentDetails {
    private static int paymentIndex = 1000;
    private int paymentDetailId;
    private double amountPaid;
    private Date dateOfPayment;
    private boolean isRefundable;

    public PaymentDetails(double amountPaid, PaymentMode paymentMode) {
        paymentIndex++;
        paymentDetailId = paymentIndex;
        this.dateOfPayment = new Date();
        this.amountPaid = amountPaid;
        this.paymentMode = paymentMode;
        this.isRefundable = false;
    }

    public static int getPaymentIndex() {
        return paymentIndex;
    }

    public int getPaymentDetailId() {
        return paymentDetailId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public boolean isRefundable() {
        return isRefundable;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    private PaymentMode paymentMode;

 

}
