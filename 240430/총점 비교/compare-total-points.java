import java.util.*;
import java.io.*;

class Person {
    String n;
    int l;
    int e;
    int m;

    public Person(String n, int l, int e, int m) {
        this.n = n;
        this.l = l;
        this.e = e;
        this.m = m;
    }
}


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Person[] arr = new Person[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, (a,b)-> (a.l+a.e+a.m)-(b.l+b.e+b.m));

        for(Person p : arr) {
            System.out.println(String.format("%s %d %d %d", p.n, p.l, p.e, p.m));
        }
    }
}