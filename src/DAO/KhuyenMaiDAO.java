package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.KhuyenMaiDTO;

public class KhuyenMaiDAO {
	MyConnect connect;
	
	// Thêm
	public int them(KhuyenMaiDTO km) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maKM", km.getMaKM());
			insertValues.put("tenKM", km.getTenKM());
			insertValues.put("dieuKien", km.getDieuKien());
			insertValues.put("ngayBatDau", km.getNgayBatDau());
			insertValues.put("ngayKetThuc", km.getNgayKetThuc());
			
			ketQua = connect.insert("khuyenmai", insertValues);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	
	// Sửa
	public int sua(KhuyenMaiDTO km) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			
			updateValues.put("tenKM", km.getTenKM());
			updateValues.put("dieuKien", km.getDieuKien());
			updateValues.put("ngayBatDau", km.getNgayBatDau());
			updateValues.put("ngayKetThuc", km.getNgayKetThuc());
			
			String condition = " maKM = '"+ km.getMaKM()+ "'";
			
			ketQua = connect.update("khuyenmai", updateValues, condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Xóa
	public int xoa(KhuyenMaiDTO km) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maKM = '"+ km.getMaKM()+ "'";
			
			ketQua = connect.delete("khuyenmai", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Đọc dữ liệu
	public ArrayList<KhuyenMaiDTO> docDB(String condition, String orderBy) {
		connect = new MyConnect();
		ArrayList<KhuyenMaiDTO> list = new ArrayList<KhuyenMaiDTO>();
		
		try {
			ResultSet rs = connect.select("khuyenmai", condition, orderBy);
//			public KhuyenMaiDTO(String maKM, String tenKM, String dieuKien, Date ngayBatDau, Date ngayKetThuc) {
			while(rs.next()) {
				KhuyenMaiDTO km = new KhuyenMaiDTO(
						rs.getString("maKM"),
						rs.getString("tenKM"),
						rs.getString("dieuKien"),
						rs.getDate("ngayBatDau"),
						rs.getDate("ngayKetThuc")
						);
				list.add(km);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return list;
	}
	
	public ArrayList<KhuyenMaiDTO> docDB(String condition) {
		return this.docDB(condition, null);
	}
	
	public ArrayList<KhuyenMaiDTO> docDB() {
		return this.docDB(null);
	}
	
}
