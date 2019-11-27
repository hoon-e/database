package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import back.aboutCenter;
import back.aboutCustomer;

public class Customer extends JFrame{
	private JPanel customer;
	private JTextField customerId;
	private JTextField customerName;
	private JTextField customerAge;
	private JTextField customerGender;
	private JList<String> centerList;
	private JTextField passwd;
	
	private int customerID = 0;
	private String customerNAME = "";
	private String customerAGE = "";
	private String customerGENDER = "";
	private String centerID;
	private String PW;
	
	public int cid = 0;
	public String msg = "";
	public String[][] contents = null;
	
	public JPanel showCustomer() {
		aboutCustomer aC = new aboutCustomer();

		customer = new JPanel();
		customer.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(customer);
		customer.setLayout(null);
		customer.setBounds(30, 30, 800, 500);
		
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
		
		String[] header = {"고객ID","고객이름", "고객나이", "고객성별"};
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		JTable table = new JTable(contents, header);
		table.setModel(tableModel);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(70, 200, 500, 300);
		customer.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				
				String d = list.getSelectedValue().toString();
				
				if( d == null ) {
					// nothing
				}
				else {
					cid = Integer.parseInt(d);
					contents = aC.getValue(cid);
					model.fireTableDataChanged();
					customer.revalidate();
					customer.repaint();
				}
			}
		});

		return customer;
	}
}
