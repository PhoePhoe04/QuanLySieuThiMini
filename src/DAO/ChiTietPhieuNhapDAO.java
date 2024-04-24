package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.ChiTietPhieuNhapDTO;

public class ChiTietPhieuNhapDAO {
    MyConnect connect;

    // Hàm thêm
    public int them(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> insertValues = new HashMap<>();
            insertValues.put("maPN", chiTietPhieuNhap.getMaPN());
            insertValues.put("maSP", chiTietPhieuNhap.getMaSP());
            insertValues.put("soLuong", chiTietPhieuNhap.getSoLuong());
            insertValues.put("thanhTien", chiTietPhieuNhap.getThanhTien());

            ketQua = connect.insert("chitietphieunhap", insertValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm sửa
    public int sua(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> updateValues = new HashMap<>();
            updateValues.put("soLuong", chiTietPhieuNhap.getSoLuong());
            updateValues.put("thanhTien", chiTietPhieuNhap.getThanhTien());

            String condition = " maPN = '" + chiTietPhieuNhap.getMaPN() + "' AND maSP = '" + chiTietPhieuNhap.getMaSP() + "'";

            ketQua = connect.update("chitietphieunhap", updateValues, condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm xóa
    public int xoa(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            String condition = " maPN = '" + chiTietPhieuNhap.getMaPN() + "' AND maSP = '" + chiTietPhieuNhap.getMaSP() + "'";

            ketQua = connect.delete("chitietphieunhap", condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Đọc data
    public ArrayList<ChiTietPhieuNhapDTO> docDB(String condition, String orderBy) {
        connect = new MyConnect();
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();

        try {
            ResultSet rs = connect.select("chitietphieunhap", condition, orderBy);
            while (rs.next()) {
                ChiTietPhieuNhapDTO chiTietPhieuNhap = new ChiTietPhieuNhapDTO();
                chiTietPhieuNhap.setMaPN(rs.getString("maPN"));
                chiTietPhieuNhap.setMaSP(rs.getString("maSP"));
                chiTietPhieuNhap.setSoLuong(rs.getInt("soLuong"));
                chiTietPhieuNhap.setThanhTien(rs.getFloat("thanhTien"));

                list.add(chiTietPhieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return list;
    }

    public ArrayList<ChiTietPhieuNhapDTO> docDB(String condition) {
        return this.docDB(condition, null);
    }

    public ArrayList<ChiTietPhieuNhapDTO> docDB() {
        return this.docDB(null);
    }

    public static void main(String[] args) {
        ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        // Tạo đối tượng chi tiết phiếu nhập
        ChiTietPhieuNhapDTO chiTietPhieuNhap = new ChiTietPhieuNhapDTO("PN01", "SP01", 10, 100000);

        // Thêm đối tượng chi tiết phiếu nhập vào cơ sở dữ liệu
        int kq = chiTietPhieuNhapDAO.them(chiTietPhieuNhap);
        System.out.println(kq);
    }
}
