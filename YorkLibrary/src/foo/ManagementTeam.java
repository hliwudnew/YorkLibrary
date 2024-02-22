package foo;

public class ManagementTeam {
	
	private LibrarySystem system = new LibrarySystem();
	
	public ManagementTeam() {
		
	}
	
	public ManagementTeam(LibrarySystem system) {
		this.system = system;
	}
	
	//Methods
	public void addItem(Item item) {
		//Adds new Item to the library stock
		system.addStock(item);
	}
	
	public void removeItem(Item item) {
		if(item != null) {
			if(!system.getStock().contains(item) && system.getBorrowed().contains(item)) {
				System.out.println("Cannot Delete Item it is being barrowed");
			}
			else {
				system.removeStock(item);
			}	
		}
	}
	
	public void disableItem(Item item) {
		item.setDisabled(true);
	}
	
	public void enableItem(Item item) {
		item.setDisabled(false);
	}
	
	public void verifyAccount(String username, String password) {
		
	}
	
	//Setters
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
}
