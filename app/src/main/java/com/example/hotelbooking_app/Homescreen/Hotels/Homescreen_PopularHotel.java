package com.example.hotelbooking_app.Homescreen.Hotels;

public class Homescreen_PopularHotel {
    private String Ten;
    private String DiaChi;
    private double DanhGia;
    private int SoLuongDanhGia;
    private String Gia;
    //    private String MoTa;
    private int Hinh;

    public Homescreen_PopularHotel(String ten, String diaChi, double danhGia, int soLuongDanhGia, String gia /* ,String moTa */, int hinh) {
        Ten = ten;
        DiaChi = diaChi;
        DanhGia = danhGia;
        SoLuongDanhGia = soLuongDanhGia;
        Gia = gia;
//        MoTa = moTa;
        Hinh = hinh;
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

//    public String getMoTa() {
//        return MoTa;
//    }
//
//    public void setMoTa(String moTa) {
//        MoTa = moTa;
//    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
