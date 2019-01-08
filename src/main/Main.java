package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import domain.Contract;
import domain.Installment;
import service.ContractService;
import service.PaypalService;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		
		Contract contract =  new Contract(number, date, totalValue);
		
		System.out.print("Enter number of installments: ");
		int months = sc.nextInt();
		
		//AQUI QUE INFORMO QUAL SERVIÇO A INTERFACE VAI USAR, USAMOS PAYPAL MAS PODERIA SER PAGSEGURO POR EXEMPLO
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, months);
		
		System.out.println("Installments:");
		for (Installment p : contract.getInstallments()) { //percorre as parcelas
			System.out.println(p);
		}
		
		sc.close();
	}
}
