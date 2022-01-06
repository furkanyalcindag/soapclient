package com.turkai.consume.soapServices;

import com.turkai.consume.helper.ReflectionHelper;
import com.turkai.consume.services.SoapConnectionKullaniciService;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetki;
import com.turkai.consume.soapModels.ResponseModel.KullaniciResponse.KullaniciYetkiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class KullaniciWSClientService {


    private final Logger log = LoggerFactory.getLogger(KullaniciWSClientService.class);
    public final String wsURL = "https://servis.jandarma.gov.tr/KullaniciWebServis/KullaniciServis.svc/soap";
    public final String soapAction
            = "http://tempuri.org/IKullaniciServis/";


    public KullaniciYetkiResponse projectUserList(String pYetkiliKullanici, String pYetkiliParola, String pProjeGrupAdi) {
        try {
            String soapActionByMethod = soapAction + "ProjedekiKullanicilariListele";
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            elements.put("pYetkiliKullanici", pYetkiliKullanici);
            elements.put("pYetkiliParola", pYetkiliParola);
            elements.put("pProjeGrupAdi", pProjeGrupAdi);

            //callSoapWebService(soapEndpointUrl, soapAction);
            SoapConnectionKullaniciService soapConnectionKullaniciService = new SoapConnectionKullaniciService();
            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = soapConnectionKullaniciService.callSoapWebService(wsURL, soapActionByMethod, "soapenv", "tem", "http://tempuri.org/", elements, "ProjedekiKullanicilariListele", nodeValueList);

            KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
            List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();
            Class<KullaniciYetki> genericFields = KullaniciYetki.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("mHATADETAY")) {

                    kullaniciYetkiResponse.setmHATADETAY(node[1]);

                } else if (node[0].endsWith("mHATAKOD")) {
                    kullaniciYetkiResponse.setmHATAKOD(Integer.parseInt(node[1]));

                } else if (node[0].endsWith("KullaniciYetki")) {

                    KullaniciYetki kullaniciYetki = new KullaniciYetki();

                    for (int y = i + 1; y < 20 + i; y++) {
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        String[] nodeValue = nodeSub[1].split("->");
                        Field[] fields = genericFields.getDeclaredFields();

                        for (Field field : fields) {

                            if (nodeValue[0].equals(field.getName())) {

                                field.setAccessible(true);
                                field.set(kullaniciYetki, nodeValue[1]);


                            }


                        }


                    }
                    kullaniciYetkiList.add(kullaniciYetki);
                    i = i + 19;


                } else {
                    System.out.println(node[0]);

                }


            }

            kullaniciYetkiResponse.setmKullaniciYetkiListe(kullaniciYetkiList);
            return kullaniciYetkiResponse;


        } catch (Exception e) {

            log.error("projectUserList", e);
            return null;
        }


    }


    public KullaniciYetkiResponse projectRemoveUser(String pYetkiliKullanici, String pYetkiliParola, String pProjeGrupAdi, String pKullaniciAdi) {

        try {
            String soapActionByMethod = soapAction + "ProjedenKullaniciCikar";
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            elements.put("pYetkiliKullanici", pYetkiliKullanici);
            elements.put("pYetkiliParola", pYetkiliParola);
            elements.put("pkullaniciAdi", pKullaniciAdi);
            elements.put("pProjeGrupAdi", pProjeGrupAdi);


            //callSoapWebService(soapEndpointUrl, soapAction);
            SoapConnectionKullaniciService soapConnectionKullaniciService = new SoapConnectionKullaniciService();
            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = soapConnectionKullaniciService.callSoapWebService(wsURL, soapActionByMethod, "soapenv", "tem", "http://tempuri.org/", elements, "ProjedenKullaniciCikar", nodeValueList);

            KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
            List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();
            Class<KullaniciYetki> genericFields = KullaniciYetki.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("mHATADETAY")) {

                    kullaniciYetkiResponse.setmHATADETAY(node[1]);

                } else if (node[0].endsWith("mHATAKOD")) {
                    kullaniciYetkiResponse.setmHATAKOD(Integer.parseInt(node[1]));

                } else if (node[0].endsWith("KullaniciYetki")) {

                    KullaniciYetki kullaniciYetki = new KullaniciYetki();

                    for (int y = i; y < 25; y++) {


                        String[] nodeSub = nodeValueList.get(y).split(":");
                        String[] nodeSub1 = nodeSub[1].split("->");

                        if (ReflectionHelper.containsField(genericFields, nodeSub1[0])) {


                            Field field = genericFields.getDeclaredField(nodeSub1[0]);

                            field.setAccessible(true);
                            field.set(kullaniciYetki, nodeSub1[1]);

                        }


                    }
                    kullaniciYetkiList.add(kullaniciYetki);
                    i = i + 19;


                } else {
                    System.out.println(node[0]);

                }


            }

            kullaniciYetkiResponse.setmKullaniciYetkiListe(kullaniciYetkiList);
            return kullaniciYetkiResponse;


        } catch (Exception e) {
            log.error("projectRemoveUser", e);
            return null;
        }


    }


    public KullaniciYetkiResponse verifyUserLDAP(String pYetkiliKullanici, String pYetkiliParola, String pProjeGrupAdi, String pPBIK, String pParola, String pIP) {

        try {
            String methodName = "ProjeGirisDogrulaLDAP";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            elements.put("pYetkiliKullanici", pYetkiliKullanici);
            elements.put("pYetkiliParola", pYetkiliParola);
            elements.put("pProjeGrupAdi", pProjeGrupAdi);
            elements.put("pPBIK", pPBIK);
            elements.put("pParola", pParola);
            elements.put("pIP", pIP);


            //callSoapWebService(soapEndpointUrl, soapAction);
            SoapConnectionKullaniciService soapConnectionKullaniciService = new SoapConnectionKullaniciService();
            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = soapConnectionKullaniciService.callSoapWebService(wsURL, soapActionByMethod, "soapenv", "tem", "http://tempuri.org/", elements, methodName, nodeValueList);

            KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
            List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();
            Class<KullaniciYetki> genericFields = KullaniciYetki.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("mHATADETAY")) {

                    kullaniciYetkiResponse.setmHATADETAY(node[1]);

                } else if (node[0].endsWith("mHATAKOD")) {
                    kullaniciYetkiResponse.setmHATAKOD(Integer.parseInt(node[1]));

                } else if (node[0].endsWith("KullaniciYetki")) {

                    KullaniciYetki kullaniciYetki = new KullaniciYetki();

                    for (int y = i; y < 20 + i; y++) {
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        String[] nodeValue = nodeSub[1].split("->");
                        Field[] fields = genericFields.getDeclaredFields();


                        for (Field field : fields) {

                            if (nodeValue[0].equals(field.getName())) {

                                field.setAccessible(true);
                                field.set(kullaniciYetki, nodeValue[1]);


                            }


                        }


                    }
                    kullaniciYetkiList.add(kullaniciYetki);
                    i = i + 19;


                } else {
                    System.out.println(node[0]);

                }


            }


            kullaniciYetkiResponse.setmKullaniciYetkiListe(kullaniciYetkiList);
            return kullaniciYetkiResponse;


        } catch (Exception e) {
            e.printStackTrace();
            log.error("verifyUserLDAP", e);
            return null;
        }


    }


    public KullaniciYetkiResponse projectAddUser(String pYetkiliKullanici, String pYetkiliParola, String pProjeGrupAdi, String pKullaniciAdi) {

        try {
            String methodName = "ProjeyeKullaniciEkle";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            elements.put("pYetkiliKullanici", pYetkiliKullanici);
            elements.put("pYetkiliParola", pYetkiliParola);
            elements.put("pkullaniciAdi", pKullaniciAdi);
            elements.put("pProjeGrupAdi", pProjeGrupAdi);

            elements.put("pProjeRolKod", "1");


            //callSoapWebService(soapEndpointUrl, soapAction);
            SoapConnectionKullaniciService soapConnectionKullaniciService = new SoapConnectionKullaniciService();
            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = soapConnectionKullaniciService.callSoapWebService(wsURL, soapActionByMethod, "soapenv", "tem", "http://tempuri.org/", elements, methodName, nodeValueList);

            KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
            List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();
            Class<KullaniciYetki> genericFields = KullaniciYetki.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("mHATADETAY")) {

                    kullaniciYetkiResponse.setmHATADETAY(node[1]);

                } else if (node[0].endsWith("mHATAKOD")) {
                    kullaniciYetkiResponse.setmHATAKOD(Integer.parseInt(node[1]));

                } else if (node[0].endsWith("KullaniciYetki")) {

                    KullaniciYetki kullaniciYetki = new KullaniciYetki();

                    for (int y = i; y < 25; y++) {


                        String[] nodeSub = nodeValueList.get(y).split(":");
                        String[] nodeSub1 = nodeSub[1].split("->");

                        if (ReflectionHelper.containsField(genericFields, nodeSub1[0])) {


                            Field field = genericFields.getDeclaredField(nodeSub1[0]);

                            field.setAccessible(true);
                            field.set(kullaniciYetki, nodeSub1[1]);

                        }


                    }
                    kullaniciYetkiList.add(kullaniciYetki);
                    i = i + 19;


                } else {
                    System.out.println(node[0]);
                }

            }

            if (kullaniciYetkiResponse.getmHATAKOD() == 40) {

                KullaniciYetkiResponse kullaniciYetkiResponse1 = new KullaniciYetkiResponse();

                kullaniciYetkiResponse1 = projectUserList(pYetkiliKullanici, pYetkiliParola, pProjeGrupAdi);
                if (kullaniciYetkiResponse1.getmHATAKOD() == 0) {
                    for (KullaniciYetki kullanici : kullaniciYetkiResponse1.getmKullaniciYetkiListe()) {
                        if (pKullaniciAdi.equals(kullanici.getmKULLANICIADI())) {
                            kullaniciYetkiList.add(kullanici);
                        }

                    }

                }

            }
            kullaniciYetkiResponse.setmKullaniciYetkiListe(kullaniciYetkiList);


            return kullaniciYetkiResponse;


        } catch (Exception e) {
            log.error("projectAddUser", e);
            return null;
        }


    }


    public KullaniciYetkiResponse projectPermissionInfo(String pYetkiliKullanici, String pYetkiliParola, String pProjeGrupAdi, String pPBIK) {

        try {
            String methodName = "ProjeYetkiBilgi";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            elements.put("pYetkiliKullanici", pYetkiliKullanici);
            elements.put("pYetkiliParola", pYetkiliParola);
            elements.put("pProjeGrupAdi", pProjeGrupAdi);
            elements.put("pPBIK", pPBIK);


            //callSoapWebService(soapEndpointUrl, soapAction);
            SoapConnectionKullaniciService soapConnectionKullaniciService = new SoapConnectionKullaniciService();
            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = soapConnectionKullaniciService.callSoapWebService(wsURL, soapActionByMethod, "soapenv", "tem", "http://tempuri.org/", elements, methodName, nodeValueList);

            KullaniciYetkiResponse kullaniciYetkiResponse = new KullaniciYetkiResponse();
            List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();
            Class<KullaniciYetki> genericFields = KullaniciYetki.class;


            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("mHATADETAY")) {

                    kullaniciYetkiResponse.setmHATADETAY(node[1]);

                } else if (node[0].endsWith("mHATAKOD")) {
                    kullaniciYetkiResponse.setmHATAKOD(Integer.parseInt(node[1]));

                } else if (node[0].endsWith("mKULLANICIYETKI")) {

                    KullaniciYetki kullaniciYetki = new KullaniciYetki();

                    for (int y = i; y < 19; y++) {
                        String[] nodeSub = nodeValueList.get(y).split(":");

                        Field[] fields = genericFields.getFields();

                        for (Field field : fields) {

                            if (nodeSub[0].equals(field.getName())) {

                                field.set(kullaniciYetki, node[1]);


                            }


                        }


                    }
                    kullaniciYetkiList.add(kullaniciYetki);
                    i = i + 19;


                } else {
                    System.out.println(node[0]);

                }


            }

            kullaniciYetkiResponse.setmKullaniciYetkiListe(kullaniciYetkiList);
            return kullaniciYetkiResponse;


        } catch (Exception e) {
            return null;
        }


    }

    public List<KullaniciYetki> generateFakeData() {

        List<KullaniciYetki> kullaniciYetkiList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            KullaniciYetki kullaniciYetki = new KullaniciYetki();
            kullaniciYetki.setmADSOYAD("hsdhjkhdkjs" + String.valueOf(i));
            kullaniciYetki.setmBIRLIKKOD("456");
            kullaniciYetki.setmDAIRE("sasasa");
            kullaniciYetki.setmGOREV("hdjkshdkjshdjks");
            kullaniciYetki.setmIL("Ankara");

            kullaniciYetkiList.add(kullaniciYetki);


        }


        return kullaniciYetkiList;
    }


}
