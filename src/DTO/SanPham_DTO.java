package DTO;

public class SanPham_DTO {
	private String maSP;
	private String tenSP;
	private String maLSP;
	private String donGia;
	private String soLuong;
	private String donViTinh;
	
	public SanPham_DTO() {
	}

	public SanPham_DTO(String maSP, String tenSP, String maLSP, String donGia, String soLuong, String donViTinh) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maLSP = maLSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.donViTinh = donViTinh;
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

	public String getDonGia() {
		return donGia;
	}

	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}

	public String getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	
	
}
