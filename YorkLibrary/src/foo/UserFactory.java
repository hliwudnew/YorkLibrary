package foo;

public class UserFactory {
	public User getUser(String user) {
		if(user != null) {
			if(user.equals("Student")) {
				return new Student();
			}
			else if(user.equals("Faculty")) {
				return new Faculty();
			}
			else if(user.equals("Nonfaculty")) {
				return new Nonfaculty();
			}
			else if(user.equals("Visitor")) {
				return new Visitor();
			}
			else {
				System.out.println("Invalid User type");
				return null;
			}
		}	
		else {
			System.out.println("Invalid User type");
			return null;
		}
	}
}
