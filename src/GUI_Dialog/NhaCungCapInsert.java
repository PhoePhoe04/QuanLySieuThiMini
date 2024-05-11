package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NhaCungCapInsert extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnXacNhan;
	
	int mouseX, mouseY;
	private JButton btnHuy;
	private JTextField txtMaHD;
	private JTextField textField;
	private JTextField textField_1;
	

	public NhaCungCapInsert() {
		init();
		addListener();
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	/*
	 * CREATE DIALOG
	 */
	
	private void init() {
		setBounds(100, 100, 521, 301);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 10));
		setResizable(false);
		setUndecorated(true);

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
		
		JLabel lblThemNhaCungCap = new JLabel("Thêm Nhà Cung Cấp");
		lblThemNhaCungCap.setHorizontalAlignment(SwingConstants.CENTER);
		lblThemNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblThemNhaCungCap.setBounds(140, 10, 233 , 30);
		pnThongTinLeft.add(lblThemNhaCungCap);
		
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp");
		lblMaNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNCC.setBounds(59, 66, 150, 20);
		pnThongTinLeft.add(lblMaNCC);
		
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaHD.setBounds(219, 66, 100, 20);
		pnThongTinLeft.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp");
		lblTenNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNCC.setBounds(59, 106, 150, 20);
		pnThongTinLeft.add(lblTenNCC);
		
		JLabel lblSDTNCC = new JLabel("Số Điện Thoại");
		lblSDTNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSDTNCC.setBounds(59, 146, 120, 20);
		pnThongTinLeft.add(lblSDTNCC);
		
		JLabel lblNgayLap = new JLabel("Địa Chỉ");
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayLap.setBounds(59, 186, 120, 20);
		pnThongTinLeft.add(lblNgayLap);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(219, 106, 100, 20);
		pnThongTinLeft.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(219, 146, 100, 20);
		pnThongTinLeft.add(textField_1);
		
		String[] provinces = {
	            "Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ", 
	            "An Giang", "Bà Rịa - Vũng Tàu", "Bạc Liêu", "Bắc Giang", "Bắc Kạn", 
	            "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", 
	            "Bình Thuận", "Cà Mau", "Cao Bằng", "Đắk Lắk", "Đắk Nông", 
	            "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", 
	            "Hà Nam", "Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình", 
	            "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", 
	            "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", 
	            "Nghệ An", "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Quảng Bình", 
	            "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", 
	            "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", 
	            "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "Trà Vinh", 
	            "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
	        };
		
		JComboBox comboBox = new JComboBox(provinces);
		comboBox.setBounds(219, 186, 100, 21);
		pnThongTinLeft.add(comboBox);
		
		
		
//		============================= BOTTOM =============================
		JPanel pnBottom = new JPanel();
		pnBottom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnBottom.setPreferredSize(new Dimension(0,40));
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		
		getContentPane().add(pnBottom, BorderLayout.SOUTH);
		
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setPreferredSize(new Dimension(150, 30));
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBottom.add(btnXacNhan);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setPreferredSize(new Dimension(150, 30));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnBottom.add(btnHuy);
	}
	
	/*
	 * CREATE ACTIONLISENER
	 */
	
	private void addListener() {
		btnXacNhan.addActionListener(e -> {
			dispose();
		});
		btnHuy.addActionListener(e ->{
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
	
	/*
	 * FUNCTION
	 */

	
	/*
	 * MAIN
	 */
	
	
	public static void main(String[] args) {
		try {
			NhaCungCapInsert dialog = new NhaCungCapInsert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
