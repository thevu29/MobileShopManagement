package mobile_shop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class danhsachNCC implements danhsach {
	private nhaCC[] listNCC;
	private int cnt;
	
	public void setSoLuong(int SoLuong) {
		listNCC = Arrays.copyOf(listNCC, SoLuong);
	}
	
	public int getSoLuong() {
		return listNCC.length;
	}
	
	public nhaCC[] getListNCC() {
		return listNCC;
	}

	public void setListNSX(nhaCC[] listNCC) {
		this.listNCC = listNCC;
	}
	
	// khởi tạo mặc định các NCC
	public danhsachNCC() throws IOException {
		listNCC = lib.readNCCFile();
		if (listNCC == null) {		
			cnt = 0;
			listNCC = new nhaCC[3];
			listNCC[0] = new nhaCC(getID(), "TGDD", "12345", "HN", "tgdd@gmail.com");
			listNCC[1] = new nhaCC(getID(), "Cho Lon", "4567", "NT", "cholon@gmail.com");
			listNCC[2] = new nhaCC(getID(), "Dien may xanh", "98765", "HCM", "dmx@gmail.com");
			lib.writeNCCFile(listNCC);
		} else {
			cnt = listNCC.length;
		}
	}
	
	public String getID() {
		cnt++;
		Integer a = cnt;
		String str = a.toString();
		while (str.length() != 3) {
			str = "0" + str;
		}
		str = "NCC" + str;
		return str;
	}
	
	public void xuatTieuDe() {
		System.out.printf("+%-20s-%-20s-%-20s-%-20s-%-20s+ \n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
							lib.repeatStr("-", 20), lib.repeatStr("-", 20));
		
	    System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s| \n","Ma nha cung cap","Ten nha cung cap","Dia chi","So dien thoai","Email");
	    
	    System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s| \n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
	    					lib.repeatStr("-", 20), lib.repeatStr("-", 20));
	}
	
	public void xuatDS() {
		if (getSoLuong() == 0) {
			lib.printMessage("Khong co nha cung cap nao trong danh sach!");
			return;
		}
		lib.printMessage("Danh sach nha cung cap");
		xuatTieuDe();
		for (int i = 0; i < getSoLuong(); i++) {
			listNCC[i].xuatNCC();
		}
	}
	
	public void themNCC() {
		listNCC = Arrays.copyOf(listNCC, listNCC.length + 1);
		listNCC[listNCC.length - 1] = new nhaCC();
		listNCC[listNCC.length - 1].nhapNCC(getID());
	}
	
	public void xoaNCC() {
		String id = lib.takeStringInput("Nhap ma NCC can xoa: ");
		int index = timkiemNCC(id);
		if (index == -1) {
			System.out.println("Khong co ma NCC nay!");
		} else {
			for (int i = index; i < listNCC.length; i++) {
				listNCC[i] = listNCC[i + 1];
			}
			listNCC[listNCC.length - 1] = null;
			listNCC = Arrays.copyOf(listNCC, listNCC.length - 1);
			System.out.println("Xoa thanh cong NCC co ma " + id);
		}
	}
	
	// Tìm kiếm trả về vị trí
	public int timkiemNCC(String maNCC) {
		for (int i = 0; i < listNCC.length; i++) {
			if (listNCC[i].getMaNCC().equals(maNCC)) {
				return i;
			}
		}
		return -1;
	}
	
	// Tìm kiếm trả về ncc tìm thấy đầu tiên
	public nhaCC timkiemById(String maNCC) {
		for (int i = 0; i < listNCC.length; i++) {
			if (listNCC[i].getMaNCC().equals(maNCC)) 
				return listNCC[i];
		}
		return null;
	}
	
	// Tìm kiếm trả về tất cả các nhà cung cấp co chứa từ khóa
	public void timkiemByKey() {
		String key = lib.takeStringInput("Nhap tu khoa chua ma NCC can tim: ");
		nhaCC[] filter = new nhaCC[listNCC.length];
		int length = 0;
		for (int i = 0; i < listNCC.length; i++) {
			if (listNCC[i].getMaNCC().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))) {
				filter[length++] = listNCC[i];
			}
		}
		
		if (length == 0) {
			lib.printMessage("Khong co NCC nao co ma chua tu khoa \"" + key + "\"");
		} else {
			lib.printMessage("Ket qua tim kiem theo ma NCC chua tu khoa \"" + key + "\"");
			xuatTieuDe();
			for (int i = 0; i < length; i++) {
				filter[i].xuatNCC();
			}
		}
	}
	
	public void menuSua() {
		String id = lib.takeStringInput("Nhap ma NCC can sua: ");
		nhaCC ncc = timkiemById(id);
		if (ncc == null) {
			lib.printMessage("Ma NCC khong ton tai!");
		} else {
			boolean exit;
			do {
				exit = false;
				xuatTieuDe();
				ncc.xuatNCC();
				System.out.println("1. Sua ten");
				System.out.println("2. Sua so dien thoai");
				System.out.println("3. Sua dia chi");
				System.out.println("4. Sua email");
				System.out.println("0. Thoat");
				
				switch(lib.takeInputChoice(0, 4)) {
					case 1:
						ncc.setTenNCC(lib.takeStringInput("Nhap ten NCC moi: "));
						break;
					case 2:
						ncc.setSdtNCC(lib.takeStringInput("Nhap so dien thoai NCC moi: "));
						break;
					case 3:
						ncc.setDiachiNCC(lib.takeStringInput("Nhap dia chi NCC moi: "));
						break;
					case 4:
						ncc.setEmailNCC(lib.takeStringInput("Nhap email NCC moi: "));
						break;
					case 0:
						exit = true;
						break;
				}
				if (!exit)
					lib.clearScreen();
			} while(!exit);
		}
	}
	
	public void menu() {
		while (true) {
			xuatDS();
			System.out.println("1. Them NCC");
			System.out.println("2. Xoa NCC");
			System.out.println("3. Sua NCC");
			System.out.println("4. Tim kiem NCC");
			System.out.println("0. Thoat");
			
			boolean exit = false;
			switch(lib.takeInputChoice(0, 4)) {
				case 1:
					themNCC();
					break;
				case 2:
					xoaNCC();
					break;
				case 3:
					menuSua();
					break;
				case 4:
					timkiemByKey();
					break;
				case 0:
					exit = true;
					break;
			}
			
			if (exit)
				break;
			lib.clearScreen();
		}
	}
}