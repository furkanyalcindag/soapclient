package com.turkai.consume.controller;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.GeneralService;
import com.turkai.consume.services.KullaniciService;
import com.turkai.consume.services.serviceInterface.IGeneralService;
import com.turkai.consume.services.serviceInterface.IKullaniciService;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetkiResponse;
import com.turkai.consume.soapModels.RequestModel.*;
import com.turkai.consume.soapServices.KullaniciWSClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class KullaniciServisController {

    KullaniciWSClientService a = new KullaniciWSClientService();

    @Autowired
    WSConfiguration wsConfiguration;
   
    @Autowired
    IGeneralService generalService;

    @Autowired
    IKullaniciService kullaniciService;


    @RequestMapping(method = RequestMethod.POST, value = "/project-user-list")
    public KullaniciYetkiResponse getProjectList(
            @RequestBody ProjectUserList auth) {
        auth.setpProjeGrupAdi(wsConfiguration.getPROJEADI());
        auth.setpYetkiliKullanici(wsConfiguration.getYTKKLNAADI());
        auth.setpYetkiliParola(wsConfiguration.getYTKPRL());

        KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();

        kullaniciYetkiResponse = a.projectUserList(auth.getpYetkiliKullanici(), auth.getpYetkiliParola(), auth.getpProjeGrupAdi());


        System.out.println(wsConfiguration.getPROJEADI());

        return kullaniciYetkiResponse;

    }

/*

    @PostMapping("/project-user-permission-info")
    public KullaniciYetkiResponse projectPermissionInfo(
            @RequestBody ProjectPermissionInfo projectPermissionInfo) {
        KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();

        projectPermissionInfo.setpProjeGrupAdi(wsConfiguration.getPROJEADI());
        projectPermissionInfo.setpYetkiliKullanici(wsConfiguration.getYTKKLNAADI());
        projectPermissionInfo.setpYetkiliParola(wsConfiguration.getYTKPRL());

        kullaniciYetkiResponse = a.projectPermissionInfo(projectPermissionInfo.getpYetkiliKullanici(), projectPermissionInfo.getpYetkiliParola(), projectPermissionInfo.getpProjeGrupAdi(),projectPermissionInfo.getpPBIK());



        System.out.println(wsConfiguration.getPROJEADI());
        return kullaniciYetkiResponse;

    }*/

    @PostMapping("/proje-kullanici-ekle")
    public ResponseEntity<KullaniciYetkiResponse> projeKullaniciEkle(
            @RequestBody ProjectAddUser projectAddUser) {

        KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
        try {


            projectAddUser.setpProjeGrupAdi(wsConfiguration.getPROJEADI());
            projectAddUser.setpYetkiliKullanici(wsConfiguration.getYTKKLNAADI());
            projectAddUser.setpYetkiliParola(wsConfiguration.getYTKPRL());


            kullaniciYetkiResponse = kullaniciService.projeKullaniciEkle(wsConfiguration, projectAddUser.getpKullaniciAdi());


            if (kullaniciYetkiResponse.getmHATAKOD() != 0 && kullaniciYetkiResponse.getmHATAKOD() != 40 ) {


                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

            } else {
                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.OK);
            }

        } catch (Exception e) {

            return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    @PostMapping("/proje-kullanici-cikar")
    public ResponseEntity<KullaniciYetkiResponse> projeKullaniciCikar(
            @RequestBody ProjectRemoveUser projectRemoveUser) {
        KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
        try {
            projectRemoveUser.setpProjeGrupAdi(wsConfiguration.getPROJEADI());
            projectRemoveUser.setpYetkiliKullanici(wsConfiguration.getYTKKLNAADI());
            projectRemoveUser.setpYetkiliParola(wsConfiguration.getYTKPRL());


            kullaniciYetkiResponse = kullaniciService.projeKullaniciCikar(wsConfiguration, projectRemoveUser.getpKullaniciAdi());


            if (kullaniciYetkiResponse.getmHATAKOD() != 0 || kullaniciYetkiResponse.getmKullaniciYetkiListe().size() == 0 || kullaniciYetkiResponse.getmKullaniciYetkiListe() == null) {


                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

            } else {
                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.OK);
            }


        } catch (Exception e) {
            return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @PostMapping("/login")
    public ResponseEntity<KullaniciYetkiResponse> verifyUserLDAP(
            @RequestBody ProjeGirisDogrulaLDAPRequest projectEnterVerifyLDAP) {
        KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();

        try {

            kullaniciYetkiResponse = kullaniciService.kullaniciDogrulaLDAPService(wsConfiguration, projectEnterVerifyLDAP.getpPBIK(), projectEnterVerifyLDAP.getpParola(), projectEnterVerifyLDAP.getpIP());

            if (kullaniciYetkiResponse.getmHATAKOD() != 0 || kullaniciYetkiResponse.getmKullaniciYetkiListe().size() == 0 || kullaniciYetkiResponse.getmKullaniciYetkiListe() == null) {


                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.UNAUTHORIZED);

            } else {
                return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.OK);
            }


        } catch (Exception e) {

            return new ResponseEntity<KullaniciYetkiResponse>(kullaniciYetkiResponse, HttpStatus.OK);


        }

    }



}
