package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import back.SignUpCheck;
import back.aboutDepartment;
import dbproject.AdminMainShow;

public class Department extends JFrame{
	
	private JPanel dep;
	private JTextField depId;
	private JTextField depName;
	private JTextField depAddress;
	private JTextField depPhone;
	private JList<String> centerList;
	
	private int depID = 0;
	private String depNAME = "";
	private String depPHONE = "";
	private String depADDR = "";
	public String msg = "";
	
	// 부서 정보 보기
	public JPanel showDep() {
		aboutDepartment aD = new aboutDepartment();

		dep = new JPanel();
		dep.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(dep);
		dep.setLayout(null);
		dep.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("부서정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		dep.add(info);
		
		String[] header = {"부서ID","부서이름", "부서번호", "부서주소", "센터ID"};
		String[][] contents = aD.getValue();
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		JTable table = new JTable(contents, header);
		table.setModel(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		
		scrollpane.setBounds(70, 100, 600, 400);
		dep.add(scrollpane);
		
		return dep;
	}
	
	// 부서 정보 수정
	public JPanel editDep() {
		aboutDepartment aD = new aboutDepartment();

		dep = new JPanel();
		dep.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(dep);
		dep.setLayout(null);
		dep.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("부서정보 수정");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		dep.add(info);
		
		// 부서 정보 열람
		String[] header = {"부서ID","부서이름", "부서주소", "부서번호"};
		String[][] contents = aD.getValue();
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		       }
		};
		
		JTable table = new JTable(contents, header);
		table.setModel(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(70, 110, 400, 350);
		dep.add(scrollpane);
		
		JLabel editmsg = new JLabel("수정하고 싶은 부서번호를 입력해주세요.");
		editmsg.setHorizontalAlignment(SwingConstants.CENTER);
		editmsg.setFont(editmsg.getFont().deriveFont(13.0f));
		editmsg.setForeground(Color.RED);
		editmsg.setBounds(485, 105, 250, 30);
		dep.add(editmsg);
		
		JTextField editCenter = new JTextField();
		editCenter.setBounds(500, 140, 200, 30);
		editCenter.setColumns(10);
		dep.add(editCenter);
		
		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(message.getFont().deriveFont(13.0f));
		message.setForeground(Color.RED);
		message.setBounds(460, 85, 200, 15);
		dep.add(message);
		
		// 수정하기
		JButton accept = new JButton("수정하기");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(editCenter.getText().equals("")) {
					message.setText("값을 입력해주세요!");
					dep.repaint();
				}
				else {
					int id = Integer.parseInt(editCenter.getText());
					boolean check = aD.isDuplicated(id);
					
					if( check == false ) {
						message.setText("존재하지않는 id값입니다!");
						dep.repaint();
					}
					else {
						int result = JOptionPane.showConfirmDialog(dep, "수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION);
						if( result == JOptionPane.YES_OPTION ) {
							if(AdminMainShow.subPanel.isEnabled()) {
								AdminMainShow.mainPanel.remove(AdminMainShow.subPanel);
							}
							AdminMainShow.subPanel = editPanel(id);
							AdminMainShow.mainPanel.add(AdminMainShow.subPanel);
							AdminMainShow.mainFrame.repaint();
						}
						else if( result == JOptionPane.NO_OPTION ) {
							// nothing
						}
					}
				}
			} 
		});
		accept.setBounds(500, 180, 80, 30);
		dep.add(accept);
		
