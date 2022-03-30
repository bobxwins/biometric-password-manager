package com.example.biom;

import javax.crypto.spec.IvParameterSpec;

import javax.crypto.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

import java.security.Security;
import java.util.Base64;

import java.io.File;

public class AndroidDecrypt {


    static {
        Security.removeProvider("BC");
        Security.addProvider(new BouncyCastleProvider());
    }

    public  void DecryptFile(String dir) {

        try {

            byte[] storedkeylength = FileUtils.readAllBytes(dir+"KeyLength.txt");
            String keylengthString = new String(storedkeylength);
            int keylengthInt=Integer.parseInt(keylengthString);
            byte[] storedIterationCount = FileUtils.readAllBytes(dir+"IterationCount.txt");
           String iterationCountString = new String(storedIterationCount);
            int iterationCountInt=Integer.parseInt( iterationCountString);

            Security.addProvider(new BouncyCastleProvider());

            char[] password = "123123123123.".toCharArray();

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WITHHMACSHA256", "BC");

            // Password Based nøgle udledning med hash algoritmen SHA-256


            PBEKeySpec keySpec = new PBEKeySpec(password, getSalt(dir), iterationCountInt, keylengthInt);
            SecretKey key = factory.generateSecret(keySpec);

          /*  Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Encrypting file..");
             */

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
          // String cipher file AES som skal opbevares i Keystore

            // Algoritmen, block mode og padding samt provider definiers her

            // nøgle samt krypterings mode defineres her
            AndroidEncrypt encrypt = new AndroidEncrypt();
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(getIV(dir)));

            File oldfile = new File(dir+"pwd.aes");
            byte[] input = FileUtils.readAllBytes(oldfile.getAbsolutePath());
            byte[] output = cipher.doFinal(input);
            File dest = new File(dir+"pwd.txt");

            if (oldfile.renameTo(dest)) {

                System.out.println("File is renamed");
            }
            FileUtils.write(dest.getAbsolutePath(), output);

            /*
            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Passwords Decrypted!");
            */

        } catch (Exception e) {
            /*
            Main.text[0].setFill(Color.RED);
            Main.text[0].setText("Incorrect credentials! Passwords not decrypted!" + "\n" + e);

             */
        }
    }

    public  byte[] getIV (String dir) {

        byte[] genIVByte = FileUtils.readAllBytes(dir+"GenIV.txt");

        String encodedIV = new String(genIVByte);

        byte[] decodedIV = Base64.getDecoder().decode(encodedIV);

        return decodedIV ;
    }

    public byte[] getSalt (String dir) {

        byte[] genSaltbytes = FileUtils.readAllBytes(dir+"Salt.txt");

        String encodedSalt = new String(genSaltbytes);

        byte[] decodedSalt = Base64.getDecoder().decode(encodedSalt);

        return decodedSalt ;
    }

}
