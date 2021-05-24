

public class Celulas {
    
    static int incrx[] = {-1,+1,0,0,-1,-1,+1,+1};
    static int incry[] = {0,0,-1,+1,-1,+1,-1,+1};
    static int n;
    static int rows;
    static int cols;
    static char[][] matrix;
    static boolean[][] visited;
    static int aux;
    static int max;

    static int dfs(int x, int y) {
        if (x<0 || x>=rows || y<0 || y>=cols) return 0; 
        if (visited[x][y] || matrix[x][y] == '.') return 0;
        visited[x][y] = true;
        int count = 1;
       for(int i=0; i<8; i++)
        count += dfs(x+incrx[i],y+incry[i]);
        
        return count;
        
    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);
       
        n = scan.nextInt();
        for (int k=0; k < n; k++) {
            rows = scan.nextInt(); cols = scan.nextInt();
            matrix = new char[rows][cols];
            visited = new boolean[rows][cols];
            max = Integer.MIN_VALUE;
            aux = 0;
            for (int i=0; i < rows; i++) {
                matrix[i] = scan.next().toCharArray();
            }
            for (int j=0; j < rows; j++) {
                for (int w=0; w < cols; w++) {
                    aux = dfs(j,w);
                    if (aux >= max) max = aux;
                }
            }
            System.out.println(max);
        }
    }
}

