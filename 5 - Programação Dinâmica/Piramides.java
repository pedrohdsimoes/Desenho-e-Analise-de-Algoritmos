import java.util.*;

public class Piramides {

    //inicializa o array todo a 0
    public static long[][] pir(int levels) {
        long pir[][] = new long[levels+1][levels+1];
        for(int i=levels; i>0; i--)
            for(int j=1; j<=i; j++) {
                pir[i][j] = 0;
            }
        return pir;
    }
   
    //
    public static boolean broken(long pir[][], int i, int j) {
        if(pir[i][j]==-1) return true;
        return false;
    }

    public static long count(long pir[][], int levels) {
        if(broken(pir, levels, 1)) return 0;
        pir[levels][1] = 1;
        long ways=0;
        for(int i=levels; i>0 ; i--)
            for(int j=levels; j>0; j--) {
                
                if(i<levels && j<=levels-i+1){
                    if(!broken(pir, i, j)) {
                        //i+1 j-1 => esq
                        //i+1 j => dir
                        pir[i][j] = pir[i+1][j] + pir[i+1][j-1];
                    }
                    else pir[i][j] = 0;
                }
            }
        
        for(int j=1; j<=levels; j++)
            if(pir[1][j]>=0) ways += pir[1][j];

    return ways;

    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int levels = scan.nextInt();
        int broken = scan.nextInt();
        int bRow=0, bCol=0;
        long pir[][] = pir(levels);
        
        for(int i=0; i<broken; i++) {
            bRow = scan.nextInt();
            bCol = scan.nextInt();
            pir[bRow][bCol] = -1;
        }

        FastPrint.out.println(count(pir, levels));
        FastPrint.out.close();
    }
}