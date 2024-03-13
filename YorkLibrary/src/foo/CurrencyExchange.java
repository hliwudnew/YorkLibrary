package foo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//adapter class that will take the getPrice request from cart and adapt it to the specified desired currency
public class CurrencyExchange implements IPayment {
	private Payment payment;
	private static Map<String, Double> rateMap = new HashMap<String, Double>(){{
		put("CAD", 1.00);
		put("EUR", 0.6787);
		put("USD", 0.7415);
		put("GBP", 0.5788);
		put("JPY", 108.9752);
		put("KRW", 972.4545);
	}}; //map of all currency types supported by the system, the key will be passed by what user selects when
	//checking out and then this map will convert it to a rate so the getPrice method can get correct pricing

	
	public CurrencyExchange(Payment payment) {
		this.payment=payment;
	}
	
	public double getPrice(String currency) {
		return this.payment.getConvertedPrice(rateMap.get(currency));
	}
	public static ArrayList<String> getCurrencyList(){
		return new ArrayList<String>(CurrencyExchange.rateMap.keySet());
	}
}
