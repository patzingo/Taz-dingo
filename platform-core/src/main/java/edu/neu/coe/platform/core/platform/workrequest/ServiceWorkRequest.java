package edu.neu.coe.platform.core.platform.workrequest;

import edu.neu.coe.platform.core.platform.Request;

/**
 *
 * @author Cynthia
 */
public class ServiceWorkRequest extends WorkRequest {

    private String sessionid;
    private String tempkey;

    public ServiceWorkRequest(String sessionid, Request request) {
        super(request);
        this.sessionid = sessionid;
    }

    @Override
    public String getSessionid() {
        return sessionid;
    }

    public String getTempkey() {
        return tempkey;
    }

    public void setTempkey(String tempkey) {
        this.tempkey = tempkey;
    }
    
}
