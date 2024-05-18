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

import BUS.KhuyenMaiBUS;
import BUS.NhaCungCapBUS;
import DTO.KhuyenMaiDTO;
import DTO.NhaCungCapDTO;


public class MyKhuyenMai extends JDialog {
	private boolean dataAccepted = false;
	
	private KhuyenMaiBUS kmBUS;
	
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JButton btnTim;
	
	public MyKhuyenMai() {
		try {
			kmBUS = new KhuyenMaiBUS();
			init();
			addActionListener();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		setResizable(false);
		setModal(true);
		setSize(650,350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Mã khuyến mãi");
		dtm.addColumn("Tên khuyến mãi");
		dtm.addColumn("Điều kiện");
		dtm.addColumn("Ngày bắt đầu");
		dtm.addColumn("Ngày kết thúc");
		
		addDataTable(kmBUS.getList_KM());
		
		tbl = new JTable(dtm);
		tbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		scrollPane = new JScrollPane(tbl);
		scrollPane.setBounds(10, 70, 616, 190);
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
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTim.setBounds(170, 20, 150, 30);
		getContentPane().add(txtTim);
		txtTim.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		
		comboBox.setBounds(10, 20, 150, 30);
		getContentPane().add(comboBox);
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBounds(341, 20, 80, 30);
		getContentPane().add(btnTim);
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnOK.addActionListener(e ->{
			dataAccepted = true;
			dispose();
		});
		
		btnTim.addActionListener(e ->{
			
		});
	}
	
	/*
	 * FUNCTION
	 */
	
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public KhuyenMaiDTO getKM() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new KhuyenMaiDTO(
					dtm.getValueAt(selectedRow, dtm.findColumn("Mã khuyến mãi"))+"",
					dtm.getValueAt(selectedRow, dtm.findColumn("Tên khuyến mãi"))+"",
					dtm.getValueAt(selectedRow, dtm.findColumn("Điều kiện"))+"",
					Date.valueOf(dtm.getValueAt(selectedRow, dtm.findColumn("Ngày bắt đầu"))+""),
					Date.valueOf(dtm.getValueAt(selectedRow, dtm.findColumn("Ngày kết thúc"))+"")
					);
		}
		return null;
	}
	
	private void addDataTable(KhuyenMaiDTO km) {
		dtm.addRow(new Object[] {km.getMaKM(), km.getTenKM(), km.getDieuKien(), km.getNgayBatDau(), km.getNgayKetThuc()});
	}
	
	private void addDataTable(ArrayList<KhuyenMaiDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			KhuyenMaiDTO km = list.get(i);
			addDataTable(km);
		}
	}
	
}
