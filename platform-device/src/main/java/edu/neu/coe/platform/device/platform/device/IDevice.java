/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.device.platform.device;

import java.util.Map;
import javax.crypto.SecretKey;

/**
 *
 * @author congliu
 */
public interface IDevice {
     /**
     * Method to login user when off line
     * @param username
     * @param password
     * @return 
     */
    
    public String userLoginOffLine(String username,String password);
    /**
     * Method to login device
     * @param adminpassword
     * @return 
     */
    
    public String deviceLogin(String adminpassword);
    /**
     * Method to login user
     * @param username
     * @param password
     * @return 
     */
    
    public String userLogin(String username, String password);
    /**
     * Method to authorize user
     * @return 
     */
    
    public String userAuthorization();
    /**
     * Method to authorize device
     * @return 
     */
    
    public String deviceAuthorization();
    /**
     * Method to send default request to service which would return "Welcome" when success
     * @param servicename
     * @return 
     */
    
    public String connectToService(String servicename);
    /**
     * Method to get device sessionid
     * @return 
     */
    
    public String getDevicesessionid();
    /**
     * Method to return user sessionid
     * @return 
     */

    public String getUsersessionid();
    /**
     * Method to return device ticket
     * @return 
     */

    public String getDeviceticket();
    /**
     * Method to return user ticket
     * @return 
     */

    public String getUserticket();
    /**
     * Method to return service ticket
     * @return 
     */

    public String getServiceticket();
    /**
     * Method to return device stepid
     * @return 
     */
    
    public String getDevicestepid();
    /**
     * Method to return user stepid
     * @return 
     */
    
    public String getUserstepid();
    /**
     * Method to send service request with additional information/parameters
     * @param servicename
     * @param additionalInformation
     * @return 
     */

    public Map<String,String> sendGeneralServiceRequest(String servicename,Map<String,String> additionalInformation);
    
    /**
    * Method to get key use for local
    * @param adminpassword
    * @return 
    */
    public SecretKey getLocalKey(String adminpassword);
    /**
     * Method to get local key
     * @return 
     */
    public SecretKey getLocalkey() ;
    
}
