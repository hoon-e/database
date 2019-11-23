package dbproject;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;
import java.awt.*;

public class MainShow extends JFrame {
	private JFrame mainFrame = new JFrame();
	private JPanel mainPanel;
	private JMenuBar menu;

	class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String clicked = e.getActionCommand();
				switch(clicked) {
				case "센터관리":
				case "부서관리":
				case "직원관리":
				case "고객관리":
				case "AS관리":
				case "":
				}
		}
	}
	
	// 메뉴 생성 함수
	public MainShow() {
		// mainFrame 생성
		mainFrame.setTitle("AS CENTER PROGRAM");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.setLocationRelativeTo(null);
		mainFrame.setLocation(500, 250);
    mainFrame.setPreferredSize(new Dimension(900, 600));
		
		// mainPanel 생성
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 900, 600));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		mainFrame.add(mainPanel);
		mainPanel.setBounds(0, 0, 900, 600);
		
		menu = new JMenuBar();
		// 관리자 메뉴 생성 : 센터관리, 부서관리, 직원관리, 고객관리, AS목록, 로그아웃
		JMenuItem[][] menuItem = new JMenuItem[6][4];
		String[] menuTitle = {"센터관리", "부서관리", "직원관리", "고객관리", " AS관리",  "로그아웃"};
		String[][] itemTitle = {
				{"센터추가", "센터보기", "센터수정", "센터삭제"},
				{"부서추가", "부서보기", "부서수정", "부서삭제"},
				{"직원추가", "직원보기", "직원수정", "직원삭제"},
				{"고객추가", "고객보기", "고객수정", "고객삭제"},
				{"AS확인", "AS관리"}};
		
		//  메뉴 생성
		JMenu[] showMenu = new JMenu[6];
		for(int i=0; i<menuItem.length; i++) {
			showMenu[i] = new JMenu(menuTitle[i]);
		}
		
		// 메뉴에 서브메뉴와 이벤트 생성
		ClickListener listener = new ClickListener();
		for(int i = 0; i < itemTitle.length; i++) {
			for(int j = 0; j < itemTitle[i].length; j++) {
				menuItem[i][j] = new JMenuItem(itemTitle[i][j]);
				menuItem[i][j].addActionListener(listener);
				showMenu[i].add(menuItem[i][j]);
			}
		}
		
		// menu 생성
		for(int i=0; i<menuItem.length; i++) {
			menu.add(showMenu[i]);
		}
		
		menu.setBounds(0, 0, 900, 35);
		mainPanel.add(menu);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
