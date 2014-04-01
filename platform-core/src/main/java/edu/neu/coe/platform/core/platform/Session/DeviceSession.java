package edu.neu.coe.platform.core.platform.Session;

import edu.neu.coe.platform.core.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class DeviceSession extends Session {

    public DeviceSession(String deviceid) {
        super(deviceid);

    }

    @Override
    public void setExpiredTime() {
        super.expiredTime = System.currentTimeMillis() + ConstantUtil.DEVICE_SESSION_EXTEND;
    }
    
}
