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

import BUS.ChiTietKMHDBUS;
import BUS.ChiTietKMSPBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhaCungCapBUS;
import DTO.ChiTietKMHDDTO;
import DTO.ChiTietKMSPDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhaCungCapDTO;


public class MyKhuyenMaiHoaDon extends JDialog {
	private boolean dataAccepted = false;
	
	private ChiTietKMHDBUS kmhdBUS;
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	
	public MyKhuyenMaiHoaDon(double tienHoaDon) {
		try {
			kmhdBUS = new ChiTietKMHDBUS();
			init(tienHoaDon);
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(double tienHoaDon) {
		setResizable(false);
		setModal(true);
		setSize(650,350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		String query = " tienHoaDon <= "+ tienHoaDon;
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Mã khuyến mãi");
		dtm.addColumn("Tiền hóa đơn");
		dtm.addColumn("Tỉ lệ giảm giá");
		
		
		addDataTable(kmhdBUS.getList_ctkmhd(query));
		
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
	
	private void addDataTable(ChiTietKMHDDTO kmhd) {
		dtm.addRow(new Object[] {kmhd.getMaKM(), kmhd.getTienHoaDon(), kmhd.getTiLeGiamGia()});
	}
	
	private void addDataTable(ArrayList<ChiTietKMHDDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			ChiTietKMHDDTO kmhd = list.get(i);
			addDataTable(kmhd);
		}
	}
	
	public ChiTietKMHDDTO getKMHD() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new ChiTietKMHDDTO(
					dtm.getValueAt(selectedRow, dtm.findColumn("Mã khuyến mãi"))+"",
					Double.parseDouble(dtm.getValueAt(selectedRow, dtm.findColumn("Tiền hóa đơn"))+""),
					Double.parseDouble(dtm.getValueAt(selectedRow, dtm.findColumn("Tỉ lệ giảm giá"))+"")
					);
		}
		return null;
	}
}
