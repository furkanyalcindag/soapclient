package com.turkai.consume.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


import com.turkai.consume.helper.SoapHelper;
import org.json.JSONObject;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.turkai.consume.soapModels.RequestModel.KullaniciBilgi;

public class SoapConnectionMERNISService {
	
	
    public void createSoapEnvelope(SOAPMessage soapMessage, String prefix, String namespace, String namespaceUri, LinkedHashMap<String, String> childElements, String methodName) throws SOAPException {
        soapMessage.getSOAPHeader().setPrefix(prefix);
        soapMessage.getSOAPBody().setPrefix(prefix);

        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        // envelope.addNamespaceDeclaration("tem","http://tempuri.org/")
        envelope.addNamespaceDeclaration(namespace, namespaceUri);
        envelope.addNamespaceDeclaration("mer", "http://schemas.datacontract.org/2004/07/MERNISServis");
        envelope.addNamespaceDeclaration("arr", "http://schemas.microsoft.com/2003/10/Serialization/Arrays");
        envelope.setPrefix("soapenv");
        envelope.removeNamespaceDeclaration(envelope.getPrefix());
            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement soapBodyElem = soapBody.addChildElement(methodName, namespace);


        SOAPElement soapKLN = soapBodyElem.addChildElement("pKullaniciBilgi", namespace);

        Class<KullaniciBilgi> genericFields = KullaniciBilgi.class;

        Field[] fields = genericFields.getDeclaredFields();


        for (Field field : fields) {


            for (Map.Entry<String, String> entry : childElements.entrySet()) {

                if (("m"+field.getName()).equals(entry.getKey())) {
                    SOAPElement soapBodyElementKln = soapKLN.addChildElement(entry.getKey(), "mer");
                    soapBodyElementKln.addTextNode(entry.getValue().toString());
                }


            }

        }

            SOAPElement tcBodyELementArr = soapBodyElem.addChildElement("pKmlNmrLst", namespace);
            SOAPElement tcBodyELement = tcBodyELementArr.addChildElement("long", "arr");
            tcBodyELement.addTextNode(childElements.get("long").toString());

    }


    public HashMap<String,Object> callSoapWebService(String soapEndpointUrl, String soapAction, String prefix, String namespace, String namespaceUri, LinkedHashMap<String, String> childElements, String methodName, ArrayList<String> nodeValueList,JSONObject jsonObject) throws IOException, SOAPException, ParserConfigurationException, SAXException {

        HashMap<String,Object> returnMap = new HashMap<>();
        HashMap<String, String> responseMap = new HashMap<>();
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, prefix, namespace, namespaceUri, childElements, methodName), soapEndpointUrl);

            SOAPBody elementBody = soapResponse.getSOAPPart().getEnvelope().getBody();

            //elementBody.getElementsByTagName("*:SubtractResult").item(0).getChildNodes().item(0)

            //System.out.println(soapResponse.getSOAPBody().getElementsByTagNameNS("*", "SubtractResult"));
            // Print the SOAP Response
            System.out.println("Response SOAP Message:");

            //soapResponse.writeTo(System.out);


            NodeList nodeList = elementBody.getElementsByTagName("*");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = (Node) nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // do something with the current element
                    System.err.println(node.getNodeName() + " :" + node.getValue());
                    responseMap.put(node.getNodeName(), node.getValue());
                    nodeValueList.add(node.getNodeName() + "->" + node.getValue());
                    System.out.println("asas" + nodeValueList.get(nodeValueList.size() - 1));
                }
            }
            soapResponse.writeTo(System.out);
            jsonObject = org.json.XML.toJSONObject(SoapHelper.soapMessageToString(soapResponse));
            System.out.println("-------------------------------");
            System.out.println(jsonObject.toString());
            soapConnection.close();
            returnMap.put("nodeValueList",nodeValueList);
            returnMap.put("json",jsonObject);
            return returnMap;


        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
            return null;
        }
    }

    private SOAPMessage createSOAPRequest(String soapAction, String prefix, String namespace, String namespaceUri, LinkedHashMap<String, String> childElements, String methodName) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        createSoapEnvelope(soapMessage, prefix, namespace, namespaceUri, childElements, methodName);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction+methodName);
        //headers.addHeader("SOAPAction", "\"\"");

        //headers.addHeader("Content-Type", "text/xml; charset=utf-8");
        //headers.addHeader("Content-Length", "10000");

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }
	
	
	
	
	
	
	
	
	

}
