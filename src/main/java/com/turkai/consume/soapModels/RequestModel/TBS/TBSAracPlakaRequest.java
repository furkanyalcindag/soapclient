package com.turkai.consume.soapModels.RequestModel.TBS;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;

public class TBSAracPlakaRequest {


    private KullaniciBilgi kullaniciBilgi;
    private String plaka;


    @JsonGetter("auth")
    public KullaniciBilgi getKullaniciBilgi() {
        return kullaniciBilgi;
    }

    public void setKullaniciBilgi(KullaniciBilgi kullaniciBilgi) {
        this.kullaniciBilgi = kullaniciBilgi;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }


}
