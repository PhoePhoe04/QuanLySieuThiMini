package DTO;

import java.util.Objects;

public class TaiKhoanDTO {
	private String tenDangNhap;
	private String matKhau;
	
	public TaiKhoanDTO() {}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public TaiKhoanDTO(String tenDangNhap, String matKhau) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matKhau, tenDangNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoanDTO other = (TaiKhoanDTO) obj;
		return Objects.equals(matKhau, other.matKhau) && Objects.equals(tenDangNhap, other.tenDangNhap);
	}

	@Override
	public String toString() {
		return "TaiKhoanDTO [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + "]";
	}

}
