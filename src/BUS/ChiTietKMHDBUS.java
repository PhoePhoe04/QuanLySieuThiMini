package BUS;

import java.util.ArrayList;


import DAO.ChiTietKMHDDAO;
import DTO.ChiTietKMHDDTO;

public class ChiTietKMHDBUS {
	private ChiTietKMHDDAO ctkmhdDAO;
	private ArrayList<ChiTietKMHDDTO> list_ctkmhd;
	
	public ChiTietKMHDBUS() {
		this.ctkmhdDAO = new ChiTietKMHDDAO();
		this.list_ctkmhd = ctkmhdDAO.docDB();
	}

	// Thêm
	public boolean them(ChiTietKMHDDTO ctkmhd) {
		if(this.ctkmhdDAO.them(ctkmhd) > 0) {
			this.list_ctkmhd.add(ctkmhd);
			return true;
		}
		
		return false;
	}
	
	// Sửa
	public boolean sua(ChiTietKMHDDTO ctkmhd) {
		if(this.ctkmhdDAO.sua(ctkmhd) > 0) {
			for(int i = 0; i < this.list_ctkmhd.size(); i++) {
				ChiTietKMHDDTO ctkmhdDTO = list_ctkmhd.get(i);
				if(ctkmhdDTO.getMaKM().equals(ctkmhd.getMaKM()) && ctkmhdDTO.getTienHoaDon() == ctkmhd.getTienHoaDon()) {
					list_ctkmhd.set(i, ctkmhd);
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Xóa
	public boolean xoa(ChiTietKMHDDTO ctkmhd) {
		for(int i = 0; i < list_ctkmhd.size(); i++) {
			ChiTietKMHDDTO ctkmhdDTO = list_ctkmhd.get(i);
			if(ctkmhdDTO.equals(ctkmhd)) {
				if(this.ctkmhdDAO.xoa(ctkmhd) > 0) {
					list_ctkmhd.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	// Main
	public static void main(String[] args) {
		ChiTietKMHDBUS ctkmhdBUS = new ChiTietKMHDBUS();
		ArrayList<ChiTietKMHDDTO> list = ctkmhdBUS.getList_ctkmhd();
		
		ChiTietKMHDDTO ctkmsp = new ChiTietKMHDDTO("KM002", 800000, 0.05);
		
		System.out.println("============ BEFORE ============");
		for (ChiTietKMHDDTO chiTietKMHDDTO : list) {
			System.out.println(chiTietKMHDDTO.toString());
		}
		
		System.out.println("\n");
		System.out.println(ctkmhdBUS.xoa(ctkmsp) ? "YES\n":"NO\n");
		
		System.out.println("============ AFTER ============");
		for (ChiTietKMHDDTO chiTietKMHDDTO : list) {
			System.out.println(chiTietKMHDDTO.toString());
		}
	}
	
	
	// Function
	public ChiTietKMHDDAO getCtkmhdDAO() {
		return ctkmhdDAO;
	}

	public void setCtkmhdDAO(ChiTietKMHDDAO ctkmhdDAO) {
		this.ctkmhdDAO = ctkmhdDAO;
	}

	public ArrayList<ChiTietKMHDDTO> getList_ctkmhd() {
		return list_ctkmhd;
	}

	public void setList_ctkmhd(ArrayList<ChiTietKMHDDTO> list_ctkmhd) {
		this.list_ctkmhd = list_ctkmhd;
	}
}
