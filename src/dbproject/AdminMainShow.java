package dbproject;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import 고객.Center;
import 회원가입.Login;

import javax.swing.Box;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminMainShow extends JFrame {
	private JFrame mainFrame = new JFrame();
	private JPanel mainPanel;
	private JPanel subPanel;
	private JMenuBar menu;
	private Center center = new Center();
	
	class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String clicked = e.getActionCommand();
				switch(clicked) {
					case "센터추가":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = center.inputCustomer();
						mainPanel.add(subPanel);
						mainFrame.repaint();
						break;
					case "센터보기":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = center.showCustomer();
						mainPanel.add(subPanel);
						mainFrame.repaint();
						break;
					case "센터수정":
					case "센터삭제":
					case "부서추가":
					case "부서보기":
					case "부서수정":
					case "부서삭제":
					case "직원추가":
					case "직원보기":
					case "직원수정":
					case "직원삭제":
						break;
					case "고객보기":
						break;
					case "고객수정":
						break;
					case "고객삭제":
						break;
					case "AS확인":
						break;
					case "AS관리":
						break;
					case "납품확인":
						break;
				}
		}
	}
	
	// 메뉴 생성 함수
	public AdminMainShow() {
		// mainFrame 생성
		mainFrame.setTitle("AS CENTER PROGRAM(Admin Mode)");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainFrame.setLocationRelativeTo(null);
		mainFrame.setLocation(500, 250);
   mainFrame.setPreferredSize(new Dimension(800, 600));
		
		// mainPanel 생성
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 0, 800, 600));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		mainFrame.add(mainPanel);
		mainPanel.setBounds(0, 0, 800, 600);
		
		subPanel = new JPanel();
		
		menu = new JMenuBar();
		// 관리자 메뉴 생성 : 센터관리, 부서관리, 직원관리, 고객관리, AS목록, 로그아웃
		JMenuItem[][] menuItem = new JMenuItem[7][5];
		String[] menuTitle = {"센터관리", "부서관리", "직원관리", "고객관리", " AS관리", "납품관리", "로그아웃"};
		String[][] itemTitle = {
				{"센터추가", "센터보기", "센터수정", "센터삭제"},
				{"부서추가", "부서보기", "부서수정", "부서삭제"},
				{"직원추가", "직원보기", "직원수정", "직원삭제", "급여관리"},
				{"고객추가", "고객보기", "고객수정", "고객삭제"},
				{"AS확인", "AS관리"},
				{"납품확인"}
				};
		
		// 메뉴에 서브메뉴와 이벤트 생성
		ClickListener listener = new ClickListener();
		
		//  메뉴 생성
		JMenu[] showMenu = new JMenu[7];
		
		for(int i=0; i<menuTitle.length; i++) {
			showMenu[i] = new JMenu(menuTitle[i]);
		}
		
		showMenu[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainFrame.dispose();
				new Login();
			}
		});
		
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
		menu.add(Box.createHorizontalGlue());

		// login info
		JLabel logininfo = new JLabel();
		logininfo.setText("관리자님, 반갑습니다.      ");
		logininfo.setForeground(Color.blue);
		logininfo.setDisplayedMnemonic(KeyEvent.VK_S);
		
		menu.add(logininfo);
		menu.setBounds(0, 0, 800, 30);
		mainPanel.add(menu);
		
		mainFrame.pack();
		mainFrame.setVisible(true);																																															
	}
}
