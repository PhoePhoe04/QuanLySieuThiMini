package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MyNhanVien extends JDialog {
	private boolean dataAccepted = false;
	
	private NhanVienBUS nvBUS;
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JButton btnTim;

	public MyNhanVien() {
		try {
			nvBUS = new NhanVienBUS();
			init();
			addActionListener();
			setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * CREATE DIALOG
	 */
	private void init() {
		setResizable(false);
		setModal(true);
		setSize(650,350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		dtm = new DefaultTableModel();
		
		dtm.addColumn("Mã nhân viên");
		dtm.addColumn("Họ");
		dtm.addColumn("Tên");
		dtm.addColumn("Ngày sinh");
		dtm.addColumn("Giới tính");
		dtm.addColumn("Địa chỉ");
		dtm.addColumn("Số điện thoại");
		
		addRow(nvBUS.getList_NV());
		
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
		
		comboBox.addItem("Mã nhân viên");
		comboBox.addItem("Họ");
		comboBox.addItem("Tên");
		comboBox.addItem("Ngày sinh");
		comboBox.addItem("Giới tính");
		comboBox.addItem("Địa chỉ");
		comboBox.addItem("Số điện thoại");
		
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
		btnOK.addActionListener(e -> {
			dataAccepted = true;
			dispose();
		});
		
		btnTim.addActionListener(e ->{
			String column = comboBox.getSelectedItem().toString();
			if(column.equals("Mã nhân viên")) {
				dtm.setRowCount(0);
				String condition = " maNV LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}else if(column.equals("Họ")) {
				dtm.setRowCount(0);
				String condition = " hoNV LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}else if(column.equals("Tên")) {
				dtm.setRowCount(0);
				String condition = " tenNV LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}else if(column.equals("Ngày sinh")) {
				dtm.setRowCount(0);
				String condition = " ngaySinh LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}else if(column.equals("Giới tính")) {
				if(txtTim.getText().toString().toLowerCase().equals("nam") || txtTim.getText().toString().equals("0")) {
					dtm.setRowCount(0);
					String condition = " gioiTinh = 0 ";
					addRow(nvBUS.layDuLieu(condition));
				}
				if(txtTim.getText().toString().toLowerCase().equals("nữ") || txtTim.getText().toString().equals("1")) {
					dtm.setRowCount(0);
					String condition = " gioiTinh = 1 ";
					addRow(nvBUS.layDuLieu(condition));
				}
			}else if(column.equals("Địa chỉ")) {
				dtm.setRowCount(0);
				String condition = " diaChi LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}else if(column.equals("Số điện thoại") && txtTim.getText().matches("[0-9]+")) {
				dtm.setRowCount(0);
				String condition = " soDienThoai LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(nvBUS.layDuLieu(condition));
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public NhanVienDTO getNhanVien() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new NhanVienDTO(
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã nhân viên").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Họ").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Tên").getModelIndex()).toString(),
					Date.valueOf(tbl.getValueAt(selectedRow, tbl.getColumn("Ngày sinh").getModelIndex()).toString()),
					tbl.getValueAt(selectedRow, tbl.getColumn("Giới tính").getModelIndex()).toString().equals("Nam") ? false : true,
					tbl.getValueAt(selectedRow, tbl.getColumn("Địa chỉ").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Số điện thoại").getModelIndex()).toString()
					);
		}
		
		return null;
	}
	
	private void addRow(ArrayList<NhanVienDTO> list) {
		for (NhanVienDTO nhanVienDTO : list) {
			dtm.addRow(new Object[] {
					nhanVienDTO.getMaNV(), 
					nhanVienDTO.getHoNV(),
					nhanVienDTO.getTenNV(),
					nhanVienDTO.getNgaySinh(),
					nhanVienDTO.isGioiTinh() == false ? "Nam":"Nữ",
					nhanVienDTO.getDiaChi(),
					nhanVienDTO.getSoDienThoai()
					});
		}
	}
}
