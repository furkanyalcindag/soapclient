package com.turkai.consume.services;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IKullaniciService;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetkiResponse;
import com.turkai.consume.soapServices.KullaniciWSClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KullaniciService implements IKullaniciService {

    private static  final Logger log =
            LoggerFactory.getLogger(KullaniciService.class);


    KullaniciWSClientService a = new KullaniciWSClientService();


    public KullaniciYetkiResponse kullaniciDogrulaLDAPService(WSConfiguration ws, String pPBIK, String pParola, String pIP) {

        KullaniciYetkiResponse yetkiResponse = new KullaniciYetkiResponse();
        try {

            yetkiResponse = a.verifyUserLDAP(ws.getYTKKLNAADI(), ws.getYTKPRL(), ws.getPROJEADI(), pPBIK, pParola, pIP);
            return yetkiResponse;

        } catch (Exception e) {

            yetkiResponse.setmKullaniciYetkiListe(null);
            yetkiResponse.setmHATAKOD(1000);
            yetkiResponse.setmHATADETAY("Service is unavaliable");
            log.error(e.getMessage());

            return yetkiResponse;

        }





    }
    
    
    
    
    public KullaniciYetkiResponse projeKullaniciEkle(WSConfiguration ws, String pKullaniciAdi) {

        KullaniciYetkiResponse yetkiResponse = new KullaniciYetkiResponse();
        try {

            yetkiResponse = a.projectAddUser(ws.getYTKKLNAADI(), ws.getYTKPRL(), ws.getPROJEADI(), pKullaniciAdi);
            return yetkiResponse;

        } catch (Exception e) {

            yetkiResponse.setmKullaniciYetkiListe(null);
            yetkiResponse.setmHATAKOD(1000);
            yetkiResponse.setmHATADETAY("Service is unavaliable");
            log.error(e.getMessage());

            return yetkiResponse;

        }





    }




	@Override
	public KullaniciYetkiResponse projeKullaniciCikar(WSConfiguration ws, String pKullaniciAdi) {
		// TODO Auto-generated method stub
		 KullaniciYetkiResponse yetkiResponse = new KullaniciYetkiResponse();
	        try {

	            yetkiResponse = a.projectRemoveUser(ws.getYTKKLNAADI(), ws.getYTKPRL(), ws.getPROJEADI(), pKullaniciAdi);
	            return yetkiResponse;

	        } catch (Exception e) {

	            yetkiResponse.setmKullaniciYetkiListe(null);
	            yetkiResponse.setmHATAKOD(1000);
	            yetkiResponse.setmHATADETAY("Service is unavaliable");
	            log.error(e.getMessage());

	            return yetkiResponse;

	        }
	}

    
    
    
    
    
    
    
    


}
