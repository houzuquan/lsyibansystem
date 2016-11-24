package cn.goour.auth;

public class EEncryptHaveKey implements IEncryptHaveKey
{

	@Override
	public byte[] Encryption(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		return null;
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
		return new String(Encryption(data,key),"UTF-8");
	}

	@Override
	public String EncryptionToString(String data, String key) throws Exception
	{
		// TODO: Implement this method
		return new String(Encryption(data,key),"UTF-8");
	}

	@Override
	public byte[] Decryption(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public byte[] Decryption(String data, String key) throws Exception
	{
		// TODO: Implement this method
		return Decryption(data.getBytes("UTF-8"),key.getBytes("UTF-8"));
	}

	@Override
	public String DecryptionToString(byte[] data, byte[] key) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(data,key),"UTF-8");
	}

	@Override
	public String DecryptionToString(String data, String key) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(data,key),"UTF-8");
	}
	
	
}
