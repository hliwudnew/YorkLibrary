package foo;

import java.util.ArrayList;

public class Student extends User{

	private ArrayList<String> courses;
	
	public Student() {
		
	}
	
	public Student(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	//Setters
	public void addCourse(String course) {
		//TODO: Oversimplification
		this.courses.add(course);
	}
	
	public void dropCourse(String course) {
		//TODO: Oversimplification
		this.courses.remove(course);
	}
	
	//Getters
	public ArrayList<String> getCourses(){
		return this.courses;
	}
	
}
