package mobile_shop;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class lib {
	static Scanner sc = new Scanner(System.in);
	
	// lặp lại chuỗi str với n lần
    public static String repeatStr(String str, int n) {
        return String.join("", Collections.nCopies(n, str));
    }

    // nhận input int
    public static int takeIntegerInput(String inp){
        while(true){
            System.out.print(inp);
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (input <= 0)
                	lib.printMessage("Khong chap nhan so <= 0");
                else
                    return input;
            } catch (NumberFormatException e){
            	lib.printMessage("Ban chi duoc phep nhap so");
            }
        }
    }
    
    // nhận input string
    public static String takeStringInput(String inp) {
        while(true){
            System.out.print(inp);
            try {
                String input = sc.nextLine();
                if (input.isEmpty())
                	lib.printMessage("Ban chua nhap gi het");
                else
                    return input;
            } catch (NoSuchElementException  e){
            	lib.printMessage("Ban chua nhap gi het");
            }
        }
    }
    
    // nhận input date
    public static String takeDateInput(String inp) {
    	while (true) {
    		System.out.print(inp);
    		try {
    			String input = sc.nextLine();
    			if (input.isEmpty()) {
    				lib.printMessage("Ban chua nhap gi het!");
    			} else if (!lib.checkDate(input)) {
    				lib.printMessage("Nhap khong dung dinh dang!");
    			} else {
    				return input;
    			}
    		} catch (NoSuchElementException e) {
    			lib.printMessage("Ban chua nhap gi het!");
    		}
    	}
    }
    
    // kiểm tra định dạng date
    public static boolean checkDate(String date) {
    	try {
    		String[] part = date.split("/");
    		int day = Integer.parseInt(part[0]);
    		int month = Integer.parseInt(part[1]);
    		int year = Integer.parseInt(part[2]);
    		
    		if (day < 0 || day > 31)
    			return false;
    		if (month < 0 || month > 12)
    			return false;
    		if (year < 0)
    			return false;
    		return true;
    	} catch(Exception e) {
    		return false;
    	}
    }
    
    // nhận input phone
    public static String takePhoneInput(String inp) {
    	while(true) {
    		System.out.print(inp);
    		try {
    			String input = sc.nextLine();
    			int check = Integer.parseInt(input);
    			if (input.isEmpty()) {
    				lib.printMessage("Ban chua nhap gi het!");
    			} else if (input.length() != 10) {
    				lib.printMessage("So dien thoai phai co 10 chu so!");
    			} else {
    				return input;
    			}
    		} catch (NoSuchElementException  e){
                lib.printMessage("Ban chua nhap gi het!");
            } catch (NumberFormatException e) {
                lib.printMessage("So dien thoai phai la cac chu so!");
            }
    	}
    }
    
    // nhận input là số nhưng trả về string
    public static String takeNumberInput(String inp) {
    	while(true) {
    		System.out.print(inp);
    		try {
    			String input = sc.nextLine();
    			int check = Integer.parseInt(input);
    			if (input.isEmpty()) {
    				lib.printMessage("Ban chua nhap gi het!");
    			} else {
    				return input;
    			}
    		} catch (NoSuchElementException  e){
                lib.printMessage("Ban chua nhap gi het!");
            } catch (NumberFormatException e) {
                lib.printMessage("Chi duoc phep nhap so!");
            }
    	}
    }
    
    // nhận lựa chọn với khoảng từ min tới max [min, max]
    public static int takeInputChoice(int min, int max) {
        String choice;
        int sln = 0;
        while(true){
            if(sln == 0)
                System.out.print("Nhap lua chon: ");
            else
                System.out.print("Nhap lai lua chon: ");
            choice = sc.nextLine();
            try {
                int result = Integer.parseInt(choice);
                if(result >= min && result <= max)
                    return result;
                lib.printMessage("Lua chon cua ban khong dung lam");
            } catch (NumberFormatException e){
            	lib.printMessage("Ban chi duoc phep nhap so");
            }
            sln++;
        }
    }
    
    // trả về ngày tháng năm hiện tại
    public static String getDateNow() {
        LocalDate date = LocalDate.now();
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    // clear console
    public static void clearScreen() {
        lib.printMessage("Nhap bat ky de tiep tuc");
        sc.nextLine();
        System.out.printf("%5s", lib.repeatStr("\n", 5));
    }
    
    // hiển thị message
    public static void printMessage(String message) {
        System.out.printf("%30s " + " *** %s *** " + " %30s%n", lib.repeatStr(" ", 30), message, lib.repeatStr(" ", 30));
    }
    
    // đọc, ghi file điện thoại   
    public static dienthoai[] readDTFile() {
		try {
			FileReader fin = new FileReader("dienthoai.txt");
			int c, length = 0;
			String str = "";
			while ((c = fin.read()) != -1) {
				str += (char)c;
			}
			if (str != "") {
				String[] strArr = str.split("\n");
				dienthoai[] listDT = new dienthoai[strArr.length];
				
				for (String a : strArr) {
					String[] strArr2 = a.split(",");
					if (strArr2[strArr2.length - 1].contains("\r")) {
	    				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);    				
	    			}
					
					if (strArr2[1].toLowerCase(Locale.ROOT).contains("iphone")) {
						listDT[length++] = new iphone(strArr2[0], strArr2[1], strArr2[2], strArr2[3], Integer.parseInt(strArr2[4]), Integer.parseInt(strArr2[5]));
					} else if (strArr2[1].toLowerCase(Locale.ROOT).contains("samsung")) {
						listDT[length++] = new samsung(strArr2[0], strArr2[1], strArr2[2], strArr2[3], Integer.parseInt(strArr2[4]), Integer.parseInt(strArr2[5]));
					} else if (strArr2[1].toLowerCase(Locale.ROOT).contains("oppo")) {
						listDT[length++] = new oppo(strArr2[0], strArr2[1], strArr2[2], strArr2[3], Integer.parseInt(strArr2[4]), Integer.parseInt(strArr2[5]));
					}
				}
				
				if (fin != null)
					fin.close();
				return listDT;
			}
			else {
				if (fin != null)
					fin.close();
				return null;
			}
		} catch (Exception e) {
			return null;
		}
    }
       
    public static void writeDTFile(dienthoai[] listDT) throws IOException {
    	FileWriter fout = new FileWriter("dienthoai.txt");
    	for (int i = 0; i < listDT.length; i++) {
    		if (i != listDT.length - 1) {
    			fout.write(listDT[i].getmaDienThoai() + "," + listDT[i].gettenDienThoai() + "," + listDT[i].getmaNSX() + "," + listDT[i].getmaNhaCungCap() + 
    					"," + listDT[i].getgiathanh() + "," + listDT[i].getSoLuong() + "\n");    			
    		} else {
    			fout.write(listDT[i].getmaDienThoai() + "," + listDT[i].gettenDienThoai() + "," + listDT[i].getmaNSX() + "," + listDT[i].getmaNhaCungCap() + 
    					"," + listDT[i].getgiathanh() + "," + listDT[i].getSoLuong());  
    		}
    	}
    	
    	if (fout != null)
    		fout.close();
    }
    
    // đọc, ghi file NCC
    public static nhaCC[] readNCCFile() {
    	try {
    		FileReader fin = new FileReader("nhacungcap.txt");
        	String str = "";
        	int c, length = 0;
        	while ((c = fin.read()) != -1) {
        		str += (char)c;
        	}
        	
        	if (str != "") {
        		String[] strArr = str.split("\n");
        		nhaCC[] listNCC = new nhaCC[strArr.length];
        		
        		for (String s : strArr) {
        			String[] strArr2 = s.split(",");
        			if (strArr2[strArr2.length - 1].contains("\r")) {
        				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);    				
        			}
        			listNCC[length++] = new nhaCC(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4]);
        		}
        		
        		if (fin != null) 
        			fin.close();
        		return listNCC;
        	} else {
        		if (fin != null) 
        			fin.close();
        		return null;
        	}
    	} catch(Exception e) {
    		return null;
    	}
    }
    
    public static void writeNCCFile(nhaCC[] listNCC) throws IOException {
    	FileWriter fout = new FileWriter("nhacungcap.txt");
    	for (int i = 0; i < listNCC.length; i++) {
    		if (i != listNCC.length - 1) {
    			fout.write(listNCC[i].getMaNCC() + "," + listNCC[i].getTenNCC() + "," + listNCC[i].getDiachiNCC() + "," + listNCC[i].getSdtNCC() + 
    					"," + listNCC[i].getEmailNCC() + "\n");    			
    		} else {
    			fout.write(listNCC[i].getMaNCC() + "," + listNCC[i].getTenNCC() + "," + listNCC[i].getDiachiNCC() + "," + listNCC[i].getSdtNCC() + 
    					"," + listNCC[i].getEmailNCC());  
    		}
    	}
    	
    	if (fout != null)
    		fout.close();
    }
    
    // đọc, ghi file NSX
    public static nhaSX[] readNSXFile() {
    	try {
    		FileReader fin = new FileReader("nhasanxuat.txt");
        	String str = "";
        	int c, length = 0;
        	while ((c = fin.read()) != -1) {
        		str += (char)c;
        	}
        	if (str != "") {
        		String[] strArr = str.split("\n");
        		nhaSX[] listNSX = new nhaSX[strArr.length];
        		
        		for (String s : strArr) {
        			String[] strArr2 = s.split(",");
        			if (strArr2[strArr2.length - 1].contains("\r")) {
        				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);    				
        			}
        			listNSX[length++] = new nhaSX(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4]);
        		}
        		
        		if (fin != null) 
        			fin.close();
        		return listNSX;
        	} else {
        		if (fin != null) 
        			fin.close();
        		return null;
        	}
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    public static void writeNSXFile(nhaSX[] listNSX) throws IOException {
    	FileWriter fout = new FileWriter("nhasanxuat.txt");
    	for (int i = 0; i < listNSX.length; i++) {
    		if (i != listNSX.length - 1) {
    			fout.write(listNSX[i].getmaNSX() + "," + listNSX[i].gettenNSX() + "," + listNSX[i].getSDTNSX() + "," + listNSX[i].getdiachiNSX() + 
    					"," + listNSX[i].getemailNSX() + "\n");    			
    		} else {
    			fout.write(listNSX[i].getmaNSX() + "," + listNSX[i].gettenNSX() + "," + listNSX[i].getSDTNSX() + "," + listNSX[i].getdiachiNSX() + 
    					"," + listNSX[i].getemailNSX());  
    		}
    	}
    	
    	if (fout != null)
    		fout.close();
    }
    
    // đọc, ghi file connguoi
    public static ArrayList<connguoi> readConNguoiFile() {
    	try {
    		FileReader fin = new FileReader("nguoi.txt");
        	String str = "";
        	int c;
        	while ((c = fin.read()) != -1) {
        		str += (char)c;
        	}
        	
        	if (str != "") {
        		String[] strArr = str.split("\n");
        		ArrayList<connguoi> listNguoi = new ArrayList<>();
        		
        		for (String s : strArr) {
        			String[] strArr2 = s.split(",");
        			if (strArr2[strArr2.length - 1].contains("\r")) {
        				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);    				
        			}
        			if (strArr2[0].equals("nvbh")) {
        				connguoi nvbh = new nvBanHang(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4], strArr2[5], strArr2[6], strArr2[7], strArr2[8], 
        						Integer.parseInt(strArr2[9]));
        				listNguoi.add(nvbh);
        			} else if (strArr2[0].equals("nvtk")) {
        				connguoi nvtk = new nvThuKho(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4], strArr2[5], strArr2[6], strArr2[7], strArr2[8], 
        						Integer.parseInt(strArr2[9]));
        				listNguoi.add(nvtk);
        			} else if (strArr2[0].equals("ql")) {
        				connguoi ql = new quanly(strArr2[0], strArr2[1], strArr2[2], strArr2[3], strArr2[4], strArr2[5], strArr2[6], strArr2[7], strArr2[8], 
        						Integer.parseInt(strArr2[9]));
        				listNguoi.add(ql);
        			} else {
        				connguoi kh = new khachhang(strArr2[1], strArr2[2], strArr2[3], strArr2[4], strArr2[5], strArr2[6], strArr2[7], strArr2[8]);
        				listNguoi.add(kh);
        			}
        		}
        		
        		if (fin != null)
        			fin.close();
        		return listNguoi;
        	} else {
        		if (fin != null)
        			fin.close();
        		return null;
        	}
    	} catch(Exception e) {
    		return null;
    	}
    }
    
    public static void writeConNguoiFile(ArrayList<connguoi> listNguoi) throws IOException {
    	FileWriter fout = new FileWriter("nguoi.txt");
    	int i = 0;
    	for (connguoi nguoi : listNguoi) {
    		if (i != (listNguoi.size() - 1)) {
    			if (nguoi instanceof nvBanHang || nguoi instanceof nvThuKho || nguoi instanceof quanly) {
    				fout.write(((nhanvien)nguoi).getChucvu() + "," + nguoi.getId() + "," + nguoi.getHoTen() + "," + nguoi.getDiaChi() + "," + nguoi.getSDT() 
    				+ "," + nguoi.getNgaySinh() + "," + nguoi.getGioiTinh() + "," + nguoi.getCMND() + "," + nguoi.getPassword() + "," 
    				+ ((nhanvien)nguoi).getMucluong() + "\n");
    			} else {
    				fout.write("kh," + nguoi.getId() + "," + nguoi.getHoTen() + "," + nguoi.getDiaChi() + "," + nguoi.getSDT() + "," + nguoi.getNgaySinh() 
    				+ "," + nguoi.getGioiTinh() + "," + nguoi.getCMND() + "," + nguoi.getPassword() + "\n");
    			}
    		}
    		else {
    			if (nguoi instanceof nvBanHang || nguoi instanceof nvThuKho || nguoi instanceof quanly) {
    				fout.write(((nhanvien)nguoi).getChucvu() + "," + nguoi.getId() + "," + nguoi.getHoTen() + "," + nguoi.getDiaChi() + "," + nguoi.getSDT() 
    				+ "," + nguoi.getNgaySinh() + "," + nguoi.getGioiTinh() + "," + nguoi.getCMND() + "," + nguoi.getPassword() + "," 
    				+ ((nhanvien)nguoi).getMucluong());
    			} else {
    				fout.write("kh," + nguoi.getId() + "," + nguoi.getHoTen() + "," + nguoi.getDiaChi() + "," + nguoi.getSDT() + "," + nguoi.getNgaySinh() 
    				+ "," + nguoi.getGioiTinh() + "," + nguoi.getCMND() + "," + nguoi.getPassword());
    			}
    		}
    		i++;
    	}
    	
    	if (fout != null)
    		fout.close();
    }
    
    // đọc, ghi file hóa đơn
    public static ArrayList<hoadon> readHoaDonFile() {
    	try {
    		FileReader fin = new FileReader("hoadon.txt");
        	ArrayList<hoadon> listHD = new ArrayList<>();
        	
        	String str = "";
        	int c;
        	while ((c = fin.read()) != -1) {
        		str += (char)c;
        	}
        	
        	if (str != "") {
        		String[] strArr = str.split("\n");
        		for (String s : strArr) {
        			String[] strArr2 = s.split(",");
        			if (strArr2[strArr2.length - 1].contains("\r")) {
        				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);
        			}

        			danhsachCTHD cthd = new danhsachCTHD();
        			for (int i = 6; i < strArr2.length; i+=3) {
        				int j = i + 1, k = j + 1;
        				if (strArr2[i] != null && strArr2[j] != null && strArr2[k] != null)
        					cthd.getListCTHD().add(new chitietHD(strArr2[i], Integer.parseInt(strArr2[j]), Integer.parseInt(strArr2[k])));		    				
        			}
        			listHD.add(new hoadon(strArr2[0], strArr2[1], strArr2[2], strArr2[3], cthd.TongTien(), cthd, strArr2[5]));
        		}    
        		if (fin != null)
        			fin.close();
        		return listHD;
        	} else {
        		if (fin != null)
        			fin.close();
        		return null;
        	}
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    public static void writeHoaDonFile(ArrayList<hoadon> listHD) throws IOException {
    	FileWriter fout = new FileWriter("hoadon.txt");
    	int i = 0;
    	for (hoadon hd : listHD) {
    		String s = "";
    		for (chitietHD cthd : hd.getDSCTHD().getListCTHD()) {
    			s = s + "," + cthd.getMadienthoai() + "," + cthd.getSoluong() + "," + cthd.getDongia();
    		}
    		
    		if (i != (listHD.size() - 1)) {
    			fout.write(hd.getMahd() + "," + hd.getManv() + "," + hd.getMakh() + "," + hd.getThoigianxuat() + "," + hd.getTongtien() + "," + hd.getTinhtrang()
    						+ s + "\n");
    		} else {
    			fout.write(hd.getMahd() + "," + hd.getManv() + "," + hd.getMakh() + "," + hd.getThoigianxuat() + "," + hd.getTongtien() + "," + hd.getTinhtrang()
    						+ s);
    		}
    		
    		i++;
    	}
    	
    	if (fout != null)
    		fout.close();
    }
    
    // đọc, ghi file phiếu nhập
    public static ArrayList<phieunhap> readPhieuNhapFile() {
    	try {
    		FileReader fin = new FileReader("phieunhap.txt");
        	ArrayList<phieunhap> listPN = new ArrayList<>();
        	
        	String str = "";
        	int c;
        	while ((c = fin.read()) != -1) {
        		str += (char)c;
        	}
        	
        	if (str != "") {
        		String[] strArr = str.split("\n");
        		for (String s : strArr) {
        			String[] strArr2 = s.split(",");
        			if (strArr2[strArr2.length - 1].equals("\r")) {
        				strArr2[strArr2.length - 1] = strArr2[strArr2.length - 1].substring(0, strArr2[strArr2.length - 1].length() - 1);
        			}
        			
        			danhsachCTPN ctpn = new danhsachCTPN();
        			for (int i = 5; i < strArr2.length; i+=3) {
        				int j = i + 1, k = j + 1;
        				if (strArr2[i] != null && strArr2[j] != null && strArr2[k] != null) {
        					ctpn.getListCTPN().add(new chitietPN(strArr2[i], Integer.parseInt(strArr2[j]), Integer.parseInt(strArr2[k])));
        				}
        			}	
        			listPN.add(new phieunhap(strArr2[0], strArr2[1], strArr2[2], strArr2[3], ctpn.TongTien(), ctpn));
        		}
        		
        		if (fin != null)
        			fin.close();
        		return listPN;
        	} else {
        		if (fin != null)
        			fin.close();
        		return null;
        	}
    	} catch(Exception e) {
    		return null;
    	}
    }
    
    public static void writePhieuNhapFile(ArrayList<phieunhap> listPN) throws IOException {
    	FileWriter fout = new FileWriter("phieunhap.txt");
    	int i = 0 ;
    	for (phieunhap pn : listPN) {
    		String s = "";
    		for (chitietPN ctpn : pn.getDSCTPN().getListCTPN()) {
    			s = s + "," + ctpn.getMaDT() + "," + ctpn.getSoluong() + "," + ctpn.getDongia();
    		}
    		
    		if (i != (listPN.size() - 1)) {
    			fout.write(pn.getMaphieu() + "," + pn.getManv() + "," + pn.getMaNCC() + "," + pn.getThoigiannhap() + "," + pn.getTotal() + s + "\n");    			
    		} else {
    			fout.write(pn.getMaphieu() + "," + pn.getManv() + "," + pn.getMaNCC() + "," + pn.getThoigiannhap() + "," + pn.getTotal() + s);  
    		}
    		
    		i++;
    	}
    	
    	if (fout != null)
    		fout.close();
    }
}