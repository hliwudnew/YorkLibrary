package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 *  Here is a quick crash course of how I have this set up because so far every tutorial I have looked at on swing is bad
 * 
 * Using JLayeredPane as a way to swap between different Panels without closing the frame (ie window)
 * So essentially make the GUI using panels and then swap them in and out of the layer for smooth transitions
 * 
 * I WOULD HIGHLY recommend looking at basic tutorials though, can't say I know how to do all of this perfectly
 * - Gabriel
 * 
 * */

public class LoginGUI implements ActionListener {

	//Basic login
	private JFrame frame;
	private JPanel loginPanel;
	private JButton login;
	private JButton register;
	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JTextField emailInput;
	private JPasswordField passwordInput;
	private JLayeredPane layers;
	//Registration
	private JPanel registrationPanel;
	private JButton signup;
	
	
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
		
		
		if(event.getSource() == login) {
			//TODO: Check database to see if their information is correct to login
			
			
			//Closes window to open main application
			frame.dispose();
			new MainGUI();
		}
		//Register
		else if(event.getSource() == register) {
			//RegistrationPanel Additions
			registrationPanel.add(text1);
			registrationPanel.add(emailInput);
			registrationPanel.add(text2);
			registrationPanel.add(passwordInput);
			registrationPanel.add(signup);
			
			//Swaps to Register Panel
			layers.removeAll();
			layers.add(registrationPanel);
			layers.repaint();
			layers.revalidate();
		}
		else if(event.getSource() == signup) {
			if(validateInput(email,password)) {
				//TODO: Call database to add credentials and sign them in
				
				//Closes window to open main application
				frame.dispose();
				new MainGUI();
			}
			else {
				//Prompts the user to enter in proper email and password
				registrationPanel.add(text3);
				layers.repaint();
				layers.revalidate();
			}
		}
	}
	
	//Returns Pass if they meet the criteria, if not it will return a message on what to fix
	public boolean validateInput(String email, String password) {
		//Checks if @ is in the right place
		String emailRegex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
			
		//TODO: Check if password is strong too
		//Checks if the email is valid
		if(matcher.matches() && email.contains(".com")) {
			return true;
		}
		else {
			return false;
		}
	}
}
