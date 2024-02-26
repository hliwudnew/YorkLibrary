package foo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Each column that is read must have a given value, if it is empty then it breaks, Right now idk how to fix it 
 * so probably best to give everything default values, or if needed just fix this issue if it needs to be address
 * 
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
			//TODO: cool solution to read arbitrary amount of columns would be to read until no last column and then go down a row
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//This can break if the columns dont exist, or a value in it is empty
				System.out.println(values[0]+ " " +values[1]); //
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
				System.out.println(values[0]+ " " +values[1]);
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
				System.out.println(values[0]+ " " +values[1]);
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
				//Throws all the users into a temp array
				Users.add(new User(values[0],values[1],null,null));
				System.out.println(values[0]+ " " +values[1]);
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
	
	
}
