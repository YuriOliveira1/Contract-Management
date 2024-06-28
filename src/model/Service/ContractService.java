package model.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){

        double newAmount = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            double interest = onlinePaymentService.interest(newAmount, i);
            double payment = onlinePaymentService.payment(interest);
            LocalDate dueDate = contract.getDate().plusMonths(i);
            contract.getInstallments().add(new Installment(dueDate, payment));
        }
    }
}
