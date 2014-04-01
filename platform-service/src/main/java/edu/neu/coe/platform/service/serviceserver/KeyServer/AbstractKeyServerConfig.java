/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.service.serviceserver.KeyServer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import edu.neu.coe.platform.service.util.ConstantUtil;
import edu.neu.coe.platform.service.util.encryption.EncryptionUtilImpl;
import edu.neu.coe.platform.service.util.encryption.IEncryptionUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Cynthia
 */
public abstract class AbstractKeyServerConfig {
    
     private static final IEncryptionUtil helper=new EncryptionUtilImpl();
    
    public IKeyServer defaultKeyServerConfiguration(){
         KeyServer keyserver=null;
         try {
             File fXmlFile = new File("./src/keyserver.xml");
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nlist=doc.getElementsByTagName("keyserver");
            
             if(nlist.getLength()>0){
                 Node node=nlist.item(0);
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) node;
                     String keyservername=element.getElementsByTagName("name").item(0).getTextContent();
                     String platformurl=element.getElementsByTagName("platformurl").item(0).getTextContent();
                     String defaultplatformname=element.getElementsByTagName("defaultplatformname").item(0).getTextContent();
                     String keystorefilespath=element.getElementsByTagName("keystorefilesdirectory").item(0).getTextContent();
                     if((new File(keystorefilespath)).isDirectory()){
                         Scanner scanIn = new Scanner(System.in);
                         String password=null;
                         System.out.println("Enter keyserver adminpassword:");
                         String adminpassword=scanIn.next();
                         NodeList keylist=doc.getElementsByTagName("key");
                         String platformpassword=null;
                         if(keylist.getLength()==0){
                             System.out.println("Enter keyserver password:");
                             password=scanIn.next();
                             System.out.println("Enter default platform password:");
                             platformpassword=scanIn.next();
                             String encryptedpassword=helper.encrypt(helper.generateSecretKey(adminpassword), password);
                            // System.out.println(helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword));
                             Element key=doc.createElement("key");
                             key.appendChild(doc.createTextNode(encryptedpassword));
                             node.appendChild(key);
                             TransformerFactory transformerFactory = TransformerFactory.newInstance();
                             Transformer transformer = transformerFactory.newTransformer();
                             DOMSource source = new DOMSource(doc);
                             StreamResult result = new StreamResult(new File("./src/keyserver.xml"));
                             transformer.transform(source, result);
                         }else {
                             String encryptedpassword=keylist.item(0).getTextContent();
                             password=helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword);
                             
                         }
                         if(!password.equals(ConstantUtil.WRONDKEY)){
                             keyserver=newKeyServer(keyservername, password, adminpassword, platformurl,keystorefilespath,defaultplatformname);
                             if(platformpassword!=null){
                                 keyserver.addPlatform(defaultplatformname, platformpassword, "");
                                 keyserver.saveKeyStore(adminpassword);
                                 System.out.println("How many user do you want to add?");
                                 int num=scanIn.nextInt();
                                 for(int i=0;i<num;i++){
                                     System.out.println("Enter username for "+(i+1)+"th user:");
                                     String username=scanIn.next();
                                     System.out.println("Enter password for "+(i+1)+"th user:");
                                     String pass=scanIn.next();
                                     keyserver.addOrUpdateUser(username, pass);
                                 }
                                 System.out.println("How many device do you want to add?");
                                 num=scanIn.nextInt();
                                 for(int i=0;i<num;i++){
                                     System.out.println("Enter deviceid for "+(i+1)+"th device:");
                                     String deviceid=scanIn.next();
                                     System.out.println("Enter password for "+(i+1)+"th device:");
                                     String pass=scanIn.next();
                                     keyserver.addOrUpdataDevice(deviceid, pass);
                                 }
                                 System.out.println("How many service do you want to add?");
                                 num=scanIn.nextInt();
                                 for(int i=0;i<num;i++){
                                     System.out.println("Enter servicename for "+(i+1)+"th service:");
                                     String servicename=scanIn.next();
                                     System.out.println("Enter password for "+(i+1)+"th service:");
                                     String pass=scanIn.next();
                                     keyserver.addOrUpdateService(servicename, pass);
                                 }
                                 keyserver.saveKeyStore(adminpassword);
                             }
                             NodeList platformlist=doc.getElementsByTagName("platform");
                             for(int i=0;i<platformlist.getLength();i++){
                                 Node platformnode=platformlist.item(i);
                                 if(platformnode.getNodeType()==Node.ELEMENT_NODE){
                                     Element platform=(Element)platformnode;
                                     String platformname=platform.getElementsByTagName("name").item(0).getTextContent();
                                     String privilege=platform.getElementsByTagName("privilege").item(0).getTextContent();
                                     keyserver.addPlatformPrivilege(platformname, privilege);
                                 }
                             }
                            
                         }
                     }else System.out.println("Invalid directory for keystore!!");
                 }
             }
            } catch ( ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(AbstractKeyServerConfig.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerConfigurationException ex) {
             Logger.getLogger(AbstractKeyServerConfig.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerException ex) {
             Logger.getLogger(AbstractKeyServerConfig.class.getName()).log(Level.SEVERE, null, ex);
         }  
             
             return keyserver;
        
    }

    protected abstract KeyServer newKeyServer(String keyservername, String password, String adminpassword, String platformurl, String keystorefilespath, String defaultplatformname);
    
}
