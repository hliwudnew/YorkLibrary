package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;

public class MainGUI{
	//Basic Setup
	private JFrame frame;
	private JFrame mgrFrame;
	private JFrame notifyFrame;
	private JButton inventory;
	private JPanel mainPage;
	private JButton home;
	private JPanel rentPage;
	private JButton rent;
	private JPanel centerContent_Rent;
	private JPanel topBar_Rent;
	private JButton home_Rent;
	private JButton inventory_Rent;
	private JButton rent_Rent;
	private JLabel topBarTitle_Rent;
	private JPanel inventoryPage;
	private JPanel centerContent_Inv;
	private JPanel topBar_Inv;
	private JButton home_Inv;
	private JButton inventory_Inv;
	private JButton rent_Inv;
	private JLabel topBarTitle_Inv;
	private JTextField textField_Search;
	private JButton btnRefreshInventory_1;
	private JScrollPane displayScrollPane;
	private JTable displayTable;
	private JScrollPane rentScroll;
	private JTable searchTable;
	private JScrollPane inventoryScroll;
	private JTable inventoryTable;
	private JLabel lblName;
	private JTextField textField_Rent;
	private JTextField textField_Request;
	private JTextField textField_Return;
	private JButton btnSeeAll;
	private JPanel onlineItemsPage;
	private JPanel readOnlinePage;
	private JPanel physicalItemPage;
	private JScrollPane onlineScroll;
	private JTable onlineTable;
	private JTextField textField_UnSubscribe;
	private JTable tableRead;
	private JTextField textField_Read;
	private JPanel centerContent_Sub;
	private JPanel topBar_1;
	private JButton home_1;
	private JButton inventory_1;
	private JButton rent_1;
	private JButton Subscribe_3;
	private JLabel topBarTitle_1;
	private JTable tableSubs;
	private JTextField textField_Sub;
	private JTextField textField_1;
	private JPanel cartPage;
	private JPanel centerContentPanel;
	private JTable table;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JTextField textFieldRemove;
    private NotificationsGUI notificationsGUI;
    private JTable table_Courses;
    private JTable table_Textbooks;
    private JTable table_Old;
	
	public static void main(String[] args) {
		new MainGUI("test@gmail.com");
		//comment
	}
	
	//update methods to reuse code for updating tables in GUI:
	
