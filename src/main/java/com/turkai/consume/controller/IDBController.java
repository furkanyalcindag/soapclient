package com.turkai.consume.controller;

import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DTO.IDB.IDBSahisDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;
import com.turkai.consume.services.serviceInterface.IIDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IDBController {
    @Autowired
    JNDDBConfiguration jnddbConfiguration;

    @Autowired
    IIDBService iidbService;


    @GetMapping("idb-sahis-sorgu")
    public ResponseEntity<IDBSahisDTO> getIdbSahisDurum(@RequestParam long tc) {
        IDBSahisDTO idbSahisDTO = new IDBSahisDTO();

        try {
            idbSahisDTO = iidbService.isExistIDBSahis(tc, jnddbConfiguration);

            if (idbSahisDTO != null) {

                return new ResponseEntity<IDBSahisDTO>(idbSahisDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<IDBSahisDTO>(idbSahisDTO, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } catch (Exception e) {

            return new ResponseEntity<IDBSahisDTO>(idbSahisDTO, HttpStatus.UNPROCESSABLE_ENTITY);
        }


    }


}
