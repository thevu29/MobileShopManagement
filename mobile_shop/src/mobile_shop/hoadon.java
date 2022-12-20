package mobile_shop;

public class hoadon {
	private String mahd;
    private String manv;
    private String makh;
    private String thoigianxuat;
    private int tongtien;
    private String tinhtrang;
    private danhsachCTHD dsCTHD;
    
    public hoadon() {
        mahd = null;
        manv = null;
        makh = null;
        thoigianxuat = null;
        tongtien=0;
        tinhtrang = null;
        dsCTHD = null;
    }
    
    public hoadon(String mahd, String manv, String makh, String thoigianxuat, int tongtien, danhsachCTHD dsCTHD, String tinhtrang) {
    	this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.thoigianxuat = thoigianxuat;
        this.tongtien = tongtien;
        this.dsCTHD = dsCTHD;
        this.tinhtrang = tinhtrang;
    }
    
	public void xuatHoaDon() {
        System.out.printf("|%-20s|%-20s|%-20s|%-10s|%-15s|%-25s| \n", getMahd(), getManv(), getMakh(), getThoigianxuat(), getTongtien(), getTinhtrang());
    }
    
	public String getMahd() {
		return mahd;
	}

	public void setMahd(String mahd) {
		this.mahd = mahd;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getThoigianxuat() {
		return thoigianxuat;
	}

	public void setThoigianxuat(String thoigianxuat) {
		this.thoigianxuat = thoigianxuat;
	}

	public int getTongtien() {
		return tongtien;
	}

	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}

	public String getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(String tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public danhsachCTHD getDSCTHD() {
		return dsCTHD;
	}

	public void setDSCTHD(danhsachCTHD dsCTHD) {
		this.dsCTHD = dsCTHD;
	}
}
