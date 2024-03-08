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
			system.removeCourse(system.getCourseByCode(course));
		}
	}
	
	public void addStudentToCourse(String code, String email) {
		Course course = system.getCourseByCode(code);
		User student = system.getUser(email);
		
		if(course != null && student != null && student.getClass() == (new Student()).getClass()) {
			if(!course.getStudents().contains(student)) {
				course.addStudent((Student) student);
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
		User fac = system.getUser(email);
		if(course != null && fac != null && fac.getClass() == (new Faculty()).getClass()) {
			if(!course.getFaculty().contains(fac)) {
				course.addFaculty((Faculty) fac);
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
		Item textbook = system.getPhysicalItem(Integer.valueOf(id));
		
		if(course != null && textbook != null) {
			if(!course.getTextBooks().contains(textbook)) {
				course.addTextBook(textbook);
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
