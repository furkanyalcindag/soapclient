package com.turkai.consume.model;

import javax.persistence.*;

@Table
@Entity
public class UyapKihbiDurum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String gelenDurumAdi;

    @Column
    private String karsilikDurumAdi;

    @Column
    private String durumTipi;

    @Column
    private int sira;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGelenDurumAdi() {
        return gelenDurumAdi;
    }

    public void setGelenDurumAdi(String gelenDurumAdi) {
        this.gelenDurumAdi = gelenDurumAdi;
    }

    public String getKarsilikDurumAdi() {
        return karsilikDurumAdi;
    }

    public void setKarsilikDurumAdi(String karsilikDurumAdi) {
        this.karsilikDurumAdi = karsilikDurumAdi;
    }

    public String getDurumTipi() {
        return durumTipi;
    }

    public void setDurumTipi(String durumTipi) {
        this.durumTipi = durumTipi;
    }

    public int getSira() {
        return sira;
    }

    public void setSira(int sira) {
        this.sira = sira;
    }
}
