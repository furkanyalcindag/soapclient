package com.turkai.consume.services;

import com.turkai.consume.DAO.IOtonomSHSDAO;
import com.turkai.consume.DTO.IDB.IDBSahisDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;
import com.turkai.consume.services.serviceInterface.IIDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDBService implements IIDBService {

    @Autowired
    IOtonomSHSDAO otonomSHSDAO;

    @Override
    public IDBSahisDTO isExistIDBSahis(long tc, JNDDBConfiguration jnddbConfiguration){

        try {
            IDBSahisDTO idbSahisDTO = new IDBSahisDTO();

            idbSahisDTO = otonomSHSDAO.isExistIDB(tc,jnddbConfiguration);

            return idbSahisDTO;

        }
        catch (Exception e){

            return null;

        }

    }



}
