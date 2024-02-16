package foo;

import java.util.ArrayList;

public class User {

	protected String email;
	protected String password;
	protected ArrayList<Item> rented;
	protected ArrayList<Item> subscriptions;
	
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
	public void rentPhysicalItem(Item wantToRent) {
		//TODO: This is an oversimplification, will need real implementation at some point
		rented.add(wantToRent);
	}
	
	public void loginAccount() {
		// No idea how to do this
	}
	
	public void openOnlineItem(Item wantToRead) {
		// Look at subscriptions and open the selected item
	}
	
	public void returnPhysicalItem(Item item) {
		//Remove from user can add to system. The swap it from the borrowed list to stock list
		this.rented.remove(item);
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
	
	public void returnRented(Item returning) {
		//TODO: oversimplification
		this.rented.remove(returning);
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
