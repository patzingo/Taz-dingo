/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Cynthia
 */
public class Request {
    
    private Map<String,String> data;
    
   public Request(HttpServletRequest request){
       data=new HashMap<>();
      Enumeration<String> enu= request.getParameterNames();
      while(enu.hasMoreElements()){
          String key=enu.nextElement();
          data.put(key,request.getParameter(key));
      }
   }
   
   public Request(){
       data=new HashMap<>();
   };

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    public List<NameValuePair> getParameterPair(){
        List<NameValuePair> pairs=new ArrayList<NameValuePair>();
        Iterator iterator=data.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry=(Map.Entry) iterator.next();
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            
        }
        return pairs;
        
    }
    
}
