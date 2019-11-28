package view;

import javax.swing.*;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AS extends JFrame{
	private JPanel AS;
	private JTextField asId;
	private JTextField asCost;
	private JTextField asWhy;
	
	public AS() {
		
		// DatePicker Import
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		 	
	}
}
