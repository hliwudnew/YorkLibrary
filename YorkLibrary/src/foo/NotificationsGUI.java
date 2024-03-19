package foo;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class NotificationsGUI {
	private JTable table;
	//Basic Setup
	
	
	//updates given table for given user with Type/Subject, Message
	public void updateTable(JTable table, User user, ArrayList<String> listToParse) {
		 DefaultTableModel clear = (DefaultTableModel) table.getModel();
			clear.setRowCount(0);
			for(String message : listToParse) {
				String[] data = message.split(",");
				String[] rowdata = {data[0].toString(),data[1]+""};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
	}
	
	public NotificationsGUI(JFrame frame, User loggedIn, LibrarySystem system) {
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel mainPage = new JPanel();
		mainPage.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(mainPage, "name_1113365353893500");
		
		JLabel lblNewLabel = new JLabel("Notifications");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Subject", "Message"
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
		
        table.getColumnModel().getColumn(1).setPreferredWidth(500); // Adjust the value as needed

		
		JButton btnNewButton = new JButton("Refresh");
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		GroupLayout gl_mainPage = new GroupLayout(mainPage);
		gl_mainPage.setHorizontalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGap(10)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(664))
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGap(273)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(101)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(114))
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGap(74)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
					.addGap(114))
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGap(11)
					.addComponent(btnNewButton_1)
					.addGap(109)
					.addGroup(gl_mainPage.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(8)
							.addComponent(btnNewButton)))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
					.addGap(43))
		);
		mainPage.setLayout(gl_mainPage);
		
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("Notifications");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
		frame.setVisible(true);
		
		//Checks to see if the user could have notifications, and fills the table accordingly
		updateTable(table, loggedIn, checkNotification(loggedIn,system));
	}
	
	
	//Used to check notifications
	public ArrayList<String> checkNotification(User loggedIn, LibrarySystem system) {//This is just tied to when the pages is opened
		ArrayList<String> notifications = new ArrayList<String>();
		/*
		 *  For putting a notification into this, format with: "SUBJECT,MESSAGE". COMMA IS REQUIRED
		 *  Just look at the example I did for new editions
		 */
		//checks if the account is a Faculty
		if(loggedIn.getClass().equals((new Faculty()).getClass())) {
			System.out.println("Faculty Notifications");
			//New Book editions check
			for(Item I: ((Faculty)loggedIn).getTextBooks()) {
				for(Item J: system.getStock()) {
					String[] owned = I.getName().split(" ");
					String[] stock = J.getName().split(" ");
					boolean newEdition = true;
					int ownedEdition = 1;
					int stockEdition = 1;
					
					//Checks if one of the books or both have an edition number
					if((isNumeric(owned[owned.length-1]) && isNumeric(stock[stock.length-1])) || (!isNumeric(owned[owned.length-1]) && isNumeric(stock[stock.length-1]))){
						//Lengths would equal if same book different edition, unless first edition with no number
						if(owned.length == stock.length || owned.length+1 == stock.length) {
							//Checks each part of the name of the book to compare
							for(int i = 0; i < owned.length; i++) {
								if(!owned[i].equals(stock[i])) {
									newEdition = false;
								}
							}
							
							//Owned editions can be original 1 or higher
							if(isNumeric(owned[owned.length-1])) {
								ownedEdition = Integer.valueOf(owned[owned.length-1]);
							}
							
							//Stock editions can be original 1 or higher
							if(isNumeric(stock[stock.length-1])) {
								stockEdition = Integer.valueOf(stock[stock.length-1]);
							}
						}
					}
					//Checks if the book is a new edition
					if(ownedEdition < stockEdition && newEdition) {
						//do notification
						String notification = "New Edition of "+I.getName()+", Your copy of: "+I.getName()+" has a new edition: "+ J.getName();
						notifications.add(notification);
					}
				}
			}
		}
		//other notification types might be thrown here or maybe in a different method call idk whichever is easier
		else {
			
		}
		//Returns all the found notifications
		return notifications;
	}
	
	//Used for checking if strings can be Integers or Doubles
	public boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} 
		catch(NumberFormatException e){  
			return false;  
		}  
	}
	
	//send a noti to the user about priority of their query for the requesting a new textbook
	public void sendNotification(String message, int priority) {
        String subject;
        if (priority == 1) {
            subject = "High Priority";
        } 
        else {
            subject = "Low Priority";
        }
        String[] rowData = {subject, message};
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
        tblModel.addRow(rowData);
    }
}
