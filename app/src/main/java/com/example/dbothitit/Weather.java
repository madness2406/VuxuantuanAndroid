package com.example.dbothitit;

public class Weather {
    String ngay3h;
    String thoiTiet3h;
    String imgThoiTiet3h;
    String nhietDOMin;
    String nhietDoMax;


    public Weather(String ngay3h, String imgThoiTiet3h, String nhietDOMin, String nhietDoMax, String thoiTiet3h) {
        this.ngay3h = ngay3h;
        this.thoiTiet3h = thoiTiet3h;
        this.imgThoiTiet3h = imgThoiTiet3h;
        this.nhietDOMin = nhietDOMin;
        this.nhietDoMax = nhietDoMax;
    }

    public String getNgay3h() {
        return ngay3h;
    }

    public void setNgay3h(String ngay3h) {
        this.ngay3h = ngay3h;
    }

    public String getImgThoiTiet3h() {
        return imgThoiTiet3h;
    }

    public void setImgThoiTiet3h(String imgThoiTiet3h) {
        this.imgThoiTiet3h = imgThoiTiet3h;
    }

    public String getNhietDOMin() {
        return nhietDOMin;
    }

    public void setNhietDOMin(String nhietDOMin) {
        this.nhietDOMin = nhietDOMin;
    }

    public String getNhietDoMax() {
        return nhietDoMax;
    }

    public void setNhietDoMax(String nhietDoMax) {
        this.nhietDoMax = nhietDoMax;
    }

    public String getThoiTiet3h() {
        return thoiTiet3h;
    }

    public void setThoiTiet3h(String thoiTiet3h) {
        this.thoiTiet3h = thoiTiet3h;
    }
}

