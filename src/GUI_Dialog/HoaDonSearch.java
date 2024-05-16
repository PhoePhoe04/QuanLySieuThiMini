package GUI_Dialog;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietPhieuNhapBUS;
import BUS.HoaDonBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.HoaDonDTO;
import DTO.NhapHangDTO;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;

public class HoaDonSearch extends JDialog {
	private boolean dataAccepted = false;
	
	private String query = "";

	private HoaDonBUS hdBUS;
	private List<HoaDonDTO> list;
	
	private JButton btnHuy;
	private JTextField txtTuNgay;
	private JButton btnTimNangCao;
	private JLabel lblTimKiemNangCao;
	private JTextField txtDenNgay;
	private JTextField txtMaSP;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTextField txtMaKM;
	private ChiTietHoaDonBUS cthdBUS;
	private List<ChiTietHoaDonDTO> list_CT;
	private JPanel panel;
	private JPanel panel_2;
	private JTextField txtTongTienBe;
	private JTextField txtTongTienLon;
	private JLabel lblDenNgay;

	private ButtonGroup btnGroup;
	private JLabel lblMaHD;
	private JTextField txtMaHD;
	private JRadioButton rdbtnAND;
	private JRadioButton rdbtnOR;

	private JButton btnTuNgay;

	private JButton btnDenNgay;
	

	public HoaDonSearch() {
		hdBUS = new HoaDonBUS();
		cthdBUS =   new ChiTietHoaDonBUS();
		list = new ArrayList<HoaDonDTO>();
		init();
		addActionListener();
		setVisible(true);
	}
	
