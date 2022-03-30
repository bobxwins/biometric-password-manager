 package com.example.biom;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;

import com.example.biom.MainActivity;

public class FileUtils {

  // readAllBytes(String FileName)
  // Reads all bytes in a file
  // The method merely hides the use of Paths.

  public static byte[] readAllBytes(String inputFile) {
    byte[] bytesRead = {};
    try {
      bytesRead = Files.readAllBytes(Paths.get(inputFile));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bytesRead;
  }

  // write(String FileName, byte[] output)
  // The method merely "hides" the use of Paths.
  // Overwrites if the file exists already; otherwise creates the file
  // This behavior is due to java.nio.file.Files.write

  public static void write(String outputFile, byte[] output) {
    try {

      Files.write(Paths.get(outputFile), output);



    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // getAllFileNames:
  // returns a list of all filenames (strings) in a directory
  // that have the specified extension

  // getAllFileNames/3:
  // file names must have both a certain filename and a certain extension

  public static String[] getAllFileNames(String dir, String name, String ext) {
    ArrayList<String> results = new ArrayList<String>();
    File[] files = new File(dir).listFiles();
    for (File file : files) {
      if (file.isFile()) {
        String fname = file.getName();
        String[] parts = fname.split("[.]");
        if (parts[0].equals(name) && parts[parts.length-1].equals(ext)) results.add(fname);
      }
    }
    String[] names = new String[results.size()];
    for (int i=0; i<names.length; i++) {
      names[i] = results.get(i);
    }
    return names;
  }

  // getAllFileNames/2: file names need only have a certain extension

  public static String[] getAllFileNames(String dir, String ext) {



    ArrayList<String> results = new ArrayList<String>();
    File[] files = new File(dir).listFiles();
    for (File file : files) {
      if (file.isFile()) {
        String fname = file.getName();
        String[] parts = fname.split("[.]");
        if (parts[parts.length-1].equals(ext)) results.add(fname);
      }
    }
    String[] names = new String[results.size()];
    for (int i=0; i<names.length; i++) {
      names[i] = results.get(i);
    }
    return names;
  }

}
