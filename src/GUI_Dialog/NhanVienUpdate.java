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

public class NhanVienUpdate extends JDialog {
	
	private boolean dataAccepted = false;
	private NhanVienDTO nhanvien;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField text_maNV;
	private JTextField text_hoNV;
	private JTextField text_tenNV;
	private JTextField txtDiaChi;
	private JTextField text_ngaySinh;
	private JTextField txtSoDienThoai;
	private JRadioButton rabut_namSua;
	private JRadioButton rabut_nuSua;
	private ButtonGroup genderGroup;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textFieldNgaySinh;

	/**
	 * Create the dialog.
	 */
	
	public NhanVienUpdate(NhanVienDTO nhanVien) {
		init();
		setData(this.nhanvien = nhanVien);
		addActionListener();
		setVisible(true);
	}
	public static void main(String[] args) {
		try {
			NhanVienDTO nhanVien = new NhanVienDTO();
			NhanVienUpdate dialog = new NhanVienUpdate(nhanVien);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addActionListener() {
		cancelButton.addActionListener(e -> {
			dispose();
		});
		okButton.addActionListener(e -> {
			dataAccepted = true;
			dispose();
		});
	}

	private void init() {
		setModal(true);
		setBounds(100, 100, 680, 400);
		setTitle("Sửa Nhân Viên");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		text_maNV = new JTextField();
		text_maNV.setEditable(false);
		text_maNV.setBounds(34, 41, 191, 33);
		contentPanel.add(text_maNV);
		text_maNV.setColumns(10);
		
		text_hoNV = new JTextField();
		text_hoNV.setColumns(10);
		text_hoNV.setBounds(34, 103, 191, 33);
		contentPanel.add(text_hoNV);
		
		text_tenNV = new JTextField();
		text_tenNV.setColumns(10);
		text_tenNV.setBounds(34, 175, 191, 33);
		contentPanel.add(text_tenNV);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(34, 251, 191, 33);
		contentPanel.add(txtDiaChi);
		
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(348, 103, 191, 33);
		contentPanel.add(txtSoDienThoai);
		
		JLabel lblHoNV = new JLabel("Họ NV");
		lblHoNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoNV.setBounds(34, 84, 73, 19);
		contentPanel.add(lblHoNV);
		
		JLabel lblTenNV = new JLabel("Tên NV");
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenNV.setBounds(34, 157, 73, 14);
		contentPanel.add(lblTenNV);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(34, 234, 73, 14);
		contentPanel.add(lblDiaChi);
		
		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoDienThoai.setBounds(348, 84, 114, 19);
		contentPanel.add(lblSoDienThoai);
		
		JLabel label_gioiTinh = new JLabel("Giới Tính :");
		label_gioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_gioiTinh.setBounds(348, 156, 89, 17);
		contentPanel.add(label_gioiTinh);
		
		rabut_namSua = new JRadioButton("Nam");
		rabut_namSua.setBackground(SystemColor.activeCaption);
		rabut_namSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_namSua.setBounds(378, 178, 59, 23);
		contentPanel.add(rabut_namSua);
		
		rabut_nuSua = new JRadioButton("Nữ");
		rabut_nuSua.setBackground(SystemColor.activeCaption);
		rabut_nuSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nuSua.setBounds(480, 178, 59, 23);
		contentPanel.add(rabut_nuSua);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(rabut_namSua);
		genderGroup.add(rabut_nuSua);
		
		JLabel lblMaNVSua = new JLabel("Mã NV");
		lblMaNVSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNVSua.setBounds(34, 22, 73, 19);
		contentPanel.add(lblMaNVSua);
		
		textFieldNgaySinh = new JTextField();
		textFieldNgaySinh.setText((String) null);
		textFieldNgaySinh.setColumns(10);
		textFieldNgaySinh.setBounds(348, 41, 191, 33);
		contentPanel.add(textFieldNgaySinh);
		
		JLabel lblSoNgaySinh = new JLabel("Ngày Sinh");
		lblSoNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoNgaySinh.setBounds(348, 22, 114, 19);
		contentPanel.add(lblSoNgaySinh);
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
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/*
	 * Function
	 */
	private void setData(NhanVienDTO nhanVien) {
		text_maNV.setText(nhanVien.getMaNV());
		text_hoNV.setText(nhanVien.getHoNV());
		text_tenNV.setText(nhanVien.getTenNV());
		textFieldNgaySinh.setText(nhanVien.getNgaySinh()+"");
		if(nhanVien.isGioiTinh())
			rabut_nuSua.setSelected(true);
		else
			rabut_namSua.setSelected(true);
		txtDiaChi.setText(nhanVien.getDiaChi());
		txtSoDienThoai.setText(nhanVien.getSoDienThoai());
	}
	
	public NhanVienDTO getNhanVien() {
		return new NhanVienDTO(
				text_maNV.getText(),
				text_hoNV.getText(),
				text_tenNV.getText(),
				Date.valueOf(textFieldNgaySinh.getText()),
				getGioiTinh(),
				txtDiaChi.getText(),
				txtSoDienThoai.getText()
				);
	}
	
	public boolean getGioiTinh() {
		if(rabut_namSua.isSelected())
			return false;
		return true;
	}
	
	public boolean showDialog(Component parentComponent) {
		return this.dataAccepted;
	}
}
