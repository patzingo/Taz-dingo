package edu.neu.coe.platform.core.util.encryption;

import java.util.Map;
import javax.crypto.SecretKey;

/**
 *
 * @author apple
 */
public interface IEncryptionUtil {

    /**
     * @param encryptionKey
     * @return
     */
    SecretKey generateSecretKey(String encryptionKey);

    /**
     * @param key
     * @param input
     * @return
     */
    String encrypt(SecretKey key, String input);

    /**
     * @param key
     * @param encryptedString
     * @return
     */
    String decrypt(SecretKey key, String encryptedString);

    /**
     * @param attributes
     * @return
     */
    boolean validateDecryptedAttributes(String... attributes);

    /**
     * @param keyBytes
     * @return
     */
    SecretKey generateSecretKeyFromBytes(byte[] keyBytes);

    /**
     * @param key
     * @param dataMap
     * @return
     */
    Map<String, String> encrypt(SecretKey key, Map<String, String> dataMap);

    /**
     * @param key
     * @param encData
     * @return
     */
    Map<String, String> decrypt(SecretKey key, Map<String, String> encData);

    /**
     *
     * @param key
     * @return
     */
    String keyToString(SecretKey key);

    /**
     *
     * @param string
     * @return
     */
    SecretKey stringToKey(String string);
    
}
