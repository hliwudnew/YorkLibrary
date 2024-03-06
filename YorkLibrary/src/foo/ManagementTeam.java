package foo;

import java.util.ArrayList;

public class ManagementTeam {
	
	private LibrarySystem system = new LibrarySystem();
	
	public ManagementTeam() {
		
	}
	
	public ManagementTeam(LibrarySystem system) {
		this.system = system;
	}
	
	//Methods
	public void addPhysicalItem(PhysicalItem item) {
		system.addStock(item);
	}
	
	public void addOnlineItem(OnlineItem item) {
		system.addSubOp(item);// Adding a new subscription entirely
	}
	
	public void removePhysicalItem(PhysicalItem item) {
		if(item != null) {
			if(!system.getStock().contains(item) && system.getBorrowed().contains(item)) {
				System.out.println("Cannot Delete Item it is being barrowed");
			}
			else {
				system.removeStock(item);
			}	
		}
	}
	
	public void removeOnlineItem(OnlineItem item) {
		System.out.println("I need to finish implementing this"); //TODO: finish
	}
	
	public void disableItem(Item item) {
		item.setDisabled(true);
	}
	
	public void disableItem(ArrayList<Item> items) {
		for(Item I: items) {
			I.setDisabled(true);
		}
	}
	
	public void enableItem(Item item) {
		item.setDisabled(false);
	}
	
	public void enableItem(ArrayList<Item> items) {
		for(Item I: items) {
			I.setDisabled(false);
		}
	}
	
	public void verifyAccount(String username, String password) {
		
	}
	
	//Setters
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
}
