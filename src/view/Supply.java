package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*****
 * @author jaehoon
 *	  공급 관련 클래스
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import back.aboutEmployee;
import back.aboutSupply;
import dbproject.AdminMainShow;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Supply extends JFrame{
	private JPanel supply;
	private JList<String> centerList;
	private JList<String> depList;
	private JTable table;
	
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
		
		// DatePicker Import
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		JLabel info = new JLabel("납품정보 추가");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(info.getFont().deriveFont(25.0f));
		info.setBounds(0, 40, 200, 40);
		supply.add(info);
		
		// 센터정보
		JLabel c = new JLabel("납품목록");
		c.setBounds(70, 100, 47, 15);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		supply.add(c);
		
		String[] centers = aS.list();
		centerList = new JList<String>();
		centerList.setListData(centers);
		
		JScrollPane roll = new JScrollPane(centerList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roll.setBounds(110, 100, 100, 80);
		supply.add(roll);
		
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(contents, header) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		// table
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
		supply.add(scrollpane);
		
		centerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				
				String d = list.getSelectedValue().toString();
				
				if( d == null ) {
					// nothing
					myModel.fireTableDataChanged();
				}
				else {
					int cid = Integer.parseInt(d);
					contents = aE.getValue(cid);
					myModel.fireTableDataChanged();
					jTableRefresh();
				}
			}
		});
		
		supply.revalidate();
		supply.repaint();
		return supply;
	}
}
