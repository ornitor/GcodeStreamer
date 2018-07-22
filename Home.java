import java.text.DecimalFormat;

public class Home
{
    public double x;
    public double y;
    static double L = 80;
    GcodeSender gcoder;  
        
   public static void main(String[] args) throws Exception 
   {
       PolyTriangle pt = new PolyTriangle();
       PatternsColection e = new PatternsColection();
       Kock k = new Kock();
       pt.gcoder = new GcodeSender();
       pt.gcoder.initialize();
       pt.gcoder.homing();
       pt.gcoder.centerHoming();
       e.quadradalInv();
       pt.gcoder.close();
    }
 
 

}
