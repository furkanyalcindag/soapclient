package com.turkai.consume.services;


import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class SoapConnectionKullaniciService {


    public void createSoapEnvelope(SOAPMessage soapMessage, String prefix, String namespace, String namespaceUri, LinkedHashMap<String,String> childElements, String methodName) throws SOAPException {
        soapMessage.getSOAPHeader().setPrefix(prefix);
        soapMessage.getSOAPBody().setPrefix(prefix);

        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        // envelope.addNamespaceDeclaration("tem","http://tempuri.org/")
        envelope.addNamespaceDeclaration(namespace, namespaceUri);
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

        for(Map.Entry<String,String > entry : childElements.entrySet()){

            SOAPElement soapBodyElement = soapBodyElem.addChildElement(entry.getKey(), namespace);
            soapBodyElement.addTextNode(entry.getValue().toString());

        }



    }

    /**
     * @param soapEndpointUrl "http://www.dneonline.com/calculator.asmx"
     * @param soapAction      "http://tempuri.org/Add"
     * @param prefix          "soapenv", "soap", "SOAP-ENV"
     * @param namespace       tem
     * @param namespaceUri    http://tempuri.org/
     */
    public ArrayList<String> callSoapWebService(String soapEndpointUrl, String soapAction, String prefix, String namespace, String namespaceUri, LinkedHashMap<String,String> childElements, String methodName, ArrayList<String> nodeValueList) throws IOException, SOAPException, ParserConfigurationException, SAXException {

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
                    nodeValueList.add(node.getNodeName()+"->"+node.getValue());
                    System.out.println("asas"+nodeValueList.get(nodeValueList.size()-1));
                }
            }
            soapResponse.writeTo(System.out);
            soapConnection.close();
            return nodeValueList;






        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
            return null;
        }
    }

    private SOAPMessage createSOAPRequest(String soapAction, String prefix, String namespace, String namespaceUri, LinkedHashMap<String,String> childElements, String methodName) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        createSoapEnvelope(soapMessage, prefix, namespace, namespaceUri, childElements, methodName);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
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
