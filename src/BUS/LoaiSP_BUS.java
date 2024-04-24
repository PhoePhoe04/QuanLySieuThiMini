package BUS;

import DTO.LoaiSP_DTO;

import java.util.ArrayList;

import DAO.LoaiSP_DAO;

public class LoaiSP_BUS {
	private static ArrayList<LoaiSP_DTO> loaisanphams;
	private LoaiSP_DAO db;
	  
	public LoaiSP_BUS() {
	}
	
	public boolean checkid(String id) {
		for(int i = 0; i < loaisanphams.size(); i++) {
			if(loaisanphams.get(i).getMaLSP().equals(id)) {
				return true;
			}
		}
		return false;
	}	
	
	public void addLoaiSP(LoaiSP_DTO sanpham) {
		if(checkid(sanpham.getMaLSP())) {
			System.err.println("Product with ID: " + sanpham.getMaLSP() + " already ");
		}else {
			loaisanphams.add(sanpham);
			db.addLoaiSP(sanpham);
		}
	}
	
	public void deleteLoaiSP(String id) {
		loaisanphams.removeIf(sanpham -> sanpham.getMaLSP().equals(id));
		db.deleteLoaiSP(id);
	}

	public void editLoaiSp(LoaiSP_DTO sanpham) {
		for(int i = 0; i < loaisanphams.size(); i++) {
			if(loaisanphams.get(i).getMaLSP().equals(sanpham.getMaLSP())){
				loaisanphams.set(i, sanpham);
				db.editLoaiSP(sanpham);
				break;
			}
		}
	}
	
	public void getdata() {
		db = new LoaiSP_DAO();
		loaisanphams = db.getLoaiSPs();
		}
	
	}
