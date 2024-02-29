package foo;

import java.util.ArrayList;
import java.util.Collections;

public class LibrarySystem {
	
	//Library System stuff
	private ArrayList<Item> stock = new ArrayList<Item>();
	private ArrayList<Item> borrowed = new ArrayList<Item>();
	private ArrayList<User> userlist = new ArrayList<User>();
	
	public LibrarySystem() {
//		stock = new ArrayList<Item>();
//		borrowed = new ArrayList<Item>();
	}
	
	public LibrarySystem(ArrayList<Item> stock, ArrayList<Item> borrowed, ArrayList<User> users) {
		this.stock = stock;
		this.borrowed = borrowed;
		this.userlist = users;
	}
	
	//Methods
	public ArrayList<Item> checkDue(User user){
		//Finds the user and returns their list of when their rented items are due
		return null;
	}
	
	public void checkPaymentInfo() {
		
	}
	
	public boolean checkUserPrivileges(User user) {
		return false;
	}
	
	
	
	
	//Setters
	public void addStock(Item item) {
		//TODO: This should be done with the management team, some how?
		this.stock.add(item);
	}
	public void removeStock(Item item) {
		//TODO: This should be done with the management team, some how?
		this.stock.remove(item);
	}
	
	//TODO: Might scrap this idea since it might not actually be helpful
	public void addBarrowed(Item item) {
		this.borrowed.add(item);
	}
	public void removeBarrowed(Item item) {
		this.borrowed.remove(item);
	}
	
	//Awesome
	public void addUser(User user) {
		if(user != null) {
			//When the user is allowed/has email and password, they can be added to the system it establishes a connection with the user to the system
			this.userlist.add(user);
			user.setSystem(this);
		}
		else {
			throw new NullPointerException();
		}
	}
	
	//Getters
	public ArrayList<Item> getStock(){
		return this.stock;
	}
	public ArrayList<Item> getBorrowed(){
		return this.borrowed;
	}
	public ArrayList<User> getUsers(){
		return this.userlist;
	}
	public int getStockOf(Item item) {
		if(item != null) {
			//Counts how many times the item appears in the stock
			return Collections.frequency(stock, item);
		}
		else {
			return 0;
		}
		
	}
}
