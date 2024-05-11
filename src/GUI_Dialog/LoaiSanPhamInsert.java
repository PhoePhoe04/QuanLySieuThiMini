package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.LoaiSP_DTO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class LoaiSanPhamInsert extends JDialog {
	private boolean dataAccepted = false;
	
	private JTextField txtMaLSP;
	private JTextField txtTenLSP;
	private JButton btnThem;
	private JButton btnHuy;

	public LoaiSanPhamInsert() {
		try {
			init();
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 * CREATE DIALOG
	 */
	private void init() {
		setModal(true);
		setSize(300,200);
		getContentPane().setLayout(new BorderLayout(10,10));
		
		// ------------- TOP -------------
		JPanel pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(0,30));
		getContentPane().add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitle = new JLabel("THÊM LOẠI SẢN PHẨM");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnTop.add(lblTitle);
		// ------------- CENTER -------------
		JPanel pnCenter = new JPanel();
		
		getContentPane().add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		JLabel lblMaLSP = new JLabel("Mã loại sản phẩm");
		lblMaLSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaLSP.setBounds(10, 10, 150, 20);
		pnCenter.add(lblMaLSP);
		
		JLabel lblTenLSP = new JLabel("Tên loại sản phẩm");
		lblTenLSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenLSP.setBounds(10, 50, 150, 20);
		pnCenter.add(lblTenLSP);
		
		txtMaLSP = new JTextField();
		txtMaLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaLSP.setBounds(170, 10, 100, 20);
		pnCenter.add(txtMaLSP);
		txtMaLSP.setColumns(10);
		
		txtTenLSP = new JTextField();
		txtTenLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenLSP.setBounds(170, 50, 100, 20);
		pnCenter.add(txtTenLSP);
		txtTenLSP.setColumns(10);

		// ------------- BOTTOM -------------
		JPanel pnBottom = new JPanel();
		pnBottom.setPreferredSize(new Dimension(0,30));
		getContentPane().add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT,5,0));
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setPreferredSize(new Dimension(80,30));
		pnBottom.add(btnThem);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuy.setPreferredSize(new Dimension(80,30));
		pnBottom.add(btnHuy);
	}
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		
		btnHuy.addActionListener(e ->{
			dispose();
		});
	}
	
	/*
	 * FUNCTION
	 */
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	public LoaiSP_DTO getLSP() {
		return new LoaiSP_DTO(txtMaLSP.getText(), txtTenLSP.getText());
	}
}
