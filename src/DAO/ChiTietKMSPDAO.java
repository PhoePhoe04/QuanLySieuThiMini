package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.ChiTietKMSPDTO;

public class ChiTietKMSPDAO {
	MyConnect connect;
	
	// Thêm
	public int them(ChiTietKMSPDTO ctkmsp) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maKM", ctkmsp.getMaKM());
			insertValues.put("maSP", ctkmsp.getMaSP());
			insertValues.put("tiLeGiamGia", ctkmsp.getTileGiamGia());
			
			ketQua = connect.insert("chitietkmsp", insertValues);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Sửa
	public int sua(ChiTietKMSPDTO ctkmsp) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
		
			insertValues.put("tiLeGiamGia", ctkmsp.getTileGiamGia());
			
			String condition = " maKM = '"+ctkmsp.getMaKM()+"'" + " && "+ " maSP ='"+ ctkmsp.getMaSP()+ "'";
			
			ketQua = connect.update("chitietkmsp", insertValues, condition);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Xóa
	public int xoa(ChiTietKMSPDTO ctkmsp) {
		connect = new MyConnect();
		int ketQua = 0;
		
		try {
			String condition = " maKM = '"+ctkmsp.getMaKM()+"'" + " && "+ " maSP ='"+ ctkmsp.getMaSP()+ "'";
			
			ketQua = connect.delete("chitietkmsp", condition);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Đọc dữ liệu
	public ArrayList<ChiTietKMSPDTO> docDB(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<ChiTietKMSPDTO> list = new ArrayList<ChiTietKMSPDTO>();
		
		try {
			ResultSet rs = connect.select("chitietkmsp", condition, orderBy);
			
			while(rs.next()) {
				ChiTietKMSPDTO ctkmsp = new ChiTietKMSPDTO(
						rs.getString("maKM"),
						rs.getString("maSP"),
						rs.getDouble("tiLeGiamGia")
						);
				list.add(ctkmsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return list;
	}
	
	public ArrayList<ChiTietKMSPDTO> docDB(String condition){
		return this.docDB(condition, null);
	}
	
	public ArrayList<ChiTietKMSPDTO> docDB(){
		return this.docDB(null);
	}
}
