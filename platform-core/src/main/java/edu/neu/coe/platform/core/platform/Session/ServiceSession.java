package edu.neu.coe.platform.core.platform.Session;

import edu.neu.coe.platform.core.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class ServiceSession extends Session {

    public ServiceSession(String serviceid) {
        super(serviceid);
    }

    @Override
    public void setExpiredTime() {
        super.expiredTime = System.currentTimeMillis() + ConstantUtil.SERVICE_SESSION_EXTEND;
    }

    @Override
    public void extendExpiredTime() {
        setExpiredTime();
    }
    
}
