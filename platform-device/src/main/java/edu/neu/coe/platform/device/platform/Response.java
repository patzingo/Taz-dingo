/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.device.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import edu.neu.coe.platform.device.platform.util.ConstantUtil;
import org.apache.http.HttpResponse;

/**
 *
 * @author Cynthia
 */
public class Response {
    
   private Map<String,String> data;

   public Response(){
       data=new HashMap<>();
   }
   
   /**
    * 
    * @param response 
    */
    public Response(HttpResponse response){
        BufferedReader rd = null;
       try {
           data=new HashMap<>();
           rd = new BufferedReader(
                   new InputStreamReader(response.getEntity().getContent()));
           String line = "";
           String prekey="";
           while ((line = rd.readLine()) != null) {
               //System.out.println(line);
               String[] pair=line.split(ConstantUtil.DOUBLEDELIMITER);
               if(pair.length==2){
               data.put(pair[0], pair[1]);
               prekey=pair[0];
               }else if(pair.length==1){
                   String value=data.get(prekey);
                   data.put(prekey, value+pair[0]);
               }
           }
       } catch (IOException | IllegalStateException ex) {
           Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
           try {
               rd.close();
           } catch (IOException ex) {
               Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
    /**
     * Method to return the response data map
     * @return 
     */
    public Map<String, String> getData() {
        return data;
    }
    
    /**
     * Method to set the response data map
     * @param data 
     */

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    /**
     * Method to convert response to HttpSservletResponse;
     * @param response
     * @return 
     */
    
    public HttpServletResponse convertToHttpResponse(HttpServletResponse response){
        Iterator iterator = data.entrySet().iterator();
	while (iterator.hasNext()) {
            try {
                Map.Entry<String,String> mapEntry = (Map.Entry) iterator.next();
                response.addHeader(mapEntry.getKey(), mapEntry.getValue());
                response.getWriter().println(mapEntry.getKey()+ConstantUtil.DOUBLEDELIMITER+mapEntry.getValue());
            } catch (IOException ex) {
                Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
       return response;
        
    }
    
}