	/*
	 * CREATE DIALOG
	 */
	private void init() {
		setResizable(false);
		setModal(true);
		setSize(500,449);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		lblTimKiemNangCao = new JLabel("Tìm kiếm nâng cao");
		panel.add(lblTimKiemNangCao);
		lblTimKiemNangCao.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtTuNgay = new JTextField();
		txtTuNgay.setEditable(false);
		txtTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTuNgay.setColumns(10);
		txtTuNgay.setBounds(150, 192, 120, 25);
		panel_1.add(txtTuNgay);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKH.setBounds(20, 87, 120, 25);
		panel_1.add(lblMaKH);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSP.setBounds(20, 122, 120, 25);
		panel_1.add(lblMaSP);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNV.setBounds(20, 52, 120, 25);
		panel_1.add(lblMaNV);
		
		JLabel lblTuNgay = new JLabel("Từ ngày");
		lblTuNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTuNgay.setBounds(20, 192, 120, 25);
		panel_1.add(lblTuNgay);
		
		txtDenNgay = new JTextField();
		txtDenNgay.setEditable(false);
		txtDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDenNgay.setColumns(10);
		txtDenNgay.setBounds(150, 227, 120, 25);
		panel_1.add(txtDenNgay);
		
		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(150, 122, 120, 25);
		panel_1.add(txtMaSP);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(150, 87, 120, 25);
		panel_1.add(txtMaKH);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(150, 52, 120, 25);
		panel_1.add(txtMaNV);
		
		txtMaKM = new JTextField();
		txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaKM.setColumns(10);
		txtMaKM.setBounds(150, 157, 120, 25);
		panel_1.add(txtMaKM);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM.setBounds(20, 157, 120, 25);
		panel_1.add(lblMaKM);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTien.setBounds(20, 262, 120, 25);
		panel_1.add(lblTongTien);
		
		txtTongTienBe = new JTextField();
		txtTongTienBe.setBounds(150, 262, 120, 25);
		panel_1.add(txtTongTienBe);
		txtTongTienBe.setColumns(10);
		
		JLabel lblMuiTen2 = new JLabel("--->");
		lblMuiTen2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMuiTen2.setBounds(280, 262, 45, 25);
		panel_1.add(lblMuiTen2);
		
		txtTongTienLon = new JTextField();
		txtTongTienLon.setBounds(335, 262, 120, 25);
		panel_1.add(txtTongTienLon);
		txtTongTienLon.setColumns(10);
		
		lblDenNgay = new JLabel("Đến ngày");
		lblDenNgay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDenNgay.setBounds(20, 227, 120, 25);
		panel_1.add(lblDenNgay);
		
		btnTuNgay = new JButton("");
		btnTuNgay.setIcon(new ImageIcon(HoaDonSearch.class.getResource("/Image/24_calendar.png")));
		btnTuNgay.setBounds(280, 192, 25, 25);
		panel_1.add(btnTuNgay);
		
		btnDenNgay = new JButton("");
		btnDenNgay.setIcon(new ImageIcon(HoaDonSearch.class.getResource("/Image/24_calendar.png")));
		btnDenNgay.setBounds(280, 227, 25, 25);
		panel_1.add(btnDenNgay);
		
		rdbtnAND = new JRadioButton("AND");
		rdbtnAND.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnAND.setBounds(20, 292, 80, 25);
		panel_1.add(rdbtnAND);
		
		rdbtnOR = new JRadioButton("OR");
		rdbtnOR.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnOR.setBounds(102, 293, 80, 25);
		panel_1.add(rdbtnOR);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		btnTimNangCao = new JButton("Tìm");
		panel_2.add(btnTimNangCao);
		btnTimNangCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimNangCao.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnHuy = new JButton("Hủy");
		panel_2.add(btnHuy);
		btnHuy.addActionListener(e ->{
			dispose();
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnGroup = new ButtonGroup();
		rdbtnAND.setSelected(true);
		btnGroup.add(rdbtnOR);
		btnGroup.add(rdbtnAND);
		
		lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaHD.setBounds(20, 17, 120, 25);
		panel_1.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaHD.setBounds(150, 17, 120, 25);
		panel_1.add(txtMaHD);
		txtMaHD.setColumns(10);
	}
	/*
	 * FUNCTION
	 */

	private void addActionListener() {
		btnHuy.addActionListener(e->{
			dispose();
		});
		btnTimNangCao.addActionListener(e->{
			HashMap<String, Object> condition = new HashMap<String, Object>();
			if(!txtMaHD.getText().isEmpty())
				condition.put(" maHD ", " LIKE '%"+ txtMaHD.getText()+ "%'");
			if(!txtMaNV.getText().isEmpty())
				condition.put(" maNV ", " LIKE '%"+ txtMaNV.getText()+ "%'");
			if(!txtMaKH.getText().isEmpty())
				condition.put(" maKH ", " LIKE '%"+ txtMaKH.getText()+ "%'");
			if(!txtMaKM.getText().isEmpty())
				condition.put(" maKM ", " LIKE '%"+ txtMaKM.getText()+ "%'");
			
			if(!txtTuNgay.getText().isEmpty() && !txtDenNgay.getText().isEmpty())
				condition.put(" ngayLap ", " BETWEEN "+ "'"+ Date.valueOf(txtTuNgay.getText())+"'" + " AND "+ "'"+ Date.valueOf(txtDenNgay.getText())+ "'");
			if(!txtTuNgay.getText().isEmpty() && txtDenNgay.getText().isEmpty())
				condition.put(" ngayLap ", " LIKE '%"+ Date.valueOf(txtTuNgay.getText())+ "%'");
			if(txtTuNgay.getText().isEmpty() && !txtDenNgay.getText().isEmpty())
				condition.put(" ngayLap ", " LIKE '%"+ Date.valueOf(txtDenNgay.getText())+ "%'");
			
			if(!txtTongTienBe.getText().isEmpty() && !txtTongTienLon.getText().isEmpty())
				condition.put(" tongTien ", " BETWEEN "+ txtTongTienBe.getText()+ " AND "+ txtTongTienLon.getText());
			if(!txtTongTienBe.getText().isEmpty() && txtTongTienLon.getText().isEmpty())
				condition.put(" tongTien => ", txtTongTienBe.getText());
			if(txtTongTienBe.getText().isEmpty() && !txtTongTienLon.getText().isEmpty())
				condition.put(" tongTien <= ", txtTongTienLon.getText());
			
			if(rdbtnAND.isSelected()) {
				for (String key : condition.keySet()) {
					if(query.length() == 0)
						this.query += key + condition.get(key);
					else
						this.query += " AND "+ key+ condition.get(key);
				}
			}
			if(rdbtnOR.isSelected()) {
				for (String key : condition.keySet()) {
					if(query.length() == 0)
						this.query += key + condition.get(key);
					else
						this.query += " OR "+ key+ condition.get(key);
				}
			}
			dataAccepted = true;
			dispose();
		});
		
		btnTuNgay.addActionListener(e ->{
			new MyCalender(this, txtTuNgay);
		});
		
		btnDenNgay.addActionListener(e ->{
			new MyCalender(this, txtDenNgay);
		});
	}
	
	public boolean showDialog(Component parenComponent) {
		return this.dataAccepted;
	}
	
	public String getQuery() {
		return this.query;
	}
}