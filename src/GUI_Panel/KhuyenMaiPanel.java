package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import BUS.ChiTietKMHDBUS;
import BUS.ChiTietKMSPBUS;
import BUS.KhuyenMaiBUS;
import DTO.ChiTietKMHDDTO;
import DTO.ChiTietKMSPDTO;
import DTO.KhuyenMaiDTO;
import GUI_Dialog.KhachHangInsert;
import GUI_Dialog.KhuyenMaiInsert;

public class KhuyenMaiPanel extends JPanel {
	private KhuyenMaiBUS kmBUS;
	private ChiTietKMHDBUS ctkmhdBUS;
	private ChiTietKMSPBUS ctkmspBUS;
	
	private DefaultTableModel dtmKM;
	private DefaultTableModel dtmKMSP;
	private DefaultTableModel dtmKMHD;
	
	private JTable tblKM;
	private JTable tblKMSP;
	private JTable tblKMHD;
	
	private JButton btnRefresh;
	private JButton btnThem;
	private JButton btnXoa;
	private JScrollPane scrPane_KM;
	private JScrollPane scrPane_KMSP;
	private JComponent scrPane_KMHD;

	
	public KhuyenMaiPanel() {
		try {
			kmBUS = new KhuyenMaiBUS();
			ctkmhdBUS = new ChiTietKMHDBUS();
			ctkmspBUS = new ChiTietKMSPBUS();
			init();
			addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		setSize(1100,700);
		   setLayout(new BorderLayout(5,10));
		   
//		   ============================================= TOP =============================================
		   JPanel pnTop = new JPanel();
		   pnTop.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   pnTop.setPreferredSize(new Dimension(1200,80));
		   add(pnTop, BorderLayout.NORTH);
		   pnTop.setLayout(null);
		   
		   btnThem = new JButton("Thêm");
		   btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		   btnThem.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/add_icon.png")));
		   btnThem.setBounds(20, 15, 130, 50);
		   btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnThem);
		   
		   btnXoa = new JButton("Xóa");
		   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXoa.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/delete2_icon.png")));
		   btnXoa.setBounds(160, 15, 130, 50);
		   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnXoa);
		   
//		   ============================================= CENTER =============================================
		   JPanel pnCenter = new JPanel();
		   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   add(pnCenter, BorderLayout.CENTER);
		   pnCenter.setLayout(null);
		   
		   JLabel lblKhuyenMai = new JLabel("KHUYẾN MÃI");
		   lblKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblKhuyenMai.setBounds(20, 10, 200, 30);
		   pnCenter.add(lblKhuyenMai);
		   
		   
		   dtmKM = new DefaultTableModel();
		   dtmKM.addColumn("Mã khuyến mãi");
		   dtmKM.addColumn("Tên khuyến mãi");
		   dtmKM.addColumn("Điều kiện");
		   dtmKM.addColumn("Ngày bắt đầu");
		   dtmKM.addColumn("Ngày kết thúc");
		   
		   addDataTable_KM(kmBUS.getList_KM());
		   
		   tblKM = new JTable(dtmKM);
		   
		   scrPane_KM = new JScrollPane(tblKM);
		   scrPane_KM.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrPane_KM.setBounds(20, 50, 525, 540);
		   pnCenter.add(scrPane_KM);
		   
		   JLabel lblKMSP = new JLabel("KHUYẾN MÃI SẢN PHẨM");
		   lblKMSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblKMSP.setBounds(555, 10, 300, 30);
		   pnCenter.add(lblKMSP);
		   
		   dtmKMSP = new DefaultTableModel();
		   dtmKMSP.addColumn("Mã khuyến mãi");
		   dtmKMSP.addColumn("Mã sản phẩm");
		   dtmKMSP.addColumn("Tỉ lệ giảm giá");
		   
		   addDataTable_KMSP(ctkmspBUS.getList_CTKMSP());
		   
		   tblKMSP = new JTable(dtmKMSP);
		   
		   scrPane_KMSP = new JScrollPane(tblKMSP);
		   scrPane_KMSP.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrPane_KMSP.setBounds(555, 50, 525, 240);
		   pnCenter.add(scrPane_KMSP);
		   
		   JLabel lblKMHD = new JLabel("KHUYẾN MÃI HÓA ĐƠN");
		   lblKMHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblKMHD.setBounds(555, 300, 300, 30);
		   pnCenter.add(lblKMHD);
		   
