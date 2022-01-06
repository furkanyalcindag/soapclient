package com.turkai.consume.services.serviceInterface;

import com.turkai.consume.DTO.IDB.IDBSahisDTO;
import com.turkai.consume.configuration.JNDDBConfiguration;

public interface IIDBService {

    IDBSahisDTO isExistIDBSahis(long tc, JNDDBConfiguration jnddbConfiguration);
}
