package dbproject;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainShow extends JFrame {

	public static void main(String[] args) {
		new MainShow();
	}
	
	public MainShow() {
		super("Show Event");
		
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = this.getContentPane();
		JPanel pane = new JPanel();
		JButton buttonStart = new JButton("Start");
		buttonStart.setMnemonic('S');
		
		pane.add(buttonStart);
		contentPane.add(pane);
		
		setVisible(true);
	}
}
