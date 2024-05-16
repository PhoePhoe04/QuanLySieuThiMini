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

import BUS.ChiTietHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.SanPham_BUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPham_DTO;
import GUI_Dialog.HoaDonInsert;
import GUI_Dialog.HoaDonSearch;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
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
	private SanPham_BUS spBUS;
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
	private JButton btnXuat;
	private JButton btnNhap;
	private JButton btnTKNC;
	
	
	public HoaDonPanel() {
		this.spBUS = new SanPham_BUS();
		this.hoaDonBUS = new HoaDonBUS();
		this.cthdBUS = new ChiTietHoaDonBUS();
		init();
		addActionListener();
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
		
		cbBoxTra = new JComboBox();
		
		cbBoxTra.addItem("");
		cbBoxTra.addItem("Mã hóa đơn");
		cbBoxTra.addItem("Mã khách hàng");
		cbBoxTra.addItem("Mã nhân viên");
		cbBoxTra.addItem("Mã khuyến mãi");
		cbBoxTra.addItem("Ngày lập");
		cbBoxTra.addItem("Tổng tiền");
		
		cbBoxTra.setBounds(840, 25, 80, 30);
		pnTop.add(cbBoxTra);
		
		txtTra = new JTextField();
		txtTra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTra.setBounds(930, 26, 80, 30);
		pnTop.add(txtTra);
		txtTra.setColumns(10);
		
		btnTra = new JButton("Tra");
		btnTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTra.setBounds(1020, 25, 70, 30);
		pnTop.add(btnTra);
		
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
				if(updateDataBase(list))
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
	}
	
	/*
	 *  FUNCTION
	 */
	
	private void addData(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> list_CTHD) {
		if(hoaDon != null) 
			if(hoaDonBUS.them(hoaDon))
				themDataTable_HD(hoaDon);
		if(list_CTHD != null) {
			for (ChiTietHoaDonDTO cthd : list_CTHD) {
				if(cthdBUS.them(cthd))
					themDataTable_CTHD(cthd);
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
				 HoaDonDTO hd = new HoaDonDTO(
						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã hóa đơn").getModelIndex())+"",
						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã khách hàng").getModelIndex())+"",
						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã nhân viên").getModelIndex())+"",
						 tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Mã khuyến mãi").getModelIndex())+"",
						 Date.valueOf(tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Ngày lập").getModelIndex()).toString()),
						 Double.parseDouble(tblHoaDon.getValueAt(i, tblHoaDon.getColumn("Tổng tiền").getModelIndex())+"")
						 );
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
				 ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(
						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã hóa đơn").getModelIndex())+"",
						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã sản phẩm").getModelIndex())+"",
						 tblCTHD.getValueAt(i, tblCTHD.getColumn("Mã khuyến mãi").getModelIndex())+"",
						 Integer.parseInt(tblCTHD.getValueAt(i, tblCTHD.getColumn("Số lượng").getModelIndex())+""),
						 Double.parseDouble(tblCTHD.getValueAt(i, tblCTHD.getColumn("Đơn giá").getModelIndex())+""),
						 Double.parseDouble(tblCTHD.getValueAt(i, tblCTHD.getColumn("Thành tiền").getModelIndex())+"")
						 );
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
