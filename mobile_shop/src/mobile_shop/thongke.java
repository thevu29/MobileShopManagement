package mobile_shop;

public class thongke {
	private static int tongdonhang = 0;
	private static int tongsanpham = 0;
	private static int doanhthu = 0;
	
	public thongke(danhsachHD listHD) {
		for (hoadon hd : listHD.getListHD()) {
			tongdonhang++;
			for (chitietHD cthd : hd.getDSCTHD().getListCTHD()) {
				tongsanpham += cthd.getSoluong();
			}
			doanhthu += hd.getTongtien();
		}
	}
	
	public void xuatThongKe(danhsachHD listHD) {
		lib.printMessage("Thong ke");
		System.out.printf("+%-20s-%-25s-%-20s+ \n", lib.repeatStr("-", 20), lib.repeatStr("-", 25), lib.repeatStr("-", 20));
	    System.out.printf("|%-20s|%-25s|%-20s| \n", "Tong don hang" , "Tong san pham ban duoc","Doanh thu");
	    System.out.printf("|%-20s|%-25s|%-20s| \n", lib.repeatStr("-", 20), lib.repeatStr("-", 25), lib.repeatStr("-", 20));
	    System.out.printf("|%-20s|%-25s|%-20s| \n", tongdonhang, tongsanpham, doanhthu);
	}
}