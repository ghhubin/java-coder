package Coder;

import javax.crypto.Cipher;   
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class myAES {
	/** 
     * ECB加密,不要IV 
     * @param key 密钥 
     * @param data 明文 
     * @return Base64编码的密文 
     * @throws Exception 
     */  
    public static byte[] AESEncodeECB(byte[] key, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "AES");   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//使用加密模式初始化 密钥  
        byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * ECB解密,不要IV 
     * @param key 密钥 
     * @param data Base64编码的密文 
     * @return 明文 
     * @throws Exception 
     */  
    public static byte[] AESDecodeECB(byte[] key, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "AES");   
        cipher.init(Cipher.DECRYPT_MODE, secretKey); 
        byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * CBC加密 
     * @param key 密钥 
     * @param keyiv IV 
     * @param data 明文 
     * @return Base64编码的密文 
     * @throws Exception 
     */  
    public byte[] AESEncodeCBC(byte[] key, byte[] iv, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "AES");  
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));  
       	byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * CBC解密 
     * @param key 密钥 
     * @param keyiv IV 
     * @param data Base64编码的密文 
     * @return 明文 
     * @throws Exception 
     */  
    public byte[] AESDecodeCBC(byte[] key, byte[] iv, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "AES");  
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));  
       	byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }
}
