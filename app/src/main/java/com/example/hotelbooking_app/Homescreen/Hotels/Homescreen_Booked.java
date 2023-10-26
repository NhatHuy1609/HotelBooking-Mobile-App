package com.example.hotelbooking_app.Homescreen.Hotels;


public class Homescreen_Booked {
    private String Ten;
    private String DiaChi;
    private double DanhGia;
    private int SoLuongDanhGia;
    private String Gia;
    private int Hinh;
    private String NgayCheckIn;
    private String NgayCheckOut;

    public Homescreen_Booked(String ten, String diaChi, double danhGia, int soLuongDanhGia, String gia, int hinh, String ngayCheckIn, String ngayCheckOut) {
        Ten = ten;
        DiaChi = diaChi;
        DanhGia = danhGia;
        SoLuongDanhGia = soLuongDanhGia;
        Gia = gia;
        Hinh = hinh;
        NgayCheckIn = ngayCheckIn;
        NgayCheckOut = ngayCheckOut;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public double getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(double danhGia) {
        DanhGia = danhGia;
    }

    public int getSoLuongDanhGia() {
        return SoLuongDanhGia;
    }

    public void setSoLuongDanhGia(int soLuongDanhGia) {
        SoLuongDanhGia = soLuongDanhGia;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getNgayCheckIn() {
        return NgayCheckIn;
    }

    public void setNgayCheckIn(String ngayCheckIn) {
        NgayCheckIn = ngayCheckIn;
    }

    public String getNgayCheckOut() {
        return NgayCheckOut;
    }

    public void setNgayCheckOut(String ngayCheckOut) {
        NgayCheckOut = ngayCheckOut;
    }
}
