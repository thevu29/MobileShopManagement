package mobile_shop;

public class nvBanHang extends nhanvien {	
	public nvBanHang() {
		super();
	}
	
	public nvBanHang(String chucvu, String id, String hoten, String diachi, String sdt, String ngaysinh, String gioitinh, String cmnd, String password, int mucluong) {
		super(chucvu, id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password, mucluong);
	}
	
	public void xuatThongTin() {
		System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%-10s|%-16s|%n", id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password, mucluong,
							"Ban hang");
	}
}