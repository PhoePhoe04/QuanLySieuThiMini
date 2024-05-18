package GUI_Dialog;

import java.awt.Component;
import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.ChiTietKMSPBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhaCungCapBUS;
import DTO.ChiTietKMSPDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhaCungCapDTO;


public class MyKhuyenMaiSanPham extends JDialog {
	private boolean dataAccepted = false;
	
	private ChiTietKMSPBUS kmspBUS;
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	
	public MyKhuyenMaiSanPham(String maSP) {
		try {
			kmspBUS = new ChiTietKMSPBUS();
			init(maSP);
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(String maSP) {
		setResizable(false);
		setModal(true);
		setSize(650,350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		String query = " maSP = '"+ maSP+"'";
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Mã khuyến mãi");
		dtm.addColumn("Mã sản phẩm");
		dtm.addColumn("Tỉ lệ giảm giá");
		
		
		addDataTable(kmspBUS.getList_CTKMSP(query));
		
		tbl = new JTable(dtm);
		tbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		scrollPane = new JScrollPane(tbl);
		scrollPane.setBounds(10, 10, 616, 250);
		getContentPane().add(scrollPane);
		
		btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(e ->{
			dispose();
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHuy.setBounds(546, 273, 80, 30);
		getContentPane().add(btnHuy);
		
		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnOK.setBounds(456, 273, 80, 30);
		getContentPane().add(btnOK);
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnOK.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
	}
	
	/*
	 * FUNCTION
	 */
	
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	private void addDataTable(ChiTietKMSPDTO kmsp) {
		dtm.addRow(new Object[] {kmsp.getMaKM(), kmsp.getMaSP(), kmsp.getTileGiamGia()});
	}
	
	private void addDataTable(ArrayList<ChiTietKMSPDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			ChiTietKMSPDTO kmsp = list.get(i);
			addDataTable(kmsp);
		}
	}
	
	public ChiTietKMSPDTO getKMSP() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new ChiTietKMSPDTO(
					dtm.getValueAt(selectedRow, dtm.findColumn("Mã khuyến mãi"))+"",
					dtm.getValueAt(selectedRow, dtm.findColumn("Mã sản phẩm"))+"",
					Double.parseDouble(dtm.getValueAt(selectedRow, dtm.findColumn("Tỉ lệ giảm giá"))+"")
					);
		}
		return null;
	}
}
