package foo;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Window.Type;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ManagementTeamGUI {
	//Basic Setup
	private JFrame frame;
	private JTable mtsTable;
	private JTextField textField_id;
	private JTextField txtRemoveItem;
	private JTextField textField_Disable;
	private JTextField textField_Name;
	private JTextField textField_Price;
	private JCheckBox checkDisabled;
	private JTextField textField_Enable;
	private JComboBox<String> comboBoxItemType;
	private JTextField textField_Link;
	private JLabel lblLink;
	private JTextField textField_CCode;
	private JTextField textField_CName;
	private JTextField textField_CCode2;
	private JTextField textField_CCode3;
	private JTextField textField_SEmail;
	private JTextField textField_FEmail;
	private JTextField textField_CRemove;
	private JTable mts2Table;
	private JTextField textField_Ctextbook;
	private JTextField textField_CIDtextbook;
	private JTable tableNotifications;
	
	//updates the items table for managementgui with 5 fields: Type, Id, Name, Price, Disabled
	//parameters are just the table to update and the library system to update from
	public void updateItemsTable(JTable table, LibrarySystem system) {
		//Clears the table of old data
		DefaultTableModel clear = (DefaultTableModel) table.getModel();
		clear.setRowCount(0);
		//Looks at all items
		ArrayList<Item> holder = new ArrayList<Item>(system.getStock()); // Books in stock
		holder.addAll(system.getBorrowed());// Books people are borrowing
		holder.addAll(system.getSubs()); // Subscription options
		
		for(Item e : holder) {
			String[] rowdata = {e.getClass().toString().substring(10),e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled().getState().getClass().toString().substring(10)+""};
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			tblModel.addRow(rowdata);
		}
	}

	public ManagementTeamGUI(LibrarySystem system, JFrame frame, User loggedIn) {
		ManagementTeam mgr = new ManagementTeam(system);
		
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel mainPage = new JPanel();
		mainPage.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(mainPage, "name_1113365353893500");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		GroupLayout gl_mainPage = new GroupLayout(mainPage);
		gl_mainPage.setHorizontalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(111)
					.addGroup(gl_mainPage.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
						.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
					.addGap(105))
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPage.createParallelGroup(Alignment.BASELINE)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panelTableItems = new JPanel();
		tabbedPane_1.addTab("Items", null, panelTableItems, null);
		
		JScrollPane mtsScroll = new JScrollPane();
		
		mtsTable = new JTable();
		mtsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Id", "Name", "Price", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		mtsScroll.setViewportView(mtsTable);
		
		updateItemsTable(mtsTable, system);
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				updateItemsTable(mtsTable, system);
			}
		});
		GroupLayout gl_panelTableItems = new GroupLayout(panelTableItems);
		gl_panelTableItems.setHorizontalGroup(
			gl_panelTableItems.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTableItems.createSequentialGroup()
					.addGap(12)
					.addComponent(mtsScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRefresh)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		gl_panelTableItems.setVerticalGroup(
			gl_panelTableItems.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTableItems.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelTableItems.createParallelGroup(Alignment.BASELINE)
						.addComponent(mtsScroll, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
						.addComponent(btnRefresh)))
		);
		panelTableItems.setLayout(gl_panelTableItems);
		
		JPanel panelTableCourses = new JPanel();
		tabbedPane_1.addTab("Courses", null, panelTableCourses, null);
		
		JScrollPane mtsScroll2 = new JScrollPane();
		
		JButton btnRefreshCourses = new JButton("Refresh");
		btnRefreshCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) mts2Table.getModel();
				clear.setRowCount(0);
				//Looks at all items
				ArrayList<Course> holder = new ArrayList<Course>(system.getCourses()); //Courses
				
				for(Course e : holder) {
					String[] rowdata = {e.getCode()+"",e.getName()};
					DefaultTableModel tblModel = (DefaultTableModel) mts2Table.getModel();
					tblModel.addRow(rowdata);
				}
			}
		});
		GroupLayout gl_panelTableCourses = new GroupLayout(panelTableCourses);
		gl_panelTableCourses.setHorizontalGroup(
			gl_panelTableCourses.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTableCourses.createSequentialGroup()
					.addContainerGap()
					.addComponent(mtsScroll2, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRefreshCourses)
					.addContainerGap(99, Short.MAX_VALUE))
		);
		gl_panelTableCourses.setVerticalGroup(
			gl_panelTableCourses.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTableCourses.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTableCourses.createParallelGroup(Alignment.BASELINE)
						.addComponent(mtsScroll2, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(btnRefreshCourses))
					.addContainerGap())
		);
		
		mts2Table = new JTable();
		mts2Table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		mtsScroll2.setViewportView(mts2Table);
		panelTableCourses.setLayout(gl_panelTableCourses);
		
		JPanel panelNotifications = new JPanel();
		tabbedPane_1.addTab("Notifications", null, panelNotifications, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_7 = new JLabel("Notifications");
		lblNewLabel_7.setFont(new Font("Book Antiqua", Font.PLAIN, 24));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_1 = new JButton("Approve Notifications");
		GroupLayout gl_panelNotifications = new GroupLayout(panelNotifications);
		gl_panelNotifications.setHorizontalGroup(
			gl_panelNotifications.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotifications.createSequentialGroup()
					.addGroup(gl_panelNotifications.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelNotifications.createSequentialGroup()
							.addGap(130)
							.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
							.addGap(253)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
						.addGroup(gl_panelNotifications.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)))
					.addGap(21))
		);
		gl_panelNotifications.setVerticalGroup(
			gl_panelNotifications.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotifications.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNotifications.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableNotifications = new JTable();
		tableNotifications.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Message"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableNotifications);
		panelNotifications.setLayout(gl_panelNotifications);
		
		JPanel addItem = new JPanel();
		tabbedPane.addTab("Add Items", null, addItem, null);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		
		JLabel lblAdd = new JLabel("Add a new item");
		
		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		
		JLabel lblNewID = new JLabel("Id");
		
		textField_Price = new JTextField();
		textField_Price.setColumns(10);
		
		JLabel lblNewName = new JLabel("Name");
		
		JLabel lblNewPrice = new JLabel("Price");
		
		JLabel lblNewDisbabled = new JLabel("Disabled");
		
		JButton btnAddItem = new JButton("Submit");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> items = new ArrayList<Item>(system.getBorrowed());
				items.addAll(system.getStock());
				items.addAll(system.getSubs());
				boolean taken = false;
				boolean invalid = false;
				//Checks if Id is already taken
				for(Item I: items) {
					if((I.getId()+"").equals(textField_id.getText())) {
						taken = true;
					}
				}
				
				//Negative id's are reserved for copies of textbooks
				if(Integer.valueOf(textField_id.getText()) < 0) {
					invalid = true;
				}
				
				String type = comboBoxItemType.getSelectedItem()+"";
				
				ItemStateContext disabled = new ItemStateContext();
				if(checkDisabled.getSelectedObjects() != null) {
					disabled.setState(new Disabled());
				}
				else {
					disabled.setState(new Enabled());
				}
				//Prevents duplicate Ids from being used
				if(!taken && !invalid) {
					if(type.equals("Physical Item")) {
						//Builds the item
						PhysicalItem holder = new PhysicalItem();
						holder.setId(Integer.valueOf(textField_id.getText()));
						holder.setName(textField_Name.getText());
						holder.setPrice(Double.valueOf(textField_Price.getText()));
						holder.setDisabled(disabled);
						holder.setBorrower("BLANK");
						holder.setDueDate("BLANK");
						holder.setFee(0);
						
						mgr.addPhysicalItem(holder);
					}
					else {//Online Item
						//Builds the item
						OnlineItem holder = new OnlineItem();
						holder.setId(Integer.valueOf(textField_id.getText()));
						holder.setName(textField_Name.getText());
						holder.setPrice(Double.valueOf(textField_Price.getText()));
						holder.setDisabled(disabled);
//						holder.setSubscriber("BLANK");
						holder.setLink(textField_Link.getText());
						
						mgr.addOnlineItem(holder);
					}
				}
				else {
					if(invalid) {
						System.out.println("Negative ID's are not allowed");
					}
					else {
						System.out.println("Duplicate ID, try again");
					}
				}
			}
		});
		
		checkDisabled = new JCheckBox("");
		checkDisabled.setSelected(true);
		Vector<String> itemTypes = new Vector<String>();
		itemTypes.add("Physical Item");
		itemTypes.add("Online Item");
		comboBoxItemType = new JComboBox<String>(itemTypes);
		comboBoxItemType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxItemType.getSelectedItem().equals("Physical Item")) {
					textField_Link.setVisible(false);
					lblLink.setVisible(false);
				}
				else {
					textField_Link.setVisible(true);
					lblLink.setVisible(true);
				}
				System.out.println(comboBoxItemType.getSelectedItem()+"");
				
			}
		});
		
		JLabel lblType = new JLabel("Item Type");
		
		textField_Link = new JTextField();
		textField_Link.setColumns(10);
		
		lblLink = new JLabel("Link");
		GroupLayout gl_addItem = new GroupLayout(addItem);
		gl_addItem.setHorizontalGroup(
			gl_addItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addItem.createSequentialGroup()
							.addGap(169)
							.addComponent(btnAddItem, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_addItem.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxItemType, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType))
							.addGap(18)
							.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewName)
								.addComponent(lblAdd, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Price, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewPrice))
							.addGap(18)
							.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewDisbabled, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkDisabled)))
						.addGroup(gl_addItem.createSequentialGroup()
							.addGap(207)
							.addComponent(lblLink))
						.addGroup(gl_addItem.createSequentialGroup()
							.addGap(181)
							.addComponent(textField_Link, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_addItem.setVerticalGroup(
			gl_addItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdd, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_addItem.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_addItem.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewName)
							.addComponent(lblNewID)
							.addComponent(lblType))
						.addGroup(gl_addItem.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewPrice)
							.addComponent(lblNewDisbabled)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
						.addComponent(checkDisabled)
						.addGroup(gl_addItem.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_Price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxItemType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblLink)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_Link, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnAddItem))
		);
		addItem.setLayout(gl_addItem);
		
		JPanel disableItem = new JPanel();
		tabbedPane.addTab("Disable/Enable", null, disableItem, null);
		
		JButton btnDisableItem = new JButton("Submit");
		btnDisableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.disableItem(system.getItemAll(Integer.valueOf(textField_Disable.getText())));
			}
		});
		
		textField_Disable = new JTextField();
		textField_Disable.setColumns(10);
		
		JLabel lblDisable = new JLabel("Disable by Id");
		
		JLabel lblEnabled = new JLabel("Enable by Id");
		
		textField_Enable = new JTextField();
		textField_Enable.setColumns(10);
		
		JButton btnEnableItem = new JButton("Submit");
		btnEnableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.enableItem(system.getItemAll(Integer.valueOf(textField_Enable.getText())));
			}
		});
		GroupLayout gl_disableItem = new GroupLayout(disableItem);
		gl_disableItem.setHorizontalGroup(
			gl_disableItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(47)
					.addComponent(lblDisable, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addGap(235)
					.addComponent(lblEnabled, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGap(162))
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(10)
					.addComponent(textField_Disable, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(187)
					.addComponent(textField_Enable, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(20)
					.addComponent(btnDisableItem, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addGap(204)
					.addComponent(btnEnableItem, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addGap(147))
		);
		gl_disableItem.setVerticalGroup(
			gl_disableItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDisable, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(6)
							.addComponent(lblEnabled)))
					.addGap(6)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Disable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Enable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDisableItem)
						.addComponent(btnEnableItem)))
		);
		disableItem.setLayout(gl_disableItem);
		
		JPanel removeItem = new JPanel();
		tabbedPane.addTab("Remove Items", null, removeItem, null);
		
		JButton btnRemoveItem = new JButton("Submit");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removeItem(system.getItemAll(Integer.valueOf(txtRemoveItem.getText())));
			}
		});
		
		txtRemoveItem = new JTextField();
		txtRemoveItem.setColumns(10);
		
		JLabel lblRemove = new JLabel("Remove Item by Id");
		GroupLayout gl_removeItem = new GroupLayout(removeItem);
		gl_removeItem.setHorizontalGroup(
			gl_removeItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(146)
					.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_removeItem.createSequentialGroup()
							.addComponent(btnRemoveItem, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_removeItem.createSequentialGroup()
								.addComponent(txtRemoveItem, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_removeItem.createSequentialGroup()
								.addComponent(lblRemove, 0, 0, Short.MAX_VALUE)
								.addGap(225)))))
		);
		gl_removeItem.setVerticalGroup(
			gl_removeItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_removeItem.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addComponent(lblRemove, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRemoveItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRemoveItem)
					.addGap(84))
		);
		removeItem.setLayout(gl_removeItem);
		
		JPanel Courses = new JPanel();
		tabbedPane.addTab("Courses", null, Courses, null);
		
		textField_CCode = new JTextField();
		textField_CCode.setColumns(10);
		
		JButton btnCSubmit = new JButton("Add Course");
		btnCSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addCourse(textField_CCode.getText(),textField_CName.getText());
			}
		});
		
		JLabel lblCName = new JLabel("Course Code");
		
		textField_CName = new JTextField();
		textField_CName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		textField_CCode2 = new JTextField();
		textField_CCode2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add Student");
		
		JButton btnCAddStudent = new JButton("Add to Course");
		btnCAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addStudentToCourse(textField_CCode2.getText(), textField_SEmail.getText());
			}
		});
		
		JLabel lblAddFaculty = new JLabel("Add Faculty");
		
		textField_CCode3 = new JTextField();
		textField_CCode3.setColumns(10);
		
		JButton btnCAddFaculty = new JButton("Add to Course");
		btnCAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addFacultyToCourse(textField_CCode3.getText(), textField_FEmail.getText());
			}
		});
		
		textField_SEmail = new JTextField();
		textField_SEmail.setColumns(10);
		
		textField_FEmail = new JTextField();
		textField_FEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course Code");
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		
		JLabel lblNewLabel_3 = new JLabel("Course Code");
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		
		JLabel lblAddCourse = new JLabel("Add Course");
		
		JLabel lblRemoveCourse = new JLabel("Remove by Code");
		
		textField_CRemove = new JTextField();
		textField_CRemove.setColumns(10);
		
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removeCourse(textField_CRemove.getText());
			}
		});
		
		textField_Ctextbook = new JTextField();
		textField_Ctextbook.setColumns(10);
		
		textField_CIDtextbook = new JTextField();
		textField_CIDtextbook.setColumns(10);
		
		JButton btnAddTextBook = new JButton("Add TextBook");
		btnAddTextBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addTextBookToCourse(textField_Ctextbook.getText(), textField_CIDtextbook.getText());
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("Add Textbook to Course");
		
		JLabel label = new JLabel("New label");
		
		JLabel lblNewLabel_6 = new JLabel("Course Code");
		
		JLabel lblNewLabel_6_1 = new JLabel("Id");
		GroupLayout gl_Courses = new GroupLayout(Courses);
		gl_Courses.setHorizontalGroup(
			gl_Courses.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(63)
					.addComponent(lblAddCourse, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(192)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(256))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(10)
					.addComponent(lblCName, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(48)
					.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(98)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(31)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addGap(211))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Courses.createSequentialGroup()
							.addComponent(textField_CCode)
							.addGap(10)
							.addComponent(textField_CName, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(53)
							.addComponent(btnCSubmit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(40)))
					.addGap(63)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Courses.createSequentialGroup()
							.addComponent(textField_CCode2)
							.addGap(10)
							.addComponent(textField_SEmail, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(46)
							.addComponent(btnCAddStudent, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
							.addGap(30)))
					.addGap(57)
					.addComponent(lblRemoveCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(55))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(52)
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(146)
					.addComponent(lblAddFaculty, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
					.addGap(106)
					.addComponent(textField_CRemove, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(50))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(64)
					.addComponent(lblNewLabel_6_1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addGap(85)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addGap(76)
					.addComponent(btnRemoveCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(44))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Ctextbook)
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(27)
							.addComponent(label)))
					.addGap(23)
					.addComponent(textField_CIDtextbook, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(50)
					.addComponent(textField_CCode3, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(textField_FEmail)
					.addGap(194))
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(63)
					.addComponent(btnAddTextBook, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addGap(128)
					.addComponent(btnCAddFaculty, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
					.addGap(224))
		);
		gl_Courses.setVerticalGroup(
			gl_Courses.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Courses.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddCourse)
						.addComponent(lblNewLabel))
					.addGap(6)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCName)
						.addComponent(lblName)
						.addGroup(gl_Courses.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_4)))
					.addGap(2)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_CCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_CName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addComponent(btnCSubmit))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_CCode2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_SEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(btnCAddStudent))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(44)
							.addComponent(lblRemoveCourse)))
					.addGap(5)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_5))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(10)
							.addComponent(lblAddFaculty))
						.addComponent(textField_CRemove, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Courses.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_6))
								.addGroup(gl_Courses.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_6_1))
								.addGroup(gl_Courses.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_1))
								.addComponent(btnRemoveCourse)))
						.addGroup(gl_Courses.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_2)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_Ctextbook, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(textField_CIDtextbook, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_CCode3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_FEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_Courses.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddTextBook)
						.addComponent(btnCAddFaculty)))
		);
		Courses.setLayout(gl_Courses);
		mainPage.setLayout(gl_mainPage);
		
		//Populates Notifications Table
		//Todo: test this
		updateTable(tableNotifications,loggedIn,checkNotification(loggedIn,system));
		
		textField_Link.setVisible(false);
		lblLink.setVisible(false);
		//Sets the window text and lets user see the GUI
		frame.setTitle("Managment Team Services");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
		frame.setVisible(true);
	}
	
	public ArrayList<String> checkNotification(User loggedIn, LibrarySystem system) {//This is just tied to when the pages is opened
		ArrayList<String> notifications = new ArrayList<String>();
		/*
		 *  For putting a notification into this, format with: "SUBJECT,MESSAGE". COMMA IS REQUIRED
		 *  Just look at the example I did for new editions
		 */
		//Checks faculty notifications
		for(Faculty f: system.getFaculty()) {
			//Checks if new editions of the books exists but is not available for purchase
			for(Item I: f.getTextBooks()) {
				for(Item J: system.getBorrowed()) {
					String[] owned = I.getName().split(" ");
					String[] barrowed = J.getName().split(" ");
					boolean newEdition = true;
					int ownedEdition = 1;
					int barrowedEdition = 1;
					
					//Checks if one of the books or both have an edition number
					if((isNumeric(owned[owned.length-1]) && isNumeric(barrowed[barrowed.length-1])) || (!isNumeric(owned[owned.length-1]) && isNumeric(barrowed[barrowed.length-1]))){
						//Lengths would equal if same book different edition, unless first edition with no number
						if(owned.length == barrowed.length || owned.length+1 == barrowed.length) {
							//Checks each part of the name of the book to compare
							for(int i = 0; i < owned.length; i++) {
								if(!owned[i].equals(barrowed[i])) {
									newEdition = false;
								}
							}
							
							//Owned editions can be original 1 or higher
							if(isNumeric(owned[owned.length-1])) {
								ownedEdition = Integer.valueOf(owned[owned.length-1]);
							}
							
							//Stock editions can be original 1 or higher
							if(isNumeric(barrowed[barrowed.length-1])) {
								barrowedEdition = Integer.valueOf(barrowed[barrowed.length-1]);
							}
						}
					}
					//Checks if the book is a new edition
					if(ownedEdition < barrowedEdition && newEdition) {
						boolean canPurchase = false;
						//Makes sure there is no copy of the new edition available for purchase
						for(Item w: system.getStock()) {
							if(w.getName().equals(I.getName())) {
								canPurchase = true;
							}
						}
						//Textbook cannot be procured hence we must decided to talk to user
						if(!canPurchase) {
							//do notification
							String notification = "New Edition of "+I.getName()+"Not Available," + f.getEmail() +" has an old edition: "+ I.getName()+" while a new Edition: "+J.getName()+" is not in stock, please consult them!";
							notifications.add(notification);
						}
					}
				}
			}
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
	
}
