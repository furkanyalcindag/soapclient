package com.turkai.consume;

import com.turkai.consume.helper.SoapHelper;
import com.turkai.consume.soapModels.JAXBElement.JAXBElementMixin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.xml.sax.SAXException;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@EnableOpenApi
public class ConsumeApplication {


    public static void main(String[] args) throws SAXException, ParserConfigurationException, SOAPException, IOException, SQLException {


        SpringApplication.run(ConsumeApplication.class, args);

        /*KullaniciWSClientService a = new KullaniciWSClientService();
        a.projectUserList("12232","212121","1212121");*/
     /*   String soapEndpointUrl = "https://www.ulker.com.tr/webservices/contactservice/contactservice.asmx?WSDL";
        String soapAction = "http://tempuri.org/getIlcelerByIlId";

        //callSoapWebService(soapEndpointUrl, soapAction);

        SoapService soapService = new SoapService();

        List<SoapChildElement> childElements = new ArrayList<>();


        SoapChildElement soapChildElement2 = new SoapChildElement();
        soapChildElement2.setKey("ilId");
        soapChildElement2.setValue("44");




        soapService.callSoapWebService(soapEndpointUrl, soapAction, "soapenv", "tem", "http://tempuri.org/", childElements, "getIlcelerByIlId");



*/

    }


    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {

        Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
        mapperBuilder.indentOutput(true).mixIn(JAXBElement.class, JAXBElementMixin.class);
        return mapperBuilder;
    }


}
