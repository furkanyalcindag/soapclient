package com.turkai.consume.soapServices;

import com.turkai.consume.DTO.TBS.AracDurumuDTO;
import com.turkai.consume.DTO.TBS.GercekSahisDTO;
import com.turkai.consume.DTO.TBS.TuzelSahisDTO;
import com.turkai.consume.services.SoapConnectionTBSService;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;
import com.turkai.consume.soapModels.ResponseModel.TBSResponse.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TBSWSClientService {

    public final String wsURL = "https://servis.jandarma.gov.tr/Kafes.Service/TrafikBilgiServis.svc";
    public final String soapAction
            = "http://tempuri.org/ITrafikBilgiServis/";

    private static final Logger log =
            LoggerFactory.getLogger(TBSWSClientService.class);

    public AracSahibiResponse plakadanAracSahibiGetir(KullaniciBilgi kullaniciBilgi, String plaka) {

        try {

            String methodName = "TBS_PlakadanAracSahibiGetir";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();

            elements.put("PROJEADI", kullaniciBilgi.getPROJEADI());
            elements.put("TLPIP", kullaniciBilgi.getTLPIP());
            elements.put("TLPKLNADI", kullaniciBilgi.getTLPKLNADI());
            elements.put("TLPNDNACK", kullaniciBilgi.getTLPNDNACK());
            elements.put("TLPNDNKOD", kullaniciBilgi.getTLPNDNKOD());
            elements.put("TLPTC", kullaniciBilgi.getTLPTC());
            elements.put("YTKKLNADI", kullaniciBilgi.getYTKKLNADI());
            elements.put("YTKPRL", kullaniciBilgi.getYTKPRL());

            elements.put("Plaka", plaka);

            SoapConnectionTBSService connectionBTSService = new SoapConnectionTBSService();

            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = connectionBTSService.callSoapWebService(wsURL, soapAction, "soapenv", "tem", "http://tempuri.org/", elements, methodName, nodeValueList);

            AracSahibiResponse aracSahibiResponse = new AracSahibiResponse();
            Class<GercekSahisDTO> genericFieldsGercekSahis = GercekSahisDTO.class;
            Class<TuzelSahisDTO> genericFieldsTuzelSahis = TuzelSahisDTO.class;

            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("HATADETAY")) {

                    aracSahibiResponse.setHatadetay(node[1]);

                } else if (node[0].endsWith("HATAKOD")) {
                    aracSahibiResponse.setHataKodu(node[1]);

                }
                else if(node[0].equals("HataKodu")){
                    if(node[1].equals("1"))
                        aracSahibiResponse.setHataKodu2("0");
                    else
                        aracSahibiResponse.setHataKodu2("1");
                }
                else if (node[0].endsWith("GercekSahis")) {

                    GercekSahisDTO gercekSahisDTO = new GercekSahisDTO();

                    //for (int y = i; y < 24 + i; y++) {
                    for (int y = i; y < nodeValueList.size() ; y++) {
                        String[] nodeValue;
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        if(nodeSub.length > 1 && nodeSub[1] != null&&nodeSub[1].startsWith("TuzelSahis")){
                            i=y-1;
                            break;
                        }
                        if (nodeSub.length > 1 && nodeSub[1] != null) {
                            nodeValue = nodeSub[1].split("->");
                        } else {
                            nodeValue = null;
                        }
                        Field[] fields = genericFieldsGercekSahis.getDeclaredFields();


                        for (Field field : fields) {

                            if (nodeValue != null && nodeValue[0].toLowerCase().equals(field.getName().toLowerCase())) {

                                field.setAccessible(true);
                                if(nodeValue[1].equals("null"))
                                    nodeValue[1]=null;
                                field.set(gercekSahisDTO, nodeValue[1]);


                            }


                        }


                    }
                    //i = i + 20;
                    if (gercekSahisDTO!=null&&gercekSahisDTO.getAd()==null&gercekSahisDTO.getSoyad()==null)
                        gercekSahisDTO=null;
                    aracSahibiResponse.setGercekSahis(gercekSahisDTO);

                } else if (node[0].endsWith("TuzelSahis")) {


                    TuzelSahisDTO tuzelSahisDTO = new TuzelSahisDTO();
                    for (int y = i; y < 8 + i; y++) {

                        String[] nodeValue;
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        if (nodeSub.length > 1 && nodeSub[1] != null) {
                            nodeValue = nodeSub[1].split("->");
                        } else {
                            nodeValue = null;
                        }
                        Field[] fields = genericFieldsTuzelSahis.getDeclaredFields();


                        for (Field field : fields) {

                            if (nodeValue != null && nodeValue[0].toLowerCase().equals(field.getName().toLowerCase())) {

                                field.setAccessible(true);
                                if(nodeValue[1].equals("null"))
                                    nodeValue[1]=null;
                                field.set(tuzelSahisDTO, nodeValue[1]);


                            }


                        }


                    }
                    i = i + 7;
                    if (tuzelSahisDTO!=null&&tuzelSahisDTO.getAd()==null&tuzelSahisDTO.getVergiNo()==null)
                        tuzelSahisDTO=null;
                    aracSahibiResponse.setTuzelSahis(tuzelSahisDTO);

                } else {
                    System.out.println(node[0]);
                }


            }
            return aracSahibiResponse;


        } catch (Exception e) {

            log.error(e.getMessage());


            return null;

        }


    }


    public AracDurumlariResponse plakadanAracDurumuGetir(KullaniciBilgi kullaniciBilgi, String plaka) {

        try {

            String methodName = "TBS_AracDurumlariniGetir";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();

            elements.put("PROJEADI", kullaniciBilgi.getPROJEADI());
            elements.put("TLPIP", kullaniciBilgi.getTLPIP());
            elements.put("TLPKLNADI", kullaniciBilgi.getTLPKLNADI());
            elements.put("TLPNDNACK", kullaniciBilgi.getTLPNDNACK());
            elements.put("TLPNDNKOD", kullaniciBilgi.getTLPNDNKOD());
            elements.put("TLPTC", kullaniciBilgi.getTLPTC());
            elements.put("YTKKLNADI", kullaniciBilgi.getYTKKLNADI());
            elements.put("YTKPRL", kullaniciBilgi.getYTKPRL());

            elements.put("Plaka", plaka);

            SoapConnectionTBSService connectionBTSService = new SoapConnectionTBSService();

            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = connectionBTSService.callSoapWebService(wsURL, soapAction, "soapenv", "tem", "http://tempuri.org/", elements, methodName, nodeValueList);

            AracDurumlariResponse aracDurumuResponse = new AracDurumlariResponse();
            Class<AracDurumuDTO> genericFieldsGercekSahis = AracDurumuDTO.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("HATADETAY")) {

                    aracDurumuResponse.setHataDetay(node[1]);

                } else if (node[0].endsWith("HATAKOD")) {
                    aracDurumuResponse.setHataKodu(node[1]);

                } else if (node[0].endsWith("Sonuc")) {

                    AracDurumuDTO aracDurumuDTO = new AracDurumuDTO();
                    int x =0;
                    for (int y = i; y < nodeValueList.size()-4 + i; y++) {

                        String[] nodeValue;
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        if (nodeSub.length > 1 && nodeSub[1] != null) {
                            nodeValue = nodeSub[1].split("->");
                        } else {
                            nodeValue = null;
                        }
                        Field[] fields = genericFieldsGercekSahis.getDeclaredFields();


                        for (Field field : fields) {

                            if (nodeValue != null && nodeValue[0].toLowerCase().equals(field.getName().toLowerCase())) {


                                field.setAccessible(true);
                                if(nodeValue[1].equals("null"))
                                    nodeValue[1]=null;
                                field.set(aracDurumuDTO, nodeValue[1]);


                            }


                        }

                        x++;


                    }
                    i = i + x;
                    aracDurumuResponse.setAracDurumu(aracDurumuDTO);

                } else {
                    System.out.println(node[0]);
                }


            }
            return aracDurumuResponse;


        } catch (Exception e) {

            log.error(e.getMessage());

            return null;

        }


    }
}
