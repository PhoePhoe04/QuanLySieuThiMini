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
import DTO.HoaDonDTO;
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
	
	private KhachHangBUS khachHangBUS;
	private NhanVienBUS nhanVienBUS;
	private SanPham_BUS sanPhamBUS;
	
	int mouseX, mouseY;
	private JButton btnHuy;
	private JTextField txtMaHD;
	private JTextField txtNgayLap;
	private JTextField txtTongTien;
	private JTextField txtMaHD_CTHD;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtThanhTien;
	private JButton btnThem;
	private JButton btnXacNhan;
	private JComboBox cbBoxMaKH;
	private JComboBox cbBoxMaNV;
	private JComboBox cbBoxMaKM;
	private JComboBox cbBoxMaSP;
	private JComboBox cbBoxMaKM_CTHD;

	private DefaultTableModel dtmCTHD;

	private JTable tblCTHD;

	private JButton btnCalender;
	

	public HoaDonInsert() {
		try {
			
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
		setBounds(100, 100, 800, 500);
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
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD.setBounds(20, 50, 150, 25);
		pnThongTinLeft.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaHD.setBounds(180, 50, 80, 25);
		pnThongTinLeft.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKH.setBounds(20, 85, 150, 25);
		pnThongTinLeft.add(lblMaKH);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaNV.setBounds(20, 120, 150, 25);
		pnThongTinLeft.add(lblMaNV);
		
		JLabel lblMaKM = new JLabel("Mã khuyến mãi");
		lblMaKM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKM.setBounds(20, 155, 150, 25);
		pnThongTinLeft.add(lblMaKM);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNgayLap.setBounds(20, 190, 150, 25);
		pnThongTinLeft.add(lblNgayLap);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(20, 225, 150, 25);
		pnThongTinLeft.add(lblTongTien);
		
		cbBoxMaKH = new JComboBox();
		this.khachHangBUS = new KhachHangBUS();
		cbBoxMaKH.addItem("");
		for(int i = 0; i < khachHangBUS.getListKH().size(); i++) {
			cbBoxMaKH.addItem(khachHangBUS.getListKH().get(i).getMaKH());
		}
		cbBoxMaKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBoxMaKH.setBounds(180, 85, 80, 25);
		pnThongTinLeft.add(cbBoxMaKH);
		
		cbBoxMaNV = new JComboBox();
		this.nhanVienBUS = new NhanVienBUS();
		cbBoxMaNV.addItem("");
		for(int i = 0; i < nhanVienBUS.getList_NV().size();i++) {
			cbBoxMaNV.addItem(nhanVienBUS.getList_NV().get(i).getMaNV());
		}
		cbBoxMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBoxMaNV.setBounds(180, 120, 80, 25);
		pnThongTinLeft.add(cbBoxMaNV);
		
		cbBoxMaKM = new JComboBox();
		cbBoxMaKM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBoxMaKM.setBounds(180, 155, 80, 25);
		pnThongTinLeft.add(cbBoxMaKM);
		
		Date currentDate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(currentDate);
		
		txtNgayLap = new JTextField(formattedDate);
		txtNgayLap.setEditable(false);
		txtNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNgayLap.setBounds(180, 189, 120, 25);
		pnThongTinLeft.add(txtNgayLap);
		txtNgayLap.setColumns(10);
		
		txtTongTien = new JTextField("0");
		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTongTien.setBounds(180, 225, 120, 25);
		pnThongTinLeft.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		btnCalender = new JButton("");
		btnCalender.setIcon(new ImageIcon(HoaDonInsert.class.getResource("/Image/calender_icon.png")));
		btnCalender.setBounds(310, 185, 32, 32);
		pnThongTinLeft.add(btnCalender);
		
		JPanel pnThongTinRight = new JPanel();
		pnthongTin.add(pnThongTinRight);
		pnThongTinRight.setLayout(null);
		
		JLabel lblCTHD = new JLabel("Chi tiết hóa đơn");
		lblCTHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCTHD.setBounds(70, 10, 200, 30);
		pnThongTinRight.add(lblCTHD);
		
		JLabel lblMaHD_CTHD = new JLabel("Mã hóa đơn");
		lblMaHD_CTHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaHD_CTHD.setBounds(10, 50, 150, 25);
		pnThongTinRight.add(lblMaHD_CTHD);
		
		txtMaHD_CTHD = new JTextField();
		txtMaHD_CTHD.setEditable(false);
		txtMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaHD_CTHD.setBounds(170, 49, 100, 25);
		pnThongTinRight.add(txtMaHD_CTHD);
		txtMaHD_CTHD.setColumns(10);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaSP.setBounds(10, 85, 150, 25);
		pnThongTinRight.add(lblMaSP);
		
		cbBoxMaSP = new JComboBox();
		this.sanPhamBUS = new SanPham_BUS();
		cbBoxMaSP.addItem("");
		for(int i = 0; i < sanPhamBUS.getList().size(); i++) {
			cbBoxMaSP.addItem(sanPhamBUS.getList().get(i).getMaSP());
		}
		cbBoxMaSP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBoxMaSP.setBounds(170, 85, 100, 25);
		pnThongTinRight.add(cbBoxMaSP);
		
		JLabel lblMaKM_CTHD = new JLabel("Mã khuyến mãi");
		lblMaKM_CTHD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMaKM_CTHD.setBounds(10, 120, 150, 25);
		pnThongTinRight.add(lblMaKM_CTHD);
		
		cbBoxMaKM_CTHD = new JComboBox();
		cbBoxMaKM_CTHD.setBounds(170, 120, 100, 25);
		pnThongTinRight.add(cbBoxMaKM_CTHD);
		
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDonGia.setBounds(10, 155, 120, 25);
		pnThongTinRight.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDonGia.setBounds(170, 155, 100, 25);
		pnThongTinRight.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoLuong.setBounds(10, 190, 120, 25);
		pnThongTinRight.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSoLuong.setBounds(170, 189, 100, 25);
		pnThongTinRight.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblThanhTien = new JLabel("Thành tiền");
		lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblThanhTien.setBounds(10, 225, 120, 25);
		pnThongTinRight.add(lblThanhTien);
		
		txtThanhTien = new JTextField();
		txtThanhTien.setEditable(false);
		txtThanhTien.setBounds(170, 224, 100, 25);
		pnThongTinRight.add(txtThanhTien);
		txtThanhTien.setColumns(10);
		
		
		// ============================= SOUTH =============================
		dtmCTHD = new DefaultTableModel();
		dtmCTHD.addColumn("Mã hóa đơn");
		dtmCTHD.addColumn("Mã sản phẩm");
		dtmCTHD.addColumn("Mã khuyến mãi");
		dtmCTHD.addColumn("Đơn giá");
		dtmCTHD.addColumn("Số lượng");
		dtmCTHD.addColumn("Thành tiền");
		
		tblCTHD = new JTable(dtmCTHD);
		tblCTHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
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
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+"))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+"))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txtSoLuong.getText().isEmpty() && txtSoLuong.getText().matches("[0-9]+"))
					txtThanhTien.setText(Integer.parseInt(txtSoLuong.getText()) * Double.parseDouble(txtDonGia.getText())+"");
				else
					txtThanhTien.setText("");
			}
		});
		
		cbBoxMaSP.addActionListener(e -> {
			String maSP = cbBoxMaSP.getSelectedItem().toString();
			SanPham_DTO sp = null;
			for(SanPham_DTO spDTO : sanPhamBUS.getList()) {
				if(spDTO.getMaSP().equals(maSP)) {
					sp = spDTO;
					break;
				}
			}
			if(sp != null) {
				txtDonGia.setText(sp.getDonGia());
				txtSoLuong.setText("");
			}
			else {
				txtDonGia.setText("");
				txtSoLuong.setText("");
			}
				
		});
		
		btnCalender.addActionListener(e -> {
			new MyCalender(this, txtNgayLap);
		});
		
		// Listener btnThem
		txtMaHD_CTHD.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtDonGia.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtSoLuong.getDocument().addDocumentListener(dcmListenerbtnThem);
		txtThanhTien.getDocument().addDocumentListener(dcmListenerbtnThem);
		
		// Listener btnXacNhan
		txtMaHD.getDocument().addDocumentListener(dcmListenerbtnXacNhan);
		cbBoxMaKH.addItemListener(iListenerbtnXacNhan);
		cbBoxMaNV.addItemListener(iListenerbtnXacNhan);
		
	}
	
	
	/*
	 *  CÁC HÀM XỬ LÝ
	 *  
	 */
	
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	
	public void themCTHD() {
		String maHD = txtMaHD_CTHD.getText();
		String maSP = cbBoxMaSP.getSelectedItem()+"";
		String maKM = cbBoxMaKM.getSelectedItem()+"";
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		double donGia = Double.parseDouble(txtDonGia.getText());
		double thanhTien = Double.parseDouble(txtThanhTien.getText());
		ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(maHD, maSP, maKM, soLuong, donGia, thanhTien);
		Object[] data = new Object[] {maHD, maSP, maKM, soLuong, donGia, thanhTien};		
		txtTongTien.setText(Double.parseDouble(txtTongTien.getText()) + Double.parseDouble(txtThanhTien.getText())+"");
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
			btnXacNhan.setEnabled(!txtMaHD.getText().isEmpty() && cbBoxMaKH.getSelectedIndex() > 0 && cbBoxMaNV.getSelectedIndex() > 0 );
		}
	};
	
	ItemListener iListenerbtnXacNhan = new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(cbBoxMaKH.getSelectedIndex() > 0 && cbBoxMaNV.getSelectedIndex() > 0 && !txtMaHD.getText().isEmpty())
				btnXacNhan.setEnabled(true);
			else
				btnXacNhan.setEnabled(false);
		}
	};
	
	public HoaDonDTO getHoaDon() {
		return new HoaDonDTO(
				txtMaHD.getText(),
				cbBoxMaKH.getSelectedItem()+"",
				cbBoxMaNV.getSelectedItem()+"",
				cbBoxMaKM.getSelectedItem()+"",
				Date.valueOf(txtNgayLap.getText()+""),
				Double.parseDouble(txtTongTien.getText())
				);
	}
	
	public ArrayList<ChiTietHoaDonDTO> getCTHD(){
		return this.lst_CTHD;
	}

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


