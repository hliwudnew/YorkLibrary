package foo;

public class DebitCardStrategy implements PaymentStrategy{
	private String name;
	private String cardNumber;
	private String expire;
	private String cvv;

	
	public DebitCardStrategy(String name, String cardNumber, String expire, String cvv) {
		this.name = name;
		this.cardNumber = cardNumber.replaceAll("\\s", "");
		this.expire = expire;
		this.cvv = cvv;
	}
	
	@Override
	public boolean pay(int amount) {
		//Makes sure the details of the card are correct
		if(cvv.trim().toCharArray().length == 3 && cardNumber.trim().toCharArray().length == 16 && expire.trim().toCharArray().length <= 5){
			System.out.println(amount +" paid with DebitCard "+ cardNumber.substring(0, 3)+"****");
			return true;
		}
		else {
			System.out.println("Invalid Card details");
			return false;
		}
	}
}
