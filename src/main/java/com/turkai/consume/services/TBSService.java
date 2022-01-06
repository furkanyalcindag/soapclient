package com.turkai.consume.services;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IGeneralService;
import com.turkai.consume.services.serviceInterface.ITBSService;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;
import com.turkai.consume.soapModels.RequestModel.TBS.TBSAracPlakaRequest;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracDurumlariResponse;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracSahibiResponse;
import com.turkai.consume.soapServices.TBSWSClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBSService implements ITBSService {

    @Autowired
    IGeneralService generalService;

    private static final Logger log =
            LoggerFactory.getLogger(TBSService.class);


    TBSWSClientService a = new TBSWSClientService();


    public AracSahibiResponse plakadanAracSahibiGetirService(WSConfiguration ws, TBSAracPlakaRequest tbsAracPlakaRequest) {


        AracSahibiResponse aracSahibiResponse = new AracSahibiResponse();

        KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();

        try {
            kullaniciBilgi = generalService.setKullaniciBilgi(ws, tbsAracPlakaRequest.getKullaniciBilgi().getTLPKLNADI(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPIP()
                    , tbsAracPlakaRequest.getKullaniciBilgi().getTLPTC(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPNDNACK(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPNDNKOD());

            aracSahibiResponse = a.plakadanAracSahibiGetir(kullaniciBilgi, tbsAracPlakaRequest.getPlaka());

            if (aracSahibiResponse != null) {

                return aracSahibiResponse;
            } else {
                throw new Exception("null");
            }


        } catch (Exception e) {

            aracSahibiResponse.setGercekSahis(null);
            aracSahibiResponse.setTuzelSahis(null);
            aracSahibiResponse.setHataKodu("1000");
            aracSahibiResponse.setHatadetay("Service is unavaliable");
            log.error(e.getMessage());

            return aracSahibiResponse;

        }


    }


    public AracDurumlariResponse plakadanAracDurumuGetirService(WSConfiguration ws, TBSAracPlakaRequest tbsAracPlakaRequest) {


        AracDurumlariResponse aracDurumlariResponse = new AracDurumlariResponse();

        KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();

        try {
            kullaniciBilgi = generalService.setKullaniciBilgi(ws, tbsAracPlakaRequest.getKullaniciBilgi().getTLPKLNADI(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPIP()
                    , tbsAracPlakaRequest.getKullaniciBilgi().getTLPTC(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPNDNACK(), tbsAracPlakaRequest.getKullaniciBilgi().getTLPNDNKOD());

            aracDurumlariResponse = a.plakadanAracDurumuGetir(kullaniciBilgi, tbsAracPlakaRequest.getPlaka());

            if (aracDurumlariResponse != null) {
                if (aracDurumlariResponse.getAracDurumu()!=null&&aracDurumlariResponse.getAracDurumu().getPlakaDurumu()!=null&&aracDurumlariResponse.getAracDurumu().getPlakaDurumu().equals("null")) {
                    aracDurumlariResponse.getAracDurumu().setPlakaDurumu(null);
                }
                if (aracDurumlariResponse.getAracDurumu()!=null&&aracDurumlariResponse.getAracDurumu().getCalintiDurumu()!=null&&aracDurumlariResponse.getAracDurumu().getCalintiDurumu().equals("null")) {
                    aracDurumlariResponse.getAracDurumu().setCalintiDurumu(null);
                }
                return aracDurumlariResponse;
            } else {
                throw new Exception("null");
            }


        } catch (Exception e) {

            aracDurumlariResponse.setAracDurumu(null);

            aracDurumlariResponse.setHataKodu("1000");
            aracDurumlariResponse.setHataDetay("Service is unavaliable");
            log.error(e.getMessage());

            return aracDurumlariResponse;

        }


    }


}
