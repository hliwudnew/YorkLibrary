package foo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VerificationGUI {
	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_Email;
	private JTextField textField_Password;
	private JPanel loginPanel;
	
	
	public VerificationGUI(ArrayList<User> accounts, JFrame frame) {
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("Account Verification");
		frame.setSize(500, 500); // TODO: this is the correct size for the login screeen, have this one when not editing the page
		//frame.setSize(862, 649); //Should only be on when editing the page

		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "name_85182241325900");
		
		JLabel lblNewLabel_2 = new JLabel("Admin Login");
		lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Login");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_Email.getText().equals("admin@gmail.com") && textField_Password.getText().equals("123")) {
					((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "verificationPage_");
					frame.setSize(862, 649);
					frame.repaint();
					frame.revalidate();
				}
				else {
					System.out.println("Invalid Admin credentials");
				}
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		
		JLabel lblNewLabel_3_1 = new JLabel("Password");
		
		textField_Password = new JTextField();
		textField_Password.setColumns(10);
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(158)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(188))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(190)
					.addComponent(textField_Email)
					.addGap(208))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(190)
					.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(248))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(190)
					.addComponent(textField_Password)
					.addGap(208))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(200)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(227))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(190)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
					.addGap(250))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(61)
					.addComponent(lblNewLabel_2)
					.addGap(67)
					.addComponent(lblNewLabel_3)
					.addGap(11)
					.addComponent(textField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_3_1)
					.addGap(11)
					.addComponent(textField_Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnNewButton_3))
		);
		loginPanel.setLayout(gl_loginPanel);
		
		JPanel centerContent = new JPanel();
		frame.getContentPane().add(centerContent, "verificationPage_");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 193, 526, 417);
		
		JLabel lblNewLabel = new JLabel("Account Verification");
		lblNewLabel.setBounds(255, 48, 244, 28);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 20));
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User user: accounts) {
					if(user.getEmail().equals(textField.getText())) {
						try {
							//Signs the user up for an account
							CSVReader.register(user.getEmail(),user.getPassword(),user.getClass().toString().substring(10));
							accounts.remove(user);
							System.out.println("Verified: "+user.getEmail());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;// no need to loop
					}
				}
			}
		});
		btnAccept.setBounds(108, 339, 89, 23);
		
		textField = new JTextField();
		textField.setBounds(108, 313, 86, 20);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Accept by Email");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(93, 293, 107, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 452, 86, 20);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Reject");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User user: accounts) {
					if(user.getEmail().equals(textField_1.getText())) {
						accounts.remove(user);
						System.out.println("Rejected: "+user.getEmail());
						break;// no need to loop
					}
				}
			}
		});
		btnNewButton_1.setBounds(108, 478, 89, 23);
		
		JLabel lblNewLabel_1_1 = new JLabel("Reject by Email");
		lblNewLabel_1_1.setBounds(108, 434, 89, 14);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Account Type", "Email"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		centerContent.setLayout(null);
		centerContent.add(btnAccept);
		centerContent.add(textField);
		centerContent.add(lblNewLabel_1_1);
		centerContent.add(textField_1);
		centerContent.add(btnNewButton_1);
		centerContent.add(lblNewLabel_1);
		centerContent.add(scrollPane);
		centerContent.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "name_85182241325900");
				frame.setSize(500, 500);
				frame.repaint();
				frame.revalidate();
		    	//Saves everything that happened
		    	CSVReader.savePendingAccounts(accounts);
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(10, 11, 89, 23);
		centerContent.add(btnNewButton_2);
		updateTable(table,accounts);
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
	
	//updates given table for given user with ID, NAME, PRICE
	public void updateTable(JTable table, ArrayList<User> listToParse) {
		 DefaultTableModel clear = (DefaultTableModel) table.getModel();
			clear.setRowCount(0);
			for(User user : listToParse) {
				String[] rowdata = {user.getClass().toString().substring(10),user.getEmail()};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
	}
}

