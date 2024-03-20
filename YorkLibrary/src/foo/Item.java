package foo;

public abstract class Item {
	
	private int id;
	private String name;
	private double price;
	private ItemStateContext status;
	//discount is a decimal representing the percentage an item is being discounted
	//i.e 0.35 means 35% discount, so item's price is price-(0.35*price) 
	private double discount; 

	
	public Item() {
		status = new ItemStateContext();
		this.discount=0.0;
	}
	
	public Item(int inputId,String inputName, double inputPrice, ItemStateContext disabled) {
		this.id = inputId;
		this.name= inputName;
		this.price = inputPrice;
		this.status = disabled;
		this.discount=0.0;
	}
	public Item(int inputId,String inputName, double inputPrice, ItemStateContext disabled, double discount) {
		this.id = inputId;
		this.name= inputName;
		this.price = inputPrice;
		this.status = disabled;
		this.discount=discount;
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
	
	public void setStatus(ItemStateContext status) {
		this.status.setState(status.getState());
	}
	public void setDiscount(double discount) {
		this.discount = discount;
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
	
	public ItemStateContext getStatus() {
		return this.status;
	}
	public double getDiscount() {
		return discount;
	}
}
