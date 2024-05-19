package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DTO.LoaiSP_DTO;
import DTO.SanPham_DTO;

public class SanPhamUpdate extends JDialog {

	private boolean dataAccepted = false;
	
	private JTextField txtMaSP;
	private JTextField txtTenSanPham;
	private JTextField txtMaLSP;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	
	private JButton btnHuy;
	private JButton btnSua;

	private JButton btnMaLSP;
	private JTextField txtDonViTinh;

	public SanPhamUpdate(SanPham_DTO sp) {
		
		try {
			init(sp);
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * CREATE DIALOG
	 */
	private void init(SanPham_DTO sp) {
		setModal(true);
		setSize(310,300);
		getContentPane().setLayout(new BorderLayout(0, 10));
		
		// ---------------- TOP ----------------
		JPanel pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(0,30));
		pnTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		getContentPane().add(pnTop, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("SỬA SẢN PHẨM");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnTop.add(lblTitle);
		
		// --------------- CENTER --------------
		JPanel pnCenter = new JPanel();
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSP.setBounds(10, 0, 130, 20);
		pnCenter.add(lblMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setBounds(150, 0, 100, 20);
		pnCenter.add(txtMaSP);
		txtMaSP.setColumns(10);
		
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenSanPham.setBounds(10, 30, 140, 20);
		pnCenter.add(lblTenSanPham);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenSanPham.setBounds(150, 30, 100, 20);
		pnCenter.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);
		
		JLabel lblMaLSP = new JLabel("Mã loại sản phẩm");
		lblMaLSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaLSP.setBounds(10, 60, 140, 20);
		pnCenter.add(lblMaLSP);
		
		txtMaLSP = new JTextField();
		txtMaLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaLSP.setBounds(150, 60, 100, 20);
		pnCenter.add(txtMaLSP);
		txtMaLSP.setColumns(10);
		
		btnMaLSP = new JButton("...");
		btnMaLSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMaLSP.setBounds(260, 60, 30, 20);
		pnCenter.add(btnMaLSP);
		
		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDonGia.setBounds(10, 90, 140, 20);
		pnCenter.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonGia.setBounds(150, 90, 100, 20);
		pnCenter.add(txtDonGia);
		txtDonGia.setColumns(10);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuong.setBounds(10, 120, 140, 20);
		pnCenter.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setBounds(150, 120, 100, 20);
		pnCenter.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JLabel lblDonViTinh = new JLabel("Đơn vị tính");
		lblDonViTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDonViTinh.setBounds(10, 150, 140, 20);
		pnCenter.add(lblDonViTinh);
		
		txtDonViTinh = new JTextField();
		txtDonViTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonViTinh.setBounds(150, 150, 100, 20);
		pnCenter.add(txtDonViTinh);
		txtDonViTinh.setColumns(10);
		
		// --------------- BOTTOM --------------
		JPanel pnBottom = new JPanel();
		pnBottom.setPreferredSize(new Dimension(0,30));
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		
		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setPreferredSize(new Dimension(80,20));
		pnBottom.add(btnSua);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuy.setPreferredSize(new Dimension(80,20));
		pnBottom.add(btnHuy);
		
		if(sp != null) {
			txtMaSP.setText(sp.getMaSP());
			txtTenSanPham.setText(sp.getTenSP());
			txtMaLSP.setText(sp.getMaLSP());
			txtDonGia.setText(sp.getDonGia()+"");
			txtSoLuong.setText(sp.getSoLuong()+"");
			txtDonViTinh.setText(sp.getDonViTinh());
		}
		
		getContentPane().add(pnBottom, BorderLayout.SOUTH);
	}
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnHuy.addActionListener(e ->{
			dispose();
		});
		
		btnSua.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		btnMaLSP.addActionListener(e ->{
			MyLoaiSanPham myLSP = new MyLoaiSanPham();
			if(myLSP.showDialog(this)) {
				LoaiSP_DTO lsp = myLSP.getLSP();
				txtMaLSP.setText(lsp.getMaLSP());
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public SanPham_DTO getSanPham() {
		return new SanPham_DTO(
				txtMaSP.getText(),
				txtTenSanPham.getText(),
				txtMaLSP.getText(),
				Double.parseDouble(txtDonGia.getText()),
				Integer.parseInt(txtSoLuong.getText()),
				txtDonViTinh.getText()
				);
	}
}
