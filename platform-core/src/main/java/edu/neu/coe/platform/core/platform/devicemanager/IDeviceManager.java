package edu.neu.coe.platform.core.platform.devicemanager;

import java.util.List;
import edu.neu.coe.platform.core.platform.Response;

/**
 *
 * @author Cynthia
 */
public interface IDeviceManager {

    public boolean isBlock(String deviceid);

    public String blockDevice(String deviceid, List<String> devicelist);

    public Response generateBlockedDeviceResponse();
    
}
