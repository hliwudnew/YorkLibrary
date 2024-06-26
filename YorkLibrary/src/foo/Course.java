package foo;

import java.util.ArrayList;

public class Course {

	private String code;
	private String name;
	private ArrayList<PhysicalItem> textBooks;
	private ArrayList<Student> students;
	private ArrayList<Faculty> faculty;
	
	public Course() {
		this.textBooks = new ArrayList<PhysicalItem>();
		this.students = new ArrayList<Student>();
		this.faculty = new ArrayList<Faculty>();
	}
	
	public Course(String code,String name, ArrayList<PhysicalItem> textBooks, ArrayList<Student> students, ArrayList<Faculty> faculty) {
		this.code = code;
		this.name = name;
		this.textBooks = textBooks;
		this.students = students;
		this.faculty = faculty;
	}
	
	//Setters
	public void addTextBook(PhysicalItem textBook) {
		if(textBook != null) {
			this.textBooks.add(textBook);
		}
		else {
			System.out.println("Textbook entered is null");
		}

	}
	
	public void setName(String name) {
		if(name != "" && name != null) {
			this.name = name;
		}
		else {
			System.out.println("Name entered is null");
		}		
	}
	
	public void addStudent(Student student) {
		if(student != null) {
			this.students.add(student);
		}
		else {
			System.out.println("Student entered is null");
		}
	}
	
	public void addFaculty(Faculty faculty) {
		if(faculty != null) {
			this.faculty.add(faculty);
		}
		else {
			System.out.println("Faculty entered is null");
		}
	}
	
	public void removeTextBook(Item textBook) {
		boolean exists = this.textBooks.contains(textBook);
		
		if(textBook != null && exists) {
			this.textBooks.remove(textBook);
		}
		else if (exists) {
			System.out.println("Textbook entered is null");
		}
		else {
			System.out.println("Textbook entered doesnt exist");
		}
	}
	
	public void removeStudent(Student student) {
		boolean exists = this.students.contains(student);
		
		if(student != null && exists) {
			this.students.remove(student);
		}
		else if (exists) {
			System.out.println("Student entered is null");
		}
		else {
			System.out.println("Student entered doesnt exist");
		}
	}
	
	public void removeFaculty(Faculty faculty) {
		boolean exists = this.faculty.contains(faculty);
		
		if(faculty != null && exists) {
			this.faculty.remove(faculty);
		}
		else if (exists) {
			System.out.println("Faculty entered is null");
		}
		else {
			System.out.println("Faculty entered doesnt exist");
		}
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	//Getters
	public ArrayList<PhysicalItem> getTextBooks(){
		return this.textBooks;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Student> getStudents(){
		return this.students;
	}
	
	public ArrayList<Faculty> getFaculty(){
		return this.faculty;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
