package com.example.quanlynhahang;

public class HoaDonModel {
    String banSo;
    String doAn;
    Double gia;

    public HoaDonModel(String banSo, String doAn, Double gia) {
        this.banSo = banSo;
        this.doAn = doAn;
        this.gia = gia;
    }

    public HoaDonModel() {
    }

    public String getBanSo() {
        return banSo;
    }

    public void setBanSo(String banSo) {
        this.banSo = banSo;
    }

    public String getDoAn() {
        return doAn;
    }

    public void setDoAn(String doAn) {
        this.doAn = doAn;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
}
