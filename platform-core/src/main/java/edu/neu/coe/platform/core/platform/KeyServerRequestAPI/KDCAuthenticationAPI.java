package edu.neu.coe.platform.core.platform.KeyServerRequestAPI;

import java.util.HashMap;
import java.util.Map;
import edu.neu.coe.platform.core.platform.Request;
import edu.neu.coe.platform.core.util.ConstantUtil;

/**
 *
 * @author Cynthia
 */
public class KDCAuthenticationAPI {

    /**
     * Method to generate platform authentication request
     *
     * @param platformname
     * @return
     */
    public static Request createPlatformAuthenticationRequest(String platformname) {
        if (platformname == null || platformname.isEmpty()) {
            return null;
        }
        Request request = new Request();
        Map<String, String> data = new HashMap<>();
        data.put(ConstantUtil.PLATFORM_NAME, platformname);
        data.put(ConstantUtil.REQUEST_TYPE, ConstantUtil.PLATFORM_AUTHENTICATE_REQUEST);
        request.setData(data);
        return request;
    }
    
}
