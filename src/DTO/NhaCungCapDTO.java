package DTO;

import java.util.Objects;

public class NhaCungCapDTO {
	protected  String maNCC,tenNCC,soDienThoaiNCC,diaChiNCC;

    public NhaCungCapDTO(String maNCC, String tenNCC, String soDienThoaiNCC, String diaChiNCC) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.soDienThoaiNCC = soDienThoaiNCC;
        this.diaChiNCC = diaChiNCC;
    }
    public NhaCungCapDTO(){}

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getSoDienThoaiNCC() {
        return soDienThoaiNCC;
    }

    public String getDiaChiNCC() {
        return diaChiNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setSoDienThoaiNCC(String soDienThoaiNCC) {
        this.soDienThoaiNCC = soDienThoaiNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.diaChiNCC = diaChiNCC;
    }

    @Override
    public String toString() {
        return maNCC + "," + tenNCC + "," + soDienThoaiNCC + "," + diaChiNCC;
    }
	@Override
	public int hashCode() {
		return Objects.hash(diaChiNCC, maNCC, soDienThoaiNCC, tenNCC);
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
		return Objects.equals(diaChiNCC, other.diaChiNCC) && Objects.equals(maNCC, other.maNCC)
				&& Objects.equals(soDienThoaiNCC, other.soDienThoaiNCC) && Objects.equals(tenNCC, other.tenNCC);
	}
    
}
