package mobile_shop;

public class khachhang extends connguoi {
	public khachhang() {
		super();
	}

	public khachhang(String id, String hoten, String diachi, String sdt, String ngaysinh, String gioitinh, String cmnd, String password) {
		super(id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password);
	}

	public void xuatThongTin() {
		System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%n", id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password);
	}
}