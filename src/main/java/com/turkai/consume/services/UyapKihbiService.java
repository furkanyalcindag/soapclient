package com.turkai.consume.services;

import com.turkai.consume.DAO.IOtonomSHSDAO;
import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DTO.UyapKihbi.GenelDTO;
import com.turkai.consume.DTO.UyapKihbi.KihbiUyapDurumDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;
import com.turkai.consume.model.UyapKihbiDurum;
import com.turkai.consume.repository.CityRepository;
import com.turkai.consume.repository.UyapKihbiDurumRepository;
import com.turkai.consume.services.serviceInterface.IMERNISService;
import com.turkai.consume.services.serviceInterface.IUyapKihbiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UyapKihbiService implements IUyapKihbiService {

    @Autowired
    IOtonomSHSDAO otonomSHSDAO;

    @Autowired
    IMERNISService mernisService;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UyapKihbiDurumRepository uyapKihbiDurumRepository;


    private static final Logger log =
            LoggerFactory.getLogger(UyapKihbiService.class);

    @Override
    public List<OtonomKIHBISahisSucLog> getKihbiList(long tc, JNDDBConfiguration jnddbConfiguration) {

        List<OtonomKIHBISahisSucLog> kihbiSahisSucLogs = new ArrayList<>();

        try {
            kihbiSahisSucLogs = otonomSHSDAO.getKihbilogs(tc, jnddbConfiguration).get();

            return kihbiSahisSucLogs;


        } catch (Exception e) {

            log.error(e.getMessage());
            return null;

        }

    }

    @Override
    public List<OtonomBLGTPLAKTUyapKayitLog> getUyapList(long tc, JNDDBConfiguration jnddbConfiguration) {
        List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogs = new ArrayList<>();

        try {
            uyapKayitLogs = otonomSHSDAO.getUyaplogs(tc, jnddbConfiguration).get();

            return uyapKayitLogs;


        } catch (Exception e) {

            log.error(e.getMessage());
            return null;

        }
    }


    @Override
    public GenelDTO getAll(List<String> servisList, long tc, JNDDBConfiguration jnddbConfiguration) {
        try {
            GenelDTO genelDTO = new GenelDTO();
            for (String item : servisList) {
                if (item.equals("uyap")) {
                    List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogs = new ArrayList<>();
                    uyapKayitLogs = getUyapList(tc, jnddbConfiguration);

                    genelDTO.setUyapKayitLogList(uyapKayitLogs);

                } else if (item.equals("kihbi")) {

                    genelDTO.setKihbiSahisSucLogs(getKihbiList(tc, jnddbConfiguration));

                } else {
                    genelDTO = null;
                }
            }

            return genelDTO;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }


    @Override
    public KihbiUyapDurumDTO getKihbiUyapDurum(long tc, JNDDBConfiguration jnddbConfiguration) {
        try {
            KihbiUyapDurumDTO kihbiUyapDurumDTO = new KihbiUyapDurumDTO();
            GenelDTO genelDTO = new GenelDTO();

            List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogs = new ArrayList<>();
            uyapKayitLogs = getUyapList(tc, jnddbConfiguration);

            genelDTO.setUyapKayitLogList(uyapKayitLogs);
            genelDTO.setKihbiSahisSucLogs(getKihbiList(tc, jnddbConfiguration));


            kihbiUyapDurumDTO.setKihbiDurum(getKihbiDurum(genelDTO.getKihbiSahisSucLogs()));

            kihbiUyapDurumDTO.setUyapDurum(getUyapDurum(genelDTO.getUyapKayitLogList()));

            return kihbiUyapDurumDTO;


        } catch (
                Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }


    public String getKihbiDurum(List<OtonomKIHBISahisSucLog> otonomKIHBISahisSucLogList) {

        List<UyapKihbiDurum> uyapKihbiDurums = uyapKihbiDurumRepository.findByDurumTipiOrderBySiraAsc("kihbi");

        String durum = "YOK";

        for (UyapKihbiDurum uyapKihbiDurum : uyapKihbiDurums) {

            for (OtonomKIHBISahisSucLog otonomKIHBISahisSucLog : otonomKIHBISahisSucLogList) {

                if (otonomKIHBISahisSucLog.getSonDrmAck().equals(uyapKihbiDurum.getGelenDurumAdi())) {
                    durum = uyapKihbiDurum.getKarsilikDurumAdi();
                    break;
                }
            }
            if (!durum.equals("YOK")) {

                break;
            }


        }

        return durum;
    }



    public String getUyapDurum(List<OtonomBLGTPLAKTUyapKayitLog> uyapList) {

        List<UyapKihbiDurum> uyapKihbiDurums = uyapKihbiDurumRepository.findByDurumTipiOrderBySiraAsc("uyap");

        String durum = "YOK";

        for (UyapKihbiDurum uyapKihbiDurum : uyapKihbiDurums) {

            for (OtonomBLGTPLAKTUyapKayitLog uyapLog : uyapList) {

                if (uyapLog.getSonDurumAck().equals(uyapKihbiDurum.getGelenDurumAdi())) {
                    durum = uyapKihbiDurum.getKarsilikDurumAdi();
                    break;
                }


            }
            if (!durum.equals("YOK")) {

                break;
            }


        }

        return durum;
    }


}
