package testcases;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import foo.Add;
import foo.CSVReader;
import foo.Cart;
import foo.Checkout;
import foo.Clear;
import foo.Course;
import foo.CreditCardStrategy;
import foo.DebitCardStrategy;
import foo.Disabled;
import foo.Enabled;
import foo.Faculty;
import foo.GiftCardStrategy;
import foo.ICartCommand1;
import foo.ICartCommand2;
import foo.Item;
import foo.ItemStateContext;
import foo.LibrarySystem;
import foo.ManagementTeam;
import foo.Menu;
import foo.Nonfaculty;
import foo.OnlineItem;
import foo.PayPalStrategy;
import foo.PaymentContext;
import foo.PhysicalItem;
import foo.Remove;
import foo.Student;
import foo.User;
import foo.UserFactory;
import foo.Visitor;

class testcases {
	
	//Tests PhysicalItem methods on their own
	@Test
	void testPhysical01() {
		PhysicalItem book = new PhysicalItem();
		//Testing setter methods

		Date date = new Date();
		date.setTime(1711000000);

		book.setName("Chair Book");
		book.setId(1);
		book.setBorrower(null);
		book.setStatus(new ItemStateContext(new Disabled()));
		book.setPrice(100);
		book.setLost(false);
		book.setDueDate(date);
		book.setFee(10);
		book.setDiscount(10);


		assertTrue(book.getBorrower() == null);
		assertTrue(book.getId() == 1);
		assertTrue(book.getDiscount() == 10);
		assertTrue(book.getPrice() == 100);
		assertTrue(book.getDiscount() == 10);
		assertTrue(book.getFee() == 10);
		assertFalse(book.isLost());
		assertTrue(book.getStatus().getState().getClass().equals(new Disabled().getClass()));
		assertTrue(book.getDueStatus().equals("Overdue"));

	}
	//Tests due dates of items
	@Test
	void testPhysical02() { 

		Date futureDate = new Date();
		PhysicalItem futureBook = new PhysicalItem();
		long future = 1735707599000l;
		futureDate.setTime(future);
		futureBook.setDueDate(futureDate);
		
		assertTrue(futureBook.getDueStatus().equals("Due in >24 Hours"));	
	}
	
	//Tests due dates for an item due within the next 24 hours
	@Test
	void testPhysical03() {
		Date presentDate = new Date();
		PhysicalItem presentBook = new PhysicalItem();
		long eightHoursMinusPresent = presentDate.getTime() + 28800000l;
		presentDate.setTime(eightHoursMinusPresent);
		presentBook.setDueDate(presentDate);
		
		assertTrue(presentBook.getDueStatus().equals("Due in: 8 Hours"));
	}
	
	//tests due date for an overdue item
	@Test
	void testPhysical04() {

		Date pastDate = new Date();
		PhysicalItem pastBook = new PhysicalItem();
		long past = 1704110400000l;
		pastDate.setTime(past);
		pastBook.setDueDate(pastDate);
		
		assertTrue(pastBook.getDueStatus().equals("Overdue"));
	}

	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical05() {

		Date presentDate = new Date();
		Date dueDate = new Date();

		long presentMinusOneDay = presentDate.getTime() - 86400000l;
		dueDate.setTime(presentMinusOneDay);

		PhysicalItem book = new PhysicalItem(dueDate, "Homer Simpson", 10.95);

		book.setName("Calculus and Vectors");
		book.setId(1);
		book.setPrice(19.99);

		assertTrue(book.getName().equals("Calculus and Vectors"));
		assertTrue(book.getBorrower().equals("Homer Simpson"));
		assertTrue(book.getId() == 1);
		assertTrue(book.getPrice() == 19.99);
		assertTrue(book.calculateFee() == 0.50);

	}
	
	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical06() {
		
		Date presentDate = new Date();
		Date dueDate = new Date();

		long presentMinusOneDay = presentDate.getTime() - 86400000l;
		dueDate.setTime(presentMinusOneDay);

		PhysicalItem book = new PhysicalItem(dueDate, "Person X", 10.95);

		book.setName("Robotics");
		book.setId(2);
		book.setPrice(30.00);

		assertTrue(book.getName().equals("Robotics"));
		assertTrue(book.getBorrower().equals("Person X"));
		assertTrue(book.getId() == 2);
		assertTrue(book.getPrice() == 30.00);
		assertTrue(book.calculateFee() == 0.50);
		assertTrue(book.daysOverdue() == 1);
	}

	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical07() {
		
		Date presentDate = new Date();
		Date dueDate = new Date();

		long presentPlusOneDay = presentDate.getTime() + 86400000l;
		dueDate.setTime(presentPlusOneDay);

		PhysicalItem book = new PhysicalItem(dueDate, "Lebron James", 23.00);

		book.setName("Basketball");
		book.setId(4);
		book.setPrice(24.00);

		assertTrue(book.getName().equals("Basketball"));
		assertTrue(book.getBorrower().equals("Lebron James"));
		assertTrue(book.getId() == 4);
		assertTrue(book.getPrice() == 24.00);
		assertTrue(book.calculateFee() == 0.00);
		assertTrue(book.daysOverdue() == 0);

	}
	
	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical08() {		
		Date dueDate = new Date();

		PhysicalItem book = new PhysicalItem(dueDate, "Kylian Mbappe", 19.98);

		book.setName("OOP");
		book.setId(5);
		book.setPrice(20.00);

		assertTrue(book.getName().equals("OOP"));
		assertTrue(book.getBorrower().equals("Kylian Mbappe"));
		assertTrue(book.getId() == 5);
		assertTrue(book.getPrice() == 20.00);
		assertTrue(book.calculateFee() == 0.00);
		assertTrue(book.daysOverdue() == 0);

	}
	
	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical09() {
		
		Date presentDate = new Date();
		Date dueDate = new Date();

		long twodays = presentDate.getTime() - 172800000l;
		dueDate.setTime(twodays);

		PhysicalItem book = new PhysicalItem(dueDate, "Michael Scott", 10.95);

		book.setName("General Relativity");
		book.setId(1);
		book.setPrice(10.00);

		assertTrue(book.getName().equals("General Relativity"));
		assertTrue(book.getBorrower().equals("Michael Scott"));
		assertTrue(book.getId() == 1);
		assertTrue(book.getPrice() == 10.00);
		assertTrue(book.calculateFee() == 1.00);
		assertTrue(book.daysOverdue() == 2);

	}
	
	//a more in depth test of due date involving a borrower
	@Test
	void testPhysical10() {		
		Date dueDate = new Date();

		PhysicalItem book = new PhysicalItem(dueDate, "Thor", 10.95);

		book.setName("Electricty 101");
		book.setId(2);
		book.setPrice(30.00);

		assertTrue(book.getName().equals("Electricty 101"));
		assertTrue(book.getBorrower().equals("Thor"));
		assertTrue(book.getId() == 2);
		assertTrue(book.getPrice() == 30.00);
		assertTrue(book.calculateFee() == 0.00);
		assertTrue(book.daysOverdue() == 0);

	}
	
	//testing basic functionality of OnlineItem on its own
	@Test 
	void OnlineItem_Test1(){
		OnlineItem newsletter = new OnlineItem();
		//Testing setter methods
		newsletter.setId(1);
		newsletter.setName("New York Times");
		newsletter.setLink("https://www.nytimes.com/ca/");
		newsletter.setPrice(0.0);
		newsletter.setStatus(new ItemStateContext(new Enabled()));
		newsletter.setSubscribers(new ArrayList<User>());
		newsletter.addSubscriber(new Student());

		assertFalse(newsletter.getSubscribers().isEmpty());
		assertTrue(newsletter.getName().equals("New York Times"));
		assertTrue(newsletter.getStatus().getState().getClass().equals(new Enabled().getClass()));
	}
	
	//testing basic functionality of OnlineItem on its own
	@Test 
	void OnlineItem_Test2(){
		OnlineItem newsletter = new OnlineItem();
		//Testing setter methods
		newsletter.setId(1);
		newsletter.setName("BBC");
		newsletter.setLink("https://www.bbc.com/news");
		newsletter.setPrice(0.0);
		newsletter.setStatus(new ItemStateContext(new Disabled()));
		newsletter.setSubscribers(new ArrayList<User>());
		newsletter.addSubscriber(new Student());

		assertFalse(newsletter.getSubscribers().isEmpty());
		assertTrue(newsletter.getName().equals("BBC"));
		assertTrue(newsletter.getStatus().getState().getClass().equals(new Disabled().getClass()));
	}
	
	//testing OnlineItem with a user subscribed to it
	@Test 
	void OnlineItem_Test3(){
		OnlineItem newsletter = new OnlineItem();
		//Testing setter methods
		newsletter.setId(1);
		newsletter.setName("BBC");
		newsletter.setLink("https://www.bbc.com/news");
		newsletter.setPrice(0.0);
		newsletter.setStatus(new ItemStateContext(new Disabled()));
		newsletter.setSubscribers(new ArrayList<User>());

		assertTrue(newsletter.getName().equals("BBC"));
		assertTrue(newsletter.getStatus().getState().getClass().equals(new Disabled().getClass()));
		
		Student student = new Student();
		
		student.subscribe(newsletter);//Disabled so wont subscribe
		assertTrue(student.getSubscriptions().isEmpty());
	}
	
