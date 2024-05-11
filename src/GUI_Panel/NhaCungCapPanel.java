package GUI_Panel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI_Dialog.NhaCungCapInsert;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NhaCungCapPanel extends JPanel {
	
	private NhaCungCapBUS nccBUS;
	
	
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
    private JButton btnSua;
    private DefaultTableModel dtmNhaCungCap;
    private ArrayList<NhaCungCapDTO> listNhaCungCap;
    private JButton btnXoa;
    private JTable tblNhaCungCap;

    public NhaCungCapPanel() {
    	try {
    		nccBUS = new NhaCungCapBUS();
    		init();
    	    addActionListener();
		} catch (Exception e) {
			// TODO: handle exception
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
        
        addRow(nccBUS.getList());

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

    }

    /*
     *  CÁC HÀM XỬ LÝ LISTENER
     */
    
    private void addActionListener() {
        btnThem.addActionListener(e -> {
            JDialog nhaCungCapInsert = new NhaCungCapInsert();
        });

        btnXoa.addActionListener(e -> {
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

        });
    }
    
    /*
     * Function
     */
    private void addRow(ArrayList<NhaCungCapDTO> list) {
    	if(list != null) {
    		for (NhaCungCapDTO ncc : list) {
				dtmNhaCungCap.addRow(new Object[] {
					ncc.getMaNCC(),
					ncc.getTenNCC(),
					ncc.getEmail(),
					ncc.getDiaChiNCC()
				});
			}
    	}
    }

    private void removeRowFromTblNhaCungCap(int rowIndex) {
        dtmNhaCungCap.removeRow(rowIndex);
    }
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

