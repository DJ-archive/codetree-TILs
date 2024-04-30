import java.util.*;
import java.io.*;

class Person {
    String n;
    int h;
    int w;

    public Person(String n, int h, int w) {
        this.n = n;
        this.h = h;
        this.w = w;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Person[] arr = new Person[n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, (a,b)-> a.h - b.h);

        for(Person p : arr) {
            System.out.println(String.format("%s %d %d", p.n, p.h, p.w));
        }
    }
}