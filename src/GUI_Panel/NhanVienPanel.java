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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI_Dialog.NhanVienInsert;
import GUI_Dialog.NhanVienUpdate;
import javax.swing.ImageIcon;

public class NhanVienPanel extends JPanel {

	private NhanVienBUS nhanVienBUS;
	
	private DefaultTableModel dtmNhanVien;
	private JTable tblNhanVien;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;

	public NhanVienPanel() {
			try {
				nhanVienBUS = new NhanVienBUS();
				init();
				addActionListener();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	/**
	 * Create the frame.
	 */
	private void init(){
	   setSize(1100,700);
	   setLayout(new BorderLayout(5,10));
	   
//	   ============================================= TOP =============================================
	   JPanel pnTop = new JPanel();
	   pnTop.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   pnTop.setPreferredSize(new Dimension(1200,80));
	   add(pnTop, BorderLayout.NORTH);
	   pnTop.setLayout(null);
	   
	   btnThem = new JButton("Thêm");
	   btnThem.setHorizontalAlignment(SwingConstants.LEFT);
	   btnThem.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnThem.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/add_icon.png")));
	   btnThem.setBounds(20, 15, 130, 50);
	   btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
	   pnTop.add(btnThem);
	   
	   btnSua = new JButton("Sửa");
	   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
	   btnSua.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnSua.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/edit_icon.png")));
	   btnSua.setBounds(160, 15, 130, 50);
	   btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
	   pnTop.add(btnSua);
	   
	   btnXoa = new JButton("Xóa");
	   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
	   btnXoa.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnXoa.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/delete2_icon.png")));
	   btnXoa.setBounds(300, 15, 130, 50);
	   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
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
	   
	   addRow(nhanVienBUS.getList_NV());
	   
	   tblNhanVien = new JTable(dtmNhanVien);
	   tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
	   
	   JScrollPane scrollPane = new JScrollPane(tblNhanVien);
	   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   scrollPane.setBounds(20, 50, 1060, 540);
	   pnCenter.add(scrollPane);
	   
	   
	   JLabel lblNhanVien = new JLabel("NHÂN VIÊN");
	   lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 20));
	   lblNhanVien.setBounds(20, 10, 200, 30);
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
	private void addRow(ArrayList<NhanVienDTO> list) {
		if(list != null) {
			for (NhanVienDTO nv : list) {
				dtmNhanVien.addRow(new Object[] {
						nv.getMaNV(),
						nv.getHoNV(),
						nv.getTenNV(),
						nv.getNgaySinh(),
						nv.isGioiTinh() == false ? "Nam":"Nữ",
						nv.getDiaChi(),
						nv.getSoDienThoai()
				});
			}
		}
	}
	
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
					frame.setSize(1100,700);
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
