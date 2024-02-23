package foo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainGUI implements ActionListener {
	//Basic Setup
	private JFrame frame;
	private JPanel mainPage;
	private JPanel rentPage;
	
	//
	
	
	public static void main(String[] args) {
		new MainGUI();
	}
	
	public MainGUI() {
		System.out.println("Logged in");
		//UI
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes window when closed (Set what happends when frame closed)
		frame.setTitle("York Library");
		frame.pack(); //Sets window to specific size
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		mainPage = new JPanel();
		frame.getContentPane().add(mainPage, "name_538473721006199");

		rentPage = new JPanel();
		frame.getContentPane().add(rentPage, "name_538473726123600");
		
		mainPage.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		rentPage.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		
		//Basically boilerplate
		frame.add(mainPage, BorderLayout.CENTER); //Adds the border to the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes window when closed (Set what happends when frame closed)
		frame.setTitle("York Library Login");
		frame.pack(); //Sets window to specific size
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { //onClick Function
		// TODO Auto-generated method stub
		
	}
}
