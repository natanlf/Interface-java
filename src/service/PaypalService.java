package service;

public class PaypalService implements OnlinePaymentService {
	
	private static final double FEE_PERCENTAGE = 0.02; //TAXA DE PAGAMENTO
	private static final double MONTHLY_INTEREST = 0.01; //JUROS SIMPLES A CADA PARCELA

	@Override
	public double paymentFee(double amount) {
		return amount * FEE_PERCENTAGE;
	}

	@Override
	public double interest(double amount, int months) {
		return amount * MONTHLY_INTEREST * months;
	}

}
