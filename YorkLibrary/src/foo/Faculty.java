package foo;

import java.util.ArrayList;

public class Faculty extends User{

	private ArrayList<Item> textBooks;
	private ArrayList<Course> courses;
	
	public Faculty() {
		textBooks = new ArrayList<Item>();
		courses = new ArrayList<Course>();
	}
	
	public Faculty(ArrayList<Item> textBooks, ArrayList<Course> courses) {
		this.courses = courses;
		this.textBooks = textBooks;
	}
	
	//Setters
	public void removeTextBook(Item textbook) {

		boolean exists = this.textBooks.contains(textbook);

		if (exists && textbook != null) {
			this.textBooks.remove(textbook);
		} else if (!exists) {
			System.out.println("Textbook cannot be removed: Doesn't Exist");
		} else {
			System.out.println("Textbook cannot be removed: Null Value");
		}
	}
	
	public void removeCourse(Course course) {

		boolean exists = this.courses.contains(course);

		if (exists && course != null) {
			this.courses.remove(course);
		} else if (!exists) {
			System.out.println("Course cannot be removed: Doesn't Exist");
		} else {
			System.out.println("Course cannot be removed: Null Value");
		}
	}
	
	public void addTextBook(Item textbook) {

		if (textbook != null) {
			this.textBooks.add(textbook);
		} else {
			System.out.println("Textbook cannot be added: Null Value");
		}
	}
	
	public void addCourse(Course course) {
		if (course != null) {
			this.courses.add(course);
		} else {
			System.out.println("Course cannot be added: Null Value");
		}
	}
	
	//Getters
	public ArrayList<Item> getTextBooks() {
		return this.textBooks;
	}
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	
}
