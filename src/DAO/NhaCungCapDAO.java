package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.NhaCungCapDTO;

public class NhaCungCapDAO {
    MyConnect connect;

    // Hàm thêm
    public int them(NhaCungCapDTO nhaCungCap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> insertValues = new HashMap<>();
            insertValues.put("maNCC", nhaCungCap.getMaNCC());
            insertValues.put("tenNCC", nhaCungCap.getTenNCC());
            insertValues.put("soDienThoaiNCC", nhaCungCap.getSoDienThoaiNCC());
            insertValues.put("diaChiNCC", nhaCungCap.getDiaChiNCC());

            ketQua = connect.insert("nhacungcap", insertValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm sửa
    public int sua(NhaCungCapDTO nhaCungCap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            HashMap<String, Object> updateValues = new HashMap<>();
            updateValues.put("tenNCC", nhaCungCap.getTenNCC());
            updateValues.put("soDienThoaiNCC", nhaCungCap.getSoDienThoaiNCC());
            updateValues.put("diaChiNCC", nhaCungCap.getDiaChiNCC());

            String condition = " maNCC = '" + nhaCungCap.getMaNCC() + "'";

            ketQua = connect.update("nhacungcap", updateValues, condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Hàm xóa
    public int xoa(NhaCungCapDTO nhaCungCap) {
        connect = new MyConnect();
        int ketQua = 0;

        try {
            String condition = " maNCC = '" + nhaCungCap.getMaNCC() + "'";

            ketQua = connect.delete("nhacungcap", condition);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return ketQua;
    }

    // Đọc data
    public ArrayList<NhaCungCapDTO> docDB(String condition, String orderBy) {
        connect = new MyConnect();
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();

        try {
            ResultSet rs = connect.select("nhacungcap", condition, orderBy);
            while (rs.next()) {
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO();
                nhaCungCap.setMaNCC(rs.getString("maNCC"));
                nhaCungCap.setTenNCC(rs.getString("tenNCC"));
                nhaCungCap.setSoDienThoaiNCC(rs.getString("soDienThoaiNCC"));
                nhaCungCap.setDiaChiNCC(rs.getString("diaChiNCC"));

                list.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.Close();
        }

        return list;
    }

    public ArrayList<NhaCungCapDTO> docDB(String condition) {
        return this.docDB(condition, null);
    }

    public ArrayList<NhaCungCapDTO> docDB() {
        return this.docDB(null);
    }

    public static void main(String[] args) {
        NhaCungCapDAO nccdao = new NhaCungCapDAO();
        NhaCungCapDTO ncc = new NhaCungCapDTO("NCC01", "Công ty A", "0123456789", "TPHCM");

        int kq = nccdao.them(ncc);
        System.out.println(kq);
    }
}
