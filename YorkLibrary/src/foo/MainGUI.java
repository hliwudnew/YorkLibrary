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

public class MainGUI implements ActionListener {
	//Basic Setup
	private JFrame frame;
	private JFrame mgrFrame;
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
	private JTextField textField_Subscribe;
	private JTextField textField_UnSubscribe;
	private JTable tableRead;
	private JTextField textField_Read;
	
	public static void main(String[] args) {
		new MainGUI("test@gmail.com");
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainGUI(String email) {
		System.out.println("Logged in");
		//Populate the system with data from CSVs
		LibrarySystem system = new LibrarySystem();
		system.setItems(CSVReader.itemData());
		system.setUsers(CSVReader.userData(system));
		
		User loggedIn = system.getUser(email);
		
		
		
		frame = new JFrame();
		mgrFrame = new JFrame();
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		mainPage = new JPanel();
		frame.getContentPane().add(mainPage, "mainPage_");
		
		JPanel centerContent = new JPanel();
		centerContent.setBackground(Color.WHITE);
		
		JPanel topBar = new JPanel();
		topBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topBar.setBackground(new Color(227, 24, 55));
		
		inventory = new JButton("My Inventory");
		
		home = new JButton("Home");
		
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
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) displayTable.getModel();
				clear.setRowCount(0);
				//Loops through the CSV data and adds it to the table
				for(Item e : system.getStock()) {
					String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) displayTable.getModel();
					tblModel.addRow(rowdata);
					//System.out.println(e.toString());
				}
			}
		});
		
		displayScrollPane = new JScrollPane();
		
		
		
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
		);
		gl_centerContent.setVerticalGroup(
			gl_centerContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent.createSequentialGroup()
					.addComponent(topBar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(106)
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
				"Id", "Name", "Price", "Disabled"
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
		
		rent = new JButton("Rent");
		
		lblName = new JLabel("{email}");
		lblName.setText(email);
		lblName.setFont(new Font("Book Antiqua", Font.PLAIN, 16));
		lblName.setForeground(new Color(255, 255, 255));
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagementTeamGUI(system, mgrFrame);
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(topBarTitle, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
					.addGap(183)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 53, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_topBar.setVerticalGroup(
			gl_topBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topBar.createSequentialGroup()
					.addGap(11)
					.addComponent(home))
				.addGroup(gl_topBar.createSequentialGroup()
					.addGap(11)
					.addComponent(inventory))
				.addGroup(gl_topBar.createParallelGroup(Alignment.BASELINE)
					.addComponent(topBarTitle, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(rent)
					.addComponent(lblName))
				.addGroup(Alignment.TRAILING, gl_topBar.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAdmin)
					.addContainerGap())
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
		
		inventory_Rent = new JButton("My Inventory");
		
		rent_Rent = new JButton("Rent");
		
		topBarTitle_Rent = new JLabel("Rentings");
		topBarTitle_Rent.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_Rent.setForeground(Color.WHITE);
		topBarTitle_Rent.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		GroupLayout gl_topBar_Rent = new GroupLayout(topBar_Rent);
		gl_topBar_Rent.setHorizontalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGap(0, 846, Short.MAX_VALUE)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(9)
					.addComponent(home_Rent)
					.addGap(10)
					.addComponent(inventory_Rent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_Rent)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(topBarTitle_Rent, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
					.addGap(327))
		);
		gl_topBar_Rent.setVerticalGroup(
			gl_topBar_Rent.createParallelGroup(Alignment.LEADING)
				.addGap(0, 42, Short.MAX_VALUE)
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(11)
					.addComponent(home_Rent))
				.addGroup(gl_topBar_Rent.createSequentialGroup()
					.addGap(11)
					.addComponent(inventory_Rent))
				.addGroup(gl_topBar_Rent.createParallelGroup(Alignment.BASELINE)
					.addComponent(topBarTitle_Rent, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(rent_Rent))
		);
		topBar_Rent.setLayout(gl_topBar_Rent);
		
		textField_Search = new JTextField();
		textField_Search.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton bthSearch = new JButton("Search");
		
		JPanel pReccomendation = new JPanel();
		pReccomendation.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		rentScroll = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		btnSeeAll = new JButton("See All");
		btnSeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) searchTable.getModel();
				clear.setRowCount(0);
				//Loops through the CSV data and adds it to the table
				for(Item e : system.getStock()) {
					String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) searchTable.getModel();
					tblModel.addRow(rowdata);
					//System.out.println(e.toString());
				}
			}
		});
		GroupLayout gl_centerContent_Rent = new GroupLayout(centerContent_Rent);
		gl_centerContent_Rent.setHorizontalGroup(
			gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addContainerGap()
					.addComponent(pReccomendation, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
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
								.addComponent(pReccomendation, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		textField_Rent = new JTextField();
		textField_Rent.setColumns(10);
		
		JLabel lblRent = new JLabel("Self Checkout");
		lblRent.setFont(new Font("Book Antiqua", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Rent by Id");
		
		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loggedIn.rentPhysicalItem(system.getItem(Integer.valueOf(textField_Rent.getText())));
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(49)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(67))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_Rent)
						.addComponent(lblRent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(54))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(53)
					.addComponent(btnRent)
					.addContainerGap(59, Short.MAX_VALUE))
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
					.addContainerGap(176, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		searchTable = new JTable();
		searchTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Disabled"
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
		
		JLabel recomendationsLabel = new JLabel("Recomendations");
		recomendationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recomendationsLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		GroupLayout gl_pReccomendation = new GroupLayout(pReccomendation);
		gl_pReccomendation.setHorizontalGroup(
			gl_pReccomendation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pReccomendation.createSequentialGroup()
					.addGap(10)
					.addComponent(recomendationsLabel)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_pReccomendation.setVerticalGroup(
			gl_pReccomendation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pReccomendation.createSequentialGroup()
					.addGap(11)
					.addComponent(recomendationsLabel)
					.addContainerGap(303, Short.MAX_VALUE))
		);
		pReccomendation.setLayout(gl_pReccomendation);
		centerContent_Rent.setLayout(gl_centerContent_Rent);
		GroupLayout gl_rentPage = new GroupLayout(rentPage);
		gl_rentPage.setHorizontalGroup(
			gl_rentPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_rentPage.createSequentialGroup()
					.addComponent(centerContent_Rent, GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_rentPage.setVerticalGroup(
			gl_rentPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rentPage.createSequentialGroup()
					.addComponent(centerContent_Rent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
		
		inventory_Inv = new JButton("My Inventory");
		
		rent_Inv = new JButton("Rent");
		
		topBarTitle_Inv = new JLabel("My Inventory");
		topBarTitle_Inv.setHorizontalAlignment(SwingConstants.CENTER);
		topBarTitle_Inv.setForeground(Color.WHITE);
		topBarTitle_Inv.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		GroupLayout gl_topBar_Inv = new GroupLayout(topBar_Inv);
		gl_topBar_Inv.setHorizontalGroup(
			gl_topBar_Inv.createParallelGroup(Alignment.LEADING)
				.addGap(0, 845, Short.MAX_VALUE)
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(9)
					.addComponent(home_Inv)
					.addGap(10)
					.addComponent(inventory_Inv)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rent_Inv)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(topBarTitle_Inv, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addGap(327))
		);
		gl_topBar_Inv.setVerticalGroup(
			gl_topBar_Inv.createParallelGroup(Alignment.LEADING)
				.addGap(0, 42, Short.MAX_VALUE)
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(11)
					.addComponent(home_Inv))
				.addGroup(gl_topBar_Inv.createSequentialGroup()
					.addGap(11)
					.addComponent(inventory_Inv))
				.addGroup(gl_topBar_Inv.createParallelGroup(Alignment.BASELINE)
					.addComponent(topBarTitle_Inv, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addComponent(rent_Inv))
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
				"Id", "Name", "Price", "Disabled"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
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
				PhysicalItem item = system.getItem(Integer.valueOf(textField_Return.getText()));
				loggedIn.returnPhysicalItem(system.getItem(Integer.valueOf(textField_Return.getText())));
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
				
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) inventoryTable.getModel();
				clear.setRowCount(0);
				//Loops through the CSV data and adds it to the table
				for(Item e : loggedIn.getRented()) {
					String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) inventoryTable.getModel();
					tblModel.addRow(rowdata);
				}
			}
		});
		
		onlineItemsPage = new JPanel();
		onlineItemsPage.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Online Items", null, onlineItemsPage, null);
		
		onlineScroll = new JScrollPane();
		
		JButton btnRefreshOnline = new JButton("Refresh");
		
		JPanel panel_Subs = new JPanel();
		panel_Subs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		onlineTable = new JTable();
		onlineTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Price", "Disabled"
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
		
		textField_Subscribe = new JTextField();
		textField_Subscribe.setColumns(10);
		
		JLabel lblSubscribe = new JLabel("Subscribe by Id");
		
		JLabel lblUnSubscribe = new JLabel("UnSubscribe by Id");
		
		textField_UnSubscribe = new JTextField();
		textField_UnSubscribe.setColumns(10);
		
		JButton btnUnSubscribe = new JButton("UnSubscribe");
		
		JButton btnSubscribe = new JButton("Subscribe");
		
		JLabel lblSub = new JLabel("Subscriptions");
		lblSub.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_panel_Subs = new GroupLayout(panel_Subs);
		gl_panel_Subs.setHorizontalGroup(
			gl_panel_Subs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Subs.createSequentialGroup()
					.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Subs.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Subs.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblUnSubscribe, Alignment.LEADING)
									.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUnSubscribe)
										.addComponent(textField_UnSubscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_Subs.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnSubscribe)
									.addGroup(gl_panel_Subs.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSubscribe)
										.addComponent(textField_Subscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
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
					.addGap(78)
					.addComponent(lblSubscribe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Subscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubscribe)
					.addGap(48)
					.addComponent(lblUnSubscribe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_UnSubscribe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUnSubscribe)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		panel_Subs.setLayout(gl_panel_Subs);
		onlineItemsPage.setLayout(gl_onlineItemsPage);
		
		readOnlinePage = new JPanel();
		readOnlinePage.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Read", null, readOnlinePage, null);
		
		JScrollPane scrollRead = new JScrollPane();
		
		JButton btnRrefreshRead = new JButton("Refresh");
		
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
					Desktop.getDesktop().browse(new URL("https://google.com").toURI());
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
				"Id", "Name", "Price", "Disabled"
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
		
		
		//Buttons
		home.addActionListener(this);
		rent.addActionListener(this);
		inventory.addActionListener(this);
		
		home_Rent.addActionListener(this);
		rent_Rent.addActionListener(this);
		inventory_Rent.addActionListener(this);
		
		
		home_Inv.addActionListener(this);
		rent_Inv.addActionListener(this);
		inventory_Inv.addActionListener(this);
		
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
		    }
		});
	}

	@Override
	//TODO: I don't like how I did this, but unless I want to move everything into separate files it will stay like this for now
	public void actionPerformed(ActionEvent event) { //onClick Function
		
		//Swaps between the different pages
		if(event.getSource() == home || event.getSource() == home_Rent || event.getSource() ==  home_Inv) {
			System.out.println("Home Page");
			((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "mainPage_");
			frame.repaint();
			frame.revalidate();
		}
		if(event.getSource() == rent || event.getSource() == rent_Rent || event.getSource() == rent_Inv) {
			System.out.println("Rent Page");
			((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "rentPage_");
			frame.repaint();
			frame.revalidate();
		}
		if(event.getSource() == inventory || event.getSource() == inventory_Rent || event.getSource() == inventory_Inv) {
			System.out.println("Inventory Page");
			((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "inventoryPage_");
			frame.repaint();
			frame.revalidate();
		}
	}
}
