package lab5.zadaca2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid;

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni

        valid = isValidXML(redovi);
        System.out.println(valid);

        br.close();
    }

    private static int isValidXML(String[] redovi) {
        Stack<String> openingTags = new Stack<>();
        //Stack<String> closingTags = new Stack<>();
        for(int i=0; i<redovi.length; i++){
            if(isOpeningTag(redovi[i])){
                openingTags.push(redovi[i]);
            }
            else if(isClosingTag(redovi[i])){
                if(!areCompatibleTags(openingTags.pop(), redovi[i])){
                    return 0;
                }
            }
        }
        return 1;
    }

    private static boolean areCompatibleTags(String open, String close) {
        return open.substring(1).equals(close.substring(2));
    }

    private static boolean isClosingTag(String tag) {
        return tag.charAt(0) == '[' &&
                tag.charAt(1) == '/' &&
                tag.charAt(tag.length()-1) == ']';
    }

    private static boolean isOpeningTag(String tag) {
        return tag.charAt(0) == '['
                && tag.charAt(tag.length() - 1) == ']'
                && tag.charAt(1) != '/';
    }
}
