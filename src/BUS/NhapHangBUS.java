package BUS;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.NhapHangDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhapHangDTO;

public class NhapHangBUS {
    private ArrayList<NhapHangDTO> list_NhapHang;
    private NhapHangDAO nhapHangDAO;
    
    public NhapHangBUS() throws SQLException{
        this.nhapHangDAO = new NhapHangDAO();
        this.list_NhapHang = new ArrayList<NhapHangDTO>();
        list_NhapHang = nhapHangDAO.docDB();
    }
    
    /*
     * Thêm một phiếu nhập hàng vào danh sách và cơ sở dữ liệu
     */
    public boolean them(NhapHangDTO nh) throws SQLException{
        if(nhapHangDAO.them(nh) > 0) {
            this.list_NhapHang.add(nh);
            return true;
        }
        return false;
    }
    
    /*
     * Xóa một phiếu nhập hàng khớp với danh sách từ cơ sở dữ liệu
     */
    public boolean xoa(NhapHangDTO nh) throws SQLException{
        for (NhapHangDTO nhDTO : list_NhapHang) {
            if(nhDTO.equals(nh)) {
                if(nhapHangDAO.xoa(nh) > 0) {
                    this.list_NhapHang.remove(nh);
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Sửa thông tin một phiếu nhập hàng
     */
    public boolean sua(NhapHangDTO nh) throws SQLException{
        if(nhapHangDAO.sua(nh) > 0) {
            for (NhapHangDTO nhDTO : list_NhapHang) {
                if(nhDTO.getMaPN().equals(nh.getMaPN())) {
                    int i = list_NhapHang.indexOf(nhDTO);
                    list_NhapHang.set(i, nh);
                    return true;
                }
            }
        }
        return false;
    }
    
    // Các phương thức khác cần thiết
    public ArrayList<NhapHangDTO> getList(){
    	return this.list_NhapHang;
    }
    public ArrayList<NhapHangDTO> getList(String condition){
    	return nhapHangDAO.docDB(condition);
    }
    
    // check
    private boolean unique(NhapHangDTO nh) {
    	for(int i = 0; i < list_NhapHang.size(); i++) {
    		if(nh.getMaPN().equals(list_NhapHang.get(i).getMaPN()))
    				return false;
    	}
    	return true;
    }
    
}
