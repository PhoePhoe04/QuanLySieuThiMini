package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import DTO.TaiKhoanDTO;

public class TaiKhoanDAO {
	MyConnect connect;
	
	// Thêm
	public int them(TaiKhoanDTO tk) throws SQLException{
		connect = new MyConnect();
		
		HashMap<String, Object> insertValues = new HashMap<String, Object>();
		insertValues.put("tenDangNhap", tk.getTenDangNhap());
		insertValues.put("matKhau", tk.getMatKhau());
		
		int ketQua = connect.insert("taikhoan", insertValues);
		
		connect.Close();
		return ketQua;
	}
	
	// Sửa
	public int sua(TaiKhoanDTO tk) throws SQLException{
		connect = new MyConnect();
		
		HashMap<String, Object> updateValues = new HashMap<String, Object>();
		updateValues.put("matKhau", tk.getMatKhau());
		
		String condition = " tenDangNhap = '"+tk.getTenDangNhap()+"'";
		
		int ketQua = connect.update("taikhoan", updateValues, condition);
		
		connect.Close();
		return ketQua;
	}
	
	// Xóa
	public int xoa(TaiKhoanDTO tk) throws SQLException{
		connect = new MyConnect();
		
		String condition = " tenDangNhap = '"+tk.getTenDangNhap()+"'";
		
		int ketQua = connect.delete("taikhoan", condition);
		
		connect.Close();
		return ketQua;
	}
	
	// Đọc dữ liệu từ SQL
	public ArrayList<TaiKhoanDTO> docDB(String condition, String orderBy) throws SQLException{
		connect = new MyConnect();
		
		ResultSet rs = this.connect.select("taikhoan", condition, orderBy);
		ArrayList<TaiKhoanDTO> list = new ArrayList<TaiKhoanDTO>();
		while(rs.next()) {
			TaiKhoanDTO tk = new TaiKhoanDTO();
			tk.setTenDangNhap(rs.getString("tenDangNhap"));
			tk.setMatKhau(rs.getString("matKhau"));
			
			list.add(tk);
		}
		connect.Close();
		return list;
	}
	
	public ArrayList<TaiKhoanDTO> docDB(String condition) throws SQLException{
		return this.docDB(condition, null);
	}
	
	public ArrayList<TaiKhoanDTO> docDB() throws SQLException{
		return this.docDB(null);
	}


	
	
	public static void main(String[] args) {
		TaiKhoanDAO tkDAO = new TaiKhoanDAO();
		TaiKhoanDTO tk = new TaiKhoanDTO("duy", "22222");
		
//		Them - done
//		int ketQua = tkDAO.them(tk);
//		System.out.println(ketQua);
		
//		Sua - done
//		int kq = tkDAO.sua(tk);
//		System.out.println(kq);
		
//		Xoa - done
//		int kq = tkDAO.xoa(tk);
//		System.out.println(kq);
		
//		DocDB -done
//		try {
//			ArrayList<TaiKhoanDTO> list = new ArrayList<TaiKhoanDTO>();
//			list = tkDAO.docDB();
//			for (TaiKhoanDTO taiKhoanDTO : list) {
//				System.out.println(taiKhoanDTO.toString());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
