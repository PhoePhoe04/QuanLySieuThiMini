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

public class HoaDonPanel extends JPanel {
	
	private HoaDonBUS hoaDonBUS;
	private ChiTietHoaDonBUS cthdBUS;
	
	private JButton btnThem;
	private JButton btnSua;
	private DefaultTableModel dtmHoaDon;
	private DefaultTableModel dtmCTHD;
	private JButton btnXoa;
	private JTable tblHoaDon;
	private JTable tblCTHD;
	
	
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
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(180, 15, 150, 50);
		btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		btnSua.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/edit_icon.png")));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnSua.setPreferredSize(new Dimension(150,50));
		pnTop.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(340, 15, 150, 50);
		btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoa.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/delete2_icon.png")));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnXoa.setPreferredSize(new Dimension(150,50));
		pnTop.add(btnXoa);
		
		
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
		
		for(int i = 0; i < hoaDonBUS.getList_hoadon().size(); i++) {
			HoaDonDTO hd = hoaDonBUS.getList_hoadon().get(i);
			dtmHoaDon.addRow(new Object[] {hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getMaKM(), hd.getNgayLap(), hd.getTongTien()});
		}
		
		tblHoaDon = new JTable(dtmHoaDon);
	
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
	}
	
	/*
	 *  CÁC HẢM HỖ TRỢ
	 */
	public void addData(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> list_CTHD) {
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
	public void addData(HoaDonDTO hoaDon) {
		addData(hoaDon,null);
	}
	public void addData(ArrayList<ChiTietHoaDonDTO> list_CTHD) {
		addData(null,list_CTHD);
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
	            removeRowFromTblHoaDon(selectedRow);
	            removeRowsFromTblCTHD(maHD);
        	}
        } else {
        	JOptionPane.showMessageDialog(this, 
					"Bạn chưa chọn hóa đơn muốn xóa!"
					);
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
	

	private void removeRowFromTblHoaDon(int rowIndex) {
	    DefaultTableModel dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
	    dtmHoaDon.removeRow(rowIndex);
	}
	
	private void removeRowsFromTblCTHD(String maHD) {
	    DefaultTableModel dtmCTHD = (DefaultTableModel) tblCTHD.getModel();
	    for (int i = dtmCTHD.getRowCount() - 1; i >= 0; i--) {
	        if (maHD.equals(dtmCTHD.getValueAt(i, dtmCTHD.findColumn("Mã hóa đơn")))) {
	            dtmCTHD.removeRow(i);
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
