package fileprocessing.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class myTests {

    public static void main (String [] args) {
        String path = "C:\\Users\\mohrw\\Desktop\\degree\\second semester\\oop\\oop-ex5\\fileprocessing\\filters\\myTester.txt";

//        try (FileReader file = new FileReader(path)) {
//            BufferedReader buffer = new BufferedReader(file);
//            Scanner s = new Scanner(buffer);
//            String text = s.next();
//            Pattern mySeq = Pattern.compile("([A-Z]|[a-z]+)#(\\d+)");
//            Matcher findSeq = mySeq.matcher(text);
//            while (findSeq.find()) {
//                System.out.println(s.next().substring(findSeq.start(), findSeq.end()));
//
//            }
//
//        }
//        catch (IOException n) {
//            System.out.println("error");
//        }
        String test = "greater_Than#3#42";
        Pattern mySeq = Pattern.compile("(\\w+)#(\\d+)#(\\d+)");
        Matcher findSeq = mySeq.matcher(test);
        if (findSeq.matches()) {
            System.out.println(findSeq.group(1)+findSeq.group(2));
        }
    }
}
