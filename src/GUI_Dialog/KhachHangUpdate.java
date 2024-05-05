package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DTO.KhachHangDTO;


public class KhachHangUpdate extends JDialog {
	
	private boolean dataAccepted = false;
	private KhachHangDTO khachHang;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaKH;
	private JTextField txtHoKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtGmail;
	private JTextField txtSoDienThoai;
	private JRadioButton rabut_namSua;
	private JRadioButton rabut_nuSua;
	private ButtonGroup genderGroup;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public KhachHangUpdate(KhachHangDTO khachHang) {
		init();
		setData(this.khachHang = khachHang);
		addActionListener();
		setVisible(true);
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
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(34, 41, 191, 33);
		contentPanel.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		txtHoKH = new JTextField();
		txtHoKH.setColumns(10);
		txtHoKH.setBounds(34, 103, 191, 33);
		contentPanel.add(txtHoKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(34, 175, 191, 33);
		contentPanel.add(txtTenKH);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(34, 251, 191, 33);
		contentPanel.add(txtDiaChi);
		
		txtGmail = new JTextField();
		txtGmail.setColumns(10);
		txtGmail.setBounds(373, 102, 191, 33);
		contentPanel.add(txtGmail);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(373, 175, 191, 33);
		contentPanel.add(txtSoDienThoai);
		
		JLabel lblHoKH = new JLabel("Họ KH");
		lblHoKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoKH.setBounds(34, 84, 73, 19);
		contentPanel.add(lblHoKH);
		
		JLabel lblTenKH = new JLabel("Tên KH");
		lblTenKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenKH.setBounds(34, 157, 73, 14);
		contentPanel.add(lblTenKH);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiaChi.setBounds(34, 234, 73, 14);
		contentPanel.add(lblDiaChi);
		
		JLabel lblGmail = new JLabel("Gmail");
		lblGmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGmail.setBounds(374, 84, 88, 17);
		contentPanel.add(lblGmail);
		
		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoDienThoai.setBounds(373, 156, 114, 19);
		contentPanel.add(lblSoDienThoai);
		
		JLabel label_gioiTinh = new JLabel("Giới Tính :");
		label_gioiTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_gioiTinh.setBounds(373, 240, 89, 17);
		contentPanel.add(label_gioiTinh);
		
		rabut_namSua = new JRadioButton("Nam");
		rabut_namSua.setBackground(SystemColor.activeCaption);
		rabut_namSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_namSua.setBounds(383, 261, 59, 23);
		contentPanel.add(rabut_namSua);
		
		rabut_nuSua = new JRadioButton("Nữ");
		rabut_nuSua.setBackground(SystemColor.activeCaption);
		rabut_nuSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		rabut_nuSua.setBounds(466, 261, 59, 23);
		contentPanel.add(rabut_nuSua);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(rabut_namSua);
		genderGroup.add(rabut_nuSua);
		
		JLabel lblMaKHSua = new JLabel("Mã KH");
		lblMaKHSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKHSua.setBounds(34, 22, 73, 19);
		contentPanel.add(lblMaKHSua);
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
	private void setData(KhachHangDTO khachHang) {
		txtMaKH.setText(khachHang.getMaKH());
		txtHoKH.setText(khachHang.getHoKH());
		txtTenKH.setText(khachHang.getTenKH());
		if(khachHang.isGioiTinh())
			rabut_nuSua.setSelected(true);
		else
			rabut_namSua.setSelected(true);
		txtDiaChi.setText(khachHang.getDiaChi());
		txtSoDienThoai.setText(khachHang.getSoDienThoai());
		txtGmail.setText(khachHang.getGmail());
	}
	
	public KhachHangDTO getKhachHang() {
		return new KhachHangDTO(
				txtMaKH.getText(),
				txtHoKH.getText(),
				txtTenKH.getText(),
				getGioiTinh(),
				txtDiaChi.getText(),
				txtSoDienThoai.getText(),
				txtGmail.getText()
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
