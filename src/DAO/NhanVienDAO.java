package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.KhachHangDTO;
import DTO.NhanVienDTO;

public class NhanVienDAO {
	MyConnect connect;
	
	// ham them
	public int them(NhanVienDTO nhanVien){
		int ketQua = 0;
		connect = new MyConnect();
		
		try {
			HashMap<String, Object> insertValues = new HashMap<String, Object>();
			
			insertValues.put("maNV", nhanVien.getMaNV());
			insertValues.put("hoNV", nhanVien.getHoNhanVien());
			insertValues.put("tenNV", nhanVien.getTenNhanVien());
			insertValues.put("ngaySinh", nhanVien.getNgaySinh());
			insertValues.put("gioiTinh", nhanVien.isGioiTinh());
			insertValues.put("diaChi", nhanVien.getDiaChi());
			insertValues.put("soDienThoai", nhanVien.getSoDienThoai());
			
			ketQua = connect.insert("nhanvien", insertValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return ketQua;
	}
	
	// Hàm sửa
	public int sua(NhanVienDTO nhanVien) {
		int ketQua = 0;
		connect = new MyConnect();
		
		try {
			HashMap<String, Object> updateValues = new HashMap<String, Object>();
			
			updateValues.put("hoNV", nhanVien.getHoNhanVien());
			updateValues.put("tenNV", nhanVien.getTenNhanVien());
			updateValues.put("ngaySinh", nhanVien.getNgaySinh());
			updateValues.put("gioiTinh", nhanVien.isGioiTinh());
			updateValues.put("diaChi", nhanVien.getDiaChi());
			updateValues.put("soDienThoai", nhanVien.getSoDienThoai());
			
			String condition = " maNV = '"+ nhanVien.getMaNV()+"'";
			
			ketQua = connect.update("nhanvien", updateValues, condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}

		return ketQua;
	}
	
	// Hàm xóa
	public int xoa(NhanVienDTO nhanVien) {
		int ketQua = 0;
		connect = new MyConnect();
		
		try {
			String condition = " maNV = '"+ nhanVien.getMaNV()+"'";
			
			ketQua = connect.delete("nhanvien", condition);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
	
		return ketQua;
	}
	
	// Đọc data
	public ArrayList<NhanVienDTO> docDB(String condition, String orderBy) {
		connect = new MyConnect();
		ArrayList<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
		
		try {
			ResultSet rs = connect.select("nhanvien", condition, orderBy);
			while(rs.next()) {
				NhanVienDTO nhanVien = new NhanVienDTO();
				nhanVien.setMaNV(rs.getString("maNV"));
				nhanVien.setHoNhanVien(rs.getString("hoNV"));
				nhanVien.setTenNhanVien(rs.getString("tenNV"));
				nhanVien.setNgaySinh(rs.getDate("ngaySinh"));
				nhanVien.setGioiTinh(rs.getBoolean("gioiTinh"));
				nhanVien.setDiaChi(rs.getString("diaChi"));
				nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
				
				list.add(nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connect.Close();
		}
		
		return list;
	}
	
	public ArrayList<NhanVienDTO> docDB(String condition) {
		return this.docDB(condition, null);
	}
	
	public ArrayList<NhanVienDTO> docDB() {
		return this.docDB(null);
	}
	public ArrayList<NhanVienDTO> timTheoMa(NhanVienDTO nhanvien) throws SQLException{
		connect = new MyConnect();
		String condition = "maNV = '"+nhanvien.getMaNV()+"'";
		ResultSet rs = connect.find("nhanvien", condition);
		ArrayList<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
		
		while(rs.next()) {
			NhanVienDTO nv = new NhanVienDTO();
			
			nv.setMaNV(rs.getString("maNV"));
			nv.setHoNhanVien(rs.getString("hoNV"));
			nv.setTenNhanVien(rs.getString("tenNV"));
			nv.setNgaySinh(rs.getDate("ngaySinh"));
			nv.setGioiTinh(rs.getBoolean("gioiTinh"));
			nv.setDiaChi(rs.getString("diaChi"));
			nv.setSoDienThoai(rs.getString("soDienThoai"));
			
			list.add(nv);
		}
		
		connect.Close();
		return list;
	}

}
