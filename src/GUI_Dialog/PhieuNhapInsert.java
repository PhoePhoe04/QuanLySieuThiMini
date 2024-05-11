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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import BUS.NhanVienBUS;
import BUS.SanPham_BUS;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;

import javax.swing.ImageIcon;

public class PhieuNhapInsert extends JDialog {
    private JButton btnThem;
    private JButton btnXacNhan;
    private JButton btnHuy;
    private JButton btnMaNV;
	private JButton btnMaNCC;
	private JButton btnNgayNhap;
	private JButton btnMaSP;
    
    private JTextField txtMaPN;
    private JTextField txtMaNV;
    private JTextField txtTongTien;
    private JTextField txtNgayNhap;
    private JTextField txtMaNCC;
    private JTextField txtMaSP;
    private JTextField txtTonKho;
    private JTextField txtMaPN_CTHD;
    private JTextField txtSoLuong;
	private JTextField txtThanhTien;
   
    private DefaultTableModel dtmCTPN;
    private JTable tblCTPN;
    
    int mouseX, mouseY;
	

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
        	
        });
        btnHuy.addActionListener(e -> {
            dispose();
        });
        
        btnMaNV.addActionListener(e ->{
        	MyNhanVien myNhanVien = new MyNhanVien();
        	if(myNhanVien.showDialog(this)) {
        		NhanVienDTO nv = myNhanVien.getNhanVien();
        		if(nv != null)
        			txtMaNV.setText(nv.getMaNV());
        	}
        });
        
        btnMaNCC.addActionListener(e ->{
        	MyNhaCungCap myNCC = new MyNhaCungCap();
        	if(myNCC.showDialog(this)) {
        		NhaCungCapDTO ncc = myNCC.getNCC();
        		if(ncc != null)
        			txtMaNCC.setText(ncc.getMaNCC());
        	}
        });
        
        
        txtMaPN.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				txtMaPN_CTHD.setText(txtMaPN.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				txtMaPN_CTHD.setText(txtMaPN.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				txtMaPN_CTHD.setText(txtMaPN.getText());
			}
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
        setBounds(100, 100, 700, 450);
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
        lblPhieuNhap.setBounds(80, 10, 150, 30);
        pnThongTinLeft.add(lblPhieuNhap);

        JLabel lblMaPN = new JLabel("Mã phiếu nhập");
        lblMaPN.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaPN.setBounds(20, 50, 120, 25);
        pnThongTinLeft.add(lblMaPN);

        txtMaPN = new JTextField();
        txtMaPN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMaPN.setBounds(150, 50, 100, 20);
        pnThongTinLeft.add(txtMaPN);
        txtMaPN.setColumns(10);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaNV.setBounds(20, 80, 120, 20);
        pnThongTinLeft.add(lblMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMaNV.setBounds(150, 80, 100, 20);
        pnThongTinLeft.add(txtMaNV);

        JLabel lblMaNCC = new JLabel("Mã nhà cung cấp");
        lblMaNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaNCC.setBounds(20, 110, 130, 20);
        pnThongTinLeft.add(lblMaNCC);

        txtMaNCC = new JTextField();
        txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMaNCC.setBounds(150, 110, 100, 20);
        pnThongTinLeft.add(txtMaNCC);

        JLabel lblTongTien = new JLabel("Tổng tiền");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTongTien.setBounds(20, 170, 120, 20);
        pnThongTinLeft.add(lblTongTien);

        txtTongTien = new JTextField();
        txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtTongTien.setBounds(150, 170, 100, 20);
        txtTongTien.setEditable(false);
        pnThongTinLeft.add(txtTongTien);
        txtTongTien.setColumns(10);

        JLabel lblNgayNhap = new JLabel("Ngày nhập");
        lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNgayNhap.setBounds(20, 140, 120, 20);
        pnThongTinLeft.add(lblNgayNhap);

        txtNgayNhap = new JTextField();
        txtNgayNhap.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtNgayNhap.setBounds(150, 140, 100, 20);
        txtNgayNhap.setEditable(false);
        pnThongTinLeft.add(txtNgayNhap);
        txtNgayNhap.setColumns(10);
        
        btnMaNV = new JButton("...");
        btnMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnMaNV.setBounds(260, 80, 30, 20);
        pnThongTinLeft.add(btnMaNV);
        
        btnMaNCC = new JButton("...");
        btnMaNCC.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnMaNCC.setBounds(260, 110, 30, 20);
        pnThongTinLeft.add(btnMaNCC);
        
        btnNgayNhap = new JButton("");
        btnNgayNhap.setIcon(new ImageIcon(PhieuNhapInsert.class.getResource("/Image/calender_icon.png")));
        btnNgayNhap.setBounds(260, 140, 30, 20);
        pnThongTinLeft.add(btnNgayNhap);

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
        lblMaSP.setBounds(20, 80, 120, 20);
        pnThongTinRight.add(lblMaSP);
        
        txtMaSP = new JTextField();
        txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtMaSP.setBounds(150, 80, 100, 20);
        pnThongTinRight.add(txtMaSP);

        JLabel lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblSoLuong.setBounds(20, 110, 120, 20);
        pnThongTinRight.add(lblSoLuong);

        txtSoLuong = new JTextField();
        txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSoLuong.setBounds(150, 110, 100, 20);
        pnThongTinRight.add(txtSoLuong);
        txtSoLuong.setColumns(10);

        JLabel lblThanhTien = new JLabel("Thành tiền");
        lblThanhTien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblThanhTien.setBounds(20, 140, 120, 20);
        pnThongTinRight.add(lblThanhTien);

        txtThanhTien = new JTextField();
        txtThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtThanhTien.setBounds(150, 140, 100, 20);
        txtThanhTien.setEditable(false); // Không thể chỉnh sửa
        pnThongTinRight.add(txtThanhTien);
        txtThanhTien.setColumns(10);
        
        btnMaSP = new JButton("...");
        btnMaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnMaSP.setBounds(260, 80, 30, 20);
        pnThongTinRight.add(btnMaSP);
        
        txtTonKho = new JTextField();
        txtTonKho.setBounds(260, 110, 50, 20);
        pnThongTinRight.add(txtTonKho);
        txtTonKho.setColumns(10);
        
        JLabel lblMaPN_CTHD = new JLabel("Mã phiếu nhập");
        lblMaPN_CTHD.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMaPN_CTHD.setBounds(20, 50, 120, 20);
        pnThongTinRight.add(lblMaPN_CTHD);
        
        txtMaPN_CTHD = new JTextField();
        txtMaPN_CTHD.setEditable(false);
        txtMaPN_CTHD.setBounds(150, 50, 100, 20);
        pnThongTinRight.add(txtMaPN_CTHD);
        txtMaPN_CTHD.setColumns(10);

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
