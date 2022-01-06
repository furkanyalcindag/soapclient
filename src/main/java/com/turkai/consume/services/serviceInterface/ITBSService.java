package com.turkai.consume.services.serviceInterface;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.soapModels.RequestModel.TBS.TBSAracPlakaRequest;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracDurumlariResponse;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.AracSahibiResponse;

public interface ITBSService {


    AracSahibiResponse plakadanAracSahibiGetirService(WSConfiguration ws, TBSAracPlakaRequest tbsAracPlakaRequest);

    AracDurumlariResponse plakadanAracDurumuGetirService(WSConfiguration ws, TBSAracPlakaRequest tbsAracPlakaRequest);


}
