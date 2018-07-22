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
       String portas[] = pt.gcoder.getList();
       GcodeSender.printList(portas);
       pt.gcoder.initialize(portas[0]);
       pt.gcoder.homing();
       pt.gcoder.centerHoming();
       e.gcoder = pt.gcoder;
       e.quadradalInv();
       pt.gcoder.close();
    }
}
