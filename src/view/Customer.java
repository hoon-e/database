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

import back.aboutCustomer;

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
	private String customerAGE = "";
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
				
				if( result == JOptionPane.YES_OPTION);
			}
		});
		
		Edit.setBounds(520, 430, 80, 30);
		customer.add(Edit);
		
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
		c.setBounds(80, 90, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		customer.add(c);
		
		String[] centers = aC.list_center();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(110, 90, 100, 80);
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
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(70, 200, 600, 300);
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
}
