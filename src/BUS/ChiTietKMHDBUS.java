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
	
	
	// Function

	public ArrayList<ChiTietKMHDDTO> getList_ctkmhd() {
		return list_ctkmhd;
	}
	
	public ArrayList<ChiTietKMHDDTO> getList_ctkmhd(String condition) {
		return this.ctkmhdDAO.docDB(condition);
	}
}
