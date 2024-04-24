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
    
    public static void main(String[] args) {
        try {
            NhapHangBUS nhBus = new NhapHangBUS();
            List<ChiTietPhieuNhapDTO> chiTietList = new ArrayList<>();
            ChiTietPhieuNhapDTO chiTiet1 = new ChiTietPhieuNhapDTO("PN01", "SP01", 5, 150000);
            chiTietList.add(chiTiet1);
            NhapHangDTO nh = new NhapHangDTO("PN01", "NV01", "NCC01", 1500000, Date.valueOf("2024-04-20"), chiTietList);
            if(nhBus.them(nh))
                System.out.println("Thêm thành công");
            else
                System.out.println("Thêm thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
