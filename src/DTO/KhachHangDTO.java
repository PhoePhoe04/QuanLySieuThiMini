package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class KhachHangDTO {
	private String maKH;
	private String hoKhachHang;
	private String tenKhachHang;

	private String diaChi;
	private boolean gioiTinh;
	private String soDienThoai;
	private String gmail;
	
	

	public KhachHangDTO() {
		super();
	}

	public KhachHangDTO(String maKH, String hoKhachHang, String tenKhachHang, String diaChi,
			boolean gioiTinh, String soDienThoai, String gmail) {
		super();
		this.maKH = maKH;
		this.hoKhachHang = hoKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.gmail = gmail;
	}

	
	


	public String getMaKH() {
		return maKH;
	}


	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}


	public String getHoKhachHang() {
		return hoKhachHang;
	}


	public void setHoKhachHang(String hoKhachHang) {
		this.hoKhachHang = hoKhachHang;
	}


	public String getTenKhachHang() {
		return tenKhachHang;
	}


	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public boolean isGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public String getSoDienThoai() {
		return soDienThoai;
	}


	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public String getGmail() {
		return gmail;
	}


	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaChi == null) ? 0 : diaChi.hashCode());
		result = prime * result + (gioiTinh ? 1231 : 1237);
		result = prime * result + ((gmail == null) ? 0 : gmail.hashCode());
		result = prime * result + ((hoKhachHang == null) ? 0 : hoKhachHang.hashCode());
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
		result = prime * result + ((soDienThoai == null) ? 0 : soDienThoai.hashCode());
		result = prime * result + ((tenKhachHang == null) ? 0 : tenKhachHang.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHangDTO other = (KhachHangDTO) obj;
		if (diaChi == null) {
			if (other.diaChi != null)
				return false;
		} else if (!diaChi.equals(other.diaChi))
			return false;
		if (gioiTinh != other.gioiTinh)
			return false;
		if (gmail == null) {
			if (other.gmail != null)
				return false;
		} else if (!gmail.equals(other.gmail))
			return false;
		if (hoKhachHang == null) {
			if (other.hoKhachHang != null)
				return false;
		} else if (!hoKhachHang.equals(other.hoKhachHang))
			return false;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		if (soDienThoai == null) {
			if (other.soDienThoai != null)
				return false;
		} else if (!soDienThoai.equals(other.soDienThoai))
			return false;
		if (tenKhachHang == null) {
			if (other.tenKhachHang != null)
				return false;
		} else if (!tenKhachHang.equals(other.tenKhachHang))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "KhachHangDTO [maKH=" + maKH + ", hoKhachHang=" + hoKhachHang + ", tenKhachHang=" + tenKhachHang
				+ ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", soDienThoai="
				+ soDienThoai + ", gmail=" + gmail + "]";
	}


	
	
	
	
}
