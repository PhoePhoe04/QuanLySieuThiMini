package GUI_Panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BUS.HoaDonBUS;
import BUS.NhapHangBUS;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	
	private void init() {
		setSize(1100, 700);
		setLayout(new BorderLayout(0, 0));
		
		// PANEL BUTTON
		JPanel pnButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnButton.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnButton.setPreferredSize(new Dimension(0,30));
		add(pnButton, BorderLayout.NORTH);
		
		JButton btnTrangChu = new JButton("TRANG CHỦ");
		btnTrangChu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		pnButton.add(btnTrangChu);
		
		JButton btnThongKe = new JButton("THỐNG KÊ");
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
		
		JLabel lblTenDoAn = new JLabel("QUẢN LÝ CỬA HÀNG MINI");
		lblTenDoAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenDoAn.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTenDoAn.setBounds(312, 90, 475, 70);
		pnTrangChu.add(lblTenDoAn);
		
		JLabel lblMSSV1 = new JLabel("3122410055 - Huỳnh Phúc Duy");
		lblMSSV1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMSSV1.setBounds(50, 200, 667, 30);
		pnTrangChu.add(lblMSSV1);
		
		// Thống kê
		
		JPanel pnThongKe = new JPanel();
		CardPanel.add(pnThongKe, "name_103048575659200");
		pnThongKe.setLayout(null);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setBounds(680, 18, 70, 25);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(btnTim);
		
		dtmHD = new DefaultTableModel();
		dtmHD.addColumn("Mã hóa đơn");
		dtmHD.addColumn("Mã khách hàng");
		dtmHD.addColumn("Mã nhân viên");
		dtmHD.addColumn("Mã khuyên mãi");
		dtmHD.addColumn("Ngày lập");
		dtmHD.addColumn("Tổng ");
		
		tblHD = new JTable(dtmHD);
		
		JScrollPane scrPnHD = new JScrollPane(tblHD);
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
		cbBoxQuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnThongKe.add(cbBoxQuy);
		
		JComboBox cbBoxThang = new JComboBox();
		cbBoxThang.setBounds(570, 20, 100, 25);
		cbBoxThang.setFont(new Font("Tahoma", Font.BOLD, 15));
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
		txtDoanhThu.setBounds(475, 90, 150, 25);
		pnThongKe.add(txtDoanhThu);
		txtDoanhThu.setColumns(10);
		
		JLabel lblNam = new JLabel("Theo năm");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNam.setBounds(20, 20, 100, 25);
		pnThongKe.add(lblNam);
	}
	
	/*
	 * Add actionlistener
	 */
	private void addActionListener() {
		
	}
}
