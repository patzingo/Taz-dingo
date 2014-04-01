package edu.neu.coe.platform.core.platform.ticket;

import edu.neu.coe.platform.core.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class PlatformTGT extends TGT {

    public PlatformTGT(String sessionid, String platformname) {
        super(sessionid);
        super.authenticator = platformname;
        super.expiredTime = System.currentTimeMillis() + ConstantUtil.PLATFORM_TICKET_ACTIVE_TIME;
    }
    
}
