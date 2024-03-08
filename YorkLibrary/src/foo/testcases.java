package foo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testcases {
	
	@Test
	void makePhysical() {
		PhysicalItem chair = new PhysicalItem();
		chair.setName("chair");
		chair.setId(1);
		chair.setBorrower(null);
		chair.setDisabled(false);
		chair.setPrice(0);
		
		assertTrue(chair.getBorrower() == null);
	}
	
	@Test 
	void makeOnline(){
		OnlineItem newsletter = new OnlineItem();
		
		newsletter.addSubscriber( new Student());
	}
	
	@Test
	//Management team adds two items, then the library has them, one person rents them and returns one of them
	void basic_Test() {
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();
		Student person = new Student();
		
		team.setSystem(system);
		
		PhysicalItem book1 = new PhysicalItem();
		book1.setId(1);
		book1.setDisabled(false);
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setId(2);
		book2.setDisabled(false);
		book2.setName("Second Book");
		
		team.addPhysicalItem(book1);
		team.addPhysicalItem(book2);
		
		//Eventually will need fixing when good passwords are supposed to be implemented
		person.setEmail("www@gmail");
		person.setPassword("1234");
		
		//Add person to Library System since they at minimum require an email and password
		//And a person requires an account to rent out books
		system.addUser(person);
		
		person.rentPhysicalItem(book1);
		person.rentPhysicalItem(book2);
	
		person.returnPhysicalItem(book1);
		
		assertTrue(person.getRented().size() == 1);
		assertTrue(person.getRented().get(0).getName() == "Second Book");
		
		assertTrue(system.getBorrowed().size() == 1);
		assertTrue(system.getStock().size() == 1);

		assertTrue(system.getBorrowed().get(0).getName() == "Second Book");
		assertTrue(system.getStock().get(0).getName() == "First Book");
	}
}
