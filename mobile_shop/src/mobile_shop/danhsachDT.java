package mobile_shop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class danhsachDT implements danhsach {
	private dienthoai[] listDT;
	private int cnt;
	
	public danhsachDT() throws IOException {
		listDT = lib.readDTFile();
		if (listDT == null) {
			cnt = 0;
			listDT = new dienthoai[10];
			listDT[0] = new iphone(getID(), "Iphone X", "NSX001", "NCC001", 15200, 200);
			listDT[1] = new samsung(getID(), "SamSung Galaxy", "NSX002", "NCC002", 12500, 150);
			listDT[2] = new oppo(getID(), "Oppo A95", "NSX003", "NCC003", 9640, 120);
			listDT[3] = new iphone(getID(), "Iphone 14", "NSX001", "NCC001" ,39999, 50);
			listDT[4] = new samsung(getID(), "SamSung Note 9+", "NSX002", "NCC002", 35000, 70);
			listDT[5] = new oppo(getID(), "Oppo Reno 9", "NSX003", "NCC003", 25000, 100);
			listDT[6] = new iphone(getID(), "Iphone 13 Pro Max", "NSX001", "NCC001", 27000, 170);
			listDT[7] = new samsung(getID(), "SamSung S22+", "NSX002", "NCC002", 30000, 150);
			listDT[8] = new oppo(getID(), "Oppo Find X 9", "NSX003", "NCC003", 27500, 250);
			listDT[9] = new iphone(getID(), "Iphone 11 Pro Max", "NSX001", "NCC001", 25500, 100);
			lib.writeDTFile(listDT);
		} else {
			cnt = listDT.length;
		}
	}
	
	public void setSoLuong(int soLuong) {
		listDT = Arrays.copyOf(listDT, soLuong);
	}
	
	public int getSoLuong() {
		return listDT.length;
	}
	
	public dienthoai[] getListDT() {
		return listDT;
	}

	public void setListDT(dienthoai[] listDT) {
		this.listDT = listDT;
	}
	
	public String getID() {
		cnt++;
		Integer a = cnt;
		String str = a.toString();
		while(str.length() != 3) {
			str = "0" + str;			
		}
		str = "DT" + str;
		return str;
	}
		
	public void xuatTieuDe() {
		System.out.printf("+%-15s-%-18s-%-15s-%-15s-%-10s-%-15s+%n", lib.repeatStr("-", 15), lib.repeatStr("-", 18), lib.repeatStr("-", 15), 
							lib.repeatStr("-", 15), lib.repeatStr("-", 10), lib.repeatStr("-", 15));
		
		System.out.printf("|%-15s|%-18s|%-15s|%-15s|%-10s|%-15s|%n", "Ma dien thoai", "Ten dien thoai", "Ma nha san xuat", "Ma nha cung cap", "Gia thanh", 
							"Ton kho");
		
		System.out.printf("|%-15s|%-18s|%-15s|%-15s|%-10s|%-15s|%n", lib.repeatStr("-", 15), lib.repeatStr("-", 18), lib.repeatStr("-", 15), 
							lib.repeatStr("-", 15), lib.repeatStr("-", 10), lib.repeatStr("-", 15));
	}
	
	public void xuatDS() {
		if (listDT.length <= 0) {
			lib.printMessage("Danh sach dien thoai rong! ");
			return;
		}
		lib.printMessage("Danh sach dien thoai");
		xuatTieuDe();
		for(int i = 0; i < listDT.length; i++) {
			listDT[i].xuatThongTin();
		}
	}
	
	public String nhapMaNSX(danhsachNSX listNSX)
	{
		while(true) {
			listNSX.xuatDS();
			String mansx = lib.takeStringInput("Nhap ma NSX: ");
			if(listNSX.timkiemNSX(mansx) == -1)
				lib.printMessage("Khong co ma NSX nay!");			
			else
				return mansx;
		}
	}
	
	public String nhapMaNCC(danhsachNCC listNCC) {
		while(true) {
			listNCC.xuatDS();
			String mancc = lib.takeStringInput("Nhap ma NCC: ");
			if (listNCC.timkiemNCC(mancc) == -1)
				lib.printMessage("Khong co ma NCC nay!");
			else
				return mancc;			
		}
	}
	
	public void themDT(danhsachNSX listNSX, danhsachNCC listNCC) {
		String madt = getID();
		String mansx = nhapMaNSX(listNSX);
		String mancc = nhapMaNCC(listNCC);
		listDT = Arrays.copyOf(listDT, getSoLuong() + 1);

		switch(listNSX.timkiemByID(mansx).gettenNSX()) {
			case "Apple":
				listDT[listDT.length - 1] = new iphone();
				break;
			case "Samsung": 
				listDT[listDT.length - 1] = new samsung();
				break;
			case "Oppo":
				listDT[listDT.length - 1] = new oppo();
				break;
		}
		
		listDT[listDT.length - 1].nhap(madt, mansx, mancc);
	}
		
	public void xoaDT() {
		if(getSoLuong() == 0) {
			lib.printMessage("Danh sach dien thoai rong");
			return;
		}

		String maDienThoai = lib.takeStringInput("Nhap ma dien thoai can xoa: ");
		int index = timkiemMaDienThoai(maDienThoai);
		
		if(index == -1) {
			lib.printMessage("Khong co ma dien thoai nay");
		} else {
			for(int i = index; i < listDT.length - 1; i++) {
				listDT[i] = listDT[i + 1];
			}
			listDT[listDT.length - 1] = null;
			listDT = Arrays.copyOf(listDT, listDT.length - 1);
			cnt--;
			lib.printMessage("Xoa thanh cong dien thoai co ma " + maDienThoai);
		}
	}
	
	public int timkiemMaDienThoai(String maDienThoai) {		//trả về vị trí mã đt tìm thấy
		for(int i = 0; i < listDT.length; i++) {
			if(listDT[i].getmaDienThoai().equals(maDienThoai)) {
				return i;
			}
		}
		return -1;
	}
	
	public void timkiemByKey() {
		String key = lib.takeStringInput("Nhap tu khoa chua ma dien thoai can tim: ");
		dienthoai[] dsdt = new dienthoai[listDT.length];

		int length = 0;
		for(int i = 0; i < listDT.length; i++) {
			if(listDT[i].getmaDienThoai().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))) {
				dsdt[length++] = listDT[i];
			}
		}

		if(length == 0) {
			lib.printMessage("Khong co dien thoai co ma chua tu khoa \"" + key + "\"");
		} else {
			lib.printMessage("Ket qua tim kiem theo ma dien thoai chua tu khoa \"" + key + "\"");
			xuatTieuDe();
			for(int i = 0; i < length; i++) {
				dsdt[i].xuatThongTin();
			}
		}
	}
	
	public void menuSua(danhsachNSX listNSX, danhsachNCC listNCC) {
		if(getSoLuong() == 0) {
			lib.printMessage("Danh sach dien thoai rong");
			return;
		}
		String id = lib.takeStringInput("Nhap ma dien thoai can sua: ");
		int index = timkiemMaDienThoai(id);
		if(index == -1) {
			lib.printMessage("Khong tim thay ma dien thoai nay!");			
		} 
		else {
			boolean exit;
			do {
				xuatTieuDe();
				listDT[index].xuatThongTin();
				exit = false;
				System.out.println("1. Sua ten dien thoai");
				System.out.println("2. Sua ma NSX");
				System.out.println("3. Sua ma NCC");
				System.out.println("4. Sua gia thanh");
				System.out.println("0. Thoat");
				
				switch (lib.takeInputChoice(0,4)) {
					case 1: 
						listDT[index].settenDienThoai(lib.takeStringInput("Nhap ten dien thoai moi: "));
						break;
					case 2: 
						listDT[index].setmaNSX(nhapMaNSX(listNSX));
						break;
					case 3: 
						listDT[index].setmaNhaCungCap(nhapMaNCC(listNCC));
						break;
					case 4: 
						listDT[index].setgiathanh(lib.takeIntegerInput("Nhap gia thanh moi: "));
						break;
					case 0: 
						exit = true;
						break;
				}
				
				if(!exit)
					lib.clearScreen();
			} while(!exit);
		}
	}
	
	public void menu(danhsachNSX listNSX, danhsachNCC listNCC) {
		while(true) {
			xuatDS();
			System.out.println("1. Them dien thoai");
			System.out.println("2. Xoa dien thoai");
			System.out.println("3. Tim kiem dien thoai");
			System.out.println("4. Sua thong tin dien thoai");
			System.out.println("0. Thoat");
			boolean out = false;
			
			switch (lib.takeInputChoice(0, 4)) {
				case 1:
					themDT(listNSX, listNCC);
					break;
				case 2:
					xoaDT();
					break;
				case 3:
					timkiemByKey();
					break;
				case 4:
					menuSua(listNSX, listNCC);
					break;
				case 0:
					out = true;
					break;
			}
			
			if(out)
				break;
			lib.clearScreen();
		}
	}
}