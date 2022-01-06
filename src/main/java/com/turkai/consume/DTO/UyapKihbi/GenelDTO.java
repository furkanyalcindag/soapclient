package com.turkai.consume.DTO.UyapKihbi;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DTO.Mernis.MernisTCKisiDTO;

import java.util.List;


public class GenelDTO {

    private List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogList;
    private List<OtonomKIHBISahisSucLog> kihbiSahisSucLogs;
    private MernisTCKisiDTO mernisDTO;


    @JsonGetter("uyap")
    public List<OtonomBLGTPLAKTUyapKayitLog> getUyapKayitLogList() {
        return uyapKayitLogList;
    }

    public void setUyapKayitLogList(List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogList) {
        this.uyapKayitLogList = uyapKayitLogList;
    }

    @JsonGetter("kihbi")
    public List<OtonomKIHBISahisSucLog> getKihbiSahisSucLogs() {
        return kihbiSahisSucLogs;
    }

    public void setKihbiSahisSucLogs(List<OtonomKIHBISahisSucLog> kihbiSahisSucLogs) {
        this.kihbiSahisSucLogs = kihbiSahisSucLogs;
    }

    @JsonGetter("mernis")
    public MernisTCKisiDTO getMernisDTO() {
        return mernisDTO;
    }

    public void setMernisDTO(MernisTCKisiDTO mernisDTO) {
        this.mernisDTO = mernisDTO;
    }
}
