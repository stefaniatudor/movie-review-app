package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static String extractTextFromFile(FileType fileType) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/input/" + fileType.toString().toLowerCase() + ".json";
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(path));


        String text = "";
        String line = reader.readLine();
        while (line!=null){
            text += line.trim();
            line = reader.readLine();
        }
        reader.close();
        return text;

    }

    public static String curateString(String string){
        string = string.trim();
        string = string.substring(1,string.length()-1);
        return string;
    }

    public static Double curateDuration(String duration){
        duration = duration.trim();
        duration= duration.replaceAll("(?!\\.)\\D","");
        return Double.valueOf(duration);
    }

    public enum FileType{
        ACCOUNTS,
        ACTORS,
        PRODUCTION,
        REQUESTS
    }
}
