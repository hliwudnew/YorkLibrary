package foo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	//This works well, just need to modify to work with folder in the project
	public static void main(String[] args) {
		String path =""; //src/data/Accounts.csv
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while((line = br.readLine())!= null) {
				String[] values = line.split(",");
				System.out.println(values[0]+ " " +values[1] + " " + values[2]+ " " + values[3]);
			}
			System.out.println("Read");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
