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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DTO.NhapHangDTO;
import BUS.ChiTietPhieuNhapBUS;
import BUS.NhapHangBUS;
import GUI_Dialog.PhieuNhapInsert;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class PhieuNhapPanel extends JPanel {

    
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    
    private JTable tblPhieuNhap;
    private JTable tblChiTiet;
    
    private DefaultTableModel dtmPhieuNhap;
    private DefaultTableModel dtmChiTiet;
    
    private NhapHangBUS nhBUS;
    private ChiTietPhieuNhapBUS ctnhBUS;

    public PhieuNhapPanel() {
        init();
        addActionListener();
    }

    private void addActionListener() {
        btnThem.addActionListener(e -> {
            JDialog phieuNhapInsert = new PhieuNhapInsert();
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
                    removeRowFromTblPhieuNhap(selectedRow);
                    removeRowsFromTblChiTiet(maPN);
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
    }

    /*
     *  KHỞI TẠO PANEL
     */

    private void init() {
        setSize(1200, 800);
        setLayout(new BorderLayout(5, 10));

        // TOP
        JPanel pnTop = new JPanel();
        pnTop.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pnTop.setPreferredSize(new Dimension(1200, 80));
        add(pnTop, BorderLayout.NORTH);
        pnTop.setLayout(null);

        btnThem = new JButton("Thêm");
        btnThem.setBounds(20, 15, 150, 50);
        btnThem.setHorizontalAlignment(SwingConstants.LEFT);
        btnThem.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/add_icon.png")));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTop.add(btnThem);

        btnXoa = new JButton("Xóa");
        btnXoa.setBounds(180, 15, 150, 50);
        btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
        btnXoa.setIcon(new ImageIcon(PhieuNhapPanel.class.getResource("/Image/delete2_icon.png")));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 25));
        pnTop.add(btnXoa);

        // CENTER
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        addRow();

        tblPhieuNhap = new JTable(dtmPhieuNhap);

        JScrollPane scrPanePhieuNhap = new JScrollPane(tblPhieuNhap);
        scrPanePhieuNhap.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPanePhieuNhap.setBounds(20, 50, 650, 640);
        pnCenter.add(scrPanePhieuNhap);

        JLabel lblPhieuNhap = new JLabel("PHIẾU NHẬP");
        lblPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPhieuNhap.setBounds(20, 10, 200, 30);
        pnCenter.add(lblPhieuNhap);

        tblChiTiet = new JTable(dtmChiTiet);

        JScrollPane scrPaneChiTiet = new JScrollPane(tblChiTiet);
        scrPaneChiTiet.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPaneChiTiet.setBounds(680, 50, 500, 640);
        pnCenter.add(scrPaneChiTiet);

        JLabel lblChiTiet = new JLabel("CHI TIẾT PHIẾU NHẬP");
        lblChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblChiTiet.setBounds(680, 10, 250, 30);
        pnCenter.add(lblChiTiet);

    }

    /*
     *  CÁC HÀM XỬ LÝ LISTENER
     */

    private void addRow() {
        String[] columnNames_PhieuNhap = {"Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp", "Tổng tiền", "Ngày nhập"};
        Object[][] data_PhieuNhap = {
                {"PN01", "NV01", "NCC01", 10000, Date.valueOf("2024-05-01")},
                {"PN02", "NV02", "NCC02", 20000, Date.valueOf("2024-05-02")}
        };

        dtmPhieuNhap = new DefaultTableModel(data_PhieuNhap, columnNames_PhieuNhap);

        String[] columnNames_ChiTiet = {"Mã phiếu nhập", "Mã sản phẩm", "Số lượng", "Thành tiền"};
        Object[][] data_ChiTiet = {
                {"PN01", "SP01", 5, 5000},
                {"PN01", "SP02", 10, 10000},
                {"PN02", "SP03", 3, 3000},
                {"PN02", "SP04", 7, 7000}
        };

        dtmChiTiet = new DefaultTableModel(data_ChiTiet, columnNames_ChiTiet);

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
        DefaultTableModel dtmPhieuNhap = (DefaultTableModel) tblPhieuNhap.getModel();
        dtmPhieuNhap.removeRow(rowIndex);
    }

    private void removeRowsFromTblChiTiet(String maPN) {
        DefaultTableModel dtmChiTiet = (DefaultTableModel) tblChiTiet.getModel();
        for (int i = dtmChiTiet.getRowCount() - 1; i >= 0; i--) {
            if (maPN.equals(dtmChiTiet.getValueAt(i, dtmChiTiet.findColumn("Mã phiếu nhập")))) {
                dtmChiTiet.removeRow(i);
            }
        }
    }
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					JPanel phieunhap = new PhieuNhapPanel();
					frame.getContentPane().add(phieunhap);
					frame.setSize(1200,800);
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
