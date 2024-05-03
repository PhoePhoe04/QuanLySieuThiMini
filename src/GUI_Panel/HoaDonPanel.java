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
	
	private JButton btnThem;
	private JButton btnSua;
	private DefaultTableModel dtmHoaDon;
	private DefaultTableModel dtmCTHD;
	private ArrayList<HoaDonDTO> list_HD;
	private ArrayList<ChiTietHoaDonDTO> list_CTHD;
	private JButton btnXoa;
	private JTable tblHoaDon;
	private JTable tblCTHD;
	
	
	public HoaDonPanel() {
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
		pnTop.setPreferredSize(new Dimension(1200, 70));
		add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		
		btnThem = new JButton("Thêm");
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/add_icon.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnThem.setPreferredSize(new Dimension(150,50));
		
		pnTop.add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		btnSua.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/edit_icon.png")));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnSua.setPreferredSize(new Dimension(150,50));
		pnTop.add(btnSua);
		
		btnXoa = new JButton("Xóa");
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
		
		addRow();
		
		tblHoaDon = new JTable(dtmHoaDon);
	
		JScrollPane scrPaneHoaDon = new JScrollPane(tblHoaDon);
		scrPaneHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));
		scrPaneHoaDon.setBounds(20, 50, 650, 650);
		pnCenter.add(scrPaneHoaDon);
		
		JLabel lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHoaDon.setBounds(20, 10, 200, 30);
		pnCenter.add(lblHoaDon);
			
		tblCTHD = new JTable(dtmCTHD);
		
		JScrollPane scrPaneChiTietHoaDon = new JScrollPane(tblCTHD);
		scrPaneChiTietHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));;
		scrPaneChiTietHoaDon.setBounds(680, 50, 500, 650);
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
				list_HD.add(hoaDon);
				addRow(hoaDon);
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
	public void addList() {
		this.list_HD = new ArrayList<HoaDonDTO>();
		HoaDonDTO hoaDon_1 = new HoaDonDTO("HD01", "KH01", "NV01", "KM01", Date.valueOf("2004-06-15"), 12233);
		HoaDonDTO hoaDon_2 = new HoaDonDTO("HD02", "KH01", "NV01", "KM01", Date.valueOf("2004-06-15"), 12233);
		list_HD.add(hoaDon_1);
		list_HD.add(hoaDon_2);
		
		this.list_CTHD = new ArrayList<ChiTietHoaDonDTO>();
		ChiTietHoaDonDTO cthd_1_1 = new ChiTietHoaDonDTO("HD01", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_1_2 = new ChiTietHoaDonDTO("HD01", "SP02", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_1_3 = new ChiTietHoaDonDTO("HD01", "SP03", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_1 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_2 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_3 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_1_4 = new ChiTietHoaDonDTO("HD01", "SP05", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_1_5 = new ChiTietHoaDonDTO("HD01", "SP04", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_4 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_5 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_6 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		ChiTietHoaDonDTO cthd_2_7 = new ChiTietHoaDonDTO("HD02", "SP01", "KM01", 4, 100, 400);
		
		list_CTHD.add(cthd_1_1);
		list_CTHD.add(cthd_1_2);
		list_CTHD.add(cthd_1_3);
		list_CTHD.add(cthd_2_1);
		list_CTHD.add(cthd_2_2);
		list_CTHD.add(cthd_2_3);
		list_CTHD.add(cthd_1_4);
		list_CTHD.add(cthd_1_5);
		list_CTHD.add(cthd_2_4);
		list_CTHD.add(cthd_2_5);
		list_CTHD.add(cthd_2_6);
		list_CTHD.add(cthd_2_7);

	}
	
	private void addRow(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> lst_CTHD) {
		
	}
	
	private void addRow() {
		addList();
		
		dtmHoaDon = new DefaultTableModel();
		
		dtmHoaDon.addColumn("Mã hóa đơn");
		dtmHoaDon.addColumn("Mã khách hàng");
		dtmHoaDon.addColumn("Mã nhân viên");
		dtmHoaDon.addColumn("Mã khuyến mãi");
		dtmHoaDon.addColumn("Ngày lập");
		dtmHoaDon.addColumn("Tổng tiền");
		
		for(int i = 0; i < list_HD.size(); i++) {
			HoaDonDTO hoaDon = list_HD.get(i);
			Object[] data = new Object[] {hoaDon.getMaHD(), hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getMaKM(), hoaDon.getNgayLap(), hoaDon.getTongTien()};
			dtmHoaDon.addRow(data);
		}
	
		dtmCTHD = new DefaultTableModel();
		
		dtmCTHD.addColumn("Mã hóa đơn");
		dtmCTHD.addColumn("Mã sản phẩm");
		dtmCTHD.addColumn("Mã khuyến mãi");
		dtmCTHD.addColumn("Số lượng");
		dtmCTHD.addColumn("Đơn giá");
		dtmCTHD.addColumn("Thành tiền");
		
		for(int i = 0; i < list_CTHD.size(); i++) {
			ChiTietHoaDonDTO cthd = list_CTHD.get(i);
			Object[] data = new Object[] {cthd.getMaHD(), cthd.getMaSP(), cthd.getMaKM(), cthd.getSoLuong(), cthd.getDonGia(), cthd.getThanhTien()};
			dtmCTHD.addRow(data);
		}
		
	}
	
	private void addRow(HoaDonDTO hoaDon) {
		dtmHoaDon.addRow(new Object[] {
				hoaDon.getMaHD(), 
				hoaDon.getMaKH(), 
				hoaDon.getMaNV(), hoaDon.getMaKM(), 
				hoaDon.getNgayLap(), 
				hoaDon.getTongTien()
		});
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