	//updates given table for given user with ID, NAME, PRICE
	public <T extends Item> void updateTable1(JTable table, User user, ArrayList<T> listToParse) {
		//Clears the table of old data  
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(Item item : listToParse) {
			String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"",item.getStatus().getState().getClass().toString().substring(10)};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	//updates given table for given user with ID, NAME, PRICE, DISABLED STATUS
	//might not need this method? can even add more methods for different attribute displays
	//could get a bit messy but it is a nice quick fix to adding this code to every single button
	public <T extends Item> void updateTable2(JTable table, User user, ArrayList<T> listToParse) {
		//Clears the table of old data
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(Item item : listToParse) {
			String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"", item.getStatus().getState().getClass().toString().substring(10)};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	
	//updates a table with physicalItem's has an extra column for the due date of the item
	public <T extends Item> void updateTable3(JTable table, User user, ArrayList<PhysicalItem> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(PhysicalItem item : listToParse) {
			String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"", item.getStatus().getState().getClass().toString().substring(10)+"", item.getDueDate().toString()};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	//updates a table with physicalItem's has an extra column for the due date and time due in of the item and if lost (used only on home screen)
	public <T extends Item> void updateTable4(JTable table, User user, ArrayList<PhysicalItem> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(PhysicalItem item : listToParse) {
			String lostStatus="";
			//check each item and see if it is lost
			if(item.daysOverdue()>=15) {
				item.setLost(true);
			}
			else {
				item.setLost(false);
			}
			//depending on which item is lost will decide what is displayed in table
			if(item.isLost()) {
				lostStatus = "Lost";
			}
			else {
				lostStatus = "Not Lost";
			}
			String[] rowdata = {item.getId()+"",item.getName(), item.getStatus().getState().getClass().toString().substring(10)+"", item.getDueStatus(), item.getDueDate().toString(), lostStatus};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	//updates a table with physicalItem's has extra column for showing discount (rent table - searchTable)
	public <T extends Item> void updateTable5(JTable table, User user, ArrayList<T> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(Item item : listToParse) {
			String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"",item.getStatus().getState().getClass().toString().substring(10)+"", Double.toString(item.getDiscount()*100)};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	//updates a table with physicalItem's has extra column for showing discount (cart table)
	public <T extends Item> void updateTable6(JTable table, User user, ArrayList<T> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(Item item : listToParse) {
			String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"",Double.toString(item.getDiscount()*100)};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	
	public <T extends Item> void updateCoursesTable(JTable table, User user, ArrayList<Course> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Loops through the CSV data and adds it to the table
		for(Course c : listToParse) {
			String[] rowdata = {c.getCode()+"",c.getName()};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}
	
	public <T extends Item> void updateTextbooksTable(JTable table, User user, ArrayList<Course> listToParse) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		for(Course c : listToParse) {
			for(PhysicalItem i: c.getTextBooks()) {
				String[] rowdata = {c.getCode()+"",i.getId()+"",i.getName()};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
		}
	}
	
	public <T extends Item> void updateOldTextbooksTable(JTable table, Faculty user) {
		//Clears the table of old data 
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		
		ArrayList<String> dupes = new ArrayList<String>();
		
		//Finds all the textbooks that are currently in a course the faculty is in
		for(Course c: user.getCourses()) {
			for(Item i : c.getTextBooks()) {
				for(Item j: user.getTextBooks()) {
					if(j.getName().equals(i.getName())) {
						dupes.add(j.getName());
					}
				}
			}
		}
		//NOTE: had to do it this way because the faculty objects for old and new textbooks had to not be the same
		//Only allows the old textbooks that are not in a current course they are in to be listed here
		for(Item i: user.getTextBooks()) {
			if(!dupes.contains(i.getName())) {
				String[] rowdata = {i.getName()};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
		}
	}			
		
	public PaymentContext debitPopup() {
		JTextField name = new JTextField();
		JTextField card = new JTextField();
		JTextField expire = new JTextField();
		JTextField cvv = new JTextField();
		Object[] message= {
				"Enter name:", name,
				"Enter card number:", card,
				"Enter expire date:", expire,
				"Enter cvv:", cvv
		};
		int option = JOptionPane.showConfirmDialog(frame, message, "Enter Debit Card Information", JOptionPane.OK_CANCEL_OPTION);
		if (option==JOptionPane.CANCEL_OPTION) {
			return null;
		}
		PaymentContext payment = new PaymentContext(new DebitCardStrategy(name.getText(),card.getText(),expire.getText(),cvv.getText()));
		return payment;
		
	}
	public PaymentContext creditPopup() {
		JTextField name = new JTextField();
		JTextField card = new JTextField();
		JTextField expire = new JTextField();
		JTextField cvv = new JTextField();
		Object[] message= {
				"Enter name:", name,
				"Enter card number:", card,
				"Enter expire date:", expire,
				"Enter cvv:", cvv
		};
		int option = JOptionPane.showConfirmDialog(frame, message, "Enter Credit Card Information", JOptionPane.OK_CANCEL_OPTION);
		if (option==JOptionPane.CANCEL_OPTION) {
			return null;
		}
		PaymentContext payment = new PaymentContext(new CreditCardStrategy(name.getText(),card.getText(),expire.getText(),cvv.getText()));
		return payment;
	}
	public PaymentContext paypalPopup() {
		JTextField email = new JTextField();
		Object[] message= {
				"Enter email:", email,
		};
		int option = JOptionPane.showConfirmDialog(frame, message, "Enter Paypal Information", JOptionPane.OK_CANCEL_OPTION);
		if (option==JOptionPane.CANCEL_OPTION) {
			return null;
		}
		PaymentContext payment = new PaymentContext(new PayPalStrategy(email.getText()));
		return payment;
	}
	public PaymentContext giftPopup() {
		JTextField card = new JTextField();
		Object[] message= {
				"Enter card number:", card,
		};
		int option = JOptionPane.showConfirmDialog(frame, message, "Enter Gift Card Information", JOptionPane.OK_CANCEL_OPTION);
		if (option==JOptionPane.CANCEL_OPTION) {
			return null;
		}
		PaymentContext payment = new PaymentContext(new GiftCardStrategy(card.getText()));
		return payment; 
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainGUI(String email) {
		System.out.println("Logged in");
		   	//use UI manager to set "look and feel" which is the style of all components in the system
			//subject to change, this is just one preset style
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
	     
	     
	     //read images from files
	     Image homeIcon =null;
	     Image cartIcon =null;
	     Image notiIcon =null;
	     Image addIcon =null;
	     Image removeIcon =null;
	     Image refreshIcon =null;
	     Image searchIcon =null;
			try {
				homeIcon = (ImageIO.read(new FileInputStream("src/images/homeIcon.png"))).getScaledInstance(10, 10, Image.SCALE_DEFAULT);
				notiIcon= (ImageIO.read(new FileInputStream("src/images/notifIcon.png"))).getScaledInstance(10, 10, Image.SCALE_DEFAULT);
				cartIcon= (ImageIO.read(new FileInputStream("src/images/cartIcon.png"))).getScaledInstance(10, 10, Image.SCALE_DEFAULT);
				addIcon = (ImageIO.read(new FileInputStream("src/images/addIcon.png"))).getScaledInstance(15, 15, Image.SCALE_DEFAULT);
				removeIcon= (ImageIO.read(new FileInputStream("src/images/removeIcon.png"))).getScaledInstance(10, 10, Image.SCALE_DEFAULT);
				refreshIcon= (ImageIO.read(new FileInputStream("src/images/refreshIcon.png"))).getScaledInstance(23, 23, Image.SCALE_DEFAULT);
				searchIcon= (ImageIO.read(new FileInputStream("src/images/searchIcon.png"))).getScaledInstance(15, 15, Image.SCALE_DEFAULT);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		//Populate the system with data from CSVs
		LibrarySystem system = CSVReader.dowloadData(new LibrarySystem());
		
		User loggedIn = system.getUser(email);
		
		
		
		frame = new JFrame();
		mgrFrame = new JFrame();
		notifyFrame = new JFrame();
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		mainPage = new JPanel();
		frame.getContentPane().add(mainPage, "homePage_");
		
		JPanel centerContent = new JPanel();
		centerContent.setBackground(Color.WHITE);
		
		JPanel topBar = new JPanel();
		topBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar.setBackground(new Color(227, 24, 55));
				
		inventory = new JButton("My Inventory");
		inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions());
				updateTable2(tableRead, loggedIn, loggedIn.getSubscriptions());
				//Updates the Course and Textbook tabs according to user account infromation
				if(loggedIn.getClass().equals((new Student()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getStudents().contains(loggedIn)) {
							courses.add(c);
						}
					}
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
				}
				else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getFaculty().contains(loggedIn)) {
							courses.add(c);
						}
					}
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
					updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
				}
			}
		});
		
		home = new JButton("Home");
		

		
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
			}
		});
		
		JLabel topBarTitle = new JLabel("York Library");
		topBarTitle.setForeground(Color.WHITE);
		topBarTitle.setFont(new Font("Book Antiqua", Font.PLAIN, 28));
		topBarTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_mainPage = new GroupLayout(mainPage);
		gl_mainPage.setHorizontalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContent, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContent, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
		);
		
		btnRefreshInventory_1 = new JButton("Refresh");
		btnRefreshInventory_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
			}
		});
		
		displayScrollPane = new JScrollPane();
		
		
		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//Provides them to the GUI
				new NotificationsGUI(notifyFrame, loggedIn,system);
			}
		});
		
		
		
		GroupLayout gl_centerContent = new GroupLayout(centerContent);
		gl_centerContent.setHorizontalGroup(
			gl_centerContent.createParallelGroup(Alignment.TRAILING)
				.addComponent(topBar, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addGroup(gl_centerContent.createSequentialGroup()
					.addGap(108)
					.addGroup(gl_centerContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(displayScrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
						.addGroup(gl_centerContent.createSequentialGroup()
							.addGap(450)
							.addComponent(btnRefreshInventory_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
					.addGap(197))
				.addGroup(gl_centerContent.createSequentialGroup()
					.addGap(745)
					.addComponent(btnNotifications, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_centerContent.setVerticalGroup(
			gl_centerContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent.createSequentialGroup()
					.addComponent(topBar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNotifications)
					.addGap(72)
					.addComponent(btnRefreshInventory_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayScrollPane, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
					.addGap(119))
		);

		
		displayTable = new JTable();
		displayTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Status", "Due In", "Due Date", "Lost"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		displayScrollPane.setViewportView(displayTable);
		
		//after table on home page is initialized, populate the table (same functionality as refresh, but it does it on startup now)
		//Clears the table of old data
		updateTable4(displayTable, loggedIn, loggedIn.getRented());
		
		rent = new JButton("Rent");
		rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable5(searchTable, loggedIn, system.getStock());
			}
		});
		
		lblName = new JLabel("{email}");
		lblName.setText(email);
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		lblName.setForeground(new Color(0, 0, 255));
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagementTeamGUI(system, mgrFrame,loggedIn);
			}
		});
		
		JButton Subscribe = new JButton("Subscribe");
		Subscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "subscribePage_");
				frame.repaint();
				frame.revalidate();
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "cart_");
				frame.repaint();
				frame.revalidate();
				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar = new GroupLayout(topBar);
		gl_topBar.setHorizontalGroup(
			gl_topBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(home, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(inventory, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Subscribe, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(38)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_topBar.setVerticalGroup(
			gl_topBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addGroup(gl_topBar.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName)
						.addComponent(btnAdmin))
					.addContainerGap())
				.addGroup(gl_topBar.createSequentialGroup()
					.addGroup(gl_topBar.createParallelGroup(Alignment.BASELINE)
						.addComponent(topBarTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_topBar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topBar.createParallelGroup(Alignment.BASELINE)
						.addComponent(home)
						.addComponent(inventory)
						.addComponent(rent)
						.addComponent(Subscribe))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topBar.setLayout(gl_topBar);
		centerContent.setLayout(gl_centerContent);
		mainPage.setLayout(gl_mainPage);
		
		rentPage = new JPanel();
		rentPage.setBackground(Color.GRAY);
		frame.getContentPane().add(rentPage, "rentPage_");
		
		centerContent_Rent = new JPanel();
		centerContent_Rent.setBackground(Color.WHITE);
		
		topBar_Rent = new JPanel();
		topBar_Rent.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar_Rent.setBackground(new Color(227, 24, 55));
		
		home_Rent = new JButton("Home");
		home_Rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
				
				
			}
		});
		
		inventory_Rent = new JButton("My Inventory");
		inventory_Rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions());
				updateTable2(tableRead, loggedIn, loggedIn.getSubscriptions());
				//Updates the Course and Textbook tabs according to user account infromation
				if(loggedIn.getClass().equals((new Student()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getStudents().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
				}
				else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getFaculty().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
					updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
				}
			}
		});
		
		rent_Rent = new JButton("Rent");
		rent_Rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
			}
		});
		
		topBarTitle_Rent = new JLabel("Rentings");
		topBarTitle_Rent.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_Rent.setForeground(Color.WHITE);
		topBarTitle_Rent.setFont(new Font("Book Antiqua", Font.PLAIN, 28));
		
		JButton Subscribe_1 = new JButton("Subscribe");
		Subscribe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "subscribePage_");
				frame.repaint();
				frame.revalidate();
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
			}
		});
		
		btnNewButton_2 = new JButton("Cart");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "cart_");
				frame.repaint();
				frame.revalidate();
				updateTable6(table, loggedIn,loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar_Rent = new GroupLayout(topBar_Rent);
		gl_topBar_Rent.setHorizontalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addContainerGap()
					.addComponent(home_Rent, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGap(9)
					.addComponent(inventory_Rent, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_Rent, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Subscribe_1, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle_Rent, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(78)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(272))
		);
		gl_topBar_Rent.setVerticalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_topBar_Rent.createParallelGroup(Alignment.BASELINE)
						.addComponent(rent_Rent)
						.addComponent(inventory_Rent)
						.addComponent(home_Rent)))
				.addGroup(gl_topBar_Rent.createParallelGroup(Alignment.BASELINE)
					.addComponent(topBarTitle_Rent, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton_2)
					.addComponent(Subscribe_1))
		);
		topBar_Rent.setLayout(gl_topBar_Rent);
		
		textField_Search = new JTextField();
		textField_Search.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel pReccomendation = new JPanel();
		pReccomendation.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		JButton bthSearch = new JButton("Search");
		bthSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = textField_Search.getText().trim().toLowerCase();
				ArrayList<Item> searchResults = new ArrayList<>();

				for (Item item : system.getStock()) {
					if (item.getName().toLowerCase().equals(searchQuery)) {
						searchResults.add(item);
					}
				}

				ArrayList<Item> recommendations = recommendations(searchQuery);
				for (Item recommendation : recommendations) {
					if (!searchResults.contains(recommendation)) {
						searchResults.add(recommendation);
					}
				}

				DefaultTableModel searchTableModel = (DefaultTableModel) searchTable.getModel();
				searchTableModel.setRowCount(0);

				if (!searchResults.isEmpty()) {
					for (Item result : searchResults) {
						String[] rowData = {String.valueOf(result.getId()), result.getName(), String.valueOf(result.getPrice()), String.valueOf(result.getStatus().getState().getClass().toString().substring(10)), String.valueOf(result.getDiscount()*100)};
						searchTableModel.addRow(rowData);
					}
				} else {
					String[] rowData = {"N/A", "N/A", "N/A", "N/A", "N/A"};
					searchTableModel.addRow(rowData);
				}
			}

			private ArrayList<Item> recommendations(String searchQuery) {
				ArrayList<Item> similarBooks = new ArrayList<>();
				for (Item item : system.getStock()) {
					String title = item.getName().toLowerCase();
					if (title.contains(searchQuery)) {
						similarBooks.add(item);
					}
				}
				return similarBooks;
			}
		});
		
		btnSeeAll = new JButton("See All");
		btnSeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateTable5(searchTable, loggedIn, system.getStock());
			}
		});

		
		rentScroll = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JPanel panelRequest = new JPanel();
		panelRequest.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		GroupLayout gl_centerContent_Rent = new GroupLayout(centerContent_Rent);
		gl_centerContent_Rent.setHorizontalGroup(
			gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(98)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addGap(74)
									.addComponent(searchLabel, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
									.addGap(77))
								.addComponent(textField_Search, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addGap(105)
									.addComponent(bthSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(104)))
							.addGap(90))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addComponent(btnSeeAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(397))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addComponent(rentScroll)
							.addGap(10)))
					.addGap(0)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 198, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelRequest, GroupLayout.PREFERRED_SIZE, 198, Short.MAX_VALUE)
					.addGap(24))
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addComponent(topBar_Rent, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_centerContent_Rent.setVerticalGroup(
			gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addComponent(topBar_Rent, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(35)
							.addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addComponent(bthSearch)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addComponent(btnSeeAll)
									.addGap(3)))
							.addComponent(rentScroll, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
								.addComponent(panelRequest, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
							.addGap(208)))
					.addContainerGap())
		);
		
		textField_Rent = new JTextField();
		textField_Request = new JTextField();
		textField_Rent.setColumns(10);
		textField_Request.setColumns(10);

		
		JLabel lblRent = new JLabel("Add to Cart");
		lblRent.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Add by ID");
		
		JButton btnRent = new JButton("Add");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loggedIn.rentPhysicalItem((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Rent.getText())));
				if(!textField_Rent.getText().isEmpty()){
					//Prevents disabled Items from being rented
					if(((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Rent.getText()))).getStatus().getState().getClass().equals(new Disabled().getClass())){
						JOptionPane.showMessageDialog(frame, "Item is currently disabled and unable to be rented.", "Failed to Add to Cart", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						//when user clicks add button, menu (invoker) calls the add command and then it gets added to cart 
						loggedIn.getMenu().clickAdd(((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Rent.getText()))));
					}
				}

			}
		});
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(textField_Rent, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(35))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(57)
					.addComponent(btnRent, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
					.addGap(73))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(61)
					.addComponent(lblRent, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addGap(52))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRent)
					.addGap(49)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Rent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRent)
					.addContainerGap(275, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblRequest = new JLabel("Request a New Book");
		JLabel lblRequest1 = new JLabel("<html>Note: Course teaching textbooks will be <br>given higher priorty than <br>other textbooks<html>");
		lblRequest1.setHorizontalAlignment(SwingConstants.CENTER);

		lblRequest.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JLabel lblNewLabelRequest = new JLabel("Add by name");
		
	    notificationsGUI = new NotificationsGUI(notifyFrame, loggedIn, system);

		JButton btnRequest = new JButton("Request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField_Request.getText().isEmpty()) {
	                boolean exists = false;
	                for(Course c: system.getCourses()) {
	                	for(Item i: c.getTextBooks()) {
	                		if(i.getName().equals(textField_Request.getText())) {
	                			exists = true;
	                			break;
	                		}
	                	}
	                }
	                
	                if (exists) {
	                	System.out.println("high priority");
	                    String notification = "The requested textbook '" + textField_Request.getText() + "' is a course teaching textbook and the request will be handled promptly.";
	                    notificationsGUI.sendNotification(notification, 1);
		                JOptionPane.showMessageDialog(frame, "Request received and set to high priority! Check your notification tab on the main page for more info!");

	                } 
	                else {
	                	System.out.println("low priority");
	                    String notification = "The requested textbook '" + textField_Request.getText() + "' is not a course teaching textbook and has been given lower priority, but will still be handled shortly.";
	                    notificationsGUI.sendNotification(notification, 2);
		                JOptionPane.showMessageDialog(frame, "Request received and set to low priority! Check your notification tab on the main page for more info!");

	                }
	                

	            }
			}
		});	
		
		GroupLayout gl_panelRequest = new GroupLayout(panelRequest);
		gl_panelRequest.setHorizontalGroup(
			gl_panelRequest.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRequest.createSequentialGroup()
					.addGap(57)
					.addComponent(btnRequest, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(gl_panelRequest.createSequentialGroup()
					.addComponent(lblRequest1, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
					.addGap(4))
				.addGroup(Alignment.TRAILING, gl_panelRequest.createSequentialGroup()
					.addGap(23)
					.addComponent(lblRequest, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addGap(18))
				.addGroup(Alignment.TRAILING, gl_panelRequest.createSequentialGroup()
					.addGap(25)
					.addComponent(textField_Request, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
					.addGap(42))
				.addGroup(gl_panelRequest.createSequentialGroup()
					.addGap(64)
					.addComponent(lblNewLabelRequest, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(71))
		);
		gl_panelRequest.setVerticalGroup(
			gl_panelRequest.createParallelGroup(Alignment.LEADING, false)
				.addGroup(gl_panelRequest.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRequest)
					.addGap(49)
					.addComponent(lblRequest1)
					.addGap(49)
					.addComponent(lblNewLabelRequest)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Request, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnRequest)
					.addContainerGap(184, Short.MAX_VALUE))
		);
		panelRequest.setLayout(gl_panelRequest);		
		
		searchTable = new JTable();
		searchTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Status", "Discount(%)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		rentScroll.setViewportView(searchTable);
//		JLabel recomendationsLabel = new JLabel("Recomendations");
//		recomendationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		recomendationsLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
//		GroupLayout gl_pReccomendation = new GroupLayout(pReccomendation);
//		gl_pReccomendation.setHorizontalGroup(
//			gl_pReccomendation.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_pReccomendation.createSequentialGroup()
//					.addGap(10)
//					.addComponent(recomendationsLabel)
//					.addContainerGap(23, Short.MAX_VALUE))
//		);
//		gl_pReccomendation.setVerticalGroup(
//			gl_pReccomendation.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_pReccomendation.createSequentialGroup()
//					.addGap(11)
//					.addComponent(recomendationsLabel)
//					.addContainerGap(303, Short.MAX_VALUE))
//		);
//		pReccomendation.setLayout(gl_pReccomendation);
		centerContent_Rent.setLayout(gl_centerContent_Rent);
		GroupLayout gl_rentPage = new GroupLayout(rentPage);
		gl_rentPage.setHorizontalGroup(
			gl_rentPage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContent_Rent, GroupLayout.PREFERRED_SIZE, 846, Short.MAX_VALUE)
		);
		gl_rentPage.setVerticalGroup(
			gl_rentPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rentPage.createSequentialGroup()
					.addComponent(centerContent_Rent, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
					.addGap(1))
		);
		rentPage.setLayout(gl_rentPage);
		
		inventoryPage = new JPanel();
		inventoryPage.setBackground(Color.GRAY);
		frame.getContentPane().add(inventoryPage, "inventoryPage_");
		
		centerContent_Inv = new JPanel();
		centerContent_Inv.setBackground(Color.WHITE);
		
		topBar_Inv = new JPanel();
		topBar_Inv.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar_Inv.setBackground(new Color(227, 24, 55));
		
		home_Inv = new JButton("Home");
		home_Inv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
			}
		});
		
		inventory_Inv = new JButton("My Inventory");
		inventory_Inv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
				//Updates the Course and Textbook tabs according to user account infromation
				if(loggedIn.getClass().equals((new Student()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getStudents().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
				}
				else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getFaculty().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
					updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
				}
			}
		});
		
		rent_Inv = new JButton("Rent");
		rent_Inv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable5(searchTable, loggedIn, system.getStock());
			}
		});
		
		topBarTitle_Inv = new JLabel("My Inventory");
		topBarTitle_Inv.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_Inv.setForeground(Color.WHITE);
		topBarTitle_Inv.setFont(new Font("Book Antiqua", Font.PLAIN, 28));
		
		JButton Subscribe_2 = new JButton("Subscribe");
		Subscribe_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "subscribePage_");
				frame.repaint();
				frame.revalidate();
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
			}
		});
		
		btnNewButton_3 = new JButton("Cart");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "cart_");
				frame.repaint();
				frame.revalidate();
				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar_Inv = new GroupLayout(topBar_Inv);
		gl_topBar_Inv.setHorizontalGroup(
			gl_topBar_Inv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(9)
					.addComponent(home_Inv)
					.addGap(10)
					.addComponent(inventory_Inv)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_Inv)
					.addGap(10)
					.addComponent(Subscribe_2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(topBarTitle_Inv, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addGap(46)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(202))
		);
		gl_topBar_Inv.setVerticalGroup(
			gl_topBar_Inv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(11)
					.addComponent(home_Inv))
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(11)
					.addComponent(inventory_Inv))
				.addGroup(gl_topBar_Inv.createParallelGroup(Alignment.BASELINE)
					.addComponent(rent_Inv)
					.addComponent(Subscribe_2)
					.addComponent(topBarTitle_Inv, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton_3))
		);
		topBar_Inv.setLayout(gl_topBar_Inv);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_centerContent_Inv = new GroupLayout(centerContent_Inv);
		gl_centerContent_Inv.setHorizontalGroup(
			gl_centerContent_Inv.createParallelGroup(Alignment.LEADING)
				.addComponent(topBar_Inv, GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
		);
		gl_centerContent_Inv.setVerticalGroup(
			gl_centerContent_Inv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Inv.createSequentialGroup()
					.addComponent(topBar_Inv, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
		);
		
		physicalItemPage = new JPanel();
		physicalItemPage.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Physical Items", null, physicalItemPage, null);
		tabbedPane.setBackgroundAt(0, new Color(192, 192, 192));
		
		inventoryScroll = new JScrollPane();
		
		inventoryTable = new JTable();
		inventoryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Status", "Due Date"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		inventoryScroll.setViewportView(inventoryTable);
		
		JButton btnRefreshInventory = new JButton("Refresh");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblReturn_2 = new JLabel("Return by Id");
		
		textField_Return = new JTextField();
		textField_Return.setColumns(10);
		
		JLabel lblReturn = new JLabel("Return To Library");
		lblReturn.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//calculate any fee on the item, if no fee then just return item
				//if there is a fee, inform the user, ask what currency they would like to pay in.
				//if they confirm payment, return item, otherwise do nothing
				PhysicalItem retItem=(PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Return.getText()));
				retItem.calculateFee();
				if(retItem.getFee()==0) {
					loggedIn.returnPhysicalItem(retItem);
					JOptionPane.showMessageDialog(frame, "Item returned successfully.", "Successful Return", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Object[] options = CurrencyExchange.getCurrencyList().toArray();
					Object userInfo=JOptionPane.showInputDialog(frame, "This item is overdue\nChoose currency type:", "Overdue Item: Currency Options", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if(userInfo!=null) {
						IPayment payment = new CurrencyExchange(new Payment(retItem.getFee()));
						double finalPrice = payment.getPrice((String)userInfo);
						int response = JOptionPane.showConfirmDialog(frame, "Your total is: "+ String.format("%.2f",finalPrice)+" "+(String)userInfo+"\n"
								+ "Would you like to confirm your purchase?", "Confirm your purchase", JOptionPane.YES_NO_OPTION);
						if(response == JOptionPane.YES_OPTION) {
							Object[] options2 = PaymentContext.getPaymentMethods();
							Object userInfo2=JOptionPane.showInputDialog(frame, "Choose payment method", "Payment Options", JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
							PaymentContext paymentInfo=null;
							if(userInfo2!=null && userInfo2.equals("Debit")) {
								paymentInfo=debitPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Paypal")) {
								paymentInfo=paypalPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Gift")) {
								paymentInfo=giftPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Credit")) {
								paymentInfo=creditPopup();
							}
							//payment will only be null if user hits cancel on the popups to enter info, if it is null
							//then cancel payment otherwise proceed to payment confirmation
							if(paymentInfo!=null) {
								int response2 = JOptionPane.showConfirmDialog(frame, "Would you like to confirm your payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
								if(response2==JOptionPane.YES_OPTION) {
									//if the user entered valid info then checkout, if not then cancel payment
									if(paymentInfo.pay(finalPrice)) {
										loggedIn.returnPhysicalItem(retItem);
										JOptionPane.showMessageDialog(frame, "Item returned successfully.\nYour payment of "+String.format("%.2f",finalPrice)+" "+(String)userInfo+" has been recieved.", "Successful Return", JOptionPane.INFORMATION_MESSAGE);
									}
									else {
										JOptionPane.showMessageDialog(frame, "Invalid payment information entered, payment was cancelled.", "Failed to Return", JOptionPane.INFORMATION_MESSAGE);
									}

								}
							}

						}
					}

				}
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(65)
					.addComponent(lblReturn_2, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(35))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(28)
					.addComponent(btnReturn, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addGap(13))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(23)
							.addComponent(textField_Return, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
						.addComponent(lblReturn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(20))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(lblReturn)
					.addGap(76)
					.addComponent(lblReturn_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Return, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReturn))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_physicalItemPage = new GroupLayout(physicalItemPage);
		gl_physicalItemPage.setHorizontalGroup(
			gl_physicalItemPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_physicalItemPage.createSequentialGroup()
					.addGroup(gl_physicalItemPage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(732)
							.addComponent(btnRefreshInventory, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(47)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
							.addGap(40)
							.addComponent(inventoryScroll, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_physicalItemPage.setVerticalGroup(
			gl_physicalItemPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_physicalItemPage.createSequentialGroup()
					.addGroup(gl_physicalItemPage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(17)
							.addComponent(btnRefreshInventory)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inventoryScroll))
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(64)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addGap(79)))
					.addGap(57))
		);
		physicalItemPage.setLayout(gl_physicalItemPage);
		btnRefreshInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Refreshes loggedin inventory
				updateTable3(inventoryTable, loggedIn,loggedIn.getRented());
			}
		});
		
		onlineItemsPage = 	new JPanel();
		onlineItemsPage.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Online Items", null, onlineItemsPage, null);
		
		onlineScroll = new JScrollPane();
		
		JButton btnRefreshOnline = new JButton("Refresh");
		btnRefreshOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions());
			}
		});
		
		JPanel panel_Subs = new JPanel();
		panel_Subs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		onlineTable = new JTable();
		onlineTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		onlineScroll.setViewportView(onlineTable);
		GroupLayout gl_onlineItemsPage = new GroupLayout(onlineItemsPage);
		gl_onlineItemsPage.setHorizontalGroup(
			gl_onlineItemsPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_onlineItemsPage.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_Subs, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(106)
					.addGroup(gl_onlineItemsPage.createParallelGroup(Alignment.TRAILING)
						.addComponent(onlineScroll, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
						.addGroup(gl_onlineItemsPage.createSequentialGroup()
							.addGap(412)
							.addComponent(btnRefreshOnline, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_onlineItemsPage.setVerticalGroup(
			gl_onlineItemsPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_onlineItemsPage.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_onlineItemsPage.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Subs, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
						.addGroup(gl_onlineItemsPage.createSequentialGroup()
							.addComponent(btnRefreshOnline)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(onlineScroll, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
					.addGap(70))
		);
		
		JLabel lblUnSubscribe = new JLabel("UnSubscribe by Id");
		
		textField_UnSubscribe = new JTextField();
		textField_UnSubscribe.setColumns(10);
		
		JButton btnUnSubscribe = new JButton("UnSubscribe");
		btnUnSubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Integer.valueOf(textField_UnSubscribe.getText()
				loggedIn.unSubscribe((OnlineItem)system.getOnlineItem(Integer.valueOf(textField_UnSubscribe.getText())));
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions() );

			}
		});
		
		JLabel lblSub = new JLabel("Subscriptions");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_panel_Subs = new GroupLayout(panel_Subs);
		gl_panel_Subs.setHorizontalGroup(
			gl_panel_Subs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Subs.createSequentialGroup()
					.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Subs.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_panel_Subs.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUnSubscribe, Alignment.LEADING)
								.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
									.addComponent(btnUnSubscribe)
									.addComponent(textField_UnSubscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_Subs.createSequentialGroup()
							.addGap(21)
							.addComponent(lblSub)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_panel_Subs.setVerticalGroup(
			gl_panel_Subs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Subs.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSub)
					.addGap(195)
					.addComponent(lblUnSubscribe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_UnSubscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUnSubscribe)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		panel_Subs.setLayout(gl_panel_Subs);
		onlineItemsPage.setLayout(gl_onlineItemsPage);
		
		readOnlinePage = new JPanel();
		readOnlinePage.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Read", null, readOnlinePage, null);
		
		JScrollPane scrollRead = new JScrollPane();
		
		JButton btnRrefreshRead = new JButton("Refresh");
		btnRrefreshRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateTable2(tableRead, loggedIn, loggedIn.getSubscriptions());
			}
		});
		
		JPanel panel_Read = new JPanel();
		panel_Read.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GroupLayout gl_readOnlinePage = new GroupLayout(readOnlinePage);
		gl_readOnlinePage.setHorizontalGroup(
			gl_readOnlinePage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_readOnlinePage.createSequentialGroup()
					.addGap(20)
					.addComponent(panel_Read, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
					.addGap(70)
					.addGroup(gl_readOnlinePage.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_readOnlinePage.createSequentialGroup()
							.addGap(444)
							.addComponent(btnRrefreshRead, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(scrollRead, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE))
					.addGap(18))
		);
		gl_readOnlinePage.setVerticalGroup(
			gl_readOnlinePage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_readOnlinePage.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_readOnlinePage.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_Read, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(gl_readOnlinePage.createSequentialGroup()
							.addGap(61)
							.addComponent(btnRrefreshRead)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollRead, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
					.addGap(24))
		);
		
		textField_Read = new JTextField();
		textField_Read.setColumns(10);
		
		JLabel lblRead = new JLabel("Read by Id");
		
		JButton btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			//TODO: ask about this because, In frame option, but looks awful
			// https://stackoverflow.com/questions/1988202/how-do-i-get-a-webpage-to-open-up-in-a-frame
			public void actionPerformed(ActionEvent e) {
				try {
					for(OnlineItem I: loggedIn.getSubscriptions()) {
						if((I.getId()+"").equals(textField_Read.getText())) {
							Desktop.getDesktop().browse(new URL(I.getLink()).toURI());
						}
					}
					//Desktop.getDesktop().browse(new URL("https://google.com").toURI());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblReadTitle = new JLabel("Read");
		lblReadTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_Read = new GroupLayout(panel_Read);
		gl_panel_Read.setHorizontalGroup(
			gl_panel_Read.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Read.createSequentialGroup()
					.addGroup(gl_panel_Read.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Read.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_panel_Read.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Read.createSequentialGroup()
									.addGap(10)
									.addComponent(btnRead))
								.addComponent(textField_Read, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_Read.createSequentialGroup()
							.addGap(70)
							.addComponent(lblRead))
						.addGroup(gl_panel_Read.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblReadTitle)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_panel_Read.setVerticalGroup(
			gl_panel_Read.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Read.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReadTitle)
					.addGap(62)
					.addComponent(lblRead)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Read, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRead)
					.addContainerGap(272, Short.MAX_VALUE))
		);
		panel_Read.setLayout(gl_panel_Read);
		
		tableRead = new JTable();
		tableRead.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollRead.setViewportView(tableRead);
		readOnlinePage.setLayout(gl_readOnlinePage);
		
		JPanel coursesPage = new JPanel();
		coursesPage.setBackground(new Color(255, 255, 255));
		JPanel textBooksPage = new JPanel();
		textBooksPage.setBackground(new Color(255, 255, 255));

		//TODO: comment out when done editing
		
//		if(loggedIn.getClass().equals((new Student()).getClass()) || loggedIn.getClass().equals((new Faculty()).getClass())){
//		tabbedPane.addTab("Courses", null, coursesPage, null);
//		tabbedPane.addTab("Textbooks", null, textBooksPage, null);
//	}
		
		tabbedPane.addTab("Courses", null, coursesPage, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_2 = new JLabel("My Courses");
		lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_coursesPage = new GroupLayout(coursesPage);
		gl_coursesPage.setHorizontalGroup(
			gl_coursesPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_coursesPage.createSequentialGroup()
					.addGroup(gl_coursesPage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_coursesPage.createSequentialGroup()
							.addGap(180)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
						.addGroup(gl_coursesPage.createSequentialGroup()
							.addGap(338)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addGap(157)))
					.addGap(215))
		);
		gl_coursesPage.setVerticalGroup(
			gl_coursesPage.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_coursesPage.createSequentialGroup()
					.addGap(88)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
		);
		
		table_Courses = new JTable();
		table_Courses.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course Code", "Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table_Courses);
		coursesPage.setLayout(gl_coursesPage);
		tabbedPane.addTab("Textbooks", null, textBooksPage, null);
	
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblNewLabel_3 = new JLabel("My Textbooks");
		lblNewLabel_3.setFont(new Font("Book Antiqua", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel("Old Textbooks");
		lblNewLabel_4.setVisible(false);
		lblNewLabel_4.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_textBooksPage = new GroupLayout(textBooksPage);
		gl_textBooksPage.setHorizontalGroup(
			gl_textBooksPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_textBooksPage.createSequentialGroup()
					.addGroup(gl_textBooksPage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_textBooksPage.createSequentialGroup()
							.addGap(19)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_textBooksPage.createSequentialGroup()
							.addGap(30)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE)
							.addGap(9)))
					.addGroup(gl_textBooksPage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_textBooksPage.createSequentialGroup()
							.addGap(41)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
							.addGap(209))
						.addGroup(gl_textBooksPage.createSequentialGroup()
							.addGap(161)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
							.addGap(329))))
		);
		gl_textBooksPage.setVerticalGroup(
			gl_textBooksPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_textBooksPage.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_textBooksPage.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_textBooksPage.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
						.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table_Old = new JTable();
		table_Old.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table_Old);
		
		table_Textbooks = new JTable();
		table_Textbooks.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course", "Id", "Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_Textbooks);
		textBooksPage.setLayout(gl_textBooksPage);
		
		/*
		 * Textbook & Course stuff
		 * Displays different UI based on what account type the loggedIn user is
		 */
		scrollPane_2.setVisible(false);
		if(loggedIn.getClass().equals((new Student()).getClass())) {
			ArrayList<Course> courses = new ArrayList<Course>();
			for(Course c: system.getCourses()) {
				if(c.getStudents().contains(loggedIn)) {
					courses.add(c);
				}
			}
			//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
			updateCoursesTable(table_Courses,loggedIn,courses);
			updateTextbooksTable(table_Textbooks,loggedIn,courses);
		}
		else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
			ArrayList<Course> courses = new ArrayList<Course>();
			for(Course c: system.getCourses()) {
				if(c.getFaculty().contains(loggedIn)) {
					courses.add(c);
				}
			}
			scrollPane_2.setVisible(true);
			lblNewLabel_4.setVisible(true);
			//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
			updateCoursesTable(table_Courses,loggedIn,courses);
			updateTextbooksTable(table_Textbooks,loggedIn,courses);
			updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
		}		
		
		centerContent_Inv.setLayout(gl_centerContent_Inv);
		GroupLayout gl_inventoryPage = new GroupLayout(inventoryPage);
		gl_inventoryPage.setHorizontalGroup(
			gl_inventoryPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_inventoryPage.createSequentialGroup()
					.addComponent(centerContent_Inv, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_inventoryPage.setVerticalGroup(
			gl_inventoryPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inventoryPage.createSequentialGroup()
					.addComponent(centerContent_Inv, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
					.addGap(1))
		);
		inventoryPage.setLayout(gl_inventoryPage);
		
		JPanel subscribePage = new JPanel();
		subscribePage.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(subscribePage, "subscribePage_");
		
		centerContent_Sub = new JPanel();
		centerContent_Sub.setBackground(new Color(255, 255, 255));
		
		topBar_1 = new JPanel();
		topBar_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar_1.setBackground(new Color(227, 24, 55));
		
		home_1 = new JButton("Home");
		home_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
			}
		});
		
		inventory_1 = new JButton("My Inventory");
		inventory_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions());
				updateTable2(tableRead, loggedIn, loggedIn.getSubscriptions());
				//Updates the Course and Textbook tabs according to user account infromation
				if(loggedIn.getClass().equals((new Student()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getStudents().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
				}
				else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getFaculty().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
					updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
				}
			}
		});
		
		rent_1 = new JButton("Rent");
		rent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable5(searchTable, loggedIn, system.getStock());
			}
		});
		
		Subscribe_3 = new JButton("Subscribe");
		Subscribe_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "subscribePage_");
				frame.repaint();
				frame.revalidate();
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
			}
		});
		
		topBarTitle_1 = new JLabel("Subscriptions");
		topBarTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_1.setForeground(Color.WHITE);
		topBarTitle_1.setFont(new Font("Book Antiqua", Font.PLAIN, 28));
		
		btnNewButton_4 = new JButton("Cart");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "cart_");
				frame.repaint();
				frame.revalidate();
				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar_1 = new GroupLayout(topBar_1);
		gl_topBar_1.setHorizontalGroup(
			gl_topBar_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_1.createSequentialGroup()
					.addGap(9)
					.addComponent(home_1)
					.addGap(10)
					.addComponent(inventory_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Subscribe_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(233))
		);
		gl_topBar_1.setVerticalGroup(
			gl_topBar_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_1.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addGroup(gl_topBar_1.createParallelGroup(Alignment.LEADING)
						.addComponent(home_1)
						.addComponent(inventory_1)
						.addGroup(gl_topBar_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(rent_1)
							.addComponent(Subscribe_3)))
					.addContainerGap())
				.addGroup(gl_topBar_1.createParallelGroup(Alignment.BASELINE)
					.addComponent(topBarTitle_1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addComponent(btnNewButton_4))
		);
		topBar_1.setLayout(gl_topBar_1);
		GroupLayout gl_subscribePage = new GroupLayout(subscribePage);
		gl_subscribePage.setHorizontalGroup(
			gl_subscribePage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subscribePage.createSequentialGroup()
					.addComponent(centerContent_Sub, GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_subscribePage.setVerticalGroup(
			gl_subscribePage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContent_Sub, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
		);
		
		JScrollPane scrollSubs = new JScrollPane();
		
		JButton btnRefreshSub = new JButton("Refresh");
		btnRefreshSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblSubsID = new JLabel("Subscribe by Id");
		
		textField_Sub = new JTextField();
		textField_Sub.setColumns(10);
		
		JLabel lblSubs = new JLabel("Subscribe");
		lblSubs.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JButton btnSub = new JButton("Subscribe");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Prevents disabled Items from being rented
				OnlineItem subItem = (OnlineItem)system.getOnlineItem(Integer.valueOf(textField_Sub.getText()));
				if(subItem.getStatus().getState().getClass().equals(new Disabled().getClass())){
	                JOptionPane.showMessageDialog(frame, "Item disabled, unable to subscribe.");
				}
				else {
					//if user has this subscription in their owned subscriptions already, do not allow them to subscribe
					if(loggedIn.getSubscriptions().contains(subItem)) {
						JOptionPane.showMessageDialog(frame, "Cannot subscribe to a subscription you already subscribed to.", "Unable to Subscribe", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					else if(subItem.getPrice()==0) {
						loggedIn.subscribe((OnlineItem)system.getOnlineItem(Integer.valueOf(textField_Sub.getText())));
						//Removes Student online textbooks from display list, since they are technically subscriptions but shouldn't be allow subscribing
						ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
						for(OnlineItem I: system.getSubs()) {
							if(I.getId() >=0) {
								holder.add(I);
							}
						}
						updateTable1(tableSubs, loggedIn, holder);
						JOptionPane.showMessageDialog(frame, "Subscription successful.", "Successful Subscribe", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					Object[] options = CurrencyExchange.getCurrencyList().toArray();
					Object userInfo=JOptionPane.showInputDialog(frame, "Choose currency type:", "Subscribe: Currency Options", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if(userInfo!=null) {
						IPayment payment = new CurrencyExchange(new Payment(subItem.getPrice()));
						double finalPrice = payment.getPrice((String)userInfo);
						int response = JOptionPane.showConfirmDialog(frame, "Your total is: "+ String.format("%.2f",finalPrice)+" "+(String)userInfo+"\n"
									+ "Would you like to confirm your subscription purchase?", "Confirm your Subscription", JOptionPane.YES_NO_OPTION);
						if(response == JOptionPane.YES_OPTION) {
							Object[] options2 = PaymentContext.getPaymentMethods();
							Object userInfo2=JOptionPane.showInputDialog(frame, "Choose payment method", "Payment Options", JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
							PaymentContext paymentInfo=null;
							if(userInfo2!=null && userInfo2.equals("Debit")) {
								paymentInfo=debitPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Paypal")) {
									paymentInfo=paypalPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Gift")) {
								paymentInfo=giftPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Credit")) {
								paymentInfo=creditPopup();
							}
							//payment will only be null if user hits cancel on the popups to enter info, if it is null
							//then cancel payment otherwise proceed to payment confirmation 
							if(paymentInfo!=null) {
								int response2 = JOptionPane.showConfirmDialog(frame, "Would you like to confirm your payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
								if(response2==JOptionPane.YES_OPTION) {
									//if the user entered valid info then checkout, if not then cancel payment
									if(paymentInfo.pay(finalPrice)) {
										//.copySubscriptionOption((OnlineItem) system.getSubOp(Integer.valueOf(textField_Sub.getText())));
										loggedIn.subscribe((OnlineItem)system.getOnlineItem(Integer.valueOf(textField_Sub.getText())));
										//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
										ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
										for(OnlineItem I: system.getSubs()) {
											if(I.getId() >=0) {
												holder.add(I);
											}
										}
										updateTable1(tableSubs, loggedIn, holder);
										JOptionPane.showMessageDialog(frame, "Subscription successful.\nYour payment of "+String.format("%.2f",finalPrice)+" "+(String)userInfo+" has been recieved.", "Successful Subscribe", JOptionPane.INFORMATION_MESSAGE);
									}
									else {
										JOptionPane.showMessageDialog(frame, "Invalid payment information entered, payment was cancelled.", "Failed to Subscribe", JOptionPane.INFORMATION_MESSAGE);
									}

								}

							}
					
						}
					}
				}

				}
			
		
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblSubsID, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
							.addGap(58))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblSubs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(75))))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(btnSub, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE))
						.addComponent(textField_Sub, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
					.addGap(35))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSubs)
					.addGap(49)
					.addComponent(lblSubsID)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Sub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSub)
					.addContainerGap(355, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JButton bthSearch_1 = new JButton("Search");
		bthSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchQuery = textField_1.getText().trim().toLowerCase();
				ArrayList<OnlineItem> searchResults = new ArrayList<>();

				for (OnlineItem item : system.getSubs()) {
					if (item.getName().toLowerCase().equals(searchQuery)) {
						searchResults.add(item);
					}
				}

				ArrayList<OnlineItem> recommendations = recommendations(searchQuery);
				for (OnlineItem recommendation : recommendations) {
					if (!searchResults.contains(recommendation)) {
						searchResults.add(recommendation);
					}
				}

				DefaultTableModel searchTableModel = (DefaultTableModel) tableSubs.getModel();
				searchTableModel.setRowCount(0);

				if (!searchResults.isEmpty()) {
					for (OnlineItem result : searchResults) {
						String[] rowData = {String.valueOf(result.getId()), result.getName(), String.valueOf(result.getPrice()), String.valueOf(result.getStatus().getState().getClass().toString().substring(10))};
						searchTableModel.addRow(rowData);
					}
				} else {
					String[] rowData = {"N/A", "N/A", "N/A", "N/A"};
					searchTableModel.addRow(rowData);
				}
			}

			private ArrayList<OnlineItem> recommendations(String searchQuery) {
				ArrayList<OnlineItem> similarSubs = new ArrayList<>();
				for (OnlineItem item : system.getSubs()) {
					String title = item.getName().toLowerCase();
					if (title.contains(searchQuery)&&item.getId()>=0) {
						similarSubs.add(item);
					}
				}
				return similarSubs;
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel searchLabel_1 = new JLabel("Search");
		searchLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		GroupLayout gl_centerContent_Sub = new GroupLayout(centerContent_Sub);
		gl_centerContent_Sub.setHorizontalGroup(
			gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
				.addComponent(topBar_1, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
				.addGroup(gl_centerContent_Sub.createSequentialGroup()
					.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_centerContent_Sub.createSequentialGroup()
									.addGap(171)
									.addComponent(bthSearch_1, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
									.addGap(163)
									.addComponent(btnRefreshSub, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
								.addComponent(scrollSubs, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)))
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(114)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
							.addGap(121)))
					.addGap(78)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 217, Short.MAX_VALUE)
					.addGap(86))
				.addGroup(gl_centerContent_Sub.createSequentialGroup()
					.addGap(176)
					.addComponent(searchLabel_1, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addGap(591))
		);
		gl_centerContent_Sub.setVerticalGroup(
			gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Sub.createSequentialGroup()
					.addComponent(topBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(61)
							.addComponent(searchLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRefreshSub)
								.addComponent(bthSearch_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollSubs, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)))
					.addGap(51))
		);
		
		tableSubs = new JTable();
		tableSubs.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollSubs.setViewportView(tableSubs);
		centerContent_Sub.setLayout(gl_centerContent_Sub);
		subscribePage.setLayout(gl_subscribePage);
		
		cartPage = new JPanel();
		frame.getContentPane().add(cartPage, "cart_");
		
		centerContentPanel = new JPanel();
		centerContentPanel.setBackground(new Color(255, 255, 255));
		
		JPanel topBar_1_1 = new JPanel();
		topBar_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar_1_1.setBackground(new Color(227, 24, 55));
		
		JButton home_1_1 = new JButton("Home");
		home_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable4(displayTable, loggedIn, loggedIn.getRented());
			}
		});
		JButton inventory_1_1 = new JButton("My Inventory");
		inventory_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
				updateTable2(onlineTable, loggedIn, loggedIn.getSubscriptions());
				updateTable2(tableRead, loggedIn, loggedIn.getSubscriptions());
				//Updates the Course and Textbook tabs according to user account infromation
				if(loggedIn.getClass().equals((new Student()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getStudents().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
				}
				else if(loggedIn.getClass().equals((new Faculty()).getClass())) {
					ArrayList<Course> courses = new ArrayList<Course>();
					for(Course c: system.getCourses()) {
						if(c.getFaculty().contains(loggedIn)) {
							courses.add(c);
						}
					}
					//I could alternatively give the student a list of textbooks but they wouldn't own them, so seems redundant
					updateCoursesTable(table_Courses,loggedIn,courses);
					updateTextbooksTable(table_Textbooks,loggedIn,courses);
					updateOldTextbooksTable(table_Old,(Faculty)loggedIn);
				}
			}
		});
		JButton rent_1_1 = new JButton("Rent");
		rent_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable5(searchTable, loggedIn, system.getStock());
			}
		});
		JButton Subscribe_3_1 = new JButton("Subscribe");
		Subscribe_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "subscribePage_");
				frame.repaint();
				frame.revalidate();
				//Removes Student online textbooks, since they are technically subscriptions but shouldn't be allow subscribing
				ArrayList<OnlineItem> holder = new ArrayList<OnlineItem>();
				for(OnlineItem I: system.getSubs()) {
					if(I.getId() >=0) {
						holder.add(I);
					}
				}
				updateTable1(tableSubs, loggedIn, holder);
			}
		});
		JLabel topBarTitle_1_1 = new JLabel("Your Cart");
		topBarTitle_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_1_1.setForeground(Color.WHITE);
		topBarTitle_1_1.setFont(new Font("Book Antiqua", Font.PLAIN, 28));
		GroupLayout gl_topBar_1_1 = new GroupLayout(topBar_1_1);
		gl_topBar_1_1.setHorizontalGroup(
			gl_topBar_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_1_1.createSequentialGroup()
					.addGap(9)
					.addComponent(home_1_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(inventory_1_1, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_1_1, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Subscribe_3_1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle_1_1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addGap(308))
		);
		gl_topBar_1_1.setVerticalGroup(
			gl_topBar_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_1_1.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addGroup(gl_topBar_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(home_1_1)
						.addComponent(inventory_1_1)
						.addGroup(gl_topBar_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(rent_1_1)
							.addComponent(Subscribe_3_1)))
					.addContainerGap())
				.addComponent(topBarTitle_1_1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
		);
		topBar_1_1.setLayout(gl_topBar_1_1);
		
		JScrollPane displayScrollPane_1 = new JScrollPane();
		
		JButton btnNewButton_5 = new JButton("Clear Cart");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loggedIn.getMenu().clickClear();
				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		
		JButton btnNewButton_6 = new JButton("Checkout");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int checkoutStatus = loggedIn.getCart().canCheckout();
				//check if user is eligible to checkout, if not then display error popups
				if(checkoutStatus==-3) {
					JOptionPane.showMessageDialog(frame, "You have 4 or more items overdue, \nplease return items before checking out more items", "Unable to Checkout", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(checkoutStatus==-1) {
					JOptionPane.showMessageDialog(frame, "Too many items being checked out, only 10 physical items can be rented at once", "Unable to Checkout", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(checkoutStatus==-2) {
					JOptionPane.showMessageDialog(frame, "Cannot checkout, cart is empty", "Unable to Checkout", JOptionPane.INFORMATION_MESSAGE);
				}
				//if user is eligible and the price of their cart is zero, just checkout, no payment needed
				else if(loggedIn.getCart().getInitialPrice()<=0) {
					loggedIn.getMenu().clickCheckout();
					loggedIn.getMenu().clickClear();
					JOptionPane.showMessageDialog(frame, "Checkout completed.", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
				}
				//if user is eligible, then ask them which currency they would like to use,  convert the cart price
				//using that and then ask if they would like to confirm (popup window)
				else {
					Object[] options = CurrencyExchange.getCurrencyList().toArray();
					Object userInfo=JOptionPane.showInputDialog(frame, "Choose currency type", "Currency Options", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if(userInfo!=null) {
						loggedIn.getCart().setCurrency((String)userInfo);
						double displayPrice = loggedIn.getCart().getConvertedPrice();
						int response = JOptionPane.showConfirmDialog(frame, "Your total is: "+ String.format("%.2f",displayPrice)+" "+(String)userInfo+"\n"
								+ "Would you like to confirm your purchase?", "Confirm your purchase", JOptionPane.YES_NO_OPTION);
						if(response == JOptionPane.YES_OPTION) {
							Object[] options2 = PaymentContext.getPaymentMethods();
							Object userInfo2=JOptionPane.showInputDialog(frame, "Choose payment method", "Payment Options", JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
							PaymentContext payment=null;
							if(userInfo2!=null && userInfo2.equals("Debit")) {
								payment=debitPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Paypal")) {
								payment=paypalPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Gift")) {
								payment=giftPopup();
							}
							else if(userInfo2!=null && userInfo2.equals("Credit")) {
								payment=creditPopup();
							}
							//payment will only be null if user hits cancel on the popups to enter info, if it is null
							//then cancel payment otherwise proceed to payment confirmation
							if(payment!=null) {
								int response2 = JOptionPane.showConfirmDialog(frame, "Would you like to confirm your payment?", "Confirm Payment", JOptionPane.YES_NO_OPTION);
								if(response2==JOptionPane.YES_OPTION) {
									//if the user entered valid info then checkout, if not then cancel payment
									if(payment.pay(displayPrice)) {
										//if the user is able to checkout without issues
										if(loggedIn.getMenu().clickCheckout()) {
											loggedIn.getMenu().clickClear();
											JOptionPane.showMessageDialog(frame, "Checkout Successful.\nYour payment of "+String.format("%.2f",displayPrice)+" "+(String)userInfo+" has been recieved.", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
										}
									}
									else {
										JOptionPane.showMessageDialog(frame, "Invalid payment information entered, payment was cancelled.", "Failed to Checkout", JOptionPane.INFORMATION_MESSAGE);
									}

								}
							}


						}
					}


				}

				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		
		textFieldRemove = new JTextField();
		textFieldRemove.setColumns(10);
		JButton btnNewButton_7 = new JButton("Remove");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldRemove.getText().isEmpty()){
					loggedIn.getMenu().clickRemove(((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textFieldRemove.getText()))));
				}
				updateTable6(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Remove Item by ID");
		
		//textField = new JTextField();
		//textField.setColumns(10);
		GroupLayout gl_centerContentPanel = new GroupLayout(centerContentPanel);
		gl_centerContentPanel.setHorizontalGroup(
			gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addGroup(gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldRemove)
							.addGap(7)))
					.addGap(18)
					.addComponent(displayScrollPane_1, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
					.addGap(189))
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addGap(137)
					.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(66)
					.addComponent(btnNewButton_6, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(485))
				.addComponent(topBar_1_1, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
		);
		gl_centerContentPanel.setVerticalGroup(
			gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addComponent(topBar_1_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(displayScrollPane_1, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
							.addGap(27)
							.addGroup(gl_centerContentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_5)
								.addComponent(btnNewButton_6))
							.addGap(152))
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addGap(156)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btnNewButton_7)
							.addContainerGap())))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Id", "Name", "Price", "Discount(%)"
		}
	) {
		boolean[] columnEditables = new boolean[] {
			false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
		displayScrollPane_1.setViewportView(table);
		centerContentPanel.setLayout(gl_centerContentPanel);
		GroupLayout gl_cartPage = new GroupLayout(cartPage);
		gl_cartPage.setHorizontalGroup(
			gl_cartPage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContentPanel, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
		);
		gl_cartPage.setVerticalGroup(
			gl_cartPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_cartPage.createSequentialGroup()
					.addComponent(centerContentPanel, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
					.addGap(0))
		);
		cartPage.setLayout(gl_cartPage);
		
		//setting icons here, after all buttons have been initialized
		if(homeIcon!=null) {
			home.setIcon(new ImageIcon(homeIcon));
			home_Inv.setIcon(new ImageIcon(homeIcon));
			home_Rent.setIcon(new ImageIcon(homeIcon));
			home_1.setIcon(new ImageIcon(homeIcon));
			home_1_1.setIcon(new ImageIcon(homeIcon));
		}
		if(cartIcon!=null) {
			btnNewButton_1.setIcon(new ImageIcon(cartIcon));
			btnNewButton_2.setIcon(new ImageIcon(cartIcon));
			btnNewButton_3.setIcon(new ImageIcon(cartIcon));
			btnNewButton_4.setIcon(new ImageIcon(cartIcon));
		}
		if(notiIcon!=null) {
			btnNotifications.setIcon(new ImageIcon(notiIcon));
		}
		if(refreshIcon!=null) {
			btnRefreshInventory_1.setIcon(new ImageIcon(refreshIcon));
			btnSeeAll.setIcon(new ImageIcon(refreshIcon));
			btnRefreshInventory.setIcon(new ImageIcon(refreshIcon));
			btnRefreshOnline.setIcon(new ImageIcon(refreshIcon));
			btnRrefreshRead.setIcon(new ImageIcon(refreshIcon));
			btnRefreshSub.setIcon(new ImageIcon(refreshIcon));
		}
		if(addIcon!=null) {
			btnRent.setIcon(new ImageIcon(addIcon));
		}
		if(removeIcon!=null) {
			btnNewButton_7.setIcon(new ImageIcon(removeIcon));
		}
		if(searchIcon!=null) {
			bthSearch_1.setIcon(new ImageIcon(searchIcon));
			bthSearch.setIcon(new ImageIcon(searchIcon));
		}
		

		
		
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("York Library");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(950, 750);
		frame.setVisible(true);
		
		//Saves everything to CSVs when the window closes
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	//Saves everything that happened
		    	CSVReader.upload(system);
		    	
		        //Closes windows
		    	frame.dispose();
		    	mgrFrame.dispose();
		    	notifyFrame.dispose();
		    }
		});
	}
}
