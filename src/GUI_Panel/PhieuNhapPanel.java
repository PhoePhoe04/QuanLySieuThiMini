package GUI_Panel;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.HoaDonDTO;
import DTO.NhapHangDTO;
import BUS.ChiTietPhieuNhapBUS;
import BUS.NhapHangBUS;
import GUI_Dialog.HoaDonSearch;
import GUI_Dialog.PhieuNhapInsert;
import GUI_Dialog.PhieuNhapSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class PhieuNhapPanel extends JPanel {

    
	private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    private JTable tblPhieuNhap;
    private JTable tblChiTiet;
    
    private JComboBox<String> cbBoxTra ;
    private DefaultTableModel dtmPhieuNhap;
    private DefaultTableModel dtmChiTiet;
    
    private NhapHangBUS nhBUS;
    private ChiTietPhieuNhapBUS ctnhBUS;
	private JButton btnTKNC;
	private JButton btnXuat;
	private JButton btnNhap;
	private JButton btnRefresh;
	private JTextField txtTim;
	private JButton  btnTra;

    public PhieuNhapPanel() {
    	try {
    		nhBUS = new NhapHangBUS();
    		ctnhBUS = new ChiTietPhieuNhapBUS();
    		init();
            addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void addActionListener() {
        btnThem.addActionListener(e -> {
        	PhieuNhapInsert data = new PhieuNhapInsert();
        	if(data.showDialog(this)) {
        		NhapHangDTO nh = data.getPN();
        		ArrayList<ChiTietPhieuNhapDTO> list = data.getList_CTPN();
        		
        		try {
					boolean completed = true;
					if(nhBUS.them(nh))
						addDataTable_PN(nh);
					
					for(int i = 0 ; i < list.size(); i++) {
						if(!ctnhBUS.them(list.get(i)))
							completed = false;
					}
					if(completed) {
						addDataTable_CTPN(list);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        		
        	}
        });

        btnXoa.addActionListener(e -> {
            int selectedRow = tblPhieuNhap.getSelectedRow();
            if (selectedRow != -1) {
                int option = JOptionPane.showConfirmDialog(this,
                        "Bạn có chắc muốn xóa phiếu nhập này!",
                        "Xác nhận xóa phiếu nhập",
                        JOptionPane.YES_NO_OPTION
                );
                if (option == JOptionPane.YES_OPTION) {
                    String maPN = (String) tblPhieuNhap.getValueAt(selectedRow, tblPhieuNhap.getColumn("Mã phiếu nhập").getModelIndex());
                    removeRowsFromTblChiTiet(maPN);
                    removeRowFromTblPhieuNhap(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Bạn chưa chọn phiếu nhập muốn xóa!"
                );
            }

        });

        tblPhieuNhap.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblPhieuNhap.getSelectedRow();
                if (selectedRow != -1) {
                    String maPN = (String) tblPhieuNhap.getValueAt(selectedRow, tblPhieuNhap.getColumn("Mã phiếu nhập").getModelIndex());
                    filterTblChiTiet(maPN);
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
			PhieuNhapSearch search = new PhieuNhapSearch();
			if(search.showDialog(this)) {
				String query = search.getQuery();
				System.out.println(query);
				dtmPhieuNhap.setRowCount(0);
				addDataTable_PN(nhBUS.getList(query));
			}
		});
		btnTra.addActionListener(e->{
			traThongTin();
		});
		btnRefresh.addActionListener(e->{
			dtmPhieuNhap.setRowCount(0);
			addDataTable_PN(nhBUS.getList());
		});
	}
    

    /*
     *  KHỞI TẠO PANEL
     */

    private void init() {
        setSize(1100, 700);
        setLayout(new BorderLayout(5, 10));

        // TOP
        JPanel pnTop = new JPanel();
        pnTop.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pnTop.setPreferredSize(new Dimension(1200, 80));
        add(pnTop, BorderLayout.NORTH);
        pnTop.setLayout(null);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(20, 15, 130, 50);
        btnThem.setHorizontalAlignment(SwingConstants.LEFT);
        btnThem.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/add_icon.png")));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnTop.add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(160, 15, 130, 50);
        btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
        btnXoa.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/delete2_icon.png")));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnTop.add(btnXoa);
        
        btnTKNC = new JButton("Tìm");
        btnTKNC.setHorizontalAlignment(SwingConstants.LEFT);
        btnTKNC.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/32_search.png")));
        btnTKNC.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnTKNC.setBounds(300, 15, 130, 50);
        pnTop.add(btnTKNC);
        
        btnXuat = new JButton("Xuất");
        btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnXuat.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/32_excel.png")));
        btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnXuat.setBounds(440, 15, 130, 50);
        pnTop.add(btnXuat);
        
        btnNhap = new JButton("Nhập");
        btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
        btnNhap.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/32_excel.png")));
        btnNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNhap.setBounds(580, 15, 130, 50);
        pnTop.add(btnNhap);
        
        cbBoxTra = new JComboBox<String>();

        cbBoxTra.addItem("");
		cbBoxTra.addItem("Mã phiếu nhập");
		cbBoxTra.addItem("Mã nhà cung cấp");
		cbBoxTra.addItem("Mã nhân viên");
		cbBoxTra.addItem("Ngày lập");
		cbBoxTra.addItem("Tổng tiền");
		
        cbBoxTra.setBounds(834, 29, 80, 30);
        pnTop.add(cbBoxTra);
        
        txtTim = new JTextField();
        txtTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtTim.setColumns(10);
        txtTim.setBounds(924, 29, 80, 30);
        pnTop.add(txtTim);
        
        btnTra = new JButton("Tra");
        btnTra.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTra.setBounds(1014, 29, 70, 30);
        pnTop.add(btnTra);

        // CENTER
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        
        dtmPhieuNhap = new DefaultTableModel();
        dtmPhieuNhap.addColumn("Mã phiếu nhập");
        dtmPhieuNhap.addColumn("Mã nhân viên");
        dtmPhieuNhap.addColumn("Mã nhà cung cấp");
        dtmPhieuNhap.addColumn("Tổng tiền");
        dtmPhieuNhap.addColumn("Ngày nhập");
        
        addDataTable_PN(nhBUS.getList());

        tblPhieuNhap = new JTable(dtmPhieuNhap);
        tblPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JScrollPane scrPanePhieuNhap = new JScrollPane(tblPhieuNhap);
        scrPanePhieuNhap.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPanePhieuNhap.setBounds(20, 50, 525, 540);
        pnCenter.add(scrPanePhieuNhap);

        JLabel lblPhieuNhap = new JLabel("PHIẾU NHẬP");
        lblPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPhieuNhap.setBounds(20, 10, 200, 30);
        pnCenter.add(lblPhieuNhap);
        
        dtmChiTiet = new DefaultTableModel();
        dtmChiTiet.addColumn("Mã phiếu nhập");
        dtmChiTiet.addColumn("Mã sản phẩm");
        dtmChiTiet.addColumn("Số lượng");
        dtmChiTiet.addColumn("Đơn giá");
        dtmChiTiet.addColumn("Thành tiền");
        
        addDataTable_CTPN(ctnhBUS.getList());

        tblChiTiet = new JTable(dtmChiTiet);
        tblChiTiet.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JScrollPane scrPaneChiTiet = new JScrollPane(tblChiTiet);
        scrPaneChiTiet.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPaneChiTiet.setBounds(555, 50, 525, 540);
        pnCenter.add(scrPaneChiTiet);

        JLabel lblChiTiet = new JLabel("CHI TIẾT PHIẾU NHẬP");
        lblChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblChiTiet.setBounds(555, 10, 250, 30);
        pnCenter.add(lblChiTiet);
        
        btnRefresh = new JButton("");
        btnRefresh.setToolTipText("Refresh");
        btnRefresh.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/24_refresh.png")));
        btnRefresh.setBounds(1050, 10, 30, 30);
        pnCenter.add(btnRefresh);

    }
    
    /*
     * FUNCTION
     */
    private void addDataTable_PN(NhapHangDTO nhapHang) {
    	dtmPhieuNhap.addRow(new Object[] {
			nhapHang.getMaPN(),
			nhapHang.getMaNV(),
			nhapHang.getMaNCC(),
			nhapHang.getTongTien(),
			nhapHang.getNgayNhap()
		});
    }
    
    private void addDataTable_PN(ArrayList<NhapHangDTO> list) {
    	for(int i = 0; i < list.size(); i++) {
    		NhapHangDTO nhapHang = list.get(i);
    		addDataTable_PN(nhapHang);
    	}
    }
    
    private void addDataTable_CTPN(ChiTietPhieuNhapDTO ctpn) {
    	dtmChiTiet.addRow(new Object[] {
    		ctpn.getMaPN(),
    		ctpn.getMaSP(),
    		ctpn.getSoLuong(),
    		ctpn.getDonGia(),
    		ctpn.getThanhTien()
    	});
    }
    
    private void addDataTable_CTPN(ArrayList<ChiTietPhieuNhapDTO> list) {
    	for(int i = 0; i < list.size(); i++) {
    		ChiTietPhieuNhapDTO ctpn = list.get(i);
    		addDataTable_CTPN(ctpn);
    	}
    }

    private void filterTblChiTiet(String selectedMaPN) {
        DefaultTableModel dtmChiTiet = (DefaultTableModel) tblChiTiet.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dtmChiTiet);

        tblChiTiet.setRowSorter(sorter);

        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
            public boolean include(Entry<?, ?> entry) {
                String maPN = (String) entry.getValue(0);
                return maPN.equals(selectedMaPN);
            }
        };
        sorter.setRowFilter(filter);
    }

    private void removeRowFromTblPhieuNhap(int rowIndex) {
    	NhapHangDTO nh = new NhapHangDTO(
    			tblPhieuNhap.getValueAt(rowIndex, tblPhieuNhap.getColumn("Mã phiếu nhập").getModelIndex()).toString(),
    			tblPhieuNhap.getValueAt(rowIndex, tblPhieuNhap.getColumn("Mã nhân viên").getModelIndex()).toString(),
    			tblPhieuNhap.getValueAt(rowIndex, tblPhieuNhap.getColumn("Mã nhà cung cấp").getModelIndex()).toString(),
    			Double.parseDouble(tblPhieuNhap.getValueAt(rowIndex, tblPhieuNhap.getColumn("Tổng tiền").getModelIndex()).toString()),
    			Date.valueOf(tblPhieuNhap.getValueAt(rowIndex, tblPhieuNhap.getColumn("Ngày nhập").getModelIndex()).toString())
    			);
    	try {
			if(nhBUS.xoa(nh))
				dtmPhieuNhap.removeRow(rowIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    


    private void removeRowsFromTblChiTiet(String maPN) {
        for (int i = dtmChiTiet.getRowCount() - 1; i >= 0; i--) {
            if (maPN.equals(dtmChiTiet.getValueAt(i, dtmChiTiet.findColumn("Mã phiếu nhập")))) {
            	ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(
            			tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Mã phiếu nhập").getModelIndex()).toString(),
            			tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Mã sản phẩm").getModelIndex()).toString(),
            			Integer.parseInt(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Số lượng").getModelIndex()).toString()),
            			Float.parseFloat(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Đơn giá").getModelIndex()).toString()),
            			Float.parseFloat(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Thành tiền").getModelIndex()).toString())
            			);
            	try {
					if(ctnhBUS.xoa(ctpn))
						dtmChiTiet.removeRow(i);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
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
			 
			 Sheet phieuNhap = workbook.getSheet("Phiếu Nhập");
			 
			 if(phieuNhap == null)
				 phieuNhap = workbook.createSheet("Phiếu Nhập");
			 
			 phieuNhap.setColumnWidth(1, 4000);
			 phieuNhap.setColumnWidth(2, 4000);
			 phieuNhap.setColumnWidth(3, 4000);
			 phieuNhap.setColumnWidth(4, 4000);
			 phieuNhap.setColumnWidth(5, 4000);
			 
			 Row tblName = phieuNhap.createRow(1);
			 
			 Cell column_maPN = tblName.createCell(1);
			 column_maPN.setCellValue("Mã phiếu nhập");
			 
			 Cell column_maNV = tblName.createCell(2);
			 column_maNV.setCellValue("Mã nhân viên");
			 
			 Cell column_maNCC = tblName.createCell(3);
			 column_maNCC.setCellValue("Mã nhà cung cấp");
			 
			 Cell column_tongTien = tblName.createCell(4);
			 column_tongTien.setCellValue("Tổng tiền");
			 
			 Cell column_ngayNhap = tblName.createCell(5);
			 column_ngayNhap.setCellValue("Ngày nhập");
			 
			 
			 for(int i = 0; i < tblPhieuNhap.getRowCount(); i++) {
				 NhapHangDTO nh = new NhapHangDTO(
	    			tblPhieuNhap.getValueAt(i, tblPhieuNhap.getColumn("Mã phiếu nhập").getModelIndex()).toString(),
	    			tblPhieuNhap.getValueAt(i, tblPhieuNhap.getColumn("Mã nhân viên").getModelIndex()).toString(),
	    			tblPhieuNhap.getValueAt(i, tblPhieuNhap.getColumn("Mã nhà cung cấp").getModelIndex()).toString(),
	    			Double.parseDouble(tblPhieuNhap.getValueAt(i, tblPhieuNhap.getColumn("Tổng tiền").getModelIndex()).toString()),
	    			Date.valueOf(tblPhieuNhap.getValueAt(i, tblPhieuNhap.getColumn("Ngày nhập").getModelIndex()).toString())
				 );
				 Row data = phieuNhap.createRow(i+2);
				 
				 Cell maPN = data.createCell(1);
				 maPN.setCellValue(nh.getMaPN());
				 Cell maNV = data.createCell(2);
				 maNV.setCellValue(nh.getMaNV());
				 Cell maNCC = data.createCell(3);
				 maNCC.setCellValue(nh.getMaNCC());
				 Cell tongTien = data.createCell(4);
				 tongTien.setCellValue(nh.getTongTien());
				 Cell ngayNhap = data.createCell(5);
				 ngayNhap.setCellValue(nh.getNgayNhap().toString());
			 }
			 
			 Sheet CTPN = workbook.getSheet("CTPN");
			 if(CTPN == null)
				 CTPN = workbook.createSheet("CTPN");
			 
			 CTPN.setColumnWidth(1, 4000);
			 CTPN.setColumnWidth(2, 4000);
			 CTPN.setColumnWidth(3, 4000);
			 CTPN.setColumnWidth(4, 4000);
			 CTPN.setColumnWidth(5, 4000);
			 
			 Row tblName_CTPN = CTPN.createRow(1);
			 
			 Cell column_CTPN_maPN = tblName_CTPN.createCell(1);
			 column_CTPN_maPN.setCellValue("Mã phiếu nhập");
			 Cell column_CTPN_maSP = tblName_CTPN.createCell(2);
			 column_CTPN_maSP.setCellValue("Mã sản phẩm");
			 Cell column_CTPN_soLuong = tblName_CTPN.createCell(3);
			 column_CTPN_soLuong.setCellValue("Số lượng");
			 Cell column_CTPN_donGia = tblName_CTPN.createCell(4);
			 column_CTPN_donGia.setCellValue("Đơn giá");
			 Cell column_CTPN_thanhTien = tblName_CTPN.createCell(5);
			 column_CTPN_thanhTien.setCellValue("Thành tiền");
			 
			 
			 for(int i = 0; i < tblChiTiet.getRowCount(); i++) {
				 ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(
	            			tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Mã phiếu nhập").getModelIndex()).toString(),
	            			tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Mã sản phẩm").getModelIndex()).toString(),
	            			Integer.parseInt(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Số lượng").getModelIndex()).toString()),
	            			Float.parseFloat(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Đơn giá").getModelIndex()).toString()),
	            			Float.parseFloat(tblChiTiet.getValueAt(i, tblChiTiet.getColumn("Thành tiền").getModelIndex()).toString())
	            			);
				 Row data = CTPN.createRow(i+2);
				 Cell maPN = data.createCell(1);
				 maPN.setCellValue(ctpn.getMaPN());
				 Cell maSP = data.createCell(2);
				 maSP.setCellValue(ctpn.getMaSP());
				 Cell soLuong = data.createCell(3);
				 soLuong.setCellValue(ctpn.getSoLuong());
				 Cell donGia = data.createCell(4);
				 donGia.setCellValue(ctpn.getDonGia());
				 Cell thanhTien = data.createCell(5);
				 thanhTien.setCellValue(ctpn.getThanhTien());
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
			
			Sheet phieNhap = workbook.getSheet("Phiếu Nhập");
			
			dtmPhieuNhap.setRowCount(0);
			for (Row row : phieNhap) {
				if(row.getRowNum() < 2)
					continue;
				String maPN = row.getCell(1).getStringCellValue();
				String maNV = row.getCell(2).getStringCellValue();
				String maNCC = row.getCell(3).getStringCellValue();
				double tongTien = row.getCell(4).getNumericCellValue();
				Date ngayLap = Date.valueOf(row.getCell(5).getStringCellValue());
				
				NhapHangDTO nh = new NhapHangDTO(maPN, maNV, maNCC, tongTien, ngayLap);
				addDataTable_PN(nh);
			}
			
			
			Sheet CTPN = workbook.getSheet("CTPN");
			
			dtmChiTiet.setRowCount(0);
			for(Row row : CTPN) {
				if(row.getRowNum() < 2)
					continue;
				ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						(int) row.getCell(3).getNumericCellValue(),
						(float) row.getCell(4).getNumericCellValue(),
						(float) row.getCell(5).getNumericCellValue()
						);
				addDataTable_CTPN(ctpn);
			}
			
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    // tra 
    private void traThongTin() {
		String column = cbBoxTra.getSelectedItem().toString();
		String condition = null;
		if(column.equals("Mã phiếu nhập")) {
			if(!txtTim.getText().toString().equals("")) {
				dtmPhieuNhap.setRowCount(0);
				condition = " maHD LIKE '%"+ txtTim.getText() + "%'";
				addDataTable_PN(nhBUS.getList(condition));
			}
		}else if(column.equals("Mã nhà cung cấp")) {
			if(!txtTim.getText().toString().equals("")) {
				dtmPhieuNhap.setRowCount(0);
				condition = " maKH LIKE '%"+ txtTim.getText() + "%'";
				addDataTable_PN(nhBUS.getList(condition));
			}
		}else if(column.equals("Mã nhân viên")) {
			if(!txtTim.getText().toString().equals("")) {
				dtmPhieuNhap.setRowCount(0);
				condition = " maNV LIKE '%"+ txtTim.getText() + "%'";
				addDataTable_PN(nhBUS.getList(condition));
			}
			
		}else if(column.equals("Ngày nhập")) {
			if(!txtTim.getText().toString().equals("")) {
				dtmPhieuNhap.setRowCount(0);
				condition = " ngaNhap LIKE '%"+ txtTim.getText() + "%'";
				addDataTable_PN(nhBUS.getList(condition));
			}
		}else if(column.equals("Tổng tiền")) {
			if(txtTim.getText().toString().matches("[0-9]+")) {
				dtmPhieuNhap.setRowCount(0);
				condition = " tongTien = "+ Double.parseDouble(txtTim.getText().toString());
				addDataTable_PN(nhBUS.getList(condition));
			}
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
					JPanel phieunhap = new PhieuNhapPanel();
					frame.getContentPane().add(phieunhap);
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