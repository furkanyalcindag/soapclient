package com.turkai.consume.services.serviceInterface;


import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DTO.UyapKihbi.GenelDTO;
import com.turkai.consume.DTO.UyapKihbi.KihbiUyapDurumDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;


import java.util.List;

public interface IUyapKihbiService {

    List<OtonomKIHBISahisSucLog> getKihbiList(long tc, JNDDBConfiguration jnddbConfiguration);

    List<OtonomBLGTPLAKTUyapKayitLog> getUyapList(long tc, JNDDBConfiguration jnddbConfiguration);

    GenelDTO getAll(List<String> servisList, long tc, JNDDBConfiguration jnddbConfiguration);

    KihbiUyapDurumDTO getKihbiUyapDurum(long tc, JNDDBConfiguration jnddbConfiguration);


}
