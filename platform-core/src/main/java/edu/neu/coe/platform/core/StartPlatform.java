package edu.neu.coe.platform.core;

import javax.xml.transform.TransformerException;
import edu.neu.coe.platform.core.platform.PlatformConfig;

/**
 *
 * @author Cynthia
 */
public class StartPlatform {
	//test 
    public static void main(String[] arg) throws TransformerException {
        
    	new PlatformConfig().defaultPlatformConfiguration();
    	//PlatformConfig config = new PlatformConfig();
        //IPlatform platform = config.defaultPlatformConfiguration();
    }
    
}
