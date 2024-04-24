package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
    private ArrayList<NhaCungCapDTO> list_NCC;
    private NhaCungCapDAO nccDAO;
    
    public NhaCungCapBUS() throws SQLException{
        this.nccDAO = new NhaCungCapDAO();
        this.list_NCC = new ArrayList<NhaCungCapDTO>();
        list_NCC = nccDAO.docDB();
    }
    
    /*
     * Thêm một nhà cung cấp vào danh sách và cơ sở dữ liệu
     */
    public boolean them(NhaCungCapDTO ncc) throws SQLException{
        if(!isValidPhoneNumber(ncc.getSoDienThoaiNCC()) || !isUniqueMaNCC(ncc.getMaNCC()))
            return false;
        if(nccDAO.them(ncc) > 0) {
            this.list_NCC.add(ncc);
            return true;
        }
        return false;
    }
    
    /*
     * Xóa một nhà cung cấp khớp với danh sách từ cơ sở dữ liệu
     */
    public boolean xoa(NhaCungCapDTO ncc) throws SQLException{
        for (NhaCungCapDTO nccDTO : list_NCC) {
            if(nccDTO.equals(ncc)) {
                if(nccDAO.xoa(ncc) > 0) {
                    this.list_NCC.remove(ncc);
                    return true;
                }
            }
        }
        return false;
    }
    
    /*
     * Sửa thông tin một nhà cung cấp
     */
    public boolean sua(NhaCungCapDTO ncc) throws SQLException{
        if(!isValidPhoneNumber(ncc.getSoDienThoaiNCC()) || !isUniqueMaNCC(ncc.getMaNCC()))
            return false;
        if(nccDAO.sua(ncc) > 0) {
            for (NhaCungCapDTO nccDTO : list_NCC) {
                if(nccDTO.getMaNCC().equals(ncc.getMaNCC())) {
                    int i = list_NCC.indexOf(nccDTO);
                    list_NCC.set(i, ncc);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    // Kiểm tra sự hợp lệ của số điện thoại
    private boolean isValidPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 10)
            return false;
        for (char c : phoneNumber.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }
    
    // Kiểm tra tính duy nhất của mã nhà cung cấp
    private boolean isUniqueMaNCC(String maNCC) {
        for (NhaCungCapDTO nccDTO : list_NCC) {
            if(nccDTO.getMaNCC().equals(maNCC))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        try {
            NhaCungCapBUS nccBus = new NhaCungCapBUS();
            NhaCungCapDTO ncc = new NhaCungCapDTO("NCC01", "Nhà Cung Cấp 1", "0987654321", "Hà Nội");
            if(nccBus.them(ncc))
                System.out.println("Thêm thành công");
            else
                System.out.println("Thêm thất bại");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
