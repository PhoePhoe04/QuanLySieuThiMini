package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class HoaDonTimKiemNC extends JDialog {
	private JTextField txtMaHD;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public HoaDonTimKiemNC() {
		init();
	}
	
	/*
	 * CREATE DIALOG
	 */
	
	private void init() {
		setSize(700,500);
		getContentPane().setLayout(null);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD.setBounds(10, 10, 150, 30);
		getContentPane().add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(170, 10, 150, 30);
		getContentPane().add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblMaHD_1 = new JLabel("Mã hóa đơn");
		lblMaHD_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_1.setBounds(10, 50, 150, 30);
		getContentPane().add(lblMaHD_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(170, 50, 150, 30);
		getContentPane().add(textField);
		
		JLabel lblMaHD_2 = new JLabel("Mã hóa đơn");
		lblMaHD_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_2.setBounds(10, 90, 150, 30);
		getContentPane().add(lblMaHD_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 90, 150, 30);
		getContentPane().add(textField_1);
		
		JLabel lblMaHD_3 = new JLabel("Mã hóa đơn");
		lblMaHD_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_3.setBounds(10, 130, 150, 30);
		getContentPane().add(lblMaHD_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 130, 150, 30);
		getContentPane().add(textField_2);
		
		JLabel lblMaHD_4 = new JLabel("Mã hóa đơn");
		lblMaHD_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_4.setBounds(10, 170, 150, 30);
		getContentPane().add(lblMaHD_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(170, 170, 150, 30);
		getContentPane().add(textField_3);
		
		JLabel lblMaHD_5 = new JLabel("Mã hóa đơn");
		lblMaHD_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_5.setBounds(10, 210, 150, 30);
		getContentPane().add(lblMaHD_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(170, 210, 150, 30);
		getContentPane().add(textField_4);
	}
}
