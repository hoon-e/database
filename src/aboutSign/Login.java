package aboutSign;

import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;
import dbproject.*;

public class Login extends JFrame{

	private JPanel loginPanel;
	private JTextField loginid;
	private JTextField loginpasswd;
	
	private int ID;
	private String PW = "";
	
	public Login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		JLabel explain = new JLabel("데이터베이스 프로젝트 | 김재훈");
		explain.setBounds(125, 220, 200, 20);
		loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5,5 ));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.add(explain);
		
		JLabel id = new JLabel("ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(40, 64, 47, 15);
		loginPanel.add(id);
		
		// loginID
		loginid = new JTextField();
		loginid.setBounds(90, 58, 166, 30);
		loginPanel.add(loginid);
		loginid.setColumns(10);
		
		// login Password
		JLabel pwd = new JLabel("PW");
		pwd.setHorizontalAlignment(SwingConstants.CENTER);
		pwd.setBounds(40, 103, 47, 15);
		loginPanel.add(pwd);
		
		loginpasswd = new JTextField();
		loginpasswd.setText("  비밀번호를 입력하세요");
		loginpasswd.setBounds(90, 95, 166, 30);
		loginPanel.add(loginpasswd);
		loginpasswd.setColumns(10);
		
		// login Button
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 버튼 클릭 시
				boolean logincheck = false;
				getInfo(loginid, loginpasswd);
				
				LoginCheck loginCheck = new LoginCheck();
				logincheck = loginCheck.doLogin(ID, PW);
				
				if(!logincheck) {
					JOptionPane.showMessageDialog(null, "ID 또는 비밀번호를 확인하세요");
				}else {
					setVisible(false);
					new MainShow();
				}
			}
		});
		
		loginButton.setBounds(265, 58, 80, 65);
		loginPanel.add(loginButton);
		
		// signup Button
		JButton signup = new JButton("회원가입");
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp();
			}
		});
		
		signup.setBounds(265, 134, 80, 30);
		loginPanel.add(signup);
		
		// find ID
		JButton findid = new JButton("ID찾기");
		findid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			//	 
		});
		findid.setBounds(85, 134, 78, 30);
		loginPanel.add(findid);
		
		// find PW
		JButton findpw = new JButton("PW찾기");
		findid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			//
		});
		findpw.setBounds(175, 134, 78, 30);
		loginPanel.add(findpw);
	
		setLocation(800, 300);
		setVisible(true);
	}
	
	private void getInfo(JTextField id, JTextField pw) {
		ID = Integer.parseInt(id.getText());
		PW = pw.getText();
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
