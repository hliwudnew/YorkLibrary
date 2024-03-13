package foo;

public class Payment {
	private double unConvertedPrice;
	
	public Payment(double price) {
		this.unConvertedPrice=price;
	}

	public double getConvertedPrice(Double rate) {
		return this.unConvertedPrice*rate;
	}
}
