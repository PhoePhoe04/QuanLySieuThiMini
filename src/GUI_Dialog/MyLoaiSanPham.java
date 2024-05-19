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

import BUS.LoaiSP_BUS;
import BUS.NhaCungCapBUS;
import DTO.LoaiSP_DTO;
import DTO.NhaCungCapDTO;


public class MyLoaiSanPham extends JDialog {
	private boolean dataAccepted = false;
	
	private LoaiSP_BUS lspBUS;
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	
	public MyLoaiSanPham() {
		try {
			lspBUS = new LoaiSP_BUS();
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
		
		dtm.addColumn("Mã loại sản phẩm");
		dtm.addColumn("Tên loại sản phẩm");
		
		addRow(lspBUS.getList());
		
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
	
	public LoaiSP_DTO getLSP() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new LoaiSP_DTO(
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã loại sản phẩm").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Tên loại sản phẩm").getModelIndex()).toString()
					);
		}
		return null;
	}
	
	
	private void addRow(ArrayList<LoaiSP_DTO> list) {
		for (LoaiSP_DTO lsp : list) {
			dtm.addRow(new Object[] {lsp.getMaLSP(), lsp.getTenLSP()});
		}
	}
}
