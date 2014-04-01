/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.device.platform.device;

/**
 *
 * @author Cynthia
 */
public class ConfigDevice extends AbstractDeviceConfiguration{

    public ConfigDevice() {
    }
    
    

    @Override
    protected IDevice newDevice(String deviceid, String password, String adminpassword, String platformurl) {
        return new DeviceImpl(deviceid, password, adminpassword, platformurl);
    }

    @Override
    protected IDevice newDevice(String deviceid, String password, String platformurl) {
        return new DeviceImpl(deviceid, password, platformurl);
    }
    
    
}
