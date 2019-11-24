package 회원가입;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import 회원가입.Login;

import java.util.*;

public class SignUp extends JFrame{
	private JPanel signupPanel;
	private JTextField signupId;
	private JTextField signupName;
	private JTextField signupAge;
	private JTextField signupGender;
	private JPasswordField signupPW;
	private JList signupCenter;
	
	private int ID = 0;
	private String PW = "";
	private int AGE = 0;
	private String NAME = "";
	private String GENDER = "";
	private int CENTER = 0;
	
	public SignUp() {
		String[] centers = null;
		
		// SignUPCheck class 생성
		SignUpCheck s = new SignUpCheck();

		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		// 회원가입 패널 생성
		signupPanel = new JPanel();
		signupPanel.setBorder(new EmptyBorder(5, 5, 5,5 ));
		setContentPane(signupPanel);
		signupPanel.setLayout(null);
		
		JLabel info = new JLabel("아래의 양식을 입력해주세요.");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(15.0f));
		info.setBounds(200, 20, 200, 40);
		signupPanel.add(info);
		
		JLabel alert = new JLabel("알림메세지");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setForeground(Color.RED);
		alert.setBounds(200, 45, 200, 40);
		signupPanel.add(alert);
		
		// ID Input
		JLabel id = new JLabel("ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(140, 113, 47, 15);
		signupPanel.add(id);
		
		signupId = new JTextField("");
		signupId.setBounds(200, 110, 210, 30);
		signupPanel.add(signupId);
		signupId.setColumns(10);
		
		// Password Input
		JLabel passwd = new JLabel("비밀번호");
		passwd.setHorizontalAlignment(SwingConstants.CENTER);
		passwd.setBounds(140, 166, 47, 15);
		signupPanel.add(passwd);
		
		signupPW = new JPasswordField("");
		signupPW.setEchoChar('*');
		signupPW.setBounds(200, 160, 210, 30);
		signupPW.setColumns(10);
		signupPanel.add(signupPW);

		// 이름 Input
		JLabel name = new JLabel("이름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(140, 216, 47, 15);
		signupPanel.add(name);
		
		signupName = new JTextField("");
		signupName.setBounds(200, 210, 210, 30);
		signupName.setColumns(10);
		signupPanel.add(signupName);
		
		// 나이
		JLabel age = new JLabel("나이");
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setBounds(140, 266, 47, 15);
		signupPanel.add(age);
		
		signupAge = new JTextField("");
		signupAge.setBounds(200, 260, 210, 30);
		signupAge.setColumns(10);
		signupPanel.add(signupAge);
		
		// 성별
		JLabel g = new JLabel("성별");
		ButtonGroup gender = new ButtonGroup();
		JRadioButton male = new JRadioButton("남", true);
		JRadioButton female = new JRadioButton("여");
		
		g.setBounds(140, 318, 47, 15);
		g.setHorizontalAlignment(SwingConstants.CENTER);
		gender.add(male);
		gender.add(female);
		male.setBounds(200, 310, 50, 30);
		female.setBounds(250, 310, 100, 30);
		
		signupPanel.add(g);
		signupPanel.add(male);
		signupPanel.add(female);
				
		// 센터
		JLabel c = new JLabel("센터");
		c.setBounds(140, 360, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		signupPanel.add(c);
		
		centers = s.list_center();
		signupCenter = new JList();
		signupCenter.setListData(centers);
		
		JScrollPane scroll = new JScrollPane(signupCenter);
		scroll.setBounds(200, 362, 100, 50);
		signupPanel.add(scroll);
		
		// 회원가입
		JButton Signup = new JButton("회원가입");
		Signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration<AbstractButton> gen = gender.getElements();
				String target = null;
				String listtarget = null;
				
				while(gen.hasMoreElements()) {
					AbstractButton ab = gen.nextElement();
					JRadioButton jb = (JRadioButton)ab;
					
					if(jb.isSelected()) {
						target = jb.getText();
					}
				}
				
				listtarget = signupCenter.getSelectedValue().toString();
				getInfo(signupId, signupPW, signupName, signupAge, target, listtarget);
				s.signUP(ID, PW, NAME, AGE, GENDER, CENTER);
			}
		});
		Signup.setBounds(220, 440, 80, 30);
		signupPanel.add(Signup);
		
		// 회원가입
		JButton Cancel = new JButton("뒤로가기");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
		});
		Cancel.setBounds(310, 440, 80, 30);
		signupPanel.add(Cancel);
		
		setLocation(650, 200);
		setVisible(true);
	}
	
	private void getInfo(JTextField signupId, JTextField signupPW, JTextField signupName,JTextField signupAge, String signupGender, String signupCenter) {
		ID = Integer.parseInt(signupId.getText());
		PW = signupPW.getText();
		NAME = signupName.getText();
		AGE = Integer.parseInt(signupAge.getText());
		GENDER = signupGender;
		CENTER = Integer.parseInt(signupCenter);
	}
}
