package mobile_shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class shop {
	private ArrayList<connguoi> listNguoi;
	
	public shop() throws IOException {
		listNguoi = lib.readConNguoiFile();
		if (listNguoi == null) {
			listNguoi = new ArrayList<>();
			taoQuanLy();
			connguoi nvbh = new nvBanHang("nvbh", "thevu", "Nguyen The Vu", "HCM", "012345", "29/08/2003", "Nam", "034203", "123", 250000);
			connguoi nvtk = new nvThuKho("nvtk", "quoccuong", "Ho Quoc Cuong", "HCM", "03456", "29/08/2003", "Nam", "01275", "123", 250000);
			connguoi kh = new khachhang("ducthang", "Dao Duc Thang", "HCM", "02468", "29/08/2003", "Nam", "02561", "123");
	        connguoi kh2 = new khachhang("thevu2","The Vu","HN", "03579", "29/08/2002", "Nu", "07821", "234");
	        listNguoi.add(nvbh);
	        listNguoi.add(nvtk);
	        listNguoi.add(kh);
	        listNguoi.add(kh2);
	        lib.writeConNguoiFile(listNguoi);
		}
	}
	
	public ArrayList<connguoi> getListNguoi() {
		return listNguoi;
	}

	public void setListNguoi(ArrayList<connguoi> listNguoi) {
		this.listNguoi = listNguoi;
	}

	public void xuatTieuDeKhachHang() {
        System.out.printf("+%-16s-%-16s-%-16s-%-10s-%-10s-%-10s-%-9s-%-16s+%n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16),
                			lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 9), lib.repeatStr("-", 16));

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%n", "Ma khach hang", "Ho ten", "Dia chi", "SDT", "Ngay sinh", "Gioi tinh", "CMND", 
        					"Password");

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16),
                			lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 9), lib.repeatStr("-", 16));
    }

    public void xuatTieuDeNhanVien() {
        System.out.printf("+%-16s-%-16s-%-16s-%-10s-%-10s-%-10s-%-9s-%-16s-%-10s-%-16s+%n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), 
			        		lib.repeatStr("-", 16), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 9), 
			        		lib.repeatStr("-", 16), lib.repeatStr("-", 10), lib.repeatStr("-", 16));

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%-10s|%-16s|%n", "ID nhan vien", "Ho ten", "Dia chi", "SDT", "Ngay sinh", "Gioi tinh",
        					"CMND", "Password", "Muc luong", "Chuc vu");

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%-10s|%-16s|%n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), 
			        		lib.repeatStr("-", 16), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 10), lib.repeatStr("-", 9), 
			        		lib.repeatStr("-", 16), lib.repeatStr("-", 10), lib.repeatStr("-", 16));
    }
	
    public void xuatDanhsachKhachHang() {
    	int cnt = 0;
    	xuatTieuDeKhachHang();
    	for (connguoi kh : listNguoi) {
    		if (kh instanceof khachhang) {
    			kh.xuatThongTin();
    			cnt++;
    		}
    	}
    	if (cnt == 0)
    		lib.printMessage("Khong co khach hang nao!");
    }
    
    public void xuatDanhsachNhanVien() {
    	int cnt = 0;
    	xuatTieuDeNhanVien();
    	for (connguoi nv : listNguoi) {
    		if (nv instanceof nvBanHang || nv instanceof nvThuKho) {
    			nv.xuatThongTin();
    			cnt++;
    		}
    	}
    	
    	if (cnt == 0) 
    		lib.printMessage("Khong co nhan vien nao!");
    }
    
    public void xuatDanhsachNhanVien(ArrayList<connguoi> listNV) {
    	int cnt = 0;
    	xuatTieuDeNhanVien();
    	for (connguoi nv : listNV) {
    		nv.xuatThongTin();
    		cnt++;
    	}
    	
    	if (cnt == 0) 
    		lib.printMessage("Khong co nhan vien nao!");
    }
    
	public void taoQuanLy() {
		if (listNguoi.stream().filter(nguoi -> nguoi instanceof quanly).findAny().orElse(null) != null) {
			System.out.println("Da co nguoi quan ly. Khong the tao them!");
			return;
		}
		nhanvien ql = new quanly("ql", "admin", "Lam Quoc Dai", "HCM", "09876", "29/08/2003", "05213", "Nam", "admin", 5000000);
		listNguoi.add(ql);
	}
	
	public void taoNguoi(String type) {		//type: kh hoặc nv
		String id;
		while (true) {
			id = lib.takeStringInput("Nhap id: ");
			if (timkiemById(id) != null) {
				System.out.println("ID nay da ton tai. Vui lòng nhap ID khac!");
			} else {
				break;
			}
		}
		
		String hoten = lib.takeStringInput("Nhap ho ten: ");
		String diachi = lib.takeStringInput("Nhap dia chi: ");
		String sdt = lib.takePhoneInput("Nhap so dien thoai: ");
		String ngaysinh = lib.takeDateInput("Nhap ngay sinh: ");
		String cmnd = lib.takeNumberInput("Nhap CMND: ");
		String gioitinh = chonGioiTinh();
		String password = lib.takeStringInput("Nhap mat khau: ");
		
		if (type.equals("kh")) {
			connguoi kh = new khachhang(id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password);
			listNguoi.add(kh);
		} else {
			int mucluong = lib.takeIntegerInput("Nhap muc luong: ");
			System.out.println("Chon chuc vu cua nhan vien: ");
			System.out.println("1. Nhan vien ban hang");
			System.out.println("2. Nhan vien thu kho");
			
			switch(lib.takeInputChoice(1, 2)) {
				case 1:
					nhanvien nvbh = new nvBanHang("nvbh", id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password, mucluong);
					listNguoi.add(nvbh);
					break;
				case 2:
					nhanvien nvtk = new nvThuKho("nvtk", id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password, mucluong);
					listNguoi.add(nvtk);
					break;
			}
		}
	}
	
	public void xoaNguoi(String type) {
		String id;
		if (type.equals("kh")) {
			id = lib.takeStringInput("Nhap ID khach hang can xoa: ");
			connguoi nguoi = timkiemById(id, "kh");
			if (nguoi == null) {
				lib.printMessage("Khong tim thay ID nay!");
			} else {
				listNguoi.remove(nguoi);
				lib.printMessage("Xoa thanh cong khach hang co ID " + id);
			}
		}
		else {
			id = lib.takeStringInput("Nhap ID nhan vien can xoa: ");
			connguoi nguoi = timkiemById(id, "nv");
			if (nguoi == null) {
				lib.printMessage("Khong tim thay ID nay!");
			} else {
				listNguoi.remove(nguoi);
				lib.printMessage("Xoa thanh cong nhan vien co ID " + id);
			}
		}
	}
	
	public String chonGioiTinh() {
		System.out.println("Gioi tinh cua ban: ");
		System.out.println("1. Nam");
		System.out.println("2. Nu");
		System.out.println("3. Khac");
		switch(lib.takeInputChoice(1, 3)) {
			case 1: return "Nam";
			case 2: return "Nu";
			case 3: return "Khac";
		}
		return null;
	}
	
	// tra ve obj connguoi co ID tim thay
	public connguoi timkiemById(String id) {
		return listNguoi.stream().filter(nguoi-> nguoi.id.equals(id)).findAny().orElse(null);
	}
	
	// tra ve obj connguoi co ID tim thay va co dieu kien
	public connguoi timkiemById(String id, String type) {
		if (type.equals("kh"))
			return listNguoi.stream().filter(nguoi -> nguoi.id.equals(id) && (nguoi instanceof khachhang)).findFirst().orElse(null);
		else if (type.equals("nvbh"))
			return listNguoi.stream().filter(nguoi -> nguoi.id.equals(id) && (nguoi instanceof nvBanHang)).findFirst().orElse(null);
		else if (type.equals("nvtk"))
			return listNguoi.stream().filter(nguoi -> nguoi.id.equals(id) && (nguoi instanceof nvThuKho)).findFirst().orElse(null);
		else
			return listNguoi.stream().filter(nguoi -> nguoi.id.equals(id) && (nguoi instanceof nvBanHang || nguoi instanceof nvThuKho)).findFirst().orElse(null);
	}
	
	// tim kiem tu khoa theo id
	public void timkiemByKey(String type) {		//type: kh, nv
		String key = lib.takeStringInput("Nhap tu khoa chua id muon tim: ");
		ArrayList<connguoi> nguoiFilter = new ArrayList<>();
		
		// khach hang
		if (type.equals("kh")) {
			for (connguoi kh : listNguoi) {
				if (kh instanceof khachhang)  {
					if (kh.getId().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))) {
						nguoiFilter.add(kh);						
					}
				}
			}
			if (!nguoiFilter.isEmpty()) {
				lib.printMessage("Ket qua tim kiem theo ma khach hang chua tu khoa \"" + key + "\"");
				xuatTieuDeKhachHang();
				for (connguoi nguoi : nguoiFilter) {
					nguoi.xuatThongTin();
				}
			} else {
				lib.printMessage("Khong tim thay khach hang nao co ID chua tu khoa " + key);
			}
		}
		// nhan vien
		else {
			for (connguoi nv : listNguoi) {
				if (nv instanceof nvBanHang || nv instanceof nvThuKho) {
					if (nv.getId().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))) {
						nguoiFilter.add(nv);
					}
				}
			}
			if (!nguoiFilter.isEmpty()) {
				lib.printMessage("Ket qua tim kiem theo ma nhan vien chua tu khoa " + "\"" + key + "\"");
				xuatTieuDeNhanVien();
				for (connguoi nguoi : nguoiFilter) {
					nguoi.xuatThongTin();
				}
			} else {
				lib.printMessage("Khong tim thay nhan vien nao co ID chua tu khoa " + key);
			}
		}
	}
	
	public void menuSua(String type, connguoi nguoi) {
		int max = 7;
		if (type == "nv")  max = 8;
		
		boolean exit;
		do {
			 exit = false;
			 if (type.equals("kh"))
				 xuatTieuDeKhachHang();
			 else
				 xuatTieuDeNhanVien();
			 
			 nguoi.xuatThongTin();
			 System.out.println("1. Sua ten");
	         System.out.println("2. Sua dia chi");
	         System.out.println("3. Sua SDT");
	         System.out.println("4. Sua ngay sinh");
	         System.out.println("5. Sua gioi tinh");
	         System.out.println("6. Sua CMND");
	         System.out.println("7. Doi password");
	         if (type.equals("nv")) {
	        	 System.out.println("8. Sua muc luong");
	         }
	         System.out.println("0. Thoat");
	         
	         switch(lib.takeInputChoice(0, max)) {
		         case 1:
		        	 nguoi.setHoTen(lib.takeStringInput("Nhap ho ten moi: "));
		        	 break;
		         case 2:
		        	 nguoi.setDiaChi(lib.takeStringInput("Nhap dia chi moi: "));
		        	 break;
		         case 3:
		        	 nguoi.setSDT(lib.takePhoneInput("Nhap so dien thoai moi: "));
		        	 break;
		         case 4:
		        	 nguoi.setNgaySinh(lib.takeDateInput("Nhap ngay sinh moi: "));
		        	 break;
		         case 5:
		        	 nguoi.setGioiTinh(chonGioiTinh());
		        	 break;
		         case 6:
		        	 nguoi.setCMND(lib.takeStringInput("Nhap CMND moi: "));
		        	 break;
		         case 7:
		        	 nguoi.setPassword(lib.takeStringInput("Nhap mat khau moi: "));
		        	 break;
		         case 8:
		        	 ((nhanvien)nguoi).setMucluong(lib.takeIntegerInput("Nhap muc luong moi: "));
		        	 break;
		         case 0:
		        	 exit = true;
		        	 break;
	         }
	         
	         if (!exit)
	        	 lib.clearScreen();
		} while (!exit);
	}
	
	public void menuKhachHang() {
		boolean exit;
		do {
			exit = false;
			xuatDanhsachKhachHang();
			System.out.println("1. Them khach hang");
			System.out.println("2. Xoa khach hang");
			System.out.println("3. Sua thong tin khach hang");
			System.out.println("4. Tim kiem khach hang");
			System.out.println("0. Thoat");
			
			switch(lib.takeInputChoice(0, 4)) {
				case 1:
					taoNguoi("kh");
					break;
				case 2:
					xoaNguoi("kh");
					break;
				case 3:
					String id = lib.takeStringInput("Nhap ID khach hang muon sua thong tin: ");
					connguoi kh = timkiemById(id, "kh");
					if (kh == null)
						lib.printMessage("Khong tim thay khach hang co ID " + id);
					else
						menuSua("kh", kh);
					break;
				case 4:
					timkiemByKey("kh");
					break;
				case 0:
					exit = true;
					break;
			}
			
			if (!exit)
				lib.clearScreen();
		} while (!exit);
	}
	
	public void menuNhanvien() {
		boolean exit;
		do {
			exit = false;
			xuatDanhsachNhanVien();
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xoa nhan vien");
            System.out.println("3. Tim kiem nhan vien");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Loc theo chuc vu");
            System.out.println("0. Thoat");
            
            switch(lib.takeInputChoice(0, 5)) {
	            case 1:
	            	taoNguoi("nv");
	            	break;
	            case 2:
	            	xoaNguoi("nv");
	            	break;
	            case 3:
	            	timkiemByKey("nv");
	            	break;
	            case 4:
	            	String id = lib.takeStringInput("Nhap ID nhan vien can sua thong tin: ");
	            	connguoi nv = timkiemById(id, "nv");
	            	if (nv == null)
	            		lib.printMessage("Khong tim thay nhan vien co ID " + id);
	            	else 
	            		menuSua("nv", nv); 
	            	break;
	            case 5:
	            	ArrayList<connguoi> nvFilter = new ArrayList<>();
	            	
	            	System.out.println("1. Loc nhan vien thu kho");
                    System.out.println("2. Loc nhan vien ban hang"); 
                    switch(lib.takeInputChoice(1, 2)) {
                    	case 1:
                    		nvFilter = locNhanVien("nvtk");
                    		if (nvFilter == null)
                    			lib.printMessage("Khong co nhan vien thu kho nao!");
                    		else
                    			xuatDanhsachNhanVien(nvFilter);
                    		break;
                    	case 2:
                    		nvFilter = locNhanVien("nvbh");
                    		if (nvFilter == null)
                    			lib.printMessage("Khong co nhan vien ban hang nao!");
                    		else
                    			xuatDanhsachNhanVien(nvFilter);
                    		break;		
                    }
                    
                    break;
	            case 0:
	            	exit = true;
	            	break;	          
            }
            
            if (!exit)
            	lib.clearScreen();
		} while (!exit);
	}
	
	public ArrayList<connguoi> locNhanVien(String type) {
		ArrayList<connguoi> nvFilter = new ArrayList<>();
		if (type.equals("nvbh")) {
			for (connguoi nv : listNguoi) {
				if (nv instanceof nvBanHang) 
					nvFilter.add(nv);
			}
		} 
		else {
			for (connguoi nv : listNguoi) {
				if (nv instanceof nvThuKho) 
					nvFilter.add(nv);
			}
		}
		if (nvFilter.toString().equals("[]"))
			return null;
		return nvFilter;
	}
	
	public connguoi login() {
		lib.printMessage("Dang nhap");
        String id = lib.takeStringInput("Nhap id: ");
        String password = lib.takeStringInput("Nhap password: ");
        return listNguoi.stream().filter(nguoi -> nguoi.id.equals(id) && nguoi.password.equals(password)).findAny().orElse(null);
	}
}