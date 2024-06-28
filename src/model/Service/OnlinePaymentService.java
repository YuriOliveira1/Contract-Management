package model.Service;

public interface OnlinePaymentService {

    public double payment(Double amount);
    public double interest(Double amount, Integer months);
}
