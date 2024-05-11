package DTO;

import java.util.Objects;

public class NhaCungCapDTO {
	protected  String maNCC,tenNCC,email,diaChiNCC;

    public NhaCungCapDTO(String maNCC, String tenNCC, String email, String diaChiNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.email = email;
        this.diaChiNCC = diaChiNCC;
    }
    public NhaCungCapDTO(){}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChiNCC() {
		return diaChiNCC;
	}
	public void setDiaChiNCC(String diaChiNCC) {
		this.diaChiNCC = diaChiNCC;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diaChiNCC, email, maNCC, tenNCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCapDTO other = (NhaCungCapDTO) obj;
		return Objects.equals(diaChiNCC, other.diaChiNCC) && Objects.equals(email, other.email)
				&& Objects.equals(maNCC, other.maNCC) && Objects.equals(tenNCC, other.tenNCC);
	}
	@Override
	public String toString() {
		return "NhaCungCapDTO [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", email=" + email + ", diaChiNCC=" + diaChiNCC
				+ "]";
	}


}
