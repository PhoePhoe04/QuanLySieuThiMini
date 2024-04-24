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
			if(kmDAO.them(km) > 0) {
				list_KM.add(km);
				return true;
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
		
		// Main
		public static void main(String[] args) {
			KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
			ArrayList<KhuyenMaiDTO> list = kmBUS.getList_KM();
//			public KhuyenMaiDTO(String maKM, String tenKM, String dieuKien, Date ngayBatDau, Date ngayKetThuc) {
			KhuyenMaiDTO km = new KhuyenMaiDTO("KM003", "hcc", "hcc", Date.valueOf("2024-04-21"), Date.valueOf("2025-04-21"));
			
			System.out.println("============ BEFORE ============");
			for (KhuyenMaiDTO khuyenMaiDTO : list) {
				System.out.println(khuyenMaiDTO.toString());
			}
			
			System.out.println("\n");
			System.out.println(kmBUS.xoa(km) ? "YES\n":"NO\n");
			
			System.out.println("============ AFTER ============");
			for (KhuyenMaiDTO khuyenMaiDTO : list) {
				System.out.println(khuyenMaiDTO.toString());
			}
		}
		
		// Function
		public ArrayList<KhuyenMaiDTO> getList_KM() {
			return list_KM;
		}
		public void setList_KM(ArrayList<KhuyenMaiDTO> list_KM) {
			this.list_KM = list_KM;
		}
		public KhuyenMaiDAO getKmDAO() {
			return kmDAO;
		}
		public void setKmDAO(KhuyenMaiDAO kmDAO) {
			this.kmDAO = kmDAO;
		}
	
}
