package com.turkai.consume.soapModels.ResponseModel.TBSResponse;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.DTO.TBS.AracDurumuDTO;

public class AracDurumlariResponse {

    private String hataDetay;
    private String hataKodu;
    private AracDurumuDTO aracDurumu;


    @JsonGetter("hataDetay")
    public String getHataDetay() {
        return hataDetay;
    }

    public void setHataDetay(String hataDetay) {
        this.hataDetay = hataDetay;
    }

    @JsonGetter("hataKod")
    public String getHataKodu() {
        return hataKodu;
    }

    public void setHataKodu(String hataKodu) {
        this.hataKodu = hataKodu;
    }

    public AracDurumuDTO getAracDurumu() {
        return aracDurumu;
    }

    public void setAracDurumu(AracDurumuDTO aracDurumu) {
        this.aracDurumu = aracDurumu;
    }
}
