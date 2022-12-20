package mobile_shop;

public class dienthoai {
	protected String maDienThoai;
	protected String tenDienThoai;
	protected String maNSX;
	protected int giathanh;
	protected String maNhaCungCap;
	protected int soLuong;
	
	public dienthoai() {
		maDienThoai = null;
		tenDienThoai = null;
		maNSX = null;
		giathanh = 0;
		maNhaCungCap = null;
		soLuong = 0;
	}
	
	public dienthoai(String maDienThoai, String tenDienThoai, String maNSX, String maNhaCungCap, int giathanh , int soLuong) {
		setmaDienThoai(maDienThoai);
		settenDienThoai(tenDienThoai);
		setmaNSX(maNSX);
		setmaNhaCungCap(maNhaCungCap);
		setgiathanh(giathanh);
		setSoLuong(soLuong);
	}

	public boolean mua(int soLuongMua) {
		if(soLuongMua > soLuong)
			return false;
		soLuong -= soLuongMua;
		return true;
	}
	
	public void nhap(String maDienThoai, String maNSX, String maNhaCungCap) {
		this.maDienThoai = maDienThoai;
		this.tenDienThoai = lib.takeStringInput("Nhap ten dien thoai: ");
		this.maNSX = maNSX;
		this.giathanh = lib.takeIntegerInput("Nhap gia thanh dien thoai: ");
		this.maNhaCungCap = maNhaCungCap;
		this.soLuong = lib.takeIntegerInput("Nhap so luong co trong kho: ");
	}
	
	public void xuatThongTin() {
		System.out.format("|%-15s|%-18s|%-15s|%-15s|%-10s|%-15s|%n", maDienThoai, tenDienThoai, maNSX, maNhaCungCap, giathanh, soLuong);
    }
	
	public String getmaDienThoai() {
		return maDienThoai;
	}
	
	public void setmaDienThoai(String maDienThoai) {
		this.maDienThoai = maDienThoai;
	}
	
	public String gettenDienThoai() {
		return tenDienThoai;
	}
	
	public void settenDienThoai(String tenDienThoai) {
		this.tenDienThoai = tenDienThoai;
	}
	public String getmaNSX() {
		return maNSX;
	}
	
	public void setmaNSX(String maNSX) {
		this.maNSX = maNSX;
	}

	public int getgiathanh() {
		return giathanh;
	}
	
	public void setgiathanh(int giathanh) {
		this.giathanh = giathanh;
	}
	
	public String getmaNhaCungCap() {
		return maNhaCungCap;
	}
	
	public void setmaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
}
