package foo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Each column that is read must have a given value, if it is empty then it breaks, Right now idk how to fix it 
 * so probably best to give everything default values, or if needed just fix this issue if it needs to be address
 * 
 * 
 */

public class CSVReader {
	//This works well, just need to modify to work with folder in the project
	public static void main(String[] args) {
		//This should work in as long as the CSV files are in the data folder/package
		String path ="src\\data\\Accounts.csv"; //TODO: make this dynamically work with multiple CSV files, unless we just throw all data into one lol
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				//This can break if the columns dont exist, or a value in it is empty
				System.out.println(values[0]+ " " +values[1] + " " + values[2]+ " " + values[3]); //
			}
			System.out.println("Data has been read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
