package mobile_shop;

public class nhaCC {
	private String maNCC;
	private String tenNCC;
	private String sdtNCC;
	private String diachiNCC;
	private String emailNCC;
	
	public nhaCC() {
		maNCC = null;
		tenNCC = null;
		sdtNCC = null;
		diachiNCC = null;
		emailNCC = null;
	}
	
	public nhaCC(String maNCC, String tenNCC, String sdtNCC, String diachiNCC, String emailNCC) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdtNCC = sdtNCC;
		this.diachiNCC = diachiNCC;
		this.emailNCC = emailNCC;
	}
	
	public void nhapNCC(String maNCC) {
		this.maNCC = maNCC;
		this.tenNCC = lib.takeStringInput("Nhap ten nha cung cap: ");
		this.sdtNCC = lib.takePhoneInput("Nhap so dien thoai nha cung cap: ");
		this.diachiNCC = lib.takeStringInput("Nhap dịa chỉ nha cung cap: ");
		this.emailNCC = lib.takeStringInput("Nhap email nha cung cap: ");
	}
	
	public void xuatNCC() {
        System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|\n", maNCC, tenNCC, diachiNCC, sdtNCC, emailNCC);
    }
	
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getSdtNCC() {
		return sdtNCC;
	}
	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}
	public String getDiachiNCC() {
		return diachiNCC;
	}
	public void setDiachiNCC(String diachiNCC) {
		this.diachiNCC = diachiNCC;
	}
	public String getEmailNCC() {
		return emailNCC;
	}
	public void setEmailNCC(String emailNCC) {
		this.emailNCC = emailNCC;
	}
}