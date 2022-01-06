package com.turkai.consume.controller;

import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiDogrulaRequest;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.TCKisiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IMERNISService;
import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiRequest;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.KisiFotografResponse;

import java.util.ArrayList;

@RestController
public class MernisServisController {


    @Autowired
    IMERNISService mernisServis;


    @Autowired
    WSConfiguration wsConfiguration;


    @PostMapping("/kisi-fotograf-getir")
    public ResponseEntity<KisiFotografResponse> kisiFotografGetir(@RequestBody KisiRequest kisiRequest) {


        KisiFotografResponse kisiFotografResponse = new KisiFotografResponse();
        try {

            kisiFotografResponse = mernisServis.kisiFotografGetir(wsConfiguration, kisiRequest);


            return new ResponseEntity<KisiFotografResponse>(kisiFotografResponse, HttpStatus.OK);

        } catch (Exception e) {


            return new ResponseEntity<KisiFotografResponse>(kisiFotografResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    @PostMapping("/tc-kisi-getir")
    public ResponseEntity<TCKisiResponse> TCKisiGetir(@RequestBody KisiRequest kisiRequest) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("23087676470");
        arrayList.add("16150342518");
        arrayList.add("18175991764");
        arrayList.add("40336239156");


        TCKisiResponse tcKisiResponse = new TCKisiResponse();
        try {

                tcKisiResponse = mernisServis.TCKisiGetir(wsConfiguration, kisiRequest);
                if (tcKisiResponse != null) {


                    return new ResponseEntity<TCKisiResponse>(tcKisiResponse, HttpStatus.OK);
                } else {
                    return new ResponseEntity<TCKisiResponse>(tcKisiResponse, HttpStatus.UNPROCESSABLE_ENTITY);

                }



        } catch (Exception e) {


            return new ResponseEntity<TCKisiResponse>(tcKisiResponse, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


    @PostMapping("/tc-kisi-dogrula")
    public ResponseEntity<Boolean> TCKisiGetir(@RequestBody KisiDogrulaRequest kisiRequest) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("23087676470");
        arrayList.add("16150342518");
        arrayList.add("18175991764");
        arrayList.add("40336239156");


        boolean isValid = false;
        try {

                isValid = mernisServis.TCKisiDogrula(wsConfiguration, kisiRequest);

                return new ResponseEntity<Boolean>(isValid, HttpStatus.OK);





        } catch (Exception e) {
            isValid = false;

            return new ResponseEntity<Boolean>(isValid, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }


}
