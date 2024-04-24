package DTO;

import java.sql.Date;
import java.util.Objects;

public class HoaDonDTO {
	private String maHD, maKH, maNV, maKM;
	private Date ngayLap;
	private double tongTien;
	
	public HoaDonDTO() {}

	public HoaDonDTO(String maHD, String maKH, String maNV, String maKM, Date ngayLap, double tongTien) {
		this.maHD = maHD;
		this.maKH = maKH;
		this.maNV = maNV;
		this.maKM = maKM;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "HoaDonDTO [maHD=" + maHD + ", maKH=" + maKH + ", maNV=" + maNV + ", maKM=" + maKM + ", ngayLap="
				+ ngayLap + ", tongTien=" + tongTien + "]";
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD, maKH, maKM, maNV, ngayLap, tongTien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDonDTO other = (HoaDonDTO) obj;
		return Objects.equals(maHD, other.maHD) && Objects.equals(maKH, other.maKH) && Objects.equals(maKM, other.maKM)
				&& Objects.equals(maNV, other.maNV) && Objects.equals(ngayLap, other.ngayLap)
				&& Double.doubleToLongBits(tongTien) == Double.doubleToLongBits(other.tongTien);
	}
	
}
