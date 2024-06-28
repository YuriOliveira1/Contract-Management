package model.Service;

public class PaypalService implements OnlinePaymentService{
    private float taxPayment = 2;

    @Override
    public double interest(Double amount, Integer months) {
        return ((amount / 100) * months) + amount;
    }

    @Override
    public double payment(Double amount) {
        return amount + ((amount * taxPayment) / 100);
    }
    

}
