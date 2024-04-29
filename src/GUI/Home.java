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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import GUI_Panel.HoaDonPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private JButton btnHoaDon;
	private JButton btnHome;
	private JButton btnDangXuat;
	
	private void addActionListener() {
		btnHome.addActionListener(e ->{
			cardLayout.show(cardPanel, "trangChu");
		});
		btnHoaDon.addActionListener(e ->{
			cardLayout.show(cardPanel, "hoaDon");
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
		setBounds(100, 100, 1430, 800);
		getContentPane().setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		
//		=============================================== PANEL LEFT ===============================================
		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(230,800));
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
		GridBagLayout gbl_pnLeftBottom = new GridBagLayout();
		gbl_pnLeftBottom.columnWidths = new int[]{20, 160, 20};
		gbl_pnLeftBottom.rowHeights = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 0};
		gbl_pnLeftBottom.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_pnLeftBottom.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnLeftBottom.setLayout(gbl_pnLeftBottom);
		
		btnHome = new JButton("Trang chủ");
		btnHome.setForeground(UIManager.getColor("Button.background"));
		btnHome.setBackground(SystemColor.activeCaption);
		btnHome.setHorizontalAlignment(SwingConstants.LEFT);
		btnHome.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnHome.setIcon(new ImageIcon(Home.class.getResource("/Image/home_icon.png")));
		btnHome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.fill = GridBagConstraints.BOTH;
		gbc_btnHome.insets = new Insets(0, 0, 15, 5);
		gbc_btnHome.gridx = 1;
		gbc_btnHome.gridy = 1;
		pnLeftBottom.add(btnHome, gbc_btnHome);
		
		btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setIcon(new ImageIcon(Home.class.getResource("/Image/iconBill.png")));
		btnHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		btnHoaDon.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnHoaDon = new GridBagConstraints();
		gbc_btnHoaDon.fill = GridBagConstraints.BOTH;
		gbc_btnHoaDon.insets = new Insets(0, 0, 15, 5);
		gbc_btnHoaDon.gridx = 1;
		gbc_btnHoaDon.gridy = 2;
		pnLeftBottom.add(btnHoaDon, gbc_btnHoaDon);
		
		JButton btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.setIcon(new ImageIcon(Home.class.getResource("/Image/iconStaff.png")));
		btnNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btnNhanVien.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnNhanVien = new GridBagConstraints();
		gbc_btnNhanVien.fill = GridBagConstraints.BOTH;
		gbc_btnNhanVien.insets = new Insets(0, 0, 15, 5);
		gbc_btnNhanVien.gridx = 1;
		gbc_btnNhanVien.gridy = 3;
		pnLeftBottom.add(btnNhanVien, gbc_btnNhanVien);
		
		JButton btnKhachHang = new JButton("Khách hàng");
		btnKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhachHang.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnKhachHang.setIcon(new ImageIcon(Home.class.getResource("/Image/iconCustomer.png")));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnKhachHang = new GridBagConstraints();
		gbc_btnKhachHang.fill = GridBagConstraints.BOTH;
		gbc_btnKhachHang.insets = new Insets(0, 0, 15, 5);
		gbc_btnKhachHang.gridx = 1;
		gbc_btnKhachHang.gridy = 4;
		pnLeftBottom.add(btnKhachHang, gbc_btnKhachHang);
		
		JButton btnSanPham = new JButton("Sản phẩm");
		btnSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btnSanPham.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSanPham.setIcon(new ImageIcon(Home.class.getResource("/Image/iconProduct.png")));
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnSanPham = new GridBagConstraints();
		gbc_btnSanPham.fill = GridBagConstraints.BOTH;
		gbc_btnSanPham.insets = new Insets(0, 0, 15, 5);
		gbc_btnSanPham.gridx = 1;
		gbc_btnSanPham.gridy = 5;
		pnLeftBottom.add(btnSanPham, gbc_btnSanPham);
		
		JButton btnKhuyenMai = new JButton("Khuyến mãi");
		btnKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		btnKhuyenMai.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnKhuyenMai.setIcon(new ImageIcon(Home.class.getResource("/Image/iconPromotion.png")));
		btnKhuyenMai.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnKhuyenMai = new GridBagConstraints();
		gbc_btnKhuyenMai.fill = GridBagConstraints.BOTH;
		gbc_btnKhuyenMai.insets = new Insets(0, 0, 15, 5);
		gbc_btnKhuyenMai.gridx = 1;
		gbc_btnKhuyenMai.gridy = 6;
		pnLeftBottom.add(btnKhuyenMai, gbc_btnKhuyenMai);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDangXuat.setIcon(new ImageIcon(Home.class.getResource("/Image/iconLogout.png")));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnDangXuat = new GridBagConstraints();
		gbc_btnDangXuat.fill = GridBagConstraints.BOTH;
		gbc_btnDangXuat.insets = new Insets(0, 0, 15, 5);
		gbc_btnDangXuat.gridx = 1;
		gbc_btnDangXuat.gridy = 7;
		pnLeftBottom.add(btnDangXuat, gbc_btnDangXuat);
		
		
		
//		=============================================== PANEL RIGHT ===============================================
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		getContentPane().add(cardPanel, BorderLayout.CENTER);
		
		JPanel hoaDon = new HoaDonPanel();
		JPanel trangChu = new JPanel();
		
		cardPanel.add(trangChu, "trangChu");
		cardPanel.add(hoaDon, "hoaDon");
		
		
		
	}

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
