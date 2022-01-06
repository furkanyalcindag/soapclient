package com.turkai.consume.services.serviceInterface;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;

public interface IGeneralService {

    KullaniciBilgi setKullaniciBilgi(WSConfiguration ws, String tlpKullaniciAdi, String tlpIP, String tlpTC, String tlpNdnAck, String tlpNdnKod);

    boolean isValidateParamEncoding(String requestParam);

    boolean isValidateParamHTML(String requestParam);

    boolean plateRegexControl(String requestParam);
}
