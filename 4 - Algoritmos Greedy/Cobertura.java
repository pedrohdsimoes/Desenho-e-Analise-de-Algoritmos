import java.util.*;
import java.io.*;

//classe para guardar segmento
class Segment implements Comparable<Segment> {
    public int posI;
    public int posF;

    Segment(int i, int f) {
        posI = i;
        posF = f;
    }

    @Override
    public int compareTo(Segment s) {
        if (posI < s.posI) return -1; // ordena por ordem crescente de posição inicial
        if (posI > s.posI) return +1;
        if (posF < s.posF) return +1; //ordena por ordem descrescente da posição final
        if (posF > s.posF) return -1; 
        return 0;
    }
}

public class Cobertura {

    public static int minorC(Segment s[], int seg, int sizeC) {
        int count = 1;
        int end = s[0].posF;
        int max ;
        for (int i = 1; i < seg; i++) {
            if (s[i].posI <= end && s[i].posF > end) {
                max = s[i].posF;
                for(int j=i+1; j<seg; j++) {
                    if (s[j].posI <= end && s[j].posF > end) {
                        if(s[j].posF > max) max = s[j].posF;
                    }
                }
                    end = max;
                    count++;
            }
            if(end >= sizeC) {
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int sizeC = scan.nextInt();
        int seg = scan.nextInt();
        Segment s[] = new Segment[seg];
        for (int i = 0; i < seg; i++) {
            s[i] = new Segment(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(s);
       
        FastPrint.out.println(minorC(s, seg, sizeC));

        FastPrint.out.close();
    }
   
}