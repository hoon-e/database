package dbproject;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.AS;
import view.Center;
import view.Customer;
import view.Department;
import view.Employee;
import view.Login;
import view.Supply;

import javax.swing.Box;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminMainShow extends JFrame {
	public static JFrame mainFrame = new JFrame();
	public static JPanel mainPanel;
	public static JPanel subPanel;
	private JMenuBar menu;
	private Center center = new Center();
	private Department dep = new Department();
	private Employee emp = new Employee();
	private Customer cust = new Customer();
	private Supply supp = new Supply();
	private AS as = new AS();
	
	class ClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String clicked = e.getActionCommand();
				switch(clicked) {
					case "센터추가": // 센터 추가
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = center.inputCenter();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "센터보기": // 센터 보기 
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = center.showCenter();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "센터수정": // 센터 수정
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = center.editCenter();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "부서추가":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = dep.inputDep();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "부서보기":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = dep.showDep();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "부서수정":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = dep.editDep();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "직원추가":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = emp.inputEmp();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "직원보기":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = emp.showEmp();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "고객보기":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = cust.showCustomer();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "고객수정":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = cust.editCustomer();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "AS보기":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = as.showAS();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "납품추가":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = supp.inputSupply();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
					case "납품확인":
						if(subPanel.isEnabled()) {
							mainPanel.remove(subPanel);
						}
						subPanel = supp.showMS();
						mainPanel.add(subPanel);
						mainFrame.revalidate();
						mainFrame.repaint();
						break;
				}
		}
	}
	
	// 메뉴 생성 함수
	public AdminMainShow() {
		revalidate();
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
		subPanel.setBounds(100, 100, 500, 400);
		
		menu = new JMenuBar();
		// 관리자 메뉴 생성 : 센터관리, 부서관리, 직원관리, 고객관리, AS목록, 로그아웃
		JMenuItem[][] menuItem = new JMenuItem[7][5];
		String[] menuTitle = {"센터관리", "부서관리", "직원관리", "고객관리", " AS관리", "납품관리", "로그아웃"};
		String[][] itemTitle = {
					{"센터추가", "센터보기", "센터수정"},
					{"부서추가", "부서보기", "부서수정"},
					{"직원추가", "직원보기"},
					{"고객보기", "고객수정"},
					{"AS보기"},
					{"납품추가", "납품확인"}
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
				mainFrame.setVisible(false);
				dispose();
				System.gc();
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
