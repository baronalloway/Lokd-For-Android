package com.baronalloway.lokdforandroid;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import android.content.Context;
import android.util.Log;

public class EncryptionKey{
	
	private static byte[] key;
    private static SecretKeySpec secretKey;

    private static Cipher cipher;
    private static Cipher dcipher;
	
	EncryptionKey(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException{
		
		//create a new char array from given password
        char[] passwordChars = password.toCharArray();
        //new MessageDigest
        MessageDigest sha = null;

        //set the key to the bytes of the password in UTF-8
        key = password.getBytes("UTF-8");

        //Create SecretKeyFactory
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        //create KeySpec
        KeySpec spec = new PBEKeySpec(passwordChars, key, 1024, 128);
        //Create SecretKey (temporary)
        SecretKey tmp = factory.generateSecret(spec);
        //Create SecretKey from SecretKey ( 2 layer security)
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        //Initialize a cipher
        cipher = Cipher.getInstance("AES");
        //Initialize a deCipher
        dcipher = Cipher.getInstance("AES");
        //set the cypher to "encrypt mode"
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        //set the cypher to "decrypt mode"
        dcipher.init(Cipher.DECRYPT_MODE, secret);
	}
	
	
	public SecretKeySpec getKey() {
        //return the secretKey
        return secretKey;
    }

    //get the cipher
    public Cipher getCipher() {
        //return the cipher
        return cipher;
    }

    //get the decipher
    public Cipher getDCipher() {
        //return the decipher
        return dcipher;
    }
    
    
    public List<WalletItem> get(SecretKeySpec inKey, Cipher cipher, Cipher dcipher, Context c) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //creating an arrayList of walletItems
        List<WalletItem> items = new ArrayList<WalletItem>();
        
        
        
        File f = new File(c.getFilesDir(), "walletItem.wal");
        
        if(f.exists())
        {
        	Log.i("info", "file exists");
        }
        else
        {
        	Log.i("info", "file does not exist");
        }
        
        
        
        
        //return this ArrayList
        return items;
    }
    
    
  
    
    
    }	


