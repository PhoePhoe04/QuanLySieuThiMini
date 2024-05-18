package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPham_BUS;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietKMHDDTO;
import DTO.ChiTietKMSPDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.SanPham_DTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class HoaDonInsert extends JDialog {
	private static final long serialVersionUID = 1L;
	
	boolean dataAccepted = false;
	
	private ArrayList<ChiTietHoaDonDTO> lst_CTHD;
	private HashMap<String, Integer> sanPhamChuaThanhToan = new HashMap<String, Integer>();
	
	private KhachHangBUS khachHangBUS;
	private NhanVienBUS nhanVienBUS;
	private SanPham_BUS sanPhamBUS;
	
	int mouseX, mouseY;
	private JTextField txtMaHD;
	private JTextField txtNgayLap;
	private JTextField txtTongTien;
	private JTextField txtMaHD_CTHD;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtThanhTien;
	private JTextField txtMaNV;
	private JTextField txtMaKH;
	private JTextField txtMaSP;
	private JTextField txtTonKho;
	
	private JButton btnHuy;
	private JButton btnThem;
	private JButton btnXacNhan;
	private DefaultTableModel dtmCTHD;
	private JTable tblCTHD;
	private JButton btnCalender;
	private JButton btnNV;
	private JButton btnKH;
	private JButton btnSP;
	private JButton btnKMSP;
	private JTextField txtMaKM_CTHD;
	private JTextField txtMaKM;
	private JButton btnKMHD;
	private JTextField txtGiamGia;
	private JTextField txtGiamGia_HD;

	public HoaDonInsert() {
		try {
			this.sanPhamChuaThanhToan = new HashMap<String, Integer>();
			this.lst_CTHD = new ArrayList<ChiTietHoaDonDTO>();
			init();
			addListener();
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init() throws SQLException{
		setBounds(100, 100, 750, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 10));
		setResizable(false);
		setModal(true);

//		============================= CENTER =============================
		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnCenter.setLayout(new BorderLayout());
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		
		// ============================= CENTER =============================
		JPanel pnthongTin = new JPanel();
		pnthongTin.setLayout(new GridLayout(1,2));
		pnCenter.add(pnthongTin, BorderLayout.CENTER);
		
		JPanel pnThongTinLeft = new JPanel();
		pnthongTin.add(pnThongTinLeft);
		pnThongTinLeft.setLayout(null);
		
		JLabel lblHoaDon = new JLabel("Hóa đơn");
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHoaDon.setBounds(116, 10, 120 , 30);
		pnThongTinLeft.add(lblHoaDon);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaHD.setBounds(20, 50, 130, 25);
		pnThongTinLeft.add(lblMaHD);
		
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMssmm");
		String formatted = formatter.format(now);
		
		txtMaHD = new JTextField("HD"+formatted);
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaHD.setBounds(160, 50, 110, 25);
		pnThongTinLeft.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKH.setBounds(20, 85, 130, 25);
		pnThongTinLeft.add(lblMaKH);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNV.setBounds(20, 120, 130, 25);
		pnThongTinLeft.add(lblMaNV);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM.setBounds(20, 155, 130, 25);
		pnThongTinLeft.add(lblMaKM);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayLap.setBounds(20, 190, 130, 25);
		pnThongTinLeft.add(lblNgayLap);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTien.setBounds(20, 225, 130, 25);
		pnThongTinLeft.add(lblTongTien);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKH.setBounds(160, 85, 110, 25);
		pnThongTinLeft.add(txtMaKH);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNV.setBounds(160, 120, 110, 25);
		pnThongTinLeft.add(txtMaNV);
		
		Date currentDate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter2.format(currentDate);
		
		txtNgayLap = new JTextField(formattedDate);
		txtNgayLap.setEditable(false);
		txtNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNgayLap.setBounds(160, 190, 110, 25);
		pnThongTinLeft.add(txtNgayLap);
		txtNgayLap.setColumns(10);
		
		txtTongTien = new JTextField("0");
		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongTien.setBounds(160, 225, 110, 25);
		pnThongTinLeft.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		btnCalender = new JButton("");
		btnCalender.setIcon(new ImageIcon(HoaDonInsert.class.getResource("/Image/calender_icon.png")));
		btnCalender.setBounds(280, 185, 30, 30);
		pnThongTinLeft.add(btnCalender);
		
		btnKH = new JButton("...");
		btnKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKH.setBounds(280, 85, 30, 25);
		pnThongTinLeft.add(btnKH);
		
		btnNV = new JButton("...");
		btnNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNV.setBounds(280, 120, 30, 25);
		pnThongTinLeft.add(btnNV);
		
		txtMaKM = new JTextField();
		txtMaKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKM.setEditable(false);
		txtMaKM.setBounds(160, 155, 110, 25);
		pnThongTinLeft.add(txtMaKM);
		txtMaKM.setColumns(10);
		
		btnKMHD = new JButton("...");
		btnKMHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKMHD.setBounds(280, 155, 30, 25);
		pnThongTinLeft.add(btnKMHD);
		
		txtGiamGia_HD = new JTextField();
		txtGiamGia_HD.setEditable(false);
		txtGiamGia_HD.setText("0");
		txtGiamGia_HD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiamGia_HD.setBounds(280, 225, 50, 25);
		pnThongTinLeft.add(txtGiamGia_HD);
		txtGiamGia_HD.setColumns(10);
		
		JPanel pnThongTinRight = new JPanel();
		pnthongTin.add(pnThongTinRight);
		pnThongTinRight.setLayout(null);
		
		JLabel lblCTHD = new JLabel("Chi tiết hóa đơn");
		lblCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCTHD.setBounds(70, 10, 200, 30);
		pnThongTinRight.add(lblCTHD);
		
		JLabel lblMaHD_CTHD = new JLabel("Mã hóa đơn");
		lblMaHD_CTHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaHD_CTHD.setBounds(10, 50, 130, 25);
		pnThongTinRight.add(lblMaHD_CTHD);
		
		txtMaHD_CTHD = new JTextField("HD"+formatted);
		txtMaHD_CTHD.setEditable(false);
		txtMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaHD_CTHD.setBounds(150, 50, 110, 25);
		pnThongTinRight.add(txtMaHD_CTHD);
		txtMaHD_CTHD.setColumns(10);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSP.setBounds(10, 85, 130, 25);
		pnThongTinRight.add(lblMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setBounds(150, 85, 110, 25);
		pnThongTinRight.add(txtMaSP);
		
		JLabel lblMaKM_CTHD = new JLabel("Mã khuyến mãi");
		lblMaKM_CTHD.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKM_CTHD.setBounds(10, 120, 130, 25);
		pnThongTinRight.add(lblMaKM_CTHD);
		
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDonGia.setBounds(10, 155, 130, 25);
		pnThongTinRight.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonGia.setBounds(150, 155, 110, 25);
		pnThongTinRight.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuong.setBounds(10, 190, 130, 25);
		pnThongTinRight.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setBounds(150, 190, 110, 25);
		pnThongTinRight.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThanhTien.setBounds(10, 225, 130, 25);
		pnThongTinRight.add(lblThanhTien);
		
		txtThanhTien = new JTextField();
		txtThanhTien.setEditable(false);
		txtThanhTien.setBounds(150, 225, 110, 25);
		pnThongTinRight.add(txtThanhTien);
		txtThanhTien.setColumns(10);
		
		btnSP = new JButton("...");
		btnSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSP.setBounds(270, 85, 30, 25);
		pnThongTinRight.add(btnSP);
		
		txtTonKho = new JTextField();
		txtTonKho.setEditable(false);
		txtTonKho.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTonKho.setBounds(270, 190, 50, 25);
		pnThongTinRight.add(txtTonKho);
		txtTonKho.setColumns(10);
		
		btnKMSP = new JButton("...");
		btnKMSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKMSP.setBounds(270, 120, 30, 25);
		pnThongTinRight.add(btnKMSP);
		
		txtMaKM_CTHD = new JTextField();
		txtMaKM_CTHD.setEditable(false);
		txtMaKM_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKM_CTHD.setBounds(150, 120, 110, 25);
		pnThongTinRight.add(txtMaKM_CTHD);
		txtMaKM_CTHD.setColumns(10);
		
		txtGiamGia = new JTextField("0");
		txtGiamGia.setEditable(false);
		txtGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiamGia.setBounds(270, 225, 50, 25);
		pnThongTinRight.add(txtGiamGia);
		txtGiamGia.setColumns(10);
		
		
		// ============================= SOUTH =============================
		dtmCTHD = new DefaultTableModel();
		dtmCTHD.addColumn("Mã hóa đơn");
		dtmCTHD.addColumn("Mã sản phẩm");
		dtmCTHD.addColumn("Mã khuyến mãi");
		dtmCTHD.addColumn("Số lượng");
		dtmCTHD.addColumn("Đơn giá");
		dtmCTHD.addColumn("Thành tiền");
		
		tblCTHD = new JTable(dtmCTHD);
		tblCTHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrPaneCTHD = new JScrollPane(tblCTHD);
		scrPaneCTHD.setPreferredSize(new Dimension(0,150));
		pnCenter.add(scrPaneCTHD, BorderLayout.SOUTH);
		
//		============================= BOTTOM =============================
		JPanel pnBottom = new JPanel();
		pnBottom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnBottom.setPreferredSize(new Dimension(0,40));
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		
		getContentPane().add(pnBottom, BorderLayout.SOUTH);
		
		btnThem = new JButton("Thêm");
		btnThem.setEnabled(false);
		btnThem.setPreferredSize(new Dimension(150, 30));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBottom.add(btnThem);
		
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setEnabled(false);
		btnXacNhan.setPreferredSize(new Dimension(150, 30));
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBottom.add(btnXacNhan);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setPreferredSize(new Dimension(150, 30));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBottom.add(btnHuy);
	}
	
	
	/*
	 * HÀM XỬ LÝ SỰ KIỆN
	 */
	
	private void addListener() {
		
		btnXacNhan.addActionListener(e -> {
			dataAccepted = true;
			dispose();
		});
		
		btnThem.addActionListener(e -> {
			txtMaHD.setEditable(false);
			themCTHD();
			txtMaSP.setText("");
			txtSoLuong.setText("");
			txtTonKho.setText("");
			txtDonGia.setText("");
			txtGiamGia.setText("0");
			txtMaKM_CTHD.setText("");
		});
		
		btnHuy.addActionListener(e ->{
			if(this.lst_CTHD.size() > 0)
				this.lst_CTHD.clear();
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
		
		txtMaHD.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				txtMaHD_CTHD.setText(txtMaHD.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				txtMaHD_CTHD.setText(txtMaHD.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				txtMaHD_CTHD.setText(txtMaHD.getText());
			}
		});
		
		txtSoLuong.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+") && Integer.parseInt(txtSoLuong.getText().toString()) <= Integer.parseInt(txtTonKho.getText().toString()))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+") && Integer.parseInt(txtSoLuong.getText().toString()) <= Integer.parseInt(txtTonKho.getText().toString()))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+") && Integer.parseInt(txtSoLuong.getText().toString()) <= Integer.parseInt(txtTonKho.getText().toString()))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
		});

		btnSP.addActionListener(e ->{
			MySanPham dialog = new MySanPham(this, sanPhamChuaThanhToan);
			if(dialog.showDialog(this)) {
				SanPham_DTO sp = dialog.getSanPham();
				txtMaKM_CTHD.setText("");
				txtGiamGia.setText("0");
				if(sp != null) {
					txtMaSP.setText(sp.getMaSP());
					txtDonGia.setText(sp.getDonGia()+"");
					txtTonKho.setText(sp.getSoLuong()+"");
				}
			}
		});
		
		btnNV.addActionListener(e ->{
			MyNhanVien myNhanVien = new MyNhanVien();
			if(myNhanVien.showDialog(this)) {
				NhanVienDTO nv = myNhanVien.getNhanVien();
				if(nv != null)
					txtMaNV.setText(nv.getMaNV());
			}
		});
		
		btnKH.addActionListener(e ->{
			MyKhachHang myKhachHang = new MyKhachHang();
			if(myKhachHang.showDialog(this)) {
				KhachHangDTO kh = myKhachHang.getKhachHang();
				if(kh != null)
					txtMaKH.setText(kh.getMaKH());
			}
		});
		
		btnCalender.addActionListener(e -> {
			new MyCalender(this, txtNgayLap);
		});
		
		btnKMSP.addActionListener(e ->{
			if(!txtMaSP.getText().isEmpty()) {
				MyKhuyenMaiSanPham myKSMP = new MyKhuyenMaiSanPham(txtMaSP.getText());
				if(myKSMP.showDialog(this)) {
					ChiTietKMSPDTO kmsp = myKSMP.getKMSP();
					txtMaKM_CTHD.setText(kmsp.getMaKM());
					txtGiamGia.setText("-"+kmsp.getTileGiamGia());
				}
			}
		});
		
		btnKMHD.addActionListener(e ->{
			MyKhuyenMaiHoaDon myKMHD = new MyKhuyenMaiHoaDon(Double.parseDouble(txtTongTien.getText()));
			if(myKMHD.showDialog(this)) {
				ChiTietKMHDDTO kmhd = myKMHD.getKMHD();
				txtMaKM.setText(kmhd.getMaKM());
				txtGiamGia_HD.setText("-"+kmhd.getTiLeGiamGia());
			}
		});
		
		// Listener btnThem
		txtMaHD_CTHD.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtDonGia.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtSoLuong.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtThanhTien.getDocument().addDocumentListener(dcmListenerbtnThem);
		
		// Listener btnXacNhan
		txtMaHD.getDocument().addDocumentListener(dcmListenerbtnXacNhan);
		txtMaNV.getDocument().addDocumentListener(dcmListenerbtnXacNhan);
		txtMaKH.getDocument().addDocumentListener(dcmListenerbtnXacNhan);
	}
	
	
	/*
	 *  Function
	 *  
	 */
	
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public void themCTHD() {
		String maHD = txtMaHD_CTHD.getText();
		String maSP = txtMaSP.getText();
		String maKM = txtMaKM_CTHD.getText();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		double donGia = Double.parseDouble(txtDonGia.getText());
		double thanhTien = Double.parseDouble(txtThanhTien.getText()) + Double.parseDouble(txtThanhTien.getText())*Double.parseDouble(txtGiamGia.getText());
		ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(maHD, maSP, maKM, soLuong, donGia, thanhTien);
		Object[] data = new Object[] {maHD, maSP, maKM, soLuong, donGia, thanhTien};		
		txtTongTien.setText(Double.parseDouble(txtTongTien.getText()) + thanhTien+"");
		if(sanPhamChuaThanhToan.containsKey(maSP)) {
			int before = sanPhamChuaThanhToan.get(maSP);
			int after = before + soLuong;
			sanPhamChuaThanhToan.put(maSP, after);
		}else {
			sanPhamChuaThanhToan.put(maSP, soLuong);
		}
		this.lst_CTHD.add(cthd);
		dtmCTHD.addRow(data);
	}

	DocumentListener dcmListenerbtnThem = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		private void updateButtonEnable() {
			btnThem.setEnabled(
					!txtMaHD_CTHD.getText().isEmpty() &&
					!txtSoLuong.getText().isEmpty() &&
					!txtDonGia.getText().isEmpty()&&
					!txtThanhTien.getText().isEmpty()
					);
		}
	};
	
	DocumentListener dcmListenerbtnXacNhan = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			updateButtonEnable();
		}
		private void updateButtonEnable() {
			btnXacNhan.setEnabled(!txtMaHD.getText().isEmpty() && !txtMaKH.getText().isEmpty() && !txtMaNV.getText().isEmpty());
		}
	};
	
	
	public HoaDonDTO getHoaDon() {
		String maHD = txtMaHD.getText();
		String maKH = txtMaKH.getText();
		String maNV = txtMaNV.getText();
		String maKM = txtMaKM.getText();
		Date ngayLap = Date.valueOf(txtNgayLap.getText()+"");
		Double tongTien = Double.parseDouble(txtTongTien.getText()) + Double.parseDouble(txtTongTien.getText())*Double.parseDouble(txtGiamGia_HD.getText());
		
		return new HoaDonDTO(maHD, maKH, maNV, maKM, ngayLap, tongTien);
	}
	
	public ArrayList<ChiTietHoaDonDTO> getCTHD(){
		return this.lst_CTHD;
	}
	

	/*
	 * MAIN
	 */
	
	public static void main(String[] args) {
		try {
			HoaDonInsert dialog = new HoaDonInsert();
			HoaDonDTO hd = dialog.getHoaDon();
			System.out.println(hd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


