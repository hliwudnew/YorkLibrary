package foo;

import java.util.ArrayList;

public abstract class User {

	private String email;
	private String password;
	private ArrayList<PhysicalItem> rented;
	private ArrayList<OnlineItem> subscriptions;
	private LibrarySystem system;
	//each user will have a cart with their own items, and a menu that allows them to interact with the cart
	private Cart cart;
	private Menu menu;
	//Ask me, Gabriel, about this connection and how it works
	
	
	public User() {
		this.rented = new ArrayList<PhysicalItem>();
		this.subscriptions = new ArrayList<OnlineItem>();
		//create a new empty cart for this user
		this.cart=new Cart(new ArrayList<Item>(), this);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(this.cart);
		ICartCommand1 clickRemove = new Remove(this.cart);
		ICartCommand2 clickClear = new Clear(this.cart);
		ICartCommand2 clickCheckout = new Checkout(this.cart);
		//create a menu with the commands that the user can use 
		this.menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
	}
	
	public User(String email, String password, ArrayList<PhysicalItem> rented, ArrayList<OnlineItem> subs) {
		this.email = email;
		this.password = password;
		this.rented = rented;
		this.subscriptions = subs;
		//create a new empty cart for this user
		this.cart=new Cart(new ArrayList<Item>(), this);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(this.cart);
		ICartCommand1 clickRemove = new Remove(this.cart);
		ICartCommand2 clickClear = new Clear(this.cart);
		ICartCommand2 clickCheckout = new Checkout(this.cart);
		//create a menu with the commands that the user can use 
		this.menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
	}
	
	//Methods
	
	//NOTE: Moved this method to the cart class, since all renting happens through the cart, the user class does not need
	//this method in its class (user can access it through menu class)
	//however, i am leaving it here strictly because it is being used by CSVReader to intialize the system (can be changed later)
	
	public void rentPhysicalItem(PhysicalItem wantToRent) {
		//Prevents from going over the limit
		if(this.rented.size() < 10) {
			if(wantToRent != null && !this.rented.contains(wantToRent) && !system.getBorrowed().contains(wantToRent)) {
				rented.add(wantToRent); //They rent  the book
				wantToRent.setBorrower(email); //The book is now borrowed by them
				system.removeStock(wantToRent); //Book is now in borrowed
				system.addBarrowed(wantToRent);
			}
			else {
				System.out.println("Item entered is null or it has been rented already");
			}
		}
		else {
			System.out.println("Cannot rent more than 10 items");
		}
	}
	
	public void loginAccount() {
		// No idea how to do this
	}
	
	public void openOnlineItem(Item wantToRead) {
		// Look at subscriptions and open the selected item
	}
	
	public void returnPhysicalItem(PhysicalItem item) {
		if(item != null && this.rented.contains(item) && !system.getStock().contains(item)) {
			item.setBorrower("BLANK");
			this.rented.remove(item);
			system.addStock(item);
			system.removeBarrowed(item);
		}
	}
	
	public void requestNewItem(Item item) {
		//Check to make sure it doesn't exist in library
	}
	
	
	//Setters
	public void unSubscribe(OnlineItem newsletter) {
		if(newsletter != null && this.subscriptions.contains(newsletter)) {
			this.subscriptions.remove(newsletter);
			newsletter.removeSubscriber(this);
		}
		else {
			System.out.println("Online Item doesnt exist");
		}
	}
	
	public void unSubscribeById(int id) {
		for(OnlineItem I: this.subscriptions) {
			if(I.getId() == id) {
				this.subscriptions.remove(I);
				break;
			}
		}
	}
	
	public void subscribe(OnlineItem newsletter) {
		if(newsletter != null && !this.subscriptions.contains(newsletter)) {
			this.subscriptions.add(newsletter);	
			newsletter.addSubscriber(this);
		}
		else {
			System.out.println("Online Item doesnt exist");
		}
	}
	
	
	public void setEmail(String validEmail) {
		if(validEmail != null && validEmail != "") {
			this.email = validEmail;
		}
		else {
			System.out.print("Please input valid email");
		}
	}
	
	public void setPassword(String validPassword) {
		if(validPassword != null && validPassword != "") {
			this.password = validPassword;
		}
		else {
			System.out.print("Please input valid password");
		}
	}
	
	public void setRented(ArrayList<PhysicalItem> items) {
		this.rented = items;
	}
	
	public void setSubscriptions(ArrayList<OnlineItem> subs) {
		this.subscriptions = subs;
	}
	
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
	
	//Getters
	public ArrayList<PhysicalItem> getRented(){
		return this.rented;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public ArrayList<OnlineItem> getSubscriptions(){
		return this.subscriptions;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public Cart getCart() {
		return this.cart;
	}
	
}
