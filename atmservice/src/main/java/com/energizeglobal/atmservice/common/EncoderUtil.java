package com.energizeglobal.atmservice.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.SecureRandom;

public class EncoderUtil implements Serializable {

    public static String getAESEncrypt(String seed, String cleartext) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed.getBytes());
        kgen.init(128, sr);
        SecretKey key = kgen.generateKey();
        byte[] rawKey = key.getEncoded();
        SecretKeySpec keySpec = new SecretKeySpec(rawKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(cleartext.getBytes());
        if (encrypted == null)
            return "";
        StringBuffer result = new StringBuffer(2 * encrypted.length);
        String HEX = "0123456789ABCDEF";
        for (int i = 0; i < encrypted.length; i++) {
            result.append(HEX.charAt((encrypted[i] >> 4) & 0x0f)).append(HEX.charAt(encrypted[i] & 0x0f));
        }
        return result.toString();
    }

    public static String getAESDecrypt(String seed, String encrypted) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed.getBytes());
        kgen.init(128, sr);
        SecretKey key = kgen.generateKey();
        byte[] rawKey = key.getEncoded();
        int len = encrypted.length() / 2;
        byte[] enc = new byte[len];
        for (int i = 0; i < len; i++) {
            enc[i] = Integer.valueOf(encrypted.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(decrypted);
    }
}
