package foo;

import java.util.ArrayList;
import java.util.Date;

public abstract class User {

	private String email;
	private String password;
	private ArrayList<PhysicalItem> rented;
	private ArrayList<OnlineItem> subscriptions;
	private LibrarySystem system;
	//each user will have a cart with their own items, and a menu that allows them to interact with the cart
	private Cart cart;
	private Menu menu;
	
	
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
	
	//public User(String email, String password, ArrayList<PhysicalItem> rented, ArrayList<OnlineItem> subs) {
	//	this.email = email;
	//	this.password = password;
	//	this.rented = rented;
	//	this.subscriptions = subs;
	//	//create a new empty cart for this user
	//	this.cart=new Cart(new ArrayList<Item>(), this);
	//	//create the command objects and initialize them so that they are using this user's cart
	//	ICartCommand1 clickAdd = new Add(this.cart);
	//	ICartCommand1 clickRemove = new Remove(this.cart);
	//	ICartCommand2 clickClear = new Clear(this.cart);
	//	ICartCommand2 clickCheckout = new Checkout(this.cart);
	//	//create a menu with the commands that the user can use 
	//	this.menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
	//}
	
	//Methods
	
	public void rentPhysicalItem(PhysicalItem wantToRent) {
		//Prevents from going over the limit of 10 items
		if(this.rented.size() < 10) {
			if(wantToRent != null && !this.rented.contains(wantToRent) && !system.getBorrowed().contains(wantToRent) && wantToRent.getStatus().getState() instanceof Enabled) {
				rented.add(wantToRent); //They rent  the book
				wantToRent.setBorrower(email); //The book is now borrowed by them
				system.removeStock(wantToRent); //Book is now in borrowed
				system.addBarrowed(wantToRent);
				Date newDue = new Date(); //makes date object set at current time
				
				//add a month (in milliseconds) to the current date item is being rented,
				//then set it as a due date (setting due date a month ahead because in
				//req2 it says "users can keep item at most for a month")
				long timeInMilli=newDue.getTime()+2629800000L; 
				wantToRent.setDueDate(new Date(timeInMilli));
			}
			else {
				System.out.println("Item entered is null or it has been rented already");
			}
		}
		else {
			System.out.println("Cannot rent more than 10 items");
		}
	}
	
	public void returnPhysicalItem(PhysicalItem item) {

		if(item != null && this.rented.contains(item) && !system.getStock().contains(item)) {
			item.setBorrower("BLANK");
			item.setDueDate(null);
			item.setFee(0);
			item.setLost(false);
			this.rented.remove(item);
			system.addStock(item);
			system.removeBarrowed(item);
			
			//the due date and fee for the return will be accounted for in MainGUI
		}
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
		if(newsletter != null && !this.subscriptions.contains(newsletter) && newsletter.getStatus().getState() instanceof Enabled) {
			this.subscriptions.add(newsletter);	
			newsletter.addSubscriber(this);
		}
		else {
			System.out.println("Online Item doesnt exist");
		}
	}
	
	public int numberOverdue() {
		int numOverdue=0;
		for(PhysicalItem item: this.rented) {
			//if a rented item has a fee on it, it is overdue so add to the count
			if(item.calculateFee()!=0) {
				numOverdue++;
			}
		}
		return numOverdue;
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
	public void setCart(Cart cart) {
		this.cart=cart;
	}
	public void setMenu(Menu menu) {
		this.menu=menu;
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
