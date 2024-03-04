package foo;

import java.util.ArrayList;

public abstract class User {

	private String email;
	private String password;
	private ArrayList<PhysicalItem> rented;
	private ArrayList<OnlineItem> subscriptions;
	private LibrarySystem system;
	//Ask me, Gabriel, about this connection and how it works
	
	
	public User() {
		this.rented = new ArrayList<PhysicalItem>();
		this.subscriptions = new ArrayList<OnlineItem>();
	}
	
	public User(String email, String password, ArrayList<PhysicalItem> rented, ArrayList<OnlineItem> subs) {
		this.email = email;
		this.password = password;
		this.rented = rented;
		this.subscriptions = subs;
	}
	
	//Methods
	public void rentPhysicalItem(PhysicalItem wantToRent) {
		if(wantToRent != null && !this.rented.contains(wantToRent) && !system.getBorrowed().contains(wantToRent)) {
			rented.add(wantToRent); //They rent  the book
			wantToRent.setBorrower(email); //The book is now borrowed by them
			system.removeStock(wantToRent); //Book is now in borrowed
			system.addBarrowed(wantToRent);
		}
		else {
			System.out.println("Item entered is null");
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
	public void subscribe(OnlineItem newsletter) {
		//TODO: oversimplification
		this.subscriptions.add(newsletter);
	}
	
	public void unSubscribe(OnlineItem newsletter) {
		//TODO: oversimplification
		this.subscriptions.remove(newsletter);
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
	
}
