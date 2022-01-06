package com.turkai.consume.soapModels.RequestModel;

public class ProjectRemoveUser extends Auth {

    private String pKullaniciAdi;

    public ProjectRemoveUser() {
    }

    public String getpKullaniciAdi() {
        return pKullaniciAdi;
    }

    public void setpKullaniciAdi(String pKullaniciAdi) {
        this.pKullaniciAdi = pKullaniciAdi;
    }
}
