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
//		chair.setDisabled(false);
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
//		book1.setDisabled(false);
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setId(2);
//		book2.setDisabled(false);
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
	
	@Test
	void testingState() {
		ItemStateContext status = new ItemStateContext();
		System.out.println(status.getState().getClass().toString().substring(10));
		status.setState(new Enabled());
		System.out.println(status.getState().getClass().toString().substring(10));
	}
	
	@Test
	void userFactoryTesting() {
		UserFactory buildUser = new UserFactory();
		
		User student = buildUser.getUser("Student");
		User fac = buildUser.getUser("Faculty");
		User nonFac = buildUser.getUser("NonFaculty");
		User visitor = buildUser.getUser("Visitor");
		User broke = buildUser.getUser("w");
		User broke2 = buildUser.getUser("");

		
		assertTrue(student.getClass().toString().equals(new Student().getClass().toString()));
		assertTrue(fac.getClass().toString().equals(new Faculty().getClass().toString()));
		assertTrue(nonFac.getClass().toString().equals(new Nonfaculty().getClass().toString()));
		assertTrue(visitor.getClass().toString().equals(new Visitor().getClass().toString()));
		assertTrue(broke == null);
		assertTrue(broke2 == null);
	}
	
	@Test
	void strategy() {
		PaymentContext payment = new PaymentContext(new GiftCardStrategy("2342345422"));
		assertTrue(payment.pay(10));

		payment = new PaymentContext(new CreditCardStrategy("First Last","1240 1400 1300 1100","2/24","111"));
		assertTrue(payment.pay(100));
		
		payment = new PaymentContext(new DebitCardStrategy("First Last","1290 1400 1300 1100","2/24","111"));
		assertTrue(payment.pay(50));
		
		payment = new PaymentContext(new PayPalStrategy("cool@gmail.com"));
		assertTrue(payment.pay(25));
		
		payment = new PaymentContext(new CreditCardStrategy("First Last","100","2/24","111"));
		assertFalse(payment.pay(100));
		
		payment = new PaymentContext(new PayPalStrategy("coolmail.com"));
		assertFalse(payment.pay(25));

	}
}
