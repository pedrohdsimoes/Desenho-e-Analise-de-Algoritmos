import java.util.*;

public class Matrix {

    public static int sms(int matrix[][],int row0, int column0, int row2, int column2) {
        int meioR = 0;
        int meioC = 0;
        int best1 = -2001, best2 = -2001, best3 = -2001, best4 = -2001, best5 = -2001;  
        if(row0==row2 && column0==column2) return matrix[row0][column0];                
        meioR = (row0+row2)/2;
        meioC = (column0+column2)/2;
        best1 = sms(matrix, row0, column0, meioR, meioC);        //1Quadrante
        best2 = sms(matrix, row0, meioC+1, meioR, column2);      //2Quadrante
        best3 = sms(matrix, meioR+1, column0, meioR+1, meioC);   //3Quadrante
        best4 = sms(matrix, meioR+1, meioC+1, row2, column2);    //4Quadrante

        return Math.max( Math.max(best1,best2),Math.max(best3,best4));

    } 
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);

        int row = scan.nextInt();
        int column = scan.nextInt();
        int matrix[][] = new int[row][column];
        for(int i=0; i<row; i++) {
            for(int j=0; j<column; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }
        FastPrint.out.println(sms(matrix, 0, 0, matrix.length, matrix[0].length));
        FastPrint.out.close();
    }
}
