package com.turkai.consume.soapModels.RequestModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Auth {

    @JsonIgnore
    String pYetkiliKullanici;
    @JsonIgnore
    String pYetkiliParola;
    @JsonIgnore
    String pProjeGrupAdi;





    public Auth() {


    }


    public String getpYetkiliKullanici() {
        return pYetkiliKullanici;
    }

    public void setpYetkiliKullanici(String pYetkiliKullanici) {
        this.pYetkiliKullanici = pYetkiliKullanici;
    }

    public String getpYetkiliParola() {
        return pYetkiliParola;
    }

    public void setpYetkiliParola(String pYetkiliParola) {
        this.pYetkiliParola = pYetkiliParola;
    }

    public String getpProjeGrupAdi() {
        return pProjeGrupAdi;
    }

    public void setpProjeGrupAdi(String pProjeGrupAdi) {
        this.pProjeGrupAdi = pProjeGrupAdi;
    }


}
