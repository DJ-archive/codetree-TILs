import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        String answer = new String(charArr);
        System.out.println(answer);
    }
}