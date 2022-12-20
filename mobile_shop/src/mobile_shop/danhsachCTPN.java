package mobile_shop;

import java.util.ArrayList;

public class danhsachCTPN {
	private ArrayList<chitietPN> listCTPN = new ArrayList<>();
	
	public ArrayList<chitietPN> getListCTPN() {
		return listCTPN;
	}

	public void setListCTPN(ArrayList<chitietPN> listCTPN) {
		this.listCTPN = listCTPN;
	}

	public void xuatTieuDe() {
        System.out.printf("|%-16s-%-16s-%-16s|\n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16));
        System.out.printf("|%-16s|%-16s|%-16s|\n", "Ma dien thoai", "So luong", "Don gia");
        System.out.printf("|%-16s|%-16s|%-16s|\n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16));
    }
	
	public void xuatDSCTPN() {
		xuatTieuDe();
        for (chitietPN ctpn : listCTPN) {
        	ctpn.xuatChiTiet();
        }
    }
		
	public int TongTien() {
		int total = 0;
		for (chitietPN ctpn : listCTPN) {
			total += ctpn.getDongia() * ctpn.getSoluong();
		}
		return total;
	}
	
	public chitietPN timkiemDaCo(String idDT) {
		for (chitietPN pn : listCTPN) {
			if (pn.getMaDT().equals(idDT))
				return pn;
		}
		return null;
	}
}