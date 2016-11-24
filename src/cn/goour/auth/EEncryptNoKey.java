package cn.goour.auth;

public class EEncryptNoKey implements IEncryptNoKey
{

	@Override
	public byte[] Encryption(byte[] data) throws Exception
	{
		// TODO: Implement this method
		return null;
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
		return new String(Encryption(data),"UTF-8");
	}

	@Override
	public String EncryptionToString(String data) throws Exception
	{
		// TODO: Implement this method
		return new String(Encryption(data),"UTF-8");
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
		return Decryption(data.getBytes("UTF-8"));
	}

	@Override
	public String DecryptionToString(byte[] data) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(data),"UTF-8");
	}

	@Override
	public String DecryptionToString(String data) throws Exception
	{
		// TODO: Implement this method
		return new String(Decryption(data),"UTF-8");
	}


}
