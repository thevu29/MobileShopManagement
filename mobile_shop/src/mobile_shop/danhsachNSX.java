package mobile_shop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class danhsachNSX implements danhsach {
	private nhaSX[] listNSX;
	private int cnt;
	
	public void setSoLuong(int SoLuong) {
		listNSX = Arrays.copyOf(listNSX, SoLuong);
	}
	
	public int getSoLuong() {
		return listNSX.length;
	}
	
	public nhaSX[] getListNSX() {
		return listNSX;
	}

	public void setListNSX(nhaSX[] listNSX) {
		this.listNSX = listNSX;
	}
	
	// khoi tao mac dinh cac NSX
	public danhsachNSX() throws IOException {
		listNSX = lib.readNSXFile();
		if (listNSX == null) {
			cnt = 0;
			listNSX = new nhaSX[3];
			listNSX[0] = new nhaSX(getID(), "Apple", "12345", "USA", "apple@gmail.com");
			listNSX[1] = new nhaSX(getID(), "SamSung", "56789", "KOREA", "samSung@gmail.com");
			listNSX[2] = new nhaSX(getID(), "Oppo", "13579", "CHINA", "oppo@gmail.com");
			lib.writeNSXFile(listNSX);
		} else {
			cnt = listNSX.length;
		}
	}
	
	public String getID() {
		cnt++;
		Integer a = cnt;
		String str = a.toString();
		while (str.length() != 3) {
			str = "0" + str;
		}
		str = "NSX" + str;
		return str;
	}
	
	public void xuatTieuDe() {
		System.out.printf("+%-20s-%-20s-%-20s-%-20s-%-20s+%n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
							lib.repeatStr("-", 20), lib.repeatStr("-", 20));
		
		System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "Ma nha san xuat", "Ten nha san xuat", "SDT", "Dia chi", "Email");
		
		System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|%n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
							lib.repeatStr("-", 20), lib.repeatStr("-", 20));
	}
	
	public void xuatDS() {
		if (getSoLuong() <= 0) {
			lib.printMessage("Khong co NSX nao trong danh sach! ");
			return;
		}
		lib.printMessage("Danh sach nha san xuat");
		xuatTieuDe();
		for (int i = 0; i < listNSX.length; i++) {
			listNSX[i].xuatNSX();
		}
	}
	
	public void themNSX() {
		listNSX = Arrays.copyOf(listNSX, listNSX.length + 1);
		listNSX[listNSX.length - 1] = new nhaSX();
		listNSX[listNSX.length - 1].nhapNSX(getID());
	}
	
	public void xoaNSX() {
		String maNSX = lib.takeStringInput("Nhap ma NSX can xoa: ");
		int index = timkiemNSX(maNSX);
		if (index == -1) {
			lib.printMessage("Khong co ma NSX nay");
		} else {
			for (int i = index; i < listNSX.length - 1; i++) {
				listNSX[i] = listNSX[i + 1];
			}
			listNSX[listNSX.length - 1] = null;
			listNSX = Arrays.copyOf(listNSX, listNSX.length - 1);
			System.out.println("Xoa thanh cong NSX co ma " + maNSX);
		}
	}
	
	// tra ve vi tri
	public int timkiemNSX(String maNSX) {
		for (int i = 0; i < listNSX.length; i++) {
			if (listNSX[i].getmaNSX().equals(maNSX)) {
				return i;
			}
		}
		return -1;
	}
	
	// tra ve nsx duoc tim thay dau tien
	public nhaSX timkiemByID(String id) {
		for (int i = 0; i < listNSX.length; i++) {
			if (listNSX[i].getmaNSX().equals(id)) {
				return listNSX[i];
			}
		}
		return null;
	}
	
	// Tim kiem tra ve tat ca nsx co ma NSX chua key
	public void timkiemByKey() {
		String key = lib.takeStringInput("Nhap tu khoa theo ma NSX can tim: ");
		nhaSX[] filter = new nhaSX[getSoLuong()];
		int length = 0;
		for (int i = 0; i < listNSX.length; i++) {
			if (listNSX[i].getmaNSX().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))) {
				filter[length++] = listNSX[i];
			}
		}
			
		if (length == 0) {
			lib.printMessage("Khong co NSX nao co ma chu tu khoa \"" + key + "\"");
		} else {
			lib.printMessage("Ket qua tim kiem theo ma NSX chua tu khoa \"" + key + "\"");
			xuatTieuDe();
			for (int i = 0; i < length; i++) 
				filter[i].xuatNSX();				
		}
	}
	
	public void menuSuaNSX() {
		String id = lib.takeStringInput("Nhap ma NSX can sua: ");
		nhaSX nsx = timkiemByID(id);
		if (nsx == null) {
			lib.printMessage("Ma NSX khong ton tai!");
		} else {
			boolean exit;
			do {
				exit = false;
				xuatTieuDe();
				nsx.xuatNSX();
				System.out.println("1. Sua ten");
				System.out.println("2. Sua so dien thoai");
				System.out.println("3. Sua dia chi");
				System.out.println("4. Sua email");
				System.out.println("0. Thoat");
				
				switch(lib.takeInputChoice(0, 4)) {
					case 1:
						nsx.settenNSX(lib.takeStringInput("Nhap ten NSX moi: "));
						break;
					case 2:
						nsx.setSDTNSX(lib.takeStringInput("Nhap so dien thoai NSX moi: "));
						break;
					case 3:
						nsx.setdiachiNSX(lib.takeStringInput("Nhap dia chi NSX moi: "));
						break;
					case 4:
						nsx.setemailNSX(lib.takeStringInput("Nhap email NSX moi: "));
						break;
					case 0:
						exit = true;
						break;
				}
				
				if (!exit) {
					lib.clearScreen();
				}
			} while (!exit);
		}
	}
	
	public void menu() {
		while(true) {
			xuatDS();
			System.out.println("1. Them NSX");
			System.out.println("2. Xoa NSX");
			System.out.println("3. Sua NSX");
			System.out.println("4. Tim kiem NSX");
			System.out.println("0. Thoat");
			
			boolean exit = false;
			switch(lib.takeInputChoice(0, 4)) {
				case 1:
					themNSX();
					break;
				case 2:
					xoaNSX();
					break;
				case 3:
					menuSuaNSX();
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