package cn.goour.auth.impl;
import java.security.*;
import cn.goour.auth.IEncryptNoKey;
import java.math.*;

public class MD5Impl implements IEncryptNoKey
{
	private static MD5Impl instance=null;
	public static MD5Impl getInstance(){
		if(instance == null){
			instance = new MD5Impl();
		}
		return instance;
	}
	@Override
	public byte[] Encryption(byte[] data) throws Exception
	{
		// TODO: Implement this method
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(data);
	}

	@Override
	public byte[] Encryption(String data) throws Exception
	{
		// TODO: Implement this method
		return Encryption(data.getBytes("UTF-8"));
	}

	@Override
	public String EncryptionToString(byte[] data) throws Exception
	{
		// TODO: Implement this method
		return new BigInteger(1,Encryption(data)).toString(16);
	}

	@Override
	public String EncryptionToString(String data) throws Exception
	{
		// TODO: Implement this method
		return new BigInteger(1,Encryption(data)).toString(16);
	}

	@Override
	public byte[] Decryption(byte[] data) throws Exception
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public byte[] Decryption(String data) throws Exception
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public String DecryptionToString(byte[] data) throws Exception
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public String DecryptionToString(String data) throws Exception
	{
		// TODO: Implement this method
		return null;
	}


}
