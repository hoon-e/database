package view;

import back.SignUpCheck;
import back.aboutDepartment;
import back.aboutEmployee;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Employee extends JFrame{
	private JPanel emp;
	private JTextField empId;
	private JTextField empName;
	private JTextField empPhone;
	private JTextField empAddress;
	private JTextField empAge;
	private JList<String> centerList;
	private JList<String> depList;
	public JTable table;
	
	private int empID = 0;
	private String empNAME = "";
	private String empPHONE = "";
	private String empADDR = "";
	private int empAGE = 0;
	public String msg = "";
	public String[][] contents = null;
	public String[] header = null;
	
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
 
	// 부서 정보 보기
	public JPanel showEmp() {
		aboutEmployee aE = new aboutEmployee();
		
		emp = new JPanel();
		emp.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(emp);
		emp.setLayout(null);
		emp.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("직원정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		emp.add(info);
		
		centerList = new JList<String>();
		depList = new JList<String>();
		
		String[] centerlist = aE.list_center();
		centerList.setListData(centerlist);
		// 동적으로 부서 정보 생성
		centerList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        int cid = Integer.parseInt(list.getSelectedValue().toString());
		        String[] depart = aE.list_dep(cid);
		        
		        depList.setListData(depart);
							emp.revalidate();
							emp.repaint();
		        }
		    }
		  );
		centerList.setBounds(100,100, 100, 80);
		emp.add(centerList);
		
		depList.setBounds(300, 100, 100, 80);
		emp.add(depList);
		
		header = new String[] {"직원ID","직원이름", "직원번호", "직원주소", "직원나이"};

		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		table = new JTable(contents, header);
		table.setModel(tableModel);
		DefaultTableModel myModel = (DefaultTableModel)table.getModel();

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(30, 200, 600, 200);
		
		emp.add(scrollpane);
		
		depList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				
				if( list.getSelectedValue() == null ) {
					// nothing
				}
				else {
					int did = Integer.parseInt(list.getSelectedValue().toString());
					contents = aE.getValue(did);
					
					if( contents == null ) {
						// nothing
						myModel.fireTableDataChanged();
						jTableRefresh();
					}
					else {
						myModel.fireTableDataChanged();
						jTableRefresh();
					}
				}
			}
		});
		
		emp.revalidate();
		emp.repaint();
		return emp;
	}
		
	// Emp 정보 삽입
	public JPanel inputEmp() {
		aboutEmployee aE = new aboutEmployee();
		
		emp = new JPanel();
		emp.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(emp);
		emp.setLayout(null);
		emp.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("직원정보 입력");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		emp.add(info);
		
		JLabel alert = new JLabel("아래의 정보를 입력하여 주세요.");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setForeground(Color.RED);
		alert.setBounds(7, 68, 200, 40);
		emp.add(alert);
		
		// emp_ID Input
		JLabel id = new JLabel("직원ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(info.getFont().deriveFont(13.0f));
		id.setBounds(140, 140, 70, 15);
		emp.add(id);
		
		empId = new JTextField("");
		empId.setBounds(230, 133, 230, 30);
		emp.add(empId);
		empId.setColumns(10);
		
		// emp_name Input
		JLabel name = new JLabel("직원이름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(name.getFont().deriveFont(13.0f));
		name.setBounds(140, 190, 70, 15);
		emp.add(name);
		
		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(message.getFont().deriveFont(13.0f));
		message.setForeground(Color.RED);
		message.setBounds(480, 125, 200, 15);
		emp.add(message);
		
		empName = new JTextField("");
		empName.setBounds(230, 183, 230, 30);
		emp.add(empName);
		empName.setColumns(10);
		
		// emp_phone Input
		JLabel age = new JLabel("직원나이");
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setFont(info.getFont().deriveFont(13.0f));
		age.setBounds(140, 240, 70, 15);
		emp.add(age);
		
		empAge = new JTextField("");
		empAge.setBounds(230, 233, 230, 30);
		emp.add(empAge);
		empAge.setColumns(10);

		// emp_phone Input
		JLabel phone = new JLabel("직원번호");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(info.getFont().deriveFont(13.0f));
		phone.setBounds(140, 290, 70, 15);
		emp.add(phone);
		
		empPhone = new JTextField("");
		empPhone.setBounds(230, 283, 230, 30);
		emp.add(empPhone);
		empPhone.setColumns(10);
		
		// emp_address Input
		JLabel addr = new JLabel("직원주소");
		addr.setHorizontalAlignment(SwingConstants.CENTER);
		addr.setFont(info.getFont().deriveFont(13.0f));
		addr.setBounds(140, 340, 70, 15);
		emp.add(addr);
		
		empAddress = new JTextField("");
		empAddress.setBounds(230, 333, 230, 30);
		emp.add(empAddress);
		empAddress.setColumns(10);
		
		// 센터 정보
		JLabel c = new JLabel("센터");
		c.setBounds(160, 390, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		emp.add(c);
		
		// 센터 
		String[] centers = aE.list_center();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		depList = new JList<String>();
		
		// 동적으로 부서 정보 생성
		centerList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        int cid = Integer.parseInt(list.getSelectedValue().toString());
		        String[] depart = aE.list_dep(cid);
		        
		        depList.setListData(depart);
							emp.revalidate();
							emp.repaint();
		        }
		    }
		  );
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(210, 385, 90, 80);
		emp.add(roll);
		
		// 부서 정보
		JLabel d = new JLabel("부서");
		d.setBounds(320, 390, 47, 15);
		d.setHorizontalAlignment(SwingConstants.CENTER);
		emp.add(d);
		
		// 부서 정보 scrollpane
		JScrollPane rolld = new JScrollPane(depList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rolld.setBounds(370, 385, 90, 80);
		emp.add(rolld);
		
		// 정보 추가 버튼
		JButton Add = new JButton("정보추가");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 값을 입력하지 않았을 경우
					if(empId.getText().equals("")| empName.getText().equals("") | empPhone.getText().equals("") | empAddress.getText().equals("")) {
						msg = "값을 입력해주세요";
						message.setText(msg);
						emp.revalidate();
						emp.repaint();
					}
					else {
						getInfo(empId, empName, empPhone, empAge, empAddress);
						boolean flag = aE.isDuplicated(empID);
						
						// 중복이 존재할 경우
						if(flag == true) {
							msg = Integer.toString(empID) + "은 이미 존재하는 직원입니다.";
							message.setText(msg);
							empId.setText("");
							emp.repaint();
						}else {
							String listTarget = depList.getSelectedValue().toString();
							// emp 정보 입력
							aE.addEmp(empID, empNAME, empPHONE, empADDR, empAGE, Integer.parseInt(listTarget));
						}
					}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Add.setBounds(500, 155, 80, 30);
		emp.add(Add);
		
		// Reset 버튼
		JButton Reset = new JButton("초기화");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empId.setText("");
				empName.setText("");
				empPhone.setText("");
				empAddress.setText("");
				//emp.repaint();
			}
		});
		
		Reset.setBounds(600, 155, 80, 30);
		emp.add(Reset);
		
		emp.revalidate();
		emp.repaint();
		return emp;
	}
	
	// 직원 정보 가져오기
	public void getInfo(JTextField empid, JTextField empname, JTextField empphone, JTextField empage, JTextField empaddr) {
		empID = Integer.parseInt(empid.getText());
		empNAME = empname.getText();
		empPHONE = empphone.getText();
		empADDR = empaddr.getText();
		empAGE = Integer.parseInt(empage.getText());
	}
}
