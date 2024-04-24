package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class HoaDon extends JFrame {
	private JTable tblCTHD;
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtMaNV;
	private JTextField txtNgayLap;
	private JTextField txtTongTien;
	private JTextField txtMaHD_CTHD;
	private JTextField txtMaSP_CTHD;
	private JTextField txtMaKM_CTHD;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtThanhTien;
	private JTextField txtTKLuaChon;
	private JTextField txtGiaThap;
	private JTextField txtGiaCao;
	private JTextField txtTKLC_CTHD;
	private JTextField txtThanhTienThap;
	private JTextField txtThanhTienCao;
	
	public HoaDon() {
		this.init();
		setVisible(true);
	}

	private void init() {
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,800);
		setLocationRelativeTo(null);
		setResizable(false);
		// Components
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		// Font - Size - Border
		Font font = new Font("Tahoma", Font.PLAIN, 15);
		Border border = BorderFactory.createLineBorder(Color.blue,2);
		
		// ==================================== Panel Top ====================================
		
		JPanel pnTop = new JPanel();
		getContentPane().add(pnTop);
		pnTop.setLayout(null);
		
		JPanel pnThongTinHD = new JPanel();
		pnThongTinHD.setBounds(10, 10, 350, 361);
		pnThongTinHD.setBorder(border);
		pnTop.add(pnThongTinHD);
		pnThongTinHD.setLayout(null);
		
		JPanel pnHDTitle = new JPanel();
		pnHDTitle.setBounds(10, 10, 330, 30);
		pnThongTinHD.add(pnHDTitle);
		pnHDTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		JLabel lblHD = new JLabel("HÓA ĐƠN");
		lblHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnHDTitle.add(lblHD);
		
		JLabel lblMaHD = new JLabel("Mã HD");
		lblMaHD.setBounds(10, 50, 50, 30);
		lblMaHD.setFont(font);
		pnThongTinHD.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(70, 50, 70, 30);
		pnThongTinHD.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel tblMaKH = new JLabel("Mã KH");
		tblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblMaKH.setBounds(10, 90, 50, 30);
		pnThongTinHD.add(tblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(70, 90, 70, 30);
		pnThongTinHD.add(txtMaKH);
		
		JLabel tblMaNV = new JLabel("Mã NV");
		tblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblMaNV.setBounds(10, 127, 50, 30);
		pnThongTinHD.add(tblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(70, 130, 70, 30);
		pnThongTinHD.add(txtMaNV);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayLap.setBounds(150, 50, 70, 30);
		pnThongTinHD.add(lblNgayLap);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setColumns(10);
		txtNgayLap.setBounds(230, 50, 100, 30);
		pnThongTinHD.add(txtNgayLap);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongTien.setBounds(150, 90, 70, 30);
		pnThongTinHD.add(lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(230, 90, 100, 30);
		pnThongTinHD.add(txtTongTien);
		
		JPanel pnButton = new JPanel();
		pnButton.setBounds(10, 167, 330, 30);
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnThongTinHD.add(pnButton);
		
		Dimension btnSize = new Dimension(70,20);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setPreferredSize(new Dimension(80,20));
		btnThem.setFont(font);
		pnButton.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setPreferredSize(btnSize);
		btnSua.setFont(font);
		pnButton.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(font);
		btnXoa.setPreferredSize(btnSize);
		pnButton.add(btnXoa);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setFont(font);
		btnLuu.setPreferredSize(btnSize);
		pnButton.add(btnLuu);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 207, 330, 2);
		pnThongTinHD.add(separator);
		
		JLabel lblTKLuaChon = new JLabel("Tìm kiếm theo lựa chọn");
		lblTKLuaChon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTKLuaChon.setBounds(10, 207, 210, 20);
		pnThongTinHD.add(lblTKLuaChon);
		

		
		JComboBox cbTKluaChon = new JComboBox<>(new String[] {"","Mã HD", "Mã KH", "Mã NV", "Ngày lập"});
		cbTKluaChon.setBounds(10, 237, 91, 30);
		pnThongTinHD.add(cbTKluaChon);
		
		txtTKLuaChon = new JTextField();
		txtTKLuaChon.setBounds(110, 237, 140, 30);
		pnThongTinHD.add(txtTKLuaChon);
		txtTKLuaChon.setColumns(10);
		
		JButton btnTKLuaChon = new JButton("Tìm");
		btnTKLuaChon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTKLuaChon.setBounds(255, 238, 85, 30);
		pnThongTinHD.add(btnTKLuaChon);
		
		JLabel lblTKTheoGia = new JLabel("Tìm kiếm theo giá tiền");
		lblTKTheoGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTKTheoGia.setBounds(10, 277, 210, 20);
		pnThongTinHD.add(lblTKTheoGia);
		
		txtGiaThap = new JTextField();
		txtGiaThap.setColumns(10);
		txtGiaThap.setBounds(10, 307, 100, 30);
		pnThongTinHD.add(txtGiaThap);
		
		JLabel lblNewLabel = new JLabel("~>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(120, 307, 30, 30);
		pnThongTinHD.add(lblNewLabel);
		
		txtGiaCao = new JTextField();
		txtGiaCao.setColumns(10);
		txtGiaCao.setBounds(150, 307, 100, 30);
		pnThongTinHD.add(txtGiaCao);
		
		JButton btnTKTheoGia = new JButton("Tìm");
		btnTKTheoGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTKTheoGia.setBounds(255, 307, 85, 30);
		pnThongTinHD.add(btnTKTheoGia);

		JTable tblHD = new JTable();
		tblHD.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã HD", "Mã KH", "Mã NV", "Ngày lập", "Tổng tiền"
			}
		));
		tblHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane spHD = new JScrollPane(tblHD);
		spHD.setBounds(370, 10, 806, 361);
		pnTop.add(spHD);
		
		// ==================================== Panel Bottom ====================================
		
		JPanel pnBottom = new JPanel();
		getContentPane().add(pnBottom);
		pnBottom.setLayout(null);
		
		JPanel pnThongTinCTHD = new JPanel();
		pnThongTinCTHD.setBounds(10, 10, 350, 361);
		pnThongTinCTHD.setBorder(border);
		pnBottom.add(pnThongTinCTHD);
		pnThongTinCTHD.setLayout(null);
		
		JPanel pnCTHDTitle = new JPanel();
		pnCTHDTitle.setBounds(10, 10, 330, 30);
		pnThongTinCTHD.add(pnCTHDTitle);
		pnCTHDTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		JLabel lblCTHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnCTHDTitle.add(lblCTHD);
		
		JLabel lblMaHD_CTHD = new JLabel("Mã HD");
		lblMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaHD_CTHD.setBounds(10, 50, 50, 30);
		pnThongTinCTHD.add(lblMaHD_CTHD);
		
		txtMaHD_CTHD = new JTextField();
		txtMaHD_CTHD.setColumns(10);
		txtMaHD_CTHD.setBounds(70, 50, 70, 30);
		pnThongTinCTHD.add(txtMaHD_CTHD);
		
		JLabel lblMaSP_CTHD = new JLabel("Mã SP");
		lblMaSP_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaSP_CTHD.setBounds(10, 90, 50, 30);
		pnThongTinCTHD.add(lblMaSP_CTHD);
		
		txtMaSP_CTHD = new JTextField();
		txtMaSP_CTHD.setColumns(10);
		txtMaSP_CTHD.setBounds(70, 90, 70, 30);
		pnThongTinCTHD.add(txtMaSP_CTHD);
		
		JLabel lblMaKM_CTHD = new JLabel("Mã KM");
		lblMaKM_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaKM_CTHD.setBounds(10, 130, 50, 30);
		pnThongTinCTHD.add(lblMaKM_CTHD);
		
		txtMaKM_CTHD = new JTextField();
		txtMaKM_CTHD.setColumns(10);
		txtMaKM_CTHD.setBounds(70, 130, 70, 30);
		pnThongTinCTHD.add(txtMaKM_CTHD);
		
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDonGia.setBounds(160, 50, 70, 30);
		pnThongTinCTHD.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(240, 50, 100, 30);
		pnThongTinCTHD.add(txtDonGia);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoLuong.setBounds(160, 90, 70, 30);
		pnThongTinCTHD.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(240, 90, 100, 30);
		pnThongTinCTHD.add(txtSoLuong);
		
		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThanhTien.setBounds(160, 130, 80, 30);
		pnThongTinCTHD.add(lblThanhTien);
		
		txtThanhTien = new JTextField();
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(240, 130, 100, 30);
		pnThongTinCTHD.add(txtThanhTien);
		
		JPanel pnButton_CTHD = new JPanel();
		pnButton_CTHD.setBounds(10, 172, 330, 30);
		pnThongTinCTHD.add(pnButton_CTHD);
		pnButton_CTHD.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnThem_CTHD = new JButton("Thêm");
		btnThem_CTHD.setPreferredSize(new Dimension(80, 20));
		btnThem_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton_CTHD.add(btnThem_CTHD);
		
		JButton btnSua_CTHD = new JButton("Sửa");
		btnSua_CTHD.setPreferredSize(new Dimension(70, 20));
		btnSua_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton_CTHD.add(btnSua_CTHD);
		
		JButton btnXoa_CTHD = new JButton("Xóa");
		btnXoa_CTHD.setPreferredSize(new Dimension(70, 20));
		btnXoa_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton_CTHD.add(btnXoa_CTHD);
		
		JButton btnLuu_CTHD = new JButton("Lưu");
		btnLuu_CTHD.setPreferredSize(new Dimension(70, 20));
		btnLuu_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnButton_CTHD.add(btnLuu_CTHD);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 212, 330, 2);
		pnThongTinCTHD.add(separator_1);
		
		JLabel lblTKLuaChon_CTHD = new JLabel("Tìm kiếm theo lựa chọn");
		lblTKLuaChon_CTHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTKLuaChon_CTHD.setBounds(10, 224, 210, 20);
		pnThongTinCTHD.add(lblTKLuaChon_CTHD);
		
		JComboBox<String> cbTKluaChon_CTHD = new JComboBox<String>(new String[] {"", "Mã HD", "Mã NV", "Mã SP", "Đơn giá"});
		cbTKluaChon_CTHD.setBounds(10, 254, 91, 30);
		pnThongTinCTHD.add(cbTKluaChon_CTHD);
		
		txtTKLC_CTHD = new JTextField();
		txtTKLC_CTHD.setColumns(10);
		txtTKLC_CTHD.setBounds(110, 254, 140, 30);
		pnThongTinCTHD.add(txtTKLC_CTHD);
		
		JButton btnTKLuaChon_CTHD = new JButton("Tìm");
		btnTKLuaChon_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTKLuaChon_CTHD.setBounds(255, 255, 85, 30);
		pnThongTinCTHD.add(btnTKLuaChon_CTHD);
		
		JLabel lblTKTheoThanhTien = new JLabel("Tìm kiếm theo thành tiền\r\n");
		lblTKTheoThanhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTKTheoThanhTien.setBounds(10, 294, 210, 20);
		pnThongTinCTHD.add(lblTKTheoThanhTien);
		
		txtThanhTienThap = new JTextField();
		txtThanhTienThap.setColumns(10);
		txtThanhTienThap.setBounds(10, 321, 100, 30);
		pnThongTinCTHD.add(txtThanhTienThap);
		
		JLabel lblNewLabel_1 = new JLabel("~>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(120, 321, 30, 30);
		pnThongTinCTHD.add(lblNewLabel_1);
		
		txtThanhTienCao = new JTextField();
		txtThanhTienCao.setColumns(10);
		txtThanhTienCao.setBounds(150, 321, 100, 30);
		pnThongTinCTHD.add(txtThanhTienCao);
		
		JButton btnTKThanhTien = new JButton("Tìm");
		btnTKThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTKThanhTien.setBounds(255, 321, 85, 30);
		pnThongTinCTHD.add(btnTKThanhTien);
		
		tblCTHD = new JTable();
		tblCTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblCTHD.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã HD", "Mã SP", "Mã KM", "Đơn giá", "Số lượng", "Thành tiền"
			}
		));
		
		JScrollPane spCTHD = new JScrollPane(tblCTHD);
		spCTHD.setBounds(370, 10, 806, 361);
		pnBottom.add(spCTHD);
		
	}
	
	public static void main(String[] args) {
		try {
			new HoaDon();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
