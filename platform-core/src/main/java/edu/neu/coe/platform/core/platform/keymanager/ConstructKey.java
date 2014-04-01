package edu.neu.coe.platform.core.platform.keymanager;

/**
 *
 * @author Cynthia
 */
import edu.neu.coe.platform.core.util.encryption.IEncryptionUtil;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import edu.neu.coe.platform.core.util.encryption.EncryptionUtilImpl;

public class ConstructKey {

    private final String platformname;
    private final String platformpassword;
    private SecretKey sessionMasterKey;
    private SecretKey keyServerMasterKey;
    public static IEncryptionUtil helper = new EncryptionUtilImpl();
    private SecretKey platformMasterKey;

    public ConstructKey(String name, String password, String adminpassword) {
        platformname = name;
        keyServerMasterKey = helper.generateSecretKey(password);
        platformMasterKey = helper.generateSecretKey(password);
        SecretKey key = helper.generateSecretKey(adminpassword);
        platformpassword = helper.encrypt(key, password);
        KeyGenerator keygenerator;
        try {
            keygenerator = KeyGenerator.getInstance("AES");
            sessionMasterKey = keygenerator.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConstructKey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePassword(String pass) {
        keyServerMasterKey = helper.generateSecretKey(pass);
    }

    public String getPlatformpassword() {
        return platformpassword;
    }

    public String getPlatformname() {
        return platformname;
    }

    public SecretKey getSessionMasterKey() {
        return sessionMasterKey;
    }

    public SecretKey getKeyServerMasterKey() {
        return keyServerMasterKey;
    }

    public void setKeyServerMasterKey(SecretKey keyServerMasterKey) {
        this.keyServerMasterKey = keyServerMasterKey;
    }

    public SecretKey getPlatformMasterKey() {
        return platformMasterKey;
    }
    
}
