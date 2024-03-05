package foo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * TIP: Don't have the data files open on your computer while running the code because the program cannot access them if they are already open
 * 
 */


public class CSVReader {
	
	private static String itemHeaders = "Id,Name,Price,Disabled,DueDate,Barrower,Fee\n";
	private static String subscriptionHeaders ="Name,Subscribers\n";
	private static String accountHeaders = "Email,Password,Rented,Subscriptions,Type,Courses,TextBooks\n";
	
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
		String path ="src\\data\\Accounts.csv";
		String line = "";
		ArrayList<User> Users = new ArrayList<User>();
		boolean result = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					User s = new Visitor();
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
			String path ="src\\data\\Accounts.csv";
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path), true));
			// New line is important because it moves down a row
			buffWrite.write(email +"," + password+",BLANK,BLANK,"+type+",BLANK,BLANK\n"); //Appends to CSV file
			buffWrite.close();// Closes the writer so the data saves
			System.out.println("Added to CSV");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Checks if the email is in the CSV file
	public static boolean checkEmail(String email) throws Exception {
		//This should work in as long as the CSV files are in the data folder/package
		String path ="src\\data\\Accounts.csv";
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
		ArrayList<PhysicalItem> items = new ArrayList<PhysicalItem>(system.getStock());
		items.addAll(system.getBorrowed());
		ArrayList<User> users = new ArrayList<User>(system.getUsers());
		// Saves all the items data and Account data
		try {
			String path ="src\\data\\Items.csv";
			String pathAccount ="src\\data\\Accounts.csv";
			
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
			buffWrite.write(itemHeaders);// Rewrites the headers
			//Saves item data
			for(PhysicalItem I: items) {
				buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+","+ I.getDueDate() +","+ I.getBorrower()+","+ I.getFee()+"\n");//Rewrites CSV file
			}
			buffWrite.close();// Closes the writer so the data saves
			
			BufferedWriter buffWrite2 = new BufferedWriter(new FileWriter(new File(pathAccount)));
			buffWrite2.write(accountHeaders);
			//Saves user data
			for(User u: users) {
				String type ="";
		    	String rented ="BLANK";
				if(u.getClass().toString().equals("class foo.Student")) {
					type = "Student";
				}
				else if(u.getClass().toString().equals("class foo.Faculty")) {
					type = "Faculty";
				}
				else if(u.getClass().toString().equals("class foo.Visitor")){
					type = "Visitor";
				}
				else {
					type = "NonFaculty";
				}
				buffWrite2.write(u.getEmail()+","+u.getPassword()+","+rented+","+"BLANK"+","+type+","+"BLANK,BLANK"+"\n");//Rewrites CSV file
			}
			
			buffWrite2.close();// Closes the writer so the data saves
			System.out.println("Added to CSV");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static LibrarySystem dowloadData(LibrarySystem system) {
		/*
		 *  Grabbing user data
		 */
		String path2 ="src\\data\\Accounts.csv";
		String line2 = "";
		ArrayList<User> users = new ArrayList<User>();
		boolean skip2 = true; // Not my greatest fix but skips the first row of column names
		
		try {
			BufferedReader br2 = new BufferedReader(new FileReader(path2));
			while((line2 = br2.readLine())!= null) {
				String[] values = line2.split(",");
				if(values.length != 0 && !skip2) {
					if(values[4].equals("Student")) {
						Student temp = new Student(null); //TODO: some how put all courses in this
						temp.setEmail(values[0]);
						temp.setPassword(values[1]);
						temp.setRented(new ArrayList<PhysicalItem>());
						temp.setSubscriptions(new ArrayList<OnlineItem>()); //TODO: some how put all subscriptions in here
						users.add(temp);
					}
					else if(values[4].equals("Faculty")) {
						Faculty temp = new Faculty(null,null); //TODO: Somehow put all courses and textbooks in this
						temp.setEmail(values[0]);
						temp.setPassword(values[1]);
						temp.setRented(new ArrayList<PhysicalItem>());
						temp.setSubscriptions(new ArrayList<OnlineItem>());
						users.add(temp);
					}
					else if(values[4].equals("NonFaculty")) {
						Nonfaculty temp = new Nonfaculty();
						temp.setEmail(values[0]);
						temp.setPassword(values[1]);
						temp.setRented(new ArrayList<PhysicalItem>());
						temp.setSubscriptions(new ArrayList<OnlineItem>());
						users.add(temp);
					}
					else if(values[4].equals("Visitor")) {
						Visitor temp = new Visitor();
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
		String path ="src\\data\\items.csv";
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
					boolean disabled = false;
					
					if(values[3].equals("true") ||values[3].equals("TRUE") ){
						disabled = true;
					}
					
					//All pulled from database
					temp.setId(Integer.valueOf(values[0]));
					temp.setName(values[1]);
					temp.setPrice(Double.valueOf(values[2]));
					temp.setDisabled(disabled);
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
//		//TODO: make this check if there are already the max for that item
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
