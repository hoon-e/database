package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import back.aboutAS;
import back.aboutEmployee;
import back.aboutSupply;
import back.aboutAS;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AS extends JFrame{
	private JPanel as;
	private JTextField asId;
	private JTextField asPrice;
	private JTextField asWhy;
	
	private JList<String> empList;
	private JList<String> partList;
	private JTable table;

	public String[][] contents = null;
	public String[] header = {"ASID", "AS가격",  "AS이유", "AS날짜", "고객ID", "직원 ID", "부품ID"};
			
	private int ASID;
	private int ASCOST;
	private String ASWHY;
	
	public JPanel inputAS(int setID) {
		aboutSupply aS = new aboutSupply();
		aboutEmployee aE = new aboutEmployee();
		aboutAS a = new aboutAS();
		
		// AS 패널 생성
		as = new JPanel();
		as.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(as);
		as.setLayout(null);
		as.setBounds(30, 30, 800, 600);
		
		JLabel info = new JLabel("AS정보 추가");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		as.add(info);
		
		// 공급 업체 목록정보
		JLabel f = new JLabel("직원목록");
		f.setBounds(70, 100, 47, 15);
		f.setHorizontalAlignment(SwingConstants.CENTER);
		as.add(f);
		
		String[] emp = aS.emp_list();
		empList = new JList<String>();
		empList.setListData(emp);
		
		JScrollPane froll = new JScrollPane(empList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		froll.setBounds(130, 100, 100, 80);
		as.add(froll);
		
		// 납품 목록정보
		JLabel c = new JLabel("부품목록");
		c.setBounds(250, 100, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		as.add(c);
		
		String[] part = a.part_list();
		partList = new JList<String>();
		partList.setListData(part);
		
		JScrollPane roll = new JScrollPane(partList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(310, 100, 100, 80);
		as.add(roll);
			
		// AS정보_ID Input
		JLabel id = new JLabel("AS ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(120, 230, 70, 15);
		as.add(id);
		
		asId = new JTextField();
		asId.setBounds(200, 230, 230, 30);
		as.add(asId);
		asId.setColumns(10);
		
		// AS가격 정보 Input
		JLabel price = new JLabel("AS 가격");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setBounds(120, 280, 70, 15);
		as.add(price);
		
		asPrice = new JTextField();
		asPrice.setBounds(200, 280, 230, 30);
		as.add(asPrice);
		asPrice.setColumns(10);
				
		// DatePicker Import
		JLabel date = new JLabel("AS일자");
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setBounds(120, 325, 70, 15);
		
		as.add(date);
		
		SqlDateModel model = new SqlDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		datePicker.setBounds(200, 320, 150, 50);
		as.add(datePicker);
		
		// AS 이유정보 Input
		JLabel why = new JLabel("AS 이유");
		why.setHorizontalAlignment(SwingConstants.CENTER);
		why.setBounds(120, 360, 70, 15);
		as.add(why);
		
		asWhy = new JTextField();
		asWhy.setBounds(200, 360, 230, 50);
		as.add(asWhy);
		asPrice.setColumns(10);
		
		// 정보 수정 버튼
		JButton Add = new JButton("정보추가");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						getInfo(asId, asPrice, asWhy);
						
						// listTarget 정보 가져오기
						String listTarget = empList.getSelectedValue().toString();
						String listTarget2 = partList.getSelectedValue().toString();
						Date dateshow = (Date)datePicker.getModel().getValue();
						
						// as 정보 추가
						a.addAS(ASID, ASCOST, ASWHY, dateshow, setID, Integer.parseInt(listTarget), Integer.parseInt(listTarget2));
						
						int result = JOptionPane.showConfirmDialog(as, "입력되었습니다.", "입력확인", JOptionPane.YES_OPTION );
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Add.setBounds(200, 420, 100, 30);
		as.add(Add);
		
		as.revalidate();
		as.repaint();
		return as;
	}
	
	public JPanel showAS() {
		aboutAS aS = new aboutAS();

		as = new JPanel();
		as.setBorder(new EmptyBorder(0, 0, 500, 400));
		setContentPane(as);
		as.setLayout(null);
		as.setBounds(30, 30, 800, 500);
		
		JLabel info = new JLabel("AS정보 보기");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		as.add(info);
		
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
		as.add(scrollpane);
		
		return as;
	}
	
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
	
	public void getInfo(JTextField asid, JTextField ascost, JTextField aswhy) {
		ASID = Integer.parseInt(asid.getText());
		ASCOST = Integer.parseInt(ascost.getText());
		ASWHY = aswhy.getText();
	}
}
