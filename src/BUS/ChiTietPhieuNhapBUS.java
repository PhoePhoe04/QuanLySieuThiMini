package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;

public class ChiTietPhieuNhapBUS {
    private ArrayList<ChiTietPhieuNhapDTO> list_CTPN;
    private ChiTietPhieuNhapDAO ctpnDAO;
    
    public ChiTietPhieuNhapBUS() throws SQLException{
        this.ctpnDAO = new ChiTietPhieuNhapDAO();
        this.list_CTPN = new ArrayList<ChiTietPhieuNhapDTO>();
        list_CTPN = ctpnDAO.docDB();
    }
    
    /*
     * Thêm một chi tiết phiếu nhập vào danh sách và cơ sở dữ liệu
     */
    public boolean them(ChiTietPhieuNhapDTO ctpn) throws SQLException{
        if(ctpnDAO.them(ctpn) > 0) {
            this.list_CTPN.add(ctpn);
            return true;
        }
        return false;
    }
    
    /*
     * Xóa một chi tiết phiếu nhập khớp với danh sách từ cơ sở dữ liệu
     */
    public boolean xoa(ChiTietPhieuNhapDTO ctpn) throws SQLException{
        for (ChiTietPhieuNhapDTO ctpnDTO : list_CTPN) {
            if(ctpnDTO.equals(ctpn)) {
                if(ctpnDAO.xoa(ctpn) > 0) {
                    this.list_CTPN.remove(ctpn);
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Sửa thông tin một chi tiết phiếu nhập
     */
    public boolean sua(ChiTietPhieuNhapDTO ctpn) throws SQLException{
        if(ctpnDAO.sua(ctpn) > 0) {
            for (ChiTietPhieuNhapDTO ctpnDTO : list_CTPN) {
                if(ctpnDTO.getMaPN().equals(ctpn.getMaPN())) {
                    int i = list_CTPN.indexOf(ctpnDTO);
                    list_CTPN.set(i, ctpn);
                    return true;
                }
            }
        }
        return false;
    }
    
    // Các phương thức khác cần thiết
    public static void main(String[] args) {
        try {
            ChiTietPhieuNhapBUS ctpnBus = new ChiTietPhieuNhapBUS();
            ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO("PN01", "SP01", 5, 150000);
            if(ctpnBus.them(ctpn))
                System.out.println("Thêm thành công");
            else
                System.out.println("Thêm thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
