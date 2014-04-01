package edu.neu.coe.platform.core.platform.devicemanager;

import edu.neu.coe.platform.core.util.ConstantUtil;
import java.util.ArrayList;
import java.util.List;
import edu.neu.coe.platform.core.platform.Response;

/**
 *
 * @author Cynthia
 */
public class DeviceManagerImpl implements IDeviceManager {

    private ArrayList<String> blockedDeviceList;

    public DeviceManagerImpl() {
        blockedDeviceList = new ArrayList<String>();
    }

    @Override
    public boolean isBlock(String deviceid) {
        return blockedDeviceList.contains(deviceid);
    }

    @Override
    public String blockDevice(String deviceid, List<String> devicelist) {
        String message = ConstantUtil.SUCCESS_BLOCK;
        if (devicelist.contains(deviceid)) {
            blockedDeviceList.add(deviceid);
        } else {
            message = ConstantUtil.FAIL_BLOCK;
        }
        return message;
    }

    @Override
    public Response generateBlockedDeviceResponse() {
        Response response = new Response();
        response.getData().put(ConstantUtil.ERROR, ConstantUtil.DEVICE_BLOCKED);
        return response;
    }
    
}