		return dep;
	}
	
	// 부서 정보 수정
	public JPanel editPanel(int did) {
		aboutDepartment aD = new aboutDepartment();
		SignUpCheck s = new SignUpCheck();
		String[] value = aD.getiDValue(did);
		
		dep = new JPanel();
		dep.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(dep);
		dep.setLayout(null);
		dep.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("부서정보 수정");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		dep.add(info);
		
		JLabel alert = new JLabel("아래의 정보를 수정하여 주세요.");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setForeground(Color.RED);
		alert.setBounds(7, 68, 200, 40);
		dep.add(alert);
		
		// dep_ID Input
		JLabel id = new JLabel("부서ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(info.getFont().deriveFont(13.0f));
		id.setBounds(140, 140, 70, 15);
		dep.add(id);
		
		depId = new JTextField(value[0]);
		depId.setBounds(230, 133, 230, 30);
		dep.add(depId);
		depId.setColumns(10);
		
		// dep_name Input
		JLabel name = new JLabel("부서이름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(name.getFont().deriveFont(13.0f));
		name.setBounds(140, 190, 70, 15);
		dep.add(name);
		
		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(message.getFont().deriveFont(13.0f));
		message.setForeground(Color.RED);
		message.setBounds(480, 125, 200, 15);
		dep.add(message);
		
		depName = new JTextField(value[1]);
		depName.setBounds(230, 183, 230, 30);
		dep.add(depName);
		depName.setColumns(10);
		
		// dep_phone Input
		JLabel phone = new JLabel("부서주소");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(info.getFont().deriveFont(13.0f));
		phone.setBounds(140, 240, 70, 15);
		dep.add(phone);
		
		depPhone = new JTextField(value[2]);
		depPhone.setBounds(230, 233, 230, 30);
		dep.add(depPhone);
		depPhone.setColumns(10);
		
		// dep_address Input
		JLabel addr = new JLabel("부서번호");
		addr.setHorizontalAlignment(SwingConstants.CENTER);
		addr.setFont(info.getFont().deriveFont(13.0f));
		addr.setBounds(140, 290, 70, 15);
		dep.add(addr);
		
		depAddress = new JTextField(value[3]);
		depAddress.setBounds(230, 283, 230, 30);
		dep.add(depAddress);
		depAddress.setColumns(10);
		
		// 센터정보
		JLabel c = new JLabel("센터");
		c.setBounds(160, 342, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		dep.add(c);
		
		String[] centers = s.list_center();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		int index = 0;
		int count = 0;
		
		// target 설정
		for(String cen : centers) {
			if( cen.equals(value[4]) ) {
				index = count;
				break;
			}
			count+=1;
		}
		
		centerList.setSelectedIndex(index);
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(230, 335, 130, 80);
		dep.add(roll);
		
		// 정보 수정 버튼
		JButton Add = new JButton("정보수정");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 값을 입력하지않았을 경우
					if(depId.getText().equals("")| depName.getText().equals("") | depPhone.getText().equals("") | depAddress.getText().equals("")) {
						msg = "값을 입력해주세요";
						message.setText(msg);
						dep.repaint();
					}
					else {
						getInfo(depId, depName, depPhone, depAddress);
						
						// listTarget 정보 가져오기
						String listTarget = centerList.getSelectedValue().toString();
						
						// dep 정보 입력
						aD.editDep(depID, depNAME, depPHONE, depADDR, Integer.parseInt(listTarget), did);
						int result = JOptionPane.showConfirmDialog(dep, "수정되었습니다.", "수정확인", JOptionPane.YES_OPTION );
						
						if( result == JOptionPane.YES_OPTION) {
							if(AdminMainShow.subPanel.isEnabled()) {
								AdminMainShow.mainPanel.remove(AdminMainShow.subPanel);
							}
							AdminMainShow.mainPanel.add(AdminMainShow.subPanel);
							AdminMainShow.mainFrame.repaint();
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Add.setBounds(500, 155, 80, 30);
		dep.add(Add);
		
		// Reset 버튼
		JButton Reset = new JButton("초기화");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depId.setText("");
				depName.setText("");
				depPhone.setText("");
				depAddress.setText("");
				//dep.repaint();
			}
		});
		
		Reset.setBounds(600, 155, 80, 30);
		dep.add(Reset);
		
		dep.setVisible(true);
		return dep;
	}
	
	// 부서 정보 삽입 함수
	public JPanel inputDep() {
			aboutDepartment aD = new aboutDepartment();
			SignUpCheck s = new SignUpCheck();
			
			dep = new JPanel();
			dep.setBorder(new EmptyBorder(0, 0, 500, 400));
			setContentPane(dep);
			dep.setLayout(null);
			dep.setBounds(30, 30, 800, 500);
			
			JLabel info = new JLabel("부서정보 입력");
			info.setHorizontalAlignment(SwingConstants.CENTER);
			info.setFont(info.getFont().deriveFont(25.0f));
			info.setBounds(0, 40, 200, 40);
			dep.add(info);
			
			JLabel alert = new JLabel("아래의 정보를 입력하여 주세요.");
			alert.setHorizontalAlignment(SwingConstants.CENTER);
			alert.setForeground(Color.RED);
			alert.setBounds(7, 68, 200, 40);
			dep.add(alert);
			
			// dep_ID Input
			JLabel id = new JLabel("부서ID");
			id.setHorizontalAlignment(SwingConstants.CENTER);
			id.setFont(info.getFont().deriveFont(13.0f));
			id.setBounds(140, 140, 70, 15);
			dep.add(id);
			
			depId = new JTextField("");
			depId.setBounds(230, 133, 230, 30);
			dep.add(depId);
			depId.setColumns(10);
			
			// dep_name Input
			JLabel name = new JLabel("부서이름");
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setFont(name.getFont().deriveFont(13.0f));
			name.setBounds(140, 190, 70, 15);
			dep.add(name);
			
			JLabel message = new JLabel("");
			message.setHorizontalAlignment(SwingConstants.CENTER);
			message.setFont(message.getFont().deriveFont(13.0f));
			message.setForeground(Color.RED);
			message.setBounds(480, 125, 200, 15);
			dep.add(message);
			
			depName = new JTextField("");
			depName.setBounds(230, 183, 230, 30);
			dep.add(depName);
			depName.setColumns(10);
			
			// dep_phone Input
			JLabel phone = new JLabel("부서번호");
			phone.setHorizontalAlignment(SwingConstants.CENTER);
			phone.setFont(info.getFont().deriveFont(13.0f));
			phone.setBounds(140, 240, 70, 15);
			dep.add(phone);
			
			depPhone = new JTextField("");
			depPhone.setBounds(230, 233, 230, 30);
			dep.add(depPhone);
			depPhone.setColumns(10);
			
			// dep_address Input
			JLabel addr = new JLabel("부서주소");
			addr.setHorizontalAlignment(SwingConstants.CENTER);
			addr.setFont(info.getFont().deriveFont(13.0f));
			addr.setBounds(140, 290, 70, 15);
			dep.add(addr);
			
			depAddress = new JTextField("");
			depAddress.setBounds(230, 283, 230, 30);
			dep.add(depAddress);
			depAddress.setColumns(10);
			
			// 센터정보
			JLabel c = new JLabel("센터");
			c.setBounds(160, 342, 47, 15);
			c.setHorizontalAlignment(SwingConstants.CENTER);
			dep.add(c);
			
			String[] centers = s.list_center();
			centerList = new JList<String>();
			centerList.setListData(centers);
			centerList.setSelectedIndex(0);
			
			JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			roll.setBounds(230, 335, 130, 80);
			dep.add(roll);
			
			// 정보 추가 버튼
			JButton Add = new JButton("정보추가");
			Add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// 값을 입력하지않았을 경우
						if(depId.getText().equals("")| depName.getText().equals("") | depPhone.getText().equals("") | depAddress.getText().equals("")) {
							msg = "값을 입력해주세요";
							message.setText(msg);
							dep.repaint();
						}
						else {
							getInfo(depId, depName, depPhone, depAddress);
							boolean flag = aD.isDuplicated(depID);
							
							// 중복이 존재할 경우
							if(flag == true) {
								msg = Integer.toString(depID) + "은 이미 존재하는 부서입니다.";
								message.setText(msg);	
								depId.setText("");
								dep.repaint();
							}else {
								String listTarget = centerList.getSelectedValue().toString();
								// dep 정보 입력
								aD.addDep(depID, depNAME, depPHONE, depADDR, Integer.parseInt(listTarget));
							}
						}
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			
			Add.setBounds(500, 155, 80, 30);
			dep.add(Add);
			
			// Reset 버튼
			JButton Reset = new JButton("초기화");
			Reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					depId.setText("");
					depName.setText("");
					depPhone.setText("");
					depAddress.setText("");
					//dep.repaint();
				}
			});
			
			Reset.setBounds(600, 155, 80, 30);
			dep.add(Reset);
			
			dep.setVisible(true);
			return dep;
		}
		
		// 부서 정보 가져오기
		public void getInfo(JTextField depid, JTextField depname, JTextField depphone, JTextField depaddr) {
			depID = Integer.parseInt(depid.getText());
			depNAME = depname.getText();
			depPHONE = depphone.getText();
			depADDR = depaddr.getText();
		}
}
