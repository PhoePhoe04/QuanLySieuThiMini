package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.SanPham_DTO;
public class SanPham_DAO{
	MyConnect connect;

	public ArrayList<SanPham_DTO> getSanPhams(String condition, String orderBy){
		connect = new MyConnect();
		ArrayList<SanPham_DTO> sanphams = new ArrayList<>();
		try {
			ResultSet resultSet = connect.select("sanpham", condition, orderBy);
			while(resultSet.next()) {
				SanPham_DTO sanpham = new SanPham_DTO();
				sanpham.setMaSP(resultSet.getString("maSP"));
				sanpham.setTenSP(resultSet.getString("tenSP"));
				sanpham.setMaLSP(resultSet.getString("maLSP"));
				sanpham.setDonGia(resultSet.getDouble("donGia"));
				sanpham.setSoLuong(resultSet.getInt("soluong"));
				sanpham.setDonViTinh(resultSet.getString("donViTinh"));
				sanphams.add(sanpham);
			}				
		}catch(SQLException e) {
			System.out.println("Failed to get products" + e.getMessage());
		}finally {
			connect.Close();
		}
		return sanphams;
	}
	
	public ArrayList<SanPham_DTO> getSanPhams(String condition){
		return this.getSanPhams(condition, null);
	}
	
	public ArrayList<SanPham_DTO> getSanPhams(){
		return this.getSanPhams(null);
	}
	
	public int addSanPham(SanPham_DTO sanpham) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maSP", sanpham.getMaSP());
			insertValues.put("tenSP", sanpham.getTenSP());
			insertValues.put("maLSP", sanpham.getMaLSP());
			insertValues.put("donGia", sanpham.getDonGia());
			insertValues.put("soLuong", sanpham.getSoLuong());
			insertValues.put("donViTinh", sanpham.getDonViTinh());
			
			ketQua = connect.insert("sanpham", insertValues);
		} catch (Exception e) {
			System.out.println("Error while adding to the database:" + e.getMessage());
		} finally {
			connect.Close();
		}
		return ketQua;
	}
	
	public int deleteSanPham(SanPham_DTO sanpham) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			String condition = " maSP = '"+ sanpham.getMaSP()+"'";
			ketQua = connect.delete("sanpham", condition);
		} catch(Exception e) {
			System.out.println(" ERROR while deleting from the database" + e.getMessage());
		} finally {
			connect.Close();
		}
		return ketQua;
	}
	
	public int editSanPham(SanPham_DTO sanpham) {
		connect = new MyConnect();
		int ketQua = 0;
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			
			updateValues.put("tenSP", sanpham.getTenSP());
			updateValues.put("maLSP", sanpham.getMaLSP());
			updateValues.put("donGia", sanpham.getDonGia());
			updateValues.put("soLuong", sanpham.getSoLuong());
			updateValues.put("donViTinh", sanpham.getDonViTinh());
			
			String condition = " maSP = '"+ sanpham.getMaSP()+"'";
			
			ketQua = connect.update("sanpham", updateValues, condition);
		}catch(Exception e) {
			System.out.println("ERROR wwhile editing the database: " + e.getMessage());
		}finally {
			connect.Close();
		}
		return ketQua;
	}
}
