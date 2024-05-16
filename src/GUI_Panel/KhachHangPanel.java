package GUI_Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	private JTextField textField;
	
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnNhap;
	private JButton btnXuat;
	private JButton btnTim;

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
	   setSize(1100,700);
	   setLayout(new BorderLayout(5,10));
	   
//	   ============================================= TOP =============================================
	   JPanel pnTop = new JPanel();
	   pnTop.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   pnTop.setPreferredSize(new Dimension(1200,80));
	   add(pnTop, BorderLayout.NORTH);
	   pnTop.setLayout(null);
	   
	   btnThem = new JButton("Thêm");
	   btnThem.setBounds(20, 15, 130, 50);
	   btnThem.setHorizontalAlignment(SwingConstants.LEFT);
	   btnThem.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnThem.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/add_icon.png")));
	   btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnThem.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnThem);
	   
	   btnSua = new JButton("Sửa");
	   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
	   btnSua.setHorizontalTextPosition(SwingConstants.RIGHT);
	   btnSua.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/edit_icon.png")));
	   btnSua.setBounds(160, 15, 130, 50);
	   btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnSua.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnSua);
	   
	   btnXoa = new JButton("Xóa");
	   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
	   btnXoa.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/delete2_icon.png")));
	   btnXoa.setBounds(300, 15, 130, 50);
	   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnXoa.setPreferredSize(new Dimension(150,50));
	   pnTop.add(btnXoa);
	   
	   btnTim = new JButton("Tìm");
	   btnTim.setHorizontalAlignment(SwingConstants.LEFT);
	   btnTim.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/32_search.png")));
	   btnTim.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnTim.setBounds(440, 15, 130, 50);
	   pnTop.add(btnTim);
	   
	   btnXuat = new JButton("Xuất");
	   btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
	   btnXuat.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/32_excel.png")));
	   btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnXuat.setBounds(580, 15, 130, 50);
	   pnTop.add(btnXuat);
	   
	   btnNhap = new JButton("Nhập");
	   btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
	   btnNhap.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/32_excel.png")));
	   btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
	   btnNhap.setBounds(720, 15, 130, 50);
	   pnTop.add(btnNhap);
	   
	   JButton btnTra = new JButton("Tra");
	   btnTra.setFont(new Font("Tahoma", Font.PLAIN, 15));
	   btnTra.setBounds(1005, 50, 80, 20);
	   pnTop.add(btnTra);
	   
	   textField = new JTextField();
	   textField.setBounds(985, 10, 100, 30);
	   pnTop.add(textField);
	   textField.setColumns(10);
	   
	   JComboBox comboBox = new JComboBox();
	   comboBox.setBounds(875, 10, 100, 30);
	   pnTop.add(comboBox);
	   
