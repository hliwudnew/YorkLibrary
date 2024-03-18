package foo;

public abstract class Item {
	
	private int id;
	private String name;
	private double price;
	private ItemStateContext status;
	
	public Item() {
		status = new ItemStateContext();
	}
	
	public Item(int inputId,String inputName, double inputPrice, ItemStateContext disabled) {
		this.id = inputId;
		this.name= inputName;
		this.price = inputPrice;
		this.status = disabled;
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
}
