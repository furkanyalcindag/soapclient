package com.turkai.consume.soapModels.ResponseModel.MERNISResponse;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.turkai.consume.DTO.Mernis.MERNISKisiFotografDTO;
import com.turkai.consume.DTO.Mernis.MernisTCKisiDTO;

public class TCKisiResponse {

    private String hataDetay;
    private String hataKodu;
    private MernisTCKisiDTO mernisTCKisiDTO;

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

    @JsonGetter("mernisKisi")
    public MernisTCKisiDTO getMernisTCKisiDTO() {
        return mernisTCKisiDTO;
    }

    public void setMernisTCKisiDTO(MernisTCKisiDTO mernisTCKisiDTO) {
        this.mernisTCKisiDTO = mernisTCKisiDTO;
    }
}
