import java.util.*;
import java.io.*;

public class Viagem {
    
    //retorna um array com a soma acumulada até i
    public static int[] prefixSum(int distancia[]) {
        int sum[] = new int[distancia.length];
        sum[0] = distancia[0];
        for(int i=1; i<distancia.length;i++) {
            sum[i] = sum[i-1] + distancia[i];
        }
        return sum;
    }

    //retorna true se for possivel dividir particoes vezes <=x
    public static boolean possible(int distancia[], int particoes, int x) {
        int count=0;
        int originalX= x;
        int prefixSum[] = prefixSum(distancia);
        if(prefixSum[0]>x) return false; // se o primeiro caso for maior que o x dado então retorna falso
        for(int i=0; i<distancia.length; i++) {
            if(prefixSum[i]>x) { //se a soma ate i for maior que x então
                int j = i-1;    
                i--;
                x = originalX + prefixSum[j]; //x passa a ser o x original mais a soma acumulada anterior à mudança de partição
                if(count>particoes-1) return false;
                else count++;
            } 
        }
        count++;
        if(count>particoes) return false;
        return true;
    }


  
    //bsearch a procura de menor X possivel (true)
    public static int bsearch(int distancia[],int low,int high, int particoes) {
        while(low < high) {
            int middle = low + (high-low) / 2;
            if(possible(distancia, particoes, middle)) high = middle;
            else low = middle + 1;
        }
        if(!possible(distancia, particoes, low)) return -1;
        return low;
        
    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);
        
        
        int caminhos = scan.nextInt();
        int distancia[] = new int[caminhos];

        for(int i=0; i<caminhos; i++) {
            distancia[i] = scan.nextInt();
        }
        
        int qSize = scan.nextInt();
        int queries[] = new int[qSize];
        for(int i=0; i<qSize; i++) {
            queries[i] = scan.nextInt();
        }

        int prefixSum[] = prefixSum(distancia);

        for(int i=0; i<qSize; i++) {
            FastPrint.out.println(bsearch(distancia, 1, prefixSum[distancia.length-1], queries[i]));
        }
        FastPrint.out.close();
    }
}


