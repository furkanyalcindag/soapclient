package com.turkai.consume.controller;

import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DTO.UyapKihbi.GenelDTO;
import com.turkai.consume.DTO.UyapKihbi.KihbiUyapDurumDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;
import com.turkai.consume.services.serviceInterface.IUyapKihbiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UyapKihbiController {

    @Autowired
    JNDDBConfiguration jnddbConfiguration;

    @Autowired
    IUyapKihbiService uyapKihbiService;


    @GetMapping("kihbi-suc-kayitlari")
    public ResponseEntity<List<OtonomKIHBISahisSucLog>> getKihbiLogs(@RequestParam long tc) {
        List<OtonomKIHBISahisSucLog> kihbiSahisSucLogs = new ArrayList<>();
        try {

            kihbiSahisSucLogs = uyapKihbiService.getKihbiList(tc, jnddbConfiguration);

            if (kihbiSahisSucLogs != null) {

                return new ResponseEntity<List<OtonomKIHBISahisSucLog>>(kihbiSahisSucLogs, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<OtonomKIHBISahisSucLog>>(kihbiSahisSucLogs, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {


            return new ResponseEntity<List<OtonomKIHBISahisSucLog>>(kihbiSahisSucLogs, HttpStatus.UNPROCESSABLE_ENTITY);


        }


    }

    @GetMapping("uyap-suc-kayitlari")
    public ResponseEntity<List<OtonomBLGTPLAKTUyapKayitLog>> getUyapLogs(@RequestParam long tc) {
        List<OtonomBLGTPLAKTUyapKayitLog> uyapSahisSucLogs = new ArrayList<>();
        try {

            uyapSahisSucLogs = uyapKihbiService.getUyapList(tc, jnddbConfiguration);

            if (uyapSahisSucLogs != null) {

                return new ResponseEntity<List<OtonomBLGTPLAKTUyapKayitLog>>(uyapSahisSucLogs, HttpStatus.OK);
            } else {
                return new ResponseEntity<List<OtonomBLGTPLAKTUyapKayitLog>>(uyapSahisSucLogs, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {

            return new ResponseEntity<List<OtonomBLGTPLAKTUyapKayitLog>>(uyapSahisSucLogs, HttpStatus.UNPROCESSABLE_ENTITY);

        }


    }

    @GetMapping("suc-kayitlari")
    public ResponseEntity<GenelDTO> getUyapLogs(@RequestParam long tc, @RequestParam String servisList) {

        GenelDTO genelDTO = new GenelDTO();
        try {

            String[] arr = servisList.split(",");
            genelDTO = uyapKihbiService.getAll(Arrays.asList(arr), tc, jnddbConfiguration);

            if (genelDTO != null) {

                return new ResponseEntity<GenelDTO>(genelDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<GenelDTO>(genelDTO, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {

            return new ResponseEntity<GenelDTO>((GenelDTO) null, HttpStatus.UNPROCESSABLE_ENTITY);

        }


    }


    @GetMapping("uyap-kihbi-durum")
    public ResponseEntity<KihbiUyapDurumDTO> getUyapKihbiDurum(@RequestParam long tc) {

        KihbiUyapDurumDTO kihbiUyapDurumDTO = new KihbiUyapDurumDTO();
        try {


            kihbiUyapDurumDTO = uyapKihbiService.getKihbiUyapDurum(tc, jnddbConfiguration);

            if (kihbiUyapDurumDTO != null) {

                return new ResponseEntity<KihbiUyapDurumDTO>(kihbiUyapDurumDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<KihbiUyapDurumDTO>(kihbiUyapDurumDTO, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {

            return new ResponseEntity<KihbiUyapDurumDTO>((KihbiUyapDurumDTO) null, HttpStatus.UNPROCESSABLE_ENTITY);

        }


    }


}
