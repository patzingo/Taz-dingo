/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.device.platform.device;

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
import edu.neu.coe.platform.device.platform.util.ConstantUtil;
import edu.neu.coe.platform.device.platform.util.encryption.EncryptionUtilImpl;
import edu.neu.coe.platform.device.platform.util.encryption.IEncryptionUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Cynthia
 * 
 */
public abstract class AbstractDeviceConfiguration {
    
     private static final IEncryptionUtil helper=new EncryptionUtilImpl();
     
     /*
     Method to configure the device
     */
     
    public IDevice defaultDeviceConfiguration(){
        IDevice device=null;
         try {
             
             File fXmlFile = new File("./src/device.xml");
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nlist=doc.getElementsByTagName("device");
             
             if(nlist.getLength()>0){
                 Node node=nlist.item(0);
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) node;
                     String deviceid=element.getElementsByTagName("id").item(0).getTextContent();
                     String platformurl=element.getElementsByTagName("platformurl").item(0).getTextContent();
                     Scanner scanIn = new Scanner(System.in);
                     String password=null;
                     String adminpassword=null;
                     NodeList keylist=doc.getElementsByTagName("key");
                     if(keylist.getLength()==0){
                         System.out.println("Enter device adminpassword:");
                         adminpassword=scanIn.next();
                         System.out.println("Enter local password:");
                         password=scanIn.next();
                         String encryptedpassword=helper.encrypt(helper.generateSecretKey(adminpassword), password);
                         System.out.println(helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword));
                         Element key=doc.createElement("key");
                         key.appendChild(doc.createTextNode(encryptedpassword));
                         node.appendChild(key);
                         TransformerFactory transformerFactory = TransformerFactory.newInstance();
                         Transformer transformer = transformerFactory.newTransformer();
                         DOMSource source = new DOMSource(doc);
                         StreamResult result = new StreamResult(new File("./src/device.xml"));
                         transformer.transform(source, result);
                     }else {
                         String encryptedpassword=keylist.item(0).getTextContent();
                         if(adminpassword!=null)
                         password=helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword);
                         else password=encryptedpassword;
                         
                     }
                     
                     if(!password.equals(ConstantUtil.WRONDKEY)){
                         if(adminpassword!=null)
                         device=newDevice(deviceid, password, adminpassword, platformurl);
                         else device=newDevice(deviceid, password, platformurl);
                         if(device.getDeviceticket()==null){
                             if(adminpassword==null){
                         System.out.println("Enter device adminpassword:");
                         adminpassword=scanIn.next();
                             }
                         String response= device.deviceLogin(adminpassword);
                         if(response.equals(ConstantUtil.SUCCESS_LOGIN)){
                             device.deviceAuthorization();
                         }
                         }
                     }
                     
                 }
             }
         } catch ( ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(AbstractDeviceConfiguration.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerConfigurationException ex) {
             Logger.getLogger(AbstractDeviceConfiguration.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerException ex) {
             Logger.getLogger(AbstractDeviceConfiguration.class.getName()).log(Level.SEVERE, null, ex);
         }
             return device;
         
    }

    protected abstract IDevice newDevice(String deviceid, String password, String adminpassword, String platformurl);

    protected abstract IDevice newDevice(String deviceid, String password, String platformurl);
    
    
    
    
    
    
}
