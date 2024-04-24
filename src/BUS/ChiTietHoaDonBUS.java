package BUS;

import java.util.ArrayList;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonBUS {
	private ArrayList<ChiTietHoaDonDTO> list_CTHD;
	private ChiTietHoaDonDAO cthdDAO;
	
	public ChiTietHoaDonBUS() {
		this.cthdDAO = new ChiTietHoaDonDAO();
		this.list_CTHD = cthdDAO.docDB();
	}
	
	// Thêm
	public boolean them(ChiTietHoaDonDTO cthd) {
		if(this.cthdDAO.them(cthd) > 0) {
			list_CTHD.add(cthd);
			return true;
		}
		return false;
	}
	
	// Sửa
	public boolean sua(ChiTietHoaDonDTO cthd) {
		if(this.cthdDAO.sua(cthd) > 0) {
			for(int i = 0; i < list_CTHD.size(); i++) {
				ChiTietHoaDonDTO cthdDTO = list_CTHD.get(i);
				if(cthdDTO.getMaHD().equals(cthd.getMaHD())) {
					list_CTHD.set(i, cthd);
					return true;
				}
			}
		}
		return false;
	}
	
	
	// Xóa
	public boolean xoa(ChiTietHoaDonDTO cthd) {
		for(int i = 0; i < list_CTHD.size(); i++) {
			ChiTietHoaDonDTO cthdDTO = list_CTHD.get(i);
			if(cthdDTO.equals(cthd)) {
				if(this.cthdDAO.xoa(cthd) > 0) {
					list_CTHD.set(i, cthd);
					return true;
				}
			}
		}
		return false;
	}

	
	
	// Main
	public static void main(String[] args) {
		ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
		ArrayList<ChiTietHoaDonDTO> list = cthdBUS.getList_CTHD();
		
		ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO();
		
		System.out.println("BEFORE");
		for (ChiTietHoaDonDTO chiTietHoaDonDTO : list) {
			System.out.println(chiTietHoaDonDTO.toString());
		}
		
		System.out.println();
		System.out.println(cthdBUS.them(cthd) ? "YES\n":"NO\n");
		
		System.out.println("AFTER");
		for (ChiTietHoaDonDTO chiTietHoaDonDTO : list) {
			System.out.println(chiTietHoaDonDTO.toString());
		}
	}
	
	// Function
	public ArrayList<ChiTietHoaDonDTO> getList_CTHD() {
		return list_CTHD;
	}

	public void setList_CTHD(ArrayList<ChiTietHoaDonDTO> list_CTHD) {
		this.list_CTHD = list_CTHD;
	}

	public ChiTietHoaDonDAO getCthdDAO() {
		return cthdDAO;
	}

	public void setCthdDAO(ChiTietHoaDonDAO cthdDAO) {
		this.cthdDAO = cthdDAO;
	}
	
}
