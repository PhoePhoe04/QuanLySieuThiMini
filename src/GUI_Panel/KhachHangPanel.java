package GUI_Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.KhachHangBUS;
import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import GUI_Dialog.KhachHangInsert;
import GUI_Dialog.KhachHangUpdate;
import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class KhachHangPanel extends JPanel {
	
	private KhachHangBUS khachHangBUS;
	
	private DefaultTableModel dtmKhachHang;
	private JTable tblKhachHang;
	
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;

	public KhachHangPanel() throws SQLException{
		try {
			khachHangBUS = new KhachHangBUS();
			init();
			addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	   pnTop.setPreferredSize(new Dimension(1200,80));
	   add(pnTop, BorderLayout.NORTH);
	   pnTop.setLayout(null);
	   
	   btnThem = new JButton("Thêm");
	   btnThem.setBounds(20, 15, 150, 50);
	   btnThem.setHorizontalAlignment(SwingConstants.LEFT);
	   btnThem.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnThem.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/add_icon.png")));
	   btnThem.setFont(new Font("Tahoma", Font.BOLD, 25));
	   btnThem.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnThem);
	   
	   btnSua = new JButton("Sửa");
	   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
	   btnSua.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnSua.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/edit_icon.png")));
	   btnSua.setBounds(180, 15, 150, 50);
	   btnSua.setFont(new Font("Tahoma", Font.BOLD, 25));
	   btnSua.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnSua);
	   
	   btnXoa = new JButton("Xóa");
	   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
	   btnXoa.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnXoa.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/delete2_icon.png")));
	   btnXoa.setBounds(340, 15, 150, 50);
	   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 25));
	   btnXoa.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnXoa);
	   
//	   ============================================= CENTER =============================================
	   JPanel pnCenter = new JPanel();
	   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   add(pnCenter, BorderLayout.CENTER);
	   pnCenter.setLayout(null);
	   
	   dtmKhachHang = new DefaultTableModel();
	   dtmKhachHang.addColumn("Mã khách hàng");
	   dtmKhachHang.addColumn("Họ");
	   dtmKhachHang.addColumn("Tên");
	   dtmKhachHang.addColumn("Giởi tính");
	   dtmKhachHang.addColumn("Địa chỉ");
	   dtmKhachHang.addColumn("Số điện thoại");
	   dtmKhachHang.addColumn("Gmail");
	   
	   ArrayList<KhachHangDTO> list = khachHangBUS.getListKH();
	   
	   for(int i = 0; i < list.size(); i++) {
		   KhachHangDTO kh = list.get(i);
		   dtmKhachHang.addRow(new Object[] {kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.isGioiTinh() == false ? "Nam":"Nữ", kh.getDiaChi(), kh.getSoDienThoai(), kh.getGmail()});
	   }
	   
	   tblKhachHang = new JTable(dtmKhachHang);
	   
	   JScrollPane scrollPane = new JScrollPane(tblKhachHang);
	   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   scrollPane.setBounds(20, 60, 1160, 640);
	   pnCenter.add(scrollPane);
	   
	   
	   JLabel lblKhachHang = new JLabel("KHÁCH HÀNG");
	   lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 20));
	   lblKhachHang.setBounds(20, 20, 200, 30);
	   pnCenter.add(lblKhachHang);
	   
	}
	
	/*
	 *  CREATE LISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e ->{
			themKhachHang();
		});
		
		btnSua.addActionListener(e -> {
			suaKhachHang();
		});
		
		btnXoa.addActionListener(e -> {
			xoaKhachHang();
		});
	}
	
	/*
	 * Function
	 */
	private void themKhachHang() {
		KhachHangInsert data = new KhachHangInsert();
		if(data.showDialog(this)) {
			KhachHangDTO kh = data.getKhachHang();
			try {
				if(khachHangBUS.them(kh)) {
					dtmKhachHang.addRow(new Object[] {kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.isGioiTinh() == false ? "Nam":"Nữ", kh.getDiaChi(), kh.getSoDienThoai(), kh.getGmail()});
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private void suaKhachHang() {
		int selectedRow = tblKhachHang.getSelectedRow();
        if (selectedRow != -1) {
        	KhachHangDTO khachHang = khachHangBUS.getListKH().get(selectedRow);
        	KhachHangUpdate data = new KhachHangUpdate(khachHang);
        	if(data.showDialog(data)) {
        		KhachHangDTO kh = data.getKhachHang();
        		try {
					if(khachHangBUS.sua(kh)) {
						dtmKhachHang.setValueAt(kh.getHoKH(), selectedRow, 1);
						dtmKhachHang.setValueAt(kh.getTenKH(), selectedRow, 2);
						dtmKhachHang.setValueAt(kh.isGioiTinh() == false ? "Nam":"Nữ", selectedRow, 3);
						dtmKhachHang.setValueAt(kh.getDiaChi(), selectedRow, 4);
						dtmKhachHang.setValueAt(kh.getSoDienThoai(), selectedRow, 5);
						dtmKhachHang.setValueAt(kh.getGmail(), selectedRow, 6);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        		
        } else {
        	JOptionPane.showMessageDialog(this, 
					"Bạn chưa chọn khách hàng muốn sửa!"
					);
        }
	}
	
	private void xoaKhachHang() {
		int selectedRow = tblKhachHang.getSelectedRow();
		if(selectedRow != -1) {
			int option = JOptionPane.showConfirmDialog(this, 
					"Bạn có chắc muốn xóa khách hàng này",
					"Xác nhận xóa khách hàng",
					JOptionPane.YES_NO_OPTION
					);
			if(option == JOptionPane.YES_OPTION) {
				KhachHangDTO kh = khachHangBUS.getListKH().get(selectedRow);
				try {
					if(khachHangBUS.xoa(kh)) 
						dtmKhachHang.removeRow(selectedRow);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(this, 
					"Bạn chưa chọn khách hàng muốn xóa"
					);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setSize(1200,800);
					KhachHangPanel kh = new KhachHangPanel();
					frame.getContentPane().add(kh);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
