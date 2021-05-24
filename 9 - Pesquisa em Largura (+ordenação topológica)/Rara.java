import java.util.*;

public class Rara {
    static int words;
    static boolean visited[];
    static boolean adj[][];
    static LinkedList<Character> order;
    static String w[];
    public static void dfs(int v) {
        visited[v] = true;
        for(int i=0; i<26; i++) 
            if(adj[v][i] && !visited[i]) {
                dfs(i);
            }
            order.addFirst((char)(v+'A'));
            
    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);
        order = new LinkedList<>();
        w = new String[101];
        boolean positions[] = new boolean[27];
        visited = new boolean[27];
        adj = new boolean[27][27];

        int words = scan.nextInt();

        for(int i=0; i<words; i++) 
            w[i] = scan.next();

        for(int i=0; i<words-1; i++){
            int prim = w[i].length();
            int sec = w[i+1].length();
            int menor = prim <= sec ? prim : sec;
            for(int j=0; j<menor; j++) {
                if(w[i].charAt(j)!=w[i+1].charAt(j)) {
                    //System.out.println(w[i].charAt(j)+ " " + (w[i+1].charAt(j)));
                    adj[w[i].charAt(j)-'A'][w[i+1].charAt(j)-'A'] = true;
                    positions[w[i].charAt(j)-'A'] = positions[w[i+1].charAt(j)-'A'] = true;
                    
                    break;
                }
            }
        }
        for(int i=0; i< 26; i++)
            if(!visited[i])
                dfs(i);

        
        for(char i : order) 
           if(positions[i-'A']) System.out.print(i);
        System.out.println();
            
        

    }
}