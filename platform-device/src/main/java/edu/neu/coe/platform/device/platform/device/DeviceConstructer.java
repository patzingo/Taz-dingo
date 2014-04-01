/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.platform.device.platform.device;

/**
 *
 * @author Cynthia
 */
public class DeviceConstructer {
    private String deviceId;
    private String devicePassword;
    /**
     * 
     * @param deviceId
     * @param devicePassword 
     */

    public DeviceConstructer(String deviceId, String devicePassword) {
        this.deviceId = deviceId;
        this.devicePassword = devicePassword;
    }
    /**
     * Method to return deviceid
     * @return 
     */

    public String getDeviceId() {
        return deviceId;
    }
    /**
     * Method to set deviceid
     * @param deviceId 
     */

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    /**
     * method to return device password
     * @return 
     */

    public String getDevicePassword() {
        return devicePassword;
    }
/**
 * Mehtod to set device password
 * @param devicePassword 
 */
    public void setDevicePassword(String devicePassword) {
        this.devicePassword = devicePassword;
    }
}
