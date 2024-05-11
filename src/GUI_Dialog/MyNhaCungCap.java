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

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;


public class MyNhaCungCap extends JDialog {
	private boolean dataAccepted = false;
	
	private NhaCungCapBUS nccBUS;
	
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JButton btnTim;
	
	public MyNhaCungCap() {
		try {
			nccBUS = new NhaCungCapBUS();
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
		
		dtm.addColumn("Mã nhà cung cấp");
		dtm.addColumn("Tên");
		dtm.addColumn("Email");
		dtm.addColumn("Địa chỉ");
		
		addRow(nccBUS.getList());
		
		tbl = new JTable(dtm);
		
		scrollPane = new JScrollPane(tbl);
		scrollPane.setBounds(10, 70, 616, 190);
		getContentPane().add(scrollPane);
		
		btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(e ->{
			dispose();
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHuy.setBounds(546, 273, 80, 30);
		getContentPane().add(btnHuy);
		
		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnOK.setBounds(456, 273, 80, 30);
		getContentPane().add(btnOK);
		
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTim.setBounds(170, 20, 150, 30);
		getContentPane().add(txtTim);
		txtTim.setColumns(10);
		
		comboBox = new JComboBox();
		
		comboBox.addItem("Mã nhà cung cấp");
		comboBox.addItem("Tên");
		comboBox.addItem("Email");
		comboBox.addItem("Địa chỉ");
		
		comboBox.setBounds(10, 20, 150, 30);
		getContentPane().add(comboBox);
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 18));
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
			String column = comboBox.getSelectedItem().toString();
			if(column.equals("Mã nhà cung cấp")) {
				dtm.setRowCount(0);
				String condition = " maNCC LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nccBUS.getList(condition));
			}else if(column.equals("Tên")) {
				dtm.setRowCount(0);
				String condition = " tenNCC LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nccBUS.getList(condition));
			}else if(column.equals("Email")) {
				dtm.setRowCount(0);
				String condition = " email LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nccBUS.getList(condition));
			}else if(column.equals("Địa chỉ")) {
				dtm.setRowCount(0);
				String condition = " diaChiNCC LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nccBUS.getList(condition));
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public NhaCungCapDTO getNCC() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new NhaCungCapDTO(
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã nhà cung cấp").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Tên").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Email").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Địa chỉ").getModelIndex()).toString()
					);
		}
		return null;
	}
	
	
	private void addRow(ArrayList<NhaCungCapDTO> list) {
		for (NhaCungCapDTO ncc : list) {
			dtm.addRow(new Object[] {
					ncc.getMaNCC(),
					ncc.getTenNCC(),
					ncc.getEmail(),
					ncc.getDiaChiNCC()
			});
		}
	}
}
