package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.KhachHangDTO;

public class KhachHangDAO {
	private MyConnect connect;
	
	// Hàm thêm
	public int them(KhachHangDTO khachHang) throws SQLException{
		connect = new MyConnect();
		
		HashMap<String, Object> insertValues = new HashMap<String, Object>();
		
		insertValues.put("maKH", khachHang.getMaKH());
		insertValues.put("hoKH", khachHang.getHoKH());
		insertValues.put("tenKH", khachHang.getTenKH());
		insertValues.put("gioiTinh", khachHang.isGioiTinh());
		insertValues.put("diaChi", khachHang.getDiaChi());
		insertValues.put("soDienThoai", khachHang.getSoDienThoai());
		insertValues.put("gmail", khachHang.getGmail());
		
		int kq = connect.insert("khachhang", insertValues);
		
		connect.Close();
		return kq;
	}
	
	// Hàm sửa
	public int sua(KhachHangDTO khachHang) throws SQLException{
		connect = new MyConnect();
		
		HashMap<String, Object> updateValues = new HashMap<String, Object>();
		
		updateValues.put("hoKH", khachHang.getHoKH());
		updateValues.put("tenKH", khachHang.getTenKH());
		updateValues.put("gioiTinh", khachHang.isGioiTinh());
		updateValues.put("diaChi", khachHang.getDiaChi());
		updateValues.put("soDienThoai", khachHang.getSoDienThoai());
		updateValues.put("gmail", khachHang.getGmail());
		
		String condition = " maKH = '"+ khachHang.getMaKH()+ "'";
		
		int kq = connect.update("khachhang", updateValues, condition);
		
		connect.Close();
		return kq;
	}
	
	// Hàm xóa
	public int xoa(KhachHangDTO khachHang) throws SQLException{
		connect = new MyConnect();
		
		String condition = " maKH = '"+ khachHang.getMaKH()+ "'";
		
		int kq = connect.delete("khachhang", condition);
		
		connect.Close();
		return kq;
	}
	
	// Đọc dữ liệu
	public ArrayList<KhachHangDTO> docDB(String condition, String orderBy) throws SQLException{
		connect = new MyConnect();
		
		ResultSet rs = connect.select("khachhang", condition, orderBy);
		ArrayList<KhachHangDTO> list = new ArrayList<KhachHangDTO>();
		
		while(rs.next()) {
			KhachHangDTO kh = new KhachHangDTO(
					rs.getString("maKH"),
					rs.getString("hoKH"),
					rs.getString("tenKH"),
					rs.getBoolean("gioiTinh"),
					rs.getString("diaChi"),
					rs.getString("soDienThoai"),
					rs.getString("gmail")
					);
			
			list.add(kh);
		}
		
		connect.Close();
		return list;
	}
	
	public ArrayList<KhachHangDTO> docDB(String condition) throws SQLException{
		return this.docDB(condition, null);
	}
	
	public ArrayList<KhachHangDTO> docDB() throws SQLException{
		return this.docDB(null);
	}
	
	
	public ArrayList<KhachHangDTO> timTheoMa(KhachHangDTO khachhang) throws SQLException{
		connect = new MyConnect();
		String condition = "maKH = '"+khachhang.getMaKH()+"'";
		ResultSet rs = connect.find("khachhang", condition);
		ArrayList<KhachHangDTO> list = new ArrayList<KhachHangDTO>();
		
		while(rs.next()) {
			KhachHangDTO kh = new KhachHangDTO();
			
			kh.setMaKH(rs.getString("maKH"));
			kh.setHoKH(rs.getString("hoKH"));
			kh.setTenKH(rs.getString("tenKH"));
			kh.setGioiTinh(rs.getBoolean("gioiTinh"));
			kh.setDiaChi(rs.getString("diaChi"));
			kh.setSoDienThoai(rs.getString("soDienThoai"));
			kh.setGmail(rs.getString("gmail"));
			
			list.add(kh);
		}
		
		connect.Close();
		return list;
	}
}
