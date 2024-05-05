package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.LoaiSP_DTO;

public class LoaiSP_DAO {
	MyConnect connect;
	
	public ArrayList<LoaiSP_DTO> getLoaiSPs(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<LoaiSP_DTO> list = new ArrayList<LoaiSP_DTO>();
		
		try {
			ResultSet rs = connect.select("loaisanpham", condition, orderBy);
			while(rs.next()) {
				LoaiSP_DTO lsp = new LoaiSP_DTO(rs.getString("maLSP"), rs.getString("tenLSP"));
				list.add(lsp);
				}
		} catch(SQLException e) {
			System.out.println("Failed to get products" + e.getMessage());
		} finally {
			connect.Close();
		}
		return list;
	}
	
	public ArrayList<LoaiSP_DTO> getLoaiSPs(String condition){
		return this.getLoaiSPs(condition, null);
	}
	
	public ArrayList<LoaiSP_DTO> getLoaiSPs(){
		return this.getLoaiSPs(null);
	}
	
	public int addLoaiSP(LoaiSP_DTO loaisp) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maLSP", loaisp.getMaLSP());
			insertValues.put("tenLSP", loaisp.getTenLSP());
			
			ketQua = connect.insert("loaisanpham", insertValues);
		}catch(Exception e) {
			System.out.println(" Error while adding to the database" + e.getMessage());
			
		}finally {
			connect.Close();
		}
		return ketQua;
	}
	
	public int deleteLoaiSP(LoaiSP_DTO loaisp) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			String condition = " maLSP = '"+ loaisp.getMaLSP()+"'";
			ketQua = connect.delete("loaisanpham", condition);
		} catch(Exception e) {
			System.out.println("Error while deleting from the database: " + e.getMessage());
		}finally {
			connect.Close();
		}
		return ketQua;
	}
	
	public int editLoaiSP(LoaiSP_DTO loaisp) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			updateValues.put("tenLSP", loaisp.getTenLSP());
			
			String condition = " maLSP = '"+ loaisp.getMaLSP()+"'";
			
			ketQua = connect.update("loaisanpham", updateValues, condition);
		} catch(Exception e) {
			System.out.println("Error while deleting from the database: " + e.getMessage());
		}finally {
			connect.Close();
		}
		return ketQua;
	}
}
