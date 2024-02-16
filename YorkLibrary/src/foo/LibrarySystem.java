package foo;

import java.util.ArrayList;

public class LibrarySystem {
	
	//Library System stuff
	ArrayList<Item> stock = new ArrayList<Item>();
	ArrayList<Item> borrowed = new ArrayList<Item>();
	
	public LibrarySystem() {
//		stock = new ArrayList<Item>();
//		borrowed = new ArrayList<Item>();
	}
	
	public LibrarySystem(ArrayList<Item> stock, ArrayList<Item> borrowed) {
		this.stock = stock;
		this.borrowed = borrowed;
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
		//Checks if the item was lent out, hence now being returned
		if(getBorrowed().contains(item)) {
			this.stock.add(item);
			this.borrowed.remove(item);
		}
		else {
			//New item TODO: this needs work and need to figure out how this would work with the Management Team since they add
			this.stock.add(item);
		}
	}
	public void removeStock(Item item) {
		this.stock.remove(item);
		//TODO: Add to borrower??
	}
	
	
	
	//Getters
	public ArrayList<Item> getStock(){
		return this.stock;
	}
	public ArrayList<Item> getBorrowed(){
		return this.borrowed;
	}
	
}
