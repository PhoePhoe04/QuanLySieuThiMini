package GUI_Panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.NhapHangBUS;
import BUS.SanPham_BUS;
import DTO.HoaDonDTO;
import DTO.NhapHangDTO;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class TrangChuPanel extends JPanel {

	private HoaDonBUS hdBUS;
	private NhapHangBUS nhBUS;
	
	private DefaultTableModel dtmHD;
	private DefaultTableModel dtmPN;
	
	private JTable tblHD;
	private JTable tblPN;
	

	private CardLayout cardLayout;
	private JPanel CardPanel;
	private JTextField txtTheoNam;
	private JTextField txtTongThu;
	private JTextField txtTongChi;
	private JTextField txtDoanhThu;
	private JComboBox cbBoxQuy;
	private JButton btnTrangChu;
	private JButton btnTongQuan;
	private JButton btnThongKe;
	private JButton btnTim;
	private JComboBox cbBoxThang;
	private JButton btnRefreshTongQuan;
	private JLabel lblSLNhanVien;
	private JLabel lblSLSanPham;
	private JLabel lblSLKM;
	private JLabel lblSLHD;
	private JLabel lblPhieuNhap;

	/**
	 * Create the panel.
	 */
	public TrangChuPanel() {
		try {
			hdBUS = new HoaDonBUS();
			nhBUS = new NhapHangBUS();
			init();
			addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws SQLException{
		setSize(1100, 700);
		setLayout(new BorderLayout(0, 0));
		
		// PANEL BUTTON
		JPanel pnButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnButton.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnButton.setPreferredSize(new Dimension(0,30));
		add(pnButton, BorderLayout.NORTH);
		
		btnTrangChu = new JButton("TRANG CHỦ");
		btnTrangChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnButton.add(btnTrangChu);
		
		btnTongQuan = new JButton("TỔNG QUAN");
		btnTongQuan.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnButton.add(btnTongQuan);
		
		btnThongKe = new JButton("THỐNG KÊ");
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnButton.add(btnThongKe);
		
		//PANEL CENTER
		cardLayout = new CardLayout();
		CardPanel = new JPanel(cardLayout);
		add(CardPanel, BorderLayout.CENTER);
		
		JPanel pnTrangChu = new JPanel();
		CardPanel.add(pnTrangChu, "TrangChu");
		pnTrangChu.setLayout(null);
		
		JLabel lblTitle = new JLabel("ĐỒ ÁN NHÓM 11");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(312, 10, 475, 70);
		pnTrangChu.add(lblTitle);
		
		JLabel lblTenDoAn = new JLabel("QUẢN LÝ SIÊU THỊ MINI");
		lblTenDoAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenDoAn.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTenDoAn.setBounds(312, 90, 475, 70);
		pnTrangChu.add(lblTenDoAn);
		
		JLabel lblMSSV1 = new JLabel("3122410055 - Huỳnh Phúc Duy");
		lblMSSV1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMSSV1.setBounds(50, 244, 667, 30);
		pnTrangChu.add(lblMSSV1);
		
		JLabel lblTrn = new JLabel("3122410132 - Trần Duy Hoành");
		lblTrn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrn.setBounds(50, 286, 667, 30);
		pnTrangChu.add(lblTrn);
		
		JLabel lblQuch = new JLabel("3122410167 - Quách Hoàng Kha\r\n");
		lblQuch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuch.setBounds(50, 326, 667, 30);
		pnTrangChu.add(lblQuch);
		
		JLabel lblV = new JLabel("3122410406 - Võ Thị Yến Thùy");
		lblV.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblV.setBounds(50, 366, 667, 30);
		pnTrangChu.add(lblV);
		
		JLabel lblThnhVin = new JLabel("Thành viên:");
		lblThnhVin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThnhVin.setBounds(50, 204, 667, 30);
		pnTrangChu.add(lblThnhVin);
		
		// Thống kê
		
		JPanel pnThongKe = new JPanel();
		CardPanel.add(pnThongKe, "ThongKe");
		pnThongKe.setLayout(null);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(680, 18, 70, 25);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(btnTim);
		
		dtmHD = new DefaultTableModel();
		dtmHD.addColumn("Mã hóa đơn");
		dtmHD.addColumn("Mã khách hàng");
		dtmHD.addColumn("Mã nhân viên");
		dtmHD.addColumn("Mã khuyên mãi");
		dtmHD.addColumn("Ngày lập");
		dtmHD.addColumn("Tổng tiền");
		
		tblHD = new JTable(dtmHD);
		
		JScrollPane scrPnHD = new JScrollPane(tblHD);
		scrPnHD.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		scrPnHD.setBounds(20, 150, 510, 500);
		pnThongKe.add(scrPnHD);
		
		
		dtmPN = new DefaultTableModel();
		dtmPN.addColumn("Mã phiếu nhập");
		dtmPN.addColumn("Mã nhà cung cấp");
		dtmPN.addColumn("Mã nhân viên");
		dtmPN.addColumn("Ngày nhập");
		dtmPN.addColumn("Tổng tiền");
		
		tblPN = new JTable(dtmPN);
		
		JScrollPane scPnPN = new JScrollPane(tblPN);
		scPnPN.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		scPnPN.setBounds(570, 150, 510, 500);
		pnThongKe.add(scPnPN);
		
		txtTheoNam = new JTextField();
		txtTheoNam.setBounds(130, 20, 100, 25);
		txtTheoNam.setColumns(10);
		pnThongKe.add(txtTheoNam);
		
		JLabel lblTheoQu = new JLabel("Theo quý");
		lblTheoQu.setBounds(240, 20, 100, 25);
		lblTheoQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(lblTheoQu);
		
		
		String[] quy = new String[] {"","Quý 1", "Quý 2", "Quý 3", "Quý 4"};
		cbBoxQuy = new JComboBox(quy);
		cbBoxQuy.setBounds(350, 20, 100, 25);
		cbBoxQuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKe.add(cbBoxQuy);
		
		String[] thang = new String[] {"", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
		cbBoxThang = new JComboBox(thang);
		cbBoxThang.setBounds(570, 20, 100, 25);
		cbBoxThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKe.add(cbBoxThang);
		
		JLabel lblThang = new JLabel("Theo tháng");
		lblThang.setBounds(460, 20, 100, 25);
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(lblThang);
		
		JLabel lblTongThu = new JLabel("Tổng thu");
		lblTongThu.setBounds(80, 115, 100, 25);
		lblTongThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(lblTongThu);
		
		txtTongThu = new JTextField();
		txtTongThu.setEditable(false);
		txtTongThu.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongThu.setText("0");
		txtTongThu.setBounds(190, 115, 150, 25);
		txtTongThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnThongKe.add(txtTongThu);
		txtTongThu.setColumns(10);
		
		JLabel lblTongChi = new JLabel("Tổng chi");
		lblTongChi.setBounds(680, 115, 100, 25);
		lblTongChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(lblTongChi);
		
		txtTongChi = new JTextField();
		txtTongChi.setEditable(false);
		txtTongChi.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongChi.setText("0");
		txtTongChi.setBounds(790, 115, 150, 25);
		txtTongChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongChi.setColumns(10);
		pnThongKe.add(txtTongChi);
		
		JLabel lblDoanhThu = new JLabel("Doanh thu");
		lblDoanhThu.setBounds(500, 55, 100, 25);
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(lblDoanhThu);
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setEditable(false);
		txtDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		txtDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDoanhThu.setText("0");
		txtDoanhThu.setBounds(475, 90, 150, 25);
		pnThongKe.add(txtDoanhThu);
		txtDoanhThu.setColumns(10);
		
		JLabel lblNam = new JLabel("Theo năm");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNam.setBounds(20, 20, 100, 25);
		pnThongKe.add(lblNam);
		
		JPanel pnTongQuan = new JPanel();
		CardPanel.add(pnTongQuan, "TongQuan");
		pnTongQuan.setLayout(null);
		
		JLabel imgNhanVien = new JLabel("");
		imgNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		imgNhanVien.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/128_human.png")));
		imgNhanVien.setBounds(179, 50, 128, 128);
		pnTongQuan.add(imgNhanVien);
		
		lblSLNhanVien = new JLabel("Nhân viên: "+ new NhanVienBUS().getList_NV().size());
		lblSLNhanVien.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSLNhanVien.setBounds(143, 188, 200, 30);
		pnTongQuan.add(lblSLNhanVien);
		
		JLabel imgSanPham = new JLabel("");
		imgSanPham.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/128_product.png")));
		imgSanPham.setBounds(486, 50, 128, 128);
		pnTongQuan.add(imgSanPham);
		
		lblSLSanPham = new JLabel("Sản phẩm:"+ new SanPham_BUS().getList().size());
		lblSLSanPham.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSLSanPham.setBounds(450, 188, 200, 30);
		pnTongQuan.add(lblSLSanPham);
		
		JLabel imgKhuyenMai = new JLabel("");
		imgKhuyenMai.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/128_promotion.png")));
		imgKhuyenMai.setBounds(793, 50, 128, 128);
		pnTongQuan.add(imgKhuyenMai);
		
		lblSLKM = new JLabel("Khuyến mãi:"+ new KhuyenMaiBUS().getList_KM().size());
		lblSLKM.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSLKM.setBounds(757, 188, 200, 30);
		pnTongQuan.add(lblSLKM);
		
		JLabel imgHoaDon = new JLabel("");
		imgHoaDon.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/128_bill.png")));
		imgHoaDon.setBounds(281, 341, 128, 128);
		pnTongQuan.add(imgHoaDon);
		
		lblSLHD = new JLabel("Hóa đơn: "+hdBUS.getList_hoadon().size());
		lblSLHD.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSLHD.setBounds(245, 490, 200, 30);
		pnTongQuan.add(lblSLHD);
		
		JLabel imgNhapHang = new JLabel("");
		imgNhapHang.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/128_phieuNhap.png")));
		imgNhapHang.setBounds(690, 341, 128, 128);
		pnTongQuan.add(imgNhapHang);
		
		lblPhieuNhap = new JLabel("Phiếu nhập: "+nhBUS.getList().size());
		lblPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPhieuNhap.setBounds(654, 490, 200, 30);
		pnTongQuan.add(lblPhieuNhap);
		
		btnRefreshTongQuan = new JButton("");
		btnRefreshTongQuan.setIcon(new ImageIcon(TrangChuPanel.class.getResource("/Image/24_refresh.png")));
		btnRefreshTongQuan.setBounds(1065, 10, 25, 25);
		pnTongQuan.add(btnRefreshTongQuan);
		
		
	}
	
	/*
	 * Add actionlistener
	 */
	private void addActionListener() {
		btnTrangChu.addActionListener(e ->{
			cardLayout.show(CardPanel, "TrangChu");
		});
		btnThongKe.addActionListener(e ->{
			cardLayout.show(CardPanel, "ThongKe");
		});
		btnTongQuan.addActionListener(e ->{
			cardLayout.show(CardPanel, "TongQuan");
		});
		
		btnRefreshTongQuan.addActionListener(e ->{
			try {
				lblSLNhanVien.setText("Nhân viên: "+ new NhanVienBUS().getList_NV().size());
				lblSLSanPham.setText("Sản phẩm:"+ new SanPham_BUS().getList().size());
				lblSLKM.setText("Khuyến mãi:"+ new KhuyenMaiBUS().getList_KM().size());
				lblSLHD.setText("Hóa đơn: "+new HoaDonBUS().getList_hoadon().size());
				lblPhieuNhap.setText("Phiếu nhập: "+ new NhapHangBUS().getList().size());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		btnTim.addActionListener(e ->{
			String queryHD = "";
			String queryPN = "";
			
			dtmHD.setRowCount(0);
			dtmPN.setRowCount(0);
			
			if(!txtTheoNam.getText().isEmpty() && txtTheoNam.getText().matches("[0-9]+")) {
				if(queryHD.length() <= 0 && queryPN.length() <= 0) {
					queryHD += " YEAR(ngaylap) = "+ txtTheoNam.getText();
					queryPN += " YEAR(ngaynhap) = "+ txtTheoNam.getText();
				}
				else {
					queryHD += " AND "+ " YEAR(ngaylap) = "+ txtTheoNam.getText();
					queryPN += " AND "+ " YEAR(ngaynhap) = "+ txtTheoNam.getText();
				}
					
			}
			
			if(!txtTheoNam.getText().isEmpty() && txtTheoNam.getText().matches("[0-9]+") && cbBoxQuy.getSelectedIndex() != 0 && cbBoxThang.getSelectedIndex() == 0) {
				switch (cbBoxQuy.getSelectedIndex()) {
					case 1: 
						queryHD += " AND "+ " MONTH(ngaylap) IN(1,2,3) ";
						queryPN += " AND "+ " MONTH(ngaynhap) IN(1,2,3) ";
						break;
					case 2: 
						queryHD += " AND "+ " MONTH(ngaylap) IN(4,5,6) ";
						queryPN += " AND "+ " MONTH(ngaynhap) IN(4,5,6) ";
						break;
					case 3: 
						queryHD += " AND "+ " MONTH(ngaylap) IN(7,8,9) ";
						queryPN += " AND "+ " MONTH(ngaynhap) IN(7,8,9) ";
						break;	
					case 4: 
						queryHD += " AND "+ " MONTH(ngaylap) IN(10,11,12) ";
						queryPN += " AND "+ " MONTH(ngaynhap) IN(10,11,12) ";
						break;
				}
			}
			
			if(!txtTheoNam.getText().isEmpty() && txtTheoNam.getText().matches("[0-9]+") && cbBoxThang.getSelectedIndex() != 0) {
				queryHD += " AND "+ " MONTH(ngaylap) = "+ cbBoxThang.getSelectedIndex();
				queryPN += " AND "+ " MONTH(ngayNhap) = "+ cbBoxThang.getSelectedIndex();
			}
			
			
			if(queryHD.length() > 0 && queryPN.length() > 0) {
				ArrayList<HoaDonDTO> listHD = hdBUS.layDuLieu(queryHD);
				ArrayList<NhapHangDTO> listPN = nhBUS.getList(queryPN);
				
				Double tongHD = 0.0;
				Double tongPN = 0.0;
				
				for (NhapHangDTO nhapHangDTO : listPN) {
					tongPN += nhapHangDTO.getTongTien();
				}
				for (HoaDonDTO hoaDonDTO : listHD) {
					tongHD += hoaDonDTO.getTongTien(); 
				}
				
				txtTongThu.setText(tongHD+ "");
				txtTongChi.setText(tongPN+ "");
				txtDoanhThu.setText((tongHD - tongPN)+"");
				if(tongHD - tongPN > 0)
					txtDoanhThu.setForeground(Color.green);
				else if(tongHD -tongPN < 0)
					txtDoanhThu.setForeground(Color.red);
				else
					txtDoanhThu.setForeground(Color.black);
				
				addDataTable_HD(listHD);
				addDataTable_PN(listPN);
			}
		});
	}
	/*
	 * Function
	 */
	private void addDataTable_HD(HoaDonDTO hd) {
		dtmHD.addRow(new Object[] {hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getMaKM(), hd.getNgayLap(), hd.getTongTien()});
	}
	private void addDataTable_HD(ArrayList<HoaDonDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			HoaDonDTO hd = list.get(i);
			addDataTable_HD(hd);
		}
	}
	
	private void addDataTable_PN(NhapHangDTO nh) {
		dtmPN.addRow(new Object[] {nh.getMaPN(), nh.getMaNV(), nh.getMaNCC(), nh.getNgayNhap(), nh.getTongTien()});
	}
	private void addDataTable_PN(ArrayList<NhapHangDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			NhapHangDTO nh = list.get(i);
			addDataTable_PN(nh);
		}
	}
}
