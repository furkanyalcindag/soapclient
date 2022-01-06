package com.turkai.consume.services;

import com.turkai.consume.configuration.JNDDBConfiguration;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DBConnectionService {


    static Connection conn = null;

    public static Connection getConnection(JNDDBConfiguration dbConfiguration) throws SQLException {

        if (conn != null) {
            return conn;
        } else {
            String jdbcUrl = dbConfiguration.getUrl()+"user=" + dbConfiguration.getUsername() +";password=" +dbConfiguration.getPassword();

            System.out.println(jdbcUrl);
            return DriverManager.getConnection(jdbcUrl);
        }


    }


}
