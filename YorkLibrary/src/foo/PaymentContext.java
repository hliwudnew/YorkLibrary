package foo;

public class PaymentContext {
	private PaymentStrategy payStrat;
	
	public PaymentContext(PaymentStrategy payStrat) {
		this.payStrat = payStrat;
	}
	
	public boolean pay(int amount) {
		return payStrat.pay(amount);
	}
}
