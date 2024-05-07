package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DTO.NhanVienDTO;

public class NhanVienInsert extends JDialog {

	
	private boolean dataAccepted = false;
	private NhanVienDTO nhanvien;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField text_maNV;
	private JTextField text_hoNV;
	private JTextField text_tenNV;
	private JTextField text_diaChi;
	private JTextField text_ngaySinh;
	private JTextField text_soDienThoai;

	private JButton okButton;
	private ButtonGroup genderButton;
	private JRadioButton rabut_nam;
	private JRadioButton rabut_nu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NhanVienInsert dialog = new NhanVienInsert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NhanVienInsert() {
		init();
		addActionListener();
		setVisible(true);
	}

	private void init() {
		
		setBounds(100, 100, 680, 400);
		setTitle("Thêm Nhân Viên");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		
		text_maNV = new JTextField();
		text_maNV.setBounds(65, 49, 191, 33);
		contentPanel.add(text_maNV);
		text_maNV.setColumns(10);
		
		text_hoNV = new JTextField();
		text_hoNV.setColumns(10);
		text_hoNV.setBounds(65, 112, 191, 33);
		contentPanel.add(text_hoNV);
		
		text_tenNV = new JTextField();
		text_tenNV.setColumns(10);
		text_tenNV.setBounds(65, 184, 191, 33);
		contentPanel.add(text_tenNV);
		
		text_diaChi = new JTextField();
		text_diaChi.setColumns(10);
		text_diaChi.setBounds(65, 260, 191, 33);
		contentPanel.add(text_diaChi);
		
		text_ngaySinh = new JTextField();
		text_ngaySinh.setColumns(10);
		text_ngaySinh.setBounds(360, 49, 191, 33);
		contentPanel.add(text_ngaySinh);
		
		text_soDienThoai = new JTextField();
		text_soDienThoai.setColumns(10);
		text_soDienThoai.setBounds(360, 112, 191, 33);
		contentPanel.add(text_soDienThoai);
		
		JLabel label_maNV = new JLabel("Mã NV");
		label_maNV.setBackground(SystemColor.controlShadow);
		label_maNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_maNV.setBounds(67, 31, 73, 14);
		contentPanel.add(label_maNV);
		
		JLabel label_hoNV = new JLabel("Họ NV");
		label_hoNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_hoNV.setBounds(65, 93, 73, 19);
		contentPanel.add(label_hoNV);
		
		JLabel label_tenNV = new JLabel("Tên NV");
		label_tenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_tenNV.setBounds(65, 166, 73, 14);
		contentPanel.add(label_tenNV);
		
		JLabel label_diaChi = new JLabel("Địa Chỉ");
		label_diaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_diaChi.setBounds(65, 243, 73, 14);
		contentPanel.add(label_diaChi);
		
		JLabel label_ngaySinh = new JLabel("Ngày Sinh");
		label_ngaySinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_ngaySinh.setBounds(360, 30, 88, 17);
		contentPanel.add(label_ngaySinh);
		
		JLabel label_soDienThoai = new JLabel("Số Điện Thoại");
		label_soDienThoai.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_soDienThoai.setBounds(360, 93, 114, 19);
		contentPanel.add(label_soDienThoai);
		
		JLabel label_gioiTinh = new JLabel("Giới Tính :");
		label_gioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_gioiTinh.setBounds(360, 165, 89, 17);
		contentPanel.add(label_gioiTinh);
		
		rabut_nam = new JRadioButton("Nam");
		rabut_nam.setBackground(SystemColor.activeCaption);
		rabut_nam.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nam.setBounds(389, 187, 59, 23);
		contentPanel.add(rabut_nam);
		
		rabut_nu = new JRadioButton("Nữ");
		rabut_nu.setBackground(SystemColor.activeCaption);
		rabut_nu.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nu.setBounds(492, 187, 59, 23);
		contentPanel.add(rabut_nu);
		
		genderButton = new ButtonGroup();
		genderButton.add(rabut_nam);
		genderButton.add(rabut_nu);
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
			this.nhanvien = new NhanVienDTO(
					text_maNV.getText(),
					text_hoNV.getText(),
					text_tenNV.getText(),
					Date.valueOf(text_ngaySinh.getText()),
					getGioiTinh(),
					text_diaChi.getText(),
					text_soDienThoai.getText()
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
	
	public NhanVienDTO getNhanVien() {
		return this.nhanvien;
	}
	
	public boolean getGioiTinh() {
		if(rabut_nam.isSelected())
			return false;
		if(rabut_nu.isSelected())
			return true;
		return true;
	}
}
