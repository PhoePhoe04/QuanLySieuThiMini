package DTO;

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

}
