package foo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * TIP: Don't have the data files open on your computer while running the code because the program cannot access them if they are already open
 * 
 */


public class CSVReader {
	
//	public static void main(String[] args) { //Type is file name
//		//This should work in as long as the CSV files are in the data folder/package
//		String path ="src\\data\\Accounts.csv";
//		String line = "";
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(path));
//			while((line = br.readLine())!= null) {
//				String[] values = line.split(",");
//				//Fixes issues with blank spaces in csv file
//				if(values.length != 0) {
//					System.out.println(values[0]+ " " +values[1]);
//				}
//			}
//			System.out.println("Data has been read");
//		} catch(FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	//Checks if the user exists in the system
	public static boolean loginData(String email, String password) {
		String path ="src/data/Accounts.csv";
		String line = "";
		ArrayList<User> Users = new ArrayList<User>();
		UserFactory buildUser = new UserFactory();
		boolean result = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					User s = buildUser.getUser("Visitor");
					s.setEmail(values[0]);
					s.setPassword(values[1]);
					Users.add(s);
					System.out.println(values[0]+ " " +values[1]);
				}
			}
			//Checks if the credentials match
			for(User u: Users) {
				if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
					result = true;
					break;
				}
			}
			
			br.close();
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Registers the user to the database
	public static void register(String email, String password, String type) throws Exception {
		try {
			String path ="src/data/Accounts.csv";
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path), true));
			// New line is important because it moves down a row
			buffWrite.write(email +"," + password+",BLANK,BLANK,"+type+",BLANK,BLANK\n"); //Appends to CSV file
			buffWrite.close();// Closes the writer so the data saves
			System.out.println("Added to CSV");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> loadPendingAccounts() {
		String path2 ="src/data/PendingAccounts.csv";
		String line2 = "";
		ArrayList<User> users = new ArrayList<User>();
		UserFactory buildUser = new UserFactory();
		boolean skip2 = true; // Not my greatest fix but skips the first row of column names
		try {
			BufferedReader br2 = new BufferedReader(new FileReader(path2));
			while((line2 = br2.readLine())!= null) {
				String[] values = line2.split(",");
				if(values.length != 0 && !skip2) {
					User temp = buildUser.getUser(values[2]);
					if(temp != null) {
						temp.setEmail(values[0]);
						temp.setPassword(values[1]);
						users.add(temp);
					}
				}
				skip2 = false;
			}
			br2.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static void savePendingAccounts(ArrayList<User> users) {
		String pathAccount ="src/data/PendingAccounts.csv";
		String accountHeaders = "Email,Password,Type\n";
		try {
			BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(new File(pathAccount)));
			buffWrite2.write(accountHeaders);
			for(User u: users) {
				String type ="";
				if(u.getClass().toString().equals(new Student().getClass().toString())) {
					type = "Student";
				}
				else if(u.getClass().toString().equals(new Faculty().getClass().toString())) {
					type = "Faculty";
				}
				else if(u.getClass().toString().equals(new Visitor().getClass().toString())){
					type = "Visitor";
				}
				else {
					type = "NonFaculty";
				}
				buffWrite2.write(u.getEmail()+","+u.getPassword()+","+type+"\n");//Rewrites CSV file				
			}
			buffWrite2.close();// Closes the writer so the data saves
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Checks if the email is in the CSV file
	public static boolean checkEmail(String email) throws Exception {
		//This should work in as long as the CSV files are in the data folder/package
		String path ="src/data/Accounts.csv";
		String line = "";
		ArrayList<String> emails = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					emails.add(values[0]);
				}
			}
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Checks if the email is used already
		for(String s: emails) {
			if(s.equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	//Checks if the email is in the CSV file
	public static boolean checkEmailPending(String email) throws Exception {
		//This should work in as long as the CSV files are in the data folder/package
		String path ="src/data/PendingAccounts.csv";
		String line = "";
		ArrayList<String> emails = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					emails.add(values[0]);
				}
			}
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Checks if the email is used already
		for(String s: emails) {
			if(s.equals(email)) {
				return true;
			}
		}
		return false;
	}

	//Uploads everything to the CSV on close
	public static void upload(LibrarySystem system) {
		String itemHeaders = "Id,Name,Price,Disabled,DueDate,Barrower,Fee\n";
		String accountHeaders = "Email,Password,Rented,Subscriptions,Type,Courses,TextBooks\n";
		
		String subscribersHeader ="Id,Email\n";
		String subscriptionsHeader = "Id,Name,Price,Disabled,Link\n";
		
		String courseHeaders = "Code,Name\n";
		String courseFHeader = "Code,Faculty\n";
		String courseTHeader = "Code,TextBook\n";
		String courseSHeader = "Code,Student\n";
		
		String facultyTHeader = "Email,Id,Name\n";
		
		ArrayList<Item> items = new ArrayList<Item>(system.getStock());
		items.addAll(system.getBorrowed());
		ArrayList<User> users = new ArrayList<User>(system.getUsers());
		ArrayList<OnlineItem> subs = new ArrayList<OnlineItem>(system.getSubs());
		
		// Saves all the items data and Account data
		try {
			String path ="src/data/Items.csv";
			String pathAccount ="src/data/Accounts.csv";
			String pathSubs ="src/data/Subscriptions.csv";
			
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
			buffWrite.write(itemHeaders);// Rewrites the headers
			/*
			 * Saves item data
			 */
			for(Item I: items) {
				buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled().getState().getClass().toString().substring(10)+","+ ((PhysicalItem) I).getDueDate() +","+ ((PhysicalItem) I).getBorrower()+","+ ((PhysicalItem) I).getFee()+"\n");//Rewrites CSV file
			}
			buffWrite.close();// Closes the writer so the data saves
			
			BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(new File(pathAccount)));
			buffWrite2.write(accountHeaders);
			/*
			 * Saves user data
			 */
			for(User u: users) {
				String type ="";
		    	String rented ="BLANK";
				if(u.getClass().toString().equals(new Student().getClass().toString())) {
					type = "Student";
				}
				else if(u.getClass().toString().equals(new Faculty().getClass().toString())) {
					type = "Faculty";
				}
				else if(u.getClass().toString().equals(new Visitor().getClass().toString())){
					type = "Visitor";
				}
				else {
					type = "NonFaculty";
				}
				buffWrite2.write(u.getEmail()+","+u.getPassword()+","+rented+","+"BLANK"+","+type+","+"BLANK,BLANK"+"\n");//Rewrites CSV file				
			}
			
			buffWrite2.close();// Closes the writer so the data saves
			/*
			 * Saves subscription data
			 */
			BufferedWriter buffWrite3 = new BufferedWriter(new FileWriter(new File(pathSubs)));
			buffWrite3.write(subscriptionsHeader);// Rewrites the headers
			//Saves the subscriptions
			for(OnlineItem I: subs) {
				buffWrite3.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled().getState().getClass().toString().substring(10)+","+I.getLink()+"\n");//Rewrites CSV file	
			}
			buffWrite3.close();
			
			buffWrite3 = new BufferedWriter(new FileWriter(new File("src/data/Subscribers.csv")));
			buffWrite3.write(subscribersHeader);// Rewrites the headers
			
			//Saves the subscribers for a subscription/ who have a subscription
			for(OnlineItem I: subs) {
				for(User u: I.getSubscribers()) {
					buffWrite3.write(I.getId()+","+u.getEmail()+"\n");//Rewrites CSV file	
				}
			}
			buffWrite3.close();
			
			/*
			 * Saves courses data
			 */
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(new File("src/data/Courses.csv")));
			//Courses
			buffWriter.write(courseHeaders);// Rewrites the headers
			for(Course c: system.getCourses()) {
				buffWriter.write(c.getCode()+","+c.getName()+"\n");//Rewrites CSV file	
			}
			buffWriter.close();
			
			buffWriter = new BufferedWriter(new FileWriter(new File("src/data/CourseStudents.csv")));
			//Course Students
			buffWriter.write(courseSHeader);// Rewrites the headers
			for(Course c: system.getCourses()) {
				if(c.getStudents() != null) {
					for(Student u: c.getStudents()) {
						buffWriter.write(c.getCode()+","+u.getEmail()+"\n");//Rewrites CSV file	
					}
				}
			}
			buffWriter.close();
			
			buffWriter = new BufferedWriter(new FileWriter(new File("src/data/CourseFaculty.csv")));
			//Course Faculty
			buffWriter.write(courseFHeader);// Rewrites the headers
			for(Course c: system.getCourses()) {
				if(c.getFaculty() != null) {
					for(User u: c.getFaculty()) {
						buffWriter.write(c.getCode()+","+u.getEmail()+"\n");//Rewrites CSV file	
					}
				}
			}
			buffWriter.close();
			
			buffWriter = new BufferedWriter(new FileWriter(new File("src/data/CourseTextBooks.csv")));
			//Course Textbooks
			buffWriter.write(courseTHeader);// Rewrites the headers
			for(Course c: system.getCourses()) {
				if(c.getTextBooks() != null) {
					for(Item u: c.getTextBooks()) {
						buffWriter.write(c.getCode()+","+u.getId()+"\n");//Rewrites CSV file	
					}
				}
			}
			
			buffWriter.close();
			
			/*
			 * Save Faculty Textbooks
			 */
			buffWriter = new BufferedWriter(new FileWriter(new File("src/data/FacultyTextBooks.csv")));

			buffWriter.write(facultyTHeader);// Rewrites the headers
			//Saves all their textbooks
			for(Faculty f: system.getFaculty()) {
				for(Item i: f.getTextBooks()) {
					buffWriter.write(f.getEmail()+","+i.getId()+","+i.getName()+"\n");//Rewrites CSV file	
				}
			}
			buffWriter.close();
			
			System.out.println("Added to CSV");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static LibrarySystem dowloadData(LibrarySystem system) {
		/*
		 *  Grabbing user data
		 */
		String path2 ="src/data/Accounts.csv";
		String line2 = "";
		ArrayList<User> users = new ArrayList<User>();
		boolean skip2 = true; // Not my greatest fix but skips the first row of column names
		UserFactory buildUser = new UserFactory();
		try {
			BufferedReader br2 = new BufferedReader(new FileReader(path2));
			while((line2 = br2.readLine())!= null) {
				String[] values = line2.split(",");
				if(values.length != 0 && !skip2) {
					User temp = buildUser.getUser(values[4]);
					if(temp != null) {
						temp.setEmail(values[0]);
						temp.setPassword(values[1]);
						temp.setRented(new ArrayList<PhysicalItem>());
						temp.setSubscriptions(new ArrayList<OnlineItem>()); 
						users.add(temp);
					}
				}
				skip2 = false;
			}
			br2.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		system.setUsers(users);//Adds all the users from the CSV to the system
		
		/*
		 *  Grabbing Item data
		 */
		String path ="src/data/items.csv";
		String line = "";
		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>();
		boolean skip = true; // Not my greatest fix but skips the first row of column names
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skip) {
					PhysicalItem temp = new PhysicalItem();
					ItemStateContext status = new ItemStateContext(new Disabled());
					
					if(values[3].contains("Enabled") ||values[3].contains("ENABLED") ){
						status.setState(new Enabled());
					}
					
					//All pulled from database
					temp.setId(Integer.valueOf(values[0]));
					temp.setName(values[1]);
					temp.setPrice(Double.valueOf(values[2]));
					temp.setDisabled(status);
					temp.setDueDate(values[4]);
					temp.setBorrower(values[5]);
					temp.setFee(Double.valueOf(values[6]));
					//Assign Item to User
					if(!temp.getBorrower().equals("BLANK")) {
						system.getUser(temp.getBorrower()).rentPhysicalItem(temp);
					}
					items.add(temp);
				}
				skip = false;
			}
			br.close();
			system.setItems(items);//All Items have been added to the database
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 *  Grabbing Subscription Data
		 */
		String path3 ="src/data/Subscriptions.csv";
		String line3 = "";
		ArrayList<OnlineItem> subscriptions = new ArrayList<OnlineItem>();
		boolean skip3 = true; // Not my greatest fix but skips the first row of column names
		
		try {
			//Creates the subscriptions
			BufferedReader br = new BufferedReader(new FileReader(path3));
			while((line3 = br.readLine())!= null) {
				String[] values = line3.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skip3) {
					OnlineItem temp = new OnlineItem();
					ItemStateContext status = new ItemStateContext(new Disabled());
					
					if(values[3].contains("Enabled") ||values[3].contains("ENABLED")){
						status.setState(new Enabled());
					}
					
					//All pulled from database
					temp.setId(Integer.valueOf(values[0]));
					temp.setName(values[1]);
					temp.setPrice(Double.valueOf(values[2]));
					temp.setDisabled(status);
					temp.setLink(values[4]);
					
					subscriptions.add(temp);
				}
				skip3 = false;
			}
			br.close();
			//Assigned the users to the subscriptions
			skip3 = true;
			br = new BufferedReader(new FileReader("src/data/Subscribers.csv"));
			while((line3 = br.readLine())!= null) {
				String[] values = line3.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skip3) {
					for(OnlineItem I: subscriptions) {
						if((I.getId()+"").equals(values[0])) {
							system.getUser(values[1]).subscribe(I);;
						}
					}
					
				}
				skip3 = false;
			}
			br.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		system.setSubscriptions(subscriptions);
		
		/*
		 * Gets Courses Data
		 */
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			String pathCourse ="src/data/Courses.csv";
			String lineCourse = "";
			boolean skipC = true; // Not my greatest fix but skips the first row of column names
			
			//Grabs courses
			BufferedReader br = new BufferedReader(new FileReader(pathCourse));
			while((lineCourse = br.readLine())!= null) {
				String[] values = lineCourse.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skipC) {
					Course temp = new Course();
					
					temp.setCode(values[0]);
					temp.setName(values[1]);

					courses.add(temp);
				}
				skipC = false;
			}
			br.close();
			
			//Grabs Courses Faculty
			skipC= true;
			br = new BufferedReader(new FileReader("src/data/CourseFaculty.csv"));
			while((lineCourse = br.readLine())!= null) {
				String[] values = lineCourse.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skipC) {
					for(Course c: courses) {
						if(c.getCode().equals(values[0])) {
							for(Faculty f: system.getFaculty()) {
								if(f.getEmail().equals(values[1])) {
									c.addFaculty(f);
									f.addCourse(c);
								}
							}
						}
					}
					
				}
				skipC = false;
			}
			br.close();
			
			//Grabs Courses Students
			skipC= true;
			br = new BufferedReader(new FileReader("src/data/CourseStudents.csv"));
			while((lineCourse = br.readLine())!= null) {
				String[] values = lineCourse.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skipC) {
					for(Course c: courses) {
						if(c.getCode().equals(values[0])) {
							for(Student s: system.getStudents()) {
								if(s.getEmail().equals(values[1])) {
									c.addStudent(s);
									s.addCourse(c);
								}
							}
						}
					}
					
				}
				skipC = false;
			}
			br.close();
			
			//Grabs Courses TextBooks
			skipC= true;
			br = new BufferedReader(new FileReader("src/data/CourseTextBooks.csv"));
			while((lineCourse = br.readLine())!= null) {
				String[] values = lineCourse.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skipC) {
					for(Course c: courses) {
						if(c.getCode().equals(values[0])) {
							for(PhysicalItem I: items) {
								if(I.getId() ==Integer.valueOf(values[1])) {
									c.addTextBook(I);
								}
							}
						}
					}
					
				}
				skipC = false;
			}
			br.close();
			
			/*
			 * Gets Faculty textbooks
			 */
			//Grabs the faculty all their previous and current textbooks
			skip3 = true;
			br = new BufferedReader(new FileReader("src/data/FacultyTextBooks.csv"));
			while((line3 = br.readLine())!= null) {
				String[] values = line3.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0 && !skip3) {
					for(Faculty f: system.getFaculty()) {
						if(f.getEmail().equals(values[0])) {
							//Assigns their previous textbooks to their textbook list
							PhysicalItem temp = new PhysicalItem();
							temp.setId(Integer.valueOf(values[1]));
							temp.setName(values[2]);
							f.addTextBook(temp);
						}
					}
				}
				skip3 = false;
			}
			br.close();
			
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		system.setCourses(courses);
		
		
		
		System.out.println("Downloaded From CSV");
		return system;
	}
	
	/*
	 * This was to directly manipulate the CSV files, DO NOT USE PLEASE OR IT WILL BREAK THINGS. I might be required in the future hence I am leaving it here
	 * Again please DO NOT uncomment this or use it 
	 * - Gabriel
	 */
