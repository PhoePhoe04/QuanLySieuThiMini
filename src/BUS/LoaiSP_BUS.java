package BUS;

import DTO.LoaiSP_DTO;

import java.util.ArrayList;

import DAO.LoaiSP_DAO;

public class LoaiSP_BUS {
	private static ArrayList<LoaiSP_DTO> loaisanphams;
	private LoaiSP_DAO db;
	  
	public LoaiSP_BUS() {
		this.db = new LoaiSP_DAO();
		this.loaisanphams = db.getLoaiSPs();
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
	
	public boolean deleteLoaiSP(LoaiSP_DTO lsp) {
		if(loaisanphams.removeIf(sanpham -> sanpham.equals(lsp))) {
			db.deleteLoaiSP(lsp);
			return true;
		}
		return false;
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
	
	public ArrayList<LoaiSP_DTO> getList(){
		return this.loaisanphams;
	}
	
	public static void main(String[] args) {
		LoaiSP_BUS lspBus = new LoaiSP_BUS();
		ArrayList<LoaiSP_DTO> list = lspBus.getList();
		LoaiSP_DTO lsp = new LoaiSP_DTO("LSP004", "Bánh kẹo");
		try {
			for (LoaiSP_DTO loaiSP_DTO : list) {
				System.out.println(loaiSP_DTO.toString());
			}
			System.out.println(lspBus.deleteLoaiSP(lsp));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
