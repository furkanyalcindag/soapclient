package com.turkai.consume.services.serviceInterface;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiDogrulaRequest;
import com.turkai.consume.soapModels.RequestModel.MERNIS.KisiRequest;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.KisiFotografResponse;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.TCKisiResponse;

public interface IMERNISService {
	
	KisiFotografResponse kisiFotografGetir(WSConfiguration ws, KisiRequest kisiRequest);

	TCKisiResponse TCKisiGetir(WSConfiguration ws, KisiRequest kisiRequest);

	boolean TCKisiDogrula(WSConfiguration ws, KisiDogrulaRequest kisiDogrulaRequest);

}
