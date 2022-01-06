package com.turkai.consume.DAO;

import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DBModel.OtonomSHSKMLTML;
import com.turkai.consume.DBModel.OtonomSHSRSMTML;
import com.turkai.consume.DTO.IDB.IDBSahisDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;
import com.turkai.consume.repository.CityRepository;
import com.turkai.consume.services.DBConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OtonomSHSDAO implements IOtonomSHSDAO {

   /* @Autowired
    JNDDBConfiguration jnddbConfiguration;*/

    @Autowired
    CityRepository cityRepository;

    @Override
    public OtonomSHSKMLTML getSahisTemel(long tc, JNDDBConfiguration jnddbConfiguration) {

        try {
            Connection conn;
            conn = DBConnectionService.getConnection(jnddbConfiguration);


            Class<OtonomSHSKMLTML> genericFields = OtonomSHSKMLTML.class;

            OtonomSHSKMLTML sahisKimlikTemel = new OtonomSHSKMLTML();

            Field[] fields = genericFields.getDeclaredFields();

            String SQL = "select * from otonom.SAHIS.dbo.OTONOM_SAHIS_SHSKMLTML where KimlikNo=?";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, tc);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (rs.getObject(field.getName()) != null) {
                        field.set(sahisKimlikTemel, rs.getObject(field.getName()));
                    }
                }
            }


            return sahisKimlikTemel;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public OtonomSHSRSMTML getSahisTemelResim(long tc, JNDDBConfiguration jnddbConfiguration) {
        try {
            Connection conn;
            conn = DBConnectionService.getConnection(jnddbConfiguration);


            Class<OtonomSHSRSMTML> genericFields = OtonomSHSRSMTML.class;

            OtonomSHSRSMTML sahisKimlikTemelResim = new OtonomSHSRSMTML();

            Field[] fields = genericFields.getDeclaredFields();

            String SQL = "select * from otonom.SAHIS.dbo.OTONOM_SAHIS_SHSRSMTML where KimlikNo=?";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, tc);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (rs.getObject(field.getName()) != null) {
                        field.set(sahisKimlikTemelResim, rs.getObject(field.getName()));
                    }
                }
            }


            return sahisKimlikTemelResim;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    @Async
    public CompletableFuture<List<OtonomKIHBISahisSucLog>> getKihbilogs(long tc, JNDDBConfiguration jnddbConfiguration) {

        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("kihbi-start " + timestamp.toString());
            Connection conn;
            conn = DBConnectionService.getConnection(jnddbConfiguration);


            Class<OtonomKIHBISahisSucLog> genericFields = OtonomKIHBISahisSucLog.class;

            List<OtonomKIHBISahisSucLog> otonomKIHBISahisSucLogs = new ArrayList<>();


            Field[] fields = genericFields.getDeclaredFields();

            String SQL = "select * from otonom.KIHBI.dbo.OTONOM_KIHBI_SHSSUCLOG where KIMLIKNO=?";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, tc);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                OtonomKIHBISahisSucLog sahisKihbi = new OtonomKIHBISahisSucLog();
                sahisKihbi.setKimlikNo(rs.getObject("KIMLIKNO").toString());
                sahisKihbi.setSucKodAck((String) getObjectValue(rs.getObject("SUCKODACK")));
                sahisKihbi.setOrgutKodAck((String) getObjectValue(rs.getObject("ORGUTKODACK")));
                sahisKihbi.setGvnSrmAck((String) getObjectValue(rs.getObject("GVNSRMACK")));
                sahisKihbi.setSucIlKod((String) getObjectValue(rs.getObject("SUCILKOD").toString()));
                sahisKihbi.setSucIlceKod((String) getObjectValue(rs.getObject("SUCILCKOD").toString()));
                sahisKihbi.setSucIlceAdi((String) getObjectValue(rs.getObject("SUCILCADI")));
                sahisKihbi.setSucMahKoy((String) getObjectValue(rs.getObject("SUCMAHKOY")));
                sahisKihbi.setSucTarihi((String) getObjectValue(rs.getObject("SUCTARIHI").toString()));
                sahisKihbi.setSonDrmAck((String) getObjectValue(rs.getObject("SONDRMACK")));
                sahisKihbi.setTkpDsyNo((String) getObjectValue(rs.getObject("TKPDSYNO").toString()));

                if(sahisKihbi.getSucIlKod().equals("0") || sahisKihbi.getSucIlKod().equals("-")){
                    sahisKihbi.setSucIlAdi("-");
                }
                else {

                    sahisKihbi.setSucIlAdi(cityRepository.findByCountryIdAndPlateNo(212, sahisKihbi.getSucIlKod()).getCityName());

                }


                otonomKIHBISahisSucLogs.add(sahisKihbi);
            }


            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("kihbi-end " + timestamp.toString());

            return CompletableFuture.completedFuture(otonomKIHBISahisSucLogs);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    @Override
    @Async
    public CompletableFuture<List<OtonomBLGTPLAKTUyapKayitLog>> getUyaplogs(long tc, JNDDBConfiguration jnddbConfiguration) {

        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("uyap-start " + timestamp.toString());
            Connection conn;
            conn = DBConnectionService.getConnection(jnddbConfiguration);


            List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogs = new ArrayList<>();


            String SQL = "select * from otonom.BLGTPLAKT.dbo.OTONOM_BLGTPLAKT_UYAPKAYITLOG where TcKimlikno=?";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, tc);

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                OtonomBLGTPLAKTUyapKayitLog uyapKayitLog = new OtonomBLGTPLAKTUyapKayitLog();

                uyapKayitLog.setTcKimlikNo(rs.getObject("TcKimlikNo").toString());
                uyapKayitLog.setAranmaTuru(getObjectValue(rs.getObject("AramaTuru")));
                uyapKayitLog.setAyCezasi(getObjectValue(rs.getObject("AyCezasi")));
                uyapKayitLog.setBirimAdi(getObjectValue(rs.getObject("BirimAdi")));
                uyapKayitLog.setDosyaNo(getObjectValue(rs.getObject("DosyaNo")));
                uyapKayitLog.setDosyaTuruAciklama(getObjectValue(rs.getObject("DosyaTuruAciklama")));
                uyapKayitLog.setGunCezasi(getObjectValue(rs.getObject("GunCezasi")));
                uyapKayitLog.setIlgiliKanunMaddesi(getObjectValue(rs.getObject("IlgiliKanunMaddesi")));
                uyapKayitLog.setKararNo(getObjectValue(rs.getObject("KararNo")));
                uyapKayitLog.setOrgut(getObjectValue(rs.getObject("Orgut")));
                uyapKayitLog.setParaCezasi(getObjectValue(rs.getObject("ParaCezasi")));
                uyapKayitLog.setSanikSucBilgisi(getObjectValue(rs.getObject("SanikSucBilgisi")));
                uyapKayitLog.setSonDurumAck(getObjectValue(rs.getObject("SonDurumAck")));
                uyapKayitLog.setSorumluKolluk(getObjectValue(rs.getObject("SorumluKolluk")));
                uyapKayitLog.setSucIlceAdi(getObjectValue(rs.getObject("SucIlceAdi")));
                uyapKayitLog.setSucIlceKodu(getObjectValue(rs.getObject("SucIlceKodu")));
                uyapKayitLog.setSucIlKodu(getObjectValue(rs.getObject("SucIlKodu")));
                uyapKayitLog.setYilCezasi(getObjectValue(rs.getObject("YilCezasi")));
                uyapKayitLog.setSanikSucTarihi(getObjectValue(rs.getObject("SanikSucTarihiDate")));
                if(uyapKayitLog.getSucIlKodu().equals("0") || uyapKayitLog.getSucIlKodu().equals("-")){
                    uyapKayitLog.setSucIlAdi("-");
                }
                else {

                    uyapKayitLog.setSucIlAdi(cityRepository.findByCountryIdAndPlateNo(212, uyapKayitLog.getSucIlKodu()).getCityName());

                }


                uyapKayitLogs.add(uyapKayitLog);
            }


            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("uyap-end " + timestamp.toString());
            return CompletableFuture.completedFuture(uyapKayitLogs);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    public String getObjectValue(Object object) {


        return object != null ? object.toString() : "-";
    }





    @Override
    public IDBSahisDTO isExistIDB(long tc, JNDDBConfiguration jnddbConfiguration){

        try{
            IDBSahisDTO idbSahisDTO = new IDBSahisDTO();
            Connection conn;
            conn = DBConnectionService.getConnection(jnddbConfiguration);


            List<OtonomBLGTPLAKTUyapKayitLog> uyapKayitLogs = new ArrayList<>();


            String SQL = "select count(1) as total from otonom.BLGTPLAKT.dbo.OTONOM_BLGTPLAKT_IDBSAHIS where KIMLIKNU=?";

            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setLong(1, tc);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            idbSahisDTO.setExist(rs.getInt("total") > 0);



            return idbSahisDTO;
        }
        catch (Exception e) {

            return null;

        }








    }


}