//	   ============================================= CENTER =============================================
	   JPanel pnCenter = new JPanel();
	   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   add(pnCenter, BorderLayout.CENTER);
	   pnCenter.setLayout(null);
	   
	   dtmKhachHang = new DefaultTableModel();
	   dtmKhachHang.addColumn("Mã khách hàng");
	   dtmKhachHang.addColumn("Họ khách hàng");
	   dtmKhachHang.addColumn("Tên khách hàng");
	   dtmKhachHang.addColumn("Giới tính");
	   dtmKhachHang.addColumn("Địa chỉ");
	   dtmKhachHang.addColumn("Số điện thoại");
	   dtmKhachHang.addColumn("Gmail");
	   
	   addDataTable(khachHangBUS.getListKH());
	   
	   tblKhachHang = new JTable(dtmKhachHang);
	   tblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
	   
	   JScrollPane scrollPane = new JScrollPane(tblKhachHang);
	   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
	   scrollPane.setBounds(20, 50, 1060, 540);
	   pnCenter.add(scrollPane);
	   
	   
	   JLabel lblKhachHang = new JLabel("KHÁCH HÀNG");
	   lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 20));
	   lblKhachHang.setBounds(20, 10, 200, 30);
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
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của hóa đơn vào file: " + selectedFile.getAbsolutePath());
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
	private void addDataTable(KhachHangDTO kh) {
		dtmKhachHang.addRow(new Object[] {kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.isGioiTinh() == false ? "Nam":"Nữ", kh.getDiaChi(), kh.getSoDienThoai(), kh.getGmail()});
	}
	private void addDataTable(ArrayList<KhachHangDTO> list) {
		for(int i = 0; i < list.size(); i++) {
			KhachHangDTO kh = list.get(i);
			addDataTable(kh);
		}
	}
	
	private void themKhachHang() {
		KhachHangInsert data = new KhachHangInsert();
		if(data.showDialog(this)) {
			KhachHangDTO kh = data.getKhachHang();
			try {
				if(khachHangBUS.them(kh)) {
					addDataTable(kh);
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
			
			Sheet khachHang = workbook.getSheet("Khách Hàng");
			
			if(khachHang == null)
				khachHang = workbook.createSheet("Khách Hàng");
			
			khachHang.setColumnWidth(1, 4000);
			khachHang.setColumnWidth(2, 4000);
			khachHang.setColumnWidth(3, 4000);
			khachHang.setColumnWidth(4, 4000);
			khachHang.setColumnWidth(5, 4000);
			khachHang.setColumnWidth(6, 4000);
			khachHang.setColumnWidth(7, 4000);
			
			Row tblName = khachHang.createRow(1);
			
			Cell column_maKH = tblName.createCell(1);
			column_maKH.setCellValue("Mã khách hàng");
			Cell column_hoKH = tblName.createCell(2);
			column_hoKH.setCellValue("Họ khách hàng");
			Cell column_tenKH = tblName.createCell(3);
			column_tenKH.setCellValue("Tên khách hàng");
			Cell column_gioiTinh = tblName.createCell(4);
			column_gioiTinh.setCellValue("Giới tính");
			Cell column_diaChi = tblName.createCell(5);
			column_diaChi.setCellValue("Địa chỉ");
			Cell column_sdt = tblName.createCell(6);
			column_sdt.setCellValue("Số điện thoại");
			Cell column_gmail = tblName.createCell(7);
			column_gmail.setCellValue("Gmail");
			
			for(int i = 0; i < tblKhachHang.getRowCount(); i++) {
				KhachHangDTO kh = new KhachHangDTO(
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Mã khách hàng").getModelIndex())+"",
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Họ khách hàng").getModelIndex())+"",
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Tên khách hàng").getModelIndex())+"",
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Giới tính").getModelIndex()).toString().equals("Nam") ? false:true,
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Địa chỉ").getModelIndex())+"",
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Số điện thoại").getModelIndex())+"",
						tblKhachHang.getValueAt(i, tblKhachHang.getColumn("Gmail").getModelIndex()).toString()
						);
				Row data = khachHang.createRow(i+2);
				
				Cell maKH = data.createCell(1);
				maKH.setCellValue(kh.getMaKH());
				Cell hoKH = data.createCell(2);
				hoKH.setCellValue(kh.getHoKH());
				Cell tenKH = data.createCell(3);
				tenKH.setCellValue(kh.getTenKH());
				Cell gioiTinh = data.createCell(4);
				gioiTinh.setCellValue(kh.isGioiTinh() == false ? "Nam":"Nữ");
				Cell diaChi = data.createCell(5);
				diaChi.setCellValue(kh.getDiaChi());
				Cell sdt = data.createCell(6);
				sdt.setCellValue(kh.getSoDienThoai());
				Cell gmail = data.createCell(7);
				gmail.setCellValue(kh.getGmail());
				
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
			
			Sheet khachhang = workbook.getSheet("Khách Hàng");
			
			dtmKhachHang.setRowCount(0);
			
			for(Row row : khachhang) {
				if(row.getRowNum() < 2) 
					continue;
				KhachHangDTO kh = new KhachHangDTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						row.getCell(3).getStringCellValue(),
						row.getCell(4).getStringCellValue().equals("Nam") ? false:true,
						row.getCell(5).getStringCellValue(),
						row.getCell(6).getStringCellValue(),
						row.getCell(7).getStringCellValue()
						);
				addDataTable(kh);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setSize(1100,700);
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
