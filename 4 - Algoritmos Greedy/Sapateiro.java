import java.util.*;
import java.io.*;

class Encomenda implements Comparable<Encomenda> {
    public int dias;
    public double multa;
    public int indice;

    Encomenda(int d, double m, int i) {
        dias = d;
        multa = m;
        indice = i+1;
    }
    
    public double getRatio() {
        return multa/dias;
    }

    @Override
    public int compareTo(Encomenda e) {
        if(getRatio() < e.getRatio()) return +1;
        if(getRatio() > e.getRatio()) return -1;
        if(indice < e.indice) return -1;
        return 0;
    }
}
public class Sapateiro {
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int encomendas = scan.nextInt();
        Encomenda e[] = new Encomenda[encomendas];
        for(int i=0; i<encomendas; i++) {
            e[i] = new Encomenda(scan.nextInt(),scan.nextInt(),i);
        }

        Arrays.sort(e);

        for(int i=0; i<encomendas; i++){
            FastPrint.out.print(e[i].indice);
            if(i!=encomendas-1) FastPrint.out.print(" ");
            else FastPrint.out.println();
        }
        FastPrint.out.close();
    }
}
