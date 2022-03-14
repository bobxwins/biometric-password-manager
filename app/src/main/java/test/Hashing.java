 /*package sample;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javafx.scene.paint.Color;
import org.bouncycastle.util.encoders.Hex;

public class Hashing {

    static String MasterPassHash =
    "\\home\\flexlab\\Download\\security-main\\password manager\\PMFileManagement\\PassHash.txt";
    public static void hashGenerate() {
        try {

            var inFile = Main.PassText;
            byte [] inFileEncoded = inFile.getBytes(StandardCharsets.UTF_8);

            MessageDigest Digest =
                    MessageDigest.getInstance("SHA-256", "BC");

            // implementer algoritme og provider

            Digest.update(inFileEncoded);
            // input

            byte[] hashValue = Digest.digest();
            // beregner og returner hash værdi som byte array


            String HexValue = Hex.toHexString(hashValue);

            byte [] hexBytes = HexValue.getBytes(StandardCharsets.UTF_8);
            FileUtils.write(MasterPassHash, hexBytes);

        } catch (Exception e) {
            Main.text[0].setFill(Color.RED);
            Main.text[0].setText("Password hashing failed" + "\n" +e);
        }
    }


    public Boolean verifyHash() {
        try {

            var inFile = Main.PassText;
            byte [] inFileEncoded = inFile.getBytes(StandardCharsets.UTF_8);

            Main.text[0].setFill(Color.GREEN);
            Main.text[0].setText("Verifying hash of master password..");

            MessageDigest Digest =
                    MessageDigest.getInstance("SHA-256", "BC");
            Digest.update(inFileEncoded);
            byte[] computedHashValue = Digest.digest();

            byte[] storedHashValue =
                    FileUtils.readAllBytes(MasterPassHash);

            String StoredHexValue = new String(storedHashValue);

            String ComputedHexValue = Hex.toHexString(computedHashValue);
            Main.text[1].setFill(Color.DARKBLUE);
            Main.text[1].setText("Expected Hashvalue: " + StoredHexValue.substring(0,40)+"..." +  "\n"+
                    "Actual Computed HashValue: " + ComputedHexValue.substring(0,40)+ "..."+  "\n");

            // der bruges in substring på 41 tegn i længde, da hashen er over 60 tegn i længde
            if (ComputedHexValue.equals( StoredHexValue))
            {
                // the succesful message is written in Decryption.DecryptFile(File)
                return true;
            }
            else {

                Main.text[0].setFill(Color.DARKRED);


                Main.text[0].setText("Master password did not match!"+ "\n"+
                        "Password Verification failed!");
                return false;
            }

        } catch (Exception e) {
            Main.text[0].setFill(Color.RED);
            Main.text[0].setText("verification failed " + "\n" +e);
        }


        return false; }
}


*/