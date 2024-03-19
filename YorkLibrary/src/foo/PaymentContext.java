package foo;

public class PaymentContext {
	private PaymentStrategy payStrat;
	private static String[] paymentMethods = {"Paypal", "Credit", "Debit", "Gift"};
	
	public PaymentContext(PaymentStrategy payStrat) {
		this.payStrat = payStrat;
	}
	
	public boolean pay(double amount) {
		return payStrat.pay(amount);
	}
	public static String[] getPaymentMethods() {
		return paymentMethods;
	}
}
