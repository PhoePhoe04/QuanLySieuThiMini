package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI_Dialog.NhanVienInsert;
import GUI_Dialog.NhanVienUpdate;

public class NhanVienPanel extends JPanel {

	private NhanVienBUS nhanVienBUS;
	
	private DefaultTableModel dtmNhanVien;
	private JTable tblNhanVien;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;

	public NhanVienPanel() throws SQLException{
			nhanVienBUS = new NhanVienBUS();
			init();
			addActionListener();
	}
	

	/**
	 * Create the frame.
	 */
	private void init(){
	   setSize(1200,800);
	   setLayout(new BorderLayout(5,10));
	   
//	   ============================================= TOP =============================================
	   JPanel pnTop = new JPanel();
	   pnTop.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   pnTop.setPreferredSize(new Dimension(1200,70));
	   add(pnTop, BorderLayout.NORTH);
	   pnTop.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	   
	   btnThem = new JButton("Thêm");
	   btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
	   btnThem.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnThem);
	   
	   btnSua = new JButton("Sửa");
	   btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
	   btnSua.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnSua);
	   
	   btnXoa = new JButton("Xóa");
	   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
	   btnXoa.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnXoa);
	   
//	   ============================================= CENTER =============================================
	   JPanel pnCenter = new JPanel();
	   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   add(pnCenter, BorderLayout.CENTER);
	   pnCenter.setLayout(null);
	   
	   dtmNhanVien = new DefaultTableModel();
	   dtmNhanVien.addColumn("Mã Nhân Viên");
	   dtmNhanVien.addColumn("Họ");
	   dtmNhanVien.addColumn("Tên");
	   dtmNhanVien.addColumn("Ngày Sinh");
	   dtmNhanVien.addColumn("Địa chỉ");
	   dtmNhanVien.addColumn("Giới tính");
	   dtmNhanVien.addColumn("Số điện thoại");
	
	   
	   ArrayList<NhanVienDTO> list = nhanVienBUS.getList_NV();
	   
	   for(int i = 0; i < list.size(); i++) {
		   NhanVienDTO nv = list.get(i);
		   dtmNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), Date.valueOf(nv.getNgaySinh()+""), nv.isGioiTinh() == false ? "Nam":"Nữ", nv.getDiaChi(), nv.getSoDienThoai()});
	   }
	   
	   tblNhanVien = new JTable(dtmNhanVien);
	   
	   JScrollPane scrollPane = new JScrollPane(tblNhanVien);
	   scrollPane.setBounds(20, 60, 1160, 640);
	   pnCenter.add(scrollPane);
	   
	   
	   JLabel lblNhanVien = new JLabel("NHÂN VIÊN");
	   lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
	   lblNhanVien.setBounds(20, 20, 200, 30);
	   pnCenter.add(lblNhanVien);
	   
	}
	
	/*
	 *  CREATE LISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e ->{
			themNhanVien();
		});
		
		btnSua.addActionListener(e -> {
			suaNhanVien();
		});
		btnXoa.addActionListener(e -> {
			xoaNhanVien();
		});
	}
	
	/*
	 * Function
	 */
	private void themNhanVien() {
		NhanVienInsert data = new NhanVienInsert();
		if(data.showDialog(this)) {
			NhanVienDTO nv = data.getNhanVien();
			try {
				if(nhanVienBUS.them(nv))
					dtmNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), Date.valueOf(nv.getNgaySinh()+""), nv.isGioiTinh() == false ? "Nam":"Nữ", nv.getDiaChi(), nv.getSoDienThoai()});
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void suaNhanVien() {
		int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow != -1) {
        	NhanVienDTO nhanVien = nhanVienBUS.getList_NV().get(selectedRow);
        	NhanVienUpdate data = new NhanVienUpdate(nhanVien);
        	if(data.showDialog(data)) {
        		NhanVienDTO nv = data.getNhanVien();
        		System.out.println(nv.toString());
        		try {
					if(nhanVienBUS.sua(nv)) {
						dtmNhanVien.setValueAt(nv.getHoNV(), selectedRow, 1);
						dtmNhanVien.setValueAt(nv.getTenNV(), selectedRow, 2);
						dtmNhanVien.setValueAt(nv.getNgaySinh(), selectedRow, 3);
						dtmNhanVien.setValueAt(nv.isGioiTinh() == false ? "Nam":"Nữ", selectedRow, 4);
						dtmNhanVien.setValueAt(nv.getDiaChi(), selectedRow, 5);
						dtmNhanVien.setValueAt(nv.getSoDienThoai(), selectedRow, 6);
					}
					System.out.println(nhanVienBUS.sua(nv));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        		
        } else {
        	JOptionPane.showMessageDialog(this, 
					"Bạn chưa nhân viên muốn sửa!"
					);
        }
	}
	
	private void xoaNhanVien() {
		int selectedRow = tblNhanVien.getSelectedRow();
		if(selectedRow != -1) {
			int option = JOptionPane.showConfirmDialog(this, 
					"Bạn có chắc muốn xóa nhân viên này",
					"Xác nhận xóa nhân viên",
					JOptionPane.YES_NO_OPTION
					);
			if(option == JOptionPane.YES_OPTION) {
				NhanVienDTO nv = nhanVienBUS.getList_NV().get(selectedRow);
				try {
					if(nhanVienBUS.xoa(nv))
						dtmNhanVien.removeRow(selectedRow);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên muốn xóa!");
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					NhanVienPanel nv = new NhanVienPanel();					
					frame.getContentPane().add(nv);
					frame.setSize(1200,800);
					frame.setVisible(true);;
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
