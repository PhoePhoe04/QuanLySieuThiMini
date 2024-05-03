package GUI_Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapInsert extends JDialog {

    private static final long serialVersionUID = 1L;
    private JButton btnThem;
    private JButton btnXacNhan;
    private JButton btnHuy;

    int mouseX, mouseY;
    private JTextField txtMaPN;
    private JComboBox<String> cboMaNV;
    private JComboBox<String> cboMaNCC;
    private JComboBox<String> cboMaSP;
    private JTextField txtTongTien;
    private JTextField txtNgayNhap;
    private DefaultTableModel dtmCTPN;
    private JTable tblCTPN;

    public PhieuNhapInsert() {
        init();
        addListener();
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void addListener() {
        btnXacNhan.addActionListener(e -> {
            dispose();
        });
        btnThem.addActionListener(e -> {
            dispose();
        });
        btnHuy.addActionListener(e -> {
            dispose();
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int newX = getLocation().x + evt.getX() - mouseX;
                int newY = getLocation().y + evt.getY() - mouseY;

                setLocation(newX, newY);
            }
        });
    }

    private void init() {
        setBounds(100, 100, 700, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(0, 10));
        setResizable(false);

        // ============================== CENTER =============================
        JPanel pnCenter = new JPanel();
        pnCenter.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pnCenter.setLayout(new BorderLayout());
        getContentPane().add(pnCenter, BorderLayout.CENTER);

        // ============================= CENTER =============================
        JPanel pnthongTin = new JPanel();
        pnthongTin.setLayout(new GridLayout(1, 2));
        pnCenter.add(pnthongTin, BorderLayout.CENTER);

        JPanel pnThongTinLeft = new JPanel();
        pnThongTinLeft.setLayout(null);
        pnthongTin.add(pnThongTinLeft);

        JLabel lblPhieuNhap = new JLabel("Phiếu nhập");
        lblPhieuNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPhieuNhap.setBounds(116, 10, 150, 30);
        pnThongTinLeft.add(lblPhieuNhap);

        JLabel lblMaPN = new JLabel("Mã phiếu nhập");
        lblMaPN.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaPN.setBounds(60, 50, 120, 20);
        pnThongTinLeft.add(lblMaPN);

        txtMaPN = new JTextField();
        txtMaPN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMaPN.setBounds(190, 50, 100, 20);
        pnThongTinLeft.add(txtMaPN);
        txtMaPN.setColumns(10);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaNV.setBounds(60, 80, 120, 20);
        pnThongTinLeft.add(lblMaNV);

        String[] maNV = {};
        cboMaNV = new JComboBox<>(maNV);
        cboMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboMaNV.setBounds(190, 80, 100, 20);
        pnThongTinLeft.add(cboMaNV);

        JLabel lblMaNCC = new JLabel("Mã nhà cung cấp");
        lblMaNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaNCC.setBounds(60, 110, 130, 20);
        pnThongTinLeft.add(lblMaNCC);

        String[] maNCC = {};
        cboMaNCC = new JComboBox<>(maNCC);
        cboMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboMaNCC.setBounds(190, 110, 100, 20);
        pnThongTinLeft.add(cboMaNCC);

        JLabel lblTongTien = new JLabel("Tổng tiền");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTongTien.setBounds(60, 140, 120, 20);
        pnThongTinLeft.add(lblTongTien);

        txtTongTien = new JTextField();
        txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtTongTien.setBounds(190, 140, 100, 20);
        txtTongTien.setEditable(false);
        pnThongTinLeft.add(txtTongTien);
        txtTongTien.setColumns(10);

        JLabel lblNgayNhap = new JLabel("Ngày nhập");
        lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNgayNhap.setBounds(60, 170, 120, 20);
        pnThongTinLeft.add(lblNgayNhap);

        JTextField txtNgayNhap = new JTextField();
        txtNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtNgayNhap.setBounds(190, 170, 100, 20);
        txtNgayNhap.setEditable(false);
        pnThongTinLeft.add(txtNgayNhap);
        txtNgayNhap.setColumns(10);

        // Chi tiết phiếu nhập
        JPanel pnThongTinRight = new JPanel();
        pnThongTinRight.setLayout(null);
        pnthongTin.add(pnThongTinRight);

        JLabel lblCTPN = new JLabel("Chi tiết phiếu nhập");
        lblCTPN.setHorizontalAlignment(SwingConstants.CENTER);
        lblCTPN.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCTPN.setBounds(60, 10, 250, 30);
        pnThongTinRight.add(lblCTPN);

        JLabel lblMaSP = new JLabel("Mã sản phẩm");
        lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaSP.setBounds(60, 50, 120, 20);
        pnThongTinRight.add(lblMaSP);
        
        String[] maSP = {};
        cboMaSP = new JComboBox<>(maSP);
        cboMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboMaSP.setBounds(190, 50, 100, 20);
        pnThongTinRight.add(cboMaSP);

        JLabel lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSoLuong.setBounds(60, 80, 120, 20);
        pnThongTinRight.add(lblSoLuong);

        JTextField txtSoLuong = new JTextField();
        txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSoLuong.setBounds(190, 80, 100, 20);
        pnThongTinRight.add(txtSoLuong);
        txtSoLuong.setColumns(10);

        JLabel lblThanhTien = new JLabel("Thành tiền");
        lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblThanhTien.setBounds(60, 110, 120, 20);
        pnThongTinRight.add(lblThanhTien);

        JTextField txtThanhTien = new JTextField();
        txtThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtThanhTien.setBounds(190, 110, 100, 20);
        txtThanhTien.setEditable(false); // Không thể chỉnh sửa
        pnThongTinRight.add(txtThanhTien);
        txtThanhTien.setColumns(10);

        // Table chi tiết phiếu nhập
        dtmCTPN = new DefaultTableModel();
        dtmCTPN.addColumn("Mã phiếu nhập");
        dtmCTPN.addColumn("Mã sản phẩm");
        dtmCTPN.addColumn("Số lượng");
        dtmCTPN.addColumn("Thành tiền");

        tblCTPN = new JTable(dtmCTPN);

        JScrollPane scrPaneCTPN = new JScrollPane(tblCTPN);
        scrPaneCTPN.setPreferredSize(new Dimension(0, 150));
        pnCenter.add(scrPaneCTPN, BorderLayout.SOUTH);

        // ============================= SOUTH =============================
        JPanel pnBottom = new JPanel();
        pnBottom.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        pnBottom.setPreferredSize(new Dimension(0, 40));
        pnBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        getContentPane().add(pnBottom, BorderLayout.SOUTH);

        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnBottom.add(btnThem);

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setPreferredSize(new Dimension(150, 30));
        btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnBottom.add(btnXacNhan);

        btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(150, 30));
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnBottom.add(btnHuy);
    }

    public static void main(String[] args) {
        try {
            PhieuNhapInsert dialog = new PhieuNhapInsert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
