package by.itransition.game.main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Key {

    static String encripting(String move) {
        byte[] keys = gettingKeys();
        byte[] keysHmac = new byte[16];
        Mac hmac;
        try {
            hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(keys, "HmacSHA256");
            try {
                hmac.init(keySpec);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            keysHmac = hmac.doFinal(move.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(keysHmac.length * 2);
        for (byte b : keysHmac) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("HMAC: " + gettingString(keysHmac));
        return gettingString(keys);
    }

    static byte[] gettingKeys() {
        SecureRandom random = new SecureRandom();
        byte[] keys = new byte[16];
        random.nextBytes(keys);
        return keys;
    }

    static String gettingString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().toUpperCase();
    }

}
