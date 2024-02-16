package foo;

import java.util.ArrayList;

public class Faculty extends User{

	private ArrayList<Item> textBooks;
	private ArrayList<String> courses;
	
	public Faculty() {
		
	}
	
	public Faculty(ArrayList<Item> textBooks, ArrayList<String> courses) {
		this.courses = courses;
		this.textBooks = textBooks;
	}
	
	//Setters
	public void removeTextBook(Item textbook) {
		//TODO: Oversimplification
		this.textBooks.remove(textbook);
	}
	
	public void removeCourse(String course) {
		//TODO: Oversimplification
		this.courses.remove(course);
	}
	
	public void addTextBook(Item textbook) {
		//TODO: Oversimplification
		this.textBooks.add(textbook);
	}
	
	public void addCourse(String course) {
		//TODO: Oversimplification
		this.courses.add(course);
	}
	
	//Getters
	public ArrayList<Item> getTextBooks() {
		return this.textBooks;
	}
	
	public ArrayList<String> getCourses() {
		return this.courses;
	}
	
	
}
