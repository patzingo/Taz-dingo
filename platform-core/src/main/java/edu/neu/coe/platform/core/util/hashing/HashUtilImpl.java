package edu.neu.coe.platform.core.util.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author apple
 */
public class HashUtilImpl {

    public enum HashingTechqniue {
        SSHA256("SHA-256"), MD5("MD5");
        private final String value;

        private HashingTechqniue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static byte[] getHash(String input, HashingTechqniue technique) {
        if (input == null || input.isEmpty() || technique == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(technique.value);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        digest.reset();
        byte[] hashedBytes = digest.digest(base64StringToByte(input));
        return hashedBytes;
    }

    public static byte[] base64StringToByte(String input) {
        try {
            if (input == null || input.isEmpty()) {
                return null;
            }

            //JAVA Sun Misc Base64Decoder has limited support
            //Using personalized Base64Coder.
            
            //BASE64Decoder decoder = new BASE64Decoder();
            //return decoder.decodeBuffer(input);
            return Base64Coder.decode(input);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(HashUtilImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String bytetoBase64String(byte[] input) {
        if (input == null) {
            return null;
        }
        //JAVA Sun Misc Base64Decoder has limited support
        //Using personalized Base64Coder.
        
        //BASE64Encoder endecoder = new BASE64Encoder();
        //return endecoder.encode(input);
        
        return new String(Base64Coder.encode(input));
        
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] getHashWithSalt(String input, HashingTechqniue technique, byte[] salt) {
        if (input == null || input.isEmpty() || technique == null || salt == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(technique.value);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(base64StringToByte(input));
        return hashedBytes;
    }

    public static String getSessionKey() {
        return bytetoBase64String(generateSalt());
    }
    
}
