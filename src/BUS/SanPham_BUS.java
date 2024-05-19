package BUS;

import java.util.ArrayList;

import DAO.SanPham_DAO;
import DTO.SanPham_DTO;

public class SanPham_BUS {
	private SanPham_DAO spDAO;
	private ArrayList<SanPham_DTO> list_sp;
	
	public SanPham_BUS() {
		this.spDAO = new SanPham_DAO();
		this.list_sp = spDAO.getSanPhams();
	}
	
	public boolean them(SanPham_DTO sanPham) {
		if(unique(sanPham)) {
			if(spDAO.addSanPham(sanPham) > 0) {
				list_sp.add(sanPham);
				return true;
			}
		}
		return false;
	}
	
	public boolean sua(SanPham_DTO sanPham) {
		if(spDAO.editSanPham(sanPham) > 0) {
			for(int i = 0; i < list_sp.size(); i++) {
				SanPham_DTO sp = list_sp.get(i);
				if(sp.getMaSP().equals(sanPham.getMaSP())) {
					list_sp.set(i, sanPham);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean xoa(SanPham_DTO sanPham) {
		if(list_sp.removeIf(e -> e.equals(sanPham))) {
			spDAO.deleteSanPham(sanPham);
			return true;
		}
		return false;
	}
	
	public ArrayList<SanPham_DTO> layDuLieu(String condition){
		return spDAO.getSanPhams(condition);
	}
	
	public ArrayList<SanPham_DTO> getList(){
		return this.list_sp;
	}
	
	public SanPham_DTO getSP(String maSP) {
		for(int i = 0; i < list_sp.size(); i++) {
			SanPham_DTO sp = list_sp.get(i);
			if(sp.getMaSP().equals(maSP))
				return sp;
		}
		return null;
	}
	
	private boolean unique(SanPham_DTO sp) {
		for(int i = 0; i < list_sp.size(); i++) {
			if(sp.getMaSP().equals(list_sp.get(i).getMaSP()))
				return false;
		}
		
		return true;
	}
}
