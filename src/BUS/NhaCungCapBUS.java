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
        if(check(ncc) && unique(ncc)) {
        	 if(nccDAO.them(ncc) > 0) {
                 this.list_NCC.add(ncc);
                 return true;
             }
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
    	if(check(ncc)) {
    		if(nccDAO.sua(ncc) > 0) {
                for (NhaCungCapDTO nccDTO : list_NCC) {
                    if(nccDTO.getMaNCC().equals(ncc.getMaNCC())) {
                        int i = list_NCC.indexOf(nccDTO);
                        list_NCC.set(i, ncc);
                        return true;
                    }
                }
            }
    	}
        return false;
    }
    
    // Kiểm tra tính duy nhất của mã nhà cung cấp
    private boolean unique(NhaCungCapDTO ncc) {
    	for (NhaCungCapDTO nccDTO : list_NCC) {
            if(nccDTO.getMaNCC().equals(ncc.getMaNCC()))
                return false;
        }
    	return true;
    }
    
    private boolean check(NhaCungCapDTO ncc) {
    	if(!ncc.getTenNCC().matches("[\\p{L}\\s]+")) {
    		return false;
    	}
    	if(!ncc.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
    		return false;
    	}
    	return true;
    }
    
    // Lấy dữ liệu
    public ArrayList<NhaCungCapDTO> getList(){
    	return this.list_NCC;
    }
    
    public ArrayList<NhaCungCapDTO> getList(String condition){
    	return nccDAO.docDB(condition);
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
