import java.util.*;
import java.io.*;

class Person {
    int num;
    int h;
    int w;

    public Person(int num, int h, int w) {
        this.num = num;
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

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Person(i+1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, (a,b) -> {
            if(a.h == b.h) {
                if(a.w == b.w) {
                    return a.num - b.num;
                } else {
                    return b.w - a.w;
                }
            } else {
                return b.h - a.h;
            }
        });

        for(Person p : arr) {
            System.out.println(String.format("%d %d %d", p.h, p.w, p.num));
        }
    }
}