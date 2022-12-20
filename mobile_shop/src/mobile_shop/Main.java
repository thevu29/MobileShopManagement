package mobile_shop;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, ParseException {
		danhsachDT listDT = new danhsachDT();
		danhsachNSX listNSX = new danhsachNSX();
		danhsachNCC listNCC = new danhsachNCC();
		danhsachHD listHD = new danhsachHD(listDT);
		danhsachPN listPN = new danhsachPN(listDT);
		shop shop = new shop();
		thongke tk = new thongke(listHD);

		boolean exit = false;
		System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Cua hang ban dien thoai", lib.repeatStr("-", 25));
		while (true) {
			System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("0. Thoat");
            switch(lib.takeInputChoice(0, 2)) {
            	// dang nhap
	            case 1:
	            	connguoi nguoi = shop.login();
	            	boolean logout = false;
	            	if (nguoi == null)
	            		lib.printMessage("Tai khoan khong ton tai!");
	            	// user la khach hang
	            	else if (nguoi instanceof khachhang) {
	            		while (true) {
	            			System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Khach hang", lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", lib.repeatStr(" ", 20), "Xin chao " + nguoi.getHoTen(), lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh sach hoa don");
                            System.out.println("2. Mua dien thoai");
                            System.out.println("0. Thoat");
                            
                            switch(lib.takeInputChoice(0, 2)) {
                            	case 1:
                            		listHD.menuHoaDon(listDT, nguoi, shop);
                            		lib.writeHoaDonFile(listHD.getListHD());
                            		lib.writeDTFile(listDT.getListDT());
                            		break;
                				case 2:
                					listHD.muaSanPham(listDT, nguoi, shop);
                					break;
                            	case 0:
                            		logout = true;
                            		break;                          		
                            }
                            
                            if (logout)
                            	break;
                            lib.clearScreen();
	            		}
	            	} 
	            	// user la nhan vien
	            	else if (nguoi instanceof nhanvien) {
	            		// quan ly
		            	if (nguoi instanceof quanly) {
		            		while (true) {
		            			System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Quan ly", lib.repeatStr("-", 25));
	                            System.out.printf("%-20s%-15s%-25s\n", lib.repeatStr(" ", 20), "Xin chao " + nguoi.getHoTen(), lib.repeatStr(" ", 25));
	                            System.out.println("1. Xem danh sach nhan vien");
	                            System.out.println("2. Xem danh sach nha san xuat");
	                            System.out.println("3. Xem danh sach nha cung cap");
	                            System.out.println("4. Xem thong ke");
	                            System.out.println("0. Thoat");
	                            
	                            switch(lib.takeInputChoice(0, 4)) {
		                            case 1: 
		                            	shop.menuNhanvien();
		                            	lib.writeConNguoiFile(shop.getListNguoi());
		                            	break;
		                            case 2:
		                            	listNSX.menu();
		                            	lib.writeNSXFile(listNSX.getListNSX());
		                            	break;
		                            case 3:
		                            	listNCC.menu();
		                            	lib.writeNCCFile(listNCC.getListNCC());
		                            	break;
		                            case 4:
		                            	tk.xuatThongKe(listHD);
		                            	break;
		                            case 0:
		                            	logout = true;
		                            	break;
	                            }
	                            
	                            if (logout)
	                            	break;
	                            lib.clearScreen();
		            		}
		            	}
		            	// nhan vien ban hang
		            	else if (nguoi instanceof nvBanHang) {
		            		while (true) {
		            			System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Nhan vien ban hang", lib.repeatStr("-", 25));
		            			System.out.printf("%-20s%-15s%-25s\n", lib.repeatStr(" ", 20), "Xin chao " + nguoi.getHoTen(), lib.repeatStr(" ", 25));
		            			System.out.println("1. Xem danh sach hoa don");
		            			System.out.println("2. Xem danh sach khach hang");
		            			System.out.println("0. Thoat");
		            			
		            			switch(lib.takeInputChoice(0, 2)) {
			            			case 1:
			            				listHD.menuHoaDon(listDT, nguoi, shop);
			            				lib.writeHoaDonFile(listHD.getListHD());
			            				break;
			            			case 2:
			            				shop.menuKhachHang();
			            				lib.writeConNguoiFile(shop.getListNguoi());
			            				break;
			            			case 0:
			            				logout= true;
			            				break;
		            			}
		            			
		            			if (logout)
		            				break;
		            			lib.clearScreen();        			
		            		}
		            	}
	            		// nhan vien thu kho
		            	else {
		            		while (true) {
		            			System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Nhan vien thu kho", lib.repeatStr("-", 25));
		            			System.out.printf("%-20s%-15s%-25s\n", lib.repeatStr(" ", 20), "Xin chao " + nguoi.getHoTen(), lib.repeatStr(" ", 25));
		            			System.out.println("1. Xem danh sach phieu nhap");
		            			System.out.println("2. Xem danh sach san pham");
		            			System.out.println("0. Thoat");
		            			
		            			switch(lib.takeInputChoice(0, 2)) {
		            				case 1:
		            					listPN.menu(listDT, listNCC, nguoi);
		            					lib.writePhieuNhapFile(listPN.getListPN());
		            					lib.writeDTFile(listDT.getListDT());
		            					break;
		            				case 2:
		            					listDT.menu(listNSX, listNCC);
		            					lib.writeDTFile(listDT.getListDT());
		            					break;
			            			case 0:
			            				logout = true;
			            				break;
		            			}
		            			
		            			if (logout)
		            				break;
		            			lib.clearScreen();
		            		}
		            	}
	            	}	            	
	      
	            	lib.clearScreen();
	            	break;
	            // dang ky tai khoan
	            case 2:
	            	System.out.printf("+%-30s%-5s%-25s+\n", lib.repeatStr("-", 30), "Dang ky", lib.repeatStr("-", 25));
	            	shop.taoNguoi("kh");
	            	lib.writeConNguoiFile(shop.getListNguoi());
	            	lib.printMessage("Dang ky thanh cong! Vui long dang nhap de tiep tuc");
	            	break;	       
	            case 0:
	            	exit = true;
	            	break;
            }
            
            if (exit)
            	break;
		}
		lib.printMessage("Chuong trinh da ket thuc");
	}
}