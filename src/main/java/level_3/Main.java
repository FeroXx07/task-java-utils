package level_3;

import level_1.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Main {
    String message = "Hello, if you can see this message, it means that the whole Encryption Protocol using AES/CBC with padding is working correctly. :)";
    CryptographyManager cryptoManager = new CryptographyManager();
    FileManager fileManager = new FileManager();
    void main(String[] args) throws IOException {
        Properties props = loadProperties();

        // Encrypt
        CryptographicInputs cryptoInput = cryptoManager.Encrypt(message);

        // Serialize
        Path toSaveKeyPath = Paths.get(props.getProperty("level_3.keyPath"));
        Path toSaveIVPath = Paths.get(props.getProperty("level_3.ivPath"));
        fileManager.saveContentsToFile(List.of(cryptoInput.getEncodedKey()), toSaveKeyPath);
        fileManager.saveContentsToFile(List.of(cryptoInput.getEncodedIV()), toSaveIVPath);

        // DeSerialize
        List<String> readKey = fileManager.readContentsFromFile(toSaveKeyPath);
        List<String> readIV = fileManager.readContentsFromFile(toSaveIVPath);
        byte[] encryptedMessage = cryptoInput.getEncrypted();

        if (readKey.isEmpty() || readIV.isEmpty()) {
            throw new RuntimeException("Error reading key or iv");
        }

        CryptographicInputs reCreatedCryptoInputsFromFile = new CryptographicInputs(encryptedMessage, readKey.getFirst(), readIV.getFirst());

        // Decrypt
        String deCryptedMessage = cryptoManager.Decrypt(reCreatedCryptoInputsFromFile);
        IO.println(deCryptedMessage);
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();

        try (InputStream in = level_2.Main.class.getResourceAsStream("/app.properties")){
            if (in == null) {
                throw new IllegalStateException(
                        "app.properties not found");
            }
            props.load(in);
        }
        return props;
    }
}
