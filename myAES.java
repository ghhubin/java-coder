package Coder;

import javax.crypto.Cipher;   
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class myAES {
	/** 
     * ECB����,��ҪIV 
     * @param key ��Կ 
     * @param data ���� 
     * @return Base64��������� 
     * @throws Exception 
     */  
    public static byte[] AESEncodeECB(byte[] key, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "AES");   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//ʹ�ü���ģʽ��ʼ�� ��Կ  
        byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * ECB����,��ҪIV 
     * @param key ��Կ 
     * @param data Base64��������� 
     * @return ���� 
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
     * CBC���� 
     * @param key ��Կ 
     * @param keyiv IV 
     * @param data ���� 
     * @return Base64��������� 
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
     * CBC���� 
     * @param key ��Կ 
     * @param keyiv IV 
     * @param data Base64��������� 
     * @return ���� 
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
