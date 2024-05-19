package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

public class KhachHangBUS {
	private ArrayList<KhachHangDTO> list_KH;
	private KhachHangDAO khDAO;
	
	public KhachHangBUS() throws SQLException{
		this.khDAO = new KhachHangDAO();
		this.list_KH = new ArrayList<KhachHangDTO>();
		list_KH = khDAO.docDB();
	}
	
	public boolean tim(KhachHangDTO kh) throws SQLException{
		for (KhachHangDTO khachHangDTO: list_KH) {
			if(khachHangDTO.equals(kh)) {
				if (khDAO.timTheoMa(kh)!=null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Thêm
	public boolean them(KhachHangDTO kh) throws SQLException{
		if(check(kh) && unique(kh)) {
			if(khDAO.them(kh) > 0) {
				this.list_KH.add(kh);
				return true;
			}
		}
		return false;
	}
	
	// Sửa
	public boolean sua(KhachHangDTO kh) throws SQLException{
		if(check(kh)){
			if(khDAO.sua(kh) > 0) {
				for (KhachHangDTO khachHangDTO : list_KH) {
					if(khachHangDTO.getMaKH().equals(kh.getMaKH())) {
						int index = list_KH.indexOf(khachHangDTO);
						list_KH.set(index, kh);
						return true;
					}
				}
			}
		}
		return false;	
	}
	
	// Xóa
	public boolean xoa(KhachHangDTO kh) throws SQLException{
		for(KhachHangDTO khachHang : list_KH) {
			if(khachHang.equals(kh)) {
				if(khDAO.xoa(kh) > 0) {
					this.list_KH.remove(khachHang);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Lấy dữ liệu theo condition
	 */
	
	public ArrayList<KhachHangDTO> layDuLieu(String condition){
		return khDAO.docDB(condition);
	}
	
	
	public ArrayList<KhachHangDTO> getListKH(){
		return this.list_KH;
	}
	
	public KhachHangDTO getKH(String maKH) {
		for(int i = 0; i < list_KH.size(); i++) {
			KhachHangDTO kh = list_KH.get(i);
			if(kh.getMaKH().equals(maKH))
				return kh;
		}
		return null;
	}
	
	
//	=============================== CÁC HÀM XỬ LÝ LOGIC ===============================
	private boolean unique(KhachHangDTO kh) {
		for (KhachHangDTO khDTO : list_KH) {
			if(khDTO.getMaKH().equals(kh.getMaKH())) {
				System.out.println("Mã khách hàng đã tồn tại");
				return false;
			}
		}
		return true;
	}
	
	private boolean check(KhachHangDTO kh) {
		// Kiểm tra số điện thoại
		if(kh.getSoDienThoai().length() != 10 || !kh.getSoDienThoai().matches("[0-9]+")) {
			return false;
		}

		// Kiểm tra gmail
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(kh.getGmail());
		if(!matcher.matches()) {
			System.out.println("Email không hợp lệ");
			return false;
		}
		
		// Kiểm tra tên
		if(kh.getTenKH().matches("[\\p{L}\\s]+") && kh.getTenKH().matches("[\\p{L}\\s]+")) {
			return true;
		}
			
		return true;
	}
	
	public static void main(String[] args) {
		try {
			KhachHangBUS kh = new KhachHangBUS();
			ArrayList<KhachHangDTO> lst = kh.getListKH();
			System.out.println(lst.size());
			for (KhachHangDTO khachHangDTO : lst) {
				System.out.println(khachHangDTO.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
