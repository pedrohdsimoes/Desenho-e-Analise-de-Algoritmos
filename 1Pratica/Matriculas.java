import java.util.Scanner;

public class Matriculas {

    //Converte as letras das matriculas em inteiros e exclui as letras K, W e Y
    public static int letterToInt (char ch) {
        if(ch>'J' && ch<='V')return ch - 'A' - 1;
        else if(ch=='X') return ch - 'A' - 2;
        else if(ch>'X') return ch - 'A' -3;
        return ch - 'A';
    }

    //Identifica a geração de cada matricula
    public static int geracao(String matricula) {

        char bloco1 = matricula.charAt(0);
        char bloco2 = matricula.charAt(3);
        char bloco3 = matricula.charAt(6);

        int gen;

        if(Character.isLetter(bloco1)==true && Character.isDigit(bloco3)) gen=1;        // XX-NN-NN
        else if(Character.isLetter(bloco3)==true && Character.isDigit(bloco1)) gen=2;   // NN-NN-XX
        else if(Character.isLetter(bloco2)==true) gen=3;                                // NN-XX-NN
        else gen=4;                                                                     // XX-NN-XX
        
    return gen;
    }
    
    //Converte as Matriculas em inteiros
    public static int convertMatriculaToInt(String m1) {
        int d1=0,b1,b2,b3;
        String bloco1,bloco2,bloco3;

        switch(geracao(m1)) {
            case 1 : 
                bloco2 = m1.substring(3,5); b2=Integer.parseInt(bloco2);
                bloco3 = m1.substring(6,8); b3=Integer.parseInt(bloco3);
                d1 = (23*letterToInt(m1.charAt(0))*10000) + (letterToInt(m1.charAt(1))*10000) +b2*100 + b3;
                break;
            
            case 2 :
                bloco1 = m1.substring(0,2); b1=Integer.parseInt(bloco1);
                bloco2 = m1.substring(3,5); b2=Integer.parseInt(bloco2);
                d1 = 5290000 + (23*letterToInt(m1.charAt(6))*10000) + (letterToInt(m1.charAt(7))*10000) + b1*100 + b2;
                break;

            case 3 :
                bloco1 = m1.substring(0,2); b1=Integer.parseInt(bloco1);
                bloco3 = m1.substring(6,8); b3=Integer.parseInt(bloco3);
                d1 = 10580000 + (23*letterToInt(m1.charAt(3))*10000) + (letterToInt(m1.charAt(4))*10000) + b1*100 + b3;
                break;
            
            case 4 :
                bloco2 = m1.substring(3,5); b2=Integer.parseInt(bloco2);
                d1 = 15870000 + (23*23*23*letterToInt(m1.charAt(0))*100) + (23*23*letterToInt(m1.charAt(1))*100) + (23*letterToInt(m1.charAt(6))*100) + (letterToInt(m1.charAt(7))*100) + b2;   
                break;      
        }
    return d1;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int casos,resultado;
        String m1,m2;

        casos = scan.nextInt();

        for(int i=0;i<casos;i++) {
            m1=scan.next();
            m2=scan.next();
            resultado = convertMatriculaToInt(m1)-convertMatriculaToInt(m2);
            System.out.println(Math.abs(resultado));
        }
    }
    
}
