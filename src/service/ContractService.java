package service;

import java.util.Calendar;
import java.util.Date;

import domain.Contract;
import domain.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;
	
	//constructor
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months; //cota b�sica
		
		for (int i = 1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i); //passa a data e o m�s
			
			//Agora acessamos a interface que j� sabe qual servi�o de pagamento vai usar, pois j� informamos na classe Main
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			
			//adiciona parcela com o valor que dever� ser pago
			contract.addInstallment(new Installment(date, fullQuota));
		}
	}

	private Date addMonths(Date date, int mes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, mes);
		return cal.getTime();
	}
}
