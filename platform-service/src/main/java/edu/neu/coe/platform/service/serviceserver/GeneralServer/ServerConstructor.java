/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.service.serviceserver.GeneralServer;

/**
 *
 * @author Cynthia
 */
public class ServerConstructor {
    private String serverid;
    private String serverpassword;

    public ServerConstructor(String serverid, String serverpassword) {
        this.serverid = serverid;
        this.serverpassword = serverpassword;
    }

    
    
    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }

    public String getServerpassword() {
        return serverpassword;
    }

    public void setServerpassword(String serverpassword) {
        this.serverpassword = serverpassword;
    }
}
