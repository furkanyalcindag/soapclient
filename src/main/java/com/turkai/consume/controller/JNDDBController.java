package com.turkai.consume.controller;

import com.turkai.consume.DAO.IOtonomSHSDAO;
import com.turkai.consume.DBModel.OtonomSHSKMLTML;
import com.turkai.consume.DBModel.OtonomSHSRSMTML;
import com.turkai.consume.configuration.JNDDBConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

@RestController
public class JNDDBController {

    @Autowired
    JNDDBConfiguration jnddbConfiguration;

    @Autowired
    IOtonomSHSDAO contactDAO;


    @GetMapping("/deneme")
    public OtonomSHSKMLTML deneme(@RequestParam long tc) throws SQLException {


        return contactDAO.getSahisTemel(tc, jnddbConfiguration);

    }

    @GetMapping("/get-sahis-resim")
    public OtonomSHSRSMTML getSahisResim(@RequestParam long sahisId) throws SQLException, IOException {

        OtonomSHSRSMTML otonomSHSRSMTML = new OtonomSHSRSMTML();
        otonomSHSRSMTML=contactDAO.getSahisTemelResim(sahisId, jnddbConfiguration);
        //Base64.getEncoder().encode(otonomSHSRSMTML.getResim());
        System.out.println(new String( Base64.getEncoder().encode(otonomSHSRSMTML.getResim())));

        BufferedImage image=null;
        ByteArrayInputStream bis = new ByteArrayInputStream(otonomSHSRSMTML.getResim());
        image=ImageIO.read(bis);
        bis.close();
        return contactDAO.getSahisTemelResim(sahisId, jnddbConfiguration);

    }



}
