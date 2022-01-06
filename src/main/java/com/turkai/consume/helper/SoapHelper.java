package com.turkai.consume.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SoapHelper {

    private final static Logger log = LoggerFactory.getLogger(SoapHelper.class);


    public static String soapMessageToString(SOAPMessage message) {

        String result = null;

        if (message != null) {

            ByteArrayOutputStream baos = null;

            try {
                baos = new ByteArrayOutputStream();
                message.writeTo(baos);
                result = baos.toString();
            } catch (Exception e) {
                log.error(e.getMessage());

            } finally {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }


        }
        return result;


    }

    public static Node getValueFromXML(String xml, String attribute) {

        Node node =null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new String(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile(attribute);
            node = (Node) expr.evaluate(doc, XPathConstants.NODESET);
            return node;


        } catch (Exception e) {

            log.error(e.getMessage());
            return null;

        }


    }



    public static Document loadXMLString(String response){

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));
            return db.parse(is);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }


    }


    public static List<String> getValueFromXml(String response, String tag){


        try{
            Document xmlDoc = loadXMLString(response);
            NodeList nodeList = xmlDoc.getElementsByTagNameNS("b",tag);
            List<String> list = new ArrayList<>(nodeList.getLength());
            for(int i=0;i<nodeList.getLength();i++){

                Node x = (Node) nodeList.item(i);
                list.add(x.getFirstChild().getNodeValue());



            }
            return list;

        }
        catch (Exception e){

            return null;



        }

    }

}
