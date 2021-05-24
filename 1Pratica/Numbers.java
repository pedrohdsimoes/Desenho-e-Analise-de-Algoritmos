import java.util.Scanner;

public class Numbers {
    private static final int N = 50;

    //função que calcula soma dos digitos
    public static int somaN(int numero){
        int soma=0;

        while(numero!=0) {
            soma += numero % 10;
            numero /= 10;
        }

    return soma;
    }
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int casos,desejado,init;
    
        casos = scan.nextInt();

        for(int i=0;i<casos;i++) {
            init = scan.nextInt(); desejado = scan.nextInt();
            if(somaN(init)==desejado)init++;
            while(somaN(init) != desejado) {
                init++;
            }
            System.out.println(somaN(init));
            System.out.println(init);

        }
        
    }
}
