package edu.neu.coe.platform.core.platform.Session;

import edu.neu.coe.platform.core.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class UserSession extends Session {

    public UserSession(String username, String deviceid) {
        super(username + ConstantUtil.FROM + deviceid);

    }

    @Override
    public void setExpiredTime() {
        super.expiredTime = System.currentTimeMillis() + ConstantUtil.USER_SESSION_EXTEND;
    }
    
}
