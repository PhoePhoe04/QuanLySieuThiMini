package DTO;

import java.util.Objects;

public class ChiTietPhieuNhapDTO {
	 	protected String maPN,maSP;
	    protected int soLuong;
	    protected float donGia;
	    protected float thanhTien;

	    public ChiTietPhieuNhapDTO(String maPN, String maSP, int soLuong, float donGia, float thanhTien) {
	        this.maPN = maPN;
	        this.maSP = maSP;
	        this.soLuong = soLuong;
	        this.donGia = donGia;
	        this.thanhTien = thanhTien;
	    }
	    public ChiTietPhieuNhapDTO(){}
		public String getMaPN() {
			return maPN;
		}
		public void setMaPN(String maPN) {
			this.maPN = maPN;
		}
		public String getMaSP() {
			return maSP;
		}
		public void setMaSP(String maSP) {
			this.maSP = maSP;
		}
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		public float getDonGia() {
			return donGia;
		}
		public void setDonGia(float donGia) {
			this.donGia = donGia;
		}
		public float getThanhTien() {
			return thanhTien;
		}
		public void setThanhTien(float thanhTien) {
			this.thanhTien = thanhTien;
		}
		@Override
		public String toString() {
			return "ChiTietPhieuNhapDTO [maPN=" + maPN + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia="
					+ donGia + ", thanhTien=" + thanhTien + "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(donGia, maPN, maSP, soLuong, thanhTien);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChiTietPhieuNhapDTO other = (ChiTietPhieuNhapDTO) obj;
			return Float.floatToIntBits(donGia) == Float.floatToIntBits(other.donGia)
					&& Objects.equals(maPN, other.maPN) && Objects.equals(maSP, other.maSP) && soLuong == other.soLuong
					&& Float.floatToIntBits(thanhTien) == Float.floatToIntBits(other.thanhTien);
		}
	    
	    
}
