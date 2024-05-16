package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

import BUS.LoaiSP_BUS;
import DTO.KhachHangDTO;
import DTO.LoaiSP_DTO;
import GUI_Dialog.LoaiSanPhamInsert;
import GUI_Dialog.LoaiSanPhamUpdate;


public class LoaiSanPhamPanel extends JPanel {
	
	private LoaiSP_BUS lspBUS;
	
	private DefaultTableModel dtmLSP;
	private JTable tblLSP;
	
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;

	private JButton btnTim;

	private AbstractButton btnXuat;

	private AbstractButton btnNhap;

	private JButton btnRefresh;

	public LoaiSanPhamPanel() {
		try {
			lspBUS = new LoaiSP_BUS();
			init();
			addActionListener();
		} catch (Exception e) {
			// TODO: handle exception
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
		   btnThem.setPreferredSize(new Dimension(150,50));
		   pnTop.add(btnThem);
		   
		   btnSua = new JButton("Sửa");
		   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
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
		   btnTim.setIcon(new ImageIcon(LoaiSanPhamPanel.class.getResource("/Image/32_search.png")));
		   btnTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnTim.setBounds(440, 15, 130, 50);
		   pnTop.add(btnTim);
		   
		   btnXuat = new JButton("Xuất");
		   btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXuat.setIcon(new ImageIcon(LoaiSanPhamPanel.class.getResource("/Image/32_excel.png")));
		   btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnXuat.setBounds(580, 15, 130, 50);
		   pnTop.add(btnXuat);
		   
		   btnNhap = new JButton("Nhập");
		   btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
		   btnNhap.setIcon(new ImageIcon(LoaiSanPhamPanel.class.getResource("/Image/32_excel.png")));
		   btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnNhap.setBounds(720, 15, 130, 50);
		   pnTop.add(btnNhap);
		   
//		   ============================================= CENTER =============================================
		   JPanel pnCenter = new JPanel();
		   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   add(pnCenter, BorderLayout.CENTER);
		   pnCenter.setLayout(null);
		   
		   dtmLSP = new DefaultTableModel();
		   dtmLSP.addColumn("Mã loại sản phẩm");
		   dtmLSP.addColumn("Tên loại sản phẩm");

		   addDataTable(lspBUS.getList());
		   
		   tblLSP = new JTable(dtmLSP);
		   tblLSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		   
		   JScrollPane scrollPane = new JScrollPane(tblLSP);
		   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrollPane.setBounds(20, 50, 1060, 540);
		   pnCenter.add(scrollPane);
		   
		   
		   JLabel lblLSP = new JLabel("LOẠI SẢN PHẨM");
		   lblLSP.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblLSP.setBounds(20, 10, 200, 30);
		   pnCenter.add(lblLSP);
		   
		   btnRefresh = new JButton("");
		   btnRefresh.setToolTipText("Làm mới");
		   btnRefresh.setIcon(new ImageIcon(LoaiSanPhamPanel.class.getResource("/Image/24_refresh.png")));
		   btnRefresh.setBounds(1050, 10, 30, 30);
		   pnCenter.add(btnRefresh);
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e -> {
			LoaiSanPhamInsert data = new LoaiSanPhamInsert();
			if(data.showDialog(this)) {
				LoaiSP_DTO lsp = data.getLSP();
				if(lspBUS.them(lsp))
					dtmLSP.addRow(new Object[] {lsp.getMaLSP(), lsp.getTenLSP()});
			}
		});
		
		btnSua.addActionListener(e -> {
			int selectedRow = tblLSP.getSelectedRow();
			if(selectedRow != -1) {
				LoaiSP_DTO lsp = new LoaiSP_DTO(
						tblLSP.getValueAt(selectedRow, tblLSP.getColumn("Mã loại sản phẩm").getModelIndex()).toString(),
						tblLSP.getValueAt(selectedRow, tblLSP.getColumn("Tên loại sản phẩm").getModelIndex()).toString()
						);
				LoaiSanPhamUpdate dataUpdate = new LoaiSanPhamUpdate(lsp);
				if(dataUpdate.showDialog(this)) {
					LoaiSP_DTO lspUpdated = dataUpdate.getLSP();
					if(lspBUS.editLoaiSp(lspUpdated)) {
						dtmLSP.setValueAt(lspUpdated.getMaLSP(), selectedRow, tblLSP.getColumn("Mã loại sản phẩm").getModelIndex());
						dtmLSP.setValueAt(lspUpdated.getTenLSP(), selectedRow, tblLSP.getColumn("Tên loại sản phẩm").getModelIndex());
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn loại sản phẩm muốn sửa!");
			}
		});	
		
		btnXoa.addActionListener(e ->{
			int selectedRow = tblLSP.getSelectedRow();
			if(selectedRow != -1) {
				int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa loại sản phẩm này", "Xác nhận xóa loại sản phẩm", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					LoaiSP_DTO lsp = new LoaiSP_DTO(
							tblLSP.getValueAt(selectedRow, tblLSP.getColumn("Mã loại sản phẩm").getModelIndex()).toString(),
							tblLSP.getValueAt(selectedRow, tblLSP.getColumn("Tên loại sản phẩm").getModelIndex()).toString()
							);
					if(lspBUS.deleteLoaiSP(lsp))
						dtmLSP.removeRow(selectedRow);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn loại sản phẩm muốn sửa!");
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
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của loại sản phẩm vào file: " + selectedFile.getAbsolutePath());
                    xuatFileExcel(selectedFile.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một file Excel.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		
		btnRefresh.addActionListener(e ->{
			addDataTable(lspBUS.getList());
		});
	}
	
	
	/*
	 * FUNCTION
	 */
	private void addDataTable(LoaiSP_DTO lsp) {
		dtmLSP.addRow(new Object[] {lsp.getMaLSP(), lsp.getTenLSP()});
	}
	
	private void addDataTable(ArrayList<LoaiSP_DTO> list) {
		for(int i = 0; i < list.size(); i++) {
			LoaiSP_DTO lsp = list.get(i);
			addDataTable(lsp);
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
			
			Sheet loaisanpham = workbook.getSheet("Loại Sản Phẩm");
			
			if(loaisanpham == null)
				loaisanpham = workbook.createSheet("Loại Sản Phẩm");
			
			loaisanpham.setColumnWidth(1, 4000);
			loaisanpham.setColumnWidth(2, 4000);
			
			Row tblName = loaisanpham.createRow(1);
			
			Cell column_maLSP = tblName.createCell(1);
			column_maLSP.setCellValue("Mã loại sản phẩm");
			Cell column_tenLSP = tblName.createCell(2);
			column_tenLSP.setCellValue("Tên loại sản phẩm");
			
			for(int i = 0; i < tblLSP.getRowCount(); i++) {
				LoaiSP_DTO lsp = new LoaiSP_DTO(
						tblLSP.getValueAt(i, tblLSP.getColumn("Mã loại sản phẩm").getModelIndex()).toString(),
						tblLSP.getValueAt(i, tblLSP.getColumn("Tên loại sản phẩm").getModelIndex()).toString()
						);
				Row data = loaisanpham.createRow(i+2);
				
				Cell maLSP = data.createCell(1);
				maLSP.setCellValue(lsp.getMaLSP());
				Cell tenLSP = data.createCell(2);
				tenLSP.setCellValue(lsp.getTenLSP());
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
			
			Sheet loaisanpham = workbook.getSheet("Loại Sản Phẩm");
			
			dtmLSP.setRowCount(0);
			
			for(Row row : loaisanpham) {
				if(row.getRowNum() < 2) 
					continue;
				LoaiSP_DTO lsp = new LoaiSP_DTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue()
						);
				addDataTable(lsp);
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
					frame.setSize(1100,700);
					LoaiSanPhamPanel lsp = new LoaiSanPhamPanel();
					frame.getContentPane().add(lsp);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
