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
import java.awt.event.ActionEvent;

public class NotificationsGUI {
	private JTable table;
	//Basic Setup
	
	public NotificationsGUI(JFrame frame) {
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
		scrollPane.setViewportView(table);
		
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
	}
}
