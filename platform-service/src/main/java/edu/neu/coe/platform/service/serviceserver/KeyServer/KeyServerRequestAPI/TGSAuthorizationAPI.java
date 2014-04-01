/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.serviceserver.KeyServer.KeyServerRequestAPI;

import edu.neu.coe.platform.service.util.ConstantUtil;
import java.util.HashMap;
import java.util.Map;
import edu.neu.coe.platform.service.platform.Request;

/**
 *
 * @author Cynthia
 */
public class TGSAuthorizationAPI {
    
   public static Request createPlatformAuthorizationRequest(String platformname){
        if(platformname==null || platformname.isEmpty() ){
            return null;
        }
        Request request=new Request();
        Map<String,String> data=new HashMap<>();
        data.put(ConstantUtil.PLATFORM_NAME, platformname);
        data.put(ConstantUtil.REQUEST_TYPE, ConstantUtil.PLATFORM_AUTHORIZATION_REQUEST);
        request.setData(data);
        return request;
        
    }
   
  
    
}
