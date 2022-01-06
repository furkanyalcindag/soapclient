package com.turkai.consume.soapModels.RequestModel;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class KullaniciBilgi {

    @JsonIgnore
    private String PROJEADI;
    private String TLPIP;
    private String TLPKLNADI;
    private String TLPNDNACK;
    private String TLPNDNKOD;
    private String TLPTC;
    @JsonIgnore
    private String YTKKLNADI;
    @JsonIgnore
    private String YTKPRL;


    @JsonGetter("username")
    public String getTLPKLNADI() {
        return TLPKLNADI;
    }

    public void setTLPKLNADI(String TLPKLNADI) {
        this.TLPKLNADI = TLPKLNADI;
    }


    public String getYTKKLNADI() {
        return YTKKLNADI;
    }

    @JsonIgnore
    public void setYTKKLNADI(String YTKKLNADI) {
        this.YTKKLNADI = YTKKLNADI;
    }

    public String getYTKPRL() {
        return YTKPRL;
    }

    @JsonIgnore
    public void setYTKPRL(String YTKPRL) {
        this.YTKPRL = YTKPRL;
    }

    public String getPROJEADI() {
        return PROJEADI;
    }

    @JsonIgnore
    public void setPROJEADI(String PROJEADI) {
        this.PROJEADI = PROJEADI;
    }

    @JsonGetter("tckn")
    public String getTLPTC() {
        return TLPTC;
    }

    public void setTLPTC(String TLPTC) {
        this.TLPTC = TLPTC;
    }

    @JsonGetter("ip")
    public String getTLPIP() {
        return TLPIP;
    }

    public void setTLPIP(String TLPIP) {
        this.TLPIP = TLPIP;
    }

    @JsonGetter("reasonCode")
    public String getTLPNDNKOD() {
        return TLPNDNKOD;
    }

    public void setTLPNDNKOD(String TLPNDNKOD) {
        this.TLPNDNKOD = TLPNDNKOD;
    }

    @JsonGetter("reasonText")
    public String getTLPNDNACK() {
        return TLPNDNACK;
    }

    public void setTLPNDNACK(String TLPNDNACK) {
        this.TLPNDNACK = TLPNDNACK;
    }
}
