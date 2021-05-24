import java.util.Scanner;


public class Life {

    private static final int N = 1000;
    
    public static void main(String[] args) {
        int count=0,tam;
        int seq[] = new int[N];

        Scanner scan = new Scanner(System.in);
        tam = scan.nextInt();

        for(int i=0;i<tam;i++) {
            seq[i] = scan.nextInt();
            scan.nextLine();
            if(seq[i]==42)count++;  
        }
        System.out.println(count);
    }
    
}
