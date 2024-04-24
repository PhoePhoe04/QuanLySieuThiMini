package DTO;

import java.util.Date;
import java.util.List;

public class NhapHangDTO {
	protected String maPN,maNV,maNCC;
    protected double tongTien;
    protected Date ngayNhap;
    protected List<ChiTietPhieuNhapDTO> list;
    public NhapHangDTO(String maPN, String maNV, String maNCC, double tongTien, Date ngayNhap , List<ChiTietPhieuNhapDTO> list) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.list = list;
    }
    public NhapHangDTO(){}

    public String getMaPN() {
        return maPN;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public List<ChiTietPhieuNhapDTO> getList() {
        return list;
    }

    public void setList(List<ChiTietPhieuNhapDTO> list) {
        this.list = list;
    }
    
    public double getTongTien() {
        return tongTien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public String toString() {
        return maPN + "," + maNV + "," + maNCC + "," + tongTien + "," + ngayNhap ;
    }

}
