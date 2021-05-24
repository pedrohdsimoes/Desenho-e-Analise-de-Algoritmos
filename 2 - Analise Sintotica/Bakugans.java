public class Bakugans {

    //função utilizada para a naive solution; Desnecessária usando o prefix Sum
    public static int sumEnergy(int posI, int posF, int energy[]) {
        int sum=0;
        for(int i=posI; i<=posF; i++) {
            sum+=energy[i];
        }
    return sum;
    }
   
    public static void main(String[] args) {
        int numBakugans, row, fotos, posI, posF;
        

        FastScanner scan = new FastScanner(System.in);

        numBakugans = scan.nextInt();

        int sumEnergy[] = new int[numBakugans+1];
        sumEnergy[0] = 0;

        for(int i=1; i<=numBakugans; i++) {
            row = scan.nextInt();
            sumEnergy[i] = sumEnergy[i-1] + row;  //prefixSum
        }

        fotos = scan.nextInt();
        for(int j=0; j<fotos; j++) {
            posI = scan.nextInt();
            posF = scan.nextInt();
            FastPrint.out.println(sumEnergy[posF]-sumEnergy[posI-1]); //soma entre posições
        }
        FastPrint.out.close();
        
    }
}