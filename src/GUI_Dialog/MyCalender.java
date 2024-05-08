package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class MyCalender extends JDialog{
	
	public MyCalender(Component parentComponent,JTextField txt) {
		init(parentComponent,txt);
	}

	private void init(Component parentComponent,JTextField txt) {
		JCalendar calendar = new JCalendar();
        setModal(true);
        // Tạo các nút OK và Cancel
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = calendar.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(selectedDate);
                txt.setText(formattedDate);
                dispose();
            }
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng cửa sổ lịch chọn ngày nếu bấm Cancel
            }
        });
        
        // Panel chứa các nút OK và Cancel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(calendar, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(parentComponent);
        setVisible(true);
	}
}
