package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import foo.CSVReader;
import foo.User;
import foo.UserFactory;



public class LoginGUI implements ActionListener {

	//Basic login
	private JFrame frame;
	private JFrame mgrFrame;
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
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnAdmin;
	private JButton btnRelogin;
	private JLabel lblTitle;
	//Verification stuff
	private ArrayList<User> accounts = new ArrayList<User>(CSVReader.loadPendingAccounts());
	
	public static void main(String[] args) {
		new LoginGUI();
	}
	public LoginGUI(){
		 try {
	         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        } 
	     catch (ClassNotFoundException e) {
	         e.printStackTrace();
	        }
	     catch (InstantiationException e) {
	         e.printStackTrace();
	        } 
	     catch (IllegalAccessException e) {
	         e.printStackTrace();
	        }
	     catch (UnsupportedLookAndFeelException e) {
	         e.printStackTrace();
	        }
	     catch (Exception e) {
	    	 e.printStackTrace();
	       }
		//UI
		frame = new JFrame(); // The window
		frame.getContentPane().setBackground(new Color(255, 128, 128));
		mgrFrame = new JFrame();
		loginPanel = new JPanel(); // Use to put stuff in the window
		loginPanel.setBackground(new Color(255, 128, 128));
		login = new JButton("Login");
		login.setBounds(100, 240, 446, 35);
		register = new JButton("Register");
		register.setBounds(100, 275, 446, 35);
		text1 = new JLabel("Email");
		text1.setBounds(100, 100, 446, 35);
		text2 = new JLabel("Password");
		text2.setBounds(100, 170, 446, 35);
		emailInput = new JTextField(100);
		emailInput.setLocation(100, 135);
		passwordInput = new JPasswordField(100);
		passwordInput.setBounds(100, 205, 446, 35);
		registrationPanel = new JPanel();
		registrationPanel.setBackground(new Color(255, 128, 128));
		layers = new JLayeredPane();
		layers.setBackground(new Color(255, 128, 128));
		layers.setSize(frame.getBounds().getSize());
		signup = new JButton("Register");
		btnRelogin = new JButton("Attempt Signin");
		text3 = new JLabel("Enter a valid email address and password");
		text4 = new JLabel("Select Account Type");
		
		
		//Account stuff
		Vector<String> accountTypes = new Vector<String>();
		accountTypes.add("Student");
		accountTypes.add("Faculty");
		accountTypes.add("Nonfaculty");
		accountTypes.add("Visitor");
		type = new JComboBox<String>(accountTypes);
		
		//Window Setup
		layers.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		layers.setLayout(new GridLayout(0,1));
		
		registrationPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		registrationPanel.setLayout(new GridLayout(0,1));
		
		//Configuration of UI, Use .bounds(0,0,0,0) to move around and give dimensions
		login.addActionListener(this);
		register.addActionListener(this);
		signup.addActionListener(this);
		btnRelogin.addActionListener(this);
		emailInput.setSize(446, 35);
		layers.add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.add(text1);
		loginPanel.add(emailInput);
		loginPanel.add(text2);
		loginPanel.add(passwordInput);
		loginPanel.add(login);
		loginPanel.add(register);
		
		lblTitle = new JLabel("York University Library");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 24));
		lblTitle.setBounds(142, 0, 371, 69);
		loginPanel.add(lblTitle);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addComponent(layers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layers, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
		);
		
		btnNewButton = new JButton("Leave");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	CSVReader.savePendingAccounts(accounts);
				mgrFrame.dispose();
				frame.dispose();
			}
		});
		
		btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerificationGUI(accounts,mgrFrame);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(648)
					.addComponent(btnAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnAdmin))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes window when closed (Set what happends when frame closed)
		frame.setTitle("York Library Login");
		frame.setSize(862, 649);
		frame.setVisible(true);
		//Saves everything to CSVs when the window closes
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	//Saves everything that happened
		    	CSVReader.savePendingAccounts(accounts);
		        //Closes windows
		    	frame.dispose();
		    }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent event) { // onClick Function
		String email = emailInput.getText();
		String password = passwordInput.getText();
		String accountType = type.getSelectedItem() +"";
		
		
		if(event.getSource() == login || event.getSource() == btnRelogin) {
			
			//Checks database to see if their information is correct to login
			if(CSVReader.loginData(email, password)) {
				//Closes window to open main application
				mgrFrame.dispose();
				frame.dispose();
				new MainGUI(email);
			}
			else {
                JOptionPane.showMessageDialog(frame, "Please Enter Valid Credentials");
				//Prompts the user to enter in proper email and password
				loginPanel.add(text3);
				layers.repaint();
				layers.revalidate();
			}
			
		}
		//Register
		else if(event.getSource() == register) {
			//RegistrationPanel Additions
			registrationPanel.add(lblTitle);
			registrationPanel.add(text1);
			registrationPanel.add(emailInput);
			registrationPanel.add(text2);
			registrationPanel.add(passwordInput);
			registrationPanel.add(text4);
			registrationPanel.add(type);
			registrationPanel.add(signup);
			registrationPanel.add(btnRelogin);
			
			//Swaps to Register Panel
			layers.removeAll();
			layers.add(registrationPanel);
			layers.repaint();
			layers.revalidate();
		}
		else if(event.getSource() == signup) {
			try {
				if(validateInput(email,password) && !CSVReader.checkEmail(email) && !CSVReader.checkEmailPending(email)) {
					//Sends a request to get the Student/Faculty/NonFaculty account verified
					UserFactory buildUser = new UserFactory();
					if(!accountType.equals("Visitor")) {
						User user = buildUser.getUser(accountType);
						user.setEmail(email);
						user.setPassword(password);
						accounts.add(user);
						
						CSVReader.savePendingAccounts(accounts);
		                JOptionPane.showMessageDialog(frame, "Account Has been submitted for verification");
						System.out.println("Account Has been submitted for verification");
					}
					else {
						//Calls database to add credentials and sign them in
						CSVReader.register(email, password,accountType);
						//Closes window to open main application
						mgrFrame.dispose();
						frame.dispose();
						new MainGUI(email);
					}
				}
				else {
					//Prompts the user to enter in proper email and password
					registrationPanel.add(text3);
					layers.repaint();
					layers.revalidate();
				}
			} catch (Exception e) {
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
