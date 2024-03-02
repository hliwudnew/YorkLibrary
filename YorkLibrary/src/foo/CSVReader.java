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
					items.add(temp);
				}
				skip = false;
			}
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return items;
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
			buffWrite.write(email +"," + password+", null, null"+"\n"); //Appends to CSV file
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
	
	public static void disableItem(int id) {
		//Grabs items from database
		ArrayList<Item> items = new ArrayList<Item>(CSVReader.itemData());
		boolean found = false;
		//Finds record we want
		for(Item I : items) {
			if(I.getId() == id) {
				found = true;
				I.setDisabled(true);
			}
		}
		
		if(found) {
			try {
				String path ="src\\data\\Items.csv";
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
				buffWrite.write("Id,Name,Price,Disabled\n");// Rewrites the headers
				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
				//TODO: Possible better fix is figure out how to find the column and row of specific data
				for(Item I: items) {
					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+"\n");//Rewrites CSV file
				}
				buffWrite.close();// Closes the writer so the data saves
				System.out.println("Added to CSV");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Items doesnt exist");
		}
	}
	
	public static void enableItem(int id) {
		//Grabs items from database
		ArrayList<Item> items = new ArrayList<Item>(CSVReader.itemData());
		boolean found = false;
		//Finds record we want
		for(Item I : items) {
			if(I.getId() == id) {
				found = true;
				I.setDisabled(false);
			}
		}
		
		if(found) {
			try {
				String path ="src\\data\\Items.csv";
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
				buffWrite.write("Id,Name,Price,Disabled\n");// Rewrites the headers
				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
				//TODO: Possible better fix is figure out how to find the column and row of specific data
				for(Item I: items) {
					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+"\n");//Rewrites CSV file
				}
				buffWrite.close();// Closes the writer so the data saves
				System.out.println("Added to CSV");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Items doesnt exist");
		}
	}
	
	//Adds item to the CSV
	public static void addItem(int id, String name, double price, boolean disabled) {
		//TODO: make this check if there are already the max for that item
		try {
			String path ="src\\data\\Items.csv";
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path), true));
			// New line is important because it moves down a row
			buffWrite.write(id +"," + name+","+price+","+disabled+"\n"); //Appends to CSV file
			buffWrite.close();// Closes the writer so the data saves
			System.out.println("Added to CSV");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void removeItem(int id) {
		//Grabs items from database
		ArrayList<Item> items = new ArrayList<Item>(CSVReader.itemData());
		boolean found = false;
		//Finds record we want to remove
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId() == id) {
				items.remove(i);
				found = true;
			}
		}
		
		if(found) {
			try {
				String path ="src\\data\\Items.csv";
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(new File(path)));
				buffWrite.write("Id,Name,Price,Disabled\n");// Rewrites the headers
				//Writes updated data, Yes i know this rewrites the entire file, but I cannot find another way to only edit a single row without rows and columns coordinates
				//TODO: Possible better fix is figure out how to find the column and row of specific data
				for(Item I: items) {
					buffWrite.write(I.getId()+","+I.getName()+","+I.getPrice()+","+I.getDisabled()+"\n");//Rewrites CSV file
				}
				buffWrite.close();// Closes the writer so the data saves
				System.out.println("Added to CSV");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Items doesnt exist");
		}
	}
}
