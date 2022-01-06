package com.turkai.consume.DBModel;

public class OtonomSHSRSMTML {

    private long SHSID;
    private byte[] Resim;


    public long getSHSID() {
        return SHSID;
    }

    public void setSHSID(long SHSID) {
        this.SHSID = SHSID;
    }

    public byte[] getResim() {
        return Resim;
    }

    public void setResim(byte[] resim) {
        Resim = resim;
    }
}
