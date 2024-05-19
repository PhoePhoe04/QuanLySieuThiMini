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
	

	// Lấy dữ liệu
	public ArrayList<HoaDonDTO> layDuLieu(String condition) {
		return this.hoaDonDAO.docDB(condition);
	}
	// Function
	public ArrayList<HoaDonDTO> getList_hoadon() {
		return list_hoadon;
	}

}
