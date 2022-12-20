package mobile_shop;

public class chitietHD {
	public String madienthoai;
    public int soluong;
    public int dongia;
    
    public chitietHD() {
    	madienthoai = null;
    	soluong = 0;
    	dongia = 0;
    }

	public chitietHD(String madienthoai, int soluong, int dongia) {
		this.madienthoai = madienthoai;
		this.soluong = soluong;
		this.dongia = dongia;
	}
	
	public String getMadienthoai() {
		return madienthoai;
	}

	public void setMadienthoai(String madienthoai) {
		this.madienthoai = madienthoai;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getDongia() {
		return dongia;
	}

	public void setDongia(int dongia) {
		this.dongia = dongia;
	}
}