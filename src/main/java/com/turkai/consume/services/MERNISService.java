package com.turkai.consume.services;

import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiDogrulaRequest;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.TCKisiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IGeneralService;
import com.turkai.consume.services.serviceInterface.IMERNISService;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;
import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiRequest;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.KisiFotografResponse;
import com.turkai.consume.soapServices.MERNISWSClientService;

@Service
public class MERNISService implements IMERNISService {

	@Autowired
	IGeneralService generalService;

	private static final Logger log = LoggerFactory.getLogger(MERNISService.class);

	MERNISWSClientService mernisWSClientService = new MERNISWSClientService();

	@Override
	public KisiFotografResponse kisiFotografGetir(WSConfiguration ws, KisiRequest kisiRequest) {
		// TODO Auto-generated method stub

		KisiFotografResponse fotografResponse = new KisiFotografResponse();

		KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();

		try {

			kullaniciBilgi = generalService.setKullaniciBilgi(ws,
					kisiRequest.getKullaniciBilgi().getTLPKLNADI(),
					kisiRequest.getKullaniciBilgi().getTLPIP(),
					kisiRequest.getKullaniciBilgi().getTLPTC(),
					kisiRequest.getKullaniciBilgi().getTLPNDNACK(),
					kisiRequest.getKullaniciBilgi().getTLPNDNKOD());

			fotografResponse = mernisWSClientService.kisiFotografGetir(kullaniciBilgi, kisiRequest.getTcNo());

			return fotografResponse;

		} catch (Exception e) {
			// TODO: handle exception
			fotografResponse.setHataKodu("1000");
			fotografResponse.setHataDetay("Service is unavaliable");
			log.error(e.getMessage());

			return fotografResponse;
		}

		
	}

	@Override
	public TCKisiResponse TCKisiGetir(WSConfiguration ws, KisiRequest kisiRequest) {
		TCKisiResponse tcKisiResponse = new TCKisiResponse();

		KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();

		try {

			kullaniciBilgi = generalService.setKullaniciBilgi(ws,
					kisiRequest.getKullaniciBilgi().getTLPKLNADI(),
					kisiRequest.getKullaniciBilgi().getTLPIP(),
					kisiRequest.getKullaniciBilgi().getTLPTC(),
					kisiRequest.getKullaniciBilgi().getTLPNDNACK(),
					kisiRequest.getKullaniciBilgi().getTLPNDNKOD());

			tcKisiResponse = mernisWSClientService.TCKisiGetir(kullaniciBilgi, kisiRequest.getTcNo());

			return tcKisiResponse;

		} catch (Exception e) {
			// TODO: handle exception
			tcKisiResponse.setHataKodu("1000");
			tcKisiResponse.setHataDetay("Service is unavaliable");
			log.error(e.getMessage());

			return tcKisiResponse;
		}
	}

	@Override
	public boolean TCKisiDogrula(WSConfiguration ws, KisiDogrulaRequest kisiDogrulaRequest) {
		TCKisiResponse tcKisiResponse = new TCKisiResponse();

		KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();

		try {

			kullaniciBilgi = generalService.setKullaniciBilgi(ws,
					kisiDogrulaRequest.getKullaniciBilgi().getTLPKLNADI(),
					kisiDogrulaRequest.getKullaniciBilgi().getTLPIP(),
					kisiDogrulaRequest.getKullaniciBilgi().getTLPTC(),
					kisiDogrulaRequest.getKullaniciBilgi().getTLPNDNACK(),
					kisiDogrulaRequest.getKullaniciBilgi().getTLPNDNKOD());

			tcKisiResponse = mernisWSClientService.TCKisiGetir(kullaniciBilgi, kisiDogrulaRequest.getTcNo());

			if(tcKisiResponse.getMernisTCKisiDTO().getAd().equals(kisiDogrulaRequest.getAd())&&
					tcKisiResponse.getMernisTCKisiDTO().getSoyad().equals(kisiDogrulaRequest.getSoyad()))
			{
				return true;
			}
			else {
				return false;
			}



		} catch (Exception e) {
			// TODO: handle exception

			log.error(e.getMessage());

			return false;
		}
	}

}
