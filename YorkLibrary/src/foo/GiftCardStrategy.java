package foo;

public class GiftCardStrategy implements PaymentStrategy{
	
	private String cardNumber;

	public GiftCardStrategy(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Override
	public boolean pay(int amount) {
		System.out.println(amount +" paid with GiftCard "+ cardNumber.substring(0, 3)+"****");
		return true;
	}
}
