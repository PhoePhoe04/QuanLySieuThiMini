package DTO;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class NhapHangDTO {
	protected String maPN,maNV,maNCC;
    protected double tongTien;
    protected Date ngayNhap;
    
    public NhapHangDTO(String maPN, String maNV, String maNCC, double tongTien, Date ngayNhap) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
    }
    public NhapHangDTO(){}
	public String getMaPN() {
		return maPN;
	}
	public void setMaPN(String maPN) {
		this.maPN = maPN;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNCC, maNV, maPN, ngayNhap, tongTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhapHangDTO other = (NhapHangDTO) obj;
		return Objects.equals(maNCC, other.maNCC) && Objects.equals(maNV, other.maNV)
				&& Objects.equals(maPN, other.maPN) && Objects.equals(ngayNhap, other.ngayNhap)
				&& Double.doubleToLongBits(tongTien) == Double.doubleToLongBits(other.tongTien);
	}
	@Override
	public String toString() {
		return "NhapHangDTO [maPN=" + maPN + ", maNV=" + maNV + ", maNCC=" + maNCC + ", tongTien=" + tongTien
				+ ", ngayNhap=" + ngayNhap + "]";
	}

    
    
}
