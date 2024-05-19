package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

public class KhuyenMaiBUS {
	private ArrayList<KhuyenMaiDTO> list_KM;
	private KhuyenMaiDAO kmDAO;
	
	public KhuyenMaiBUS() {
		this.kmDAO = new KhuyenMaiDAO();
		this.list_KM = kmDAO.docDB();
	}	
		// Thêm
		public boolean them(KhuyenMaiDTO km) {
			if(unique(km)) {
				if(kmDAO.them(km) > 0) {
					list_KM.add(km);
					return true;
				}
			}
			return false;
		}
		
		// Sửa
		public boolean sua(KhuyenMaiDTO km) {
			if(kmDAO.sua(km) > 0) {
				for (KhuyenMaiDTO khuyenMaiDTO : list_KM) {
					if(khuyenMaiDTO.getMaKM().equals(km.getMaKM())) {
						int index = list_KM.indexOf(khuyenMaiDTO);
						list_KM.set(index, km);
						return true;
					}
				}
			}
			return false;
		}
		
		// Xóa
		public boolean xoa(KhuyenMaiDTO km) {
			for (KhuyenMaiDTO khuyenMaiDTO : list_KM) {
				if(khuyenMaiDTO.equals(km)) {
					if(kmDAO.xoa(km) > 0) {
						list_KM.remove(khuyenMaiDTO);
						return true;
					}
				}
			}
			
			return false;
		}
		
		// Function
		public ArrayList<KhuyenMaiDTO> getList_KM() {
			return list_KM;
		}
		
		public ArrayList<KhuyenMaiDTO> getList_KM(String condition){
			return this.kmDAO.docDB(condition);
		}
		
		public KhuyenMaiDTO getKM(String maKM) {
			for(int i = 0; i < list_KM.size(); i++) {
				KhuyenMaiDTO km = list_KM.get(i);
				return km;
			}
			return null;
		}
		
		// Check
		private boolean unique(KhuyenMaiDTO km) {
			for(int i = 0; i < list_KM.size(); i++) {
				if(km.getMaKM().equals(list_KM.get(i).getMaKM()))
					return false;
			}
			
			return true;
		}
		
	
}
