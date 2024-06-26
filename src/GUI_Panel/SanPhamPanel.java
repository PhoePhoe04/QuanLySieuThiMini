package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.SanPham_BUS;
import DTO.KhachHangDTO;
import DTO.LoaiSP_DTO;
import DTO.SanPham_DTO;
import GUI_Dialog.SanPhamInsert;
import GUI_Dialog.SanPhamUpdate;

public class SanPhamPanel extends JPanel {
	private SanPham_BUS spBUS;
	
	private DefaultTableModel dtmSanPham;
	private JTable tblSanPham;
	
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXuat;
	private JButton btnNhap;

	private JButton btnRefresh;
	private JButton btnClear;
	private JButton btnTra;

	private JTextField txtTra;

	private JComboBox comboBox;

	public SanPhamPanel() {
		try {
			spBUS = new SanPham_BUS();
			init();
			addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * CREATE PANEL
	 */
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
		   btnThem.setBounds(20, 15, 130, 50);
		   btnThem.setHorizontalAlignment(SwingConstants.LEFT);
		   btnThem.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/add_icon.png")));
		   btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnThem);
		   
		   btnSua = new JButton("Sửa");
		   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		   btnSua.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/edit_icon.png")));
		   btnSua.setBounds(160, 15, 130, 50);
		   btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnSua);
		   
		   btnXoa = new JButton("Xóa");
		   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXoa.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/delete2_icon.png")));
		   btnXoa.setBounds(300, 15, 130, 50);
		   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnXoa);
		   
		   btnXuat = new JButton("Xuat");
		   btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXuat.setIcon(new ImageIcon(SanPhamPanel.class.getResource("/Image/32_excel.png")));
		   btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnXuat.setBounds(440, 15, 130, 50);
		   pnTop.add(btnXuat);
		   
		   btnNhap = new JButton("Nhập");
		   btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
		   btnNhap.setIcon(new ImageIcon(SanPhamPanel.class.getResource("/Image/32_excel.png")));
		   btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnNhap.setBounds(580, 15, 130, 50);
		   pnTop.add(btnNhap);
		   
		   btnTra = new JButton("Tra");
		   btnTra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		   btnTra.setBounds(1000, 30, 80, 30);
		   pnTop.add(btnTra);
		   
		   txtTra = new JTextField();
		   txtTra.setBounds(870, 30, 120, 30);
		   pnTop.add(txtTra);
		   txtTra.setColumns(10);
		   
		   
		   String[] columnDB = new String[] {"", "Mã sản phẩm", "Tên sản phẩm", "Mã loại sản phẩm", "Đơn giá", "Số lượng","Thành tiền"};
		   comboBox = new JComboBox(columnDB);
		   comboBox.setBounds(760, 30, 100, 30);
		   pnTop.add(comboBox);
		   
