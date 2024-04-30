import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);
        for(int a : list) {
            System.out.print(a+" ");
        }
        System.out.println();

        Collections.sort(list, Collections.reverseOrder());
        for(int a : list) {
            System.out.print(a+" ");
        }
    }
}