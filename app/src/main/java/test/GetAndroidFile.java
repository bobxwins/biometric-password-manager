package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetAndroidFile {

    public void readFile()  {

        try {
    String androidFilePath = "/storage/emulated/0/Android/data/com.example.myapp/files/myfile.txt";

    String windowsFilePath = "C:\\Users\\bob-w\\Downloads\\encrypt";

    List<String> cmd = new LinkedList<>();

            cmd.add("C:\\Users\\bob-w\\AppData\\Local\\Android\\Sdk\\platform-tools\\adb.exe");
            cmd.add("pull");
            cmd.add(androidFilePath);
            cmd.add(windowsFilePath);

    ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);

    String line = "null";

            pb.redirectErrorStream(true); // can use these 2 line if you want to see output or errors in file.
            pb.redirectOutput(new File("C:\\Users\\bob-w\\Downloads\\encrypt\\logs.txt"));

    Process p = pb.start();

            while(p == null)
            Thread.sleep(1000);

    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

    Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
    Matcher matcher;


            while ((line = in.readLine()) != null) {
        if (line.matches(pattern.pattern())) {
            matcher = pattern.matcher(line);
            if (matcher.find()) ;
        }

    }

}
        catch (Exception e) {
                e.printStackTrace();
                }
                }
}


