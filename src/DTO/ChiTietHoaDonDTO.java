package DTO;

import java.util.Objects;

public class ChiTietHoaDonDTO {
	private String maHD, maSP, maKM;
	private int soLuong;
	private double donGia, thanhTien;
	
	public ChiTietHoaDonDTO() {
	}
	public ChiTietHoaDonDTO(String maHD, String maSP, String maKM, int soLuong, double donGia, double thanhTien) {
		this.maHD = maHD;
		this.maSP = maSP;
		this.maKM = maKM;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDonDTO [maHD=" + maHD + ", maSP=" + maSP + ", maKM=" + maKM + ", soLuong=" + soLuong
				+ ", donGia=" + donGia + ", thanhTien=" + thanhTien + "]";
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(donGia, maHD, maKM, maSP, soLuong, thanhTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDonDTO other = (ChiTietHoaDonDTO) obj;
		return Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(maHD, other.maHD) && Objects.equals(maKM, other.maKM)
				&& Objects.equals(maSP, other.maSP) && soLuong == other.soLuong
				&& Double.doubleToLongBits(thanhTien) == Double.doubleToLongBits(other.thanhTien);
	}
}
