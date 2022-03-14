package test;

import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

import javafx.scene.paint.Color;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

import java.security.Security;

import java.util.Base64;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Encrypt {

    public File Passwordfolder = new File("C:\\Users\\bob-w\\Downloads\\encrypt\\lol");
    public File[] listOfFiles = Passwordfolder.listFiles();

    public static String IVdir = "C:\\Users\\bob-w\\Downloads\\encrypt\\GenIV.txt";

    public static String Saltdir = "C:\\Users\\bob-w\\Downloads\\encrypt\\Salt.txt";

    public static String IterationCountdir = "C:\\Users\\bob-w\\Downloads\\encrypt\\IterationCount.txt";

    public static String KeyLengthdir = "C:\\Users\\bob-w\\Downloads\\encrypt\\KeyLength.txt";

    private static byte[] generatedIV = new byte[16]; //16

    private static byte[] salt = new byte[32]; //32

    private static int iterationCount = 5000;

    private static int keylength = 192;

    public void SymmetricKeyGenerator() {

        try {

            GetFilesFromAndroid getAndroidFile = new GetFilesFromAndroid();
            getAndroidFile.readFile();


            Security.addProvider(new BouncyCastleProvider());

            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");


            secureRandom.nextBytes(generatedIV);
            // NextBytes bruger en User-specified  antal bytes
            secureRandom.nextBytes(salt);
            char[] password = PasswordUtils.getUserPassword();
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keylength);

            // Den selected Fils path som  en string

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WITHHMACSHA256", "BC");

            // Password Based nøgle udledning med hash algoritmen SHA-256

            SecretKey key = factory.generateSecret(keySpec);

            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encrypting file..");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(generatedIV));
            // Algoritmen, block mode og padding samt provider definiers her

            // nøgle samt krypterings mode defineres her

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String inFile = file.getPath();
                    byte[] input = FileUtils.readAllBytes(inFile);
                    byte[] output = cipher.doFinal(input);
                    String outFile = inFile + "." + "Encrypted" + "." + "aes";
                    FileUtils.write(outFile, output);
                    file.delete();
                }
            }

            byte[] EncodedSalt = Base64.getEncoder().encode(salt);

            byte[] EncodedIterationCount = String.valueOf(iterationCount).getBytes(StandardCharsets.UTF_8);

            // konverter først iterationCount til en string som hernæst konverterees til et bytearray.

            byte[] EncodedKeyLength = String.valueOf(keylength).getBytes(StandardCharsets.UTF_8);

            byte[] EncodedIV = Base64.getEncoder().encode(generatedIV);

            FileUtils.write(Saltdir, EncodedSalt);

            FileUtils.write(IterationCountdir, EncodedIterationCount);

            FileUtils.write(KeyLengthdir, EncodedKeyLength);


            FileUtils.write(IVdir, EncodedIV);


            // genere 2 filer med den symstriske nøgle og generated IV
            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encryption succesful!");


        } catch (Exception e) {
            Main.text[0].setFill(Color.RED);
            Main.text[0].setText("Encryption failed!" + "\n" + e);
        }

    }

}