package com.turkai.consume.soapModels.ResponseModel.TBSResponse;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.DTO.TBS.GercekSahisDTO;
import com.turkai.consume.DTO.TBS.TuzelSahisDTO;

public class AracSahibiResponse {

    private String hatadetay;
    private String hataKod;
    private String hataKodu;
    private GercekSahisDTO gercekSahis;
    private TuzelSahisDTO tuzelSahis;

    @JsonGetter("hataDetay")
    public String getHatadetay() {
        return hatadetay;
    }

    public void setHatadetay(String hatadetay) {
        this.hatadetay = hatadetay;
    }

    @JsonGetter("hataKod")
    public String getHataKod() {
        return hataKod;
    }

    public void setHataKodu(String hataKod) {
        this.hataKod = hataKod;
    }

    public GercekSahisDTO getGercekSahis() {
        return gercekSahis;
    }

    public void setGercekSahis(GercekSahisDTO gercekSahis) {
        this.gercekSahis = gercekSahis;
    }

    public TuzelSahisDTO getTuzelSahis() {
        return tuzelSahis;
    }

    public void setTuzelSahis(TuzelSahisDTO tuzelSahis) {
        this.tuzelSahis = tuzelSahis;
    }

    @JsonGetter("aracVarMi")
    public String getHataKodu2() {
        return hataKodu;
    }

    public void setHataKodu2(String hataKodu2) {
        this.hataKodu = hataKodu2;
    }
}
