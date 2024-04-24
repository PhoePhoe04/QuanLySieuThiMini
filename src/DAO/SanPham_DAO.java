package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.SanPham_DTO;
public class SanPham_DAO extends Connect{

	public ArrayList<SanPham_DTO> getSanPhams(){
		ArrayList<SanPham_DTO> sanphams = new ArrayList<>();
		try {
			getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(" SELECT * FROM SanPham");
			while(resultSet.next()) {
				SanPham_DTO sanpham = new SanPham_DTO();
				sanpham.setMaSP(resultSet.getString("maSP"));
				sanpham.setTenSP(resultSet.getString("tenSP"));
				sanpham.setMaLSP(resultSet.getString("maLSP"));
				sanpham.setDonGia(resultSet.getString("donGia"));
				sanpham.setSoLuong(resultSet.getString("soluong"));
				sanpham.setDonViTinh(resultSet.getString("donViTinh"));
				sanphams.add(sanpham);
			}				
		}catch(SQLException e) {
			System.out.println("Failed to get products" + e.getMessage());
		}
		return sanphams;
	}
	
	public void addSanPham(SanPham_DTO sanpham) {
		try {
			getConnection();
			Statement statement = connection.createStatement();
			String query = "INSERT INTO SanPham(maSP, tenSP, maLSP,donGia, soLuong, donViTinh) VALUES ('"
					+ sanpham.getMaSP()+"', '"
					+ sanpham.getTenSP()+"', '"
					+ sanpham.getMaLSP()+"', '"
					+ sanpham.getDonGia()+"','"
					+ sanpham.getSoLuong()+"','"
					+ sanpham.getDonViTinh()+ ")";
			statement.executeUpdate(query);
			closeConnection();
		} catch (SQLException e) {
			System.out.println("Error while adding to the database:" + e.getMessage());
		}
	}
	
	public void deleteSanPham(String id) {
		try {
			getConnection();
			Statement statement = connection.createStatement();
			String query = "DELETE FROM SanPham WHERE maSp = '"+ id + "'";
			statement.executeUpdate(query);	
		} catch(SQLException e) {
			System.out.println(" ERROR while deleting from the database" + e.getMessage());
		}
		
	}
	
	public void editSanPham(SanPham_DTO sanpham) {
		try {
			getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE SanPham SET maSP = '"
					+ sanpham.getMaSP() + "', tenSP = '"
					+ sanpham.getTenSP() + "', maLSP = '"
					+ sanpham.getMaLSP() + "', donGia = '"
					+ sanpham.getDonGia() + "', soLuong = '"
					+ sanpham.getSoLuong() + "', donViTinh ='"
					+ sanpham.getDonViTinh() 
					+ "' WHERE maSP = '" + sanpham.getMaSP() 
					+ "'";
			statement.executeUpdate(query);
		}catch(SQLException e) {
			System.out.println("ERROR wwhile editing the database: " + e.getMessage());
		}
	
	}
}
