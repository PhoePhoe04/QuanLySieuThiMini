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
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.FlowLayout;

public class DangNhap extends JFrame {
	
	private TaiKhoanDTO taiKhoan;
	private TaiKhoanBUS taiKhoanBUS;
	
	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField pfPassword;
	
	public DangNhap() throws SQLException{
		this.taiKhoanBUS = new TaiKhoanBUS();
		this.taiKhoan = new TaiKhoanDTO();
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
		
		JLabel lblTaiKhoan = new JLabel("Tài Khoản");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTaiKhoan.setBounds(300, 120, 100, 30);
		contentPane.add(lblTaiKhoan);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhau.setBounds(300, 180, 100, 30);
		contentPane.add(lblMatKhau);
		
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
		btnDangNhap.addActionListener( e -> { dangNhapListener(); });
		contentPane.add(btnDangNhap);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThoat.setBounds(570, 230, 80, 50);
		btnThoat.addActionListener( e -> { thoatListener(); });
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
	
//	 Các hàm xử lý ActionListener	
	
	private void thoatListener() {
		try {
			this.dispose();
			System.exit(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void dangNhapListener() {
		this.taiKhoan.setTenDangNhap(this.txtTaiKhoan.getText());
		this.taiKhoan.setMatKhau(this.pfPassword.getText());

		try {
			if(taiKhoanBUS.checkLogin(taiKhoan.getTenDangNhap(), taiKhoan.getMatKhau())) {
				JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
				System.out.println("Đăng nhập thành công");
				
				
			}
			else {
				JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
				System.out.println("Tài khoản hoặc mật khẩu không chính xác");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	 Hàm main để test
	
	public static void main(String[] args) {
		try {
			new DangNhap();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
