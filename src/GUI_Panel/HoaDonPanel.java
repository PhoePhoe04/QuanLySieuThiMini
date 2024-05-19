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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.SanPham_BUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.SanPham_DTO;
import GUI_Dialog.HoaDonInsert;
import GUI_Dialog.HoaDonSearch;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class HoaDonPanel extends JPanel {
	
	String query = "";
	
	private HoaDonBUS hoaDonBUS;
	private KhachHangBUS khachHangBus;
	private SanPham_BUS spBUS;
	private ChiTietHoaDonBUS cthdBUS;
	private NhanVienBUS nvBUS;
	
	private JButton btnThem;
	private DefaultTableModel dtmHoaDon;
	private DefaultTableModel dtmCTHD;
	private JButton btnXoa;
	private JTable tblHoaDon;
	private JTable tblCTHD;
	private JButton btnXuat;
	private JButton btnNhap;
	private JButton btnTKNC;
	private JButton btnRefresh;
	private JButton btnClear;

	private JButton btnPDF;
	
	
	public HoaDonPanel() {
		try {
			this.nvBUS = new NhanVienBUS();
			this.khachHangBus = new KhachHangBUS();
			this.spBUS = new SanPham_BUS();
			this.hoaDonBUS = new HoaDonBUS();
			this.cthdBUS = new ChiTietHoaDonBUS();
			init();
			addActionListener();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 *  KHỞI TẠO PANEL
	 */
	
	private void init() {
		setSize(1100,700);
		setLayout(new BorderLayout(5, 10));
		
//		============================ TOP ============================
		
		JPanel pnTop = new JPanel();
		pnTop.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		pnTop.setPreferredSize(new Dimension(1200, 80));
		add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(null);
		
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(20, 15, 130, 50);
		btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		btnThem.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/add_icon.png")));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnThem.setPreferredSize(new Dimension(150,50));
		
		pnTop.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(160, 15, 130, 50);
		btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoa.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/delete2_icon.png")));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXoa.setPreferredSize(new Dimension(150,50));
		pnTop.add(btnXoa);
		
		btnTKNC = new JButton("Tìm");
		btnTKNC.setHorizontalAlignment(SwingConstants.LEFT);
		btnTKNC.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/32_search.png")));
		btnTKNC.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTKNC.setBounds(300, 15, 130, 50);
		pnTop.add(btnTKNC);
		
		btnXuat = new JButton("Xuất");
		btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnXuat.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/32_excel.png")));
		btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXuat.setBounds(440, 15, 130, 50);
		pnTop.add(btnXuat);
		
		btnNhap = new JButton("Nhập");
		btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
		btnNhap.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/32_excel.png")));
		btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNhap.setBounds(580, 15, 130, 50);
		pnTop.add(btnNhap);
		
		btnPDF = new JButton("PDF");
		btnPDF.setHorizontalAlignment(SwingConstants.LEFT);
		btnPDF.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/32_pdf.png")));
		btnPDF.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPDF.setBounds(720, 15, 130, 50);
		pnTop.add(btnPDF);
		
		
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
		tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 13));
	
		JScrollPane scrPaneHoaDon = new JScrollPane(tblHoaDon);
		scrPaneHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));
		scrPaneHoaDon.setBounds(20, 50, 525, 540);
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
		
		themDataTable_CTHD(cthdBUS.getList_CTHD());
			
		tblCTHD = new JTable(dtmCTHD);
		tblCTHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrPaneChiTietHoaDon = new JScrollPane(tblCTHD);
		scrPaneChiTietHoaDon.setBorder(BorderFactory.createLineBorder(Color.black,2));;
		scrPaneChiTietHoaDon.setBounds(555, 50, 525, 540);
		pnCenter.add(scrPaneChiTietHoaDon);
		
		JLabel lblCTHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblCTHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCTHD.setBounds(555, 10, 200, 30);
		pnCenter.add(lblCTHD);
		
		btnRefresh = new JButton("");
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/24_refresh.png")));
		btnRefresh.setBounds(1055, 10, 30, 30);
		pnCenter.add(btnRefresh);
		
		btnClear = new JButton("");
		btnClear.setToolTipText("Clear");
		btnClear.setIcon(new ImageIcon(HoaDonPanel.class.getResource("/Image/24_clear.png")));
		btnClear.setBounds(1015, 10, 30, 30);
		pnCenter.add(btnClear);
	}
	
	/*
	 * XỬ LÝ SỰ KIỆN
	 */
	private void addActionListener() {
		btnThem.addActionListener(e -> {
			HoaDonInsert dialog = new HoaDonInsert();
			if(dialog.showDialog(HoaDonPanel.this)) {
				HoaDonDTO hoaDon = dialog.getHoaDon();
				System.out.println(hoaDon.toString());
				ArrayList<ChiTietHoaDonDTO> list = dialog.getCTHD();
				if(updateDataBase(list))
					addData(hoaDon, list);
			}
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
		
		btnClear.addActionListener(e ->{
			dtmCTHD.setRowCount(0);
			dtmHoaDon.setRowCount(0);
		});
		
		btnRefresh.addActionListener(e ->{
			dtmCTHD.setRowCount(0);
			dtmHoaDon.setRowCount(0);
			themDataTable_HD(hoaDonBUS.getList_hoadon());
			themDataTable_CTHD(cthdBUS.getList_CTHD());
		});

		btnXuat.addActionListener(e ->{
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setCurrentDirectory(new File("C:\\Users\\Phuc Duy\\eclipse-workspace2"));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File Excel", "xlsx", "xls");
			fileChooser.setFileFilter(filter);
			
			int result = fileChooser.showOpenDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName  = selectedFile.getName();
				
				if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    // File Excel được chọn, thực hiện các hành động mong muốn ở đây
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của hóa đơn vào file: " + selectedFile.getAbsolutePath());
                    xuatFileExcel(selectedFile.getAbsolutePath());
                } else {
                    // File không phải là file Excel, thông báo cho người dùng
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một file Excel.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		
		btnNhap.addActionListener(e ->{
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setCurrentDirectory(new File("C:\\Users\\Phuc Duy\\eclipse-workspace2"));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File Excel", "xlsx", "xls");
			fileChooser.setFileFilter(filter);
			
			int result = fileChooser.showOpenDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName  = selectedFile.getName();
				
				if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    // File Excel được chọn, thực hiện các hành động mong muốn ở đây
                    JOptionPane.showMessageDialog(this, "Đã lấy dữ liệu từ file: " + selectedFile.getAbsolutePath());
                    nhapFileExcel(selectedFile.getAbsolutePath());
                } else {
                    // File không phải là file Excel, thông báo cho người dùng
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một file Excel.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		
		btnTKNC.addActionListener(e ->{
			HoaDonSearch search = new HoaDonSearch();
			if(search.showDialog(this)) {
				String query = search.getQuery();
				System.out.println(query);
				dtmHoaDon.setRowCount(0);
				themDataTable_HD(hoaDonBUS.layDuLieu(query));
			}
		});
		
		btnPDF.addActionListener(e ->{
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setCurrentDirectory(new File("D:\\Hoc"));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File PDF", "pdf");
			fileChooser.setFileFilter(filter);
			
			int selectedRow = tblHoaDon.getSelectedRow();
			if(selectedRow != -1) {
				HoaDonDTO hd = getData_HD(selectedRow);
				ArrayList<ChiTietHoaDonDTO> list = new ArrayList<ChiTietHoaDonDTO>();
				for(int i = dtmCTHD.getRowCount() - 1; i >= 0; i--) {
					if(hd.getMaHD().equals(dtmCTHD.getValueAt(i, dtmCTHD.findColumn("Mã hóa đơn")))) {
						ChiTietHoaDonDTO cthd = getData_CTHD(i);
						list.add(cthd);
					}
					
				}
				int result = fileChooser.showOpenDialog(this);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String fileName  = selectedFile.getName();
					
					if (fileName.endsWith(".pdf")) {
	                    // File Excel được chọn, thực hiện các hành động mong muốn ở đây
	                    JOptionPane.showMessageDialog(this, "Đã in dữ liệu được chọn ra file: " + selectedFile.getAbsolutePath());
	                    xuatPDF(selectedFile.getAbsolutePath(),hd,list);
	                } else {
	                    // File không phải là file Excel, thông báo cho người dùng
	                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một file PDF.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
				}
				;
			}else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		});
	}
	
	/*
	 *  FUNCTION
	 */
	
	private void addData(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> list_CTHD) {
		if(hoaDon != null) {
			if(hoaDonBUS.them(hoaDon)) {
				themDataTable_HD(hoaDon);
				if(list_CTHD != null) {
					for (ChiTietHoaDonDTO cthd : list_CTHD) {
						if(cthdBUS.them(cthd))
							themDataTable_CTHD(cthd);
					}
				}
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
    				removeRowsFromTblCTHD(maHD);
    				removeRowFromTblHoaDon(selectedRow);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        } else {
        	JOptionPane.showMessageDialog(this, 
					"Bạn chưa chọn hóa đơn muốn xóa!"
					);
        }
	}

	private void removeRowFromTblHoaDon(int rowIndex) {
		HoaDonDTO hd = getData_HD(rowIndex);
		System.out.println(hd.toString());
		if(hoaDonBUS.xoa(hd))
			dtmHoaDon.removeRow(rowIndex);
	}
	
	private void removeRowsFromTblCTHD(String maHD) {
		for(int i = dtmCTHD.getRowCount() - 1; i >= 0; i--) {
			if(maHD.equals(dtmCTHD.getValueAt(i, dtmCTHD.findColumn("Mã hóa đơn")))) {
				ChiTietHoaDonDTO cthd = getData_CTHD(i);
				System.out.println(cthd.toString());
				if(cthdBUS.xoa(cthd))
					dtmCTHD.removeRow(i);
			}
		}
	}
	
	private HoaDonDTO getData_HD(int selectedRow) {
		String maHoaDon = dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Mã hóa đơn"))+"";
		String maKhachHang = dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Mã khách hàng"))+"";
		String maNhanVien = dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Mã nhân viên"))+"";
		String maKhuyenMai = dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Mã khuyến mãi")) == null ? null : dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Mã khuyến mãi"))+"";
		Date ngayLap = Date.valueOf(dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Ngày lập"))+"");
		Double tongTien = Double.parseDouble(dtmHoaDon.getValueAt(selectedRow, dtmHoaDon.findColumn("Tổng tiền"))+"");
		
		HoaDonDTO hd = new HoaDonDTO(maHoaDon, maKhachHang, maNhanVien, maKhuyenMai, ngayLap, tongTien);
		
		return hd;
	}
	
	private ChiTietHoaDonDTO getData_CTHD(int selectedRow) {
		String maHoaDon = dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Mã hóa đơn"))+ "";
		String maSanPham = dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Mã sản phẩm"))+"";
		String maKhuyenMai = dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Mã khuyến mãi")) == null ? null : dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Mã khuyến mãi"))+ "";
		int soLuong = Integer.parseInt(dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Số lượng"))+"");
		double donGia = Double.parseDouble(dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Đơn giá"))+"");
		double thanhTien = Double.parseDouble(dtmCTHD.getValueAt(selectedRow, dtmCTHD.findColumn("Thành tiền"))+"");
				
		ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(maHoaDon, maSanPham, maKhuyenMai, soLuong, donGia, thanhTien);
		return cthd;
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
	
	private void themDataTable_HD(HoaDonDTO hd) {
		if(hd !=null)
			dtmHoaDon.addRow(new Object[] {hd.getMaHD(), hd.getMaKH(), hd.getMaNV(), hd.getMaKM(), hd.getNgayLap(), hd.getTongTien()});
	}
	
	private void themDataTable_HD(ArrayList<HoaDonDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			HoaDonDTO hd = list.get(i);
			themDataTable_HD(hd);
		}
	}
	
	private void themDataTable_CTHD(ChiTietHoaDonDTO cthd) {
		if(cthd != null)
			dtmCTHD.addRow(new Object[] {cthd.getMaHD(), cthd.getMaSP(), cthd.getMaKM(), cthd.getDonGia() ,cthd.getSoLuong(), cthd.getThanhTien()});
	}
	
	private void themDataTable_CTHD(ArrayList<ChiTietHoaDonDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			ChiTietHoaDonDTO cthd = list.get(i);
			themDataTable_CTHD(cthd);
		}
	}
	
	private boolean updateDataBase(ArrayList<ChiTietHoaDonDTO> list) {
		ArrayList<SanPham_DTO> list_DataBase = spBUS.getList();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (ChiTietHoaDonDTO cthd : list) {
			if(hm.containsKey(cthd.getMaHD())) {
				int after = hm.get(cthd.getMaHD());
				hm.put(cthd.getMaSP(), cthd.getSoLuong() + after);
			}else {
				hm.put(cthd.getMaSP(), cthd.getSoLuong());
			}
		}
		for (SanPham_DTO sp : list_DataBase) {
			if(hm.containsKey(sp.getMaSP())) {
				sp.setSoLuong(sp.getSoLuong() - hm.get(sp.getMaSP()));
				if(!spBUS.sua(sp)) 
					return false;
			}
		}
		return true;
	}
	
	private void xuatFileExcel(String path) {
		Workbook workbook = null;
		File file = new File(path);
		
		try {
			if(file.exists()) {
				try(FileInputStream fis = new FileInputStream(file)) {
					workbook = WorkbookFactory.create(fis);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
				workbook = new XSSFWorkbook();
			 
			 Sheet hoadon = workbook.getSheet("Hóa Đơn");
			 
			 if(hoadon == null)
				 hoadon = workbook.createSheet("Hóa Đơn");
			 
			 hoadon.setColumnWidth(1, 4000);
			 hoadon.setColumnWidth(2, 4000);
			 hoadon.setColumnWidth(3, 4000);
			 hoadon.setColumnWidth(4, 4000);
			 hoadon.setColumnWidth(5, 4000);
			 hoadon.setColumnWidth(6, 4000);
			 
			 Row tblName = hoadon.createRow(1);
			 
			 Cell column_maHD = tblName.createCell(1);
			 column_maHD.setCellValue("Mã hóa đơn");
			 Cell column_maKH = tblName.createCell(2);
			 column_maKH.setCellValue("Mã khách hàng");
			 Cell column_maNV = tblName.createCell(3);
			 column_maNV.setCellValue("Mã nhân viên");
			 Cell column_maKM = tblName.createCell(4);
			 column_maKM.setCellValue("Mã khuyến mãi");
			 Cell column_ngayLap = tblName.createCell(5);
			 column_ngayLap.setCellValue("Ngày lập");
			 Cell column_tongTien = tblName.createCell(6);
			 column_tongTien.setCellValue("Tổng tiền");
			 
			 for(int i = 0; i < tblHoaDon.getRowCount(); i++) {
//				 HoaDonDTO hd = new HoaDonDTO(
//						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã hóa đơn").getModelIndex())+"",
//						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã khách hàng").getModelIndex())+"",
//						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã nhân viên").getModelIndex())+"",
//						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã khuyến mãi").getModelIndex())+"",
//						 Date.valueOf(tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Ngày lập").getModelIndex()).toString()),
//						 Double.parseDouble(tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Tổng tiền").getModelIndex())+"")
//						 );
				 HoaDonDTO hd = getData_HD(i);
				 
				 Row data = hoadon.createRow(i+2);
				 
				 Cell maHD = data.createCell(1);
				 maHD.setCellValue(hd.getMaHD());
				 
				 Cell maKH = data.createCell(2);
				 maKH.setCellValue(hd.getMaKH());

				 Cell maNV = data.createCell(3);
				 maNV.setCellValue(hd.getMaNV());
				 
				 Cell maKM = data.createCell(4);
				 maKM.setCellValue(hd.getMaKM());
				 
				 Cell ngayLap = data.createCell(5);
				 ngayLap.setCellValue(hd.getNgayLap().toString());
				 
				 Cell tongTien = data.createCell(6);
				 tongTien.setCellValue(hd.getTongTien());
				 
			 }
			 
			 Sheet CTHD = workbook.getSheet("CTHD");
			 if(CTHD == null)
				 CTHD = workbook.createSheet("CTHD");
			 
			 CTHD.setColumnWidth(1, 4000);
			 CTHD.setColumnWidth(2, 4000);
			 CTHD.setColumnWidth(3, 4000);
			 CTHD.setColumnWidth(4, 4000);
			 CTHD.setColumnWidth(5, 4000);
			 CTHD.setColumnWidth(6, 4000);
			 
			 Row tblName_CTHD = CTHD.createRow(1);
			 
			 Cell column_CTHD_maHD = tblName_CTHD.createCell(1);
			 column_CTHD_maHD.setCellValue("Mã hóa đơn");
			 Cell column_CTHD_maSP = tblName_CTHD.createCell(2);
			 column_CTHD_maSP.setCellValue("Mã sản phẩm");
			 Cell column_CTHD_maKM = tblName_CTHD.createCell(3);
			 column_CTHD_maKM.setCellValue("Mã khuyến mãi");
			 Cell column_CTHD_soLuong = tblName_CTHD.createCell(4);
			 column_CTHD_soLuong.setCellValue("Số lượng");
			 Cell column_CTHD_donGia = tblName_CTHD.createCell(5);
			 column_CTHD_donGia.setCellValue("Đơn giá");
			 Cell column_CTHD_thanhTien = tblName_CTHD.createCell(6);
			 column_CTHD_thanhTien.setCellValue("Thành tiền");
			 
			 for(int i = 0; i < tblCTHD.getRowCount(); i++) {
//				 ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(
//						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã hóa đơn").getModelIndex())+"",
//						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã sản phẩm").getModelIndex())+"",
//						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã khuyến mãi").getModelIndex())+"",
//						 Integer.parseInt(tblCTHD.getValueAt(i, tblCTHD.getColumn("Số lượng").getModelIndex())+""),
//						 Double.parseDouble(tblCTHD.getValueAt(i, tblCTHD.getColumn("Đơn giá").getModelIndex())+""),
//						 Double.parseDouble(tblCTHD.getValueAt(i, tblCTHD.getColumn("Thành tiền").getModelIndex())+"")
//						 );
				 ChiTietHoaDonDTO cthd = getData_CTHD(i);
				 
				 Row data = CTHD.createRow(i+2);
				 
				 Cell maHD = data.createCell(1);
				 maHD.setCellValue(cthd.getMaHD());
				 
				 Cell maSP = data.createCell(2);
				 maSP.setCellValue(cthd.getMaSP());

				 Cell maKM = data.createCell(3);
				 maKM.setCellValue(cthd.getMaKM());
				 
				 Cell soLuong = data.createCell(4);
				 soLuong.setCellValue(cthd.getSoLuong());
				 
				 Cell donGia = data.createCell(5);
				 donGia.setCellValue(cthd.getDonGia());
				 
				 Cell thanhTien = data.createCell(6);
				 thanhTien.setCellValue(cthd.getThanhTien());
				 
			 }
			 
			 try(FileOutputStream fos = new FileOutputStream(path)) {
				workbook.write(fos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void nhapFileExcel(String path) {
		String excelFilePath = path;
		try(FileInputStream fis = new FileInputStream(new File(excelFilePath))) {
			Workbook workbook = WorkbookFactory.create(fis);
			
			Sheet hoaDon = workbook.getSheet("Hóa Đơn");
			
			dtmHoaDon.setRowCount(0);
			for (Row row : hoaDon) {
				if(row.getRowNum() < 2)
					continue;
				String maHD = row.getCell(1).getStringCellValue();
				String maKH = row.getCell(2).getStringCellValue();
				String maNV = row.getCell(3).getStringCellValue();
				String maKM = row.getCell(4).getStringCellValue();
				Date ngayLap = Date.valueOf(row.getCell(5).getStringCellValue());
				double tongTien = row.getCell(6).getNumericCellValue();
				
				HoaDonDTO hd = new HoaDonDTO(maHD, maKH, maNV, maKM, ngayLap, tongTien);
				themDataTable_HD(hd);
			}
			
			Sheet CTHD = workbook.getSheet("CTHD");
			
			dtmCTHD.setRowCount(0);
			for(Row row : CTHD) {
				if(row.getRowNum() < 2)
					continue;
				ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						row.getCell(3).getStringCellValue(),
						(int) row.getCell(4).getNumericCellValue(),
						row.getCell(5).getNumericCellValue(),
						row.getCell(6).getNumericCellValue()
						);
				themDataTable_CTHD(cthd);
			}
			
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void xuatPDF(String path, HoaDonDTO hd, ArrayList<ChiTietHoaDonDTO> list) {
		String dest = path;
		
		KhachHangDTO kh = khachHangBus.getKH(hd.getMaKH());
		NhanVienDTO nv = nvBUS.getNV(hd.getMaNV());
		
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
        	
            // Add header
            String fontPath = "C:\\Windows\\Fonts\\times.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);

            Paragraph header = new Paragraph("ĐỒ ÁN NHÓM 11", fontBold);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Paragraph subHeader = new Paragraph("Ngày lập: "+ hd.getNgayLap().toString(), font);
            subHeader.setAlignment(Element.ALIGN_CENTER);
            document.add(subHeader);

            Paragraph maHoaDon = new Paragraph("Mã: "+ hd.getMaHD(), fontBold);
            maHoaDon.setAlignment(Element.ALIGN_LEFT);
            maHoaDon.setFont(font);
            document.add(maHoaDon);

            Paragraph title = new Paragraph("HOÁ ĐƠN", fontBold);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph customerInfo = new Paragraph("Họ tên khách hàng: "+ kh.getHoKH()+" "+kh.getTenKH()
            		+ "\nSố điện thoại: "+ kh.getSoDienThoai()
            		+ "\nĐịa Chỉ: "+ kh.getDiaChi()
            		, font);
            document.add(customerInfo);

            // Create table
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            
            float[] columnWidths = {1f, 3f, 2f, 2f, 2f, 2f,3f};
            table.setWidths(columnWidths);

            // Add table header
            String[] headers = {"TT", "Sản phẩm", "Đơn Vị Tính", "Số Lượng", "Đơn Giá", "Khuyến mãi", "Thành Tiền"};
            for (String headerTitle : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(headerTitle, fontBold));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }

            // Add table rows
            Double tienCTHD = 0.0;
            for(int i = 0; i < list.size(); i++) {
    			ChiTietHoaDonDTO cthd = list.get(i);
				SanPham_DTO sp = spBUS.getSP(cthd.getMaSP());
				System.out.println(cthd.getMaSP());
				table.addCell(new PdfPCell(new Phrase(String.valueOf(i+1), font)));
                table.addCell(new PdfPCell(new Phrase(sp.getTenSP()+"", font)));
                table.addCell(new PdfPCell(new Phrase(sp.getDonViTinh()+"", font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getSoLuong()+"", font)));
                table.addCell(new PdfPCell(new Phrase(sp.getDonGia()+"", font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getMaKM() == null ?"": cthd.getMaKM()+"", font)));
                table.addCell(new PdfPCell(new Phrase(cthd.getThanhTien()+"", font)));
                
                tienCTHD += cthd.getThanhTien();
			}

            // Add total row
            PdfPCell congCell = new PdfPCell(new Phrase("CỘNG:", fontBold));
            congCell.setColspan(6);
            congCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(congCell);
            
            PdfPCell tienCell = new PdfPCell(new Phrase(tienCTHD+"", font));
            tienCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(tienCell);

            document.add(table);
            
            KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
            KhuyenMaiDTO km = kmBUS.getKM(hd.getMaKM());
            
            Paragraph apDungKM = new Paragraph(km.getTenKM() == null ? "Áp dụng khuyến mãi: " : "Áp dụng khuyến mãi: "+km.getTenKM(),font);
            apDungKM.setAlignment(Element.ALIGN_LEFT);
            document.add(apDungKM);
            
            Paragraph tongChiPhiHD = new Paragraph("Tổng chi phí hóa đơn: "+ hd.getTongTien(), font);
            tongChiPhiHD.setAlignment(Element.ALIGN_LEFT);
            document.add(tongChiPhiHD);

            Paragraph nhanVienBanHang = new Paragraph("\nNhân viên bán hàng: "+nv.getHoNV()+" "+nv.getTenNV(),font);
            nhanVienBanHang.setAlignment(Element.ALIGN_LEFT);
            document.add(nhanVienBanHang);
            
            Paragraph camOn = new Paragraph("\n\nCẢM ƠN QUÝ KHÁCH ĐÃ MUA SẢN PHẨM CỦA CHÚNG TÔI",font);
            camOn.setAlignment(Element.ALIGN_CENTER);
            document.add(camOn);
            
            document.close();
            System.out.println("PDF created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
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
					JPanel hoadon = new HoaDonPanel();
					frame.getContentPane().add(hoadon);
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
