package mobile_shop;

import java.util.ArrayList;

public class danhsachCTHD {
	private ArrayList<chitietHD> listCTHD = new ArrayList<>();

	public ArrayList<chitietHD> getListCTHD() {
		return listCTHD;
	}

	public void setListCTHD(ArrayList<chitietHD> listCTHD) {
		this.listCTHD = listCTHD;
	}
	
	public void xuatTieuDe() {
		int length = 70 - "Chi tiet hoa don".length();
        System.out.printf("|%-45s%-65s| \n", lib.repeatStr(" ", 45), "Chi tiet hoa don" + lib.repeatStr(" ", length));
        System.out.printf("|%-20s+%-20s-%-20s-%-25s-+%-25s| \n", lib.repeatStr(" ", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 25), 
        					lib.repeatStr(" ", 25));
        System.out.printf("|%-20s|%-20s|%-20s|%-25s |%-25s| \n", lib.repeatStr(" ", 20), "Ma dien thoai", "So luong", "Gia", lib.repeatStr(" ", 25));
        System.out.printf("|%-20s|%-20s|%-20s|%-25s-|%-25s| \n", lib.repeatStr(" ", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 25),
        					lib.repeatStr(" ", 25));
	}
	
	public void xuatDSCTHD() {
		xuatTieuDe();
        for (chitietHD cthd : listCTHD) {
            System.out.printf("|%-20s|%-20s|%-20s|%-25s |%-25s| \n", lib.repeatStr(" ", 20), cthd.getMadienthoai(),cthd.getSoluong(), cthd.getDongia(), 
            					lib.repeatStr(" ", 25));
        }
	}
	
	public String nhapMaDienThoai(danhsachDT listDT) {
		do {
			String maDT = lib.takeStringInput("Nhap ma dien thoai: ");
			if (listDT.timkiemMaDienThoai(maDT) == -1)
				lib.printMessage("Khong co ma dien thoai nay!");
			else {
				return maDT;
			}
		} while(true);
	}
	
	public void themCTHD(danhsachDT listDT) {
		listDT.xuatDS();
		boolean exit = false;
		do {
			String maDT = nhapMaDienThoai(listDT);
			dienthoai dt = listDT.getListDT()[listDT.timkiemMaDienThoai(maDT)];
			int soluongmua = lib.takeIntegerInput("Nhap so luong muon mua: ");
			int gia = dt.getgiathanh();
			if (!dt.mua(soluongmua)) {
				lib.printMessage("Khong the mua");
	            lib.printMessage("Trong kho chi con " + dt.getSoLuong());
	            continue;
			}
			
			boolean daco = false;
			for (chitietHD cthd : listCTHD) {
				if (cthd.getMadienthoai().equals(maDT)) {
					cthd.setSoluong(cthd.getSoluong() + soluongmua);
					daco = true;
				}
			}
			if (!daco) {
				listCTHD.add(new chitietHD(maDT, soluongmua, gia));
			}
			
			System.out.println("1. Mua tiep");
	        System.out.println("0. Dat hang");
	        if (lib.takeInputChoice(0, 1) == 0) {
	    		lib.printMessage("Dat hang thanh cong!");
	        	exit = true;
	        }
		} while (!exit);
	}
	
	public int TongTien() {
		int total = 0;
		for (chitietHD cthd : listCTHD) {
			total += cthd.getSoluong() * cthd.getDongia();
		}
		return total;
	}
}