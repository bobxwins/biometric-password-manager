package test;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

import javafx.scene.paint.Color;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

import java.security.Security;
import java.util.Base64;

import java.io.File;

import java.nio.file.*;

import java.nio.charset.StandardCharsets;

public class Decrypt {

  private static  byte[] storedkeylength = FileUtils.readAllBytes(Encrypt.KeyLengthdir);

  private static String keylengthString = new String(storedkeylength);

  private static int keylengthInt=Integer.parseInt(keylengthString);

  private static  byte[] storedIterationCount = FileUtils.readAllBytes(Encrypt.IterationCountdir);
  private static String iterationCountString = new String(storedIterationCount);
  private static int iterationCountInt=Integer.parseInt( iterationCountString);



    public  void DecryptFile() {

        try {


            Security.addProvider(new BouncyCastleProvider());

            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");

            secureRandom.nextBytes(getIV());
            // NextBytes bruger en User-specified  antal bytes
            secureRandom.nextBytes(getSalt());
            char[] password = PasswordUtils.getUserPassword();

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WITHHMACSHA256", "BC");

            // Password Based nøgle udledning med hash algoritmen SHA-256



            PBEKeySpec keySpec = new PBEKeySpec(password, getSalt(), iterationCountInt, keylengthInt);
            SecretKey key = factory.generateSecret(keySpec);

            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encrypting file..");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
          //  cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()));
            // Algoritmen, block mode og padding samt provider definiers her

            // nøgle samt krypterings mode defineres her
            Encrypt encrypt = new Encrypt();
             cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV()));

         for (File file : encrypt.listOfFiles) {

                if (file.isFile()) {
                    String inFile= file.getPath();
                    byte[] input = FileUtils.readAllBytes(inFile);
                   byte[] output = cipher.doFinal(input);
                     String dir = "C:\\Users\\bob-w\\Downloads\\encrypt\\output";
                    String outFile = dir + "/"  + file.getName();
                FileUtils.write(outFile, output);

                }
             }

            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Passwords Decrypted!");

        } catch (Exception e) {
            Main.text[0].setFill(Color.RED);
            Main.text[0].setText("Incorrect credentials! Passwords not decrypted!" + "\n" + e);
        }
    }

    public static byte[] getIV () {

        byte[] genIVByte = FileUtils.readAllBytes(Encrypt.IVdir);

        String encodedIV = new String(genIVByte);

        byte[] decodedIV = Base64.getDecoder().decode(encodedIV);

        return decodedIV ;
    }

    public static byte[] getSalt () {

        byte[] genSaltbytes = FileUtils.readAllBytes(Encrypt.Saltdir);

        String encodedSalt = new String(genSaltbytes);

        byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);

        return decodedSalt ;
    }

}
