/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.platform.ticket;

import edu.neu.coe.platform.service.util.ConstantUtil;



/**
 *
 * @author Cynthia
 */
public class TGT {
    
    private String sessionID;
    protected Long expiredTime;
    private Long createdTime;
    protected String authenticator; 

    public TGT() {
    } 
    
    public TGT(String sessionid){
        createdTime=System.currentTimeMillis();
        sessionID=sessionid;
    }

    public String getSessionID() {
        return sessionID;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public String getAuthenticator() {
        return authenticator;
    }
    
    public String converToString(){
        return sessionID+ConstantUtil.DELIMITER+expiredTime+ConstantUtil.DELIMITER+createdTime+ConstantUtil.DELIMITER+authenticator;
    }
    
    public void setTicket(String ticket){
        String[] value=ticket.split(ConstantUtil.DELIMITER);
        //System.out.println(System.currentTimeMillis());
        sessionID=value[0];
        expiredTime=Long.parseLong(value[1]);
        createdTime=Long.parseLong(value[2]);
        authenticator=value[3];
        
        
    }
    
    
}
