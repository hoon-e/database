package dbproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import back.SignUpCheck;
import view.Center;
import view.Customer;
import view.Department;
import view.Employee;
import view.Login;
import view.SignUp;

public class UserMainShow extends JFrame{
	private static JFrame mainFrame = new JFrame();
	private static JPanel mainPanel;
	public static JPanel subPanel;
	private JMenuBar menu;
	
	private Center center = new Center();
	private Department dep = new Department();
	private Employee emp = new Employee();
	private Customer cust = new Customer();
	
	class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String clicked = e.getActionCommand();
				switch(clicked) {
				// 고객은 센터, 부서, 직원 정보를 열람하는 것만 가능하다.
				case "센터정보": // 센터 정보 보기
				case "부서정보":
				case "직원정보":
				// AS신청을
				case "AS신청":
				case "정보수정":
				case "로그아웃":
				}
			}
	}
	
	// 메뉴 생성 함수
	public UserMainShow(int loginid) {
		SignUpCheck c = new SignUpCheck();
		String username = c.getName(loginid);
		// mainFrame 생성
		mainFrame.setTitle("AS CENTER PROGRAM");
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
		subPanel.setBounds(100, 100, 500, 400);
				
		menu = new JMenuBar();
		//  사용자 메뉴 생성 : 센터정보, 부서정보, 직원정보, AS신청, 정보수정,  로그아웃
		JMenuItem[][] menuItem = new JMenuItem[6][3];
		String[] menuTitle = {"센터정보",  "부서정보", "직원정보", " AS신청", "정보수정",  "로그아웃"};
		String[][] itemTitle = {
				{},{},{},{"AS신청", "AS확인", "AS수정"}
		};
		
		//  메뉴 생성
		JMenu[] showMenu = new JMenu[6];
		for(int i=0; i<menuItem.length; i++) {
			showMenu[i] = new JMenu(menuTitle[i]);
		}
		
		showMenu[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(subPanel.isEnabled()) {
					mainPanel.remove(subPanel);
				}
				subPanel = center.showCenter();
				mainPanel.add(subPanel);
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		
		showMenu[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(subPanel.isEnabled()) {
					mainPanel.remove(subPanel);
				}
				subPanel = dep.showDep();
				mainPanel.add(subPanel);
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		
		showMenu[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(subPanel.isEnabled()) {
					mainPanel.remove(subPanel);
				}
				subPanel = emp.showEmp();
				mainPanel.add(subPanel);
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		
		showMenu[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainFrame.setVisible(false);
				dispose();
				System.gc();
				new Login();
			}
		});
		
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
		
		// box horizontal 생성
		menu.add(Box.createHorizontalGlue());

		// login info
		JLabel logininfo = new JLabel();
		logininfo.setText(username + "님, 반갑습니다.      ");
		logininfo.setForeground(Color.blue);
		logininfo.setDisplayedMnemonic(KeyEvent.VK_S);
		
		menu.add(logininfo);
		menu.setBounds(0, 0, 800, 32);
		mainPanel.add(menu);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}
