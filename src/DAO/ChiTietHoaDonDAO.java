package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonDAO {
	MyConnect connect;
	
	// Thêm
	public int them(ChiTietHoaDonDTO cthd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maHD", cthd.getMaHD());
			insertValues.put("maSP", cthd.getMaSP());
			insertValues.put("maKM", cthd.getMaKM());
			insertValues.put("soLuong", cthd.getSoLuong());
			insertValues.put("donGia", cthd.getDonGia());
			insertValues.put("thanhTien", cthd.getThanhTien());
			
			ketQua = connect.insert("chitiethoadon", insertValues);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Sửa
	public int sua(ChiTietHoaDonDTO cthd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			
			updateValues.put("soLuong", cthd.getSoLuong());
			updateValues.put("donGia", cthd.getDonGia());
			updateValues.put("thanhTien", cthd.getThanhTien());
			
			String condition = " maHD = '"+ cthd.getMaHD()+ "'"+ " && "+ " maSP ='"+ cthd.getMaSP()+ "'"+ " && "+ " maKM ='"+cthd.getMaKM()+  "'"; 
			
			ketQua = connect.update("chitiethoadon", updateValues, condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Xóa
	public int xoa(ChiTietHoaDonDTO cthd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maHD = '"+ cthd.getMaHD()+ "'"+ " && "+ " maSP ='"+ cthd.getMaSP()+ "'"+ " && "+ " maKM ='"+cthd.getMaKM()+  "'"; 
			
			ketQua = connect.delete("chitiethoadon", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Đọc dữ liệu
	public ArrayList<ChiTietHoaDonDTO> docDB(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<ChiTietHoaDonDTO> list = new ArrayList<ChiTietHoaDonDTO>();
		
		try {
			ResultSet rs = connect.select("chitiethoadon", condition, orderBy);
			while(rs.next()) {
				ChiTietHoaDonDTO cthd = new ChiTietHoaDonDTO(
						rs.getString("maHD"),
						rs.getString("maSP"),
						rs.getString("maKM"),
						rs.getInt("soLuong"),
						rs.getDouble("donGia"),
						rs.getDouble("thanhTien")
						);
				list.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return list;
	}
	
	public ArrayList<ChiTietHoaDonDTO> docDB(String condition){
		return this.docDB(condition, null);
	}
	
	public ArrayList<ChiTietHoaDonDTO> docDB(){
		return this.docDB(null);
	}
}
