package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;

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
		if(check(kh)) {
			if(khDAO.them(kh) > 0) {
				this.list_KH.add(kh);
				return true;
			}
		}
		return false;
	}
	
	// Sửa
	public boolean sua(KhachHangDTO kh) throws SQLException{
		if(khDAO.sua(kh) > 0) {
			for (KhachHangDTO khachHangDTO : list_KH) {
				if(khachHangDTO.getMaKH().equals(kh.getMaKH())) {
					int index = list_KH.indexOf(khachHangDTO);
					list_KH.set(index, kh);
					return true;
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
	
	
//	=============================== CÁC HÀM XỬ LÝ LOGIC ===============================
	private boolean check(KhachHangDTO kh) {
		// Kiểm tra mã khách hàng
		for (KhachHangDTO khDTO : list_KH) {
			if(khDTO.getMaKH().equals(kh.getMaKH())) {
				System.out.println("Mã khách hàng đã tồn tại");
				return false;
			}
		}
		// Kiểm tra số điện thoại
		if(kh.getSoDienThoai().length() != 10) {
			System.out.println("Số điện thoại phải có 10 chữ số");
			return false;
		}
			
		for(int i = 0; i < kh.getSoDienThoai().length(); i++) {
			if(Character.isLetter(kh.getSoDienThoai().charAt(i))) {
				System.out.println("Số điện thoại chỉ có số");
				return false;
			}
				
		}
		// Kiểm tra gmail
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(kh.getGmail());
		if(!matcher.matches()) {
			System.out.println("Email không hợp lệ");
			return false;
		}
			
		return true;
	}
	

}
