

public class Redes {
    static int n;
    static boolean adj[][];
    static boolean visited[];

    static void dfs(int v) {
        visited[v]=true;
       
        for(int i=1; i<=n; i++) 
            if(adj[v][i] && !visited[i]){
                dfs(i);
                
                }
    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        n=scan.nextInt();
        adj = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        int edges = scan.nextInt();
        for(int i=0; i<edges; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            adj[a][b] = adj[b][a] = true;
        }

        int count = 0;
        for(int i=1; i<=n; i++) 
            if(!visited[i]) {
                count++;
                dfs(i);
            }
        
        System.out.println(count);
        
    }   
}
