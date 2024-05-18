package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTim;
	private JButton btnXuat;
	private JButton btnNhap;
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
		   
		   btnSua = new JButton("Sửa");
		   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		   btnSua.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/edit_icon.png")));
		   btnSua.setBounds(160, 15, 130, 50);
		   btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnSua);
		   
		   btnXoa = new JButton("Xóa");
		   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXoa.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/delete2_icon.png")));
		   btnXoa.setBounds(300, 15, 130, 50);
		   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnXoa);
		   
		   btnTim = new JButton("Tìm");
		   btnTim.setHorizontalAlignment(SwingConstants.LEFT);
		   btnTim.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/32_search.png")));
		   btnTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnTim.setBounds(440, 15, 130, 50);
		   pnTop.add(btnTim);
		   
		   btnXuat = new JButton("Xuất");
		   btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXuat.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/32_excel.png")));
		   btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnXuat.setBounds(580, 15, 130, 50);
		   pnTop.add(btnXuat);
		   
		   btnNhap = new JButton("Nhập");
		   btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
		   btnNhap.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/32_excel.png")));
		   btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnNhap.setBounds(720, 15, 130, 50);
		   pnTop.add(btnNhap);
		   
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
		btnThem.addActionListener(e ->{
			KhuyenMaiInsert data = new KhuyenMaiInsert();
			if(data.showDialog(this)) {
				KhuyenMaiDTO km = data.getKM();
				if(km != null) {
					System.out.println(km.toString());
				}
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	
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
