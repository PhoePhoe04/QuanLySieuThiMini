package GUI_Panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import GUI_Dialog.HoaDonInsert;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class HoaDonPanel extends JPanel {
	
	private HoaDonBUS hoaDonBUS;
	private ChiTietHoaDonBUS cthdBUS;
	
	private JButton btnThem;
	private DefaultTableModel dtmHoaDon;
	private DefaultTableModel dtmCTHD;
	private JButton btnXoa;
	private JTable tblHoaDon;
	private JTable tblCTHD;
	private JTextField txtTra;
	private JComboBox cbBoxTra;
	private JButton btnTra;
	
	
	public HoaDonPanel() {
		this.hoaDonBUS = new HoaDonBUS();
		this.cthdBUS = new ChiTietHoaDonBUS();
		init();
		addActionListener();
	}

	/*
	 *  KHỞI TẠO PANEL
	 */
	
	private void init() {
		setSize(1200,800);
		setLayout(new BorderLayout(5, 10));
		
//		============================ TOP ============================
		
		JPanel pnTop = new JPanel();
		pnTop.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnTop.setPreferredSize(new Dimension(1200, 80));
		add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(null);
		
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(20, 15, 150, 50);
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/add_icon.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnThem.setPreferredSize(new Dimension(150,50));
		
		pnTop.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(180, 15, 150, 50);
		btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoa.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/delete2_icon.png")));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnXoa.setPreferredSize(new Dimension(150,50));
		pnTop.add(btnXoa);
		
		JButton btnTKNC = new JButton("Tìm");
		btnTKNC.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnTKNC.setBounds(340, 15, 150, 50);
		pnTop.add(btnTKNC);
		
		cbBoxTra = new JComboBox();
		
		cbBoxTra.addItem("");
		cbBoxTra.addItem("Mã hóa đơn");
		cbBoxTra.addItem("Mã khách hàng");
		cbBoxTra.addItem("Mã nhân viên");
		cbBoxTra.addItem("Mã khuyến mãi");
		cbBoxTra.addItem("Ngày lập");
		cbBoxTra.addItem("Tổng tiền");
		
		cbBoxTra.setBounds(750, 25, 150, 30);
		pnTop.add(cbBoxTra);
		
		txtTra = new JTextField();
		txtTra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTra.setBounds(910, 25, 150, 30);
		pnTop.add(txtTra);
		txtTra.setColumns(10);
		
		btnTra = new JButton("Tra");
		btnTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTra.setBounds(1070, 25, 70, 30);
		pnTop.add(btnTra);
		
		
//		============================ CENTER ============================
		
		JPanel pnCenter = new JPanel();
		pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(null);
		
		dtmHoaDon = new DefaultTableModel();
		dtmHoaDon.addColumn("Mã hóa đơn");
		dtmHoaDon.addColumn("Mã khách hàng");
		dtmHoaDon.addColumn("Mã nhân viên");
		dtmHoaDon.addColumn("Mã khuyến mãi");
		dtmHoaDon.addColumn("Ngày lập");
		dtmHoaDon.addColumn("Tổng tiền");
		
		themDataTable_HD(hoaDonBUS.getList_hoadon());
		
		tblHoaDon = new JTable(dtmHoaDon);
		tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
	
		JScrollPane scrPaneHoaDon = new JScrollPane(tblHoaDon);
		scrPaneHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));
		scrPaneHoaDon.setBounds(20, 50, 650, 640);
		pnCenter.add(scrPaneHoaDon);
		
		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHoaDon.setBounds(20, 10, 200, 30);
		pnCenter.add(lblHoaDon);
		
		dtmCTHD = new DefaultTableModel();
		dtmCTHD.addColumn("Mã hóa đơn");
		dtmCTHD.addColumn("Mã sản phẩm");
		dtmCTHD.addColumn("Mã khuyến mãi");
		dtmCTHD.addColumn("Đơn giá");
		dtmCTHD.addColumn("Số lượng");
		dtmCTHD.addColumn("Thành tiền");
		
		for(int i = 0; i < cthdBUS.getList_CTHD().size(); i++) {
			ChiTietHoaDonDTO cthd = cthdBUS.getList_CTHD().get(i);
			dtmCTHD.addRow(new Object[] {cthd.getMaHD(), cthd.getMaSP(), cthd.getMaKM(), cthd.getSoLuong(), cthd.getDonGia(), cthd.getThanhTien()});
		}
			
		tblCTHD = new JTable(dtmCTHD);
		
		JScrollPane scrPaneChiTietHoaDon = new JScrollPane(tblCTHD);
		scrPaneChiTietHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));;
		scrPaneChiTietHoaDon.setBounds(680, 50, 500, 640);
		pnCenter.add(scrPaneChiTietHoaDon);
		
		JLabel lblCTHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCTHD.setBounds(680, 10, 200, 30);
		pnCenter.add(lblCTHD);
	}
	
	/*
	 * XỬ LÝ SỰ KIỆN
	 */
	private void addActionListener() {
		btnThem.addActionListener(e -> {
			HoaDonInsert dialog = new HoaDonInsert();
			if(dialog.showDialog(HoaDonPanel.this)) {
				HoaDonDTO hoaDon = dialog.getHoaDon();
				ArrayList<ChiTietHoaDonDTO> list = dialog.getCTHD();
				addData(hoaDon, list);
			}
			System.out.println(dialog.showDialog(HoaDonPanel.this));
		});
		
		btnXoa.addActionListener(e ->{
			xoaHD_CTHD();
		});
		
		tblHoaDon.getSelectionModel().addListSelectionListener(e -> {
			if(!e.getValueIsAdjusting()) {
				int selectedRow = tblHoaDon.getSelectedRow();
				if(selectedRow != -1) {
					String maHD = (String) tblHoaDon.getValueAt(selectedRow, tblHoaDon.getColumn("Mã hóa đơn").getModelIndex());
					filter_tblCTHD(maHD);
				}
			}
		});
		
		btnTra.addActionListener(e -> {
			traThongTin();
		});
	}
	
	/*
	 *  FUNCTION
	 */
	
	private void addData(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> list_CTHD) {
		if(hoaDon != null) 
			if(hoaDonBUS.them(hoaDon))
				dtmHoaDon.addRow(new Object[] {hoaDon.getMaHD(), hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getMaKM() == "null" ? "": hoaDon.getMaKM(), hoaDon.getNgayLap(), hoaDon.getTongTien()});
			
		if(list_CTHD != null) {
			for (ChiTietHoaDonDTO cthd : list_CTHD) {
				if(cthdBUS.them(cthd))
					dtmCTHD.addRow(new Object[] {cthd.getMaHD(), cthd.getMaSP(), cthd.getMaKM() == "null" ? "":cthd.getMaKM(), cthd.getSoLuong(), cthd.getDonGia(), cthd.getThanhTien()});
			}
		}
			
	}

	private void xoaHD_CTHD() {
		int selectedRow = tblHoaDon.getSelectedRow();
        if (selectedRow != -1) {
        	int option = JOptionPane.showConfirmDialog(this,
        			"Bạn có chắc muốn xóa hóa đơn này!",
        			"Xác nhận xóa hóa đơn",
        			JOptionPane.YES_NO_OPTION
        			);
        	if(option == JOptionPane.YES_OPTION) {
        		String maHD = (String) tblHoaDon.getValueAt(selectedRow, tblHoaDon.getColumn("Mã hóa đơn").getModelIndex());
        		try {
        			if(xoaData_CTHD(maHD)) {
        				removeRowsFromTblCTHD(maHD);
        				removeRowFromTblHoaDon(selectedRow);
        			}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        } else {
        	JOptionPane.showMessageDialog(this, 
					"Bạn chưa chọn hóa đơn muốn xóa!"
					);
        }
	}
	
	private boolean xoaData_CTHD(String maHD) {
		if(maHD != null) {
			if(!cthdBUS.xoa(maHD))
				return false;
		}
		return true;
	}

	private void removeRowFromTblHoaDon(int rowIndex) {
		HoaDonDTO hd = hoaDonBUS.getList_hoadon().get(rowIndex);
		if(hoaDonBUS.xoa(hd))
			dtmHoaDon.removeRow(rowIndex);
	}
	
	private void removeRowsFromTblCTHD(String maHD) {
	    for (int i = dtmCTHD.getRowCount() - 1; i >= 0; i--) {
	        if (maHD.equals(dtmCTHD.getValueAt(i, dtmCTHD.findColumn("Mã hóa đơn")))) {
	            dtmCTHD.removeRow(i);
	        }
	    }
	}
	
	private void filter_tblCTHD(String selectedMaHD) {
		 DefaultTableModel dtmCTHD = (DefaultTableModel) tblCTHD.getModel();
		 
		 TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtmCTHD);
		 
		 tblCTHD.setRowSorter(sorter);
		 
		 RowFilter<Object, Object> filter = new RowFilter<Object, Object>(){
			 public boolean include(Entry<?, ?> entry) {
				 String maHD = (String) entry.getValue(0);
				 return maHD.equals(selectedMaHD);
			 }
		 };
		 sorter.setRowFilter(filter); 
	}
	
	private void themDataTable_HD(ArrayList<HoaDonDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			HoaDonDTO hd = list.get(i);
			dtmHoaDon.addRow(new Object[] {hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getMaKM(), hd.getNgayLap(), hd.getTongTien()});
		}
	}
	
	private void traThongTin() {
		String column = cbBoxTra.getSelectedItem().toString();
		String condition = null;
		if(column.equals("Mã hóa đơn")) {
			if(!txtTra.getText().toString().equals("")) {
				dtmHoaDon.setRowCount(0);
				condition = " maHD LIKE '%"+ txtTra.getText() + "%'";
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
		}else if(column.equals("Mã khách hàng")) {
			if(!txtTra.getText().toString().equals("")) {
				dtmHoaDon.setRowCount(0);
				condition = " maKH LIKE '%"+ txtTra.getText() + "%'";
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
		}else if(column.equals("Mã nhân viên")) {
			if(!txtTra.getText().toString().equals("")) {
				dtmHoaDon.setRowCount(0);
				condition = " maNV LIKE '%"+ txtTra.getText() + "%'";
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
		}else if(column.equals("Mã khuyến mãi")) {
			if(!txtTra.getText().toString().equals("")) {
				dtmHoaDon.setRowCount(0);
				condition = " maKM LIKE '%"+ txtTra.getText() + "%'";
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
			
		}else if(column.equals("Ngày lập")) {
			if(!txtTra.getText().toString().equals("")) {
				dtmHoaDon.setRowCount(0);
				condition = " ngayLap LIKE '%"+ txtTra.getText() + "%'";
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
		}else if(column.equals("Tổng tiền")) {
			if(txtTra.getText().toString().matches("[0-9]+")) {
				dtmHoaDon.setRowCount(0);
				condition = " tongTien = "+ Double.parseDouble(txtTra.getText().toString());
				ArrayList<HoaDonDTO> list = hoaDonBUS.layDuLieu(condition);
				themDataTable_HD(list);
			}
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					JPanel hoadon = new HoaDonPanel();
					frame.getContentPane().add(hoadon);
					frame.setSize(1200,800);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
