package DTO;

import java.util.Objects;

public class ChiTietKMHDDTO {
	private String maKM;
	private double tienHoaDon, tiLeGiamGia;
	
	public ChiTietKMHDDTO() {}
	public ChiTietKMHDDTO(String maKM, double tienHoaDon, double tiLeGiamGia) {
		this.maKM = maKM;
		this.tienHoaDon = tienHoaDon;
		this.tiLeGiamGia = tiLeGiamGia;
	}
	@Override
	public String toString() {
		return "ChiTietKMHDDTO [maKM=" + maKM + ", tienHoaDon=" + tienHoaDon + ", tiLeGiamGia=" + tiLeGiamGia + "]";
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public double getTienHoaDon() {
		return tienHoaDon;
	}
	public void setTienHoaDon(double tienHoaDon) {
		this.tienHoaDon = tienHoaDon;
	}
	public double getTiLeGiamGia() {
		return tiLeGiamGia;
	}
	public void setTiLeGiamGia(double tiLeGiamGia) {
		this.tiLeGiamGia = tiLeGiamGia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKM, tiLeGiamGia, tienHoaDon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietKMHDDTO other = (ChiTietKMHDDTO) obj;
		return Objects.equals(maKM, other.maKM)
				&& Double.doubleToLongBits(tiLeGiamGia) == Double.doubleToLongBits(other.tiLeGiamGia)
				&& Double.doubleToLongBits(tienHoaDon) == Double.doubleToLongBits(other.tienHoaDon);
	}
	
	
}
