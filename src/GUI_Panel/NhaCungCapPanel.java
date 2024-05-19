package GUI_Panel;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.NhaCungCapBUS;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import GUI_Dialog.NhaCungCapInsert;
import GUI_Dialog.NhaCungCapUpdate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NhaCungCapPanel extends JPanel {
	
	private NhaCungCapBUS nccBUS;
	
	
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
    private JButton btnSua;
    private DefaultTableModel dtmNhaCungCap;
    private JButton btnXoa;
    private JTable tblNhaCungCap;
	private JButton btnXuat;
	private JButton btnNhap;


	private JButton btnRefresh;
	private JButton btnClear;


	private JButton btnTra;
	private JTextField txtTra;
	private JComboBox comboBox;

    public NhaCungCapPanel() {
    	try {
    		nccBUS = new NhaCungCapBUS();
    		init();
    	    addActionListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
       
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

        btnThem = new JButton("Thêm");
        btnThem.setBounds(20, 15, 130, 50);
        btnThem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        pnTop.setLayout(null);
        btnThem.setHorizontalAlignment(SwingConstants.LEFT);
        btnThem.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/add_icon.png")));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnThem.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBounds(160, 15, 130, 50);
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSua.setHorizontalAlignment(SwingConstants.LEFT);
        btnSua.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/edit_icon.png")));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSua.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(300, 15, 130, 50);
        btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
        btnXoa.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/delete2_icon.png")));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnXoa.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnXoa);
        
        btnXuat = new JButton("Xuất");
        btnXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnXuat.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/32_excel.png")));
        btnXuat.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnXuat.setBounds(440, 15, 130, 50);
        pnTop.add(btnXuat);
        
        btnNhap = new JButton("Nhập");
        btnNhap.setHorizontalAlignment(SwingConstants.LEFT);
        btnNhap.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/32_excel.png")));
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
 	   
 	   
 	   	String[] columnDB = new String[] {"", "Mã NCC", "Tên NCC", "Email", "Địa chỉ"};
 	   	comboBox = new JComboBox(columnDB);
 	   	comboBox.setBounds(760, 30, 100, 30);
 	   	pnTop.add(comboBox);
        
        // CENTER
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        dtmNhaCungCap = new DefaultTableModel();
        dtmNhaCungCap.addColumn("Mã NCC");
        dtmNhaCungCap.addColumn("Tên NCC");
        dtmNhaCungCap.addColumn("Email");
        dtmNhaCungCap.addColumn("Địa chỉ");
        
        addDataTable(nccBUS.getList());
        
        tblNhaCungCap = new JTable(dtmNhaCungCap);
        tblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JScrollPane scrPaneNhaCungCap = new JScrollPane(tblNhaCungCap);
        scrPaneNhaCungCap.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPaneNhaCungCap.setBounds(20, 50, 1060, 540);
        pnCenter.add(scrPaneNhaCungCap);

        JLabel lblNhaCungCap = new JLabel("NHÀ CUNG CẤP");
        lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNhaCungCap.setBounds(20, 10, 200, 30);
        pnCenter.add(lblNhaCungCap);
        
        btnRefresh = new JButton("");
        btnRefresh.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/24_refresh.png")));
        btnRefresh.setBounds(1050, 10, 30, 30);
        pnCenter.add(btnRefresh);
        
        btnClear = new JButton("");
        btnClear.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/24_clear.png")));
        btnClear.setBounds(1010, 10, 30, 30);
        pnCenter.add(btnClear);

    }

    /*
     *  CÁC HÀM XỬ LÝ LISTENER
     */
    
    private void addActionListener() {
        btnThem.addActionListener(e -> {
        	them();
        });

        btnXoa.addActionListener(e -> {
            xoa();
        });
        
        btnSua.addActionListener(e ->{
        	sua();
        });
        
        btnTra.addActionListener(e ->{
        	tra();
        });
        
        btnRefresh.addActionListener(e ->{
        	dtmNhaCungCap.setRowCount(0);
        	addDataTable(nccBUS.getList());
        });
        
        btnClear.addActionListener(e ->{
        	dtmNhaCungCap.setRowCount(0);
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
                    JOptionPane.showMessageDialog(this, "Đã xuất thông tin của nhà cung cấp vào file: " + selectedFile.getAbsolutePath());
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
    private void addDataTable(NhaCungCapDTO ncc) {
    	dtmNhaCungCap.addRow(new Object[] {ncc.getMaNCC(), ncc.getTenNCC(), ncc.getEmail(), ncc.getDiaChiNCC()});
    }
    
    private void addDataTable(ArrayList<NhaCungCapDTO> list) {
    	for(int i = 0; i < list.size(); i++) {
    		NhaCungCapDTO ncc = list.get(i);
    		addDataTable(ncc);
    	}
    }

    private void removeRowFromTblNhaCungCap(int rowIndex) {
        dtmNhaCungCap.removeRow(rowIndex);
    }
    
    private void them() {
    	NhaCungCapInsert data = new NhaCungCapInsert();
        if(data.showDialog(this)) {
        	NhaCungCapDTO ncc = data.getNCC();
        	try {
        		if(nccBUS.them(ncc))
            		addDataTable(ncc);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
    }
    
    private void xoa() {
    	int selectedRow = tblNhaCungCap.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa nhà cung cấp này!",
                    "Xác nhận xóa nhà cung cấp",
                    JOptionPane.YES_NO_OPTION
            );
            if (option == JOptionPane.YES_OPTION) {
            	NhaCungCapDTO ncc = new NhaCungCapDTO(
            			dtmNhaCungCap.getValueAt(selectedRow, 0).toString(),
            			dtmNhaCungCap.getValueAt(selectedRow, 1).toString(),
            			dtmNhaCungCap.getValueAt(selectedRow, 2).toString(),
            			dtmNhaCungCap.getValueAt(selectedRow, 3).toString()
            			);
            	try {
					if(nccBUS.xoa(ncc))
					removeRowFromTblNhaCungCap(selectedRow);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Bạn chưa chọn nhà cung cấp muốn xóa!"
            );
        }
    }
    
    private void sua() {
    	int selectedRow = tblNhaCungCap.getSelectedRow();
    	if(selectedRow != -1) {
    		NhaCungCapDTO ncc = new NhaCungCapDTO(
    				tblNhaCungCap.getValueAt(selectedRow, tblNhaCungCap.getColumn("Mã NCC").getModelIndex()).toString(),
    				tblNhaCungCap.getValueAt(selectedRow, tblNhaCungCap.getColumn("Tên NCC").getModelIndex()).toString(),
    				tblNhaCungCap.getValueAt(selectedRow, tblNhaCungCap.getColumn("Email").getModelIndex()).toString(),
    				tblNhaCungCap.getValueAt(selectedRow, tblNhaCungCap.getColumn("Địa chỉ").getModelIndex()).toString()
    				);
    		NhaCungCapUpdate data = new NhaCungCapUpdate(ncc);
    		if(data.showDialog(this)) {
    			NhaCungCapDTO nccUpdated = data.getNCC();
    			try {
					if(nccBUS.sua(nccUpdated)) {
						dtmNhaCungCap.setValueAt(nccUpdated.getMaNCC(), selectedRow, tblNhaCungCap.getColumn("Mã NCC").getModelIndex());
						dtmNhaCungCap.setValueAt(nccUpdated.getTenNCC(), selectedRow, tblNhaCungCap.getColumn("Tên NCC").getModelIndex());
						dtmNhaCungCap.setValueAt(nccUpdated.getEmail(), selectedRow, tblNhaCungCap.getColumn("Email").getModelIndex());
						dtmNhaCungCap.setValueAt(nccUpdated.getDiaChiNCC(), selectedRow, tblNhaCungCap.getColumn("Địa chỉ").getModelIndex());
					}
				} catch (Exception e) {
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
			
			Sheet nhacungcap = workbook.getSheet("Nhà Cung Cấp");
			
			if(nhacungcap == null)
				nhacungcap = workbook.createSheet("Nhà Cung Cấp");
			
			nhacungcap.setColumnWidth(1, 4000);
			nhacungcap.setColumnWidth(2, 4000);
			nhacungcap.setColumnWidth(3, 8000);
			nhacungcap.setColumnWidth(4, 4000);
			
			Row tblName = nhacungcap.createRow(1);
			
			Cell column_maNCC = tblName.createCell(1);
			column_maNCC.setCellValue("Mã nhà cung cấp");
			Cell column_tenNCC = tblName.createCell(2);
			column_tenNCC.setCellValue("Tên nhà cung cấp");
			Cell column_Email = tblName.createCell(3);
			column_Email.setCellValue("Email");
			Cell column_diaChi = tblName.createCell(4);
			column_diaChi.setCellValue("Địa chỉ");
			
			for(int i = 0; i < tblNhaCungCap.getRowCount(); i++) {
				NhaCungCapDTO ncc = new NhaCungCapDTO(
						tblNhaCungCap.getValueAt(i, tblNhaCungCap.getColumn("Mã NCC").getModelIndex()).toString(),
						tblNhaCungCap.getValueAt(i, tblNhaCungCap.getColumn("Tên NCC").getModelIndex()).toString(),
						tblNhaCungCap.getValueAt(i, tblNhaCungCap.getColumn("Email").getModelIndex()).toString(),
						tblNhaCungCap.getValueAt(i, tblNhaCungCap.getColumn("Địa chỉ").getModelIndex()).toString()
						);
				Row data = nhacungcap.createRow(i+2);
				
				Cell maNCC = data.createCell(1);
				maNCC.setCellValue(ncc.getMaNCC());
				Cell tenNCC = data.createCell(2);
				tenNCC.setCellValue(ncc.getTenNCC());
				Cell email = data.createCell(3);
				email.setCellValue(ncc.getEmail());
				Cell diaChi = data.createCell(4);
				diaChi.setCellValue(ncc.getDiaChiNCC());
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
			
			Sheet nhacungcap = workbook.getSheet("Nhà Cung Cấp");
			
			dtmNhaCungCap.setRowCount(0);
			
			for(Row row : nhacungcap) {
				if(row.getRowNum() < 2) 
					continue;
				NhaCungCapDTO ncc = new NhaCungCapDTO(
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getStringCellValue(),
						row.getCell(3).getStringCellValue(),
						row.getCell(4).getStringCellValue()
						);
				addDataTable(ncc);
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
					query += " maNCC LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 2: 
					query += " tenNCC LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 3: 
					query += " email LIKE '%"+ txtTra.getText()+ "%'";
					break;
				case 4: 
					query += " diaChi LIKE '%"+ txtTra.getText()+ "%'";
					break;
			}
		}
		if(query.length() > 0) {
			dtmNhaCungCap.setRowCount(0);
			addDataTable(nccBUS.getList(query));
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
					JPanel nhacungcap = new NhaCungCapPanel();
					frame.getContentPane().add(nhacungcap);
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

