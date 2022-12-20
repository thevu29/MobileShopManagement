package mobile_shop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

public class danhsachHD implements danhsach {
	private ArrayList<hoadon> listHD;
	private int cnt;
	
	public danhsachHD(danhsachDT listDT) throws IOException {
		listHD = lib.readHoaDonFile();
		if (listHD == null) {
			listHD = new ArrayList<>();
			cnt = 0;
			danhsachCTHD cthd1 = new danhsachCTHD();
			dienthoai d1 = listDT.getListDT()[0];
			dienthoai d2 = listDT.getListDT()[1];
			dienthoai d3 = listDT.getListDT()[2];
			d1.mua(5); d2.mua(4); d3.mua(3);
			cthd1.getListCTHD().add(new chitietHD(d1.getmaDienThoai(), 5, d1.getgiathanh()));
			cthd1.getListCTHD().add(new chitietHD(d2.getmaDienThoai(), 4, d2.getgiathanh()));
			cthd1.getListCTHD().add(new chitietHD(d3.getmaDienThoai(), 3, d3.getgiathanh()));
			listHD.add(new hoadon(getID(), "thevu", "daoducthang", "20/10/2022", cthd1.TongTien(), cthd1, "Da xu ly"));
			
			danhsachCTHD cthd2 = new danhsachCTHD();
			d1 = listDT.getListDT()[3];
			d2 = listDT.getListDT()[4];
			d3 = listDT.getListDT()[5];
			d1.mua(5); d2.mua(4); d3.mua(3);
			cthd2.getListCTHD().add(new chitietHD(d1.getmaDienThoai(), 5, d1.getgiathanh()));
			cthd2.getListCTHD().add(new chitietHD(d2.getmaDienThoai(), 4, d2.getgiathanh()));
			cthd2.getListCTHD().add(new chitietHD(d3.getmaDienThoai(), 3, d3.getgiathanh()));
			listHD.add(new hoadon(getID(), "thevu", "nguyenvu", "20/11/2022", cthd2.TongTien(), cthd2, "Da xu ly"));
			
			danhsachCTHD cthd3 = new danhsachCTHD();
			d1 = listDT.getListDT()[6];
			d2 = listDT.getListDT()[7];
			d3 = listDT.getListDT()[8];
			d1.mua(5); d2.mua(4); d3.mua(3);
			cthd3.getListCTHD().add(new chitietHD(d1.getmaDienThoai(), 5, d1.getgiathanh()));
			cthd3.getListCTHD().add(new chitietHD(d2.getmaDienThoai(), 4, d2.getgiathanh()));
			cthd3.getListCTHD().add(new chitietHD(d3.getmaDienThoai(), 3, d3.getgiathanh()));
			listHD.add(new hoadon(getID(), "", "thevu2", "30/11/2022", cthd3.TongTien(), cthd3, "Chua xu ly"));
			lib.writeHoaDonFile(listHD);
		} else {
			cnt = listHD.size();
		}
	}
	
	public String getID() {
		cnt++;
        Integer a = cnt;
        String str = a.toString();
        while(str.length() != 3)
            str = "0" + str;
        str = "HD" + str;
        return str;
    }
	
	public ArrayList<hoadon> getListHD() {
		return listHD;
	}

	public void setListHD(ArrayList<hoadon> listHD) {
		this.listHD = listHD;
	}

	public void xuatTieuDe() {
       System.out.printf("+%-20s-%-20s-%-20s-%-10s-%-15s-%-25s+ \n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
    		   				lib.repeatStr("-", 10), lib.repeatStr("-", 15), lib.repeatStr("-", 25));
       
       System.out.printf("|%-20s|%-20s|%-20s|%-10s|%-15s|%-25s| \n", "Ma hoa don" , "Ma nhan vien xu ly", "Ma khach hang", "Thoi gian", "Tong tien", 
    		   				"Tinh trang");
       
       System.out.printf("|%-20s|%-20s|%-20s|%-10s|%-15s|%-25s| \n", lib.repeatStr("-", 20) , lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
    		   				lib.repeatStr("-", 10), lib.repeatStr("-", 15), lib.repeatStr("-", 25));
    }
	
	private void xuatCTHoaDon(hoadon hd) {
       xuatTieuDe();
       hd.xuatHoaDon();
       
       System.out.printf("|%-20s-%-20s-%-20s-%-10s-%-15s-%-25s| \n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20),
    		   				lib.repeatStr("-", 10), lib.repeatStr("-", 15), lib.repeatStr("-", 25));
       
       hd.getDSCTHD().xuatDSCTHD();
       
       System.out.printf("+%-20s-%-20s-%-20s-%-10s-%-15s-%-25s+ \n", lib.repeatStr("-", 20), lib.repeatStr("-", 20), lib.repeatStr("-", 20), 
    		   				lib.repeatStr("-", 10), lib.repeatStr("-", 15), lib.repeatStr("-", 25));
   }
	
	public void xuatDS() {}
	
	public void xemDSHoaDon(connguoi nguoi) {
		int cnt = 0;
		xuatTieuDe();
		for (hoadon hd : listHD) {
			if (hd.getMakh().equals(nguoi.getId()) || nguoi instanceof nvBanHang) {
				hd.xuatHoaDon();
				cnt++;				
			}
		}
		if (cnt == 0)
			lib.printMessage("Khong co hoa don nao!");
	}
	
