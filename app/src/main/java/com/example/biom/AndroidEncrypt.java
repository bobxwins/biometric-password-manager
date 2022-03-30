
package com.example.biom;

import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

import java.security.Security;

import java.util.Base64;

import java.io.File;
import java.nio.charset.StandardCharsets;


public class AndroidEncrypt {

    static {
        Security.removeProvider("BC");
        // Confirm that positioning this provider at the end works for your needs!
        Security.addProvider(new BouncyCastleProvider());
    }
    // public static String passwordDir = "/storage/emulated/0/Android/data/com.example.biom/files";

    public    String IVdir = "GenIV.txt";

    public    String Saltdir = "Salt.txt";

    public   String IterationCountdir = "IterationCount.txt";

    public    String KeyLengthdir = "KeyLength.txt";

    private static byte[] generatedIV = new byte[16]; //16

    private static byte[] salt = new byte[32]; //32

    private static int iterationCount = 5000;

    private static int keylength = 192;

    public void SymmetricKeyGenerator(String dir) {

        try {
          //  System.out.println("this is encrypt dirfolder"+dirfolder);
            System.out.println(dir+Saltdir);
            // Confirm that positioning this provider at the end works for your needs!



            SecureRandom secureRandom = SecureRandom.getInstance("DEFAULT", "BC");

            FileUtils.write(dir+"55s.txt", "tekllnst".getBytes(StandardCharsets.UTF_8));


            secureRandom.nextBytes(generatedIV);
            // NextBytes bruger en User-specified  antal bytes

            secureRandom.nextBytes(salt);



            char[] password = "123123123123.".toCharArray();



            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keylength);

            // Den selected Fils path som  en string

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WITHHMACSHA256", "BC");

            // Password Based nøgle udledning med hash algoritmen SHA-256

            SecretKey key = factory.generateSecret(keySpec);

        /*  Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encrypting file..");  */

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(generatedIV));
            // Algoritmen, block mode og padding samt provider definiers her

            // nøgle samt krypterings mode defineres her

             //    File Passwordfolder = new File(dir+"/output");

          // File[] listOfFiles = Passwordfolder.listFiles();
                  File oldfile = new File(dir+"pwd.txt");
                    byte[] input = FileUtils.readAllBytes(oldfile.getAbsolutePath());
                    byte[] output = cipher.doFinal(input);
                    File dest = new File(dir+"pwd.aes");

                   if (oldfile.renameTo(dest)) {

                System.out.println("File is renamed");
            }
                    FileUtils.write(dest.getAbsolutePath(), output);

            FileUtils.write(dir+"9s.txt", "tekllnst".getBytes(StandardCharsets.UTF_8));

            byte[] EncodedSalt = Base64.getEncoder().encode(salt);

            byte[] EncodedIterationCount = String.valueOf(iterationCount).getBytes(StandardCharsets.UTF_8);

            // konverter først iterationCount til en string som hernæst konverterees til et bytearray.

            byte[] EncodedKeyLength = String.valueOf(keylength).getBytes(StandardCharsets.UTF_8);

            byte[] EncodedIV = Base64.getEncoder().encode(generatedIV);

            FileUtils.write(dir+Saltdir, EncodedSalt);

            FileUtils.write(dir+IterationCountdir, EncodedIterationCount);

            FileUtils.write(dir+KeyLengthdir, EncodedKeyLength);

            FileUtils.write(dir+IVdir, EncodedIV);


        /* Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encryption succesful!"); */


        } catch (Exception e) {

        /*    Main.text[0].setFill(Color.RED);
            Main.text[0].setText("Encryption failed!" + "\n" + e); */
        }

    }

}
