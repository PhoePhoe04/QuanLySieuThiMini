package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.NhapHangDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NhapHangDAO {
    MyConnect connect;

    // Hàm thêm
    public int them(NhapHangDTO nhapHang) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> insertValues = new HashMap<>();
            insertValues.put("maPN", nhapHang.getMaPN());
            insertValues.put("maNV", nhapHang.getMaNV());
            insertValues.put("maNCC", nhapHang.getMaNCC());
            insertValues.put("tongTien", nhapHang.getTongTien());
            insertValues.put("ngayNhap", nhapHang.getNgayNhap());

            ketQua = connect.insert("nhaphang", insertValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm sửa
    public int sua(NhapHangDTO nhapHang) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> updateValues = new HashMap<>();
            updateValues.put("maNV", nhapHang.getMaNV());
            updateValues.put("maNCC", nhapHang.getMaNCC());
            updateValues.put("tongTien", nhapHang.getTongTien());
            updateValues.put("ngayNhap", nhapHang.getNgayNhap());

            String condition = " maPN = '" + nhapHang.getMaPN() + "'";

            ketQua = connect.update("nhaphang", updateValues, condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm xóa
    public int xoa(NhapHangDTO nhapHang) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            String condition = " maPN = '" + nhapHang.getMaPN() + "'";

            ketQua = connect.delete("nhaphang", condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Đọc data
    public ArrayList<NhapHangDTO> docDB(String condition, String orderBy) {
        connect = new MyConnect();
        ArrayList<NhapHangDTO> list = new ArrayList<>();

        try {
            ResultSet rs = connect.select("nhaphang", condition, orderBy);
            while (rs.next()) {
                NhapHangDTO nhapHang = new NhapHangDTO();
                nhapHang.setMaPN(rs.getString("maPN"));
                nhapHang.setMaNV(rs.getString("maNV"));
                nhapHang.setMaNCC(rs.getString("maNCC"));
                nhapHang.setTongTien(rs.getDouble("tongTien"));
                nhapHang.setNgayNhap(rs.getDate("ngayNhap"));

                list.add(nhapHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return list;
    }

    public ArrayList<NhapHangDTO> docDB(String condition) {
        return this.docDB(condition, null);
    }

    public ArrayList<NhapHangDTO> docDB() {
        return this.docDB(null);
    }
 
}
