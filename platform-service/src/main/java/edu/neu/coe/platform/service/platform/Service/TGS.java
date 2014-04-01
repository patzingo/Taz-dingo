/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.Service;

import edu.neu.coe.platform.service.util.ConstantUtil;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import edu.neu.coe.platform.service.platform.Response;
import edu.neu.coe.platform.service.platform.ticket.TGT;
import edu.neu.coe.platform.service.platform.ticket.ServiceTicket;
import edu.neu.coe.platform.service.platform.workrequest.WorkRequest;
import edu.neu.coe.platform.service.util.encryption.EncryptionUtilImpl;
import edu.neu.coe.platform.service.util.encryption.IEncryptionUtil;

/**
 *
 * @author Cynthia
 */
public abstract class TGS extends Service{
    
    public static IEncryptionUtil helper=new EncryptionUtilImpl();

    /**
     * Method to execute request and return response
     * @param sessionid
     * @return 
     */
    @Override
    public Response excuteRequest(String sessionid) {
        Response response=new Response();
        System.out.println("TGS received request");
        Map<String,String> responsedata=new HashMap<>();
        WorkRequest request=super.workqueue.pullWorkRequest(sessionid);

        Map<String,String> data=request.getRequest().getData();
        String ticketstring=data.get(ConstantUtil.TICKET);
        TGT ticket = new TGT();
        ticket.setTicket(ticketstring);
         String type=data.get(ConstantUtil.REQUEST_TYPE);
        String identifier=getIdentifier(data,type);
        if(checkTicket(ticket, identifier, sessionid)){

            ServiceTicket tgt=generateTicket(getPrivilege(identifier, type), ticket,getStepid(request));
            SecretKey masterkey=getMasterKey();
            //System.out.println(tgt.converToString());
            System.out.println("encrypted ticket with key:"+masterkey);
            String encryptedtgt=helper.encrypt(masterkey, tgt.converToString());
           // System.out.println(encryptedtgt);
            responsedata.put(ConstantUtil.TICKET,encryptedtgt);
            responsedata=addAdditionalData(responsedata,request);
            SecretKey shortkey=getShortTermKey(request);
            System.out.println("encrypted data with key:"+shortkey);
            responsedata=helper.encrypt(shortkey, responsedata);
            responsedata.put(ConstantUtil.ERROR, ConstantUtil.NO_ERROR);
            System.out.println("Authorizate Successfully");
           
        }else responsedata.put(ConstantUtil.ERROR, ConstantUtil.INVALID_KDC_TICKET);
        response.setData(responsedata);
        return response;
    }
    
    /**
     * Method to add additional data to response
     * @param data
     * @param request
     * @return 
     */
    protected abstract Map<String,String> addAdditionalData(Map<String,String> data,WorkRequest request);
    
    /**
     * Method to get the masterkey
     * @return 
     */
    protected abstract SecretKey getMasterKey();
    
    /**
     * Method to get short term key /old stepid
     * @param request
     * @return 
     */
    protected abstract SecretKey getShortTermKey(WorkRequest request);
    /**
     * Method to get account identification
     * @param data
     * @param type
     * @return 
     */
    protected abstract String getIdentifier(Map<String,String>  data,String type);
    
    /**
     * Method to get privilege of an account
     * @param identifier
     * @param type
     * @return 
     */
    protected abstract String getPrivilege(String identifier,String type);
    
    private boolean checkTicket(TGT ticket,String identifier,String sessionid){
        if(ticket.getAuthenticator().equals(identifier))
        if(ticket.getSessionID().equals(sessionid))
        if(System.currentTimeMillis()-ticket.getExpiredTime()<0)
            return true;
        return false;
    }
    
    private ServiceTicket generateTicket(String Privilege,TGT ticket,String stepid){
        
        return new ServiceTicket(Privilege, ticket,stepid);
    }
    /**
     * Method to get new stepid/new temporary key
     * @param request
     * @return 
     */
    protected abstract String getStepid(WorkRequest request);
    
    
}
