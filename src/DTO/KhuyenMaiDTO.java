package DTO;

import java.sql.Date;
import java.util.Objects;

public class KhuyenMaiDTO {
	private String maKM, tenKM, dieuKien;
	private Date ngayBatDau, ngayKetThuc;
	
	public KhuyenMaiDTO() {
	}
	public KhuyenMaiDTO(String maKM, String tenKM, String dieuKien, Date ngayBatDau, Date ngayKetThuc) {
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.dieuKien = dieuKien;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}
	
	@Override
	public String toString() {
		return "KhuyenMaiDTO [maKM=" + maKM + ", tenKM=" + tenKM + ", dieuKien=" + dieuKien + ", ngayBatDau="
				+ ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + "]";
	}
	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public String getTenKM() {
		return tenKM;
	}
	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}
	public String getDieuKien() {
		return dieuKien;
	}
	public void setDieuKien(String dieuKien) {
		this.dieuKien = dieuKien;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dieuKien, maKM, ngayBatDau, ngayKetThuc, tenKM);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMaiDTO other = (KhuyenMaiDTO) obj;
		return Objects.equals(dieuKien, other.dieuKien) && Objects.equals(maKM, other.maKM)
				&& Objects.equals(ngayBatDau, other.ngayBatDau) && Objects.equals(ngayKetThuc, other.ngayKetThuc)
				&& Objects.equals(tenKM, other.tenKM);
	}
}
