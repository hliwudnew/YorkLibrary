package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
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

public class MainGUI implements ActionListener {
	//Basic Setup
	private JFrame frame;
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
	private JTextField textField;
	private JButton btnRefreshInventory_1;
	private JScrollPane displayScrollPane;
	private JTable displayTable;
	private JScrollPane rentScroll;
	private JTable searchTable;
	private JScrollPane inventoryScroll;
	private JTable inventoryTable;
	
	public static void main(String[] args) {
		new MainGUI();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainGUI() {
		System.out.println("Logged in");
		frame = new JFrame();
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
					.addComponent(topBarTitle, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(327))
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
					.addComponent(rent))
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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton bthSearch = new JButton("Search");
		
		JPanel pReccomendation = new JPanel();
		pReccomendation.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		rentScroll = new JScrollPane();
		GroupLayout gl_centerContent_Rent = new GroupLayout(centerContent_Rent);
		gl_centerContent_Rent.setHorizontalGroup(
			gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
				.addComponent(topBar_Rent, GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
				.addGroup(gl_centerContent_Rent.createSequentialGroup()
					.addContainerGap()
					.addComponent(pReccomendation, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(98)
							.addGroup(gl_centerContent_Rent.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addGap(74)
									.addComponent(searchLabel, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
									.addGap(77))
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
								.addGroup(gl_centerContent_Rent.createSequentialGroup()
									.addGap(105)
									.addComponent(bthSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(104)))
							.addGap(315))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rentScroll, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(225))))
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
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bthSearch)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rentScroll, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
						.addGroup(gl_centerContent_Rent.createSequentialGroup()
							.addGap(18)
							.addComponent(pReccomendation, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
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
				.addGroup(gl_rentPage.createSequentialGroup()
					.addComponent(centerContent_Rent, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_rentPage.setVerticalGroup(
			gl_rentPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rentPage.createSequentialGroup()
					.addComponent(centerContent_Rent, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
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
		
		JButton btnRefreshInventory = new JButton("Refresh");
		
		inventoryScroll = new JScrollPane();
		GroupLayout gl_centerContent_Inv = new GroupLayout(centerContent_Inv);
		gl_centerContent_Inv.setHorizontalGroup(
			gl_centerContent_Inv.createParallelGroup(Alignment.TRAILING)
				.addComponent(topBar_Inv, GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
				.addGroup(gl_centerContent_Inv.createSequentialGroup()
					.addGap(119)
					.addGroup(gl_centerContent_Inv.createParallelGroup(Alignment.TRAILING)
						.addComponent(inventoryScroll, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
						.addComponent(btnRefreshInventory, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addGap(185))
		);
		gl_centerContent_Inv.setVerticalGroup(
			gl_centerContent_Inv.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerContent_Inv.createSequentialGroup()
					.addComponent(topBar_Inv, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(92)
					.addComponent(btnRefreshInventory)
					.addGap(18)
					.addComponent(inventoryScroll, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
					.addGap(120))
		);
		
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
		
		btnRefreshInventory_1.addActionListener(this);
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("York Library");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
		frame.setVisible(true);
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
		//Refreshes homepage stock
		if(event.getSource() == btnRefreshInventory_1) {
			ArrayList<Item> items = new ArrayList<Item>();
			//Grabs everything from the items CSV
			items.addAll(CSVReader.itemData());
			
			//Clears the table of old data
			DefaultTableModel clear = (DefaultTableModel) displayTable.getModel();
			clear.setRowCount(0);
			//Loops through the CSV data and adds it to the table
			for(Item e : items) {
				String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
				DefaultTableModel tblModel = (DefaultTableModel) displayTable.getModel();
				tblModel.addRow(rowdata);
				//System.out.println(e.toString());
			}
		}
		
	}
}
