package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {
	private HoaDonDAO hoaDonDAO;
	private ArrayList<HoaDonDTO> list_hoadon;
	
	public HoaDonBUS() {
		this.hoaDonDAO = new HoaDonDAO();
		this.list_hoadon = hoaDonDAO.docDB();
	}
	
	// Thêm
	public boolean them(HoaDonDTO hoaDon) {
		if(this.hoaDonDAO.them(hoaDon) > 0) {
			list_hoadon.add(hoaDon);
			return true;
		}
		return false;
	}
	
	// Sửa
	public boolean sua(HoaDonDTO hoaDon) {
		for(int i = 0; i < list_hoadon.size(); i++) {
			HoaDonDTO hoaDonDTO = list_hoadon.get(i);
			if(hoaDonDTO.getMaHD().equals(hoaDon.getMaHD())) {
				if(this.hoaDonDAO.sua(hoaDon) > 0) {
					list_hoadon.set(i, hoaDon);
					return true;
				}
			}
		}
		return false;
	}
	
	// Xóa
	public boolean xoa(HoaDonDTO hoaDon) {
		for(int i = 0; i < list_hoadon.size(); i++) {
			HoaDonDTO hoaDonDTO = list_hoadon.get(i);
			if(hoaDonDTO.equals(hoaDon)) {
				if(this.hoaDonDAO.xoa(hoaDon) > 0) {
					list_hoadon.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean xoa(String maHD) {
		if(hoaDonDAO.xoa(maHD) > 0) {
			for(int i = 0; i < list_hoadon.size(); i++) {
				HoaDonDTO hoaDonDTO = list_hoadon.get(i);
				if(hoaDonDTO.getMaHD().equals(maHD)) {
					list_hoadon.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	// Lấy dữ liệu
	public ArrayList<HoaDonDTO> layDuLieu(String condition) {
		return this.hoaDonDAO.docDB(condition);
	}
	
	// Main
	public static void main(String[] args) {
		HoaDonBUS hoaDonBUS = new HoaDonBUS();
		ArrayList<HoaDonDTO> list = hoaDonBUS.getList_hoadon();
		
//		public HoaDonDTO(String maHD, String maKH, String maNV, String maKM, Date ngayLap, double tongTien) {
		HoaDonDTO hoaDon = new HoaDonDTO("HD001", "KH002", "NV002", null, Date.valueOf("2024-4-22"), 0);
		
		System.out.println("BEFORE");
		for (HoaDonDTO hoaDonDTO : list) {
			System.out.println(hoaDonDTO.toString());
		}
		System.out.println();
		
		System.out.println(hoaDonBUS.them(hoaDon) ? "YES\n":"NO\n");
		
		System.out.println("AFTER");
		for (HoaDonDTO hoaDonDTO : list) {
			System.out.println(hoaDonDTO.toString());
		}
		
	}
	
	// Function

	public HoaDonDAO getHoaDonDAO() {
		return hoaDonDAO;
	}

	public void setHoaDonDAO(HoaDonDAO hoaDonDAO) {
		this.hoaDonDAO = hoaDonDAO;
	}

	public ArrayList<HoaDonDTO> getList_hoadon() {
		return list_hoadon;
	}

	public void setList_hoadon(ArrayList<HoaDonDTO> list_hoadon) {
		this.list_hoadon = list_hoadon;
	}
}
