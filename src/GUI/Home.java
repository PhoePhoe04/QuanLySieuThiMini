package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import GUI_Panel.HoaDonPanel;
import GUI_Panel.KhachHangPanel;
import GUI_Panel.LoaiSanPhamPanel;
import GUI_Panel.NhaCungCapPanel;
import GUI_Panel.NhanVienPanel;
import GUI_Panel.PhieuNhapPanel;
import GUI_Panel.SanPhamPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Home extends JFrame {

	private JPanel contentPane;

	public Home() {
		init();
		addActionListener();
	}
	
	int mouseX, mouseY;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton btnHome;
	private JButton btnHoaDon;
	private JButton btnNhanVien;
	private JButton btnKhachHang;
	private JButton btnSanPham;
	private JButton btnNhaCungCap;
	private JButton btnLoaiSanPham;
	private JButton btnPhieuNhap;
	private JButton btnKhuyenMai;
	private JButton btnDangXuat;

	
	private void addActionListener() {
		btnHome.addActionListener(e ->{
			cardLayout.show(cardPanel, "trangChu");
		});
		
		btnHoaDon.addActionListener(e ->{
			cardLayout.show(cardPanel, "hoaDon");
		});
		
		btnNhaCungCap.addActionListener(e ->{
			cardLayout.show(cardPanel, "nhaCungCap");
		});
		
		btnPhieuNhap.addActionListener(e ->{
			cardLayout.show(cardPanel, "phieuNhap");
		});
		
		btnKhachHang.addActionListener(e ->{
			cardLayout.show(cardPanel, "khachHang");
		});
		
		btnLoaiSanPham.addActionListener(e ->{
			cardLayout.show(cardPanel, "loaisanpham");
		});
		
		btnNhanVien.addActionListener(e ->{
			cardLayout.show(cardPanel, "nhanvien");
		});
		
		btnSanPham.addActionListener(e ->{
			cardLayout.show(cardPanel, "sanpham");
		});
		
		
		
		btnDangXuat.addActionListener(e ->{
			dispose();
		});
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				mouseX = evt.getX();
				mouseY = evt.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				int newX = getLocation().x + evt.getX() - mouseX;
				int newY = getLocation().y + evt.getY() - mouseY;
				
				setLocation(newX, newY);
			}
		});
	}
	
	

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 700);
		getContentPane().setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		
//		=============================================== PANEL LEFT ===============================================
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(250,700));
		getContentPane().add(pnLeft, BorderLayout.WEST);
		pnLeft.setBackground(SystemColor.activeCaption);
		pnLeft.setLayout(new BorderLayout(0, 0));
		
		// ********************************* TOP *********************************
		JPanel pnLeftTop = new JPanel();
		pnLeftTop.setPreferredSize(new Dimension(0, 150));
		pnLeftTop.setBorder(BorderFactory.createLineBorder(Color.black,2));
		pnLeft.add(pnLeftTop, BorderLayout.NORTH);
		
		// ********************************* BOTTOM ********************************* 
		JPanel pnLeftBottom = new JPanel();
		pnLeftBottom.setBorder(BorderFactory.createLineBorder(Color.black,2));
		pnLeft.add(pnLeftBottom, BorderLayout.CENTER);
		pnLeftBottom.setLayout(null);
		
		btnHome = new JButton("Trang chủ");
		btnHome.setIcon(new ImageIcon(Home.class.getResource("/Image/32_Home.png")));
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnHome.setBounds(15, 30, 220, 40);
		pnLeftBottom.add(btnHome);
		
		btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		btnHoaDon.setIcon(new ImageIcon(Home.class.getResource("/Image/32_Bill.png")));
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnHoaDon.setBounds(15, 80, 220, 40);
		pnLeftBottom.add(btnHoaDon);
		
		btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btnNhanVien.setIcon(new ImageIcon(Home.class.getResource("/Image/32_staff.png")));
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNhanVien.setBounds(15, 130, 220, 40);
		pnLeftBottom.add(btnNhanVien);
		
		btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhachHang.setIcon(new ImageIcon(Home.class.getResource("/Image/32_Customer.png")));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnKhachHang.setBounds(15, 180, 220, 40);
		pnLeftBottom.add(btnKhachHang);
		
		btnSanPham = new JButton("Sản phẩm");
		btnSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btnSanPham.setIcon(new ImageIcon(Home.class.getResource("/Image/32_product.png")));
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnSanPham.setBounds(15, 230, 220, 40);
		pnLeftBottom.add(btnSanPham);
		
		btnLoaiSanPham = new JButton("Loại sản phẩm");
		btnLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btnLoaiSanPham.setIcon(new ImageIcon(Home.class.getResource("/Image/32_product_type.png")));
		btnLoaiSanPham.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLoaiSanPham.setBounds(15, 280, 220, 40);
		pnLeftBottom.add(btnLoaiSanPham);
		
		btnNhaCungCap = new JButton("Nhà cung cấp");
		btnNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
		btnNhaCungCap.setIcon(new ImageIcon(Home.class.getResource("/Image/32_supplier.png")));
		btnNhaCungCap.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNhaCungCap.setBounds(15, 330, 220, 40);
		pnLeftBottom.add(btnNhaCungCap);
		
		btnPhieuNhap = new JButton("Phiếu nhập");
		btnPhieuNhap.setHorizontalAlignment(SwingConstants.LEFT);
		btnPhieuNhap.setIcon(new ImageIcon(Home.class.getResource("/Image/32_add_product.png")));
		btnPhieuNhap.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnPhieuNhap.setBounds(15, 380, 220, 40);
		pnLeftBottom.add(btnPhieuNhap);
		
		btnKhuyenMai = new JButton("Khuyến mãi");
		btnKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhuyenMai.setIcon(new ImageIcon(Home.class.getResource("/Image/32_promotion.png")));
		btnKhuyenMai.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnKhuyenMai.setBounds(10, 430, 230, 40);
		pnLeftBottom.add(btnKhuyenMai);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setIcon(new ImageIcon(Home.class.getResource("/Image/32_logout.png")));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnDangXuat.setBounds(10, 480, 230, 40);
		pnLeftBottom.add(btnDangXuat);
		
		
//		=============================================== PANEL RIGHT ===============================================
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		getContentPane().add(cardPanel, BorderLayout.CENTER);
		
		
		try {
			JPanel trangChu = new JPanel();
			JPanel hoaDon = new HoaDonPanel();
			JPanel nhaCungCap = new NhaCungCapPanel();
			JPanel phieuNhap = new PhieuNhapPanel();
			JPanel khachHang = new KhachHangPanel();
			JPanel sanpham = new SanPhamPanel();
			JPanel loaisanpham = new LoaiSanPhamPanel();
			JPanel nhanvien = new NhanVienPanel();
			
			
			cardPanel.add(trangChu, "trangChu");
			cardPanel.add(hoaDon, "hoaDon");
			cardPanel.add(nhaCungCap,"nhaCungCap");
			cardPanel.add(phieuNhap,"phieuNhap");
			cardPanel.add(khachHang, "khachHang");
			cardPanel.add(sanpham, "sanpham");
			cardPanel.add(loaisanpham, "loaisanpham");
			cardPanel.add(nhanvien, "nhanvien");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	
	/*
	 * FUNCTION
	 */

	/*
	 * MAIN
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Home frame = new Home();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
