package com.turkai.consume.services;

import com.turkai.consume.configuration.WSConfiguration;
import com.turkai.consume.services.serviceInterface.IGeneralService;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GeneralService implements IGeneralService {

    @Autowired
    WSConfiguration wsConfiguration;

    public WSConfiguration fillStaticField() {
        WSConfiguration wsConfiguration1 = new WSConfiguration();
        wsConfiguration1.setYTKPRL(wsConfiguration.getYTKPRL());
        wsConfiguration1.setYTKKLNAADI(wsConfiguration.getYTKKLNAADI());
        wsConfiguration1.setPROJEGRUPADI(wsConfiguration.getPROJEGRUPADI());


        return wsConfiguration;
    }

    public KullaniciBilgi setKullaniciBilgi(WSConfiguration ws, String tlpKullaniciAdi, String tlpIP, String tlpTC, String tlpNdnAck, String tlpNdnKod) {

        KullaniciBilgi kullaniciBilgi = new KullaniciBilgi();
        kullaniciBilgi.setPROJEADI(ws.getPROJEADI());
        kullaniciBilgi.setTLPIP(tlpIP);
        kullaniciBilgi.setTLPKLNADI(tlpKullaniciAdi);
        kullaniciBilgi.setYTKKLNADI(ws.getYTKKLNAADI());
        kullaniciBilgi.setYTKPRL(ws.getYTKPRL());
        kullaniciBilgi.setTLPNDNACK(tlpNdnAck);
        kullaniciBilgi.setTLPNDNKOD(tlpNdnKod);
        kullaniciBilgi.setTLPTC(tlpTC);

        return kullaniciBilgi;
    }

    public boolean isValidateParamHTML(String requestParam) {

        String regex = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

        Pattern p = Pattern.compile(regex);

        if (requestParam == null) {

            return false;
        }

        Matcher m = p.matcher(requestParam);

        //return m.matches();
        return requestParam.contains("<")||requestParam.contains("(");
    }

    public boolean isValidateParamEncoding(String requestParam) {


        if (requestParam == null) {
            return false;
        }
        else if (requestParam.contains("%")) {
            return true;
        }
        else {
            return false;
        }


    }


    public boolean plateRegexControl(String requestParam){

        String pattern = "(0[1-9]|[1-7][0-9]|8[01])[a-zA-ZÇçİıĞğÖöŞşÜü\\s]{1,30}[0-9]{1,4}";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(requestParam);

        return m.matches();

    }


}
