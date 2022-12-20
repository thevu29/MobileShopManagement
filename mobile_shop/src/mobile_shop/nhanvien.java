package mobile_shop;

public abstract class nhanvien extends connguoi {	
	protected int mucluong;
	protected String chucvu;
	
	public nhanvien() {
		super();
		mucluong = 0;
	}

	public nhanvien(String chucvu, String id, String hoten, String diachi, String sdt, String ngaysinh, String gioitinh, String cmnd, String password, int mucluong) {
		super(id, hoten, diachi, sdt, ngaysinh, gioitinh, cmnd, password);
		this.mucluong = mucluong;
		this.chucvu = chucvu;
	}
	
	public abstract void xuatThongTin();
	
	public int getMucluong() {
		return mucluong;
	}

	public void setMucluong(int mucluong) {
		this.mucluong = mucluong;
	}
	
	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
}