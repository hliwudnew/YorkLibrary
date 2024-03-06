package foo;

import java.util.ArrayList;
import java.util.Collections;

public class LibrarySystem {
	
	//Library System stuff
	private ArrayList<Item> stock = new ArrayList<Item>();
	private ArrayList<Item> borrowed = new ArrayList<Item>();
	private ArrayList<User> userlist = new ArrayList<User>();
	private ArrayList<OnlineItem> subscriptions = new ArrayList<OnlineItem>(); //Holds who has subscriptions
	private ArrayList<OnlineItem> subOptions = new ArrayList<OnlineItem>(); // Holds the actual subscription
	
	
	public LibrarySystem() {
		stock = new ArrayList<Item>();
		borrowed = new ArrayList<Item>();
		userlist = new ArrayList<User>();
		subscriptions = new ArrayList<OnlineItem>();
		subOptions = new ArrayList<OnlineItem>();
	}
	
	public LibrarySystem(ArrayList<Item> stock, ArrayList<Item> borrowed, ArrayList<User> users,ArrayList<OnlineItem> subs,ArrayList<OnlineItem> options) {
		this.stock = stock;
		this.borrowed = borrowed;
		this.userlist = users;
		this.subscriptions = subs;
		this.subOptions = options;
	}
	

	//Both below are used to populate the LibrarySystem with data from the CSVs
	public void setUsers(ArrayList<User> usersCSV) {
		this.userlist = usersCSV;
	}
	
	public void setItems(ArrayList<PhysicalItem> itemsCSV) {
		ArrayList<Item> stock = new ArrayList<Item>();
		ArrayList<Item> barrowed = new ArrayList<Item>();
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
	
	public void setSubscriptions(ArrayList<OnlineItem> subsCSV) {
		this.subscriptions = subsCSV;
	}
	public void setSubOptions(ArrayList<OnlineItem> subOp) {
		this.subOptions = subOp;
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
		this.stock.add(item);
	}
	public void removeStock(Item item) {
		this.stock.remove(item);
	}
	
	public void addBarrowed(Item item) {
		this.borrowed.add(item);
	}
	public void removeBarrowed(Item item) {
		this.borrowed.remove(item);
	}
	public void addSub(OnlineItem sub) {
		this.subscriptions.add(sub);
	}
	public void removeSub(OnlineItem sub) {
		this.subscriptions.remove(sub);
	}
	public void addSubOp(OnlineItem op) {
		this.subOptions.add(op);
	}
	public void removeSubOp(Item i) {
		this.subOptions.remove(i);
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
	public ArrayList<Item> getStock(){
		return this.stock;
	}
	public ArrayList<Item> getBorrowed(){
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
	
	public Item getPhysicalItem(int id) {
		ArrayList<Item> items = new ArrayList<Item>(this.stock);
		items.addAll(this.borrowed);
		for(Item I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}
	
	public ArrayList<Item> getItemAll(int id) {
		ArrayList<Item> holder = new ArrayList<Item>();
		
		ArrayList<Item> items = new ArrayList<Item>(this.stock);
		items.addAll(this.borrowed);
		items.addAll(this.subscriptions);
		items.addAll(this.subOptions);
		for(Item I: items) {
			if(I.getId() == id) {
				holder.add(I);
			}
		}
		return holder;
	}
	
	public Item getOnlineItem(int id) {
		ArrayList<Item> items = new ArrayList<Item>(this.subscriptions);
		items.addAll(this.subOptions);
		for(Item I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
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
	
	public Item getSubOp(int id) {
		for(Item I: this.subOptions) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}
	
	public OnlineItem getSub(int id, String email) {
		for(OnlineItem I: this.subscriptions) {
			if(I.getId() == id && I.getSubscriber().equals(email)) {
				return I;
			}
		}
		return null;
	}
	
	public ArrayList<OnlineItem> getSubs(){
		return this.subscriptions;
	}
	
	public ArrayList<OnlineItem> getSubOps(){
		return this.subOptions;
	}
}
