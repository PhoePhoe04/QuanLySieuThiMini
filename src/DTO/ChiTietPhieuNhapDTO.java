package DTO;

public class ChiTietPhieuNhapDTO {
	 	protected String maPN,maSP;
	    protected int soLuong;
	    protected float thanhTien;

	    public ChiTietPhieuNhapDTO(String maPN, String maSP, int soLuong, float thanhTien) {
	        this.maPN = maPN;
	        this.maSP = maSP;
	        this.soLuong = soLuong;
	        this.thanhTien = thanhTien;
	    }
	    public ChiTietPhieuNhapDTO(){}
	    public String getMaPN() {
	        return maPN;
	    }

	    public String getMaSP() {
	        return maSP;
	    }

	    public int getSoLuong() {
	        return soLuong;
	    }

	    public float getThanhTien() {
	        return thanhTien;
	    }

	    public void setMaPN(String maPN) {
	        this.maPN = maPN;
	    }

	    public void setMaSP(String maSP) {
	        this.maSP = maSP;
	    }

	    public void setSoLuong(int soLuong) {
	        this.soLuong = soLuong;
	    }

	    public void setThanhTien(float thanhTien) {
	        this.thanhTien = thanhTien;
	    }

	    @Override
	    public String toString() {
	        return maPN + "," + maSP + "," + soLuong + "," + thanhTien;
	    }

}
