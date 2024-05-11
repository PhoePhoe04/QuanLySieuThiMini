package GUI_Dialog;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.SanPham_BUS;
import DTO.KhachHangDTO;
import DTO.SanPham_DTO;

public class MySanPham extends JDialog {
	
	private SanPham_BUS spBUS;
	
	private boolean dataAccepted = false;

	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JButton btnTim;

	public MySanPham(Component parentComponent, HashMap<String, Integer> hm) {
		try {
			spBUS = new SanPham_BUS();
			init(parentComponent, hm);
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MySanPham(Component parentComponent) {
		try {
			spBUS = new SanPham_BUS();
			init(parentComponent, null);
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * CREATE DIALOG
	 */
	private void init(Component parentComponent, HashMap<String, Integer> hm) {
		setResizable(false);
		setModal(true);
		setSize(650,350);
		setLocationRelativeTo(parentComponent);
		getContentPane().setLayout(null);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Mã sản phẩm");
		dtm.addColumn("Tên sản phẩm");
		dtm.addColumn("Mã loại sản phẩm");
		dtm.addColumn("Đơn giá");
		dtm.addColumn("Số lượng");
		dtm.addColumn("Đơn vị tính");
		
		if(hm != null)
			addRow(spBUS.getList(), hm);
		else
			addRow(spBUS.getList());
		
		tbl = new JTable(dtm);
		tbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		scrollPane = new JScrollPane(tbl);
		scrollPane.setBounds(10, 70, 616, 190);
		getContentPane().add(scrollPane);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuy.setBounds(546, 273, 80, 30);
		getContentPane().add(btnHuy);
		
		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOK.setBounds(456, 273, 80, 30);
		getContentPane().add(btnOK);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTim.setBounds(170, 20, 150, 30);
		getContentPane().add(txtTim);
		txtTim.setColumns(10);
		
		comboBox = new JComboBox();
		
		comboBox.setBounds(10, 20, 150, 30);
		getContentPane().add(comboBox);
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(341, 20, 80, 30);
		getContentPane().add(btnTim);
	}
	
	/*
	 * CREATE ACTIONLISTENER
	 */
	
	private void addActionListener() {
		btnOK.addActionListener(e ->{
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
	
	public SanPham_DTO getSanPham() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new SanPham_DTO(
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã sản phẩm").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Tên sản phẩm").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã loại sản phẩm").getModelIndex()).toString(),
					Double.parseDouble(tbl.getValueAt(selectedRow, tbl.getColumn("Đơn giá").getModelIndex()).toString()),
					Integer.parseInt(tbl.getValueAt(selectedRow, tbl.getColumn("Số lượng").getModelIndex()).toString()),
					tbl.getValueAt(selectedRow, tbl.getColumn("Đơn vị tính").getModelIndex()).toString()
					);
		}
		return null;
	}
	
	private void addRow(ArrayList<SanPham_DTO> list) {
		for(SanPham_DTO sanPhamDTO : list) {
			dtm.addRow(new Object[] {
					sanPhamDTO.getMaSP(),
					sanPhamDTO.getTenSP(),
					sanPhamDTO.getMaLSP(),
					sanPhamDTO.getDonGia(),
					sanPhamDTO.getSoLuong(),
					sanPhamDTO.getDonViTinh()
			});
		}
	}
	
	private void addRow(ArrayList<SanPham_DTO> list, HashMap<String, Integer> hm) {
			for(SanPham_DTO sanPhamDTO : list) {
				int soLuong;
				if(hm.containsKey(sanPhamDTO.getMaSP()))
					soLuong = sanPhamDTO.getSoLuong() - hm.get(sanPhamDTO.getMaSP());
				else
					soLuong = sanPhamDTO.getSoLuong();
				dtm.addRow(new Object[] {
						sanPhamDTO.getMaSP(),
						sanPhamDTO.getTenSP(),
						sanPhamDTO.getMaLSP(),
						sanPhamDTO.getDonGia(),
						soLuong,
						sanPhamDTO.getDonViTinh()
			});
		}
	}
}
