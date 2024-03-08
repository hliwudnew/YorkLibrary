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
	
	public ManagementTeamGUI(LibrarySystem system, JFrame frame) {
		ManagementTeam mgr = new ManagementTeam(system);
		
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel mainPage = new JPanel();
		mainPage.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(mainPage, "name_1113365353893500");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_mainPage = new GroupLayout(mainPage);
		gl_mainPage.setHorizontalGroup(
			gl_mainPage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGap(210)
					.addGroup(gl_mainPage.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING)
						.addComponent(tabbedPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
					.addGap(105))
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
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
				"Type", "Id", "Name", "Price", "Disabled"
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
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) mtsTable.getModel();
				clear.setRowCount(0);
				//Looks at all items
				ArrayList<Item> holder = new ArrayList<Item>(system.getStock()); // Books in stock
				holder.addAll(system.getBorrowed());// Books people are borrowing
				holder.addAll(system.getSubs()); // Subscription options
				
				for(Item e : holder) {
					String[] rowdata = {e.getClass().toString().substring(10),e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) mtsTable.getModel();
					tblModel.addRow(rowdata);
				}
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
				//Checks if Id is already taken
				for(Item I: items) {
					if((I.getId()+"").equals(textField_id.getText())) {
						taken = true;
					}
				}
				
				String type = comboBoxItemType.getSelectedItem()+"";
				
				boolean disabled;
				if(checkDisabled.getSelectedObjects() != null) {
					disabled = true;
				}
				else {
					disabled = false;
				}
				//Prevents duplicate Ids from being used
				if(!taken) {
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
					System.out.println("Duplicate ID, try again");
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
			gl_addItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addContainerGap(383, Short.MAX_VALUE)
					.addComponent(lblNewDisbabled, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(lblNewPrice)
								.addGroup(gl_addItem.createSequentialGroup()
									.addComponent(textField_Price, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(checkDisabled)))))
					.addContainerGap(22, Short.MAX_VALUE))
				.addGroup(gl_addItem.createSequentialGroup()
					.addContainerGap(183, Short.MAX_VALUE)
					.addComponent(textField_Link, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(175))
				.addGroup(Alignment.LEADING, gl_addItem.createSequentialGroup()
					.addGap(207)
					.addComponent(lblLink)
					.addContainerGap(219, Short.MAX_VALUE))
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
						.addComponent(lblNewDisbabled)
						.addComponent(lblNewPrice))
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
					.addGap(3)
					.addComponent(textField_Link, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnAddItem))
		);
		addItem.setLayout(gl_addItem);
		
		JPanel disableItem = new JPanel();
		tabbedPane.addTab("Disable/Enable", null, disableItem, null);
		
		JButton btnDisableItem = new JButton("Submit");
		btnDisableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.disableItem(system.getOnlineItem(Integer.valueOf(textField_Disable.getText())));
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
				mgr.enableItem(system.getOnlineItem(Integer.valueOf(textField_Enable.getText())));
			}
		});
		GroupLayout gl_disableItem = new GroupLayout(disableItem);
		gl_disableItem.setHorizontalGroup(
			gl_disableItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(33)
							.addComponent(lblDisable, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_disableItem.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_disableItem.createSequentialGroup()
									.addGap(10)
									.addComponent(btnDisableItem, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField_Disable, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
					.addGap(70)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGroup(gl_disableItem.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_Enable, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEnableItem, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
							.addGap(10))
						.addGroup(gl_disableItem.createSequentialGroup()
							.addComponent(lblEnabled, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(50))))
		);
		gl_disableItem.setVerticalGroup(
			gl_disableItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisable, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnabled))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Disable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Enable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisableItem)
						.addComponent(btnEnableItem))
					.addGap(41))
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
		textField_CCode.setBounds(10, 56, 86, 20);
		textField_CCode.setColumns(10);
		
		JButton btnCSubmit = new JButton("Add Course");
		btnCSubmit.setBounds(63, 87, 89, 23);
		btnCSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addCourse(textField_CCode.getText(),textField_CName.getText());
			}
		});
		
		JLabel lblCName = new JLabel("Course Code");
		lblCName.setBounds(10, 31, 62, 14);
		
		textField_CName = new JTextField();
		textField_CName.setBounds(106, 56, 86, 20);
		textField_CName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(120, 31, 62, 14);
		
		textField_CCode2 = new JTextField();
		textField_CCode2.setBounds(255, 47, 86, 20);
		textField_CCode2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add Student");
		lblNewLabel.setBounds(315, 11, 60, 14);
		
		JButton btnCAddStudent = new JButton("Add to Course");
		btnCAddStudent.setBounds(301, 73, 106, 23);
		btnCAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addStudentToCourse(textField_CCode2.getText(), textField_SEmail.getText());
			}
		});
		
		JLabel lblAddFaculty = new JLabel("Add Faculty");
		lblAddFaculty.setBounds(315, 125, 57, 14);
		
		textField_CCode3 = new JTextField();
		textField_CCode3.setBounds(255, 170, 86, 20);
		textField_CCode3.setColumns(10);
		
		JButton btnCAddFaculty = new JButton("Add to Course");
		btnCAddFaculty.setBounds(297, 201, 110, 23);
		btnCAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addFacultyToCourse(textField_CCode3.getText(), textField_FEmail.getText());
			}
		});
		
		textField_SEmail = new JTextField();
		textField_SEmail.setBounds(351, 47, 86, 20);
		textField_SEmail.setColumns(10);
		
		textField_FEmail = new JTextField();
		textField_FEmail.setBounds(351, 170, 86, 20);
		textField_FEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course Code");
		lblNewLabel_1.setBounds(267, 150, 62, 14);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(378, 150, 24, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Course Code");
		lblNewLabel_3.setBounds(280, 31, 62, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(381, 31, 24, 14);
		
		JLabel lblAddCourse = new JLabel("Add Course");
		lblAddCourse.setBounds(63, 11, 60, 14);
		
		JLabel lblRemoveCourse = new JLabel("Remove by Code");
		lblRemoveCourse.setBounds(494, 91, 82, 14);
		
		textField_CRemove = new JTextField();
		textField_CRemove.setBounds(478, 115, 103, 20);
		textField_CRemove.setColumns(10);
		
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.setBounds(478, 146, 109, 23);
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removeCourse(textField_CRemove.getText());
			}
		});
		
		textField_Ctextbook = new JTextField();
		textField_Ctextbook.setBounds(10, 170, 86, 20);
		textField_Ctextbook.setColumns(10);
		
		textField_CIDtextbook = new JTextField();
		textField_CIDtextbook.setBounds(119, 170, 86, 20);
		textField_CIDtextbook.setColumns(10);
		
		JButton btnAddTextBook = new JButton("Add TextBook");
		btnAddTextBook.setBounds(63, 201, 106, 23);
		btnAddTextBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.addTextBookToCourse(textField_Ctextbook.getText(), textField_CIDtextbook.getText());
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("Add Textbook to Course");
		lblNewLabel_5.setBounds(52, 125, 117, 14);
		
		JLabel label = new JLabel("New label");
		label.setBounds(37, 170, 46, 14);
		
		JLabel lblNewLabel_6 = new JLabel("Course Code");
		lblNewLabel_6.setBounds(10, 150, 62, 14);
		
		JLabel lblNewLabel_6_1 = new JLabel("Id");
		lblNewLabel_6_1.setBounds(136, 150, 46, 14);
		Courses.setLayout(null);
		Courses.add(lblAddCourse);
		Courses.add(lblNewLabel);
		Courses.add(lblCName);
		Courses.add(lblName);
		Courses.add(lblNewLabel_3);
		Courses.add(lblNewLabel_4);
		Courses.add(textField_CCode);
		Courses.add(textField_CName);
		Courses.add(btnCSubmit);
		Courses.add(textField_CCode2);
		Courses.add(textField_SEmail);
		Courses.add(btnCAddStudent);
		Courses.add(lblAddFaculty);
		Courses.add(lblRemoveCourse);
		Courses.add(lblNewLabel_1);
		Courses.add(lblNewLabel_2);
		Courses.add(textField_CRemove);
		Courses.add(textField_CCode3);
		Courses.add(textField_FEmail);
		Courses.add(btnRemoveCourse);
		Courses.add(btnCAddFaculty);
		Courses.add(textField_Ctextbook);
		Courses.add(textField_CIDtextbook);
		Courses.add(btnAddTextBook);
		Courses.add(lblNewLabel_5);
		Courses.add(label);
		Courses.add(lblNewLabel_6);
		Courses.add(lblNewLabel_6_1);
		mainPage.setLayout(gl_mainPage);
		
		
		
		textField_Link.setVisible(false);
		lblLink.setVisible(false);
		//Sets the window text and lets user see the GUI
		frame.setTitle("Managment Team Services");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
		frame.setVisible(true);
	}
}
