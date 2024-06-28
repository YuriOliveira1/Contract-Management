package Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.Service.ContractService;
import model.Service.OnlinePaymentService;
import model.Service.PaypalService;
import model.entities.Contract;
import model.entities.Installment;


public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do Contrato: ");
        System.out.print("Numero: ");
        Integer number = sc.nextInt();
        sc.nextLine();
        System.out.print("Data: ");
        String dateString = sc.nextLine();
        LocalDate data = LocalDate.parse(dateString, fmt);
        System.out.print("Valor do contrato: ");
        double valor = sc.nextDouble();
        System.out.print("Entre com o número de parcelas: ");
        int parcelas = sc.nextInt();

        Contract contract = new Contract(number, data, valor);

        OnlinePaymentService service2 = new PaypalService();

        ContractService service = new ContractService(service2);
        service.processContract(contract, parcelas);

        System.out.println("Parcelas");
        for (Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }

        sc.close();
    }
}
