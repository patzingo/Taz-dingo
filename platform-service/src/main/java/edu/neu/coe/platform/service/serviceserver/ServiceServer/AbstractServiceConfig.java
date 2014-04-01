/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.service.serviceserver.ServiceServer;

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
public abstract class AbstractServiceConfig {
    
     private static final IEncryptionUtil helper=new EncryptionUtilImpl();
    
    public IServiceServer defaultServiceServerConfiguration(){
        ServiceServer serviceserver=null;
         try {
             
             File fXmlFile = new File("./src/service.xml");
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nlist=doc.getElementsByTagName("service");
             
             if(nlist.getLength()>0){
                 Node node=nlist.item(0);
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     Element element = (Element) node;
                     String servicename=element.getElementsByTagName("name").item(0).getTextContent();
                     String platformurl=element.getElementsByTagName("platformurl").item(0).getTextContent();
                     Scanner scanIn = new Scanner(System.in);
                     String password=null;
                     System.out.println("Enter service adminpassword:");
                     String adminpassword=scanIn.next();
                     NodeList keylist=doc.getElementsByTagName("key");
                     if(keylist.getLength()==0){
                         System.out.println("Enter password:");
                         password=scanIn.next();
                         String encryptedpassword=helper.encrypt(helper.generateSecretKey(adminpassword), password);
                         System.out.println(helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword));
                         Element key=doc.createElement("key");
                         key.appendChild(doc.createTextNode(encryptedpassword));
                         node.appendChild(key);
                         TransformerFactory transformerFactory = TransformerFactory.newInstance();
                         Transformer transformer = transformerFactory.newTransformer();
                         DOMSource source = new DOMSource(doc);
                         StreamResult result = new StreamResult(new File("./src/service.xml"));
                         transformer.transform(source, result);
                     }else {
                         String encryptedpassword=keylist.item(0).getTextContent();
                         password=helper.decrypt(helper.generateSecretKey(adminpassword), encryptedpassword);
                         
                     }
                     if(!password.equals(ConstantUtil.WRONDKEY)){
                         serviceserver=newServiceServer(servicename, password, adminpassword, platformurl);
                         
                         
                     }
                     
                 }
             }
              } catch ( ParserConfigurationException | SAXException | IOException ex) {
             Logger.getLogger(AbstractServiceConfig.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerConfigurationException ex) {
             Logger.getLogger(AbstractServiceConfig.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerException ex) {
             Logger.getLogger(AbstractServiceConfig.class.getName()).log(Level.SEVERE, null, ex);
         }
             return serviceserver;
        
    }

    protected abstract ServiceServer newServiceServer(String servicename, String password, String adminpassword, String platformurl);
    
}