	//testing OnlineItem with a user subscribed to it
	@Test 
	void OnlineItem_Test4(){
		OnlineItem newsletter = new OnlineItem();
		//Testing setter methods
		newsletter.setId(1);
		newsletter.setName("BBC");
		newsletter.setLink("https://www.bbc.com/news");
		newsletter.setPrice(25.0);
		newsletter.setStatus(new ItemStateContext(new Enabled()));
		newsletter.setSubscribers(new ArrayList<User>());

		assertTrue(newsletter.getName().equals("BBC"));
		assertTrue(newsletter.getStatus().getState().getClass().equals(new Enabled().getClass()));
		
		Student student = new Student();
		
		student.subscribe(newsletter);
		assertFalse(student.getSubscriptions().isEmpty());
		assertEquals(1, student.getSubscriptions().size());
		assertEquals(newsletter,student.getSubscriptions().get(0));
		assertEquals(newsletter.getName(),student.getSubscriptions().get(0).getName());
	}
	
	//testing OnlineItem with a several users of different types subscribing to it
	@Test 
	void OnlineItem_Test5(){
		OnlineItem newsletter = new OnlineItem();
		//Testing setter methods
		newsletter.setId(1);
		newsletter.setName("CBC");
		newsletter.setLink("https://www.cbc.ca/");
		newsletter.setPrice(100.0);
		newsletter.setStatus(new ItemStateContext(new Enabled()));
		newsletter.setSubscribers(new ArrayList<User>());

		
		Student student = new Student();
		Faculty fac = new Faculty();
		Nonfaculty nfac = new Nonfaculty();
		Visitor visitor = new Visitor();
		
		student.subscribe(newsletter);
		fac.subscribe(newsletter);
		nfac.subscribe(newsletter);
		visitor.subscribe(newsletter);
		
		assertEquals(4, newsletter.getSubscribers().size());
		assertTrue(student.getSubscriptions().contains(newsletter));
		assertTrue(fac.getSubscriptions().contains(newsletter));
		assertTrue(nfac.getSubscriptions().contains(newsletter));
		assertTrue(visitor.getSubscriptions().contains(newsletter));
	}
	
	
	//testing OnlineItem with a several users of different types subscribing to it
	// and users subscribed to multiple items at once
	@Test 
	void OnlineItem_Test6(){
		OnlineItem cbc = new OnlineItem();
		//Testing setter methods
		cbc.setId(1);
		cbc.setName("CBC");
		cbc.setLink("https://www.cbc.ca/");
		cbc.setPrice(100.0);
		cbc.setStatus(new ItemStateContext(new Enabled()));
		cbc.setSubscribers(new ArrayList<User>());

		OnlineItem bbc = new OnlineItem();
		//Testing setter methods
		bbc.setId(2);
		bbc.setName("BBC");
		bbc.setLink("https://www.bbc.com/news");
		bbc.setPrice(25.0);
		bbc.setStatus(new ItemStateContext(new Enabled()));
		bbc.setSubscribers(new ArrayList<User>());
		
		Student student = new Student();
		Faculty fac = new Faculty();
		Nonfaculty nfac = new Nonfaculty();
		Visitor visitor = new Visitor();
		
		student.subscribe(bbc);
		fac.subscribe(bbc);
		nfac.subscribe(bbc);
		visitor.subscribe(bbc);
		
		student.subscribe(cbc);
		fac.subscribe(cbc);
		nfac.subscribe(cbc);
		visitor.subscribe(cbc);
		
		assertEquals(2, student.getSubscriptions().size());
		assertEquals(2, fac.getSubscriptions().size());
		assertEquals(2, nfac.getSubscriptions().size());
		assertEquals(2, visitor.getSubscriptions().size());
		assertEquals(4, cbc.getSubscribers().size());
		assertEquals(4, bbc.getSubscribers().size());
	}
	
	//testing OnlineItem with a several users of different types subscribing to it
	// and testing unsubscribe functionality
	@Test 
	void OnlineItem_Test7(){
		OnlineItem cbc = new OnlineItem();
		//Testing setter methods
		cbc.setId(1);
		cbc.setName("CBC");
		cbc.setLink("https://www.cbc.ca/");
		cbc.setPrice(100.0);
		cbc.setStatus(new ItemStateContext(new Enabled()));
		cbc.setSubscribers(new ArrayList<User>());
		
		Student student = new Student();
		Faculty fac = new Faculty();
		Nonfaculty nfac = new Nonfaculty();
		Visitor visitor = new Visitor();
		
		student.subscribe(cbc);
		fac.subscribe(cbc);
		nfac.subscribe(cbc);
		visitor.subscribe(cbc);
		
		student.unSubscribe(cbc);
		fac.unSubscribe(cbc);
		nfac.unSubscribe(cbc);
		visitor.unSubscribe(cbc);
		
		assertEquals(0, student.getSubscriptions().size());
		assertEquals(0, fac.getSubscriptions().size());
		assertEquals(0, nfac.getSubscriptions().size());
		assertEquals(0, visitor.getSubscriptions().size());
		assertEquals(0, cbc.getSubscribers().size());
	}
	
	//testing OnlineItem with a several users of different types subscribing to it
	// and testing unsubscribe functionality
	@Test 
	void OnlineItem_Test8(){
		OnlineItem cbc = new OnlineItem();
		//Testing setter methods
		cbc.setId(1);
		cbc.setName("CBC");
		cbc.setLink("https://www.cbc.ca/");
		cbc.setPrice(100.0);
		cbc.setStatus(new ItemStateContext(new Enabled()));
		cbc.setSubscribers(new ArrayList<User>());

		OnlineItem bbc = new OnlineItem();
		//Testing setter methods
		bbc.setId(2);
		bbc.setName("BBC");
		bbc.setLink("https://www.bbc.com/news");
		bbc.setPrice(25.0);
		bbc.setStatus(new ItemStateContext(new Enabled()));
		bbc.setSubscribers(new ArrayList<User>());
		
		Student student = new Student();
		Faculty fac = new Faculty();
		Nonfaculty nfac = new Nonfaculty();
		Visitor visitor = new Visitor();
		
		student.subscribe(bbc);
		fac.subscribe(bbc);
		nfac.subscribe(bbc);
		visitor.subscribe(bbc);
		
		student.subscribe(cbc);
		fac.subscribe(cbc);
		nfac.subscribe(cbc);
		visitor.subscribe(cbc);
		
		student.unSubscribe(cbc);
		fac.unSubscribe(cbc);
		nfac.unSubscribe(cbc);
		visitor.unSubscribe(cbc);
		
		assertEquals(1, student.getSubscriptions().size());
		assertEquals(1, fac.getSubscriptions().size());
		assertEquals(1, nfac.getSubscriptions().size());
		assertEquals(1, visitor.getSubscriptions().size());
		assertEquals(0, cbc.getSubscribers().size());
		assertEquals(4, bbc.getSubscribers().size());
		assertFalse(student.getSubscriptions().contains(cbc));
		assertFalse(fac.getSubscriptions().contains(cbc));
		assertFalse(nfac.getSubscriptions().contains(cbc));
		assertFalse(visitor.getSubscriptions().contains(cbc));
	}
	
	//test student subscribing to item when enabled, then testing that
	// student can no longer subscribe if item is disabled
	@Test 
	void OnlineItem_Test9(){
		OnlineItem cbc = new OnlineItem();
		//Testing setter methods
		cbc.setId(1);
		cbc.setName("CBC");
		cbc.setLink("https://www.cbc.ca/");
		cbc.setPrice(100.0);
		cbc.setStatus(new ItemStateContext(new Enabled()));
		cbc.setSubscribers(new ArrayList<User>());
		
		Student student = new Student();
		
		student.subscribe(cbc);
		assertTrue(student.getSubscriptions().contains(cbc));
		
		student.unSubscribe(cbc);
		assertFalse(student.getSubscriptions().contains(cbc));
		
		cbc.setStatus(new ItemStateContext(new Disabled()));
		
		student.subscribe(cbc);
		assertFalse(student.getSubscriptions().contains(cbc));
	}
	
	//testing subscribe and unsubscribe with multiple user types
	@Test 
	void OnlineItem_Test10(){
		OnlineItem cbc = new OnlineItem();
		//Testing setter methods
		cbc.setId(1);
		cbc.setName("CBC");
		cbc.setLink("https://www.cbc.ca/");
		cbc.setPrice(100.0);
		cbc.setStatus(new ItemStateContext(new Enabled()));
		cbc.setSubscribers(new ArrayList<User>());
		
		Student student = new Student();
		Faculty fac = new Faculty();
		Nonfaculty nfac = new Nonfaculty();
		Visitor visitor = new Visitor();
		
		student.subscribe(cbc);
		fac.subscribe(cbc);
		nfac.subscribe(cbc);
		visitor.subscribe(cbc);
		assertEquals(4, cbc.getSubscribers().size());
		
		student.unSubscribe(cbc);
		fac.unSubscribe(cbc);
		nfac.unSubscribe(cbc);
		visitor.unSubscribe(cbc);
		assertEquals(0,cbc.getSubscribers().size());
		
		cbc.setStatus(new ItemStateContext(new Disabled()));
		
		student.subscribe(cbc);
		fac.subscribe(cbc);
		nfac.subscribe(cbc);
		visitor.subscribe(cbc);
		assertEquals(0,cbc.getSubscribers().size());
	}


