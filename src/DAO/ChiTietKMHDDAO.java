package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.ChiTietKMHDDTO;

public class ChiTietKMHDDAO {
	MyConnect connect;
	
	// Thêm
	public int them(ChiTietKMHDDTO ctkmhd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			insertValues.put("maKM", ctkmhd.getMaKM());
			insertValues.put("tienHoaDon", ctkmhd.getTienHoaDon());
			insertValues.put("tiLeGiamGia", ctkmhd.getTiLeGiamGia());
			
			ketQua = connect.insert("chitietkmhd", insertValues);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Sửa
	public int sua(ChiTietKMHDDTO ctkmhd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			updateValues.put("tiLeGiamGia", ctkmhd.getTiLeGiamGia());
			
			String condition = " maKM = '"+ ctkmhd.getMaKM()+ "'"+ " && "+ " tienHoaDon = '"+ ctkmhd.getTienHoaDon()+ "'";
			
			ketQua = connect.update("chitietkmhd", updateValues, condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Xóa
	public int xoa(ChiTietKMHDDTO ctkmhd) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maKM = '"+ ctkmhd.getMaKM()+ "'"+ " && "+ " tienHoaDon = '"+ ctkmhd.getTienHoaDon()+ "'";
			
			ketQua = connect.delete("chitietkmhd", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Đọc dữ liệu
	public ArrayList<ChiTietKMHDDTO> docDB(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<ChiTietKMHDDTO> list = new ArrayList<ChiTietKMHDDTO>();
		
		try {
			ResultSet rs = connect.select("chitietkmhd", condition, orderBy);
			while(rs.next()) {
				ChiTietKMHDDTO ctkmhd = new ChiTietKMHDDTO(
						rs.getString("maKM"),
						rs.getDouble("tienHoaDon"),
						rs.getDouble("tiLeGiamGia")
						);
				list.add(ctkmhd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<ChiTietKMHDDTO> docDB(String condition){
		return this.docDB(condition, null);
	}
	
	public ArrayList<ChiTietKMHDDTO> docDB(){
		return this.docDB(null);
	}
	
}
