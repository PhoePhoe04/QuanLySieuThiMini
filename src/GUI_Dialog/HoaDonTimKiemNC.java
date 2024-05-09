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
import javax.swing.JRadioButton;

public class HoaDonTimKiemNC extends JDialog {
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTextField txtMaKM;
	private JTextField txtNgayLap;
	private JTextField txtTongTien;

	public HoaDonTimKiemNC() {
		init();
		addActionListener();
	}

	/*
	 * CREATE DIALOG
	 */
	
	private void init() {
		setSize(600,450);
		getContentPane().setLayout(null);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD.setBounds(20, 70, 150, 30);
		getContentPane().add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(170, 70, 150, 30);
		getContentPane().add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(20, 270, 150, 30);
		getContentPane().add(lblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(170, 120, 150, 30);
		getContentPane().add(txtMaKH);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(20, 120, 150, 30);
		getContentPane().add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(170, 170, 150, 30);
		getContentPane().add(txtMaNV);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKM.setBounds(20, 170, 150, 30);
		getContentPane().add(lblMaKM);
		
		txtMaKM = new JTextField();
		txtMaKM.setColumns(10);
		txtMaKM.setBounds(170, 220, 150, 30);
		getContentPane().add(txtMaKM);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayLap.setBounds(20, 220, 150, 30);
		getContentPane().add(lblNgayLap);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setColumns(10);
		txtNgayLap.setBounds(170, 270, 150, 30);
		getContentPane().add(txtNgayLap);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(20, 320, 150, 30);
		getContentPane().add(lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(170, 320, 150, 30);
		getContentPane().add(txtTongTien);
		
		JRadioButton rdbtnSimple = new JRadioButton("Simple");
		rdbtnSimple.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnSimple.setBounds(20, 20, 100, 30);
		getContentPane().add(rdbtnSimple);
		
		JRadioButton rdbtnMerge = new JRadioButton("Merge");
		rdbtnMerge.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnMerge.setBounds(125, 20, 100, 30);
		getContentPane().add(rdbtnMerge);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBounds(496, 373, 80, 30);
		getContentPane().add(btnHuy);
		
		JButton btnTra = new JButton("Tra");
		btnTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTra.setBounds(406, 373, 80, 30);
		getContentPane().add(btnTra);
	}
	
	private void addActionListener() {
		
	}

}
