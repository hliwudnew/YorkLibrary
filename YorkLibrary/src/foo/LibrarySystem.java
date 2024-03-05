package foo;

import java.util.ArrayList;
import java.util.Collections;

public class LibrarySystem {
	
	//Library System stuff
	private ArrayList<PhysicalItem> stock = new ArrayList<PhysicalItem>();
	private ArrayList<PhysicalItem> borrowed = new ArrayList<PhysicalItem>();
	private ArrayList<User> userlist = new ArrayList<User>();
	
	public LibrarySystem() {
		stock = new ArrayList<PhysicalItem>();
		borrowed = new ArrayList<PhysicalItem>();
		userlist = new ArrayList<User>();
	}
	
	public LibrarySystem(ArrayList<PhysicalItem> stock, ArrayList<PhysicalItem> borrowed, ArrayList<User> users) {
		this.stock = stock;
		this.borrowed = borrowed;
		this.userlist = users;
	}
	

	//Both below are used to populate the LibrarySystem with data from the CSVs
	public void setUsers(ArrayList<User> usersCSV) {
		this.userlist = usersCSV;
	}
	
	public void setItems(ArrayList<PhysicalItem> itemsCSV) {
		ArrayList<PhysicalItem> stock = new ArrayList<PhysicalItem>();
		ArrayList<PhysicalItem> barrowed = new ArrayList<PhysicalItem>();
		for(PhysicalItem I: itemsCSV) {
			if(I.getBorrower().equals("BLANK")) {
				stock.add(I);
			}
			else {
				barrowed.add(I);
			}
		}
		this.stock = stock;
		this.borrowed = barrowed;
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
	public void addStock(PhysicalItem item) {
		this.stock.add(item);
	}
	public void removeStock(PhysicalItem item) {
		this.stock.remove(item);
	}
	
	public void addBarrowed(PhysicalItem item) {
		this.borrowed.add(item);
	}
	public void removeBarrowed(PhysicalItem item) {
		this.borrowed.remove(item);
	}
	
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
	public ArrayList<PhysicalItem> getStock(){
		return this.stock;
	}
	public ArrayList<PhysicalItem> getBorrowed(){
		return this.borrowed;
	}
	public ArrayList<User> getUsers(){
		return this.userlist;
	}
	public User getUser(String email) {
		for(User u: this.userlist) {
			if(u.getEmail().equals(email)) {
				u.setSystem(this);
				return u;
			}
		}
		return null;
	}
	
	public PhysicalItem getItem(int id) {
		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>(this.stock);
		items.addAll(this.borrowed);
		for(PhysicalItem I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}
	
	public int getStockOf(PhysicalItem item) {
		if(item != null) {
			//Counts how many times the item appears in the stock
			return Collections.frequency(stock, item);
		}
		else {
			return 0;
		}
		
	}
}
