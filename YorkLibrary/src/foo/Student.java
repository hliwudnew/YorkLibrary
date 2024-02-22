package foo;

import java.util.ArrayList;

public class Student extends User{

	private ArrayList<Course> courses;
	
	public Student() {
		
	}
	
	public Student(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	//Setters
	public void addCourse(Course course) {
		//TODO: Oversimplification
		this.courses.add(course);
	}
	
	public void dropCourse(Course course) {
		//TODO: Oversimplification
		this.courses.remove(course);
	}
	
	//Getters
	public ArrayList<Course> getCourses(){
		return this.courses;
	}
	
}
