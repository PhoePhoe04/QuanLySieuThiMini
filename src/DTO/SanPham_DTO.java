package DTO;

import java.util.Objects;

public class SanPham_DTO {
	private String maSP;
	private String tenSP;
	private String maLSP;
	private double donGia;
	private int soLuong;
	private String donViTinh;
	
	public SanPham_DTO() {
	}
	public SanPham_DTO(String maSP, String tenSP, String maLSP, double donGia, int soLuong, String donViTinh) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maLSP = maLSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
	}

	@Override
	public String toString() {
		return "SanPham_DTO [maSP=" + maSP + ", tenSP=" + tenSP + ", maLSP=" + maLSP + ", donGia=" + donGia
				+ ", soLuong=" + soLuong + ", donViTinh=" + donViTinh + "]";
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaLSP() {
		return maLSP;
	}

	public void setMaLSP(String maLSP) {
		this.maLSP = maLSP;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(donGia, donViTinh, maLSP, maSP, soLuong, tenSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham_DTO other = (SanPham_DTO) obj;
		return Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(donViTinh, other.donViTinh) && Objects.equals(maLSP, other.maLSP)
				&& Objects.equals(maSP, other.maSP) && soLuong == other.soLuong && Objects.equals(tenSP, other.tenSP);
	}

	
	
}
