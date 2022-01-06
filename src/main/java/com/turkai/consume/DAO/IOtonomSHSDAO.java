package com.turkai.consume.DAO;

import com.turkai.consume.DBModel.OtonomBLGTPLAKTUyapKayitLog;
import com.turkai.consume.DBModel.OtonomKIHBISahisSucLog;
import com.turkai.consume.DBModel.OtonomSHSKMLTML;
import com.turkai.consume.DBModel.OtonomSHSRSMTML;
import com.turkai.consume.DTO.IDB.IDBSahisDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOtonomSHSDAO {

    OtonomSHSKMLTML getSahisTemel(long tc, JNDDBConfiguration jnddbConfiguration);

    OtonomSHSRSMTML getSahisTemelResim(long sahisId, JNDDBConfiguration jnddbConfiguration);


    CompletableFuture<List<OtonomKIHBISahisSucLog>> getKihbilogs(long tc, JNDDBConfiguration jnddbConfiguration);
    CompletableFuture<List<OtonomBLGTPLAKTUyapKayitLog>> getUyaplogs(long tc, JNDDBConfiguration jnddbConfiguration);
    IDBSahisDTO isExistIDB(long tc, JNDDBConfiguration jnddbConfiguration);



}
