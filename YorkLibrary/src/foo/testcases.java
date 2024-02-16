package foo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testcases {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	
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
		
		newsletter.addSubscriber(null);
	}
	
	@Test
	void basic_Test() {
		//Management team adds two items, then the library has them, one person rents them and returns one of them
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();
		Student person = new Student();
		
		team.setSystem(system);
		
		PhysicalItem book1 = new PhysicalItem();
		book1.setId(1);
		book1.setDisabled(false);
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book1.setId(2);
		book1.setDisabled(false);
		book1.setName("Second Book");
		
		//TODO: Currently stores the address for the item, this is an issue??
		team.addItem(book1);
		team.addItem(book2);
		
		//Eventually will need fixing when good passwords are supposed to be implemented
		person.setEmail("www@gmail");
		person.setPassword("1234");
		
		person.rentPhysicalItem(book1);
		person.rentPhysicalItem(book2);

		
		person.returnPhysicalItem(book1);
		
		assertTrue(person.rented.size() == 1);
		assertTrue(person.rented.get(0).getName() == "Second Book");
		
		assertTrue(system.borrowed.size() == 1);
		assertTrue(system.stock.size() == 1);

		assertTrue(system.borrowed.get(0).getName() == "Second Book");
		assertTrue(system.stock.get(0).getName() == "First Book");
	}
}