//		   ============================================= CENTER =============================================
		   JPanel pnCenter = new JPanel();
		   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   add(pnCenter, BorderLayout.CENTER);
		   pnCenter.setLayout(null);
		   
		   dtmSanPham = new DefaultTableModel();
		   dtmSanPham.addColumn("Mã sản phẩm");
		   dtmSanPham.addColumn("Tên sản phẩm");
		   dtmSanPham.addColumn("Mã loại");
		   dtmSanPham.addColumn("Đơn giá");
		   dtmSanPham.addColumn("Số lượng");
		   dtmSanPham.addColumn("Đơn vị tính");
		   
		   addDataTable(spBUS.getList());
		   
		   tblSanPham = new JTable(dtmSanPham);
		   tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		   
		   JScrollPane scrollPane = new JScrollPane(tblSanPham);
		   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrollPane.setBounds(20, 50, 1060, 540);
		   pnCenter.add(scrollPane);
		   
		   
		   JLabel lblSanPham = new JLabel("SẢN PHẨM");
		   lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblSanPham.setBounds(20, 10, 200, 30);
		   pnCenter.add(lblSanPham);
		   
		   btnRefresh = new JButton("");
		   btnRefresh.setIcon(new ImageIcon(SanPhamPanel.class.getResource("/Image/24_refresh.png")));
		   btnRefresh.setBounds(1050, 10, 30, 30);
		   pnCenter.add(btnRefresh);
		   
		   btnClear = new JButton("");
		   btnClear.setIcon(new ImageIcon(SanPhamPanel.class.getResource("/Image/24_clear.png")));
		   btnClear.setBounds(1010, 10, 30, 30);
		   pnCenter.add(btnClear);
		   
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e ->{
			them();
		});
		
		btnSua.addActionListener(e ->{
			sua();
		});
		
		btnXoa.addActionListener(e ->{
			xoa();
		});
		
		btnClear.addActionListener(e ->{
			dtmSanPham.setRowCount(0);
		});
		
		btnRefresh.addActionListener(e ->{
			dtmSanPham.setRowCount(0);
			addDataTable(spBUS.getList());
		});
		
		btnTra.addActionListener(e ->{
			tra();
		});
		
		btnXuat.addActionListener(e ->{
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setCurrentDirectory(new File("C:\\Users\\Phuc Duy\\eclipse-workspace2"));
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File Excel", "xlsx", "xls");
			fileChooser.setFileFilter(filter);
			
			int result = fileChooser.showOpenDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName = selectedFile.getName();
				
				if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của sản phẩm vào file: " + selectedFile.getAbsolutePath());
                    xuatFileExcel(selectedFile.getAbsolutePath());
                } else {
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
				String fileName = selectedFile.getName();
				
				if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                    JOptionPane.showMessageDialog(this, "Đã lấy dữ liệu từ file: " + selectedFile.getAbsolutePath());
                    nhapFileExcel(selectedFile.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một file Excel.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	private void addDataTable(SanPham_DTO sp) {
		dtmSanPham.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getMaLSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDonViTinh()});
	}
	
	private void addDataTable(ArrayList<SanPham_DTO> list) {
		for(int i = 0; i < list.size(); i++) {
			SanPham_DTO sp = list.get(i);
			addDataTable(sp);
		}
	}
	
	private void them() {
		SanPhamInsert data = new SanPhamInsert();
		if(data.showDialog(this)) {
			SanPham_DTO sp = data.getSanPham();
			if(spBUS.them(sp)) 
				addDataTable(sp);
		}
	}
	
	private void sua() {
		int selectedRow = tblSanPham.getSelectedRow();
		if(selectedRow != -1) {
			SanPham_DTO sp = new SanPham_DTO(
					tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex()).toString(),
					tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex()).toString(),
					tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex()).toString(),
					Double.parseDouble(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex()).toString()),
					Integer.parseInt(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex()).toString()),
					tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex()).toString()
					);
			SanPhamUpdate dataUpdated = new SanPhamUpdate(sp);
			if(dataUpdated.showDialog(this)) {
				SanPham_DTO spUpdated = dataUpdated.getSanPham();
				if(spBUS.sua(spUpdated)) {
					dtmSanPham.setValueAt(spUpdated.getMaSP(), selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex());
					dtmSanPham.setValueAt(spUpdated.getTenSP(), selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex());
					dtmSanPham.setValueAt(spUpdated.getMaLSP(), selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex());
					dtmSanPham.setValueAt(spUpdated.getDonGia(), selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex());
					dtmSanPham.setValueAt(spUpdated.getSoLuong(), selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex());
					dtmSanPham.setValueAt(spUpdated.getDonViTinh(), selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex());
				}
				System.out.println(spUpdated.toString());
			}
		}else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm muốn sửa");
		}
	}
	
	private void xoa() {
		int selectedRow = tblSanPham.getSelectedRow();
		if(selectedRow != -1) {
			int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này!", "Xác nhận xóa sản phẩm", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				SanPham_DTO sp = new SanPham_DTO(
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex()).toString(),
						Double.parseDouble(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex()).toString()),
						Integer.parseInt(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex()).toString()),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex()).toString()
						);
				if(spBUS.xoa(sp))
					dtmSanPham.removeRow(selectedRow);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm muốn xóa");
		}
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
			}else
				workbook = new XSSFWorkbook();
			
			Sheet sanpham = workbook.getSheet("Sản Phẩm");
			
			if(sanpham == null)
				sanpham = workbook.createSheet("Sản Phẩm");
			
			sanpham.setColumnWidth(1, 4000);
			sanpham.setColumnWidth(2, 4000);
			sanpham.setColumnWidth(3, 4000);
			sanpham.setColumnWidth(4, 4000);
			sanpham.setColumnWidth(5, 4000);
			sanpham.setColumnWidth(6, 4000);
			
			Row tblName = sanpham.createRow(1);
			
			Cell column_maSP = tblName.createCell(1);
			column_maSP.setCellValue("Mã sản phẩm");
			Cell column_tenSP = tblName.createCell(2);
			column_tenSP.setCellValue("Tên sản phẩm");
			Cell column_maLSP = tblName.createCell(3);
			column_maLSP.setCellValue("Mã loại sản phẩm");
			Cell column_donGia = tblName.createCell(4);
			column_donGia.setCellValue("Đơn giá");
			Cell column_soLuong = tblName.createCell(5);
			column_soLuong.setCellValue("Số lượng");
			Cell column_donViTinh = tblName.createCell(6);
			column_donViTinh.setCellValue("Đơn vị tính");
			
			for(int i = 0; i < tblSanPham.getRowCount(); i++) {
				SanPham_DTO sp = new SanPham_DTO(
						tblSanPham.getValueAt(i, tblSanPham.getColumn("Mã sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(i, tblSanPham.getColumn("Tên sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(i, tblSanPham.getColumn("Mã loại").getModelIndex()).toString(),
						Double.parseDouble(tblSanPham.getValueAt(i, tblSanPham.getColumn("Đơn giá").getModelIndex()).toString()),
						Integer.parseInt(tblSanPham.getValueAt(i, tblSanPham.getColumn("Số lượng").getModelIndex()).toString()),
						tblSanPham.getValueAt(i, tblSanPham.getColumn("Đơn vị tính").getModelIndex()).toString()
						);
				Row data = sanpham.createRow(i+2);
				
				Cell maSP = data.createCell(1);
				maSP.setCellValue(sp.getMaSP());
				Cell tenSP = data.createCell(2);
				tenSP.setCellValue(sp.getTenSP());
				Cell maLSP = data.createCell(3);
				maLSP.setCellValue(sp.getMaLSP());
				Cell donGia = data.createCell(4);
				donGia.setCellValue(sp.getDonGia());
				Cell soLuong = data.createCell(5);
				soLuong.setCellValue(sp.getSoLuong());
				Cell donViTinh = data.createCell(6);
				donViTinh.setCellValue(sp.getDonViTinh());
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
			
			Sheet sanpham = workbook.getSheet("Sản Phẩm");
			
			dtmSanPham.setRowCount(0);
			
			for(Row row : sanpham) {
				if(row.getRowNum() < 2) 
					continue;
				SanPham_DTO sp = new SanPham_DTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						row.getCell(3).getStringCellValue(),
						(double) row.getCell(4).getNumericCellValue(),
						(int) row.getCell(5).getNumericCellValue(),
						row.getCell(6).getStringCellValue()
						);
				addDataTable(sp);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void tra() {
		String query = "";
		if(comboBox.getSelectedIndex() != 0 && !txtTra.getText().isEmpty()) {
			switch (comboBox.getSelectedIndex()) {
				case 1: 
					query += " maSP LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 2: 
					query += " tenSP LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 3: 
					query += " maLSP LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 4: 
					query += " donGia LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 5: 
					query += " soLuong LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 6: 
					query += " thanhTien LIKE '%"+ txtTra.getText()+ "%'";
					break;
			}
		}
		if(query.length() > 0) {
			dtmSanPham.setRowCount(0);
			addDataTable(spBUS.layDuLieu(query));
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
					frame.setSize(1100,700);
					SanPhamPanel sp = new SanPhamPanel();
					frame.getContentPane().add(sp);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
