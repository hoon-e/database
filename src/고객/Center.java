package 고객;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import back.aboutCenter;

public class Center extends JFrame{
	
	private JPanel center;
	private JTextField centerId;
	private JTextField centerName;
	private JTextField centerPhone;
	private JTextField centerAddress;
	
	private int centerID = 0;
	private String centerNAME = "";
	private String centerPHONE = "";
	private String centerADDR = "";
	public String msg = "";
	
	public JPanel showCustomer() {
		center = new JPanel();
		center.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(center);
		center.setLayout(null);
		center.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("센터정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		center.add(info);
		
		return center;
	}
	
	// Customer 정보 삽입 함수
	public JPanel inputCustomer() {
		aboutCenter aC = new aboutCenter();
		
		center = new JPanel();
		center.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(center);
		center.setLayout(null);
		center.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("센터정보 입력");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		center.add(info);
		
		JLabel alert = new JLabel("아래의 정보를 입력하여 주세요.");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setForeground(Color.RED);
		alert.setBounds(7, 68, 200, 40);
		center.add(alert);
		
		// Center_ID Input
		JLabel id = new JLabel("센터ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(info.getFont().deriveFont(15.0f));
		id.setBounds(140, 160, 70, 15);
		center.add(id);
		
		centerId = new JTextField("");
		centerId.setBounds(230, 153, 230, 30);
		center.add(centerId);
		centerId.setColumns(10);
		
		// Center_name Input
		JLabel name = new JLabel("센터이름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(name.getFont().deriveFont(15.0f));
		name.setBounds(140, 230, 70, 15);
		center.add(name);
		
		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(message.getFont().deriveFont(13.0f));
		message.setForeground(Color.RED);
		message.setBounds(480, 157, 200, 15);
		center.add(message);
		
		centerName = new JTextField("");
		centerName.setBounds(230, 223, 230, 30);
		center.add(centerName);
		centerName.setColumns(10);
		
		// Center_phone Input
		JLabel phone = new JLabel("센터번호");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(info.getFont().deriveFont(15.0f));
		phone.setBounds(140, 300, 70, 15);
		center.add(phone);
		
		centerPhone = new JTextField("");
		centerPhone.setBounds(230, 293, 230, 30);
		center.add(centerPhone);
		centerPhone.setColumns(10);
		
		// Center_address Input
		JLabel addr = new JLabel("센터주소");
		addr.setHorizontalAlignment(SwingConstants.CENTER);
		addr.setFont(info.getFont().deriveFont(15.0f));
		addr.setBounds(140, 370, 70, 15);
		center.add(addr);
		
		centerAddress = new JTextField("");
		centerAddress.setBounds(230, 363, 230, 30);
		center.add(centerAddress);
		centerAddress.setColumns(10);
		
		// 정보 추가 버튼
		JButton Add = new JButton("정보추가");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 값을 입력하지않았을 경우
					if(centerId.getText().equals("")| centerName.getText().equals("") | centerPhone.getText().equals("") | centerAddress.getText().equals("")) {
						msg = "값을 입력해주세요";
						message.setText(msg);
						center.repaint();
					}
					else {
						getInfo(centerId, centerName, centerPhone, centerAddress);
						boolean flag = aC.isDuplicated(centerID);
						
						// 중복이 존재할 경우
						if(flag == true) {
							msg = Integer.toString(centerID) + "은 이미 존재하는 센터입니다.";
							message.setText(msg);
							centerId.setText("");
							center.repaint();
						}else {
							// Center 정보 입력
							aC.addCenter(centerID, centerNAME, centerPHONE, centerADDR);
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Add.setBounds(230, 430, 80, 30);
		center.add(Add);
		
		JButton Reset = new JButton("초기화");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerId.setText("");
				centerName.setText("");
				centerPhone.setText("");
				centerAddress.setText("");
				//center.repaint();
			}
		});
		Reset.setBounds(330, 430, 80, 30);
		center.add(Reset);
		
		return center;
	}
	
	public void getInfo(JTextField centerid, JTextField centername, JTextField centerphone, JTextField centeraddr) {
		centerID = Integer.parseInt(centerid.getText());
		centerNAME = centername.getText();
		centerPHONE = centerphone.getText();
		centerADDR = centeraddr.getText();
	}
}
