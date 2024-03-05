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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
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
				ArrayList<PhysicalItem> holder = new ArrayList<PhysicalItem>(system.getStock());
				holder.addAll(system.getBorrowed());
				for(PhysicalItem e : holder) {
					String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
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
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(143)
							.addComponent(mtsScroll, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)))
					.addGap(162))
		);
		gl_mainPage.setVerticalGroup(
			gl_mainPage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPage.createSequentialGroup()
					.addGroup(gl_mainPage.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_mainPage.createSequentialGroup()
							.addGap(49)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
						.addGroup(gl_mainPage.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRefresh)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mtsScroll, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
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
				boolean disabled;
				if(checkDisabled.getSelectedObjects() != null) {
					disabled = true;
				}
				else {
					disabled = false;
				}
				//Builds the item
				PhysicalItem holder = new PhysicalItem();
				holder.setId(Integer.valueOf(textField_id.getText()));
				holder.setName(textField_Name.getText());
				holder.setPrice(Double.valueOf(textField_Price.getText()));
				holder.setDisabled(disabled);
				holder.setBorrower("BLANK");
				holder.setDueDate("BLANK");
				holder.setFee(0);
				mgr.addItem(holder);
			}
		});
		
		checkDisabled = new JCheckBox("");
		checkDisabled.setSelected(true);
		GroupLayout gl_addItem = new GroupLayout(addItem);
		gl_addItem.setHorizontalGroup(
			gl_addItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addGap(169)
					.addComponent(btnAddItem, GroupLayout.PREFERRED_SIZE, 128, Short.MAX_VALUE)
					.addGap(143))
				.addGroup(Alignment.LEADING, gl_addItem.createSequentialGroup()
					.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addItem.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNewID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(lblNewName)
							.addGap(94)
							.addComponent(lblNewPrice)
							.addGap(69)
							.addComponent(lblNewDisbabled, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
							.addGap(13))
						.addGroup(gl_addItem.createSequentialGroup()
							.addGap(149)
							.addComponent(lblAdd, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
							.addGap(144))
						.addGroup(gl_addItem.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField_id, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_Name, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Price, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkDisabled)
							.addGap(55)))
					.addGap(37))
		);
		gl_addItem.setVerticalGroup(
			gl_addItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_addItem.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewID)
						.addComponent(lblNewName)
						.addComponent(lblNewPrice)
						.addComponent(lblNewDisbabled))
					.addGap(11)
					.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addItem.createSequentialGroup()
							.addGroup(gl_addItem.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(88)
							.addComponent(btnAddItem))
						.addComponent(checkDisabled))
					.addContainerGap())
		);
		addItem.setLayout(gl_addItem);
		
		JPanel removeItem = new JPanel();
		tabbedPane.addTab("Remove", null, removeItem, null);
		
		JButton btnRemoveItem = new JButton("Submit");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.removeItem(system.getItem(Integer.valueOf(txtRemoveItem.getText())));
			}
		});
		
		txtRemoveItem = new JTextField();
		txtRemoveItem.setColumns(10);
		
		JLabel lblRemove = new JLabel("Remove by Id");
		GroupLayout gl_removeItem = new GroupLayout(removeItem);
		gl_removeItem.setHorizontalGroup(
			gl_removeItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(157)
					.addComponent(btnRemoveItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(155))
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(145)
					.addComponent(txtRemoveItem)
					.addGap(146))
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(154)
					.addComponent(lblRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(156))
		);
		gl_removeItem.setVerticalGroup(
			gl_removeItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_removeItem.createSequentialGroup()
					.addGap(77)
					.addComponent(lblRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtRemoveItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRemoveItem)
					.addGap(54))
		);
		removeItem.setLayout(gl_removeItem);
		
		JPanel disableItem = new JPanel();
		tabbedPane.addTab("Disable/Enable", null, disableItem, null);
		
		JButton btnDisableItem = new JButton("Submit");
		btnDisableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mgr.disableItem(system.getItem(Integer.valueOf(textField_Disable.getText())));
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
				mgr.enableItem(system.getItem(Integer.valueOf(textField_Enable.getText())));
			}
		});
		GroupLayout gl_disableItem = new GroupLayout(disableItem);
		gl_disableItem.setHorizontalGroup(
			gl_disableItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_disableItem.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(9)
							.addComponent(lblDisable, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(textField_Disable, 149, 149, 149)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(12)
							.addComponent(btnDisableItem, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
							.addGap(9)))
					.addGap(43)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(9)
							.addComponent(lblEnabled, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_Enable, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(12)
							.addComponent(btnEnableItem, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
					.addGap(66))
		);
		gl_disableItem.setVerticalGroup(
			gl_disableItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_disableItem.createSequentialGroup()
					.addGap(71)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addComponent(lblEnabled)
							.addGap(11)
							.addComponent(textField_Enable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btnEnableItem)
							.addContainerGap())
						.addGroup(gl_disableItem.createSequentialGroup()
							.addComponent(lblDisable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(textField_Disable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btnDisableItem)
							.addGap(60))))
		);
		disableItem.setLayout(gl_disableItem);
		
		mtsTable = new JTable();
		mtsTable.setModel(new DefaultTableModel(
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
		mtsScroll.setViewportView(mtsTable);
		mainPage.setLayout(gl_mainPage);
		
		
		
		
		//Sets the window text and lets user see the GUI
		frame.setTitle("Managment Team Services");
		//frame.setSize(1000, 750); //In editor it doesn't fit, but when running application it looks good
		frame.setSize(862, 649);
		frame.setVisible(true);
	}
}
