import java.util.TreeMap; // Mapas (associar valores a chaves)



public class Bakugans {
    public static void main(String[] args) {
        
        TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
        FastScanner scan = new FastScanner(System.in);

        int a = scan.nextInt();
        int r = scan.nextInt();
        int energia;
        Integer count;

        for(int i=0; i<a+r; i++) {
            String ask = scan.next();
            if(ask.equals("BAK")) { 
                energia = scan.nextInt();
                count = m.get(energia);
                 if(count==null) 
                     m.put(energia,1); 
                 else m.put(energia,count+1);
            }
            else if(ask.equals("MIN")) {
                int min = m.firstKey();
                
                if(m.get(min)==1) m.remove(m.firstKey());
                else {
                    m.put(min,m.get(min)-1);
                }
                FastPrint.out.println(min);
            }
            else {
                int max = m.lastKey();
                
                if(m.get(max)==1) m.remove(m.lastKey());
                else {
                    m.put(max,m.get(max)-1);
                }
                FastPrint.out.println(max);
            }

        }

        FastPrint.out.close();
 
    }
}