	public void xemCTHoaDon(connguoi nguoi) {
		String id = lib.takeStringInput("Nhap ma hoa don can xem chi tiet: ");
		hoadon hd = timHoaDonByID(id, nguoi);
		if (hd == null)
			lib.printMessage("Khong co hoa don nao co ma hoa don " + id);
		else
			xuatCTHoaDon(hd);
	}
	
	public void muaSanPham(danhsachDT listDT, connguoi nguoi, shop shop) {
		lib.printMessage("Mua dien thoai");
		String maHD = getID();
		String manv = "";
		String makh = nguoi.getId();
		String thoigianxuat = lib.getDateNow();
		
		danhsachCTHD cthd = new danhsachCTHD();
		cthd.themCTHD(listDT);
		
		listHD.add(new hoadon(maHD, manv, makh, thoigianxuat, cthd.TongTien(), cthd, "Chua xu ly"));
	}
	
	public hoadon timHoaDonByID(String id, connguoi nguoi) {
		if (nguoi instanceof nvBanHang)
			return listHD.stream().filter(hd -> hd.getMahd().equals(id)).findAny().orElse(null);
		else
			return listHD.stream().filter(hd -> hd.getMahd().equals(id) && hd.getMakh().equals(nguoi.getId())).findAny().orElse(null);
	}
	
	public void timHoaDonByKey(connguoi nguoi) {
		String id = lib.takeStringInput("Nhap tu khoa chua ma hoa don muon tim: ");
		ArrayList<hoadon> hdFilter = new ArrayList<>();
		for (hoadon hd : listHD) {
			if (hd.getMakh().equals(nguoi.getId()) || nguoi instanceof nvBanHang) {
				if (hd.getMahd().toLowerCase(Locale.ROOT).contains(id.toLowerCase(Locale.ROOT)))
					hdFilter.add(hd);			
			}
		}
		
		if (hdFilter.isEmpty())
			lib.printMessage("Khong co hoa don co ma chua tu khoa \"" + id + "\"");
		else {
			lib.printMessage("Ket qua tim kiem theo ma hoa don chua tu khoa \"" + id + "\"");
			xuatTieuDe();
			for (hoadon hd : hdFilter) {
				hd.xuatHoaDon();
			}
		}
	}
	
	public void XuLyHoaDon(connguoi nguoi) {
		String id = lib.takeStringInput("Nhap ma hoa don can xu ly: ");
		hoadon hd = timHoaDonByID(id, nguoi);
		if (hd == null) {
			System.out.println("Khong tim thay hoa don co ma " + id);
		} else {
			if (hd.getTinhtrang().equals("Chua xu ly")) {
				hd.setManv(nguoi.getId());
				hd.setTinhtrang("Da xu ly");
			} else {
				System.out.println("Hoa don nay da duoc xu ly!");
			}
		}
	}
	
	public void locHoaDon() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngaybatdau = lib.takeDateInput("Nhap ngay bat dau: ");
		String ngayketthuc = lib.takeDateInput("Nhap ngay ket thuc: ");
		Date min = sdf.parse(ngaybatdau);
		Date max = sdf.parse(ngayketthuc);
		
		ArrayList<hoadon> hdFilter = new ArrayList<>();
		for (hoadon hd : listHD) {
			Date thoigianxuat = sdf.parse(hd.getThoigianxuat());
			if (thoigianxuat.getTime() >= min.getTime() && thoigianxuat.getTime() <= max.getTime())
				hdFilter.add(hd);
		}
		
		if (hdFilter.isEmpty()) {
			lib.printMessage("Khong co hoa don nao trong khoang thoi gian tu " + ngaybatdau + " den " + ngayketthuc);
		} else {
			lib.printMessage("Cac hoa don trong khoang thoi gian tu " + ngaybatdau + " den " + ngayketthuc);
			xuatTieuDe();
			for (hoadon hd : hdFilter) 
				hd.xuatHoaDon();
		}
	}
	
	public void menuHoaDon(danhsachDT listDT, connguoi nguoi, shop shop) throws ParseException {
		boolean exit = false;
		if (nguoi instanceof nvBanHang) {		//nhan vien ban hang xem hoa don
			do {
				xemDSHoaDon(nguoi);
				System.out.println("1. Xem chi tiet hoa don");
				System.out.println("2. Tim hoa don");
				System.out.println("3. Xu ly hoa don");
				System.out.println("4. Loc hoa don");
				System.out.println("0. Thoat");
				
				switch(lib.takeInputChoice(0, 4)) {
					case 1:
						xemCTHoaDon(nguoi);
						break;
					case 2:
						timHoaDonByKey(nguoi);
						break;
					case 3:
						XuLyHoaDon(nguoi);
						break;
					case 4:
						locHoaDon();
						break;
					case 0:
						exit = true;
						break;
				}
				
				if (!exit)
					lib.clearScreen();
			} while(!exit);
		} 
		else {		//khach hang xem hoa don
			do {
				xemDSHoaDon(nguoi);
				System.out.println("1. Xem chi tiet hoa don");
				System.out.println("2. Tim hoa don");
				System.out.println("0. Thoat");
				
				switch(lib.takeInputChoice(0, 2)) {
					case 1:
						xemCTHoaDon(nguoi);
						break;
					case 2:
						timHoaDonByKey(nguoi);
						break;
					case 0:
						exit = true;
						break;
				}
				
			if (!exit)
				lib.clearScreen();
			} while (!exit);			
		}
	}
}