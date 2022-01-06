package com.turkai.consume.controller;

import com.turkai.consume.DTO.TBS.AracDurumuDTO;
import com.turkai.consume.DTO.TBS.GercekSahisDTO;
import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IGeneralService;
import com.turkai.consume.services.serviceInterface.ITBSService;
import com.turkai.consume.soapModels.RequestModel.TBS.TBSAracPlakaRequest;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetkiResponse;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracDurumlariResponse;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracSahibiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TBSServisController {

    @Autowired
    ITBSService tbsService;

    @Autowired
    WSConfiguration wsConfiguration;

    @Autowired
    IGeneralService generalService;


    @PostMapping("/plakadan-arac-sahibi-getir")
    public ResponseEntity<AracSahibiResponse> plakadanAracSahibiGetir(@RequestBody TBSAracPlakaRequest aracPlakaRequest) {

        AracSahibiResponse aracSahibiResponse = new AracSahibiResponse();
        try {


            aracSahibiResponse = tbsService.plakadanAracSahibiGetirService(wsConfiguration, aracPlakaRequest);


            return new ResponseEntity<AracSahibiResponse>(aracSahibiResponse, HttpStatus.OK);

        } catch (Exception e) {


            return new ResponseEntity<AracSahibiResponse>(aracSahibiResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }


    }


    @PostMapping("/plakadan-arac-sahibi-getir-mock")
    public ResponseEntity<AracSahibiResponse> plakadanAracSahibiGetirFake(@RequestBody TBSAracPlakaRequest aracPlakaRequest) {

        AracSahibiResponse aracSahibiResponse = new AracSahibiResponse();
        try {

            if (!generalService.plateRegexControl(aracPlakaRequest.getPlaka())) {
                return new ResponseEntity<AracSahibiResponse>(aracSahibiResponse, HttpStatus.UNPROCESSABLE_ENTITY);

            } else {


                List<String> plates = new ArrayList<>();
                plates.add("34DC2450");
                plates.add("06BU2536");
                plates.add("06AL6398");
                plates.add("06GPK20");


                List<String> plates2 = new ArrayList<>();
                plates2.add("82ABC820");
                plates2.add("82ABC830");

                if (plates.contains(aracPlakaRequest.getPlaka())) {

                    aracSahibiResponse = tbsService.plakadanAracSahibiGetirService(wsConfiguration, aracPlakaRequest);
                } else if (plates2.contains(aracPlakaRequest.getPlaka())) {

                    GercekSahisDTO gercekSahisDTO = new GercekSahisDTO();
                    gercekSahisDTO.setAd("Abuzer");
                    gercekSahisDTO.setSoyad("Kadayıf");
                    gercekSahisDTO.setDogumTarihi("19721212");
                    gercekSahisDTO.setDogumYeri("Babil");
                    gercekSahisDTO.setMernisNo("33153956498");
                    aracSahibiResponse.setGercekSahis(gercekSahisDTO);
                    aracSahibiResponse.setHatadetay("Başarılı");
                    aracSahibiResponse.setHataKodu("0");
                    aracSahibiResponse.setHataKodu2("1");

                } else {
                    aracSahibiResponse.setHatadetay("Başarılı");
                    aracSahibiResponse.setHataKodu("0");
                    aracSahibiResponse.setHataKodu2("0");
                }


                return new ResponseEntity<AracSahibiResponse>(aracSahibiResponse, HttpStatus.OK);
            }


        } catch (Exception e) {


            return new ResponseEntity<AracSahibiResponse>(aracSahibiResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }


    }


    @PostMapping("/plakadan-arac-durumu-getir-mock")
    public ResponseEntity<AracDurumlariResponse> plakadanAracDurumuGetirFake(@RequestBody TBSAracPlakaRequest aracPlakaRequest) {

        AracDurumlariResponse aracDurumlariResponse = new AracDurumlariResponse();
        try {

            if (!generalService.plateRegexControl(aracPlakaRequest.getPlaka())) {
                return new ResponseEntity<AracDurumlariResponse>(aracDurumlariResponse, HttpStatus.BAD_REQUEST);

            } else {

                List<String> plates = new ArrayList<>();
                plates.add("34DC2450");
                plates.add("06BU2536");
                plates.add("06AL6398");
                plates.add("06GPK20");

                List<String> plates2 = new ArrayList<>();
                plates2.add("82ABC820");
                plates2.add("82ABC830");


                if (plates.contains(aracPlakaRequest.getPlaka())) {


                    aracDurumlariResponse = tbsService.plakadanAracDurumuGetirService(wsConfiguration, aracPlakaRequest);

                } else if (aracPlakaRequest.getPlaka().equals("82ABC820")) {
                    aracDurumlariResponse.setHataDetay("Başarılı");
                    aracDurumlariResponse.setHataKodu("0");
                    AracDurumuDTO aracDurumuDTO = new AracDurumuDTO();
                    aracDurumuDTO.setCalintiDurumu("Aranan Araç.");
                    aracDurumlariResponse.setAracDurumu(aracDurumuDTO);


                } else if (aracPlakaRequest.getPlaka().equals("82ABC830")) {

                    aracDurumlariResponse.setHataDetay("Başarılı");
                    aracDurumlariResponse.setHataKodu("0");
                    AracDurumuDTO aracDurumuDTO = new AracDurumuDTO();
                    aracDurumuDTO.setPlakaDurumu("Kayıp Plaka");
                    aracDurumlariResponse.setAracDurumu(aracDurumuDTO);


                } else {
                    aracDurumlariResponse.setHataDetay("Başarılı");
                    aracDurumlariResponse.setHataKodu("0");
                    AracDurumuDTO aracDurumuDTO = new AracDurumuDTO();
                    aracDurumuDTO.setPlakaDurumu(null);
                    aracDurumuDTO.setCalintiDurumu(null);
                    aracDurumlariResponse.setAracDurumu(aracDurumuDTO);
                }
                return new ResponseEntity<AracDurumlariResponse>(aracDurumlariResponse, HttpStatus.OK);
            }

        } catch (Exception e) {


            return new ResponseEntity<AracDurumlariResponse>(aracDurumlariResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }


    }

    @PostMapping("/plakadan-arac-durumu-getir")
    public ResponseEntity<AracDurumlariResponse> plakadanAracDurumuGetir(@RequestBody TBSAracPlakaRequest aracPlakaRequest) {

        AracDurumlariResponse aracDurumlariResponse = new AracDurumlariResponse();
        try {


            aracDurumlariResponse = tbsService.plakadanAracDurumuGetirService(wsConfiguration, aracPlakaRequest);


            return new ResponseEntity<AracDurumlariResponse>(aracDurumlariResponse, HttpStatus.OK);

        } catch (Exception e) {


            return new ResponseEntity<AracDurumlariResponse>(aracDurumlariResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }


    }


}
