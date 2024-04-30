import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++) {
            list.add(Integer.parseInt(st.nextToken()));
            if(i%2==1) {
                Collections.sort(list);
                System.out.print(list.get(list.size()/2)+" ");
            }
        }
    }
}