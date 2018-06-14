import java.text.DecimalFormat;

public class PatternsColection
{
    
    static double w1 = 11, w2 = 12;
    static double dt=0.2/((w1+w2)/2);
    
    public double[] x;
        
    
   public static void main(String[] args) throws Exception 
   {
        polyTriangle();
   }
    
   public static void polyTriangle(){
        GcodeSender gcoder = new GcodeSender();
        PatternsColection pt = new PatternsColection();
        gcoder.initialize();
        for(int k=0;k<10;k++){
            for(int i=0;i<10;i++){
                for(int j=0;j<3;j++){
                    pt.x = pt.triangle(115.*(10.- k)/10., i*2.*Math.PI/30., j );
                    gcoder.sendG1(pt.x[0], pt.x[1],8000); 
                }
            }
        }
        gcoder.close();
    }
        
    public double [] triangle( double raio, double ang, int iv)
    {
        double vertice[] = new double[2];
        vertice[0] = raio*Math.cos(ang + iv*2*Math.PI/3);
        vertice[1] = raio*Math.sin(ang + iv*2*Math.PI/3);
        return vertice;
    }
    
}
