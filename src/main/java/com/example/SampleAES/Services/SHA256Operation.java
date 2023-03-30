package com.example.SampleAES.Services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.google.common.hash.Hashing;
@Service
public class SHA256Operation {
	
	
	public static String  hashTheOriginalURLAndGenerateHash(String url) {
		System.out.println("url " + url);
		final byte[] defaultBytes = url.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();

            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return (hexString+"").substring(17);
        } catch (Exception ex) {
        	System.out.println(ex.toString());
            return "";
        }
	}

}
