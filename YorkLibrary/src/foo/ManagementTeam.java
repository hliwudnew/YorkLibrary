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
	
	public void disableItem(Item item) {
		
	}
	
	public void enableItem(Item item) {
		
	}
	
	public void verifyAccount(String username, String password) {
		
	}
	//Setters
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
}
