public class Intersecao {

    public static boolean quadradoDentroCirculo(double qx, double qy, double ql,double cx, double cy, double cr) {
        double x0, y0, x2, y2, r2, cie, cse, cid, csd;
        x0 = qx;
        y0 = qy;
        x2 = qx + ql;
        y2 = qy + ql;

        //formula matematica para saber se o ponto se encontra no circulo (x-cx)^2-(y-cy)^2 < r^2
        r2 = cr*cr;
        cie = Math.pow((cx-x0), 2) + Math.pow((cy-y0), 2);  //canto inferior esquerdo
        cse = Math.pow((cx-x0), 2) + Math.pow((cy-y2), 2);  //canto superior esquerdo
        cid = Math.pow((cx-x2), 2) + Math.pow((cy-y0), 2);  //canto inferior direito
        csd = Math.pow((cx-x2), 2) + Math.pow((cy-y2), 2);  //canto superior direito

        if( (cie<r2) && (cse<r2) && (cid<r2) && (csd<r2) ) return true; //se todos os pontos do quadrado tiverem dentro 
                                                                        //do circulo, entao o quadrado está dentro

        else return false;
    } 
    public static boolean circuloDentroQuadrado(double qx, double qy, double ql,double cx, double cy, double cr) {
        double x0, y0, x2, y2, cie, cse, cid, csd;
        
        x0 = qx;
        y0 = qy;
        x2 = qx + ql;
        y2 = qy + ql;

        //pontos extremos do circulo 
        //(ajuda pensar no circulo com um quadrado a volta e pensar como verificar se esse quadrado está dentro do Quadrado original )
        cie = cx - cr;
        cid = cx + cr;
        cse = cy - cr;
        csd = cy + cr;

        //se o canto inferior esquerdo (cie) está entre x0 e x2 , esse ponto encontra-se no quadrado
        //se o canto inferior direito (cid) está entre x0 e x2 , esse ponto encontra-se no quadrado
        //se o canto superior esquerdo (cse) está entre y0 e y2 , esse ponto encontra-se no quadrado
        //se o canto superior direito (csd) está entre y0 e y2 , esse ponto encontra-se no quadrado
        if( (cie>=x0 && cie<=x2) && (cid>=x0 && cid<=x2) && (cse>=y0 && cse<=y2) && (csd>=y0 && csd<=y2) )
        return true;

        else return false;
    } 
    
    public static boolean fora(double qx, double qy, double ql,double cx, double cy, double cr) { 
        double x0, y0, x2, y2, topCircle, leftCircle, rightCircle, downCircle;
        
        x0 = qx;
        y0 = qy;
        x2 = qx + ql;
        y2 = qy + ql;

        topCircle = cy + cr;
        leftCircle = cx - cr;
        rightCircle = cx + cr;
        downCircle = cy - cr;

        //para o circulo estar fora do quadrado
        //o topo do circulo nao pode passar de y0
        //a parte esquerda do circulo não pode passar x2
        //a parte de baixo do circulo não pode passar y2
        //a parte direita do circulo não pode passar x0
        if((topCircle<=y0) || (leftCircle>=x2) || (downCircle>=y2) || (rightCircle<=x0)) return true;

        else return false;
    } 
    
    //Divide and Conquer
    public static double intersecao(double qx, double qy, double ql, double cx, double cy, double cr) {
        double aQuadrado, aCirculo, area;
        aQuadrado = ql * ql;
        aCirculo = Math.PI * cr * cr;

        //casos base
        if(fora(qx, qy, ql, cx, cy, cr)) return 0;
        else if(quadradoDentroCirculo(qx, qy, ql, cx, cy, cr)==true) return aQuadrado;
        else if(circuloDentroQuadrado(qx, qy, ql, cx, cy, cr)==true) return aCirculo;

        
        //recursão
        else if(ql>0.001) {
            ql /= 2;
            area = 0;

            area += intersecao(qx, qy, ql, cx, cy, cr);         //canto inferior esquerdo
            area += intersecao(qx + ql, qy, ql, cx, cy, cr);         //canto canto inferior direito
            area += intersecao(qx, qy + ql, ql, cx, cy, cr);         //canto superior esquerdo
            area += intersecao(qx + ql, qy + ql, ql, cx, cy, cr);         //canto superior direito
        return area;
        }
        
    else return 0;
    }

    public static void main(String[] args) {
        int casos, qx , qy, ql, cx, cy, cr;

        FastScanner scan = new FastScanner(System.in);

        casos = scan.nextInt();

        for(int i=0; i<casos; i++) {
            qx = scan.nextInt();
            qy = scan.nextInt();
            ql = scan.nextInt();
            cx = scan.nextInt();
            cy = scan.nextInt();
            cr = scan.nextInt();

            FastPrint.out.println(intersecao(qx, qy, ql, cx, cy, cr));
            
        }
        FastPrint.out.close();
    }
    
}
