package mobile_shop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class danhsachPN implements danhsach {
	private ArrayList<phieunhap> listPN;
	private int cnt;
	
	public danhsachPN(danhsachDT listDT) throws IOException {
		listPN = lib.readPhieuNhapFile();
		if (listPN == null) {
			cnt = 0;
			listPN = new ArrayList<>();
			dienthoai d1 = listDT.getListDT()[0];
			dienthoai d2 = listDT.getListDT()[1];
			dienthoai d3 = listDT.getListDT()[2];
			
			danhsachCTPN ctpn1 = new danhsachCTPN();
			ctpn1.getListCTPN().add(new chitietPN(d1.getmaDienThoai(), 50, d1.getgiathanh()));
			listPN.add(new phieunhap(getID(), "quoccuong", "NCC001", "20/10/2022", ctpn1.TongTien(), ctpn1));
			
			danhsachCTPN ctpn2 = new danhsachCTPN();
			ctpn2.getListCTPN().add(new chitietPN(d2.getmaDienThoai(), 100, d2.getgiathanh()));
			ctpn2.getListCTPN().add(new chitietPN(d3.getmaDienThoai(), 20, d3.getgiathanh()));
			listPN.add(new phieunhap(getID(), "quoccuong", "NCC002", "20/11/2022", ctpn2.TongTien(), ctpn2));
			
			lib.writePhieuNhapFile(listPN);
		} else {
			cnt = listPN.size();
		}
	}
	
	public ArrayList<phieunhap> getListPN() {
		return listPN;
	}

	public void setListPN(ArrayList<phieunhap> listPN) {
		this.listPN = listPN;
	}

	public String getID() {
		cnt++;
		int a = cnt;
        String str = Integer.toString(a);
		while (str.length() != 3) {
			str = "0" + str;
		}
		str = "PN" + str;
		return str;
	}
	
	public void xuatTieuDe() {
        System.out.printf("+%-16s-%-16s-%-16s-%-18s-%-16s+\n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 18),
                			lib.repeatStr("-", 16));
        
        System.out.printf("|%-16s|%-16s|%-16s|%-18s|%-16s|\n", "Ma phieu nhap", "Thoi gian nhap", "Tong tien", "Ma nhân vien nhap", "Ma nha cung cap");
        
        System.out.printf("|%-16s|%-16s|%-16s|%-18s|%-16s|\n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 18),
        					lib.repeatStr("-", 16));
    }
	
	public void xuatDS() {
        if (!listPN.isEmpty()) {
            for (phieunhap pn : listPN) {
               pn.xuatPhieuNhap();
            }
        } else {
            lib.printMessage("Danh sach phieu nhap rong!");
        }
    }
	
	public void xemCTPhieuNhap() {
		if (listPN.isEmpty()) {
			lib.printMessage("Danh sach phieu nhap rong!");
		} else {
			String id = lib.takeStringInput("Nhap ma phieu nhap can xem chi tiet: ");
			phieunhap pn = timkiemById(id);
			if (pn == null)
				System.out.println("Khong co phieu nhap nao co ma " + id);
			else 
				pn.xuatCTPN();
		}
	}
	
	public String nhapNhaCungCap(danhsachNCC listNCC) {
		while (true) {
			listNCC.xuatDS();
			String maNCC = lib.takeStringInput("Nhap ma nha cung cap: ");
			if (listNCC.timkiemNCC(maNCC) == -1) {
				lib.printMessage("Khong co nha cung cap nao co ma " + maNCC);
			} else {
				return maNCC;
			}
		}
	}
	
	public void themPhieuNhap(danhsachNCC listNCC, danhsachDT listDT, connguoi nguoi) {
		String maPN = getID();
		String maNV = nguoi.getId();
		String maNCC = nhapNhaCungCap(listNCC);
		String thoigiannhap = lib.getDateNow();
		danhsachCTPN listCTPN = new danhsachCTPN();
		
		boolean stop = false;
		do {
			listDT.xuatDS();
			String idDT = lib.takeStringInput("Nhap ma dien thoai: ");
			int indexDT = listDT.timkiemMaDienThoai(idDT);
			if (indexDT != -1) {
				int soluong = lib.takeIntegerInput("Nhap so luong: ");
				chitietPN ctpn = listCTPN.timkiemDaCo(idDT);
				if (ctpn == null)
					listCTPN.getListCTPN().add(new chitietPN(idDT, soluong, listDT.getListDT()[indexDT].getgiathanh()));
				else
					ctpn.setSoluong(ctpn.getSoluong() + soluong);
			} else {
				lib.printMessage("Khong co dien thoai nao co ma " + idDT);
			}
			
			System.out.println("1. Tiep tuc nhap");
			System.out.println("2. Xac nhan nhap hang");		
			if(lib.takeInputChoice(1, 2) == 2) {
				lib.printMessage("Nhap hang thanh cong!");
				stop = true;
			}
		} while(!stop);
		
		listPN.add(new phieunhap(maPN, maNV, maNCC, thoigiannhap, listCTPN.TongTien(), listCTPN));
		
		// cong them so luong ton kho cua dien thoai sau khi nhap
		for (chitietPN ctpn : listCTPN.getListCTPN()) {
			dienthoai dt = listDT.getListDT()[listDT.timkiemMaDienThoai(ctpn.getMaDT())];
			dt.setSoLuong(dt.getSoLuong() + ctpn.getSoluong());
		}
	} 
	
	public phieunhap timkiemById(String id) {
		return listPN.stream().filter(pn -> pn.getMaphieu().equals(id)).findAny().orElse(null);
	}
	
	public void timkiemByKey() {
		String id = lib.takeStringInput("Nhap tu khoa chua ma phieu nhap muon tim: ");
		ArrayList<phieunhap> pnFilter = new ArrayList<>();
		for (phieunhap pn : listPN) {
			if (pn.getMaphieu().toLowerCase(Locale.ROOT).contains(id.toLowerCase(Locale.ROOT))) {
				pnFilter.add(pn);
			}
		}
		
		if (pnFilter.isEmpty()) {
			lib.printMessage("Khong co phieu nhap co ma chua tu khoa \"" + id + "\"");			
		} else {
			lib.printMessage("Ket qua tim kiem theo ma phieu nhap chua tu khoa \"" + id + "\"");
			xuatTieuDe();
			for (phieunhap pn : pnFilter) 
				pn.xuatPhieuNhap();
		}
	}
	
	public void locPhieuNhap() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String ngaybatdau = lib.takeDateInput("Nhap ngay bat dau: ");
		String ngayketthuc = lib.takeDateInput("Nhap ngay ket thuc: ");
		Date min = sdf.parse(ngaybatdau);
		Date max = sdf.parse(ngayketthuc);
		
		ArrayList<phieunhap> pnFilter = new ArrayList<>();
		for (phieunhap pn : listPN) {
			Date thoigiannhap = sdf.parse(pn.getThoigiannhap());
			if (thoigiannhap.getTime() >= min.getTime() && thoigiannhap.getTime() <= max.getTime())
				pnFilter.add(pn);
		}
		
		if (pnFilter.isEmpty()) {
			lib.printMessage("Khong co phieu nhap nao trong khoang thoi gian tu " + ngaybatdau + " den " + ngayketthuc);
		} else {
			lib.printMessage("Cac phieu nhap trong khoang thoi gian tu " + ngaybatdau + " den " + ngayketthuc);
			xuatTieuDe();
			for (phieunhap pn : pnFilter) 
				pn.xuatPhieuNhap();
		}
	}
	
	public void menu(danhsachDT listDt, danhsachNCC listNCC, connguoi nguoi) throws ParseException {
		boolean exit = false;
		do {
			xuatTieuDe();
			xuatDS();
			System.out.println("1. Xem chi tiet phieu nhap");
			System.out.println("2. Them phieu nhap");
			System.out.println("3. Tim kiem phieu nhap");
			System.out.println("4. Loc phieu nhap");
			System.out.println("0. Thoat");
			
			switch(lib.takeInputChoice(0, 4)) {
				case 1:
					xemCTPhieuNhap();
					break;
				case 2:
					themPhieuNhap(listNCC, listDt, nguoi);
					break;
				case 3:
					timkiemByKey();
					break;
				case 4:
					locPhieuNhap();
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