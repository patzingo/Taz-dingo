package edu.neu.coe.platform.core.platform.Session;

/**
 *
 * @author Cynthia
 */
public interface ISession {

    public String getSessionID();

    public String getStepID();

    public Long getCreatedTime();

    public Long getExpiredTime();

    public String getRequestSender();

    public void disableSession();

    public void extendExpiredTime();
    
}
