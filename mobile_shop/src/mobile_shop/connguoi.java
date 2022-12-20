package mobile_shop;

public abstract class connguoi {
    protected String hoten;
    protected String id;
    protected String diachi;
    protected String sdt;
    protected String ngaysinh;
    protected String gioitinh;
    protected String cmnd;
    protected String password;
    
    public connguoi() {
    	hoten = null;
    	id = null;
    	diachi = null;
    	sdt = null;
    	gioitinh = null;
    	cmnd = null;
    	password = null;
    }
    
    public connguoi(String id, String hoten, String diachi, String sdt, String ngaysinh, String gioitinh, String cmnd, String password) {
        this.hoten = hoten;
        this.id = id;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.cmnd = cmnd;
        this.password = password;
    }

    public abstract void xuatThongTin();

    public String getHoTen() {
        return hoten;
    }

    public void setHoTen(String hoTen) {
        this.hoten = hoTen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diachi;
    }

    public void setDiaChi(String diaChi) {
        this.diachi = diaChi;
    }

    public String getSDT() {
        return sdt;
    }

    public void setSDT(String SDT) {
        this.sdt = SDT;
    }

    public String getNgaySinh() {
        return ngaysinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaysinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioitinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioitinh = gioiTinh;
    }

    public String getCMND() {
        return cmnd;
    }

    public void setCMND(String CMND) {
        this.cmnd = CMND;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}