import java.io.*;
import java.util.*;

class Letra implements Comparable<Letra> {
    public int frequency;
    public int position;

    Letra(int f, int p) {
        frequency = f;
        position = p;
    }

    @Override
    public int compareTo(Letra a) {
        if(frequency < a.frequency) return +1;
        if(frequency > a.frequency) return -1;
        if( position < a.position ) return -1;
        return 0;
    }
}
public class ADN {
    public static void main (String[] args) {

        FastScanner scan = new FastScanner(System.in);

        Letra v[] = new Letra[26];

        for(int i=0; i<26; i++) {
            v[i] = new Letra(0,-1);
        }

        String adn = scan.next();

        for(int i=0; i<adn.length();i++) {
            int l = adn.charAt(i) - 'A';    //posição da letra no array de 0 a 26 em que A é 0, B é 1 ...

            if(v[l].position==-1) v[l].position=i;  //guarda a primeira posiçao da letra
            v[l].frequency++;
        }

        Arrays.sort(v);

        for(int i=0; i<v.length; i++) {
            if(v[i].frequency==0) continue;
            FastPrint.out.println(adn.charAt(v[i].position) + " " + v[i].frequency);
            
        }

        FastPrint.out.close();    
    }    
          
  
}
