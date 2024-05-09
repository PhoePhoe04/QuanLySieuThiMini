package DTO;

import java.util.Objects;

public class ChiTietPhieuNhapDTO {
	 	protected String maPN,maSP;
	    protected int soLuong;
	    protected float thanhTien;

	    public ChiTietPhieuNhapDTO(String maPN, String maSP, int soLuong, float thanhTien) {
	        this.maPN = maPN;
	        this.maSP = maSP;
	        this.soLuong = soLuong;
	        this.thanhTien = thanhTien;
	    }
	    public ChiTietPhieuNhapDTO(){}
	    public String getMaPN() {
	        return maPN;
	    }

	    public String getMaSP() {
	        return maSP;
	    }

	    public int getSoLuong() {
	        return soLuong;
	    }

	    public float getThanhTien() {
	        return thanhTien;
	    }

	    public void setMaPN(String maPN) {
	        this.maPN = maPN;
	    }

	    public void setMaSP(String maSP) {
	        this.maSP = maSP;
	    }

	    public void setSoLuong(int soLuong) {
	        this.soLuong = soLuong;
	    }

	    public void setThanhTien(float thanhTien) {
	        this.thanhTien = thanhTien;
	    }

	    @Override
	    public String toString() {
	        return maPN + "," + maSP + "," + soLuong + "," + thanhTien;
	    }
		@Override
		public int hashCode() {
			return Objects.hash(maPN, maSP, soLuong, thanhTien);
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
			return Objects.equals(maPN, other.maPN) && Objects.equals(maSP, other.maSP) && soLuong == other.soLuong
					&& Float.floatToIntBits(thanhTien) == Float.floatToIntBits(other.thanhTien);
		}
	    
}
