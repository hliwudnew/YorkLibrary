package testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import foo.CSVReader;
import foo.CreditCardStrategy;
import foo.DebitCardStrategy;
import foo.Disabled;
import foo.Enabled;
import foo.Faculty;
import foo.GiftCardStrategy;
import foo.Item;
import foo.ItemStateContext;
import foo.LibrarySystem;
import foo.ManagementTeam;
import foo.Nonfaculty;
import foo.OnlineItem;
import foo.PayPalStrategy;
import foo.PaymentContext;
import foo.PhysicalItem;
import foo.Student;
import foo.User;
import foo.UserFactory;
import foo.Visitor;

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
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(book1.getNextValidId()); //TODO: Idk how ID's work anymore so feel free to fix and or change this - Gabriel 
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(book1.getNextValidId()); //TODO: Idk how ID's work anymore so feel free to fix and or change this - Gabriel 
		book2.setName("Second Book");
		
		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system
		
		//Eventually will need fixing when good passwords are supposed to be implemented
		person.setEmail("www@gmail");
		person.setPassword("1234");
		
		//Add person to Library System since they at minimum require an email and password
		//And a person requires an account to rent out books
		system.addUser(person);
		
		person.rentPhysicalItem(book1); //User rents the original copies of these items
		person.rentPhysicalItem(book2);
	
		person.returnPhysicalItem(book1); // Return first book
		
		assertTrue(person.getRented().size() == 1);
		assertTrue(person.getRented().get(0).getName() == "Second Book");
		
		assertTrue(system.getBorrowed().size() == 1);
		assertTrue(system.getStock().size() == 39); // bc 2 item * 20 = 40 -1 bc 1 is borrowed = 39

		assertTrue(system.getBorrowed().get(0).getName() == "Second Book");
		
		int count = 0;
		for(Item I: system.getStock()) {
			if(I.getName().equals(book1.getName())) {
				count++;
			}
		}
		
		assertTrue(count == 20); //Checks to make sure all copies of First Book are in the system, meaning the rented one was returned
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
		User nonFac = buildUser.getUser("Nonfaculty");
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
	
	@Test
	void CSVReaderModificationTests() {
		LibrarySystem system = CSVReader.dowloadData(new LibrarySystem()); // Downloads data from the CSV files for the library system
		
		//Checks if the system actually got populated
		assertTrue(system.getStock().size() > 0);
		assertTrue(system.getBorrowed().size() > 0);
		assertTrue(system.getUsers().size() > 0);
		assertTrue(system.getCourses().size() > 0);
		assertTrue(system.getFaculty().size() > 0);
		assertTrue(system.getStudents().size() > 0);
		assertTrue(system.getSubs().size() >0);
		
		OnlineItem newYorkTimes = new OnlineItem();
		
		//Grabs the New York Times subscription
		for(OnlineItem I: system.getSubs()) {
			if(I.getName().equals("New York Times")) {
				newYorkTimes = I;
				break;
			}
		}
		
		
		PhysicalItem math = new PhysicalItem();
		//Grabs Math & Stats Book
		for(Item I: system.getStock()) {
			if(I.getName().equals("Math & Stats")) {
				math = (PhysicalItem) I;
				break;
			}
		}
		
		//We are going to modify it save it and check it
		math.setStatus(new ItemStateContext(new Disabled()));
		newYorkTimes.setStatus(new ItemStateContext(new Disabled())); // Disabled online subscription
		
		//Saves the system
		CSVReader.upload(system);
		
		//Re download the new data
		system = CSVReader.dowloadData(new LibrarySystem());
		
		OnlineItem updatedNewYork = new OnlineItem();
		//Grabs the New York Times subscription
		for(OnlineItem I: system.getSubs()) {
			if(I.getName().equals("New York Times")) {
				updatedNewYork = I;
				break;
			}
		}
		
		PhysicalItem updatedMath = new PhysicalItem();
		//Grabs Math & Stats Book
		for(Item I: system.getStock()) {
			if(I.getName().equals("Math & Stats")) {
				updatedMath = (PhysicalItem) I;
				break;
			}
		}
		
		
		//Checks to see if it saved our previous change
		assertTrue(updatedMath.getStatus().getState().getClass().equals(new Disabled().getClass()));
		assertTrue(updatedNewYork.getStatus().getState().getClass().equals(new Disabled().getClass()));
		
		//Going to set it back to original so this test can re run and not get stuck!
		updatedNewYork.setStatus(new ItemStateContext(new Enabled()));
		updatedMath.setStatus(new ItemStateContext(new Enabled()));
		
		//Saves the system
		CSVReader.upload(system);
	}
	
	@Test
	void CSVReaderAccountTests() {
		
		try {
			//Email checking in CSVs
			assertTrue(CSVReader.checkEmail("Fac2@gmail.com"));
			assertFalse(CSVReader.checkEmail("ThisEmailShouldNotBeInHereEverBecasueTesting@hotmail.com"));
			
			//Email checking in CSVs for pending verification accounts
			assertFalse(CSVReader.checkEmailPending("Fac2@gmail.com"));
			assertTrue(CSVReader.checkEmailPending("pending@gmail.com"));
			
			//Login and Failed login
			assertTrue(CSVReader.loginData("Fac2@gmail.com", "Fac2@"));
			assertFalse(CSVReader.loginData("Fac2@gmail.com", "Password123"));
			
			ArrayList<User> pendingAccounts = CSVReader.loadPendingAccounts();
			
			assertTrue(pendingAccounts.size() > 0); // Some might be added by other testcases, so I cant directly just test 1
			
			boolean exists= false;
			//Making sure that the pending account actually exists in the CSV
			for(User u: pendingAccounts) {
				if(u.getEmail().equals("pending@gmail.com")) {
					u.setPassword("newPassword#21"); //Using this to test savePending
					exists = true;
				}
			}
			assertTrue(exists);
			// Saves changes
			CSVReader.savePendingAccounts(pendingAccounts);
			
			pendingAccounts = CSVReader.loadPendingAccounts();
			boolean updated = false;
			//Making sure that the pending account actually exists in the CSV
			for(User u: pendingAccounts) {
				if(u.getEmail().equals("pending@gmail.com")) {
					//Check for our update
					if(u.getPassword().equals("newPassword#21")) {
						updated = true;
						u.setPassword("pend2@345314wwSS");//Reset back to old password to test can rerun
					}
				}
			}
			assertTrue(updated);
			
			// Saves changes
			CSVReader.savePendingAccounts(pendingAccounts);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
