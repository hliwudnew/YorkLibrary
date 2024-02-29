package foo;

import java.util.ArrayList;

public class User {

	private String email;
	private String password;
	private ArrayList<Item> rented;
	private ArrayList<Item> subscriptions;
	private LibrarySystem system; //TODO: will need to check in everything if the user is in the system
	//Ask me, Gabriel, about this connection and how it works
	
	
	//TODO: Pretty sure this will be needed, hence we should be able to remove the addStock from Library system since Managment team handles it, since users shouldnt have access to it
	//private LibrarySystem system = new LibrarySystem();
	
	public User() {
		this.rented = new ArrayList<Item>();
		this.subscriptions = new ArrayList<Item>();
	}
	
	public User(String email, String password, ArrayList<Item> rented, ArrayList<Item> subs) {
		this.email = email;
		this.password = password;
		this.rented = rented;
		this.subscriptions = subs;
	}
	
	//Methods
	public void rentPhysicalItem(PhysicalItem wantToRent) {
		//TODO: This is an oversimplification, will need real implementation at some point
		if(wantToRent != null) {
			rented.add(wantToRent); // They rent  the book
			wantToRent.setBorrower(this); // The book is now borrowed by them
			system.removeStock(wantToRent); // Book is now in borrowed
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
		//Remove from user can add to system. The swap it from the borrowed list to stock list
		item.setBorrower(null);
		this.rented.remove(item);
		system.addStock(item);
		system.removeBarrowed(item);
	}
	
	public void requestNewItem(Item item) {
		//Check to make sure it doesn't exist in library
	}
	
	
	//Setters
	public void subscribe(Item newsletter) {
		//TODO: oversimplification
		this.subscriptions.add(newsletter);
	}
	
	public void unSubscribe(Item newsletter) {
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
			this.email = validPassword;
		}
		else {
			System.out.print("Please input valid password");
		}
	}
	
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
	
	//Getters
	public ArrayList<Item> getRented(){
		return this.rented;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public ArrayList<Item> getSubscriptions(){
		return this.subscriptions;
	}
	
}
