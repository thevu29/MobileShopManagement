package mobile_shop;

public class phieunhap {
	private String maphieu;
	private String manv;
	private String maNCC;
	private String thoigiannhap;
	private int total;
	private danhsachCTPN dsCTPN;
	
	public phieunhap() {
		maphieu = null;
		manv = null;
		maNCC = null;
		thoigiannhap = null;
		total = 0;
		dsCTPN = null;
	}
	
	public phieunhap(String maphieu, String manv, String maNCC, String thoigiannhap, int total, danhsachCTPN dsCTPN) {
		this.maphieu = maphieu;
		this.manv = manv;
		this.maNCC = maNCC;
		this.thoigiannhap = thoigiannhap;
		this.total = total;
		this.dsCTPN = dsCTPN;
	}
	
	public void xuatPhieuNhap() {
		System.out.printf("|%-16s|%-16s|%-16s|%-18s|%-16s|\n", maphieu, thoigiannhap, total, manv, maNCC);
	}
	
	public void xuatCTPN() {
		System.out.printf("+%-50s+\n", lib.repeatStr("-", 50));
        System.out.printf("|%-10s%-30s%-10s|\n", lib.repeatStr(" ", 10), "Chi tiet phieu nhap", lib.repeatStr(" ", 10));
        System.out.printf("|%-50s|\n", "Ma phieu nhap: " + getMaphieu());
        System.out.printf("|%-50s|\n", "Thoi gian nhap: " + getThoigiannhap());
        System.out.printf("|%-50s|\n", "Tong tien: " + getTotal());
        System.out.printf("|%-50s|\n", "Ma nhan vien nhap: " + getManv());
        System.out.printf("|%-50s|\n", "Ma nha cung cap: " + getMaNCC());
        dsCTPN.xuatDSCTPN();
        System.out.printf("+%-16s-%-16s-%-16s+\n", lib.repeatStr("-", 16), lib.repeatStr("-", 16), lib.repeatStr("-", 16));
	}
	
	public String getMaphieu() {
		return maphieu;
	}

	public void setMaphieu(String maphieu) {
		this.maphieu = maphieu;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getThoigiannhap() {
		return thoigiannhap;
	}

	public void setThoigiannhap(String thoigiannhap) {
		this.thoigiannhap = thoigiannhap;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public danhsachCTPN getDSCTPN() {
		return dsCTPN;
	}

	public void setDSCTPN(danhsachCTPN listCTPN) {
		this.dsCTPN = listCTPN;
	}
}