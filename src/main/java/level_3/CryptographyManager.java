package level_3;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptographyManager {
    public CryptographicInputs Encrypt(String plainText) {
        SecretKey key = null;
        byte[] iv = null;
        byte[] encrypted = null;
        try {
            // Generate an AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            key = keyGen.generateKey();

            // Generate a random 16-byte IV because we want to use CBC instead of EBC
            iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Encrypt
            Cipher encryptCipher =
                    Cipher.getInstance("AES/CBC/PKCS5Padding");

            encryptCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            encrypted = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException |
                 NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        if (key == null)
            throw new RuntimeException("Failed to encrypt with key");

        return new CryptographicInputs(encrypted, key, iv);
    }

    public String Decrypt(CryptographicInputs inputs) {

        try {
            // Decode the AES key and IV
            SecretKey key = CryptographicInputs.decodeKey(inputs.getEncodedKey());
            byte[] iv = CryptographicInputs.decodeIV(inputs.getEncodedIV());
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Initialize the cipher for decryption
            Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            decryptCipher.init(Cipher.DECRYPT_MODE,key,ivSpec);

            // Decrypt
            byte[] decrypted = decryptCipher.doFinal(inputs.getEncrypted());

            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt", e);
        }
    }
}
