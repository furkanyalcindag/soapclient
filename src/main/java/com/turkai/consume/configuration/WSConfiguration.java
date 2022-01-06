package com.turkai.consume.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wsstatic")
public class WSConfiguration {
    private String YTKKLNAADI;
    private String YTKPRL;
    private String PROJEADI;
    private String TLPNDNKOD;
    private String PROJEGRUPADI;

    public String getPROJEGRUPADI() {
        return PROJEGRUPADI;
    }

    public void setPROJEGRUPADI(String PROJEGRUPADI) {
        this.PROJEGRUPADI = PROJEGRUPADI;
    }

    public String getYTKKLNAADI() {
        return YTKKLNAADI;
    }

    public void setYTKKLNAADI(String YTKKLNAADI) {
        this.YTKKLNAADI = YTKKLNAADI;
    }

    public String getYTKPRL() {
        return YTKPRL;
    }

    public void setYTKPRL(String YTKPRL) {
        this.YTKPRL = YTKPRL;
    }

    public String getPROJEADI() {
        return PROJEADI;
    }

    public void setPROJEADI(String PROJEADI) {
        this.PROJEADI = PROJEADI;
    }

    public String getTLPNDNKOD() {
        return TLPNDNKOD;
    }

    public void setTLPNDNKOD(String TLPNDNKOD) {
        this.TLPNDNKOD = TLPNDNKOD;
    }





}
