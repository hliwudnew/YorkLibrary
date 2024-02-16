package foo;

public abstract class Item {
	
	private int id;
	private String name;
	private double price;
	
	public Item() {
		
	}
	
	public Item(int inputId,String inputName, double inputPrice) {
		this.id = inputId;
		this.name= inputName;
		this.price = inputPrice;
	}
	
	//Setters
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//Getters
	public double getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
