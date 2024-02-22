package foo;

import java.util.ArrayList;

public class Faculty extends User{

	private ArrayList<Item> textBooks;
	private ArrayList<Course> courses;
	
	public Faculty() {
		
	}
	
	public Faculty(ArrayList<Item> textBooks, ArrayList<Course> courses) {
		this.courses = courses;
		this.textBooks = textBooks;
	}
	
	//Setters
	public void removeTextBook(Item textbook) {
		//TODO: Oversimplification
		this.textBooks.remove(textbook);
	}
	
	public void removeCourse(Course course) {
		//TODO: Oversimplification
		this.courses.remove(course);
	}
	
	public void addTextBook(Item textbook) {
		//TODO: Oversimplification
		this.textBooks.add(textbook);
	}
	
	public void addCourse(Course course) {
		//TODO: Oversimplification
		this.courses.add(course);
	}
	
	//Getters
	public ArrayList<Item> getTextBooks() {
		return this.textBooks;
	}
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	
}
