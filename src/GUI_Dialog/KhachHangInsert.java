package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DTO.KhachHangDTO;

public class KhachHangInsert extends JDialog {
	
	private boolean dataAccepted = false;
	private KhachHangDTO khachHang;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField text_maKH;
	private JTextField text_hoKH;
	private JTextField text_tenKH;
	private JTextField text_diaChi;
	private JTextField text_ngaySinh;
	private JTextField text_soDienThoai;
	private JTextField text_gmail;

	private JButton okButton;
	private ButtonGroup genderButton;
	private JRadioButton rabut_nam;
	private JRadioButton rabut_nu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KhachHangInsert dialog = new KhachHangInsert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public KhachHangInsert() {
		init();
		addActionListener();
		setVisible(true);
	}

	private void init() {
		
		setBounds(100, 100, 680, 400);
		setTitle("Thêm Khách Hàng");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		
		text_maKH = new JTextField();
		text_maKH.setBounds(65, 49, 191, 33);
		contentPanel.add(text_maKH);
		text_maKH.setColumns(10);
		
		text_hoKH = new JTextField();
		text_hoKH.setColumns(10);
		text_hoKH.setBounds(65, 112, 191, 33);
		contentPanel.add(text_hoKH);
		
		text_tenKH = new JTextField();
		text_tenKH.setColumns(10);
		text_tenKH.setBounds(65, 184, 191, 33);
		contentPanel.add(text_tenKH);
		
		text_diaChi = new JTextField();
		text_diaChi.setColumns(10);
		text_diaChi.setBounds(65, 260, 191, 33);
		contentPanel.add(text_diaChi);
		
		text_ngaySinh = new JTextField();
		text_ngaySinh.setColumns(10);
		text_ngaySinh.setBounds(378, 49, 191, 33);
		contentPanel.add(text_ngaySinh);
		
		text_soDienThoai = new JTextField();
		text_soDienThoai.setColumns(10);
		text_soDienThoai.setBounds(378, 112, 191, 33);
		contentPanel.add(text_soDienThoai);
		
		JLabel label_maKH = new JLabel("Mã KH");
		label_maKH.setBackground(SystemColor.controlShadow);
		label_maKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_maKH.setBounds(67, 31, 73, 14);
		contentPanel.add(label_maKH);
		
		JLabel lblHKh = new JLabel("Họ KH");
		lblHKh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHKh.setBounds(65, 93, 73, 19);
		contentPanel.add(lblHKh);
		
		JLabel lblTnKh = new JLabel("Tên KH");
		lblTnKh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTnKh.setBounds(65, 166, 73, 14);
		contentPanel.add(lblTnKh);
		
		JLabel label_diaChi = new JLabel("Địa Chỉ");
		label_diaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_diaChi.setBounds(65, 243, 73, 14);
		contentPanel.add(label_diaChi);
		
		JLabel label_ngaySinh = new JLabel("Ngày Sinh");
		label_ngaySinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_ngaySinh.setBounds(379, 31, 88, 17);
		contentPanel.add(label_ngaySinh);
		
		JLabel label_soDienThoai = new JLabel("Số Điện Thoại");
		label_soDienThoai.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_soDienThoai.setBounds(378, 93, 114, 19);
		contentPanel.add(label_soDienThoai);
		
		JLabel label_gioiTinh = new JLabel("Giới Tính :");
		label_gioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_gioiTinh.setBounds(378, 242, 89, 17);
		contentPanel.add(label_gioiTinh);
		
		rabut_nam = new JRadioButton("Nam");
		rabut_nam.setBackground(SystemColor.activeCaption);
		rabut_nam.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nam.setBounds(388, 263, 59, 23);
		contentPanel.add(rabut_nam);
		
		rabut_nu = new JRadioButton("Nữ");
		rabut_nu.setBackground(SystemColor.activeCaption);
		rabut_nu.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nu.setBounds(471, 263, 59, 23);
		contentPanel.add(rabut_nu);
		
		genderButton = new ButtonGroup();
		genderButton.add(rabut_nam);
		genderButton.add(rabut_nu);
		
		text_gmail = new JTextField();
		text_gmail.setColumns(10);
		text_gmail.setBounds(378, 190, 191, 33);
		contentPanel.add(text_gmail);
		
		JLabel label_gmail = new JLabel("Gmail");
		label_gmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_gmail.setBounds(378, 168, 88, 17);
		contentPanel.add(label_gmail);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/*
	 * Add actionlister
	 */
	
	private void addActionListener() {
		okButton.addActionListener(e ->{
			this.khachHang = new KhachHangDTO(
					text_maKH.getText(),
					text_hoKH.getText(),
					text_tenKH.getText(),
					getGioiTinh(),
					text_diaChi.getText(),
					text_soDienThoai.getText(),
					text_gmail.getText()
					);
			this.dataAccepted = true;
			dispose();
		});
	}
	
	/*
	 * Function
	 */
	
	public boolean showDialog(Component parentComponent) {
		return this.dataAccepted;
	}
	
	public KhachHangDTO getKhachHang() {
		return this.khachHang;
	}
	
	public boolean getGioiTinh() {
		if(rabut_nam.isSelected())
			return false;
		if(rabut_nu.isSelected())
			return true;
		return true;
	}
}
