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

import BUS.LoaiSP_BUS;
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
		   btnXoa.setHorizontalTextPosition(SwingConstants.RIGHT);
		   btnXoa.setIcon(new ImageIcon(KhachHangPanel.class.getResource("/Image/delete2_icon.png")));
		   btnXoa.setBounds(300, 15, 130, 50);
		   btnXoa.setFont(new Font("Tahoma", Font.BOLD, 20));
		   btnXoa.setPreferredSize(new Dimension(150,50));
		   pnTop.add(btnXoa);
		   
//		   ============================================= CENTER =============================================
		   JPanel pnCenter = new JPanel();
		   pnCenter.setBorder(BorderFactory.createLineBorder(Color.black,2));
		   add(pnCenter, BorderLayout.CENTER);
		   pnCenter.setLayout(null);
		   
		   dtmLSP = new DefaultTableModel();
		   dtmLSP.addColumn("Mã loại sản phẩm");
		   dtmLSP.addColumn("Tên loại sản phẩm");
		 
		   addRow(lspBUS.getList());
		   
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
	}
	
	/*
	 * FUNCTION
	 */
	private void addRow(ArrayList<LoaiSP_DTO> list) {
		for (LoaiSP_DTO lsp : list) {
			dtmLSP.addRow(new Object[] {
				lsp.getMaLSP(),
				lsp.getTenLSP()
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
