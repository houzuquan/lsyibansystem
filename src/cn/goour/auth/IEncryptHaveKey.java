package cn.goour.auth;

public interface IEncryptHaveKey
{
	public byte[] Encryption(byte[] data,byte[] key)throws Exception;
	public byte[] Encryption(String data,String key)throws Exception;
	public String EncryptionToString(byte[] data,byte[] key)throws Exception;
	public String EncryptionToString(String data,String key)throws Exception;
	
	public byte[] Decryption(byte[] data,byte[] key)throws Exception;
	public byte[] Decryption(String data,String key)throws Exception;
	public String DecryptionToString(byte[] data,byte[] key)throws Exception;
	public String DecryptionToString(String data,String key)throws Exception;
}
