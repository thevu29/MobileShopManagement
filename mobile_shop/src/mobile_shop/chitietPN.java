package mobile_shop;

public class chitietPN {
	private String maDT;
    private int soluong;
    private int dongia;
    
    public chitietPN() {
    	maDT = null;
    	soluong = 0;
    	dongia = 0;
    }

	public chitietPN(String maDT, int soluong, int dongia) {
		this.maDT = maDT;
		this.soluong = soluong;
		this.dongia = dongia;
	}
	
	public void xuatChiTiet() {
        System.out.printf("|%-16s|%-16s|%-16s|\n", maDT, soluong, dongia);
    }
	
	public String getMaDT() {
		return maDT;
	}

	public void setMaDT(String maDT) {
		this.maDT = maDT;
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