package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLConnect {
	private String host = "";
	private String username = "";
	private String password = "";
	private String database = "";
	
	Connection connect = null;
	Statement st = null;
	ResultSet rs = null;
	
	
	public MySQLConnect() {}
	
	public MySQLConnect(String host, String username, String password, String database) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	protected void driverTest() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnect() {
		if(this.connect == null) {
			
			// Kiểm tra driver
			driverTest();
			
			// Tạo url kết nối database
			String url = "jdbc:mySQL://"+ this.host+ ":3306/" + this.database;
			
			// Kết nối đến database
			try {
				this.connect = DriverManager.getConnection(url, this.username, this.password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.connect;
	}
	
	protected Statement getStatement() {
		try {
			if(this.st == null) 
				this.st = getConnect().createStatement();
			else
				this.st.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.st;
	}
	
	// Hàm thực thi câu lệnh sql
	public ResultSet excuteQuery(String query) {
		try {
			this.rs = getStatement().executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.rs;
	}
	
	// Hàm thực thi insert, update, delete
	public int excuteUpdate(String query) {
		int res = 0;
		
		try {
			res = getStatement().executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void Close() {
		try {
			if (this.rs != null && this.rs.isClosed()) {
			    this.rs.close();
			    this.rs = null;
			}
			if (this.st != null && this.st.isClosed()) {
			    this.st.close();
			    this.st = null;
			}
			if (this.connect != null && this.connect.isClosed()) {
			    this.connect.close();
			    this.connect = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
