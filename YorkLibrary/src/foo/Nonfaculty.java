package foo;

import java.util.ArrayList;

public class Nonfaculty extends User {
	private ArrayList<Course> courses;

	public Nonfaculty() {

	}

	public Nonfaculty(ArrayList<Course> courses) {
		this.courses = courses;
	}

	//Getters
	public ArrayList<Course> getCourses(){
		return this.courses;
	}

}
