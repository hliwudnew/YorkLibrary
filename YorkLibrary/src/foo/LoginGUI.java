package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginGUI implements ActionListener {

	//Basic login
	private JFrame frame;
	private JPanel loginPanel;
	private JButton login;
	private JButton register;
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JLabel text4;
	private JTextField emailInput;
	private JPasswordField passwordInput;
	private JLayeredPane layers;
	//Registration
	private JPanel registrationPanel;
	private JButton signup;
	private JComboBox<String> type;
	
	
	public static void main(String[] args) {
		new LoginGUI();
	}
	public LoginGUI(){
		//UI
		frame = new JFrame(); // The window
		loginPanel = new JPanel(); // Use to put stuff in the window
		login = new JButton("Login");
		register = new JButton("Register");
		text1 = new JLabel("Email");
		text2 = new JLabel("Password");
		emailInput = new JTextField(100);
		passwordInput = new JPasswordField(100);
		registrationPanel = new JPanel();
		layers = new JLayeredPane();
		signup = new JButton("Register");
		text3 = new JLabel("Enter a valid email address and password");
		text4 = new JLabel("Select Account Type");
		
		//Account stuff
		Vector<String> accountTypes = new Vector<String>();
		accountTypes.add("Student");
		accountTypes.add("Faculty");
		accountTypes.add("NonFaculty");
		accountTypes.add("Visitor");
		type = new JComboBox<String>(accountTypes);
		
		//Window Setup
		layers.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); //TODO: Can this be a factory from class lol?
		layers.setLayout(new GridLayout(0,1));
		
		loginPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		loginPanel.setLayout(new GridLayout(0,1));
		
		registrationPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		registrationPanel.setLayout(new GridLayout(0,1));
		
		//Configuration of UI, Use .bounds(0,0,0,0) to move around and give dimensions
		login.addActionListener(this);
		register.addActionListener(this);
		signup.addActionListener(this);
		emailInput.setSize(100, 50);
		
		
		//LoginPanel Additions
		loginPanel.add(text1);
		loginPanel.add(emailInput);
		loginPanel.add(text2);
		loginPanel.add(passwordInput);
		loginPanel.add(login);
		loginPanel.add(register);
		layers.add(loginPanel);
		
		
		//Basically boilerplate
		frame.add(layers, BorderLayout.CENTER); //Adds the border to the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes window when closed (Set what happends when frame closed)
		frame.setTitle("York Library Login");
		frame.pack(); //Sets window to specific size
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) { // onClick Function
		String email = emailInput.getText();
		String password = passwordInput.getText();
		String accountType = type.getSelectedItem() +"";
		
		
		if(event.getSource() == login) {
			
			//Checks database to see if their information is correct to login
			if(CSVReader.loginData(email, password)) {
				//Closes window to open main application
				frame.dispose();
				new MainGUI(email);
			}
			else {
				//Prompts the user to enter in proper email and password
				loginPanel.add(text3);
				layers.repaint();
				layers.revalidate();
			}
			
		}
		//Register
		else if(event.getSource() == register) {
			//RegistrationPanel Additions
			registrationPanel.add(text1);
			registrationPanel.add(emailInput);
			registrationPanel.add(text2);
			registrationPanel.add(passwordInput);
			registrationPanel.add(text4);
			registrationPanel.add(type);
			registrationPanel.add(signup);
			
			//Swaps to Register Panel
			layers.removeAll();
			layers.add(registrationPanel);
			layers.repaint();
			layers.revalidate();
		}
		else if(event.getSource() == signup) {
			try {
				if(validateInput(email,password) && !CSVReader.checkEmail(email)) {
					
					//Calls database to add credentials and sign them in
					CSVReader.register(email, password,accountType);
					
					//Closes window to open main application
					frame.dispose();
					new MainGUI(email);
				}
				else {
					//Prompts the user to enter in proper email and password
					registrationPanel.add(text3);
					layers.repaint();
					layers.revalidate();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Returns Pass if they meet the criteria, if not it will return a message on what to fix
	public boolean validateInput(String email, String password) {
		//Checks if @ is in the right place, Email Checker
		String emailRegex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		
		//Password conditions
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean specialChar = false; 
        //Goofy way to do this but it is written nicely instead of being gross
        Set<Character> set = new HashSet<Character>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+')); 
        boolean goodPassword = false;
        
        //Checks each char in the password
        for (char i : password.toCharArray()) 
        { 
            if (Character.isLowerCase(i)) 
                hasLower = true; 
            if (Character.isUpperCase(i)) 
                hasUpper = true; 
            if (Character.isDigit(i)) 
                hasDigit = true; 
            if (set.contains(i)) 
                specialChar = true; 
        } 
        //Checks password
        if (hasDigit && hasLower && hasUpper && specialChar) {
        	goodPassword = true;
        }
        
		//Checks if the email is valid                 //checks if password is valid
		if((matcher.matches() && email.contains(".com")) && goodPassword){
			return true;
		}
		else {
			return false;
		}
	}

}
