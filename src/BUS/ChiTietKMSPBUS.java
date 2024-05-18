package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.ChiTietKMSPDAO;
import DTO.ChiTietKMSPDTO;

public class ChiTietKMSPBUS {
	private ArrayList<ChiTietKMSPDTO> list_CTKMSP;
	private ChiTietKMSPDAO ctkmspDAO;
	
	public ChiTietKMSPBUS() {
		this.ctkmspDAO = new ChiTietKMSPDAO();
		this.list_CTKMSP = ctkmspDAO.docDB();
	}
	
	// Thêm 
	public boolean them(ChiTietKMSPDTO ctkmsp) {
		if(ctkmspDAO.them(ctkmsp) > 0) {
			list_CTKMSP.add(ctkmsp);
			return true;
		}
		
		return false;
	}
	
	// Sửa
	public boolean sua(ChiTietKMSPDTO ctkmsp) {
		for (int i = 0 ; i < list_CTKMSP.size(); i++) {
			ChiTietKMSPDTO chiTietKMSPDTO = list_CTKMSP.get(i);
			if(chiTietKMSPDTO.getMaKM().equals(ctkmsp.getMaKM()) && chiTietKMSPDTO.getMaSP().equals(ctkmsp.getMaSP())) {
				if(ctkmspDAO.sua(ctkmsp) > 0) {
					list_CTKMSP.set(i, ctkmsp);
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Xóa
	public boolean xoa(ChiTietKMSPDTO ctkmsp) {
		for(int i = 0; i < list_CTKMSP.size(); i++) {
			ChiTietKMSPDTO chiTietKMSPDTO = list_CTKMSP.get(i);
			if(chiTietKMSPDTO.equals(ctkmsp)) {
				if(ctkmspDAO.xoa(ctkmsp) > 0) {
					list_CTKMSP.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	// Function
	public ArrayList<ChiTietKMSPDTO> getList_CTKMSP() {
		return list_CTKMSP;
	}

	public ArrayList<ChiTietKMSPDTO> getList_CTKMSP(String condition) {
		return ctkmspDAO.docDB(condition);
	}
	
}
