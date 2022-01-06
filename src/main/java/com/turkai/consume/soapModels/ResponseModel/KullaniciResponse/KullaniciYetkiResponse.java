package com.turkai.consume.soapModels.ResponseModel.KullaniciResponse;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

public class KullaniciYetkiResponse {
    private String mHATADETAY;
    private int mHATAKOD;
    private List<KullaniciYetki> mKullaniciYetkiListe;


    @JsonGetter("hataDetay")
    public String getmHATADETAY() {
        return mHATADETAY;
    }

    public void setmHATADETAY(String mHATADETAY) {
        this.mHATADETAY = mHATADETAY;
    }

    @JsonGetter("hataKod")
    public int getmHATAKOD() {
        return mHATAKOD;
    }

    public void setmHATAKOD(int mHATAKOD) {
        this.mHATAKOD = mHATAKOD;
    }

    public List<KullaniciYetki> getmKullaniciYetkiListe() {
        return mKullaniciYetkiListe;
    }

    public void setmKullaniciYetkiListe(List<KullaniciYetki> mKullaniciYetkiListe) {
        this.mKullaniciYetkiListe = mKullaniciYetkiListe;
    }
}
