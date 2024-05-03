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
import javax.swing.table.DefaultTableModel;

import DTO.NhaCungCapDTO;
import GUI_Dialog.NhaCungCapInsert;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NhaCungCapPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
    private JButton btnSua;
    private DefaultTableModel dtmNhaCungCap;
    private ArrayList<NhaCungCapDTO> listNhaCungCap;
    private JButton btnXoa;
    private JTable tblNhaCungCap;

    public NhaCungCapPanel() {
        init();
        addActionListener();
    }

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
                    removeRowFromTblNhaCungCap(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Bạn chưa chọn nhà cung cấp muốn xóa!"
                );
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
        pnTop.setPreferredSize(new Dimension(1200, 70));
        add(pnTop, BorderLayout.NORTH);
        pnTop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        btnThem = new JButton("Thêm");
        btnThem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThem.setHorizontalAlignment(SwingConstants.LEFT);
        btnThem.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/add_icon.png")));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnThem.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSua.setHorizontalAlignment(SwingConstants.LEFT);
        btnSua.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/edit_icon.png")));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnSua.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
        btnXoa.setIcon(new ImageIcon(NhaCungCapPanel.class.getResource("/Image/delete2_icon.png")));
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnXoa.setPreferredSize(new Dimension(150, 50));
        pnTop.add(btnXoa);

        // CENTER
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setLayout(null);
        addRow();

        tblNhaCungCap = new JTable(dtmNhaCungCap);

        JScrollPane scrPaneNhaCungCap = new JScrollPane(tblNhaCungCap);
        scrPaneNhaCungCap.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        scrPaneNhaCungCap.setBounds(20, 50, 1150, 650);
        pnCenter.add(scrPaneNhaCungCap);

        JLabel lblNhaCungCap = new JLabel("NHÀ CUNG CẤP");
        lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNhaCungCap.setBounds(20, 10, 200, 30);
        pnCenter.add(lblNhaCungCap);

    }

    /*
     *  CÁC HÀM XỬ LÝ LISTENER
     */

    private void addRow() {
        String[] columnNames_NhaCungCap = {"Mã NCC", "Tên NCC", "Số điện thoại", "Địa chỉ"};
        Object[][] data_NhaCungCap = {
                {"NCC01", "Nhà cung cấp A", "0123456789", "Địa chỉ A"},
                {"NCC02", "Nhà cung cấp B", "0987654321", "Địa chỉ B"}
        };

        dtmNhaCungCap = new DefaultTableModel(data_NhaCungCap, columnNames_NhaCungCap);
    }

    private void removeRowFromTblNhaCungCap(int rowIndex) {
        DefaultTableModel dtmNhaCungCap = (DefaultTableModel) tblNhaCungCap.getModel();
        dtmNhaCungCap.removeRow(rowIndex);
    }
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					JPanel nhacungcap = new NhaCungCapPanel();
					frame.getContentPane().add(nhacungcap);
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

