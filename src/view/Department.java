package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JTextField depEdit; 
	private JTable table;
	
	private int depID = 0;
	private String depNAME = "";
	private String depPHONE = "";
	private String depADDR = "";
	public String msg = "";
	public String[][] contents = null;
	public String[] header = null;
	
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
		
		JLabel cen = new JLabel("센터선택");
		cen.setHorizontalAlignment(SwingConstants.CENTER);
		cen.setFont(cen.getFont().deriveFont(13.0f));
		cen.setBounds(80,125, 50, 30);
		
		dep.add(cen);
		
		centerList = new JList<String>();
		// 센터 리스트
		String[] centerlist = aD.list_center();
		centerList.setListData(centerlist);
		
		JScrollPane scroll = new JScrollPane(centerList);
		
		header = new String[]{"부서ID","부서이름", "부서번호", "부서주소", "센터ID"};
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		scroll.setBounds(80, 160, 100, 80);
		dep.add(scroll);
		
		table = new JTable(null, header);
		table.setModel(tableModel);
		JScrollPane scrollpane = new JScrollPane(table);
		DefaultTableModel myModel = (DefaultTableModel)table.getModel();

		scrollpane.setBounds(220, 150, 400, 300);
		dep.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				int cid = Integer.parseInt(list.getSelectedValue().toString());
				
				contents = aD.getValue(cid);
				
				if( contents == null ) {
					myModel.fireTableDataChanged();
					jTableRefresh();
				}else {
					myModel.fireTableDataChanged();
					jTableRefresh();
				}
			}
		});
		
		myModel.fireTableDataChanged();
		jTableRefresh();
		
		dep.revalidate();
		dep.repaint();
		
		return dep;
	}
	
	// table refresh
 public void jTableRefresh(){
	 @SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		table.setModel(tableModel);  
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
		
		JLabel cen = new JLabel("센터선택");
		cen.setHorizontalAlignment(SwingConstants.CENTER);
		cen.setFont(cen.getFont().deriveFont(13.0f));
		cen.setBounds(80,125, 50, 30);
		
		dep.add(cen);
		
		depEdit = new JTextField();
		depEdit.setBounds(320, 110, 200, 30);
		depEdit.setColumns(10);
		dep.add(depEdit);
		
		// 수정하기
		JButton accept = new JButton("수정하기");
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					String value = depEdit.getText();
					
					if(value == null) {
						// nothing
					}
					else {
						int id = Integer.parseInt(value);
						int result = JOptionPane.showConfirmDialog(dep, "수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION);
						
						// YES를 누르면 panel editPanel로 교체
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
		});
		accept.setBounds(530, 110, 80, 30);
		dep.add(accept);
		
		centerList = new JList<String>();
		// 센터 리스트
		String[] centerlist = aD.list_center();
		centerList.setListData(centerlist);
		
		JScrollPane scroll = new JScrollPane(centerList);
		
		header = new String[]{"부서ID","부서이름", "부서번호", "부서주소", "센터ID"};
		
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		scroll.setBounds(80, 160, 100, 80);
		dep.add(scroll);
		
		table = new JTable(null, header);
		table.setModel(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				String selectedData = null;
				int row = table.getSelectedRow();
				selectedData = (String)table.getValueAt(row, 0);
				
				depEdit.setText(selectedData);
			}
		});
		
		JScrollPane scrollpane = new JScrollPane(table);
		DefaultTableModel myModel = (DefaultTableModel)table.getModel();

		scrollpane.setBounds(220, 150, 400, 300);
		dep.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				int cid = Integer.parseInt(list.getSelectedValue().toString());
				
				contents = aD.getValue(cid);
				
				if( contents == null ) {
					myModel.fireTableDataChanged();
					jTableRefresh();
				}else {
					myModel.fireTableDataChanged();
					jTableRefresh();
				}
			}
		});
		
		myModel.fireTableDataChanged();
		jTableRefresh();
		
		dep.revalidate();
		dep.repaint();
		
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
						dep.revalidate();
						dep.repaint();
					}
					else {
						getInfo(depId, depName, depPhone, depAddress);
						
						// listTarget 정보 가져오기
						String listTarget = centerList.getSelectedValue().toString();
						
						// dep 정보 수정
						aD.editDep(depID, depNAME, depPHONE, depADDR, Integer.parseInt(listTarget), did);
						int result = JOptionPane.showConfirmDialog(dep, "수정되었습니다.", "수정확인", JOptionPane.YES_OPTION );
						
						// YES일 경우 현재의 화면을 교체합니다.				
						if( result == JOptionPane.YES_OPTION) {
							if(AdminMainShow.subPanel.isEnabled()) {
								AdminMainShow.mainPanel.remove(AdminMainShow.subPanel);
							}
							AdminMainShow.subPanel = showDep();
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
		
		dep.revalidate();
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
								dep.revalidate();
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
			
			dep.revalidate();
			dep.repaint();
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