	@Test
	//Management team adds two items, then the library has them, one person rents them and returns one of them
	void librarysystem_Test1() {
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();
		Student person = new Student();

		team.setSystem(system);

		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");

		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
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
	//in depth test that tests: management team options, creation of librarySystem, adding several items to the system
	// and testing users renting/subscribing the items that are added
	@Test
	void librarysystem_Test2() { 
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();

		Student student = new Student();
		Faculty faculty = new Faculty();
		Nonfaculty nonfaculty = new Nonfaculty();
		Visitor visitor = new Visitor();

		team.setSystem(system);// Assigns the management team to the library system

		//Physical Items
		PhysicalItem bookS = new PhysicalItem();
		bookS.setStatus(new ItemStateContext(new Enabled()));
		bookS.setId(PhysicalItem.getNextValidId());
		bookS.setName("First Book");

		PhysicalItem bookF = new PhysicalItem();
		bookF.setStatus(new ItemStateContext(new Enabled()));
		bookF.setId(PhysicalItem.getNextValidId());
		bookF.setName("Second Book");

		PhysicalItem bookN = new PhysicalItem();
		bookN.setStatus(new ItemStateContext(new Enabled()));
		bookN.setId(PhysicalItem.getNextValidId());
		bookN.setName("Third Book");

		PhysicalItem bookV = new PhysicalItem();
		bookV.setStatus(new ItemStateContext(new Enabled()));
		bookV.setId(PhysicalItem.getNextValidId());
		bookV.setName("Fourth Book");

		PhysicalItem bookD = new PhysicalItem();
		bookD.setStatus(new ItemStateContext(new Disabled()));
		bookD.setId(PhysicalItem.getNextValidId());
		bookD.setName("Disabled Book");

		//OnlineItems
		OnlineItem newYorkTimes = new OnlineItem();
		newYorkTimes.setId(1000);
		newYorkTimes.setName("New York Times");
		newYorkTimes.setStatus(new ItemStateContext(new Enabled()));
		newYorkTimes.setLink("https://www.nytimes.com/ca/");

		OnlineItem bbc = new OnlineItem();
		bbc.setId(1000);
		bbc.setName("British Broadcasting Central");
		bbc.setStatus(new ItemStateContext(new Enabled()));
		bbc.setLink("https://www.bbc.com/news");

		//Adding to system
		team.addPhysicalItem(bookS); // Makes 20 copies of the item into the system
		team.addPhysicalItem(bookF); // Makes 20 copies of the item into the system
		team.addPhysicalItem(bookN);
		team.addPhysicalItem(bookV);
		team.addPhysicalItem(bookD);

		team.addOnlineItem(newYorkTimes);
		team.addOnlineItem(bbc);

		//Eventually will need fixing when good passwords are supposed to be implemented
		student.setEmail("stuD@gmail");
		student.setPassword("1234Wdda@#$@");
		faculty.setEmail("facW@gmail");
		faculty.setPassword("1234GHWUhh@$i");
		nonfaculty.setEmail("NON@gmail");
		nonfaculty.setPassword("1234GHWUhh@$i");
		visitor.setEmail("VISIT@gmail");
		visitor.setPassword("1234GHWUhh@$i");



		//Add them to Library System since they at minimum require an email and password
		//And a person requires an account to rent out books
		system.addUser(student);
		system.addUser(faculty);
		system.addUser(nonfaculty);
		system.addUser(visitor);

		//Rent items
		student.rentPhysicalItem(bookS); //User rents the original copies of these items
		faculty.rentPhysicalItem(bookF);
		nonfaculty.rentPhysicalItem(bookN);
		visitor.rentPhysicalItem(bookV);

		//Subscribe to online newsletters
		student.subscribe(newYorkTimes);
		student.subscribe(bbc);

		faculty.subscribe(newYorkTimes);

		nonfaculty.subscribe(bbc);

		//Checking item additions
		int count = 0;
		for(Item I: system.getStock()) {
			if(I.getName().equals(bookS.getName())) {
				count++;
			}
		}
		for(Item I: system.getBorrowed()) {
			if(I.getName().equals(bookS.getName())) {
				count++;
			}
		}

		assertTrue(count == 20); //Checks to make sure 20 copies are made
		assertEquals(96, system.getStock().size()); // 5 original items * 20 = 100 - 4 rented copies, 96
		assertEquals(4, system.getBorrowed().size());// 4 barrowed copies
		assertEquals(2, system.getSubs().size());// Two subscriptions available at this time
		assertEquals(2, newYorkTimes.getSubscribers().size());
		assertEquals(2, bbc.getSubscribers().size());
		assertEquals(2, student.getSubscriptions().size());
		assertEquals(1, faculty.getSubscriptions().size());
		assertEquals(1, nonfaculty.getSubscriptions().size());
		assertTrue(visitor.getSubscriptions().isEmpty());
	}
	
	//tests library system with users and faculty being added to a course and adding a textbook 
	// to that course
	@Test
	void librarysystem_Test3() {
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();
		Student person = new Student();
		Faculty teacher = new Faculty();

		Course eecs3311 = new Course();

		team.setSystem(system);

		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");

		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
		book2.setName("Second Book");

		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system

		person.setEmail("ww@gmail");
		person.setPassword("1234GHWUhh@$i");

		teacher.setEmail("ww2@gmail");
		teacher.setPassword("1234GHWUhh@$i");

		system.addUser(person);
		system.addUser(teacher);

		//Add course
		eecs3311.setName("Software Design");
		eecs3311.setCode("EECS3311");

		eecs3311.addStudent(person);
		eecs3311.addFaculty(teacher);
		eecs3311.addTextBook(book1);
		eecs3311.addTextBook(book2);

		person.addCourse(eecs3311);
		teacher.addCourse(eecs3311);

		system.addCourse(eecs3311);

		assertEquals(1,system.getCourses().size());
		assertEquals(1,system.getFaculty().size());
		assertEquals(1,system.getStudents().size());
		assertEquals(2,system.getCourseByCode("EECS3311").getTextBooks().size());
		assertEquals(1,teacher.getCourses().size());
		assertEquals(1,person.getCourses().size());
		assertEquals(system.getCourseByCode("EECS3311"),teacher.getCourses().get(0));
		assertEquals(system.getCourseByCode("EECS3311"),person.getCourses().get(0));
		assertTrue(eecs3311.getTextBooks().contains(book1));
		assertTrue(eecs3311.getTextBooks().contains(book2));

	}
	
	@Test
	void librarysystem_Test4() { //make sure overloaded constructor is working
		ArrayList<Item> stock = new ArrayList<>();
		ArrayList<Item> borrowed = new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		ArrayList<OnlineItem> subs = new ArrayList<>();
		ArrayList<Course> courses = new ArrayList<>();

		// Add some dummy data
		stock.add(new PhysicalItem());
		borrowed.add(new PhysicalItem());
		users.add(new Student());
		subs.add(new OnlineItem());
		courses.add(new Course());

		LibrarySystem lib = new LibrarySystem(stock, borrowed, users, subs, courses);
		assertNotNull(lib);
		assertEquals(1, lib.getStock().size());
		assertEquals(1, lib.getBorrowed().size());
		assertEquals(1, lib.getUsers().size());
		assertEquals(1, lib.getSubs().size());
		assertEquals(1, lib.getCourses().size());
	}

	@Test
	void librarysystem_Test5() { //make sure system throws exception when user is null
		try {
			LibrarySystem lib = new LibrarySystem();
			lib.addUser(null);
			fail("Expected a NullPointerException");
		} 
		catch (NullPointerException e) {
			
		}
	}
	
	//testing system with several null and invalid objects to test that they are
	//handled appropriately
	@Test
	void librarysystem_Test6() {
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem(new ArrayList<Item>(), new ArrayList<Item>(), new ArrayList<User>(), new ArrayList<OnlineItem>(), new ArrayList<Course>());
		Student person = new Student();
		Faculty teacher = new Faculty();
		Visitor empty = null;

		Course eecs3311 = new Course();
		Course notInSystem = new Course();
		
		team.setSystem(system);
		
		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
		book2.setName("Second Book");
		
		PhysicalItem book3 = new PhysicalItem();
		book3.setStatus(new ItemStateContext(new Enabled()));
		book3.setId(PhysicalItem.getNextValidId());
		book3.setName("DOESNT EXISTS");
		
		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system
		
		person.setEmail("ww@gmail");
		person.setPassword("1234GHWUhh@$i");
		
		teacher.setEmail("ww2@gmail");
		teacher.setPassword("1234GHWUhh@$i");
		
		system.addUser(person);
		system.addUser(teacher);
		try {
			system.addUser(empty);
			fail();
		}
		catch(NullPointerException e) {
			
		}
		
		//Add course
		eecs3311.setName("");// wont work because its empty hence we will check result
		eecs3311.setCode("EECS3311");
		
		eecs3311.addStudent(person);
		eecs3311.addFaculty(teacher);
		eecs3311.addTextBook(book1);
		eecs3311.addTextBook(book2);
		
		person.addCourse(eecs3311);
		teacher.addCourse(eecs3311);

		system.addCourse(eecs3311);
		
		//Incorrect values testing
		assertFalse(system.getCourses().contains(notInSystem));// Invalid course, never put into system
		system.removeCourse(null);// course doesn't exist, hence course list stays same size
		assertFalse(system.getUsers().contains(empty));
		assertEquals(null,system.getCourse("EECS2030"));
		assertEquals(null, system.getItemAll(70));// There is no items with these id's hence we are testing to make sure they dont exist
		assertEquals(null, system.getPhysicalItem(70));
		assertEquals(null, system.getOnlineItem(70));
		
	}
	
	//testing librarysystem constructor that will create a librarysystem from
	//previous data
	@Test
	void librarysystem_Test7() {
		ManagementTeam team = new ManagementTeam();
		Student person = new Student();
		Faculty teacher = new Faculty();

		Course eecs3311 = new Course();
		Course eecs2030 = new Course();
		
		//Physical Items
		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
		book2.setName("Second Book");
		
		
		//OnlineItems
		OnlineItem newYorkTimes = new OnlineItem();
		newYorkTimes.setId(1000);
		newYorkTimes.setName("New York Times");
		newYorkTimes.setStatus(new ItemStateContext(new Enabled()));
		newYorkTimes.setLink("https://www.nytimes.com/ca/");
		
		OnlineItem bbc = new OnlineItem();
		bbc.setId(1000);
		bbc.setName("British Broadcasting Central");
		bbc.setStatus(new ItemStateContext(new Disabled()));
		bbc.setLink("https://www.bbc.com/news");
		
		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system
		
		person.setEmail("ww@gmail");
		person.setPassword("1234GHWUhh@$i");
		
		teacher.setEmail("ww2@gmail");
		teacher.setPassword("1234GHWUhh@$i");
		
		//Add course
		eecs3311.setName("Cool Course");
		eecs3311.setCode("EECS3311");
		
		eecs3311.addStudent(person);
		eecs3311.addFaculty(teacher);
		eecs3311.addTextBook(book1);
		eecs3311.addTextBook(book2);
		
		person.addCourse(eecs3311);
		teacher.addCourse(eecs3311);
		
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<OnlineItem> subs = new ArrayList<OnlineItem>();
		ArrayList<Item> barrowed = new ArrayList<Item>();
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(teacher);
		users.add(person);
		courses.add(eecs2030);
		courses.add(eecs3311);
		items.add(book1);
		items.add(book2);
		subs.add(newYorkTimes);
		subs.add(bbc);

		//Giving a new system old/already created data
		LibrarySystem system = new LibrarySystem(items, barrowed, users,subs,courses);
		team.setSystem(system);
		
		assertEquals(2, system.getUsers().size());
		assertEquals(2, system.getCourses().size());
		assertEquals(2, system.getSubs().size());
		assertEquals(2, system.getStock().size());
		assertTrue(system.getBorrowed().isEmpty());
	}
	
	//test library system with disabled items functioning as intended (not allowing user subscribing when disabled)
	@Test
	void librarysystem_Test8() {
		ManagementTeam team = new ManagementTeam();
		LibrarySystem system = new LibrarySystem();
		Student student = new Student();
		Faculty faculty = new Faculty();
		Nonfaculty nonfaculty = new Nonfaculty();
		Visitor visitor = new Visitor();		
		
		team.setSystem(system);
		
		//OnlineItems
		OnlineItem newYorkTimes = new OnlineItem();
		newYorkTimes.setId(1000);
		newYorkTimes.setName("New York Times");
		newYorkTimes.setStatus(new ItemStateContext(new Enabled()));
		newYorkTimes.setLink("https://www.nytimes.com/ca/");
		
		OnlineItem bbc = new OnlineItem();
		bbc.setId(1000);
		bbc.setName("British Broadcasting Central");
		bbc.setStatus(new ItemStateContext(new Disabled()));
		bbc.setLink("https://www.bbc.com/news");
		
		
		student.setEmail("ww@gmail");
		student.setPassword("1234GHWUhh@$i");
		
		faculty.setEmail("ww2@gmail");
		faculty.setPassword("1234GHWUhh@$i");
		
		nonfaculty.setEmail("ww3@gmail");
		nonfaculty.setPassword("1234GHWUhh@$i");
		
		visitor.setEmail("ww4@gmail");
		visitor.setPassword("1234GHWUhh@$i");
		
		system.addUser(visitor);
		system.addUser(nonfaculty);
		system.addUser(student);
		system.addUser(faculty);
		
		system.addSub(bbc);
		system.addSub(newYorkTimes);
		
		student.subscribe(bbc);// Wont subscribe to it because its disabled
		faculty.subscribe(bbc);
		nonfaculty.subscribe(bbc);
		visitor.subscribe(bbc);
		
		assertTrue(student.getSubscriptions().isEmpty());
		assertTrue(faculty.getSubscriptions().isEmpty());
		assertTrue(nonfaculty.getSubscriptions().isEmpty());
		assertTrue(visitor.getSubscriptions().isEmpty());
	}
	
	//test student trying to rent a disabled item and enabled item and test
	//that only the enabled one is rented
	@Test
	void librarysystem_Test9() {
		ManagementTeam team = new ManagementTeam();
		Student student = new Student();
		LibrarySystem system = new LibrarySystem();

		team.setSystem(system);
		
		//Physical Items
		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Disabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");
		
		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
		book2.setName("Second Book");
		
		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system
		
		student.setEmail("ww@gmail");
		student.setPassword("1234GHWUhh@$i");
		
		system.addUser(student);
		
		student.rentPhysicalItem(book1);
		student.rentPhysicalItem(book2);
		
		assertTrue(student.getRented().contains(book2));
		assertFalse(student.getRented().contains(book1));// Book disabled cannot be rented. hence they dont have it
	}
	
	//test that user is only allowed to own 10 items maximum
	@Test
	void librarysystem_Test10() {
		ManagementTeam team = new ManagementTeam();
		Student student = new Student();
		LibrarySystem system = new LibrarySystem();
		
		team.setSystem(system);
		
		//Physical Items
		PhysicalItem book1 = new PhysicalItem();
		book1.setStatus(new ItemStateContext(new Enabled()));
		book1.setId(PhysicalItem.getNextValidId()); 
		book1.setName("First Book");
		PhysicalItem book2 = new PhysicalItem();
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setId(PhysicalItem.getNextValidId());
		book2.setName("Second Book");
		PhysicalItem book3 = new PhysicalItem();
		book3.setStatus(new ItemStateContext(new Enabled()));
		book3.setId(PhysicalItem.getNextValidId());
		book3.setName("Third Book");
		PhysicalItem book4 = new PhysicalItem();
		book4.setStatus(new ItemStateContext(new Enabled()));
		book4.setId(PhysicalItem.getNextValidId());
		book4.setName("Fourth Book");
		PhysicalItem book5 = new PhysicalItem();
		book5.setStatus(new ItemStateContext(new Enabled()));
		book5.setId(PhysicalItem.getNextValidId());
		book5.setName("Fifth Book");
		PhysicalItem book6 = new PhysicalItem();
		book6.setStatus(new ItemStateContext(new Enabled()));
		book6.setId(PhysicalItem.getNextValidId());
		book6.setName("Sixth Book");
		PhysicalItem book7 = new PhysicalItem();
		book7.setStatus(new ItemStateContext(new Enabled()));
		book7.setId(PhysicalItem.getNextValidId());
		book7.setName("Seventh Book");
		PhysicalItem book8 = new PhysicalItem();
		book8.setStatus(new ItemStateContext(new Enabled()));
		book8.setId(PhysicalItem.getNextValidId());
		book8.setName("Eighth Book");
		PhysicalItem book9 = new PhysicalItem();
		book9.setStatus(new ItemStateContext(new Enabled()));
		book9.setId(PhysicalItem.getNextValidId());
		book9.setName("Ninth Book");
		PhysicalItem book10 = new PhysicalItem();
		book10.setStatus(new ItemStateContext(new Enabled()));
		book10.setId(PhysicalItem.getNextValidId());
		book10.setName("Tenth Book");
		PhysicalItem book11 = new PhysicalItem();
		book11.setStatus(new ItemStateContext(new Enabled()));
		book11.setId(PhysicalItem.getNextValidId());
		book11.setName("Eleventh Book");
		
		team.addPhysicalItem(book1); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book2); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book3); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book4); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book5); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book6); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book7); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book8); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book9); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book10); // Makes 20 copies of the item into the system
		team.addPhysicalItem(book11); // Makes 20 copies of the item into the system
		
		student.setEmail("ww@gmail");
		student.setPassword("1234GHWUhh@$i");
		
		system.addUser(student);
		
		//Attempts to rent one of each of the 11 unique available books

		student.rentPhysicalItem(book1);
		student.rentPhysicalItem(book2);
		student.rentPhysicalItem(book3);
		student.rentPhysicalItem(book4);
		student.rentPhysicalItem(book5);
		student.rentPhysicalItem(book6);
		student.rentPhysicalItem(book7);
		student.rentPhysicalItem(book8);
		student.rentPhysicalItem(book9);
		student.rentPhysicalItem(book10);
		student.rentPhysicalItem(book11);//Wont rent because above limit

		assertEquals(10, student.getRented().size());
		assertEquals(210, system.getStock().size());// 11*20 = 220 - 10 rented = 210
	}
	
	
	@Test
	void testingState() {
		ItemStateContext status = new ItemStateContext();
		assertTrue(status.getState() instanceof Disabled);
		status.setState(new Enabled());
		assertTrue(status.getState() instanceof Enabled);
		status.status();//Prints to console
	}

	@Test
	void testingState2() {
		ItemStateContext status = new ItemStateContext(new Enabled());
		assertTrue(status.getState() instanceof Enabled);
		status.setState(new Disabled());
		assertTrue(status.getState() instanceof Disabled);
		status.status();//Prints to console
	}
	
	//test that user factory creates correct objects for corresponding input values
	// and tests invalid inputs
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

	//testing payment methods in isolation (is tested using cart later)
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
	
	//tests that the csv files are read properly and creates the system with the data
	@Test
	void CSVReader_Test1() {
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

	//tests that the csv files can read the pending accounts file and various methods for checking credentials
	@Test
	void CSVReader_Test2() {
		//w
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
	
	//tests that the csv files are read properly and creates the system with the data
	@Test
	void CSVReader_Test3() {
		LibrarySystem system = CSVReader.dowloadData(new LibrarySystem()); // Downloads data from the CSV files for the library system
		ManagementTeam team = new ManagementTeam(system);
		team.setSystem(system);

		//Checks if the system actually got populated
		assertTrue(system.getStock().size() > 0);
		assertTrue(system.getBorrowed().size() > 0);
		assertTrue(system.getUsers().size() > 0);
		assertTrue(system.getCourses().size() > 0);
		assertTrue(system.getFaculty().size() > 0);
		assertTrue(system.getStudents().size() > 0);
		assertTrue(system.getSubs().size() >0);
		
		Student student = new Student();
		student.setEmail("student@gmail.com");
		system.addUser(student);
		
		ArrayList<PhysicalItem> available = new ArrayList<PhysicalItem>();
		ArrayList<PhysicalItem> unAvailable = new ArrayList<PhysicalItem>();
		
		for(Item I: system.getStock()) {//makes a list of rentable items, ie not borrowed or disabled
			if(((PhysicalItem)I).getBorrower().equals("BLANK") && I.getStatus().getState() instanceof Enabled) {
				available.add((PhysicalItem)I);
			}
		}
		
		for(Item I: system.getStock()) {//makes a list of not rentable items, ie disabled
			if(I.getStatus().getState() instanceof Disabled) {
				unAvailable.add((PhysicalItem)I);
			}
		}
		
		student.rentPhysicalItem(available.get(0));
		student.rentPhysicalItem(available.get(20));
		student.rentPhysicalItem(available.get(25));
		student.rentPhysicalItem(available.get(40));
		
		student.rentPhysicalItem(unAvailable.get(0));// Both are disabled
		student.rentPhysicalItem(unAvailable.get(1));
		
		student.rentPhysicalItem((PhysicalItem)system.getBorrowed().get(0));// Rented out already to someone else

		assertEquals(4, student.getRented().size());
		assertEquals(available.get(0).getBorrower(), student.getEmail());
		assertEquals(available.get(20).getBorrower(), student.getEmail());
		assertEquals(available.get(25).getBorrower(), student.getEmail());
		assertEquals(available.get(40).getBorrower(), student.getEmail());
		assertFalse(unAvailable.get(0).getBorrower().equals(student.getEmail()));
		assertFalse(unAvailable.get(1).getBorrower().equals(student.getEmail()));
		assertFalse(((PhysicalItem)system.getBorrowed().get(0)).getBorrower().equals(student.getEmail()));

	}
	//tests that the csv files are read properly and creates the system with the data
	@Test
	void CSVReader_Test4() {
		LibrarySystem system = CSVReader.dowloadData(new LibrarySystem()); // Downloads data from the CSV files for the library system
		ManagementTeam team = new ManagementTeam(system);
		team.setSystem(system);

		//Checks if the system actually got populated
		assertTrue(system.getStock().size() > 0);
		assertTrue(system.getBorrowed().size() > 0);
		assertTrue(system.getUsers().size() > 0);
		assertTrue(system.getCourses().size() > 0);
		assertTrue(system.getFaculty().size() > 0);
		assertTrue(system.getStudents().size() > 0);
		assertTrue(system.getSubs().size() >0);
		
		Student student = new Student();
		student.setEmail("student@gmail.com");
		system.addUser(student);
		ArrayList<OnlineItem> newsletters = new ArrayList<OnlineItem>();
		for(OnlineItem i: system.getSubs()) {
			if(i.getId() > 0) {
				student.subscribe(i);
				newsletters.add(i);
			}
		}
		// This testcase is dependant on the 3 subscriptions in the CSV files, 2 enabled 1 disabled
		assertEquals(3, newsletters.size());//checking total number of newsletters in the system
		assertEquals(2, student.getSubscriptions().size());// Doesn't rent the disabled newsletter
		assertTrue(newsletters.contains(student.getSubscriptions().get(0)));
		assertTrue(newsletters.contains(student.getSubscriptions().get(1)));
		newsletters.remove(student.getSubscriptions().get(0));//Removing is easier than searching for disabled
		newsletters.remove(student.getSubscriptions().get(1));
		assertFalse(student.getSubscriptions().contains(newsletters.get(0)));
	}
	
	//tests that the csv files are read properly and creates the system with the data
	@Test
	void CSVReader_Test5() {
		LibrarySystem system = CSVReader.dowloadData(new LibrarySystem()); // Downloads data from the CSV files for the library system
		ManagementTeam team = new ManagementTeam(system);
		team.setSystem(system);

		//Checks if the system actually got populated
		assertTrue(system.getStock().size() > 0);
		assertTrue(system.getBorrowed().size() > 0);
		assertTrue(system.getUsers().size() > 0);
		assertTrue(system.getCourses().size() > 0);
		assertTrue(system.getFaculty().size() > 0);
		assertTrue(system.getStudents().size() > 0);
		assertTrue(system.getSubs().size() >0);
		
		Student student = new Student();
		student.setEmail("student@gmail.com");
		
		Faculty fac = new Faculty();
		fac.setEmail("faculty@gmail.com");
		
		system.addUser(fac);
		system.addUser(student);
		
		fac.addCourse(system.getCourse("EECS 2030"));
		student.addCourse(system.getCourse("EECS 2030"));
		system.getCourse("EECS 2030").addStudent(student);
		system.getCourse("EECS 2030").addFaculty(fac);
		
		//Simple test of courses with csv data
		assertTrue(system.getCourse("EECS 2030").getFaculty().contains(fac));
		assertTrue(system.getCourse("EECS 2030").getStudents().contains(student));
		assertTrue(student.getCourses().contains(system.getCourse("EECS 2030")));
		assertTrue(fac.getCourses().contains(system.getCourse("EECS 2030")));
	}
	
	//testing management and course functions
	@Test
	void managementAndFacAndCourseTest1() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);

		PhysicalItem book = new PhysicalItem();
		//Testing setter methods
		book.setName("Chair Book");
		book.setId(PhysicalItem.getNextValidId());
		book.setBorrower(null);
		book.setStatus(new ItemStateContext(new Enabled()));
		book.setPrice(100);
		book.setLost(false);
		book.setDueDate(new Date());
		book.setFee(10);
		book.setDiscount(10);

		ArrayList<PhysicalItem> listrent =new ArrayList<PhysicalItem>();
		ArrayList<OnlineItem> onlinelist =new ArrayList<OnlineItem>();
		UserFactory buildUser = new UserFactory();

		User fac = buildUser.getUser("Faculty");
		fac.setEmail("facGuy@gmail.com");
		fac.setPassword("124");

		User student = buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");

		team.addPhysicalItem(book);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Disabled()));
		book2.setPrice(10);
		book2.setDiscount(0);


		team.addPhysicalItem(book2);
		assertTrue(system.getStock().size()==40);

		team.removeItem(book);
		assertTrue(system.getStock().size()==39); //book should be removed, but all copies should be in there (thus total=39)

		system.addUser(student);
		system.addUser(fac);



		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 50.45, new ItemStateContext(new Enabled()), date, "w@bv.com", 0, 0);
		team.disableItem(book3); //cannot disable beacuse borrowed
		team.addPhysicalItem(book3);
		//assertEquals(book3.getDueStatus(), "Due in: 0 Hours");
		assertEquals(book3.calculateFee(), 0);

		PhysicalItem bookNull = null;
		team.addPhysicalItem(bookNull);


		OnlineItem oItem = new OnlineItem();
		oItem.setName("Sub Item 1");
		oItem.setId(PhysicalItem.getNextValidId());
		oItem.setStatus(new ItemStateContext(new Enabled()));
		oItem.setPrice(100);
		oItem.addSubscriber(student);
		team.disableItem(oItem);
		assertTrue(oItem.getStatus().getState() instanceof Disabled);
		team.enableItem(oItem);
		assertTrue(oItem.getStatus().getState() instanceof Enabled);
		team.addOnlineItem(oItem);
		assertTrue(system.getSubs().contains(oItem));
		team.removeItem(oItem);
		assertFalse(system.getSubs().contains(oItem));


		//now test adding textbook to course and then student and faculty
		team.addCourse("123", "the course");
		assertTrue(system.getCourses().size()==1);
		team.addTextBookToCourse("123", String.valueOf(book2.getId()));
		team.addStudentToCourse("123", "guy@gmail.com");
		team.addFacultyToCourse("123", "facGuy@gmail.com");
		team.removeCourse("123");
		assertTrue(system.getCourses().size()==0);

		//now test adding student and faculty first and then adding a textbook
		team.addCourse("125", "the course");
		assertTrue(system.getCourses().size()==1);
		team.addStudentToCourse("125", "guy@gmail.com");
		team.addFacultyToCourse("125", "facGuy@gmail.com");
		team.addTextBookToCourse("125", String.valueOf(book2.getId()));
		team.removeCourse("125");
		assertTrue(system.getCourses().size()==0);
	}

	//testing librarysystem with multiple courses and textbooks and a student and faculty being added to them
	//also tests that course can be removed properly
	@Test
	void courseAndFacTestManagement2() {
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		UserFactory buildUser = new UserFactory();

		User fac = buildUser.getUser("Faculty");
		fac.setEmail("facGuy@gmail.com");
		fac.setPassword("124");

		User student = buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		system.addUser(student);
		system.addUser(fac);

		team.addCourse("125", "a course");
		assertTrue(system.getCourses().size()==1);
		team.addCourse("127", "a course 2");
		assertTrue(system.getCourses().size()==2);
		team.addCourse("129", "a course 3");
		assertTrue(system.getCourses().size()==3);
		team.addCourse("129", "a course 3");
		assertTrue(system.getCourses().size()==3); //should not be added cuz already added


		team.addStudentToCourse("125", "guy@gmail.com");
		team.addFacultyToCourse("125", "facGuy@gmail.com");
		team.addStudentToCourse("127", "guy@gmail.com");
		team.addFacultyToCourse("127", "facGuy@gmail.com");
		team.addStudentToCourse("129", "guy@gmail.com");
		team.addFacultyToCourse("129", "facGuy@gmail.com");

		team.addTextBookToCourse("125", String.valueOf(book3.getId()));
		team.addTextBookToCourse("127", String.valueOf(book3.getId()));
		team.addTextBookToCourse("129", String.valueOf(book3.getId()));


		team.addTextBookToCourse("127", String.valueOf(book4.getId()));
		team.addTextBookToCourse("129", String.valueOf(book5.getId()));

		//check that user and faculty both have one textbook subscription for each textbook
		assertEquals(student.getSubscriptions().size(), 3);
		assertTrue(((Faculty)fac).getTextBooks().size()==3);

		team.removeCourse("125");
		assertTrue(system.getCourses().size()==2);
	}

	//manually test courses without using management team functions
	@Test
	void courseAndFacTest3() {
		LibrarySystem system = new LibrarySystem();

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		UserFactory buildUser = new UserFactory();

		User fac = buildUser.getUser("Faculty");
		fac.setEmail("facGuy@gmail.com");
		fac.setPassword("124");

		User student = buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");

		system.addUser(student);
		system.addUser(fac);

		Course course = new Course("100", "", new ArrayList<PhysicalItem>(), new ArrayList<Student>(), new ArrayList<Faculty>());

		//should not be added because they are null
		course.addStudent(null);
		course.addFaculty(null);
		course.addTextBook(null);

		course.addStudent((Student)student);
		course.addFaculty((Faculty)fac);
		course.addTextBook(book3);
		course.addTextBook(book4);
		course.addTextBook(book5);

		assertEquals(course.getTextBooks().size(), 3);
		assertEquals(course.getStudents().size(), 1);
		assertEquals(course.getStudents().size(), 1);

		course.removeStudent((Student)student);
		course.removeFaculty((Faculty)fac);
		course.removeTextBook(book3);
		course.removeTextBook(book4);
		course.removeTextBook(book5);

		assertEquals(course.getTextBooks().size(), 0);
		assertEquals(course.getStudents().size(), 0);
		assertEquals(course.getStudents().size(), 0);
	}

	//manually test courses and faculty without using team
	@Test
	void courseAndFacTest4() {
		LibrarySystem system = new LibrarySystem();

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);


		ArrayList<Item> items =new ArrayList<Item>();
		ArrayList<Course> courses =new ArrayList<Course>();
		Faculty fac = new Faculty(items, courses); 
		fac.setEmail("facGuy@gmail.com");
		fac.setPassword("124");

		system.addUser(fac);

		Course course = new Course("100", "", new ArrayList<PhysicalItem>(), new ArrayList<Student>(), new ArrayList<Faculty>());

		//should not be added because they are null
		course.addFaculty(null);
		course.addTextBook(null);


		course.addFaculty((Faculty)fac);
		course.addTextBook(book3);
		course.addTextBook(book4);
		course.addTextBook(book5);

		assertEquals(course.getTextBooks().size(), 3);
		assertEquals(course.getFaculty().size(), 1);

		fac.addTextBook(book3);
		fac.addTextBook(book4);
		fac.addTextBook(book5);
		fac.addCourse(course);

		assertEquals(fac.getTextBooks().size(), 3);
		assertEquals(fac.getCourses().size(), 1);

		course.removeFaculty((Faculty)fac);
		course.removeTextBook(book3);
		course.removeTextBook(book4);
		course.removeTextBook(book5);

		assertEquals(course.getTextBooks().size(), 0);
		assertEquals(course.getFaculty().size(), 0);

		fac.removeTextBook(book3);
		fac.removeTextBook(book4);
		fac.removeTextBook(book5);
		fac.removeCourse(course);

		assertEquals(fac.getTextBooks().size(), 0);
		assertEquals(fac.getCourses().size(), 0);


		//these should not raise exceptions
		fac.removeTextBook(book5); //cannot be removed, doesnt exist
		fac.removeTextBook(null); //cannot remove null
		fac.removeCourse(course); //cannot be removed, doesnt exist
		fac.removeTextBook(null); //cannot remove null
	}
	//manually test courses and faculty without using team and add textbooks all at once, not one by one
	@Test
	void courseAndFacTest5() {
		LibrarySystem system = new LibrarySystem();

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);


		ArrayList<Item> items =new ArrayList<Item>();
		ArrayList<Course> courses =new ArrayList<Course>();
		Faculty fac = new Faculty(items, courses); 
		fac.setEmail("facGuy@gmail.com");
		fac.setPassword("124");

		system.addUser(fac);

		Course course = new Course("100", "", new ArrayList<PhysicalItem>(), new ArrayList<Student>(), new ArrayList<Faculty>());

		//should not be added because they are null
		course.addFaculty(null);
		course.addTextBook(null);


		course.addFaculty((Faculty)fac);
		course.addTextBook(book3);
		course.addTextBook(book4);
		course.addTextBook(book5);

		assertEquals(course.getTextBooks().size(), 3);
		assertEquals(course.getFaculty().size(), 1);

		fac.addTextBooks(course.getTextBooks());
		fac.addCourse(course);

		assertEquals(fac.getTextBooks().size(), 3);
		assertEquals(fac.getCourses().size(), 1);

		course.removeFaculty((Faculty)fac);
		course.removeTextBook(book3);
		course.removeTextBook(book4);
		course.removeTextBook(book5);

		assertEquals(course.getTextBooks().size(), 0);
		assertEquals(course.getFaculty().size(), 0);

		fac.removeTextBook(book3);
		fac.removeTextBook(book4);
		fac.removeTextBook(book5);
		fac.removeCourse(course);

		assertEquals(fac.getTextBooks().size(), 0);
		assertEquals(fac.getCourses().size(), 0);

		//these should not raise exceptions
		fac.removeTextBook(book5); //cannot be removed, doesnt exist
		fac.removeTextBook(null); //cannot remove null
		fac.removeCourse(course); //cannot be removed, doesnt exist
		fac.removeTextBook(null); //cannot remove null
	}



	//test courses and student 
	@Test
	void courseAndStudentTest6() {
		LibrarySystem system = new LibrarySystem();

		UserFactory buildUser = new UserFactory();

		Student student = (Student) buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");

		system.addUser(student);


		Course course = new Course("100", "", new ArrayList<PhysicalItem>(), new ArrayList<Student>(), new ArrayList<Faculty>());

		course.addStudent(student);
		student.addCourse(course);

		assertEquals(course.getStudents().size(), 1);
		assertEquals(student.getCourses().size(), 1);

		course.removeStudent(student);
		student.dropCourse(course);

		assertEquals(course.getStudents().size(), 0);
		assertEquals(student.getCourses().size(), 0);

	}
	@Test
	void testManagement1() { //enable singular book
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		team.disableItem(bookList);
		assertTrue(book3.getStatus().getState() instanceof Disabled);
	}
	@Test
	void testManagement2() { //disable singular book
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book9);
		team.enableItem(bookList);
		assertTrue(book9.getStatus().getState() instanceof Enabled);
	}
	@Test
	void testManagement3() { //disable multiple books
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.disableItem(bookList);
		assertTrue(book3.getStatus().getState() instanceof Disabled);
		assertTrue(book4.getStatus().getState() instanceof Disabled);
		assertTrue(book5.getStatus().getState() instanceof Disabled);
	}
	@Test
	void testManagement4() { //enable multiple books
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.enableItem(bookList);
		assertTrue(book3.getStatus().getState() instanceof Enabled);
		assertTrue(book4.getStatus().getState() instanceof Enabled);
		assertTrue(book5.getStatus().getState() instanceof Enabled);

	}
	@Test
	void testManagement5() { //make sure faculty is successfully added to course
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Course course = new Course();
		Faculty faculty = new Faculty();
		String code = "EECS3101";
		String email = "email@gmail.com";
		course.setCode(code);
		faculty.setEmail(email);
		system.addCourse(course);
		system.addUser(faculty);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.addFacultyToCourse(code, email);
		assertTrue(course.getFaculty().contains(faculty));		
	}

	//using management team to properly set up a course with a faculty member
	@Test
	void testManagement6() { 
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Course course = new Course();
		Faculty faculty = new Faculty();
		String code = "EECS3101";
		String email = "email@gmail.com";
		course.setCode(code);
		faculty.setEmail(email);
		system.addCourse(course);
		system.addUser(faculty);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.addFacultyToCourse(code, email); //add first faculty
		int facultyCount = course.getFaculty().size(); //should only ever be 1
		team.addFacultyToCourse(code, email); //try to add another faculty to a course
		assertEquals(facultyCount, course.getFaculty().size()); //make sure the above line doesn't work. i.e another faculty should not be added to the same course
	}

	//using management team to properly set up a course with a faculty member
	@Test
	void testManagement7() {
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Course course = new Course();
		Faculty faculty = new Faculty();
		String code = "EECS3101";
		String email = "email@gmail.com";
		course.setCode(code);
		faculty.setEmail(email);
		system.addCourse(course);
		system.addUser(faculty);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		assertEquals(0, faculty.getTextBooks().size()); //make sure no textbooks exist yet in the faculty
		team.addFacultyToCourse(code, email);  
		assertEquals(course.getTextBooks().size(), faculty.getTextBooks().size()); //number of textbooks in the course must equal the amount of textbooks in the the faculty
	}

	//testing adding multiple textbooks to a course at once using an array
	@Test
	void testManagement8() {
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Faculty faculty = new Faculty();
		Course course = new Course();
		String code = "EECS3101";
		String email = "email@gmail.com";
		ArrayList<PhysicalItem> textbooks = new ArrayList<>();
		textbooks.add(new PhysicalItem(1, "book1", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0));
		textbooks.add(new PhysicalItem(2, "book2", 20, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0));
		faculty.addTextBooks(textbooks);

		course.addFaculty(faculty);
		team.addFacultyToCourse(code, email);

		ArrayList<String> array = new ArrayList<>();
		for (Item textbook : faculty.getTextBooks()) {
			array.add(textbook.getName());
		}
		assertTrue(array.contains("book1"));
		assertTrue(array.contains("book2"));
	}
	
	@Test
	void testManagement9() { //different input for adding faculty to course
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Course course = new Course();
		Faculty faculty = new Faculty();
		String code = "EECS3101";
		String email = "testing@outlook.com";
		course.setCode(code);
		faculty.setEmail(email);
		system.addCourse(course);
		system.addUser(faculty);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "Running Time", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "Big Oh", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "Dynamic Programming", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.addFacultyToCourse(code, email);
		assertTrue(course.getFaculty().contains(faculty));		
	}
	
	@Test
	void testManagement10() { //different input for adding faculty to course
		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);
		Course course = new Course();
		Faculty faculty = new Faculty();
		String code = "CODE2002";
		String email = "user@hotmail.com";
		course.setCode(code);
		faculty.setEmail(email);
		system.addCourse(course);
		system.addUser(faculty);
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "Data Structures", 10, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "OOP", 20, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "foo", 30, new ItemStateContext(new Disabled()), null, "BLANK", 0, 0);
		ArrayList<Item> bookList = new ArrayList<Item>();
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);
		team.addFacultyToCourse(code, email);
		assertTrue(course.getFaculty().contains(faculty));		
	}
	
	
	//test user buying books using menu options (command pattern) such as clear, checkout, etc.
	@Test
	void testCart1() {

		LibrarySystem system = new LibrarySystem();


		PhysicalItem book = new PhysicalItem();
		//Testing setter methods
		book.setName("Chair Book");
		book.setId(1);
		book.setBorrower(null);
		book.setStatus(new ItemStateContext(new Disabled()));
		book.setPrice(100);
		book.setLost(false);
		book.setDueDate(new Date());
		book.setFee(10);
		book.setDiscount(10);

		UserFactory buildUser = new UserFactory();

		User student = buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");

		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(2);
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0);

		system.addUser(student);
		system.addStock(book2);
		system.addStock(book);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book2); //test adding same item twice
		assertEquals(student.getCart().getItems().size(), 1);
		assertEquals(student.getCart().getInitialPrice(), 10);
		student.getMenu().clickRemove(book2);
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		assertEquals(student.getCart().getItems().get(0), book2);

		student.getMenu().clickClear();
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
		//assertEquals();
	}

	//test user buying books using menu options (command pattern)
	@Test
	void testCart2() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);

		PhysicalItem book = new PhysicalItem();
		//Testing setter methods
		book.setName("Chair Book");
		book.setId(PhysicalItem.getNextValidId());
		book.setBorrower("BLANK");
		book.setStatus(new ItemStateContext(new Enabled()));
		book.setPrice(100);
		book.setLost(false);
		book.setDueDate(new Date());
		book.setFee(10);
		book.setDiscount(10);

		UserFactory buildUser = new UserFactory();

		User student = buildUser.getUser("Student");
		student.setEmail("guy@gmail.com");
		student.setPassword("123");

		team.addPhysicalItem(book);
		assertTrue(system.getStock().size()>0);

		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Disabled()));
		book2.setPrice(10);
		book2.setDiscount(0);

		team.enableItem(book);
		team.disableItem(book2);

		team.addPhysicalItem(book2);
		assertTrue(system.getStock().size()>20);

		team.removeItem(book);
		assertFalse(system.getStock().size()>=40); //book should be removed, but all copies should be in there (thus total=39)
		//team.removeItem(book2);

		system.addUser(student);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book2); //test adding same item twice
		assertEquals(student.getCart().getItems().size(), 1);
		assertEquals(student.getCart().getInitialPrice(), 10);
		student.getMenu().clickRemove(book2);
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		assertEquals(student.getCart().getItems().get(0), book2);

		student.getMenu().clickClear();
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
		//assertEquals();
	}

	//test user buying books using menu options (command pattern)
	@Test
	void testCart3() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);

		PhysicalItem book = new PhysicalItem();
		//Testing setter methods
		book.setName("Chair Book");
		book.setId(PhysicalItem.getNextValidId());
		book.setBorrower("BLANK");
		book.setStatus(new ItemStateContext(new Enabled()));
		book.setPrice(100);
		book.setLost(false);
		book.setDueDate(new Date());
		book.setFee(10);
		book.setDiscount(10);


		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		team.addPhysicalItem(book);
		assertTrue(system.getStock().size()>0);

		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Disabled()));
		book2.setPrice(10);
		book2.setDiscount(0);

		team.disableItem(book);
		team.enableItem(book2);

		team.addPhysicalItem(book2);
		assertTrue(system.getStock().size()>20);
		assertTrue(system.getItemAll(book2.getId())==book2);


		team.removeItem(book);
		assertFalse(system.getStock().size()>=40); //book should be removed, but all copies should be in there (thus total=39)
		//team.removeItem(book2);



		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book2); //test adding same item twice
		assertEquals(student.getCart().getItems().size(), 1);
		assertEquals(student.getCart().getInitialPrice(), 10);
		student.getMenu().clickRemove(book2);
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		assertEquals(student.getCart().getItems().get(0), book2);

		student.getMenu().clickClear();
		assertEquals(student.getCart().getItems().size(), 0);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
		//assertEquals();
	}

	//test buying several books at once
	@Test
	void testCart4() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);

		assertEquals(student.getCart().getItems().size(), 8);
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
	}


	//test buying several books with payment
	@Test
	void testCart5() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);


		student.getCart().setCurrency("EUR");
		double displayPrice = student.getCart().getConvertedPrice();
		assertEquals(80, student.getCart().getInitialPrice()); //price in CAD
		assertEquals(54.296, displayPrice); //price in EUR
		assertEquals(student.getCart().getItems().size(), 8);

		PaymentContext payment = new PaymentContext(new PayPalStrategy("guy@gmail.com"));
		assertTrue(payment.pay(displayPrice)); //verify payment details are correct
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
	}



	//test buying several books with DEBIT payment in USD and discount
	@Test
	void testCart7() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0.50);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		//add 50 percent discount to some items
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);

		//8 books being rented all cost 10 dollars, 5 are discounted half off
		//thus the price should be 55 dollars in CAD

		student.getCart().setCurrency("USD");
		double displayPrice = student.getCart().getConvertedPrice();
		displayPrice=(Math.round(displayPrice*100))/100;
		assertEquals(55, student.getCart().getInitialPrice()); //price in CAD
		assertEquals(40.00, displayPrice); //price in USD
		assertEquals(student.getCart().getItems().size(), 8);

		PaymentContext payment = new PaymentContext(new DebitCardStrategy("John Smith", "1234 1234 1234 1234", "04/25", "254" ));
		assertTrue(payment.pay(displayPrice)); //verify payment details are correct
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
	}

	//test adding items, then clearing and trying to checkout with empty cart (should not be allowed)
	@Test
	void testCart8() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0.50);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		//add 50 percent discount to some items
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);


		student.getMenu().clickClear();
		assertEquals(student.getCart().getItems().size(), 0);
		//canCheckout() should return -2, meaning cart is empty and thus checkout didnt go through
		// so checkout returns false
		assertEquals(false, student.getMenu().clickCheckout());
		assertEquals(student.getCart().getItems().size(), 0);
	}
	//test buying several books with payment and discount, while removing items
	//and trying to add same item multiple times
	@Test
	void testCart9() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0.50);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		//add 50 percent discount to some items
		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0.50);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);

		assertEquals(student.getCart().getItems().size(), 8);

		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book3);
		assertEquals(student.getCart().getItems().size(), 8); //cart should not have increased in size

		student.getMenu().clickRemove(book8);
		student.getMenu().clickRemove(book3);
		assertEquals(student.getCart().getItems().size(), 6);

		student.getMenu().clickAdd(book3);
		assertEquals(student.getCart().getItems().size(), 7);


		//7 books being rented all cost 10 dollars, 4 are discounted half off
		//thus the price should be 50 dollars in CAD

		student.getCart().setCurrency("KRW");
		double displayPrice = student.getCart().getConvertedPrice();
		displayPrice=(Math.round(displayPrice*100))/100;
		assertEquals(50, student.getCart().getInitialPrice()); //price in CAD
		assertEquals(48622, displayPrice); //price in Korean Won
		assertEquals(student.getCart().getItems().size(), 7);

		PaymentContext payment = new PaymentContext(new PayPalStrategy("guy@gmail.com"));
		assertTrue(payment.pay(displayPrice)); //verify payment details are correct
		student.getMenu().clickCheckout();
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0);
	}

	//test buying too many books (should not allow checkout)
	@Test
	void testCart10() {

		LibrarySystem system = new LibrarySystem();
		ManagementTeam team = new ManagementTeam(system);



		ArrayList<Course> courses =new ArrayList<Course>();


		User student = new Student(courses);
		student.setEmail("guy@gmail.com");
		student.setPassword("123");
		student.setRented(new ArrayList<PhysicalItem>());
		student.setSubscriptions(new ArrayList<OnlineItem>());
		student.setSystem(system);

		//create a new empty cart for this user
		Cart cart=new Cart(new ArrayList<Item>(), student);
		//create the command objects and initialize them so that they are using this user's cart
		ICartCommand1 clickAdd = new Add(cart);
		ICartCommand1 clickRemove = new Remove(cart);
		ICartCommand2 clickClear = new Clear(cart);
		ICartCommand2 clickCheckout = new Checkout(cart);
		//create a menu with the commands that the user can use 
		Menu menu=new Menu(clickAdd, clickRemove, clickClear, clickCheckout);
		student.setCart(cart);
		student.setMenu(menu);
		system.addUser(student);


		Date date = new Date();
		PhysicalItem book2 = new PhysicalItem(date, "BLANK", 0);
		book2.setName("some book");
		book2.setId(PhysicalItem.getNextValidId());
		book2.setStatus(new ItemStateContext(new Enabled()));
		book2.setPrice(10);
		book2.setDiscount(0);


		team.addPhysicalItem(book2);
		assertTrue(system.getItemAll(book2.getId())==book2);

		PhysicalItem book3 = new PhysicalItem(PhysicalItem.getNextValidId(), "book3", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book4 = new PhysicalItem(PhysicalItem.getNextValidId(), "book4", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book5 = new PhysicalItem(PhysicalItem.getNextValidId(), "book5", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book6 = new PhysicalItem(PhysicalItem.getNextValidId(), "book6", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book7 = new PhysicalItem(PhysicalItem.getNextValidId(), "book7", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book8 = new PhysicalItem(PhysicalItem.getNextValidId(), "book8", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book9 = new PhysicalItem(PhysicalItem.getNextValidId(), "book9", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book10 = new PhysicalItem(PhysicalItem.getNextValidId(), "book10", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book11 = new PhysicalItem(PhysicalItem.getNextValidId(), "book11", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);
		PhysicalItem book12 = new PhysicalItem(PhysicalItem.getNextValidId(), "book12", 10, new ItemStateContext(new Enabled()), null, "BLANK", 0, 0);

		team.addPhysicalItem(book3);
		team.addPhysicalItem(book4);
		team.addPhysicalItem(book5);
		team.addPhysicalItem(book6);
		team.addPhysicalItem(book7);
		team.addPhysicalItem(book8);
		team.addPhysicalItem(book9);
		team.addPhysicalItem(book10);
		team.addPhysicalItem(book11);
		team.addPhysicalItem(book12);

		student.getMenu().clickAdd(book2);
		student.getMenu().clickAdd(book3);
		student.getMenu().clickAdd(book4);
		student.getMenu().clickAdd(book5);
		student.getMenu().clickAdd(book6);
		student.getMenu().clickAdd(book7);
		student.getMenu().clickAdd(book8);
		student.getMenu().clickAdd(book9);
		student.getMenu().clickAdd(book10);
		student.getMenu().clickAdd(book11);
		student.getMenu().clickAdd(book12);



		student.getCart().setCurrency("EUR");
		double displayPrice = student.getCart().getConvertedPrice();
		displayPrice=(Math.round(displayPrice*100))/100;
		assertEquals(110, student.getCart().getInitialPrice()); //price in CAD
		assertEquals(74.0, displayPrice); //price in EUR
		assertEquals(student.getCart().getItems().size(), 11);

		//11 items in cart so checkout should not work (return false)
		assertFalse(student.getMenu().clickCheckout());

		//remove item so checkout works
		student.getMenu().clickRemove(book12);
		//recalculate price for user's currency
		displayPrice = student.getCart().getConvertedPrice();
		displayPrice=(Math.round(displayPrice*100))/100;
		//check updated price and size of cart
		assertEquals(100, student.getCart().getInitialPrice()); //price in CAD
		assertEquals(67.0, displayPrice); //price in EUR
		assertEquals(student.getCart().getItems().size(), 10);

		//try to checkout  (it works now)
		PaymentContext payment = new PaymentContext(new PayPalStrategy("guy@gmail.com"));
		assertTrue(payment.pay(displayPrice)); //verify payment details are correct
		assertTrue(student.getMenu().clickCheckout()); //checkout should be true now
		student.getCart().clear();
		assertEquals(student.getCart().getItems().size(), 0); 
		//assertEquals(); 
	}
	
	//testing invalid method input using null
	@Test
	void testFacultyEdgeCase() {
		Faculty faculty = new Faculty();
		faculty.removeTextBook(null);
		assertEquals(0, faculty.getTextBooks().size());
	}
	
	@Test
	void testUser1() { //valid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Student"); 
        OnlineItem item = new OnlineItem();
        item.setId(1);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(1);
        
        assertEquals(0, user.getSubscriptions().size());
		
	}
	@Test
	void testUser2() { //second valid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Faculty"); 
        OnlineItem item = new OnlineItem();
        item.setId(5);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(5);
        
        assertEquals(0, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser3() { //third valid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Nonfaculty"); 
        OnlineItem item = new OnlineItem();
        item.setId(2);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(2);
        
        assertEquals(0, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser4() { //fourth valid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Visitor"); 
        OnlineItem item = new OnlineItem();
        item.setId(9);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(9);
        
        assertEquals(0, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser5() { //invalid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Student"); 
        OnlineItem item = new OnlineItem();
        item.setId(5);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(7);
        
        assertEquals(1, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser6() { //second invalid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Faculty"); 
        OnlineItem item = new OnlineItem();
        item.setId(5);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(2);
        
        assertEquals(1, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser7() { //third invalid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Nonfaculty"); 
        OnlineItem item = new OnlineItem();
        item.setId(5);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(4);
        
        assertEquals(1, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser8() { //fourth invalid ID for unSubscribeById
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Visitor"); 
        OnlineItem item = new OnlineItem();
        item.setId(7);
        user.getSubscriptions().add(item);
        
        user.unSubscribeById(-1);
        
        assertEquals(1, user.getSubscriptions().size());
		
	}
	
	@Test
	void testUser9() { //nothing overdue yet
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Student");
		assertEquals(0,user.numberOverdue());
	}
	
	@Test
	void testUser10() { //overdue by one day
		UserFactory buildUser = new UserFactory();
		User user = buildUser.getUser("Student");
		PhysicalItem item = new PhysicalItem();		
		Date presentDate = new Date();
		Date dueDate = new Date();
		
		long presentMinusOneDay = presentDate.getTime() - 86400000l;
		dueDate.setTime(presentMinusOneDay);
		item.setDueDate(dueDate);   	    
	    
	    item.setFee(25);
	    user.getRented().add(item);
	    
		assertEquals(1,user.numberOverdue());
	}	 
}


