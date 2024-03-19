package foo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayPalStrategy implements PaymentStrategy{
	private String buyerEmail;
	
	public PayPalStrategy(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	@Override
	public boolean pay(double amount) {
		//Checks if @ is in the right place, Email Checker
		String emailRegex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(buyerEmail);
		
		if(matcher.matches() && buyerEmail.contains(".com")) {
			System.out.println(String.format("%.2f",amount) +" paid with PayPal from "+ buyerEmail+"'s account");
			return true;
		}
		else {
			System.out.println("Invalid Email");
			return false;
		}
	}
}
