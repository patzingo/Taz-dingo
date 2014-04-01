/* 
 *To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.service.serviceserver.KeyServer;

import edu.neu.coe.platform.service.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class StartKeyServer {
    
    public static void main(String[] arg){
        KeyServerConfig config=new KeyServerConfig();
        IKeyServer keyserver=config.defaultKeyServerConfiguration();
        System.out.println(keyserver);
//        keyserver.addOrUpdateService("service", "123");
//        keyserver.addOrUpdataDevice("device", "123");
//        keyserver.addOrUpdateUser("user", "123");
//        keyserver.saveKeyStore("321");
//        System.out.println(((KeyServer)keyserver).getKey(ConstantUtil.SERVICEPASSWORD, "service"));
//          System.out.println(((KeyServer)keyserver).getKey(ConstantUtil.PLATFORM, "platform"));
//          System.out.println(((KeyServer)keyserver).getPlatformPassword("platform"));
    }
    
   
    
    
    
}
