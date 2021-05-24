import java.util.*;

public class Sequence {

    public static int bs1(int sequence[],int a, int b) {
        int max = -2001;
        int sum = 0;
        for(int i=b; i>=a; i--) {
            sum += sequence[i];
            if(sum > max) max = sum;
        }
        return max;
    }

    public static int bs2(int sequence[],int a, int b) {
        int max = -2001;
        int sum = 0;
        for(int i=a; i<=b; i++) {
            sum += sequence[i];
            if(sum > max) max = sum;
        }
        return max;
    }

    public static int sms(int sequence[],int a, int b) {
        int meio = 0;
        int best1 = -2001,best2 = -2001, best3 = -2001;
        if(a==b) return sequence[a];
        meio = (a+b)/2;
        best1 = sms(sequence, a, meio);
        best2 = sms(sequence, meio+1, b);
        best3 = bs1(sequence, a, meio) + bs2(sequence, meio+1, b);
        
        return Math.max( Math.max(best1, best2) , best3);
    }
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int n = scan.nextInt();
        int sequence[] = new int[n];
        for(int i=0; i<n; i++) {
            sequence[i] = scan.nextInt();
        }
        FastPrint.out.println(sms(sequence,0, sequence.length-1));
        FastPrint.out.close();
    }
   
}
