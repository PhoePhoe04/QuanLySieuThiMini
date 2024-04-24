package DTO;

import java.util.Objects;

public class ChiTietKMSPDTO {
	private String maKM, maSP;
	private double tileGiamGia;
	
	public ChiTietKMSPDTO() {
	}
	public ChiTietKMSPDTO(String maKM, String maSP, double tileGiamGia) {
		this.maKM = maKM;
		this.maSP = maSP;
		this.tileGiamGia = tileGiamGia;
	}
	@Override
	public String toString() {
		return "ChiTietKMSPDTO [maKM=" + maKM + ", maSP=" + maSP + ", tileGiamGia=" + tileGiamGia + "]";
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public double getTileGiamGia() {
		return tileGiamGia;
	}
	public void setTileGiamGia(double tileGiamGia) {
		this.tileGiamGia = tileGiamGia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKM, maSP, tileGiamGia);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietKMSPDTO other = (ChiTietKMSPDTO) obj;
		return Objects.equals(maKM, other.maKM) && Objects.equals(maSP, other.maSP)
				&& Double.doubleToLongBits(tileGiamGia) == Double.doubleToLongBits(other.tileGiamGia);
	}
}
