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
	private JTextField textField_RemO;
	
	public ManagementTeamGUI(LibrarySystem system, JFrame frame) {
		ManagementTeam mgr = new ManagementTeam(system);
		
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel mainPage = new JPanel();
		mainPage.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(mainPage, "name_1113365353893500");
		
		JScrollPane mtsScroll = new JScrollPane();
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) mtsTable.getModel();
				clear.setRowCount(0);
				//Looks at all items
				ArrayList<Item> holder = new ArrayList<Item>(system.getStock()); // Books in stock
				holder.addAll(system.getBorrowed());// Books people are borrowing
				holder.addAll(system.getSubOps()); // Subscription options
				
				for(Item e : holder) {
					String[] rowdata = {e.getClass().toString().substring(10),e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) mtsTable.getModel();
					tblModel.addRow(rowdata);
				}
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_mainPage = new GroupLayout(mainPage);
		gl_mainPage.setHorizontalGroup(
			gl_mainPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGroup(gl_mainPage.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(213)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(143)
							.addComponent(mtsScroll, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)))
					.addGap(162))
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGroup(gl_mainPage.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(49)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
						.addComponent(btnRefresh))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mtsScroll, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel addItem = new JPanel();
		tabbedPane.addTab("Add", null, addItem, null);
		
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
				String type = comboBoxItemType.getSelectedItem()+"";
				
				boolean disabled;
				if(checkDisabled.getSelectedObjects() != null) {
					disabled = true;
				}
				else {
					disabled = false;
				}
				
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
					holder.setSubscriber("BLANK");
					holder.setLink(textField_Link.getText());
					
					mgr.addOnlineItem(holder);
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
		tabbedPane.addTab("Remove", null, removeItem, null);
		
		JButton btnRemoveItem = new JButton("Submit");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removePhysicalItem((PhysicalItem) system.getPhysicalItem(Integer.valueOf(txtRemoveItem.getText())));
			}
		});
		
		txtRemoveItem = new JTextField();
		txtRemoveItem.setColumns(10);
		
		JLabel lblRemove = new JLabel("Remove Physical Item by Id");
		
		JButton btnRemoveItem_1 = new JButton("Submit");
		btnRemoveItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removeOnlineItem((OnlineItem) system.getOnlineItem(Integer.valueOf(textField_RemO.getText())));
			}
		});
		
		textField_RemO = new JTextField();
		textField_RemO.setColumns(10);
		
		JLabel lblRemove_1 = new JLabel("Remove Online Item by Id");
		GroupLayout gl_removeItem = new GroupLayout(removeItem);
		gl_removeItem.setHorizontalGroup(
			gl_removeItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_removeItem.createSequentialGroup()
							.addGap(12)
							.addComponent(btnRemoveItem, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(82)
							.addComponent(btnRemoveItem_1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_removeItem.createSequentialGroup()
							.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRemoveItem, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_removeItem.createSequentialGroup()
									.addComponent(lblRemove, 0, 0, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(61)
							.addGroup(gl_removeItem.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_RemO, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_removeItem.createSequentialGroup()
									.addComponent(lblRemove_1)
									.addGap(13)))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_removeItem.setVerticalGroup(
			gl_removeItem.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_removeItem.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addGroup(gl_removeItem.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemove, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRemove_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
						.addComponent(txtRemoveItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_RemO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_removeItem.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRemoveItem)
						.addComponent(btnRemoveItem_1))
					.addGap(72))
		);
		removeItem.setLayout(gl_removeItem);
		
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