/*
//	public static void disableItem(int id) {
//		//Grabs items from database
//		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>(CSVReader.itemData());
//		boolean found = false;
//		//Finds record we want
//		for(PhysicalItem I : items) {
//			if(I.getId() == id) {
//				found = true;
//				I.setDisabled(true);
//			}
//		}
//		
//		if(found) {
//			try {
//				String path ="src\\data\\Items.csv";
//				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
//				buffWrite.write(itemHeaders);// Rewrites the headers
//				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
//				for(PhysicalItem I: items) {
//					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+ I.getDueDate() +","+ I.getBorrower()+","+ I.getFee() +","+"\n");//Rewrites CSV file
//				}
//				buffWrite.close();// Closes the writer so the data saves
//				System.out.println("Added to CSV");
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		else {
//			System.out.println("Items doesnt exist");
//		}
//	}
	
//	public static void enableItem(int id) {
//		//Grabs items from database
//		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>(CSVReader.itemData());
//		boolean found = false;
//		//Finds record we want
//		for(PhysicalItem I : items) {
//			if(I.getId() == id) {
//				found = true;
//				I.setDisabled(false);
//			}
//		}
//		
//		if(found) {
//			try {
//				String path ="src\\data\\Items.csv";
//				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
//				buffWrite.write(itemHeaders);// Rewrites the headers
//				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
//				for(PhysicalItem I: items) {
//					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+ I.getDueDate() +","+ I.getBorrower()+","+ I.getFee() +","+"\n");//Rewrites CSV file
//				}
//				buffWrite.close();// Closes the writer so the data saves
//				System.out.println("Added to CSV");
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		else {
//			System.out.println("Items doesnt exist");
//		}
//	}
	
//	//Adds item to the CSV
//	public static void addItem(int id, String name, double price, boolean disabled) {
//		try {
//			String path ="src\\data\\Items.csv";
//			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path), true));
//			// New line is important because it moves down a row
//			buffWrite.write(id +"," + name+","+price+","+disabled+",BLANK,BLANK,0\n"); //Appends to CSV file
//			buffWrite.close();// Closes the writer so the data saves
//			System.out.println("Added to CSV");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//	}

//	public static void removeItem(int id) {
//		//Grabs items from database
//		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>(CSVReader.itemData());
//		boolean found = false;
//		//Finds record we want to remove
//		for(int i = 0; i < items.size(); i++) {
//			if(items.get(i).getId() == id) {
//				items.remove(i);
//				found = true;
//			}
//		}
//		
//		if(found) {
//			try {
//				String path ="src\\data\\Items.csv";
//				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
//				buffWrite.write(itemHeaders);// Rewrites the headers
//				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
//				for(PhysicalItem I: items) {
//					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+ I.getDueDate() +","+ I.getBorrower()+","+ I.getFee() +","+"\n");//Rewrites CSV file
//				}
//				buffWrite.close();// Closes the writer so the data saves
//				System.out.println("Added to CSV");
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		else {
//			System.out.println("Items doesnt exist");
//		}
//	}
 * 
 */
	
}
