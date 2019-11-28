package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import back.SignUpCheck;
import back.aboutCustomer;
import back.aboutDepartment;
import dbproject.AdminMainShow;

public class Customer extends JFrame{
	private JPanel customer;
	private JTextField customerId;
	private JTextField customerName;
	private JTextField customerAge;
	private JTextField customerGender;
	private JList<String> centerList;
	private JTextField passwd;
	private JTable table;
	
	private int customerID = 0;
	private String customerNAME = "";
	private int customerAGE = 0;
	private String customerGENDER = "";
	private String centerID;
	private String PW;
	
	public int cid = 0;
	public String msg = "";
	public String[][] contents = null;
	public String[] header = {"고객ID","고객이름", "고객나이", "고객성별"};
	
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
	 
	public JPanel editCustomer() {
		aboutCustomer aC = new aboutCustomer();
		
		customer = new JPanel();
		customer.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(customer);
		customer.setLayout(null);
		customer.setBounds(30, 30, 800, 600);
		
		// 고객 정보 수정
		JLabel info = new JLabel("고객정보 수정");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		customer.add(info);
		
		// 센터정보
		JLabel c = new JLabel("센터");
		c.setBounds(60, 100, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		customer.add(c);
		
		String[] centers = aC.list_center();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(110, 100, 100, 80);
		customer.add(roll);
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		table = new JTable(contents, header);
		table.setModel(tableModel);
		// table = new JTable(contents, header);
		DefaultTableModel myModel = (DefaultTableModel)table.getModel();
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				String selectedData = null;
				int row = table.getSelectedRow();
				selectedData = (String)table.getValueAt(row, 0);
				
				customerId.setText(selectedData);
			}
		});
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(250, 100, 400, 300);
		customer.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				
				String d = list.getSelectedValue().toString();
				
				if( d == null ) {
					// nothing
					myModel.fireTableDataChanged();
				}
				else {
					cid = Integer.parseInt(d);
					contents = aC.getValue(cid);
					myModel.fireTableDataChanged();
					jTableRefresh();
				}
			}
		});
		
		// emp_ID Input
		JLabel id = new JLabel("수정할 직원ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(info.getFont().deriveFont(13.0f));
		id.setForeground(Color.red);
		id.setBounds(250, 440, 100, 15);
		customer.add(id);
		
		customerId = new JTextField("");
		customerId.setBounds(360, 430, 150, 30);
		customer.add(customerId);
		customerId.setColumns(10);
		
		// 정보 수정 버튼
		JButton Edit = new JButton("정보수정");
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cust_id = Integer.parseInt(customerId.getText());
				
				int result = JOptionPane.showConfirmDialog(customer, "수정하시겠습니까?","수정 확인", JOptionPane.YES_NO_OPTION);
				
				if( result == JOptionPane.YES_OPTION) {
					if(AdminMainShow.subPanel.isEnabled()) {
						AdminMainShow.mainPanel.remove(AdminMainShow.subPanel);
					}
					AdminMainShow.subPanel = editPanel(cust_id);
					AdminMainShow.mainPanel.add(AdminMainShow.subPanel);
					AdminMainShow.mainFrame.repaint();
				}
			}
		});
		
		Edit.setBounds(520, 430, 80, 30);
		customer.add(Edit);
		
		customer.revalidate();
		customer.repaint();
		return customer;			
}

	// 부서 정보 수정
	public JPanel editPanel(int cust_id) {
		aboutCustomer aC = new aboutCustomer();
		String[] value = aC.getiDValue(cust_id);
		
		customer = new JPanel();
		customer.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(customer);
		customer.setLayout(null);
		customer.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("고객정보 수정");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		customer.add(info);
		
		JLabel alert = new JLabel("아래의 정보를 수정하여 주세요.");
		alert.setHorizontalAlignment(SwingConstants.CENTER);
		alert.setForeground(Color.RED);
		alert.setBounds(7, 68, 200, 40);
		customer.add(alert);
		
		// customer_ID Input
		JLabel id = new JLabel("고객ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(info.getFont().deriveFont(13.0f));
		id.setBounds(140, 140, 70, 15);
		customer.add(id);
		
		customerId = new JTextField(value[0]);
		customerId.setBounds(230, 133, 230, 30);
		customer.add(customerId);
		customerId.setColumns(10);
		
		// customer_name Input
		JLabel name = new JLabel("고객이름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(name.getFont().deriveFont(13.0f));
		name.setBounds(140, 190, 70, 15);
		customer.add(name);
		
		JLabel message = new JLabel("");
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(message.getFont().deriveFont(13.0f));
		message.setForeground(Color.RED);
		message.setBounds(480, 125, 200, 15);
		customer.add(message);
		
		customerName = new JTextField(value[1]);
		customerName.setBounds(230, 183, 230, 30);
		customer.add(customerName);
		customerName.setColumns(10);
		
		// customer_age Input
		JLabel age = new JLabel("고객나이");
		age.setHorizontalAlignment(SwingConstants.CENTER);
		age.setFont(info.getFont().deriveFont(13.0f));
		age.setBounds(140, 240, 70, 15);
		customer.add(age);
		
		customerAge = new JTextField(value[2]);
		customerAge.setBounds(230, 233, 230, 30);
		customer.add(customerAge);
		customerAge.setColumns(10);
		
		// customer_gender
		JLabel g = new JLabel("고객성별");
		
		ButtonGroup gender = new ButtonGroup();
		JRadioButton male = new JRadioButton("남");
		JRadioButton female = new JRadioButton("여");
		
		if(value[3].equals("남")) {
			male.setSelected(true);
			female.setSelected(false);
		}
		else {
			female.setSelected(true);
			male.setSelected(false);
		}
			
		g.setBounds(140, 280, 57, 15);
		g.setHorizontalAlignment(SwingConstants.CENTER);
		g.setFont(info.getFont().deriveFont(13.0f));
		gender.add(male);
		gender.add(female);
		male.setBounds(230, 270, 50, 30);
		female.setBounds(270, 270, 100, 30);
		
		customer.add(g);
		customer.add(male);
		customer.add(female);
		
		// customer_password Input
		JLabel pw = new JLabel("고객비밀번호");
		pw.setHorizontalAlignment(SwingConstants.CENTER);
		pw.setFont(info.getFont().deriveFont(13.0f));
		pw.setBounds(130, 320, 80, 15);
		customer.add(pw);
		
		passwd = new JTextField(value[5]);
		passwd.setBounds(230, 315, 230, 30);
		customer.add(passwd);
		passwd.setColumns(10);
		
		// 센터정보
		JLabel c = new JLabel("센터");
		c.setBounds(160, 365, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		customer.add(c);
		
		String[] centers = aC.list_center();
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
		roll.setBounds(230, 370, 130, 80);
		customer.add(roll);
	
		int cid = Integer.parseInt(value[0]);

		// 정보 수정 버튼
		JButton Add = new JButton("정보수정");
		Add.addActionListener(new ActionListener() {
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
												
						listtarget = centerList.getSelectedValue().toString();
						getInfo(customerId, passwd, customerName, customerAge, target, listtarget);
						
						aC.updateCustomer(customerID, PW, customerNAME, customerAGE, customerGENDER, Integer.parseInt(centerID), cid);
						
						int result = JOptionPane.showConfirmDialog(customer, "수정되었습니다.", "수정확인", JOptionPane.YES_OPTION );
					}
			}
		);
		
		Add.setBounds(500, 155, 80, 30);
		customer.add(Add);
		
		// Reset 버튼
		JButton Reset = new JButton("초기화");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerId.setText("");
				customerName.setText("");
				customerAge.setText("");
			}
		});
		
		Reset.setBounds(600, 155, 80, 30);
		customer.add(Reset);

		customer.revalidate();
		customer.repaint();
		
		return customer;
	}

	public JPanel showCustomer() {
		aboutCustomer aC = new aboutCustomer();

		customer = new JPanel();
		customer.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(customer);
		customer.setLayout(null);
		customer.setBounds(30, 30, 800, 600);
		
		JLabel info = new JLabel("고객정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		customer.add(info);
		
		// 센터정보
		JLabel c = new JLabel("센터");
		c.setBounds(70, 100, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		customer.add(c);
		
		String[] centers = aC.list_center();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(110, 100, 100, 80);
		customer.add(roll);
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		table = new JTable(contents, header);
		table.setModel(tableModel);
		
		// tableModel 가져오기
		DefaultTableModel myModel = (DefaultTableModel)table.getModel();
		
		// 바뀐 값 있다면 초기화 시켜줌
		myModel.fireTableDataChanged();
		jTableRefresh();
		
		// table = new JTable(contents, header);
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(100, 200, 500, 300);
		customer.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				
				String d = list.getSelectedValue().toString();
				
				if( d == null ) {
					// nothing
					myModel.fireTableDataChanged();
				}
				else {
					cid = Integer.parseInt(d);
					contents = aC.getValue(cid);
					myModel.fireTableDataChanged();
					jTableRefresh();
				}
			}
		});
		
		customer.revalidate();
		customer.repaint();
		return customer;
	}
	
	private void getInfo(JTextField customerId, JTextField passwd, JTextField customerName,JTextField customerAge, String gender, String center) {
		customerID = Integer.parseInt(customerId.getText());
		PW = passwd.getText();
		customerNAME = customerName.getText();
		customerAGE = Integer.parseInt(customerAge.getText());
		customerGENDER = gender;
		centerID = center;
	}
}
