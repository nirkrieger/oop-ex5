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

        try (FileReader file = new FileReader(path)) {
            BufferedReader buffer = new BufferedReader(file);
            Scanner s = new Scanner(buffer);
            Pattern mySeq = Pattern.compile("[a-z]+#");
            Matcher findSeq = mySeq.matcher();
            System.out.println(s.next());
            System.out.println(s.next().split("#"));
        }
        catch (IOException n) {
            System.out.println("error");
        }

    }
}
