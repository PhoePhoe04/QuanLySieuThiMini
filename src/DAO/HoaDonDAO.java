package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.HoaDonDTO;

public class HoaDonDAO {
	MyConnect connect;
	
	// Thêm
	public int them(HoaDonDTO hoaDon) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			insertValues.put("maHD", hoaDon.getMaHD());
			insertValues.put("maKH", hoaDon.getMaKH());
			insertValues.put("maNV", hoaDon.getMaNV());
			insertValues.put("maKM", hoaDon.getMaKM());
			insertValues.put("ngayLap", hoaDon.getNgayLap());
			insertValues.put("tongTien", hoaDon.getTongTien());
			
			ketQua = connect.insert("hoadon", insertValues);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Sửa
	public int sua(HoaDonDTO hoaDon) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			updateValues.put("maKH", hoaDon.getMaKH());
			updateValues.put("maNV", hoaDon.getMaNV());
			updateValues.put("maKM", hoaDon.getMaKM());
			updateValues.put("ngayLap", hoaDon.getNgayLap());
			updateValues.put("tongTien", hoaDon.getTongTien());
			
			String condition = " maHD = '"+  hoaDon.getMaHD()+ "'";
			
			ketQua = connect.update("hoadon", updateValues, condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Xóa
	public int xoa(HoaDonDTO hoaDon) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maHD = '"+  hoaDon.getMaHD()+ "'";
			
			ketQua = connect.delete("hoadon", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	public int xoa(String maHD) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maHD = '"+ maHD+ "'";
			
			ketQua = connect.delete("hoadon", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Đọc dữ liệu
	public ArrayList<HoaDonDTO> docDB(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<HoaDonDTO> list = new ArrayList<HoaDonDTO>();
		
		try {
			ResultSet rs = connect.select("hoadon", condition, orderBy);
			while(rs.next()) {
				HoaDonDTO hoaDon = new HoaDonDTO(
						rs.getString("maHD"),
						rs.getString("maKH"),
						rs.getString("maNV"),
						rs.getString("maKM"),
						rs.getDate("ngayLap"),
						rs.getDouble("tongTien")
						);
				list.add(hoaDon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return list;
	}
	
	public ArrayList<HoaDonDTO> docDB(String condition){
		return this.docDB(condition, null);
	}
	
	public ArrayList<HoaDonDTO> docDB(){
		return this.docDB(null);
	}
}
