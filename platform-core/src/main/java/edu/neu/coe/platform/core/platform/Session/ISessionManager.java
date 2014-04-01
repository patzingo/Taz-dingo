package edu.neu.coe.platform.core.platform.Session;

/**
 *
 * @author Cynthia
 */
public interface ISessionManager {

    public ISession getSession(String sessionid);

    public void addORUpdateSession(ISession session);

    public boolean validateSession(ISession session);
    
}
