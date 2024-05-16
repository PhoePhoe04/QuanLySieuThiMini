package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.NhanVienBUS;
import DTO.KhachHangDTO;
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
	private JButton btnTim;
	private JButton btnXuat;
	private JButton btnNhap;

	private JButton btnRefresh;

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
	   
//	   ============================================= CENTER =============================================
	   JPanel pnCenter = new JPanel();
	   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   add(pnCenter, BorderLayout.CENTER);
	   pnCenter.setLayout(null);
	   
	   dtmNhanVien = new DefaultTableModel();
	   dtmNhanVien.addColumn("Mã nhân viên");
	   dtmNhanVien.addColumn("Họ");
	   dtmNhanVien.addColumn("Tên");
	   dtmNhanVien.addColumn("Ngày sinh");
	   dtmNhanVien.addColumn("Giới tính");
	   dtmNhanVien.addColumn("Địa chỉ");
	   dtmNhanVien.addColumn("Số điện thoại");
	   
//	   addDataTable(nhanVienBUS.getList_NV());
	   
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
	   
	   btnRefresh = new JButton("");
	   btnRefresh.setIcon(new ImageIcon(NhanVienPanel.class.getResource("/Image/24_refresh.png")));
	   btnRefresh.setBounds(1050, 10, 30, 30);
	   pnCenter.add(btnRefresh);
	   
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
		
		btnRefresh.addActionListener(e ->{
			addDataTable(nhanVienBUS.getList_NV());
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
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của nhân viên vào file: " + selectedFile.getAbsolutePath());
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
	 * Function
	 */
	private void addDataTable(NhanVienDTO nv) {
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
	
	private void addDataTable(ArrayList<NhanVienDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			NhanVienDTO nv = list.get(i);
			addDataTable(nv);
		}
	}
	
	private void themNhanVien() {
		NhanVienInsert data = new NhanVienInsert();
		if(data.showDialog(this)) {
			NhanVienDTO nv = data.getNhanVien();
			try {
				if(nhanVienBUS.them(nv))
					addDataTable(nv);
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
			
			Sheet nhanvien = workbook.getSheet("Nhân Viên");
			
			if(nhanvien == null)
				nhanvien = workbook.createSheet("Nhân Viên");
			
			nhanvien.setColumnWidth(1, 4000);
			nhanvien.setColumnWidth(2, 4000);
			nhanvien.setColumnWidth(3, 4000);
			nhanvien.setColumnWidth(4, 4000);
			nhanvien.setColumnWidth(5, 4000);
			nhanvien.setColumnWidth(6, 4000);
			nhanvien.setColumnWidth(7, 4000);
			
			Row tblName = nhanvien.createRow(1);
			
			Cell column_maNV = tblName.createCell(1);
			column_maNV.setCellValue("Mã nhân viên");
			Cell column_hoNV = tblName.createCell(2);
			column_hoNV.setCellValue("Họ");
			Cell column_tenNV = tblName.createCell(3);
			column_tenNV.setCellValue("Tên");
			Cell column_ngaySinh = tblName.createCell(4);
			column_ngaySinh.setCellValue("Ngày sinh");
			Cell column_diaChi = tblName.createCell(5);
			column_diaChi.setCellValue("Địa chỉ");
			Cell column_gioiTinh = tblName.createCell(6);
			column_gioiTinh.setCellValue("Ngày sinh");
			Cell column_sdt = tblName.createCell(7);
			column_sdt.setCellValue("Số điện thoại");
			
			for(int i = 0; i < tblNhanVien.getRowCount(); i++) {
				NhanVienDTO nv = new NhanVienDTO(
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Mã nhân viên").getModelIndex()).toString(),
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Họ").getModelIndex()).toString(),
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Tên").getModelIndex()).toString(),
						Date.valueOf(tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Ngày sinh").getModelIndex()).toString()),
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Giới tính").getModelIndex()).toString().equals("Nam") ? false:true,
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Địa chỉ").getModelIndex()).toString(),
						tblNhanVien.getValueAt(i, tblNhanVien.getColumn("Số điện thoại").getModelIndex()).toString()
						);
				
				Row data = nhanvien.createRow(i+2);
				
				Cell maNV = data.createCell(1);
				maNV.setCellValue(nv.getMaNV());
				Cell hoNV = data.createCell(2);
				hoNV.setCellValue(nv.getHoNV());
				Cell tenNV = data.createCell(3);
				tenNV.setCellValue(nv.getTenNV());
				Cell ngaySinh = data.createCell(4);
				ngaySinh.setCellValue(nv.getNgaySinh().toString());
				Cell gioiTinh = data.createCell(5);
				gioiTinh.setCellValue(nv.isGioiTinh() == false ? "Nam":"Nữ");
				Cell diaChi = data.createCell(6);
				diaChi.setCellValue(nv.getDiaChi());
				Cell sdt = data.createCell(7);
				sdt.setCellValue(nv.getSoDienThoai());
				
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
			
			Sheet nhanvien = workbook.getSheet("Nhân Viên");
			
			dtmNhanVien.setRowCount(0);
			
			for(Row row : nhanvien) {
				if(row.getRowNum() < 2) 
					continue;
				NhanVienDTO nv = new NhanVienDTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						row.getCell(3).getStringCellValue(),
						Date.valueOf(row.getCell(4).getStringCellValue().toString()),
						row.getCell(5).getStringCellValue().equals("Nam") ? false:true,
						row.getCell(6).getStringCellValue(),
						row.getCell(7).getStringCellValue()
						);
				
				addDataTable(nv);
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
