package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.FlowLayout;

public class DangNhap extends JFrame {
	

	
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField pfPassword;
	
	public DangNhap() throws SQLException{
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void init() {
//			JFrame
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
//			Components
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTaiKhoan.setBounds(400, 120, 250, 30);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pfPassword.setBounds(400, 180, 250, 30);
		contentPane.add(pfPassword);
		pfPassword.setColumns(10);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDangNhap.setBounds(400, 230, 120, 50);
		contentPane.add(btnDangNhap);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThoat.setBounds(570, 230, 80, 50);
		contentPane.add(btnThoat);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 30, 685, 50);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ SIÊU THỊ MINI");
		lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDangNhap.setPreferredSize(new Dimension(500, 30));
		panel.add(lblDangNhap);
		
	}
	
//	 Hàm main để test
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new DangNhap();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
