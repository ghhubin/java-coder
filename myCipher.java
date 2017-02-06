package Coder;

import javax.crypto.Cipher;   
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class myCipher {
	/** 
     * ECB����,��ҪIV 
     * @param key ��Կ 
     * @param data ���� 
     * @return bOut ���� 
     * @throws Exception 
     */  
    public byte[] AESEncodeECB(byte[] key, byte[] data)  
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
     * @return ���� 
     * @throws Exception 
     */  
    public byte[] AESDecodeECB(byte[] key, byte[] data)  
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
    
    /** 
     * ECB����,��ҪIV 
     * @param key ��Կ 
     * @param data ����  
     * @throws Exception 
     */  
    public byte[] DES3EncodeECB(byte[] key, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "desede");   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//ʹ�ü���ģʽ��ʼ�� ��Կ  
        byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * ECB����,��ҪIV 
     * @param key ��Կ  
     * @return ���� 
     * @throws Exception 
     */  
    public byte[] DES3DecodeECB(byte[] key, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "desede");   
        cipher.init(Cipher.DECRYPT_MODE, secretKey); 
        byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * CBC���� 
     * @param key ��Կ 
     * @param keyiv IV 
     * @param data ����  
     * @throws Exception 
     */  
    public byte[] DES3EncodeCBC(byte[] key, byte[] iv, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "desede");  
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));  
       	byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }  
    /** 
     * CBC���� 
     * @param key ��Կ 
     * @param keyiv IV  
     * @return ���� 
     * @throws Exception 
     */  
    public byte[] DES3DecodeCBC(byte[] key, byte[] iv, byte[] data)  
            throws Exception {  
    	Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
    	SecretKeySpec secretKey = new SecretKeySpec(key, "desede");  
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));  
       	byte[] bOut = cipher.doFinal(data);  
        return bOut;  
    }
}