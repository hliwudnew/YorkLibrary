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

public class ManagementTeamGUI {
	//Basic Setup
	private JFrame frame;
	private JTable mtsTable;
	private JTextField textField;
	private JTextField txtRemoveItem;
	private JTextField textField_Disable;
	
	
	
	public static void main(String[] args) {
		new ManagementTeamGUI();
	}
	
	
	public ManagementTeamGUI() {
		frame = new JFrame();
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel mainPage = new JPanel();
		mainPage.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(mainPage, "name_1113365353893500");
		
		JScrollPane mtsScroll = new JScrollPane();
		
		JButton btnRefresh = new JButton("Refresh");
		//TODO: This is awesome, I'll convert the action thing in the library GUI
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ArrayList<Item> items = new ArrayList<Item>();
				//Grabs everything from the items CSV
				items.addAll(CSVReader.itemData());
				
				//Clears the table of old data
				DefaultTableModel clear = (DefaultTableModel) mtsTable.getModel();
				clear.setRowCount(0);
				//Loops through the CSV data and adds it to the table
				for(Item e : items) {
					String[] rowdata = {e.getId()+"",e.getName(),e.getPrice() +"",e.getDisabled()+""};
					DefaultTableModel tblModel = (DefaultTableModel) mtsTable.getModel();
					tblModel.addRow(rowdata);
					//System.out.println(e.toString());
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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblAdd = new JLabel("Add a new item");
		GroupLayout gl_addItem = new GroupLayout(addItem);
		gl_addItem.setHorizontalGroup(
			gl_addItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addGap(149)
					.addGroup(gl_addItem.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdd))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		gl_addItem.setVerticalGroup(
			gl_addItem.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addItem.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(122))
		);
		addItem.setLayout(gl_addItem);
		
		JPanel removeItem = new JPanel();
		tabbedPane.addTab("Remove", null, removeItem, null);
		
		JButton btnRemoveItem = new JButton("Submit");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
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
		tabbedPane.addTab("Disable", null, disableItem, null);
		
		JButton btnDisableItem = new JButton("Submit");
		btnDisableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		textField_Disable = new JTextField();
		textField_Disable.setColumns(10);
		
		JLabel lblDisable = new JLabel("Disable by Id");
		GroupLayout gl_disableItem = new GroupLayout(disableItem);
		gl_disableItem.setHorizontalGroup(
			gl_disableItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(153)
					.addGroup(gl_disableItem.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(9)
							.addComponent(lblDisable, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(textField_Disable)
						.addGroup(gl_disableItem.createSequentialGroup()
							.addGap(12)
							.addComponent(btnDisableItem, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addGap(9)))
					.addGap(138))
		);
		gl_disableItem.setVerticalGroup(
			gl_disableItem.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_disableItem.createSequentialGroup()
					.addGap(73)
					.addComponent(lblDisable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(textField_Disable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnDisableItem)
					.addGap(58))
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
