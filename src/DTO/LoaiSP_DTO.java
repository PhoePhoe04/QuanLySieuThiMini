package DTO;

public class LoaiSP_DTO {
	private String maLSP;
	private String tenLSP;
	
	public LoaiSP_DTO() {
	}
	public LoaiSP_DTO(String maLSP, String tenLSP) {
		this.maLSP = maLSP;
		this.tenLSP = tenLSP;
	}
	public String getMaLSP() {
		return maLSP;
	}
	public void setMaLSP(String maLSP) {
		this.maLSP = maLSP;
	}
	public String getTenLSP() {
		return tenLSP;
	}
	public void setTenLSP(String tenLSP) {
		this.tenLSP = tenLSP;
	}
}
