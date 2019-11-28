package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/*****
 * @author jaehoon
 *	  공급 관련 클래스
 */
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

import back.aboutSupply;
import back.aboutEmployee;
import back.aboutSupply;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Supply extends JFrame{
	private JPanel supply;
	private JList<String> supplyList;
	private JList<String> partList;
	private JList<String> empList;
	private JTable table;
	
	private JTextField suppId;
	private JTextField suppPrice;
	
	private int MS_ID = 0;
	private int MS_PRICE = 0;
	
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
	
	public JPanel inputSupply() {
		aboutSupply aS = new aboutSupply();
		aboutEmployee aE = new aboutEmployee();
		
		supply = new JPanel();
		supply.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(supply);
		supply.setLayout(null);
		supply.setBounds(30, 30, 800, 600);
		
		JLabel info = new JLabel("납품정보 추가");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		supply.add(info);
		
		// 공급 업체 목록정보
		JLabel f = new JLabel("업체목록");
		f.setBounds(70, 100, 47, 15);
		f.setHorizontalAlignment(SwingConstants.CENTER);
		supply.add(f);
		
		String[] factories = aS.fac_list();
		supplyList = new JList<String>();
		supplyList.setListData(factories);
		
		JScrollPane froll = new JScrollPane(supplyList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		froll.setBounds(130, 100, 100, 80);
		supply.add(froll);
		
		// 납품 목록정보
		JLabel c = new JLabel("납품목록");
		c.setBounds(250, 100, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		supply.add(c);
		
		String[] supplys = aS.list();
		partList = new JList<String>();
		partList.setListData(supplys);
		
		JScrollPane roll = new JScrollPane(partList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(310, 100, 100, 80);
		supply.add(roll);
		
		// 직원 목록
		JLabel e = new JLabel("직원목록");
		e.setBounds(430, 100, 47, 15);
		e.setHorizontalAlignment(SwingConstants.CENTER);
		supply.add(e);
		
		String[] employees = aS.emp_list();
		empList = new JList<String>();
		empList.setListData(employees);
		
		JScrollPane eroll = new JScrollPane(empList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		eroll.setBounds(490, 100, 100, 80);
		supply.add(eroll);
		
		// 납품정보_ID Input
		JLabel id = new JLabel("납품ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(120, 230, 70, 15);
		supply.add(id);
		
		suppId = new JTextField();
		suppId.setBounds(200, 230, 230, 30);
		supply.add(suppId);
		suppId.setColumns(10);
		
		// 납품가격 정보 Input
		JLabel price = new JLabel("납품가");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setBounds(120, 280, 70, 15);
		supply.add(price);
		
		suppPrice = new JTextField();
		suppPrice.setBounds(200, 280, 230, 30);
		supply.add(suppPrice);
		suppPrice.setColumns(10);
				
		// DatePicker Import
		
		JLabel date = new JLabel("남품일자");
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setBounds(120, 329, 70, 15);
		
		supply.add(date);
		
		SqlDateModel model = new SqlDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		datePicker.setBounds(200, 330, 150, 50);
		supply.add(datePicker);
		
		// 정보 수정 버튼
		JButton Add = new JButton("정보추가");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						getInfo(suppId, suppPrice);
						
						// listTarget 정보 가져오기
						String listTarget = supplyList.getSelectedValue().toString();
						String listTarget2 = partList.getSelectedValue().toString();
						String listTarget3 = empList.getSelectedValue().toString();
						Date dateshow = (Date)datePicker.getModel().getValue();
						
						// Supply 정보 추가
						aS.addSupply(MS_ID, MS_PRICE, dateshow, Integer.parseInt(listTarget2), Integer.parseInt(listTarget), Integer.parseInt(listTarget3));
						
						int result = JOptionPane.showConfirmDialog(supply, "수정되었습니다.", "수정확인", JOptionPane.YES_OPTION );
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Add.setBounds(200, 400, 100, 30);
		supply.add(Add);
		
		supply.revalidate();
		supply.repaint();
		return supply;
	}
	
	public JPanel showMS() {
		aboutSupply aS = new aboutSupply();

		supply = new JPanel();
		supply.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(supply);
		supply.setLayout(null);
		supply.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("납품정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		supply.add(info);
		
		String[] header = {"납품ID", "납품가격",  "납품일자",  "리스트ID", "공급업체ID", "직원ID"};
		String[][] contents = aS.getValue();
		
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
		scrollpane.setBounds(90, 100, 550, 400);
		supply.add(scrollpane);
		
		return supply;
	}
	
	public void getInfo(JTextField msid, JTextField sprice) {
		MS_ID = Integer.parseInt(msid.getText());
		MS_PRICE = Integer.parseInt(sprice.getText());
	}
}
