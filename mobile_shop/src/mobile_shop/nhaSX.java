package mobile_shop;

public class nhaSX {
	private String maNSX;
	private String tenNSX;
	private String sdtNSX;
	private String diachiNSX;
	private String emailNSX;
	
	public nhaSX() {
		maNSX = null;
		tenNSX = null;
		sdtNSX = null;
		diachiNSX = null;
		emailNSX = null;
	}
	
	public nhaSX(String maNSX, String tenNSX, String sdtNSX, String diachiNSX, String emailNSX) {
		setmaNSX(maNSX);
		settenNSX(tenNSX);
		setSDTNSX(sdtNSX);
		setdiachiNSX(diachiNSX);
		setemailNSX(emailNSX);
	}

	public void nhapNSX(String maNSX) {
		this.maNSX = maNSX;
		this.tenNSX = lib.takeStringInput("Nhap ten nha san xuat: ");
		this.sdtNSX = lib.takePhoneInput("Nhap so dien thoai nha san xuat: ");
		this.diachiNSX = lib.takeStringInput("Nhap dia chi nha san xuat: ");
		this.emailNSX = lib.takeStringInput("Nhap email nha san xuat: ");
	}
	
	public void xuatNSX() {
		System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|%n", maNSX, tenNSX, sdtNSX, diachiNSX, emailNSX);
	}
	
	public String getmaNSX() {
		return maNSX;
	}
	
	public void setmaNSX(String maNSX) {
		this.maNSX = maNSX;
	}
	
	public String gettenNSX() {
		return tenNSX;
	}
	
	public void settenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}
	
	public String getSDTNSX() {
		return sdtNSX;
	}
	
	public void setSDTNSX(String sdtNSX) {
		this.sdtNSX = sdtNSX;
	}
	
	public String getdiachiNSX() {
		return diachiNSX;
	}
	
	public void setdiachiNSX(String diachiNSX) {
		this.diachiNSX = diachiNSX;
	}
	
	public String getemailNSX() {
		return emailNSX;
	}
	
	public void setemailNSX(String emailNSX) {
		this.emailNSX = emailNSX;
	}
}
