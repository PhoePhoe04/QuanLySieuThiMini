package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

public class TaiKhoanBUS {
	private ArrayList<TaiKhoanDTO> list_TK;
	private TaiKhoanDAO tkDAO;
	
	public TaiKhoanBUS() throws SQLException{
		this.tkDAO = new TaiKhoanDAO();
		this.list_TK = new ArrayList<TaiKhoanDTO>();
		list_TK = tkDAO.docDB();
	}
	
	/*
	 * Thêm 1 tài khoản vào danh sách và database
	 */
	public boolean them(TaiKhoanDTO tk) throws SQLException{
		if(tkDAO.them(tk) > 0) {
			list_TK.add(tk);
			return true;
		}
		return false;
	}
	
	/*
	 * Xóa 1 tài khoản khỏi danh sách và database | tài khoản phải khớp với database
	 */
	public boolean xoa(TaiKhoanDTO tk) throws SQLException{
		for(TaiKhoanDTO taiKhoan : list_TK) {
			if(taiKhoan.equals(tk)) {
				if(tkDAO.xoa(tk) > 0) {
					list_TK.remove(tk);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Sửa 1 tài khoản trong database và trong list (chỉ sửa mật khẩu)
	 */
	public boolean sua(TaiKhoanDTO tk) throws SQLException{
		if(tkDAO.sua(tk) > 0) {
			for (TaiKhoanDTO taikhoan : list_TK) {
				if(taikhoan.getTenDangNhap().equals(tk.getTenDangNhap())) {
					taikhoan.setMatKhau(tk.getMatKhau());
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Kiểm tra đăng nhập
	 */
	public boolean checkLogin(String tenDangNhap, String matKhau) throws SQLException{
		for (TaiKhoanDTO  taiKhoan: list_TK) {
			if(taiKhoan.getTenDangNhap().equals(tenDangNhap) && taiKhoan.getMatKhau().equals(matKhau))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws SQLException{
	}
}
