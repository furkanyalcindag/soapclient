package com.turkai.consume.services.serviceInterface;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetkiResponse;

public interface IKullaniciService {
	
	KullaniciYetkiResponse kullaniciDogrulaLDAPService(WSConfiguration ws, String pPBIK, String pParola, String pIP);
	KullaniciYetkiResponse projeKullaniciEkle(WSConfiguration ws, String pKullaniciAdi); 

	
	KullaniciYetkiResponse projeKullaniciCikar(WSConfiguration ws, String pKullaniciAdi); 
}
