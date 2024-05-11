package GUI_Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BUS.SanPham_BUS;
import DTO.KhachHangDTO;
import DTO.SanPham_DTO;
import GUI_Dialog.SanPhamInsert;
import GUI_Dialog.SanPhamUpdate;

public class SanPhamPanel extends JPanel {
	private SanPham_BUS spBUS;
	
	private DefaultTableModel dtmSanPham;
	private JTable tblSanPham;
	
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;

	public SanPhamPanel() {
		try {
			spBUS = new SanPham_BUS();
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
		   btnThem.setHorizontalTextPosition(SwingConstants.RIGHT);
		   btnThem.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/add_icon.png")));
		   btnThem.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnThem);
		   
		   btnSua = new JButton("Sửa");
		   btnSua.setHorizontalAlignment(SwingConstants.LEFT);
		   btnSua.setHorizontalTextPosition(SwingConstants.RIGHT);
		   btnSua.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/edit_icon.png")));
		   btnSua.setBounds(160, 15, 130, 50);
		   btnSua.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnSua);
		   
		   btnXoa = new JButton("Xóa");
		   btnXoa.setHorizontalAlignment(SwingConstants.LEFT);
		   btnXoa.setHorizontalTextPosition(SwingConstants.RIGHT);
		   btnXoa.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/delete2_icon.png")));
		   btnXoa.setBounds(300, 15, 130, 50);
		   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		   pnTop.add(btnXoa);
		   
//		   ============================================= CENTER =============================================
		   JPanel pnCenter = new JPanel();
		   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   add(pnCenter, BorderLayout.CENTER);
		   pnCenter.setLayout(null);
		   
		   dtmSanPham = new DefaultTableModel();
		   dtmSanPham.addColumn("Mã sản phẩm");
		   dtmSanPham.addColumn("Tên sản phẩm");
		   dtmSanPham.addColumn("Mã loại");
		   dtmSanPham.addColumn("Đơn giá");
		   dtmSanPham.addColumn("Số lượng");
		   dtmSanPham.addColumn("Đơn vị tính");
		   
		   addRow(spBUS.getList());
		   
		   tblSanPham = new JTable(dtmSanPham);
		   tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 13));
		   
		   JScrollPane scrollPane = new JScrollPane(tblSanPham);
		   scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   scrollPane.setBounds(20, 50, 1060, 540);
		   pnCenter.add(scrollPane);
		   
		   
		   JLabel lblSanPham = new JLabel("SẢN PHẨM");
		   lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 20));
		   lblSanPham.setBounds(20, 10, 200, 30);
		   pnCenter.add(lblSanPham);
		   
	}
	
	/*
	 * ADD ACTIONLISTENER
	 */
	private void addActionListener() {
		btnThem.addActionListener(e ->{
			SanPhamInsert data = new SanPhamInsert();
			if(data.showDialog(this)) {
				SanPham_DTO sp = data.getSanPham();
				if(spBUS.them(sp)) 
					dtmSanPham.addRow(new Object[] {
							sp.getMaSP(),
							sp.getTenSP(),
							sp.getMaLSP(),
							sp.getDonGia(),
							sp.getSoLuong(),
							sp.getDonViTinh()
					});
			}
		});
		
		btnSua.addActionListener(e ->{
			int selectedRow = tblSanPham.getSelectedRow();
			if(selectedRow != -1) {
				SanPham_DTO sp = new SanPham_DTO(
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex()).toString(),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex()).toString(),
						Double.parseDouble(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex()).toString()),
						Integer.parseInt(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex()).toString()),
						tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex()).toString()
						);
				SanPhamUpdate dataUpdated = new SanPhamUpdate(sp);
				if(dataUpdated.showDialog(this)) {
					SanPham_DTO spUpdated = dataUpdated.getSanPham();
					if(spBUS.sua(spUpdated)) {
						dtmSanPham.setValueAt(spUpdated.getMaSP(), selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex());
						dtmSanPham.setValueAt(spUpdated.getTenSP(), selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex());
						dtmSanPham.setValueAt(spUpdated.getMaLSP(), selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex());
						dtmSanPham.setValueAt(spUpdated.getDonGia(), selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex());
						dtmSanPham.setValueAt(spUpdated.getSoLuong(), selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex());
						dtmSanPham.setValueAt(spUpdated.getDonViTinh(), selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex());
					}
					System.out.println(spUpdated.toString());
				}
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm muốn sửa");
			}
		});
		
		btnXoa.addActionListener(e ->{
			int selectedRow = tblSanPham.getSelectedRow();
			if(selectedRow != -1) {
				int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này!", "Xác nhận xóa sản phẩm", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					SanPham_DTO sp = new SanPham_DTO(
							tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã sản phẩm").getModelIndex()).toString(),
							tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Tên sản phẩm").getModelIndex()).toString(),
							tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Mã loại").getModelIndex()).toString(),
							Double.parseDouble(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn giá").getModelIndex()).toString()),
							Integer.parseInt(tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Số lượng").getModelIndex()).toString()),
							tblSanPham.getValueAt(selectedRow, tblSanPham.getColumn("Đơn vị tính").getModelIndex()).toString()
							);
					if(spBUS.xoa(sp))
						dtmSanPham.removeRow(selectedRow);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm muốn xóa");
			}
		});
	}
	
	/*
	 * FUNCTION
	 */
	private void addRow(ArrayList<SanPham_DTO> list) {
		for (SanPham_DTO sp : list) {
			dtmSanPham.addRow(new Object[] {
					sp.getMaSP(),
					sp.getTenSP(),
					sp.getMaLSP(),
					sp.getDonGia(),
					sp.getSoLuong(),
					sp.getDonViTinh()
			});
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
					SanPhamPanel sp = new SanPhamPanel();
					frame.getContentPane().add(sp);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
