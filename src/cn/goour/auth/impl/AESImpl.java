package cn.goour.auth.impl;
import cn.goour.auth.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.math.*;

public class AESImpl implements IEncryptHaveKey
{
	private static AESImpl instance=null;
	public static AESImpl getInstance(){
		if(instance == null){
			instance = new AESImpl();
		}
		return instance;
	}
	@Override
	public byte[] Encryption(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		SecretKeySpec skey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,skey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] Encryption(String data, String key) throws Exception
	{
		// TODO: Implement this method
		return Encryption(data.getBytes("UTF-8"),key.getBytes("UTF-8"));
	}

	@Override
	public String EncryptionToString(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		return byteArrayToHexStr(Encryption(data,key));
	}

	@Override
	public String EncryptionToString(String data, String key) throws Exception
	{
		// TODO: Implement this method
		return byteArrayToHexStr(Encryption(data,key));
	}

	@Override
	public byte[] Decryption(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		SecretKeySpec skey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE,skey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] Decryption(String HexData, String md5Key) throws Exception
	{
		// TODO: Implement this method
		return Decryption(HexStrToByteArray(HexData),md5Key.getBytes("UTF-8"));
	}

	@Override
	public String DecryptionToString(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(data,key),"UTF-8");
	}

	@Override
	public String DecryptionToString(String HexData, String md5Key) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(HexData,md5Key),"UTF-8");
	}
	private String byteArrayToHexStr(byte[] bytes){
		return new BigInteger(1, bytes).toString(16);
	}
	private byte[] HexStrToByteArray(String hexstr) throws Exception
	{
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
	private int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}
}
