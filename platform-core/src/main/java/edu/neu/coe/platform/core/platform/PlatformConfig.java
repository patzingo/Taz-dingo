package edu.neu.coe.platform.core.platform;

/**
 *
 * @author Cynthia
 */
public class PlatformConfig extends AbstractPlatformConfig {

    public PlatformConfig() {
    }

    @Override
    protected IPlatform newPlatform(String platformname, String password, String adminpassword, String defaultkeyserverurl, String defaultkeyservername) {
        return new Platform(platformname, password, adminpassword, defaultkeyserverurl, defaultkeyservername);
    }
    
}
