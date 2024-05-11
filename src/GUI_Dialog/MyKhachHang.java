package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;

public class MyKhachHang extends JDialog {
	private boolean dataAccepted = false;
	
	private KhachHangBUS khBUS;
	
	private DefaultTableModel dtm;
	private JTable tbl;

	private JScrollPane scrollPane;
	private JButton btnHuy;
	private JButton btnOK;
	private JTextField txtTim;
	private JComboBox comboBox;
	private JButton btnTim;

	public MyKhachHang() {
		try {
			khBUS = new KhachHangBUS();
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
		
		dtm.addColumn("Mã khách hàng");
		dtm.addColumn("Họ");
		dtm.addColumn("Tên");
		dtm.addColumn("Giới tính");
		dtm.addColumn("Địa chỉ");
		dtm.addColumn("Số điện thoại");
		dtm.addColumn("Gmail");
		
		addRow(khBUS.getListKH());
		
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
		
		comboBox.addItem("Mã khách hàng");
		comboBox.addItem("Họ");
		comboBox.addItem("Tên");
		comboBox.addItem("Giới tính");
		comboBox.addItem("Địa chỉ");
		comboBox.addItem("Số điện thoại");
		comboBox.addItem("Gmail");
		
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
			if(column.equals("Mã khách hàng")) {
				dtm.setRowCount(0);
				String condition = " maKH LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}else if(column.equals("Họ")) {
				dtm.setRowCount(0);
				String condition = " hoKH LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}else if(column.equals("Tên")) {
				dtm.setRowCount(0);
				String condition = " tenKH LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}else if(column.equals("Giới tính")) {
				if(txtTim.getText().toString().toLowerCase().equals("nam") || txtTim.getText().toString().equals("0")) {
					dtm.setRowCount(0);
					String condition = " gioiTinh = 0 ";
					addRow(khBUS.layDuLieu(condition));
				}
				if(txtTim.getText().toString().toLowerCase().equals("nữ") || txtTim.getText().toString().equals("1")) {
					dtm.setRowCount(0);
					String condition = " gioiTinh = 1 ";
					addRow(khBUS.layDuLieu(condition));
				}
			}else if(column.equals("Địa chỉ")) {
				dtm.setRowCount(0);
				String condition = " diaChi LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}else if(column.equals("Số điện thoại")) {
				dtm.setRowCount(0);
				String condition = " soDienThoai LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}else if(column.equals("Gmail")) {
				dtm.setRowCount(0);
				String condition = " gmail LIKE '%"+ txtTim.getText().toString()+ "%'";
				addRow(khBUS.layDuLieu(condition));
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	public boolean showDialog(Component parentComponent) {
		return dataAccepted;
	}
	
	public KhachHangDTO getKhachHang() {
		int selectedRow = tbl.getSelectedRow();
		if(selectedRow != -1) {
			return new KhachHangDTO(
					tbl.getValueAt(selectedRow, tbl.getColumn("Mã khách hàng").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Họ").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Tên").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Giới tính").getModelIndex()).toString().equals("Nam") ? false : true,
					tbl.getValueAt(selectedRow, tbl.getColumn("Địa chỉ").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Số điện thoại").getModelIndex()).toString(),
					tbl.getValueAt(selectedRow, tbl.getColumn("Gmail").getModelIndex()).toString()
					);
		}
		return null;
	}
	
	
	private void addRow(ArrayList<KhachHangDTO> list) {
		for(KhachHangDTO khachHangDTO : list) {
			dtm.addRow(new Object[] {
					khachHangDTO.getMaKH(),
					khachHangDTO.getHoKH(),
					khachHangDTO.getTenKH(),
					khachHangDTO.isGioiTinh() == false ? "Nam": "Nữ",
					khachHangDTO.getDiaChi(),
					khachHangDTO.getSoDienThoai(),
					khachHangDTO.getGmail(),
					});
		}
	}
	
}
