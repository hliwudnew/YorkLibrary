package foo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LibrarySystem {

	//Library System stuff
	private ArrayList<Item> stock = new ArrayList<Item>();
	private ArrayList<Item> borrowed = new ArrayList<Item>();
	private ArrayList<User> userlist = new ArrayList<User>();
	private ArrayList<OnlineItem> subscriptions = new ArrayList<OnlineItem>();
	private ArrayList<Course> courses = new ArrayList<Course>();


	public LibrarySystem() {
		stock = new ArrayList<Item>();
		borrowed = new ArrayList<Item>();
		userlist = new ArrayList<User>();
		subscriptions = new ArrayList<OnlineItem>();
		courses = new ArrayList<Course>();
	}

	public LibrarySystem(ArrayList<Item> stock, ArrayList<Item> borrowed, ArrayList<User> users,ArrayList<OnlineItem> subs,ArrayList<Course> courses ) {
		this.stock = stock;
		this.borrowed = borrowed;
		this.userlist = users;
		this.subscriptions = subs;
		this.courses = courses;
	}


	//Both below are used to populate the LibrarySystem with data from the CSVs
	public void setUsers(ArrayList<User> usersCSV) {
		this.userlist = usersCSV;
	}

	public void setItems(ArrayList<PhysicalItem> itemsCSV) {
		ArrayList<Item> stock = new ArrayList<Item>();
		ArrayList<Item> barrowed = new ArrayList<Item>();
		for(PhysicalItem I: itemsCSV) {
			if(I.getBorrower().equals("BLANK")) {
				stock.add(I);
			}
			else {
				barrowed.add(I);
			}
		}
		this.stock = stock;
		this.borrowed = barrowed;
	}

	public void setSubscriptions(ArrayList<OnlineItem> subsCSV) {
		this.subscriptions = subsCSV;
	}

	public void setCourses(ArrayList<Course> coursesCSVs) {
		this.courses = coursesCSVs;
	}

	//Methods
	public ArrayList<Item> checkDue(User user){
		//Finds the user and returns their list of when their rented items are due
		return null;
	}

	public void checkPaymentInfo() {

	}

	public boolean checkUserPrivileges(User user) {
		return false;
	}




	//Setters
	public void addStock(Item item) {
		this.stock.add(item);
	}
	public void removeStock(Item item) {
		this.stock.remove(item);
	}

	public void addBarrowed(Item item) {
		this.borrowed.add(item);
	}
	public void removeBarrowed(Item item) {
		this.borrowed.remove(item);
	}
	public void addSub(OnlineItem sub) {
		this.subscriptions.add(sub);
	}
	public void removeSub(OnlineItem sub) {
		this.subscriptions.remove(sub);
	}

	public void addUser(User user) {
		if(user != null) {
			//When the user is allowed/has email and password, they can be added to the system it establishes a connection with the user to the system
			this.userlist.add(user);
			user.setSystem(this);
		}
		else {
			throw new NullPointerException();
		}
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public void removeCourse(Course course) {
		if(course != null) {
			this.courses.remove(course);
		}
	}

	//Getters
	public ArrayList<Item> getStock(){
		return this.stock;
	}
	public ArrayList<Item> getBorrowed(){
		return this.borrowed;
	}
	public ArrayList<User> getUsers(){
		return this.userlist;
	}
	public User getUser(String email) {
		for(User u: this.userlist) {
			if(u.getEmail().equals(email)) {
				u.setSystem(this);
				return u;
			}
		}
		return null;
	}
	public Course getCourse(String code) {
		for(Course C: this.courses) {
			if(C.getCode().equals(code)) {
				return C;
			}
		}
		return null;
	}

	public Item getItemAll(int id) {
		ArrayList<Item> items = new ArrayList<Item>(this.stock);
		items.addAll(this.borrowed);
		items.addAll(this.subscriptions);
		for(Item I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}

	public Item getPhysicalItem(int id) {
		ArrayList<Item> items = new ArrayList<Item>(this.stock);
		items.addAll(this.borrowed);
		for(Item I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}

	public Item getOnlineItem(int id) {
		ArrayList<Item> items = new ArrayList<Item>(this.subscriptions);
		for(Item I: items) {
			if(I.getId() == id) {
				return I;
			}
		}
		return null;
	}

	public int getStockOf(Item item) {
		if(item != null) {
			//Counts how many times the item appears in the stock
			return Collections.frequency(stock, item);
		}
		else {
			return 0;
		}

	}

	public ArrayList<OnlineItem> getSubs(){
		return this.subscriptions;
	}


	public ArrayList<Course> getCourses(){
		return this.courses;
	}

	public Course getCourseByCode(String code) {
		for(Course C: this.courses) {
			if(C.getCode().equals(code)) {
				return C;
			}
		}
		return null;
	}

	public ArrayList<Faculty> getFaculty() {
		ArrayList<Faculty> fac = new ArrayList<Faculty>();
		for(User I: this.userlist) {
			if(I.getClass() == (new Faculty()).getClass()) {
				fac.add((Faculty) I);
			}
		}

		return fac;
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> student = new ArrayList<Student>();
		for(User I: this.userlist) {
			if(I.getClass() == (new Student()).getClass()) {
				student.add((Student) I);
			}
		}

		return student;
	}

	public boolean existsInCSV(String path, String request, int col) {
		boolean found = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length > col && parts[col].trim().equalsIgnoreCase(request)) {
					found = true;
					break;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return found;
	}

}
