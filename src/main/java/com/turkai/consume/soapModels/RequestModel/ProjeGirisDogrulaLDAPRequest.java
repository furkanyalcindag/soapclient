package com.turkai.consume.soapModels.RequestModel;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ProjeGirisDogrulaLDAPRequest {

    private String pPBIK;
    private String pParola;
    private String pIP;


    @JsonGetter("pbik")
    public String getpPBIK() {
        return pPBIK;
    }

    public void setpPBIK(String pPBIK) {
        this.pPBIK = pPBIK;
    }

    @JsonGetter("password")
    public String getpParola() {
        return pParola;
    }

    public void setpParola(String pParola) {
        this.pParola = pParola;
    }


    @JsonGetter("ip")
    public String getpIP() {
        return pIP;
    }

    public void setpIP(String pIP) {
        this.pIP = pIP;
    }
}
