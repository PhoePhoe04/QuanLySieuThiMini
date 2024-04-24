package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.LoaiSP_DTO;

public class LoaiSP_DAO extends Connect{
	public ArrayList<LoaiSP_DTO> getLoaiSPs(){
		ArrayList<LoaiSP_DTO> loaisanphams = new ArrayList<>();
		try {
			getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(" SELECT * FROM LoaiSP");
			while(resultSet.next()) {
				LoaiSP_DTO loaisp = new LoaiSP_DTO();
				loaisp.setMaLSP(resultSet.getString("maLSP"));
				loaisp.setTenLSP(resultSet.getString("tenLSP"));
				loaisanphams.add(loaisp);
			}
		} catch(SQLException e) {
			System.out.println("Failed to get products" + e.getMessage());
		}
		return loaisanphams;
	}
	public void addLoaiSP(LoaiSP_DTO loaisp) {
		try {
			getConnection();
			Statement statement =connection.createStatement();
			String query = "INSERT INTO LoaiSP ( maLSP, tenLSP) VALUES ('"
					+ loaisp.getMaLSP() + "','"
					+ loaisp.getTenLSP()+ ")";
			statement.executeUpdate(query);
			closeConnection();
		}catch(SQLException e) {
			System.out.println(" Error while adding to the database" + e.getMessage());
			
		}			
	}
	public void deleteLoaiSP(String id) {
		try {
			getConnection();
			Statement statement = connection.createStatement();
			String query = " DELETE FROM LoaiSP WHERE maLSP = '" + id + "'";
			statement.executeUpdate(query);
		} catch(SQLException e) {
			System.out.println("Error while deleting from the database: " + e.getMessage());
		}
	}
	public void editLoaiSP(LoaiSP_DTO loaisp) {
		try {
			getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE LoaiSP SET maLSP = '"
					+ loaisp.getMaLSP() + "', tenLSP = '"
					+loaisp.getTenLSP() 
					+ "'WHERE maLSP = '" + loaisp.getMaLSP()
					+ "'";
			statement.executeUpdate(query);
			} catch(SQLException e) {
				System.out.println("Error while editing the database: " + e.getMessage());
			}
	}
}