		   dtmKMHD = new DefaultTableModel();
		   dtmKMHD.addColumn("Mã khuyến mãi");
		   dtmKMHD.addColumn("Tiền hóa đơn");
		   dtmKMHD.addColumn("Tỉ lệ giảm giá");
		   
		   addDataTable_KMHD(ctkmhdBUS.getList_ctkmhd());
		   
		   tblKMHD = new JTable(dtmKMHD);
		   
		   scrPane_KMHD = new JScrollPane(tblKMHD);
		   scrPane_KMHD.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrPane_KMHD.setBounds(555, 340, 525, 250);
		   pnCenter.add(scrPane_KMHD);
		   
		   btnRefresh = new JButton("");
		   btnRefresh.setToolTipText("Refresh");
		   btnRefresh.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/24_refresh.png")));
		   btnRefresh.setBounds(1050, 10, 30, 30);
		   pnCenter.add(btnRefresh);
		   
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		tblKM.getSelectionModel().addListSelectionListener(e ->{
			if(!e.getValueIsAdjusting()) {
				int selectedRow = tblKM.getSelectedRow();
				if(selectedRow != -1) {
					String maKM = (String) tblKM.getValueAt(selectedRow, tblKM.getColumn("Mã khuyến mãi").getModelIndex());
					filter_tblKMHD(maKM);
					filter_tblKMSP(maKM);
				}
			}
		});
		
		btnThem.addActionListener(e ->{
			KhuyenMaiInsert data = new KhuyenMaiInsert();
			if(data.showDialog(this)) {
				KhuyenMaiDTO km = data.getKM();
				ChiTietKMSPDTO kmsp = data.getKMSP();
				ChiTietKMHDDTO kmhd = data.getKMHD();
				if(km != null) {
					if(kmBUS.them(km))
						addDataTable_KM(km);
				}else if(kmsp != null) {
					System.out.println(kmsp.toString());
					if(ctkmspBUS.them(kmsp))
						addDataTable_KMSP(kmsp);
				}else if(kmhd != null) {
					if(ctkmhdBUS.them(kmhd))
						addDataTable_KMHD(kmhd);
				}
			}
		});
		
