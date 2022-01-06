package com.turkai.consume.soapServices;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.turkai.consume.DTO.Mernis.MernisTCKisiDTO;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.TCKisiResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.turkai.consume.DTO.Mernis.MERNISKisiFotografDTO;
import com.turkai.consume.DTO.TBS.GercekSahisDTO;
import com.turkai.consume.helper.ReflectionHelper;
import com.turkai.consume.services.SoapConnectionMERNISService;
import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;
import com.turkai.consume.soapModels.ResponseModel.MERNISResponse.KisiFotografResponse;

public class MERNISWSClientService {

    public final String wsURL = "https://servis.jandarma.gov.tr/MERNISServisV2/MERNISServis.svc/soap";
    public final String soapAction = "http://tempuri.org/IMERNISServis/";

    private static final Logger log = LoggerFactory.getLogger(MERNISWSClientService.class);

    public KisiFotografResponse kisiFotografGetir(KullaniciBilgi kullaniciBilgi, String tc) {

        try {

            String methodName = "KPS_BilesikKutukSorgulaKimlikNoServisClient";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();

            elements.put("mPROJEADI", kullaniciBilgi.getPROJEADI());
            elements.put("mTLPIP", kullaniciBilgi.getTLPIP());
            elements.put("mTLPKLNADI", kullaniciBilgi.getTLPKLNADI());
            elements.put("mTLPNDNACK", kullaniciBilgi.getTLPNDNACK());
            elements.put("mTLPNDNKOD", kullaniciBilgi.getTLPNDNKOD());
            elements.put("mTLPTC", kullaniciBilgi.getTLPTC());
            elements.put("mYTKKLNADI", kullaniciBilgi.getYTKKLNADI());
            elements.put("mYTKPRL", kullaniciBilgi.getYTKPRL());
            JSONObject jsonObject = null;

            elements.put("long", tc);

            SoapConnectionMERNISService connectionMERNISService = new SoapConnectionMERNISService();

            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = (ArrayList<String>) connectionMERNISService.callSoapWebService(wsURL, soapAction, "soapenv", "tem",
                    "http://tempuri.org/", elements, methodName, nodeValueList,jsonObject).get("nodeValueList");

            KisiFotografResponse kisiFotografResponse = new KisiFotografResponse();

            Class<MERNISKisiFotografDTO> genericFieldsKisiFotograf = MERNISKisiFotografDTO.class;

            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("HATADETAY")) {

                    kisiFotografResponse.setHataDetay(node[1]);

                } else if (node[0].endsWith("HATAKOD")) {
                    kisiFotografResponse.setHataKodu(node[1]);

                } else if (node[0].endsWith("TCVatandasiKisiKutukleri")) {
                    MERNISKisiFotografDTO mernisKisiFotografDTO = new MERNISKisiFotografDTO();

                    for (int y = i; y < nodeValueList.size(); y++) {
                        String[] nodeValue;
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        if (nodeSub.length > 1 && nodeSub[1] != null && nodeSub[1].startsWith("YabanciKisiKutukleri")) {
                            i = y - 1;
                            break;
                        }
                        if (nodeSub.length > 1 && nodeSub[1] != null) {
                            nodeValue = nodeSub[1].split("->");
                        } else {
                            nodeValue = null;
                        }
                        Field[] fields = genericFieldsKisiFotograf.getDeclaredFields();

                        if (ReflectionHelper.containsField(genericFieldsKisiFotograf, nodeValue[0])) {

                            Field field = genericFieldsKisiFotograf.getDeclaredField(nodeValue[0]);
                            field.setAccessible(true);
                            if (field.get(mernisKisiFotografDTO) == null || field.get(mernisKisiFotografDTO).equals("null"))
                                field.set(mernisKisiFotografDTO, nodeValue[1]);

                        }

                    }
                    kisiFotografResponse.setMernisKisiFotografDTO(mernisKisiFotografDTO);

                } else {
                    System.out.println(node[0]);
                }
            }
            return kisiFotografResponse;

        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());

            return null;
        }


    }


 /*   public TCKisiResponse TCKisiGetir(KullaniciBilgi kullaniciBilgi, String tc) {

        try {

            String methodName = "KPS_BilesikKutukSorgulaKimlikNoServisClient";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();

            elements.put("mPROJEADI", kullaniciBilgi.getPROJEADI());
            elements.put("mTLPIP", kullaniciBilgi.getTLPIP());
            elements.put("mTLPKLNADI", kullaniciBilgi.getTLPKLNADI());
            elements.put("mTLPNDNACK", kullaniciBilgi.getTLPNDNACK());
            elements.put("mTLPNDNKOD", kullaniciBilgi.getTLPNDNKOD());
            elements.put("mTLPTC", kullaniciBilgi.getTLPTC());
            elements.put("mYTKKLNADI", kullaniciBilgi.getYTKKLNADI());
            elements.put("mYTKPRL", kullaniciBilgi.getYTKPRL());

            elements.put("long", tc);

            SoapConnectionMERNISService connectionMERNISService = new SoapConnectionMERNISService();

            ArrayList<String> nodeValueList = new ArrayList<>();

            nodeValueList = connectionMERNISService.callSoapWebService(wsURL, soapAction, "soapenv", "tem",
                    "http://tempuri.org/", elements, methodName, nodeValueList);

            TCKisiResponse tcKisiResponse = new TCKisiResponse();

            Class<MERNISKisiFotografDTO> genericFieldsKisiFotograf = MERNISKisiFotografDTO.class;

            for (int i = 0; i < nodeValueList.size(); i++) {
                String[] node = nodeValueList.get(i).split("->");

                if (node[0].endsWith("HATADETAY")) {

                    tcKisiResponse.setHataDetay(node[1]);

                } else if (node[0].endsWith("HATAKOD")) {
                    tcKisiResponse.setHataKodu(node[1]);

                }


                else if (node[0].endsWith("TCKKBilgisi")) {
                    MernisTCKisiDTO mernisTCKisiDTO = new MernisTCKisiDTO();

                    for (int y = i; y < nodeValueList.size(); y++) {
                        String[] nodeValue;
                        String[] nodeSub = nodeValueList.get(y).split(":");
                        if (nodeSub.length > 1 && nodeSub[1] != null && nodeSub[1].startsWith("YabanciKisiKutukleri")) {
                            i = y - 1;
                            break;
                        }
                        if (nodeSub.length > 1 && nodeSub[1] != null) {
                            nodeValue = nodeSub[1].split("->");
                        } else {
                            nodeValue = null;
                        }

                        if (nodeValue != null && nodeValue[0].equals("Ad")) {
                            mernisTCKisiDTO.setAd(nodeValue[1]);

                        } else if (nodeValue != null && nodeValue[0].equals("Soyad")) {
                            mernisTCKisiDTO.setSoyad(nodeValue[1]);
                        } else if (nodeValue != null && nodeValue[0].equals("AnneAd")) {
                            mernisTCKisiDTO.setAnneAd(nodeValue[1]);
                        } else if (nodeValue != null && nodeValue[0].equals("BabaAd")) {
                            mernisTCKisiDTO.setBabaAd(nodeValue[1]);
                        } else if (nodeValue != null && nodeValue[0].equals("DogumYer")) {
                            mernisTCKisiDTO.setDogumYeri(nodeValue[1]);
                        } else if (nodeValue != null && nodeValue[0].equals("DogumTarih")) {

                            String ay = "";
                            String gun = "";
                            String yil = "";
                            for (int z = 1; z < 5; z++) {
                                String[] nodeValueDogumTarihi;
                                String[] nodeSubDogumTarihi = nodeValueList.get(y + z).split(":");

                                if (nodeSubDogumTarihi.length > 1 && nodeSubDogumTarihi[1] != null) {
                                    nodeValueDogumTarihi = nodeSubDogumTarihi[1].split("->");
                                } else {
                                    nodeValueDogumTarihi = null;
                                }


                                if (nodeValueDogumTarihi != null && nodeValueDogumTarihi[0].equals("Ay")) {
                                    ay = nodeValueDogumTarihi[1];

                                    if(ay.length()==1){
                                        ay="0"+ay;
                                    }
                                } else if (nodeValueDogumTarihi != null && nodeValueDogumTarihi[0].equals("Gun")) {
                                    gun = nodeValueDogumTarihi[1];
                                    if(gun.length()==1){
                                        gun="0"+gun;
                                    }

                                } else if (nodeValueDogumTarihi != null && nodeValueDogumTarihi[0].equals("Yil")) {
                                    yil = nodeValueDogumTarihi[1];

                                } else {
                                    continue;
                                }
                            }


                            mernisTCKisiDTO.setDogumTarihi(gun + "." + ay + "." + yil);
                        } else if (nodeValue != null && nodeValue[0].equals("Cinsiyet")) {

                            String cinsiyet = "";
                            for (int z = 1; z < 4; z++) {
                                String[] nodeValueCinsiyet;
                                String[] nodeSubCinsiyet = nodeValueList.get(y + z).split(":");

                                if (nodeSubCinsiyet.length > 1 && nodeSubCinsiyet[1] != null) {
                                    nodeValueCinsiyet = nodeSubCinsiyet[1].split("->");
                                } else {
                                    nodeValueCinsiyet = null;
                                }


                                if (nodeValueCinsiyet != null && nodeValueCinsiyet[0].equals("Aciklama")) {
                                    cinsiyet = nodeValueCinsiyet[1];

                                }

                            }


                            mernisTCKisiDTO.setCinsiyet(cinsiyet);
                        } else {
                            continue;
                        }


                    }
                    tcKisiResponse.setMernisTCKisiDTO(mernisTCKisiDTO);

                } else {
                    System.out.println(node[0]);
                }
            }
            return tcKisiResponse;

        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());

            return null;
        }


    }
*/


    public TCKisiResponse TCKisiGetir(KullaniciBilgi kullaniciBilgi, String tc) {

        try {

            String methodName = "KPS_BilesikKutukSorgulaKimlikNoServisClient";
            String soapActionByMethod = soapAction + methodName;
            LinkedHashMap<String, String> elements = new LinkedHashMap<>();
            MernisTCKisiDTO mernisTCKisiDTO = new MernisTCKisiDTO();

            elements.put("mPROJEADI", kullaniciBilgi.getPROJEADI());
            elements.put("mTLPIP", kullaniciBilgi.getTLPIP());
            elements.put("mTLPKLNADI", kullaniciBilgi.getTLPKLNADI());
            elements.put("mTLPNDNACK", kullaniciBilgi.getTLPNDNACK());
            elements.put("mTLPNDNKOD", kullaniciBilgi.getTLPNDNKOD());
            elements.put("mTLPTC", kullaniciBilgi.getTLPTC());
            elements.put("mYTKKLNADI", kullaniciBilgi.getYTKKLNADI());
            elements.put("mYTKPRL", kullaniciBilgi.getYTKPRL());
            JSONObject jsonObject = new JSONObject();

            elements.put("long", tc);

            SoapConnectionMERNISService connectionMERNISService = new SoapConnectionMERNISService();

            HashMap<String,Object> returnMap = new HashMap<>();
            ArrayList<String> nodeValueList = new ArrayList<>();

            returnMap = connectionMERNISService.callSoapWebService(wsURL, soapAction, "soapenv", "tem",
                    "http://tempuri.org/", elements, methodName, nodeValueList,jsonObject);

            nodeValueList = (ArrayList<String>) returnMap.get("nodeValueList");

            jsonObject = (JSONObject) returnMap.get("json");
            TCKisiResponse tcKisiResponse = new TCKisiResponse();
            JSONObject jsonObjectBody = jsonObject.getJSONObject("s:Envelope").getJSONObject("s:Body").getJSONObject("KPS_BilesikKutukSorgulaKimlikNoServisClientResponse").getJSONObject("KPS_BilesikKutukSorgulaKimlikNoServisClientResult");
            JSONObject jsonObjectTCKisi = jsonObjectBody.getJSONObject("a:mBilesikKutukBilgileriSonucu").getJSONObject("b:SorguSonucu").getJSONObject("b:BilesikKutukBilgileri").getJSONObject("b:TCVatandasiKisiKutukleri").getJSONObject("b:KisiBilgisi");
            tcKisiResponse.setHataDetay(jsonObjectBody.getString("a:mHATADETAY"));
            tcKisiResponse.setHataKodu(String.valueOf(jsonObjectBody.get("a:mHATAKOD")));
            JSONObject jsonObjectTemelBilgi = new JSONObject();
            jsonObjectTemelBilgi = jsonObjectTCKisi.getJSONObject("b:TemelBilgisi");
            JSONObject jsonObjectKayitBilgisi =jsonObjectTCKisi.getJSONObject("b:KayitYeriBilgisi");
            mernisTCKisiDTO.setDurum(jsonObjectTCKisi.getJSONObject("b:DurumBilgisi").getJSONObject("b:Durum").getString("b:Aciklama"));
            mernisTCKisiDTO.setMedeniHal(jsonObjectTCKisi.getJSONObject("b:DurumBilgisi").getJSONObject("b:MedeniHal").getString("b:Aciklama"));
            String gun="";
            String ay="";
            String yil ="";
            gun=String.valueOf(jsonObjectTCKisi.getJSONObject("b:DurumBilgisi").getJSONObject("b:OlumTarih").get("b:Gun"));
            ay=String.valueOf(jsonObjectTCKisi.getJSONObject("b:DurumBilgisi").getJSONObject("b:OlumTarih").get("b:Ay"));
            yil=String.valueOf(jsonObjectTCKisi.getJSONObject("b:DurumBilgisi").getJSONObject("b:OlumTarih").get("b:Yil"));

            if(!ay.startsWith("{")&&ay!=null&&ay.length()==1){
                ay="0"+ay;
            }

            if(!gun.startsWith("{")&&gun!=null&&gun.length()==1){
                gun="0"+ay;
            }

            if(!gun.startsWith("{")&&yil!=null){
                mernisTCKisiDTO.setOlumTarihi(gun+"."+ay+"."+yil);

            }
            else{
                mernisTCKisiDTO.setOlumTarihi("");
            }

            mernisTCKisiDTO.setAd(jsonObjectTemelBilgi.getString("b:Ad"));
            mernisTCKisiDTO.setSoyad(jsonObjectTemelBilgi.getString("b:Soyad"));
            mernisTCKisiDTO.setAnneAd(jsonObjectTemelBilgi.getString("b:AnneAd"));
            mernisTCKisiDTO.setBabaAd(jsonObjectTemelBilgi.getString("b:BabaAd"));
            mernisTCKisiDTO.setDogumYeri(jsonObjectTemelBilgi.getString("b:DogumYer"));
            mernisTCKisiDTO.setCinsiyet(jsonObjectTemelBilgi.getJSONObject("b:Cinsiyet").getString("b:Aciklama"));

            gun=String.valueOf(jsonObjectTemelBilgi.getJSONObject("b:DogumTarih").get("b:Gun"));
            ay=String.valueOf(jsonObjectTemelBilgi.getJSONObject("b:DogumTarih").get("b:Ay"));
            yil=String.valueOf(jsonObjectTemelBilgi.getJSONObject("b:DogumTarih").get("b:Yil"));
            if(ay!=null&&ay.length()==1){
                ay="0"+ay;
            }

            if(gun!=null&&gun.length()==1){
                gun="0"+ay;
            }

            if(yil!=null){
                mernisTCKisiDTO.setDogumTarihi(gun+"."+ay+"."+yil);
            }
            else {
                mernisTCKisiDTO.setDogumTarihi("");
            }


            mernisTCKisiDTO.setAileSiraNo(String.valueOf(jsonObjectKayitBilgisi.get("b:AileSiraNo")));
            mernisTCKisiDTO.setBireySiraNo(String.valueOf(jsonObjectKayitBilgisi.get("b:BireySiraNo")));
            mernisTCKisiDTO.setCilt(jsonObjectKayitBilgisi.getJSONObject("b:Cilt").getString("b:Aciklama"));
            mernisTCKisiDTO.setNufusIl(jsonObjectKayitBilgisi.getJSONObject("b:Il").getString("b:Aciklama"));
            mernisTCKisiDTO.setNufusIlce(jsonObjectKayitBilgisi.getJSONObject("b:Ilce").getString("b:Aciklama"));


            tcKisiResponse.setMernisTCKisiDTO(mernisTCKisiDTO);



            return tcKisiResponse;

        } catch (Exception e) {
            // TODO: handle exception
            log.error(e.getMessage());

            return null;
        }


    }


}
