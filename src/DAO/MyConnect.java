package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import DTO.KhachHangDTO;
import DTO.NhanVienDTO;


public class MyConnect {
	private MySQLConnect connect;
	
// ============================================================== CONTRUCSTOR ==============================================================	
	
	// Tạo kết nối mặc định
	public MyConnect() {
		connect = new MySQLConnect("localhost", "root", "", "quanlysieuthimini");
	}
	
	// Tạo kết nối
	public MyConnect(String host, String username, String password, String database) {
		connect = new MySQLConnect(host, username, password, database);
	}
	
// =========================================================== HÀM XỬ LÝ CHUỖI ===========================================================	
	
	// Thêm điều kiện vào query
	public void addCondition(StringBuilder query, String condition) {
		if(condition != null)
			query.append(" WHERE "+ condition);
	}
	
	// Thêm orderBy vào query
	public void addOrderBy(StringBuilder query, String orderBy) {
		if(orderBy != null)
			query.append(" ORDER BY "+ orderBy);
	}
	
// =========================================================== HÀM THÊM,SỬA,XÓA ===========================================================	
	
	// Hàm insert
	public int insert(String tableName, HashMap<String, Object> columnValues) {
		
		StringBuilder query = new StringBuilder(" INSERT INTO "+ tableName);
		
		StringBuilder values = new StringBuilder();
		
		query.append("(");
		// Duyệt và đưa thông tin tên cột và giá trị values vào
		/*
		 * Phần hashmap là cặp kiểu dữ liệu
		 * ví dụ: table hocsinh gồm hoHS = "Trần", tenHS = "Văn A"
		 * <hoHS, "Trần">, <tenHS, "Văn A">
		 * HashMap<key, object> : key là tên các cột, object là giá trị tương ứng của cột
		 */
		for (String key : columnValues.keySet()) {
			query.append(key + ",");
			if(columnValues.get(key) instanceof String && !columnValues.get(key).equals("null"))
				values.append("'"+ columnValues.get(key)+ "',");
			else if(columnValues.get(key) instanceof Date)
				values.append("'"+ columnValues.get(key)+ "',");// Nếu giá trị là chuỗi hoặc Date sẽ thêm vào '' ngược lại thì truyền thẳng giá trị vào
			else
				values.append(columnValues.get(key)+ ",");
		} /* Sau dòng code này ta đc  INSERT INTO hocsinh(hoHS, tenHS, 
		 * và biến values là :  'Trần','Văn A',
		 */
		
		// cắt dấu dư thừa
		query = query.delete(query.length()-1, query.length()); // bỏ ký tự cuối cùng
		values = values.delete(values.length()-1, values.length());
		/*
		 * ta được INSERT INTO hocsinh(hoHS, tenHS
		 * và values = 'Trần', 'Văn A'
		 */
		
		query.append(") VALUES("+ values.toString()+ ")");
		query.append(";");
		/*
		 * ta được INSERT INTO hocsinh(hoHS, tenHS) VALUES('Trần', 'Văn A');
		 */
		
		System.out.println(query);
		
		return this.connect.excuteUpdate(query.toString());
	}
	
	// Hàm delete
	public int delete(String tableName, String condition) {
		
		StringBuilder query = new StringBuilder(" DELETE FROM "+ tableName);
		this.addCondition(query, condition);
		query.append(";");
		System.out.println(query.toString());
		
		return this.connect.excuteUpdate(query.toString());
	}
	
	// Hàm update
	public int update(String tableName, HashMap<String, Object> columnValues, String condition) {
		StringBuilder query = new StringBuilder(" UPDATE "+ tableName+ " SET ");
		for (String key : columnValues.keySet()) {
			if(columnValues.get(key) instanceof String)
				query.append(key+ " = '"+ columnValues.get(key).toString()+ "',");
			else if(columnValues.get(key) instanceof Date)
				query.append(key+ " = '"+ columnValues.get(key).toString()+ "',");
			else
				query.append(key+ " = " + columnValues.get(key)+ ",");
		}
		query = query.delete(query.length()-1, query.length());
		this.addCondition(query, condition);
		query.append(";");
		System.out.println(query);
		
		return this.connect.excuteUpdate(query.toString());
	}
	
	// Hàm đếm số cột
	public static int getColumnCount(ResultSet result) {
		int soCot = 0;
		try {
			soCot = result.getMetaData().getColumnCount();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soCot;
	}
	
	// Hàm lấy tên cột
	public static String[] getColumnName(ResultSet result) throws SQLException {
		
		ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
		int soCot = rsMetaData.getColumnCount();
		String[] list = new String[soCot];
		for(int i = 0; i < soCot; i++) {
			list[i] = rsMetaData.getColumnName(i);
		}
	
		return list;
	}
	
	// Hàm đóng kết nối
	public void Close() {
		this.connect.Close();
	}
	
// =========================================================== HÀM ĐỌC DỮ LIỆU ===========================================================
	
	// Hàm đọc dữ liệu
	public ResultSet select(String tableName, String condition, String orderBy) {
		StringBuilder query = new StringBuilder(" SELECT * FROM "+ tableName);
		this.addCondition(query, condition);
		this.addOrderBy(query, orderBy);
		query.append(";");
		
		System.out.println(query);
		
		return this.connect.excuteQuery(query.toString());
	}
	public ResultSet select(String tableName, String condition) {
		return this.select(tableName, condition, null);
	}
	
	public ResultSet select(String tableName) {
		return this.select(tableName, null, null);
	}
	
	// Ham Tim
	public ResultSet find(String tableName,String condition) {
		StringBuilder query = new StringBuilder(" SELECT * FROM "+ tableName);
		this.addCondition(query, condition);
		query.append(";");
		
		System.out.println(query);
		
		return this.connect.excuteQuery(query.toString());
	}

	
	public static void main(String[] args) {
		MyConnect cn = new MyConnect();
		try {
			if(cn.connect == null) {
				System.out.println("huhu");
			}else
				System.out.println(cn.connect);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			cn.Close();
		}
	}
}
