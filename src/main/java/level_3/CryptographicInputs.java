package level_3;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class CryptographicInputs {
    private final String encodedKey;
    private final String encodedIV;
    private final byte[] encrypted;
    public CryptographicInputs(byte[] encryptedData, Key key, byte[] iv) {
        encrypted = encryptedData;
        encodedKey = encodeKey(key);
        encodedIV = encodeIV(iv);
    }

    public CryptographicInputs(byte[] encryptedData, String encodedKey, String encodedIV) {
        encrypted = encryptedData;
        this.encodedKey = encodedKey;
        this.encodedIV = encodedIV;
    }

    public String getEncodedKey() { return encodedKey;}
    public String getEncodedIV() { return encodedIV;}

    public byte[] getEncrypted() { return encrypted;}

    public static String encodeKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String encodeIV(byte[] iv) {
        return Base64.getEncoder().encodeToString(iv);
    }

    public static SecretKey decodeKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        javax.crypto.SecretKey restoredKey = new javax.crypto.spec.SecretKeySpec(decodedKey, "AES");
        return restoredKey;
    }

    public static byte[] decodeIV(String encodedIV) {
        return Base64.getDecoder().decode(encodedIV);
    }

}