		btnXoa.addActionListener(e ->{
			xoa();
		});
	}
	
	/*
	 * FUNCTION
	 */
	
	private void filter_tblKMSP(String selectedMaKM) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtmKMSP);
		tblKMSP.setRowSorter(sorter);
		RowFilter<Object, Object> filter = new RowFilter<Object, Object>(){
			public boolean include(Entry<?,?> entry) {
				String maKM = (String) entry.getValue(0);
				return maKM.equals(selectedMaKM);
			}
		};
		sorter.setRowFilter(filter);
	}
	
	private void filter_tblKMHD(String selectedMaKM) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtmKMHD);
		tblKMHD.setRowSorter(sorter);
		RowFilter<Object,  Object> filter = new RowFilter<Object, Object>(){
			public boolean include(Entry<?,?> entry) {
				String maKM = (String) entry.getValue(0);
				return maKM.equals(selectedMaKM);
			}
		};
		sorter.setRowFilter(filter);
	}
	
	private void addDataTable_KM(KhuyenMaiDTO km) {
		dtmKM.addRow(new Object[] {km.getMaKM(), km.getTenKM(), km.getDieuKien(), km.getNgayBatDau(), km.getNgayKetThuc()});
	}
	
	private void addDataTable_KM(ArrayList<KhuyenMaiDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			KhuyenMaiDTO km = list.get(i);
			addDataTable_KM(km);
		}
	}
	
	private void addDataTable_KMSP(ChiTietKMSPDTO kmsp) {
		dtmKMSP.addRow(new Object[] {kmsp.getMaKM(), kmsp.getMaSP(), kmsp.getTileGiamGia()});
	}
	
	private void addDataTable_KMSP(ArrayList<ChiTietKMSPDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			ChiTietKMSPDTO kmsp = list.get(i);
			addDataTable_KMSP(kmsp);
		}
	}
	
	private void addDataTable_KMHD(ChiTietKMHDDTO kmhd) {
		dtmKMHD.addRow(new Object[] {kmhd.getMaKM(), kmhd.getTienHoaDon(), kmhd.getTiLeGiamGia()});
	}
	
	private void addDataTable_KMHD(ArrayList<ChiTietKMHDDTO> list) {
		for(int i = 0; i < list.size() ;i++) {
			ChiTietKMHDDTO kmhd = list.get(i);
			addDataTable_KMHD(kmhd);
		}
	}
	
	private KhuyenMaiDTO getKM(int selectedRow) {
		String maKM = dtmKM.getValueAt(selectedRow, dtmKM.findColumn("Mã khuyến mãi")).toString();
		String tenKM = dtmKM.getValueAt(selectedRow, dtmKM.findColumn("Tên khuyến mãi")).toString();
		String dieuKien = dtmKM.getValueAt(selectedRow, dtmKM.findColumn("Điều kiện")).toString();
		Date ngayBD = Date.valueOf(dtmKM.getValueAt(selectedRow, dtmKM.findColumn("Ngày bắt đầu")).toString());
		Date ngayKT = Date.valueOf(dtmKM.getValueAt(selectedRow, dtmKM.findColumn("Ngày kết thúc")).toString());
		
		KhuyenMaiDTO km = new KhuyenMaiDTO(maKM, tenKM, dieuKien, ngayBD, ngayKT);
		return km;
	}
	
	private ChiTietKMSPDTO getKMSP(int selectedRow) {
		String maKM = dtmKMSP.getValueAt(selectedRow, dtmKMSP.findColumn("Mã khuyến mãi")).toString();
		String maSP = dtmKMSP.getValueAt(selectedRow, dtmKMSP.findColumn("Mã sản phẩm")).toString();
		Double tiLeGiamGia = Double.parseDouble(dtmKMSP.getValueAt(selectedRow, dtmKMSP.findColumn("Tỉ lệ giảm giá")).toString());
		
		ChiTietKMSPDTO kmsp = new ChiTietKMSPDTO(maKM, maSP, tiLeGiamGia);
		return kmsp;
	}
	
	private ChiTietKMHDDTO getKMHD(int selectedRow) {
		String maKM = dtmKMHD.getValueAt(selectedRow, dtmKMHD.findColumn("Mã khuyến mãi")).toString();
		Double tienHoaDon = Double.parseDouble(dtmKMHD.getValueAt(selectedRow, dtmKMHD.findColumn("Tiền hóa đơn")).toString());
		Double tiLeGiamGia = Double.parseDouble(dtmKMHD.getValueAt(selectedRow, dtmKMHD.findColumn("Tỉ lệ giảm giá")).toString());
		
		ChiTietKMHDDTO kmhd = new ChiTietKMHDDTO(maKM, tienHoaDon, tiLeGiamGia);
		return kmhd;
	}
	
	private void removeRow_tblKM(int selectedRow) {
		KhuyenMaiDTO km = getKM(selectedRow);
		if(kmBUS.xoa(km))
			dtmKM.removeRow(selectedRow);
	}
	
	private void removeRow_tblKMSP(String maKM) {
		for(int i = dtmKMSP.getRowCount()-1; i >= 0; i--) {
			if(maKM.equals( dtmKMSP.getValueAt(i, dtmKMSP.findColumn("Mã khuyến mãi")).toString() )){
				ChiTietKMSPDTO kmsp = getKMSP(i);
				if(ctkmspBUS.xoa(kmsp))
					dtmKMSP.removeRow(i);
			}
		}
	}
	
	private void removeRow_tblKMHD(String maHD) {
		for(int i = dtmKMHD.getRowCount()-1; i >= 0; i--) {
			if(maHD.equals( dtmKMHD.getValueAt(i, dtmKMHD.findColumn("Mã khuyến mãi")).toString())) {
				ChiTietKMHDDTO kmhd = getKMHD(i);
				if(ctkmhdBUS.xoa(kmhd))
					dtmKMHD.removeRow(i);
			}
		}
	}
	
	private void xoa() {
		int selectedRow_tblKM = tblKM.getSelectedRow();
		if(selectedRow_tblKM != -1) {
			KhuyenMaiDTO km = getKM(selectedRow_tblKM);
			System.out.println(km.toString());
			System.out.println("----------------------------");
			removeRow_tblKMSP(km.getMaKM());
			removeRow_tblKMHD(km.getMaKM());
			removeRow_tblKM(selectedRow_tblKM);
		}
	}
	
	/*
	 * MAIN
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					JPanel km = new KhuyenMaiPanel();
					frame.getContentPane().add(km);
					frame.setSize(1100,700);
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
