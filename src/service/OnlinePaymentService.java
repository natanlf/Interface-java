package service;

public interface OnlinePaymentService {
	
	double paymentFee(double amount); //taxa de pagamento
	double interest(double amount, int months); //quantia e meses
}
