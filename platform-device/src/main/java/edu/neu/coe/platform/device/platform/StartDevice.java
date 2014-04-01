/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.device.platform;

import java.util.Scanner;
import edu.neu.coe.platform.device.platform.device.AbstractDeviceConfiguration;
import edu.neu.coe.platform.device.platform.device.ConfigDevice;
import edu.neu.coe.platform.device.platform.device.IDevice;
import edu.neu.coe.platform.device.platform.util.ConstantUtil;


/**
 *
 * @author Cynthia
 */
public class StartDevice {
    //device test
    public static void main(String[] arg){
        ConfigDevice config=new ConfigDevice();
        IDevice device=config.defaultDeviceConfiguration();
        System.out.println(device);
        String response=device.userLogin("user", "123u");
		if(response.equals(ConstantUtil.SUCCESS_LOGIN))
		response=device.userAuthorization();
                else{
                    System.out.println("Enter device adminpassword:");
                    Scanner scanIn=new Scanner(System.in);
                    String adminpassword=scanIn.next();
                    response=device.deviceLogin(adminpassword);
                    if(response.equals(ConstantUtil.SUCCESS_LOGIN))
                        response=device.deviceAuthorization();
                    if(response.equals(ConstantUtil.SUCCESS_GET_TICKET));
                   response=device.userLogin("user", "123u");
		if(response.equals(ConstantUtil.SUCCESS_LOGIN))
		response=device.userAuthorization();
                }
		if(response.equals(ConstantUtil.SUCCESS_GET_SERVICE_TICKET))
			response=device.connectToService("service");
		System.out.println(response);
    }
    
    
    
}
