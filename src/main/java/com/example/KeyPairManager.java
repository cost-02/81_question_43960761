package com.example;

import java.io.*;
import java.security.*;

public class KeyPairManager {

    // Metodo per generare la coppia di chiavi
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    // Metodo per salvare la coppia di chiavi in un file
    public static void saveKeyPair(KeyPair keyPair, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(keyPair);
        }
    }

    // Metodo per caricare la coppia di chiavi da un file
    public static KeyPair loadKeyPair(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (KeyPair) ois.readObject();
        }
    }

    public static void main(String[] args) {
        try {
            // Generazione e salvataggio della coppia di chiavi
            KeyPair keyPair = generateKeyPair();
            String filePath = "keyPair.dat";
            saveKeyPair(keyPair, filePath);

            // Ricaricamento della coppia di chiavi
            KeyPair loadedKeyPair = loadKeyPair(filePath);

            // Visualizzazione di un messaggio di conferma
            System.out.println("KeyPair generated and reloaded successfully.");
        } catch (NoSuchAlgorithmException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
