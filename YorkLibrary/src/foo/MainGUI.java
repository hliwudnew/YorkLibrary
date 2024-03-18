package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

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
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
	
	public static void main(String[] args) {
		new MainGUI("test@gmail.com");
		
	}
	
	//update methods to reuse code for updating tables in GUI:
	
	//updates given table for given user with ID, NAME, PRICE
	public <T extends Item> void updateTable1(JTable table, User user, ArrayList<T> listToParse) {
		 DefaultTableModel clear = (DefaultTableModel) table.getModel();
			clear.setRowCount(0);
			for(Item item : listToParse) {
				String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+""};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
	}
	//updates given table for given user with ID, NAME, PRICE, DISABLED STATUS
	//might not need this method? can even add more methods for different attribute displays
	//could get a bit messy but it is a nice quick fix to adding this code to every single button
	public <T extends Item> void updateTable2(JTable table, User user, ArrayList<T> listToParse) {
		 DefaultTableModel clear = (DefaultTableModel) table.getModel();
			clear.setRowCount(0);
			for(Item item : listToParse) {
				String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"", item.getDisabled().getState().getClass().toString().substring(10)};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
	}
	
	//Updates a table with physicalItem's has an extra column for the due date of the item
	public <T extends Item> void updateTable3(JTable table, User user, ArrayList<PhysicalItem> listToParse) {
		 DefaultTableModel clear = (DefaultTableModel) table.getModel();
			clear.setRowCount(0);
			for(PhysicalItem item : listToParse) {
				String[] rowdata = {item.getId()+"",item.getName(),item.getPrice()+"", item.getDisabled().getState().getClass().toString().substring(10)+"", item.getDueDate().toString()};
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.addRow(rowdata);
			}
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainGUI(String email) {
		System.out.println("Logged in");
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
			}
		});
		
		home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) displayTable.getModel();
				clear.setRowCount(0);
				//Loops through the CSV data and adds it to the table
				for(Item item : system.getStock()) {
					String[] rowdata = {item.getId()+"",item.getName(),item.getPrice() +"",item.getDisabled().getState().getClass().toString().substring(10)+""};
					DefaultTableModel tblModel = (DefaultTableModel) displayTable.getModel();
					tblModel.addRow(rowdata);
					//System.out.println(e.toString());
				}
				updateTable2(displayTable, loggedIn, system.getStock());
			}
		});
		
		JLabel topBarTitle = new JLabel("York Library");
		topBarTitle.setForeground(Color.WHITE);
		topBarTitle.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
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
				updateTable2(displayTable, loggedIn, system.getStock());
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
		    gl_centerContent.createParallelGroup(Alignment.LEADING)
		        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
		        .addGroup(gl_centerContent.createSequentialGroup()
		            .addGap(108)
		            .addGroup(gl_centerContent.createParallelGroup(Alignment.TRAILING)
		                .addComponent(displayScrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
		                .addComponent(btnRefreshInventory_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
		            .addGap(197))
		        .addGroup(Alignment.TRAILING, gl_centerContent.createSequentialGroup()
		            .addContainerGap(747, Short.MAX_VALUE)
		            .addComponent(btnNotifications)
		            .addContainerGap())
		);
		gl_centerContent.setVerticalGroup(
		    gl_centerContent.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_centerContent.createSequentialGroup()
		            .addComponent(topBar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addGroup(gl_centerContent.createParallelGroup(Alignment.BASELINE)
		                .addComponent(btnNotifications))
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
		displayScrollPane.setViewportView(displayTable);
		
		//after table on home page is initialized, populate the table (same functionality as refresh, but it does it on startup now)
		//Clears the table of old data
		updateTable2(displayTable, loggedIn, system.getStock());
		
		rent = new JButton("Rent");
		rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable2(searchTable, loggedIn, system.getStock());
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
				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar = new GroupLayout(topBar);
		gl_topBar.setHorizontalGroup(
			gl_topBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar.createSequentialGroup()
					.addGap(9)
					.addComponent(home)
					.addGap(10)
					.addComponent(inventory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Subscribe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(38)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 71, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_topBar.setVerticalGroup(
			gl_topBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addGroup(gl_topBar.createParallelGroup(Alignment.LEADING)
						.addComponent(home)
						.addComponent(inventory)
						.addGroup(gl_topBar.createParallelGroup(Alignment.BASELINE)
							.addComponent(rent)
							.addComponent(lblName)
							.addComponent(Subscribe))
						.addComponent(btnAdmin))
					.addContainerGap())
				.addGroup(gl_topBar.createSequentialGroup()
					.addGroup(gl_topBar.createParallelGroup(Alignment.BASELINE)
						.addComponent(topBarTitle, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
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
				updateTable2(displayTable, loggedIn, system.getStock());
				
				
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
		topBarTitle_Rent.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		
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
				updateTable1(table, loggedIn,loggedIn.getCart().getItems());
			}
		});
		GroupLayout gl_topBar_Rent = new GroupLayout(topBar_Rent);
		gl_topBar_Rent.setHorizontalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(9)
					.addComponent(home_Rent)
					.addGap(10)
					.addComponent(inventory_Rent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_Rent)
					.addGap(10)
					.addComponent(Subscribe_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle_Rent, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
					.addGap(78)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(272))
		);
		gl_topBar_Rent.setVerticalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(11)
					.addComponent(home_Rent))
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(11)
					.addComponent(inventory_Rent))
				.addGroup(gl_topBar_Rent.createParallelGroup(Alignment.BASELINE)
					.addComponent(rent_Rent)
					.addComponent(Subscribe_1)
					.addComponent(topBarTitle_Rent, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton_2))
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
						String[] rowData = {String.valueOf(result.getId()), result.getName(), String.valueOf(result.getPrice()), String.valueOf(result.getDisabled().getState().getClass().toString().substring(10))};
						searchTableModel.addRow(rowData);
					}
				} else {
					String[] rowData = {"N/A", "N/A", "N/A", "N/A"};
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
				updateTable2(searchTable, loggedIn, system.getStock());
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
					//.addComponent(pReccomendation, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
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
							.addGap(96))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSeeAll)
								.addComponent(rentScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 173, Short.MAX_VALUE)
					.addComponent(panelRequest, GroupLayout.PREFERRED_SIZE, 173, Short.MAX_VALUE)
					.addGap(55))
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addComponent(topBar_Rent, GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_centerContent_Rent.setVerticalGroup(
			gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addComponent(topBar_Rent, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(35)
							.addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bthSearch)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(Alignment.TRAILING, gl_centerContent_Rent.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSeeAll)
									.addGap(3)))
							.addComponent(rentScroll, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelRequest, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								)))
					.addContainerGap())
		);
		
		textField_Rent = new JTextField();
		textField_Rent.setColumns(10);
		
		JLabel lblRent = new JLabel("Add to Cart");
		lblRent.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Add by ID");
		
		JButton btnRent = new JButton("Add");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loggedIn.rentPhysicalItem((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Rent.getText())));
				if(!textField_Rent.getText().isEmpty()){
					//when user clicks add button, menu (invoker) calls the add command and then it gets added to cart 
					loggedIn.getMenu().clickAdd(((PhysicalItem)system.getPhysicalItem(Integer.valueOf(textField_Rent.getText()))));
				}

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addComponent(lblRent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(textField_Rent, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addGap(35))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(57)
					.addComponent(btnRent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(56)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(60))
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
					.addContainerGap(174, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
	

		JLabel lblRequest = new JLabel("Request a New Book");
		lblRequest.setFont(new Font("Book Antiqua", Font.BOLD, 16));

		JLabel lblNewLabelRequest = new JLabel("Add by name");

		JButton btnRequest = new JButton("Request");
		btnRequest.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (!textField_Rent.getText().isEmpty()) {
		            loggedIn.getMenu().clickAdd(((PhysicalItem) system.getPhysicalItem(Integer.valueOf(textField_Rent.getText()))));
		        }
		    }
		});
		
		
		

		
		
		searchTable = new JTable();
		searchTable.setModel(new DefaultTableModel(
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
				updateTable2(displayTable, loggedIn, system.getStock());
			}
		});
		
		inventory_Inv = new JButton("My Inventory");
		inventory_Inv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
				frame.repaint();
				frame.revalidate();
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented());
			}
		});
		
		rent_Inv = new JButton("Rent");
		rent_Inv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable2(searchTable, loggedIn, system.getStock());
			}
		});
		
		topBarTitle_Inv = new JLabel("My Inventory");
		topBarTitle_Inv.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_Inv.setForeground(Color.WHITE);
		topBarTitle_Inv.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		
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
				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
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
				.addComponent(topBar_Inv, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
		);
		gl_centerContent_Inv.setVerticalGroup(
			gl_centerContent_Inv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Inv.createSequentialGroup()
					.addComponent(topBar_Inv, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(475, Short.MAX_VALUE))
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
				}
				else {
					Object[] options = CurrencyExchange.getCurrencyList().toArray();
					Object userInfo=JOptionPane.showInputDialog(frame, "This item is overdue\nChoose currency type:", "Overdue Item: Currency Options", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					IPayment payment = new CurrencyExchange(new Payment(retItem.getFee()));
					double finalPrice = payment.getPrice((String)userInfo);
					int response = JOptionPane.showConfirmDialog(frame, "Your total is: "+ String.format("%.2f",finalPrice)+"\n"
							+ "Would you like to confirm your payment?", "Confirm payment", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION) {
						loggedIn.returnPhysicalItem(retItem);
					}
				}
				updateTable3(inventoryTable, loggedIn, loggedIn.getRented() );
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
					.addGroup(gl_physicalItemPage.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(732)
							.addComponent(btnRefreshInventory, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_physicalItemPage.createSequentialGroup()
							.addGap(47)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(inventoryScroll, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
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
							.addComponent(inventoryScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_physicalItemPage.createSequentialGroup()
							.addGap(64)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addComponent(panel_Subs, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addGap(106)
					.addGroup(gl_onlineItemsPage.createParallelGroup(Alignment.TRAILING)
						.addComponent(onlineScroll, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefreshOnline, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_onlineItemsPage.setVerticalGroup(
			gl_onlineItemsPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_onlineItemsPage.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_onlineItemsPage.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Subs, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_onlineItemsPage.createSequentialGroup()
							.addComponent(btnRefreshOnline)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(onlineScroll, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))))
		);
		
		JLabel lblUnSubscribe = new JLabel("UnSubscribe by Id");
		
		textField_UnSubscribe = new JTextField();
		textField_UnSubscribe.setColumns(10);
		
		JButton btnUnSubscribe = new JButton("UnSubscribe");
		btnUnSubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Integer.valueOf(textField_UnSubscribe.getText()
				loggedIn.unSubscribe((OnlineItem)system.getOnlineItem(Integer.valueOf(textField_UnSubscribe.getText())));
				updateTable2(tableSubs, loggedIn, loggedIn.getSubscriptions() );

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
					.addComponent(panel_Read, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addGroup(gl_readOnlinePage.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRrefreshRead)
						.addComponent(scrollRead, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_readOnlinePage.setVerticalGroup(
			gl_readOnlinePage.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_readOnlinePage.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addGroup(gl_readOnlinePage.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_Read, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_readOnlinePage.createSequentialGroup()
							.addComponent(btnRrefreshRead)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollRead, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)))
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
				updateTable2(displayTable, loggedIn, system.getStock());
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
			}
		});
		
		rent_1 = new JButton("Rent");
		rent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable2(searchTable, loggedIn, system.getStock());
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
		topBarTitle_1.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		
		btnNewButton_4 = new JButton("Cart");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "cart_");
				frame.repaint();
				frame.revalidate();
				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
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
					.addComponent(topBarTitle_1, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(225))
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
				.addComponent(topBarTitle_1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
				.addGroup(gl_topBar_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_4)
					.addContainerGap(14, Short.MAX_VALUE))
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

				//.copySubscriptionOption((OnlineItem) system.getSubOp(Integer.valueOf(textField_Sub.getText())));
				loggedIn.subscribe((OnlineItem)system.getOnlineItem(Integer.valueOf(textField_Sub.getText())));
				updateTable1(tableSubs, loggedIn, system.getSubs());

			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(38)
					.addComponent(lblSubsID, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(65))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(btnSub)
							.addContainerGap())
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_Sub, 104, 104, 104)
								.addComponent(lblSubs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(54))))
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
					.addContainerGap(174, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel pReccomendation_1 = new JPanel();
		pReccomendation_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel recomendationsLabel_1 = new JLabel("Recomendations");
		recomendationsLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		recomendationsLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		GroupLayout gl_pReccomendation_1 = new GroupLayout(pReccomendation_1);
		gl_pReccomendation_1.setHorizontalGroup(
			gl_pReccomendation_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 155, Short.MAX_VALUE)
				.addGroup(gl_pReccomendation_1.createSequentialGroup()
					.addGap(10)
					.addComponent(recomendationsLabel_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pReccomendation_1.setVerticalGroup(
			gl_pReccomendation_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 339, Short.MAX_VALUE)
				.addGroup(gl_pReccomendation_1.createSequentialGroup()
					.addGap(11)
					.addComponent(recomendationsLabel_1)
					.addContainerGap(303, Short.MAX_VALUE))
		);
		pReccomendation_1.setLayout(gl_pReccomendation_1);
		
		JButton bthSearch_1 = new JButton("Search");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel searchLabel_1 = new JLabel("Search");
		searchLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		GroupLayout gl_centerContent_Sub = new GroupLayout(centerContent_Sub);
		gl_centerContent_Sub.setHorizontalGroup(
			gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
				.addComponent(topBar_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_centerContent_Sub.createSequentialGroup()
					.addContainerGap()
					.addComponent(pReccomendation_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRefreshSub)
								.addComponent(scrollSubs, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(106)
							.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_centerContent_Sub.createSequentialGroup()
									.addGap(74)
									.addComponent(searchLabel_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_centerContent_Sub.createSequentialGroup()
									.addGap(105)
									.addComponent(bthSearch_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))))
					.addGap(10)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_centerContent_Sub.setVerticalGroup(
			gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Sub.createSequentialGroup()
					.addComponent(topBar_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Sub.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_centerContent_Sub.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
								.addComponent(searchLabel_1)
								.addGap(6)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(bthSearch_1)
								.addGap(24)
								.addComponent(btnRefreshSub)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollSubs, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_centerContent_Sub.createSequentialGroup()
								.addGap(28)
								.addComponent(pReccomendation_1, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addGroup(gl_centerContent_Sub.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		tableSubs = new JTable();
		tableSubs.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
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
		
		JPanel topBar_1_1 = new JPanel();
		topBar_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar_1_1.setBackground(new Color(227, 24, 55));
		
		JButton home_1_1 = new JButton("Home");
		home_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "homePage_");
				frame.repaint();
				frame.revalidate();
				updateTable2(displayTable, loggedIn, system.getStock());
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
			}
		});
		JButton rent_1_1 = new JButton("Rent");
		rent_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
				frame.repaint();
				frame.revalidate();
				updateTable2(searchTable, loggedIn, system.getStock());
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
		topBarTitle_1_1.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		GroupLayout gl_topBar_1_1 = new GroupLayout(topBar_1_1);
		gl_topBar_1_1.setHorizontalGroup(
			gl_topBar_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar_1_1.createSequentialGroup()
					.addGap(9)
					.addComponent(home_1_1)
					.addGap(10)
					.addComponent(inventory_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Subscribe_3_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(topBarTitle_1_1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(318, Short.MAX_VALUE))
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
				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		
		JButton btnNewButton_6 = new JButton("Checkout");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				//check if user is eligible to checkout, if not then display error popups
				if(loggedIn.getCart().canCheckout()==-1) {
					JOptionPane.showMessageDialog(frame, "Too many items being checked out, only 10 physical items can be rented at once", "Unable to Checkout", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(loggedIn.getCart().canCheckout()==-2) {
					JOptionPane.showMessageDialog(frame, "Cannot checkout, cart is empty", "Unable to Checkout", JOptionPane.INFORMATION_MESSAGE);
				}
				//if user is eligible, then ask them which currency they would like to use,  convert the cart price
				//using that and then ask if they would like to confirm (popup window)
				else {
					Object[] options = CurrencyExchange.getCurrencyList().toArray();
					Object userInfo=JOptionPane.showInputDialog(frame, "Choose currency type", "Currency Options", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					loggedIn.getCart().setCurrency((String)userInfo);
					double displayPrice = loggedIn.getCart().getConvertedPrice();
					int response = JOptionPane.showConfirmDialog(frame, "Your total is: "+ String.format("%.2f",displayPrice)+"\n"
							+ "Would you like to confirm your purchase?", "Confirm your purchase", JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.YES_OPTION) {
						loggedIn.getMenu().clickCheckout();
						loggedIn.getMenu().clickClear();
					}

				}

				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
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
				updateTable1(table, loggedIn, loggedIn.getCart().getItems());
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Remove Item by ID");
		
		//textField = new JTextField();
		//textField.setColumns(10);
		GroupLayout gl_centerContentPanel = new GroupLayout(centerContentPanel);
		gl_centerContentPanel.setHorizontalGroup(
			gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(topBar_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addGroup(gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton_7))
						.addGroup(gl_centerContentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(displayScrollPane_1, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addGap(189))
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addGap(137)
					.addComponent(btnNewButton_5)
					.addGap(66)
					.addComponent(btnNewButton_6)
					.addContainerGap(495, Short.MAX_VALUE))
		);
		gl_centerContentPanel.setVerticalGroup(
			gl_centerContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContentPanel.createSequentialGroup()
					.addComponent(topBar_1_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
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
			"Id", "Name", "Price"
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
				.addComponent(centerContentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		gl_cartPage.setVerticalGroup(
			gl_cartPage.createParallelGroup(Alignment.LEADING)
				.addComponent(centerContentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		cartPage.setLayout(gl_cartPage);
		
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("York Library");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
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
