public class Bipartidos {
    static int n;
    static boolean visited[];
    static boolean adj[][];
    static int nos;
    static int arestas;
    static char color[];
    static boolean bipartido;
    public static void dfs(int v) {
        visited[v] = true;
        
        for(int i=1; i<=nos; i++) {
            if(adj[v][i] && !visited[i]){
               if(color[v]=='R') {
                   color[i]='G';
                   dfs(i);
               }
               else if(color[v]=='G') {
                   color[i]='R';
                   dfs(i);
               }
            }
            if(adj[v][i] && visited[i]) {
                if(color[v]==color[i]) bipartido=false;
            }
        }       
    }
   public static void main(String[] args) {
       FastScanner scan = new FastScanner(System.in);
       
       n = scan.nextInt();
       for(int i=0; i<n; i++) {
            nos = scan.nextInt();
            arestas = scan.nextInt();
            adj = new boolean[nos+1][nos+1];
            visited = new boolean[nos+1];
            color = new char[nos+1];
          
            color[1] = 'R';
            bipartido = true;
            for(int j=1; j<=arestas; j++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                adj[a][b] = adj[b][a] = true;
            }
            for(int k=1; k<=nos; k++) {
                if(!visited[k]) {
                    dfs(k);
                }
            }
            if(bipartido) System.out.println("sim");
            else System.out.println("nao");
       }
   } 
}
