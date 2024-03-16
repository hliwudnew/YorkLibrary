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
		if(item != null) {
			system.addStock(item);
		}
		else {
			System.out.println("Null Item");
		}
	}
	
	public void addOnlineItem(OnlineItem item) {
		if(item != null) {
			system.addSub(item);
		}
		else {
			System.out.println("Null Item");
		}
	}
	
	public void removeItem(Item item) {
		if(item != null) {
			//Physical Item
			if(item.getClass() == (new PhysicalItem()).getClass()) {
				if(!system.getStock().contains(item) && system.getBorrowed().contains(item)) {
					System.out.println("Cannot Delete Item it is being barrowed");
				}
				else {
					system.removeStock(item);
				}	
			}
			//Online Item
			else {
				//Unsubscribes everyone from the online item
				for(User u: ((OnlineItem)item).getSubscribers()) {
					u.unSubscribe((OnlineItem)item);
				}
				//Removes the online item from the system
				system.removeSub((OnlineItem)item);
				
			}
		}
		else {
			System.out.println("Item Doesnt exits");
		}
	}
	
	public void disableItem(Item item) {
		if(item.getClass().toString().equals(new PhysicalItem().getClass().toString())) {
			if(((PhysicalItem) item).getBorrower().contains("@")) {
				System.out.println("Cannot disabled item, it is currently being rented");
			}
			else {
				ItemStateContext status = new ItemStateContext(new Disabled());
				item.setDisabled(status);
			}
		}
		//Can disabled all online items easily
		else {
			ItemStateContext status = new ItemStateContext(new Disabled());
			item.setDisabled(status);
		}
	}
	
	public void disableItem(ArrayList<Item> items) {
		for(Item I: items) {
			ItemStateContext status = new ItemStateContext(new Disabled());
			I.setDisabled(status);
		}
	}
	
	public void enableItem(Item item) {
		ItemStateContext status = new ItemStateContext(new Enabled());
		item.setDisabled(status);
	}
	
	public void enableItem(ArrayList<Item> items) {
		for(Item I: items) {
			ItemStateContext status = new ItemStateContext(new Enabled());
			I.setDisabled(status);
		}
	}
	
	public void verifyAccount(String username, String password) {
		
	}
	
	public void addCourse(String code, String name) {
		if(!code.equals("") && !name.equals("") && system.getCourseByCode(code) == null) {
			Course course = new Course();
			course.setName(name);
			course.setCode(code);
			system.addCourse(course);
		}
		else {
			System.out.println("Enter proper values");
		}
	}
	
	public void removeCourse(String course) {
		if(!course.equals("") && course != null) {
			Course holder = system.getCourse(course);
			
			ArrayList<Integer> ids = new ArrayList<Integer>();
			for(Item I: holder.getTextBooks()) {
				ids.add(I.getId()*-1);//Looking at online copies of books hence negative
			}
			
			ArrayList<OnlineItem> remove = new ArrayList<OnlineItem>();
			//Unsubscribes users from the Online Copy of their textbooks
			for(User u: holder.getStudents()) {
				for(OnlineItem I: u.getSubscriptions()) {
					if(ids.contains(I.getId())) {
						remove.add(I);
					}
				}
			}
			
			for(User u: holder.getStudents()) {
				for(int i =0; i < remove.size(); i++) {
					u.unSubscribe(remove.get(i));
				}
			}
			
			
			//Removes course
			system.removeCourse(system.getCourseByCode(course));
		}
	}
	
	public void addStudentToCourse(String code, String email) {
		Course course = system.getCourseByCode(code);
		Student student = (Student) system.getUser(email);
		
		if(course != null && student != null && student.getClass() == (new Student()).getClass()) {
			if(!course.getStudents().contains(student)) {
				ArrayList<Integer> subIds = new ArrayList<Integer>();
				for(Item I: system.getSubs()) {
					subIds.add(I.getId());
				}
				
				ArrayList<Integer> holder = new ArrayList<Integer>();
				for(Item I: student.getSubscriptions()) {
					holder.add(I.getId());
				}
				
				//Assigns the Student virtual copies of the textbooks
				for(Item I: course.getTextBooks()) {
					//If the Student doesn't have the online textbook
					if(!holder.contains(I.getId()*-1)) {// negative is for online copies
						//If textbook already has a online copy subscribe them too it
						if(subIds.contains(I.getId()*-1)){
						  student.subscribe((OnlineItem) system.getOnlineItem(I.getId()*-1));
						  ((OnlineItem) system.getOnlineItem(I.getId()*-1)).addSubscriber(student);
						}
						else {
							//IF not edition of the textbook is online make one
							OnlineItem textBookCopy = new OnlineItem();
							
							textBookCopy.setId(I.getId()*-1);
							textBookCopy.setName(I.getName());
							textBookCopy.setPrice(I.getPrice());
							textBookCopy.setDisabled(I.getDisabled());
							textBookCopy.setLink("https://www.amazon.ca/New-Used-Textbooks-Books/b?ie=UTF8&node=15115321");
							
							student.subscribe(textBookCopy);
							textBookCopy.addSubscriber(student);
						}
					}
				}
				course.addStudent(student); // Assigns student to course
			}
			else {
				System.out.println("They are already in the class");
			}
		}
		else {
			System.out.println("Student or Course entered wrong");
		}
		
	}
	
	public void addFacultyToCourse(String code, String email) {
		Course course = system.getCourseByCode(code);
		Faculty fac = (Faculty)system.getUser(email);
		if(course != null && fac != null && fac.getClass() == (new Faculty()).getClass()) {
			if(!course.getFaculty().contains(fac)) {
				//Adds the faculty to the course
				course.addFaculty(fac);
				
				
				for(Item I: course.getTextBooks()) {
					//Assigns them the textbook if they dont have any
					if(fac.getTextBooks().size() == 0) {
						//Assign them the textbook they are missing it
						PhysicalItem temp = new PhysicalItem();
						temp.setId(I.getId());
						temp.setName(I.getName());
						fac.addTextBook(temp);
					}
					//They already have textbooks
					else {
						ArrayList<String> holder = new ArrayList<String>();
						for(Item K: fac.getTextBooks()) {
							holder.add(K.getName());
						}
						//If they dont have the textbook by name assign it to them
						if(!holder.contains(I.getName())) {
							PhysicalItem temp = new PhysicalItem();
							temp.setId(I.getId());
							temp.setName(I.getName());
							fac.addTextBook(temp);
						}
					}
				}
			}
			else {
				System.out.println("They are already in the class");
			}
		}
		else {
			System.out.println("Faculty or Course entered wrong");
		}
	}
	
	public void addTextBookToCourse(String code, String id) {
		Course course = system.getCourseByCode(code);
		PhysicalItem textbook =(PhysicalItem)system.getPhysicalItem(Integer.valueOf(id));
		
		if(course != null && textbook != null) {
			if(!course.getTextBooks().contains(textbook)) {
				
				ArrayList<Integer> subIds = new ArrayList<Integer>();
				for(Item I: system.getSubs()) {
					subIds.add(I.getId());
				}
				
				
				//If the online copy already exists then assign it to the students
				if(subIds.contains(textbook.getId()*-1)){//Negative is for online copy
					for(User u: course.getStudents()) {
						
						ArrayList<Integer> holder = new ArrayList<Integer>();
						for(Item I: u.getSubscriptions()) {
							holder.add(I.getId());
						}
						
						//If the user doesn't already have the online textbook we subscribe them to it
						if(!holder.contains(textbook.getId()*-1)) {
							((OnlineItem) system.getOnlineItem(textbook.getId()*-1)).addSubscriber(u);
							u.subscribe(((OnlineItem) system.getOnlineItem(textbook.getId()*-1)));
						}
					
					}
				}
				//Online copy doesnt exist
				else {
					//IF not edition of the textbook is online make one
					OnlineItem textBookCopy = new OnlineItem();
					textBookCopy.setId(textbook.getId()*-1);//Negative Ids are used for textbook online copies
					textBookCopy.setName(textbook.getName());
					textBookCopy.setPrice(textbook.getPrice());
					textBookCopy.setDisabled(textbook.getDisabled());
					textBookCopy.setLink("https://www.amazon.ca/New-Used-Textbooks-Books/b?ie=UTF8&node=15115321");
					system.addSub(textBookCopy);
					
					//Assigns a virtual copy to all of the student in the course
					for(User u: course.getStudents()) {
						textBookCopy.addSubscriber(u);
						u.subscribe(textBookCopy);
					}
				}
				
				course.addTextBook(textbook); // Assigns textbook to course
				//Assigns the textbook to the faulty
				for(Faculty u: course.getFaculty()) {
					//assigns them the textbook if they dont have any
					if(u.getTextBooks().size() == 0) {
						PhysicalItem temp = new PhysicalItem();
						temp.setId(textbook.getId());
						temp.setName(textbook.getName());
						u.addTextBook(temp);
					}
					//They already have textbooks
					else {
						//Gets all their textbook names
						ArrayList<String> holder = new ArrayList<String>();
						for(Item I: u.getTextBooks()) {
							holder.add(I.getName());
						}
						//Checks if the faculty already has a textbook of that name, if not gives them the textbook
						if(!holder.contains(textbook.getName())) {
							PhysicalItem temp = new PhysicalItem();
							temp.setId(textbook.getId());
							temp.setName(textbook.getName());
							u.addTextBook(temp);
						}
					}
				}
			}
			else {
				System.out.println("Book is already assigned to the class");
			}
		}
		else {
			System.out.println("TextBook Id or Course entered wrong");
		}
	}
	
	
	//Setters
	public void setSystem(LibrarySystem system) {
		this.system = system;
	}
}
