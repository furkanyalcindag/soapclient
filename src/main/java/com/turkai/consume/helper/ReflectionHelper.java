package com.turkai.consume.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.turkai.consume.soapServices.KullaniciWSClientService;

public class ReflectionHelper {


	private final static Logger log = LoggerFactory.getLogger(ReflectionHelper.class);

    public static boolean containsField(Class<?> clazz,String fieldname){

        try {
            clazz.getDeclaredField(fieldname);
            return true;
        } catch (NoSuchFieldException e) {
            //log.error("projectRemoveUser",e);
            return false;
        }


    }


}
