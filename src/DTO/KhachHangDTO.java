package DTO;

import java.sql.Date;
import java.util.Objects;

public class KhachHangDTO {
	private String maKH;
	private String hoKH;
	private String tenKH;
	private String diaChi;
	private boolean gioiTinh;
	private String soDienThoai;
	private String gmail;
	
	public KhachHangDTO() {
		
	}
	public KhachHangDTO(String maKH, String hoKH, String tenKH, boolean gioiTinh, String diaChi, String soDienThoai, String gmail) {
		this.maKH = maKH;
		this.hoKH = hoKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.gmail = gmail;
	}
	@Override
	public String toString() {
		return "KhachHangDTO [maKH=" + maKH + ", hoKH=" + hoKH + ", tenKH=" + tenKH + ", diaChi=" + diaChi
				+ ", gioiTinh=" + gioiTinh + ", soDienThoai=" + soDienThoai + ", gmail=" + gmail + "]";
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoKH() {
		return hoKH;
	}
	public void setHoKH(String hoKH) {
		this.hoKH = hoKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diaChi, gioiTinh, gmail, hoKH, maKH, soDienThoai, tenKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHangDTO other = (KhachHangDTO) obj;
		return Objects.equals(diaChi, other.diaChi) && gioiTinh == other.gioiTinh && Objects.equals(gmail, other.gmail)
				&& Objects.equals(hoKH, other.hoKH) && Objects.equals(maKH, other.maKH)
				&& Objects.equals(soDienThoai, other.soDienThoai) && Objects.equals(tenKH, other.tenKH);
	}
	
}
