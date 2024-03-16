package foo;

public abstract class Item {
	
	private int id;
	private String name;
	private double price;
	private ItemStateContext disabled;
	
	public Item() {
		disabled = new ItemStateContext();
	}
	
	public Item(int inputId,String inputName, double inputPrice, ItemStateContext disabled) {
		this.id = inputId;
		this.name= inputName;
		this.price = inputPrice;
		this.disabled = disabled;
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
	
	public void setDisabled(ItemStateContext status) {
		this.disabled.setState(status.getState());
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
	
	public ItemStateContext getDisabled() {
		return this.disabled;
	}
}
