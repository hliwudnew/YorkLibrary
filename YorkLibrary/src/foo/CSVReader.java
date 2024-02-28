package foo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * TIP: Don't have the data files open on your computer while running the code because the program cannot access them if they are already open
 * 
 */


public class CSVReader {

	//This works well, just need to modify to work with folder in the project
	public static void main(String[] args) { //Type is file name
		//This should work in as long as the CSV files are in the data folder/package
		String path ="src\\data\\Accounts.csv";
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					System.out.println(values[0]+ " " +values[1]);
				}
			}
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> userData() {
		String path ="src\\data\\Accounts.csv";
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					System.out.println(values[0]+ " " +values[1]);
				}
			}
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Item> itemData() {
		String path ="src\\data\\items.csv";
		String line = "";
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//Fixes issues with blank spaces in csv file
				if(values.length != 0) {
					System.out.println(values[0]+ " " +values[1]);
				}
			}
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
					Users.add(new User(values[0],values[1],null,null));
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
			
			
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Registers the user to the database
	public static void register(String email, String password) throws Exception {
		try {
			String path ="src\\data\\Accounts.csv";
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path), true));
			// New line is important because it moves down a row
			buffWrite.write("\n"+email +"," + password+", null, null"); //Appends to CSV file
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
	
}
