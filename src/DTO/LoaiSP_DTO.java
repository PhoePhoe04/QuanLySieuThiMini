package DTO;

import java.util.Objects;

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
	@Override
	public String toString() {
		return "LoaiSP_DTO [maLSP=" + maLSP + ", tenLSP=" + tenLSP + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLSP, tenLSP);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSP_DTO other = (LoaiSP_DTO) obj;
		return Objects.equals(maLSP, other.maLSP) && Objects.equals(tenLSP, other.tenLSP);
	}
}
