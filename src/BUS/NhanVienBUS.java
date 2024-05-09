package BUS;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
	private ArrayList<NhanVienDTO> list_NV;
	private NhanVienDAO nvDAO;
	
	public NhanVienBUS() throws SQLException{
		this.nvDAO = new NhanVienDAO();
		this.list_NV = new ArrayList<NhanVienDTO>();
		this.list_NV = nvDAO.docDB();
	}
	public boolean tim(NhanVienDTO nv) throws SQLException{
		for (NhanVienDTO nhanVienDTO : list_NV) {
			if(nhanVienDTO.equals(nv)) {
				if (nvDAO.timTheoMa(nv)!=null) {
					return true;
				}
			}
		}
		
		return false;
	}
	/*
	 * Thêm 1 nhân viên vào danh sách và database
	 */
	public boolean them(NhanVienDTO nv) throws SQLException{
		if(nv.getSoDienThoai().length() != 10 || checkSoDienThoai(nv.getSoDienThoai()) || !(checkMaNV(nv.getMaNV())) )
			return false;
		if(nvDAO.them(nv) > 0) {
			this.list_NV.add(nv);
			return true;
		}
		return false;
	}
	
	/*
	 * Xóa 1 nhân viên có trong danh sách và database | nhân viên phải khớp với danh sách từ database
	 */
	public boolean xoa(NhanVienDTO nv) throws SQLException{
		for (NhanVienDTO nhanVienDTO : list_NV) {
			if(nhanVienDTO.equals(nv)) {
				if(nvDAO.xoa(nv) > 0) {
					this.list_NV.remove(nv);
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Sửa 1 nhân viên 
	 */
	public boolean sua(NhanVienDTO nv) throws SQLException{
//		if(nv.getSoDienThoai().length() != 10 || checkSoDienThoai(nv.getSoDienThoai()) || !(checkMaNV(nv.getMaNV())) )
//			return false;
		if(nvDAO.sua(nv) > 0) {
			for (NhanVienDTO nhanVienDTO : list_NV) {
				if(nhanVienDTO.getMaNV().equals(nv.getMaNV())) {
					int i = list_NV.indexOf(nhanVienDTO);
					list_NV.set(i, nv);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * Lấy dữ liệu theo condition
	 */
	public ArrayList<NhanVienDTO> layDuLieu(String condition){
		return nvDAO.docDB(condition);
	}
	
	public ArrayList<NhanVienDTO> getList_NV(){
		return this.list_NV;
	}
	
	// ----------------- Các hàm hỗ trợ xử lý -----------------
	private boolean checkSoDienThoai(String sdt) {
		for (int i = 0; i < sdt.length(); i++) {
			if(Character.isLetter(sdt.charAt(i)))
				return true;
		}
		return false;
	}
	private boolean checkMaNV(String maNV) {
		for (NhanVienDTO nvDTO : list_NV) {
			if(nvDTO.getMaNV().equals(maNV))
				return false;
		}
		return true;
	}
	
	
}
