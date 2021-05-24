import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Queries {
    /*
    
    //fatorial para numeros gigantestos
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    //para depois fazer combinatória
    public static long nCr(int n, int p) {
        BigInteger factorialN = factorial(n);
        BigInteger factorialK = factorial(p);
        BigInteger factorialNMinusP = factorial(n - p);
        return factorialN.divide(factorialK.multiply(factorialNMinusP)).longValue();
    }

    */
    
    //OU simplesmente formula de combinatoria de n, 2 a 2: 

    public static int nCr2(int n) {
        return n * (n-1) / 2;
    }

    public static void bsearch(int soma[], int low, int high, int key) {
        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (key == soma[middle]) {
                FastPrint.out.println(soma[middle]); // Se encontrar numero igual no array soma, imprime exatamente esse
                                                     // numero
                return; // Para sair de função void
            } else if (key < soma[middle])
                high = middle - 1;
            else
                low = middle + 1;
        }

        if (low < soma.length) { // Se o numero dado for menor que o numero que estamos a procurar então
            if (low == 0)
                FastPrint.out.println(soma[low]); // Se low=0 significa que o numero que estamos a procurar é menor que
                                                  // o menor numeor do array soma e por isso o numero mais proximo será
                                                  // soma[0]

            else if (Math.abs(key - soma[low]) < Math.abs(key - soma[high]))
                FastPrint.out.println(soma[low]); // Se |diferença entre o numero que queremos e soma[low]| for menor do
                                                  // que soma[low-1] então queremos soma[low]

            else if (Math.abs(key - soma[low]) == Math.abs(key - soma[high]))
                FastPrint.out.println(soma[high] + " " + soma[low]); // Se essa diferença for igual então queremos os
                                                                        // dois numeros
            else
                FastPrint.out.println(soma[high]); // Senão queremos o numero atras de soma[low] porque está mais
                                                      // proximo da key
        } else if (high == soma.length-1)
            FastPrint.out.println(soma[high]);

    }

    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int n = scan.nextInt();
        int conjunto[] = new int[n];

        for (int i = 0; i < n; i++) {
            conjunto[i] = scan.nextInt();
        }
        int qSize = scan.nextInt();
        int queries[] = new int[qSize];

        for (int i = 0; i < qSize; i++) {
            queries[i] = scan.nextInt();
        }

        // Array que contem todas as somas possiveis do array conjunto(input) ->
        // Combinações de n, 2 a 2
        int soma[] = new int[nCr2(n)];
        
        int incr = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                soma[incr] = conjunto[i] + conjunto[j];
                incr++;
            }
        }

        Arrays.sort(soma); // ordenar array soma por ordem crescente
           
        for (int i = 0; i < qSize; i++) {
            bsearch(soma, 0, soma.length - 1, queries[i]);
        }
        
        FastPrint.out.close();
    }

}
