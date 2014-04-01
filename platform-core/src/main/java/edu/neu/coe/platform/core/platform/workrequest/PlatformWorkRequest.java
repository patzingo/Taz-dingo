package edu.neu.coe.platform.core.platform.workrequest;

import edu.neu.coe.platform.core.platform.Request;
import edu.neu.coe.platform.core.platform.Session.ISession;

/**
 *
 * @author Cynthia
 */
public class PlatformWorkRequest extends WorkRequest {

    private ISession session;
    private String prestep;

    public PlatformWorkRequest(ISession session, Request request, String prestep) {
        super(request);
        this.session = session;
        this.prestep = prestep;
    }

    public ISession getSession() {
        return session;
    }

    public String getPrestep() {
        return prestep;
    }

    @Override
    public String getSessionid() {
        return this.session.getSessionID();
    }
    
}
