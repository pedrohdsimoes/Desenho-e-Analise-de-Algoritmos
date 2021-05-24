
public class Troco {

    public static void coins(int size, int type[], int nCoins) {
        int coins[] = new int[size+1];
        int use[] = new int[size+1];
       
        
        coins[0] = 0;
        for(int i=1; i<=size; i++) {
            coins[i] = 10001;
            for(int j=1; j<=nCoins; j++){
                if(type[j] <= i && 1 + coins[i-type[j]] < coins[i] ) {
                    coins[i] = 1 + coins[i-type[j]];
                    use[i] = type[j];
                }
            }
           
        }
       
        System.out.print(size+": ");
        System.out.print("["+coins[size]+"]");
        int i=1;
        while(size>0) {
            System.out.print(" " + use[size]);
            i++;
            size -= use[size];
            
        }
        
        System.out.println();
    }
   public static void main(String[] args) {
       FastScanner scan = new FastScanner(System.in);

       int nCoins = scan.nextInt();
       int type[] = new int[nCoins+1];
       for(int i=1; i<=nCoins; i++) {
           type[i] = scan.nextInt();
       }
       int nQueries = scan.nextInt();
       int queries[] = new int[nQueries+1];
       for(int i=1; i<=nQueries; i++) {
           queries[i] = scan.nextInt();
           coins(queries[i], type, nCoins);
       }
      
      
   } 
}
