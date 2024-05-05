package DTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class NhanVienDTO {
	private String maNV;
	private String hoNV;
	private String tenNV;
	private Date ngaySinh;
	private String diaChi;
	private boolean gioiTinh;
	private String soDienThoai;
	
	public NhanVienDTO() {
	}
	
	public NhanVienDTO(String maNV, String hoNV, String tenNV, Date ngaySinh, boolean gioiTinh, String diaChi, String soDienThoai) {
		this.maNV = maNV;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString() {
		return "NhanVienDTO [maNV=" + maNV + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh
				+ ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai + "]";
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoNV() {
		return hoNV;
	}

	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Date getNgaySinh() {
		return ngaySinh;
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
		return Objects.hash(diaChi, gioiTinh, hoNV, maNV, ngaySinh, soDienThoai, tenNV);
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
		return Objects.equals(diaChi, other.diaChi) && gioiTinh == other.gioiTinh && Objects.equals(hoNV, other.hoNV)
				&& Objects.equals(maNV, other.maNV) && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(soDienThoai, other.soDienThoai) && Objects.equals(tenNV, other.tenNV);
	}
	
	
}
