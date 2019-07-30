package cn.ovea_y.puzzle.util.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    public static String ASEEncoding(byte[] key, byte[] iv, String content){
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

//            int blockSize = cipher.getBlockSize();
//            int plaintextLength = content.getBytes().length;
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            byte[] keyBytes = key;
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }

            byte[] ivBytes = iv;
            if (ivBytes.length % base != 0) {
                int groups = ivBytes.length / base + (ivBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(ivBytes, 0, temp, 0, ivBytes.length);
                ivBytes = temp;
            }
//            if(plaintextLength % blockSize != 0){
//                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//            }
//            byte[] data = new byte[plaintextLength];
//            System.arraycopy(content.getBytes(), 0, data, 0, content.getBytes().length);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(ivBytes));
            byte[] data = content.getBytes();
            return new String(Base64.getEncoder().encode(cipher.doFinal(data)));
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ASEDncoding(byte[] key, byte[] iv, String content){
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            byte[] keyBytes = key;
            int base = 16;
            if (keyBytes.length % base != 0) {
                int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
                keyBytes = temp;
            }

            byte[] ivBytes = iv;
            if (ivBytes.length % base != 0) {
                int groups = ivBytes.length / base + (ivBytes.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(ivBytes, 0, temp, 0, ivBytes.length);
                ivBytes = temp;
            }
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, "AES"), new IvParameterSpec(ivBytes));
            byte[] encrypted1 = Base64.getDecoder().decode(content);
            return new String(cipher.doFinal(encrypted1), "UTF-8");
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
