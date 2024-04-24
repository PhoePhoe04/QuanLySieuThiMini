package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class NhanVienDTO {
	private String maNV;
	private String hoNhanVien;
	private String tenNhanVien;
	private Date ngaySinh;
	private String diaChi;
	private boolean gioiTinh;
	private String soDienThoai;
	public NhanVienDTO(String maNV, String hoNhanVien, String tenNhanVien, Date ngaySinh, String diaChi,
			boolean gioiTinh, String soDienThoai) {
		super();
		this.maNV = maNV;
		this.hoNhanVien = hoNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
	}
	public NhanVienDTO() {
		super();
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoNhanVien() {
		return hoNhanVien;
	}
	public void setHoNhanVien(String hoNhanVien) {
		this.hoNhanVien = hoNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getNgaySinh() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(ngaySinh);
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaChi == null) ? 0 : diaChi.hashCode());
		result = prime * result + (gioiTinh ? 1231 : 1237);
		result = prime * result + ((hoNhanVien == null) ? 0 : hoNhanVien.hashCode());
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		result = prime * result + ((ngaySinh == null) ? 0 : ngaySinh.hashCode());
		result = prime * result + ((soDienThoai == null) ? 0 : soDienThoai.hashCode());
		result = prime * result + ((tenNhanVien == null) ? 0 : tenNhanVien.hashCode());
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
		NhanVienDTO other = (NhanVienDTO) obj;
		if (diaChi == null) {
			if (other.diaChi != null)
				return false;
		} else if (!diaChi.equals(other.diaChi))
			return false;
		if (gioiTinh != other.gioiTinh)
			return false;
		if (hoNhanVien == null) {
			if (other.hoNhanVien != null)
				return false;
		} else if (!hoNhanVien.equals(other.hoNhanVien))
			return false;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		if (ngaySinh == null) {
			if (other.ngaySinh != null)
				return false;
		} else if (!ngaySinh.equals(other.ngaySinh))
			return false;
		if (soDienThoai == null) {
			if (other.soDienThoai != null)
				return false;
		} else if (!soDienThoai.equals(other.soDienThoai))
			return false;
		if (tenNhanVien == null) {
			if (other.tenNhanVien != null)
				return false;
		} else if (!tenNhanVien.equals(other.tenNhanVien))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhanVienDTO [maNV=" + maNV + ", hoNhanVien=" + hoNhanVien + ", tenNhanVien=" + tenNhanVien
				+ ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", soDienThoai="
				+ soDienThoai + "]";
	}

	
}
