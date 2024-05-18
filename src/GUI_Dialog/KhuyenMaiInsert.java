package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.ChiTietKMHDDTO;
import DTO.ChiTietKMSPDTO;
import DTO.KhuyenMaiDTO;
import DTO.SanPham_DTO;

import java.awt.Font;
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class KhuyenMaiInsert extends JDialog {
	
	private boolean dataAccepted = false;

	private JButton btnKM;
	private JButton btnKMSP;
	private JButton btnKMHD;
	private JPanel cardPanel;
	private JLabel lblThemKM;
	private JPanel ButtonKM;
	private JButton btnThemKM;
	private JButton btnHuyKM;
	private JPanel CenterKM;
	private JTextField txtMaKM;
	private JTextField txtTenKM;
	private JTextField txtDieuKien;
	private JTextField txtNgayBD;
	private JTextField txtNgayKT;
	
	private JPanel pnKM;
	private JPanel pnKMSP;
	private JPanel pnKMHD;
	private JLabel lblThemKMSP;
	private JPanel CenterKMSP;
	private JPanel ButtonKMSP;
	private JTextField txtMaKM_KMSP;
	private JTextField txtMaSP;
	private JTextField txtTiLeGiamGia_KMSP;

	private JLabel lblThemKMHD;
	private JPanel CenterKMHD;
	private JPanel ButtonKMHD;
	private JButton btnThemKMHD;
	private JButton btnHuyKMHD;
	private JTextField txtMaKM_KMHD;
	private JTextField txtTongTienHoaDon;
	private JTextField txtTieLeGiamGia_KMHD;
	private JButton btnNgayBD_KM;
	private JButton btnNgayKT_KM;
	private CardLayout cardLayout;

	private JButton btnMySanPham;
	private JButton btnThemKMSP;
	private JButton btnHuyKMSP;

	private JButton btnMyKM_KMSP;

	private JButton btnMyKM_KMHD;

	public KhuyenMaiInsert() {
		try {
			init();
			addActionListener();
			setLocationRelativeTo(null);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void init() {
		setModal(true);
		setSize(450,350);
		
		// NORTH
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		btnKM = new JButton("KM");
		btnKM.setPreferredSize(new Dimension(100,30));
		btnKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(btnKM);
		
		btnKMSP = new JButton("KMSP");
		btnKMSP.setPreferredSize(new Dimension(100,30));
		btnKMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(btnKMSP);
		
		btnKMHD = new JButton("KMHD");
		btnKMHD.setPreferredSize(new Dimension(100,30));
		btnKMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(btnKMHD);
		
		// CENTER
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		getContentPane().add(cardPanel, BorderLayout.CENTER);
		
		// ---------------------------------------------- KHUYEN MAI
		pnKM = new JPanel();
		pnKM.setLayout(new BorderLayout(0, 0));
		
		lblThemKM = new JLabel("THÊM KHUYẾN MÃI");
		lblThemKM.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThemKM.setHorizontalAlignment(SwingConstants.CENTER);
		pnKM.add(lblThemKM, BorderLayout.NORTH);
		
		ButtonKM = new JPanel();
		FlowLayout fl_ButtonKM = (FlowLayout) ButtonKM.getLayout();
		fl_ButtonKM.setAlignment(FlowLayout.RIGHT);
		pnKM.add(ButtonKM, BorderLayout.SOUTH);
		
		btnThemKM = new JButton("Thêm");
		btnThemKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonKM.add(btnThemKM);
		
		btnHuyKM = new JButton("Hủy");
		btnHuyKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonKM.add(btnHuyKM);
		
		CenterKM = new JPanel();
		pnKM.add(CenterKM, BorderLayout.CENTER);
		CenterKM.setLayout(null);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM.setBounds(20, 20, 150, 25);
		CenterKM.add(lblMaKM);
		
		txtMaKM = new JTextField();
		txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKM.setBounds(180, 20, 150, 25);
		CenterKM.add(txtMaKM);
		txtMaKM.setColumns(10);
		
		JLabel lblTenKM = new JLabel("Tên khuyến mãi");
		lblTenKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenKM.setBounds(20, 55, 150, 25);
		CenterKM.add(lblTenKM);
		
		txtTenKM = new JTextField();
		txtTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenKM.setColumns(10);
		txtTenKM.setBounds(180, 55, 150, 25);
		CenterKM.add(txtTenKM);
		
		JLabel lblDieuKien = new JLabel("Điều kiện");
		lblDieuKien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDieuKien.setBounds(20, 90, 150, 25);
		CenterKM.add(lblDieuKien);
		
		txtDieuKien = new JTextField();
		txtDieuKien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDieuKien.setColumns(10);
		txtDieuKien.setBounds(180, 90, 150, 25);
		CenterKM.add(txtDieuKien);
		
		JLabel lblNgayBD = new JLabel("Ngày bắt đầu");
		lblNgayBD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayBD.setBounds(20, 125, 150, 25);
		CenterKM.add(lblNgayBD);
		
		txtNgayBD = new JTextField();
		txtNgayBD.setEditable(false);
		txtNgayBD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNgayBD.setColumns(10);
		txtNgayBD.setBounds(180, 125, 150, 25);
		CenterKM.add(txtNgayBD);
		
		JLabel lblNgayKT = new JLabel("Ngày kết thúc");
		lblNgayKT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayKT.setBounds(20, 160, 150, 25);
		CenterKM.add(lblNgayKT);
		
		txtNgayKT = new JTextField();
		txtNgayKT.setEditable(false);
		txtNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNgayKT.setColumns(10);
		txtNgayKT.setBounds(180, 160, 150, 25);
		CenterKM.add(txtNgayKT);
		
		btnNgayBD_KM = new JButton("");
		btnNgayBD_KM.setIcon(new ImageIcon(KhuyenMaiInsert.class.getResource("/Image/24_calendar.png")));
		btnNgayBD_KM.setBounds(340, 125, 25, 25);
		CenterKM.add(btnNgayBD_KM);
		
		btnNgayKT_KM = new JButton("");
		btnNgayKT_KM.setIcon(new ImageIcon(KhuyenMaiInsert.class.getResource("/Image/24_calendar.png")));
		btnNgayKT_KM.setBounds(340, 160, 25, 25);
		CenterKM.add(btnNgayKT_KM);
		
		// ---------------------------------------------- KHUYEN MAI SAN PHAM
		pnKMSP = new JPanel();
		pnKMSP.setLayout(new BorderLayout(0, 0));
		
		lblThemKMSP = new JLabel("THÊM KHUYẾN MÃI SP");
		lblThemKMSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThemKMSP.setHorizontalAlignment(SwingConstants.CENTER);
		pnKMSP.add(lblThemKMSP, BorderLayout.NORTH);
		
		CenterKMSP = new JPanel();
		pnKMSP.add(CenterKMSP, BorderLayout.CENTER);
		CenterKMSP.setLayout(null);
		
		JLabel lblMaKM_KMSP = new JLabel("Mã khuyến mãi");
		lblMaKM_KMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM_KMSP.setBounds(20, 20, 150, 25);
		CenterKMSP.add(lblMaKM_KMSP);
		
		txtMaKM_KMSP = new JTextField();
		txtMaKM_KMSP.setEditable(false);
		txtMaKM_KMSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKM_KMSP.setBounds(180, 20, 150, 25);
		CenterKMSP.add(txtMaKM_KMSP);
		txtMaKM_KMSP.setColumns(10);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSP.setBounds(20, 55, 150, 25);
		CenterKMSP.add(lblMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(180, 55, 150, 25);
		CenterKMSP.add(txtMaSP);
		
		JLabel lblTiLeGiamGia_KMSP = new JLabel("Tỉ lệ giảm giá");
		lblTiLeGiamGia_KMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTiLeGiamGia_KMSP.setBounds(20, 90, 150, 25);
		CenterKMSP.add(lblTiLeGiamGia_KMSP);
		
		txtTiLeGiamGia_KMSP = new JTextField();
		txtTiLeGiamGia_KMSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTiLeGiamGia_KMSP.setColumns(10);
		txtTiLeGiamGia_KMSP.setBounds(180, 90, 150, 25);
		CenterKMSP.add(txtTiLeGiamGia_KMSP);
		
		btnMyKM_KMSP = new JButton("...");
		btnMyKM_KMSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMyKM_KMSP.setBounds(340, 20, 25, 25);
		CenterKMSP.add(btnMyKM_KMSP);
		
		btnMySanPham = new JButton("...");
		btnMySanPham.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMySanPham.setBounds(340, 55, 25, 25);
		CenterKMSP.add(btnMySanPham);
		
		ButtonKMSP = new JPanel();
		FlowLayout flowLayout = (FlowLayout) ButtonKMSP.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnKMSP.add(ButtonKMSP, BorderLayout.SOUTH);
		
		btnThemKMSP = new JButton("Thêm");
		btnThemKMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonKMSP.add(btnThemKMSP);
		
		btnHuyKMSP = new JButton("Hủy");
		btnHuyKMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuyKMSP.setHorizontalAlignment(SwingConstants.RIGHT);
		ButtonKMSP.add(btnHuyKMSP);
		
		// ---------------------------------------------- KHUYEN MAI HOA DON
		pnKMHD = new JPanel();
		pnKMHD.setLayout(new BorderLayout(0, 0));
		
		lblThemKMHD = new JLabel("THÊM KHUYẾN MÃI HD");
		lblThemKMHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThemKMHD.setHorizontalAlignment(SwingConstants.CENTER);
		pnKMHD.add(lblThemKMHD, BorderLayout.NORTH);
		
		CenterKMHD = new JPanel();
		pnKMHD.add(CenterKMHD, BorderLayout.CENTER);
		CenterKMHD.setLayout(null);
		
		JLabel lblMaKM_KMHD = new JLabel("Mã khuyến mãi");
		lblMaKM_KMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM_KMHD.setBounds(20, 20, 150, 25);
		CenterKMHD.add(lblMaKM_KMHD);
		
		txtMaKM_KMHD = new JTextField();
		txtMaKM_KMHD.setEditable(false);
		txtMaKM_KMHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKM_KMHD.setBounds(180, 20, 150, 25);
		CenterKMHD.add(txtMaKM_KMHD);
		txtMaKM_KMHD.setColumns(10);
		
		txtTongTienHoaDon = new JTextField();
		txtTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongTienHoaDon.setColumns(10);
		txtTongTienHoaDon.setBounds(180, 55, 150, 25);
		CenterKMHD.add(txtTongTienHoaDon);
		
		JLabel lblTongTienHD = new JLabel("Tổng tiền hóa đơn");
		lblTongTienHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTienHD.setBounds(20, 55, 150, 25);
		CenterKMHD.add(lblTongTienHD);
		
		txtTieLeGiamGia_KMHD = new JTextField();
		txtTieLeGiamGia_KMHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTieLeGiamGia_KMHD.setColumns(10);
		txtTieLeGiamGia_KMHD.setBounds(180, 90, 150, 25);
		CenterKMHD.add(txtTieLeGiamGia_KMHD);
		
		JLabel lblTiLeGiamGia_KMHD = new JLabel("Tỉ lệ giảm giá");
		lblTiLeGiamGia_KMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTiLeGiamGia_KMHD.setBounds(20, 90, 150, 25);
		CenterKMHD.add(lblTiLeGiamGia_KMHD);
		
		btnMyKM_KMHD = new JButton("...");
		btnMyKM_KMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMyKM_KMHD.setBounds(340, 20, 25, 25);
		CenterKMHD.add(btnMyKM_KMHD);
		
		ButtonKMHD = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) ButtonKMHD.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnKMHD.add(ButtonKMHD, BorderLayout.SOUTH);
		
		btnThemKMHD = new JButton("Thêm");
		btnThemKMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonKMHD.add(btnThemKMHD);
		
		btnHuyKMHD = new JButton("Hủy");
		btnHuyKMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonKMHD.add(btnHuyKMHD);
		
		cardPanel.add(pnKM,"KM");
		cardPanel.add(pnKMSP,"KMSP");
		cardPanel.add(pnKMHD,"KMHD");
	}
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnKM.addActionListener(e ->{
			cardLayout.show(cardPanel, "KM");
		});
		
		btnKMSP.addActionListener(e ->{
			cardLayout.show(cardPanel, "KMSP");
		});
		
		btnKMHD.addActionListener(e ->{
			cardLayout.show(cardPanel, "KMHD");
		});
		
		// KM
		btnNgayBD_KM.addActionListener(e ->{
			new MyCalender(this, txtNgayBD);
		});
		btnNgayKT_KM.addActionListener(e ->{
			new MyCalender(this, txtNgayKT);
		});
		btnHuyKM.addActionListener(e ->{
			dispose();
		});
		btnThemKM.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		
		// KMSP
		btnMyKM_KMSP.addActionListener(e ->{
			 MyKhuyenMai myKM = new MyKhuyenMai();
			 if(myKM.showDialog(this)) {
				 KhuyenMaiDTO km = myKM.getKM();
				 txtMaKM_KMSP.setText(km.getMaKM());
			 }
		});
		btnMySanPham.addActionListener(e ->{
			MySanPham mySP = new MySanPham(this);
			if(mySP.showDialog(this)) {
				SanPham_DTO sp = mySP.getSanPham();
				txtMaSP.setText(sp.getMaSP());
			}
		});
		btnThemKMSP.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		btnHuyKMSP.addActionListener(e ->{
			dispose();
		});
		
		// KMHD
		btnMyKM_KMHD.addActionListener(e ->{
			MyKhuyenMai myKM = new MyKhuyenMai();
			if(myKM.showDialog(this)) {
				KhuyenMaiDTO km = myKM.getKM();
				txtMaKM_KMHD.setText(km.getMaKM());
			}
		});
		btnThemKMHD.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		btnHuyKMHD.addActionListener(e ->{
			dispose();
		});
	}
	
	/*
	 * Function
	 */
	public boolean showDialog(Component parentComponent) {
		return this.dataAccepted;
	}
	public KhuyenMaiDTO getKM() {
		if(!txtMaKM.getText().isEmpty() && !txtTenKM.getText().isEmpty() &&  !txtDieuKien.getText().isEmpty() && !txtNgayBD.getText().isEmpty() && !txtNgayBD.getText().isEmpty()) {
			return new KhuyenMaiDTO(
					txtMaKM.getText(),
					txtTenKM.getText(),
					txtDieuKien.getText(),
					Date.valueOf(txtNgayBD.getText()),
					Date.valueOf(txtNgayBD.getText())
					);
		}
		return null;
	}
	public ChiTietKMSPDTO getKMSP() {
		if(!txtMaKM_KMSP.getText().isEmpty() && !txtMaSP.getText().isEmpty() && !txtTiLeGiamGia_KMSP.getText().isEmpty()) {
			return new ChiTietKMSPDTO(
					txtMaKM_KMSP.getText(),
					txtMaSP.getText(),
					Double.parseDouble(txtTiLeGiamGia_KMSP.getText())
					);
		}
		return null;
	}
	
	public ChiTietKMHDDTO getKMHD() {
		if(!txtMaKM_KMHD.getText().isEmpty() && !txtTongTienHoaDon.getText().isEmpty() && !txtTieLeGiamGia_KMHD.getText().isEmpty()) {
			return new ChiTietKMHDDTO(
					txtMaKM_KMHD.getText(),
					Double.parseDouble(txtTongTienHoaDon.getText()),
					Double.parseDouble(txtTieLeGiamGia_KMHD.getText())
					);
		}
		return null;
		
	}
}
