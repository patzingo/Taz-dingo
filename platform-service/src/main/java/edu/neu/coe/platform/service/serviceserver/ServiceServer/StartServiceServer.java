/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.neu.coe.platform.service.serviceserver.ServiceServer;


/**
 *
 * @author Cynthia
 */
public class StartServiceServer {
    
    public static void main(String[] arg){
        ServiceConfig config=new ServiceConfig();
        IServiceServer service=config.defaultServiceServerConfiguration();
        System.out.println(service);
    }
    
   
    
}
