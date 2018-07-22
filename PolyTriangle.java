import java.text.DecimalFormat;

public class PolyTriangle
{
    
    static double w1 = 11, w2 = 12;
    static double dt=0.2/((w1+w2)/2);
    
    public double[] x;
    public GcodeSender gcoder;
    
    
   public static void main(String[] args) throws Exception 
   {
       PolyTriangle pt = new PolyTriangle();
       pt.gcoder = new GcodeSender();
       pt.gcoder.initialize();
       pt.drawPolyTriangleInv();
       pt.drawPolyTriangle();
       pt.gcoder.close();
 
   }
    
   public void drawPolyTriangle(){
        for(int k=0;k<6;k++){
            for(int i=0;i<10;i++){
                for(int j=0;j<3;j++){
                   x = triangle(115.*(10.- k)/10., i*2.*Math.PI/30., j );
                   gcoder.sendG1(x[0], x[1],8000); 
                }
            }
        }
    }
   
    public void drawPolyTriangleInv(){
        for(int k=0;k<10;k++){
            for(int i=0;i<10;i++){
                for(int j=0;j<3;j++){
                   x = triangle(115.*(k+1)/10., i*2.*Math.PI/30., j );
                   gcoder.sendG1(x[0], x[1],8000); 
                }
            }
        }
    }
        
    public double [] triangle(double raio, double ang, int iv)
    {
        double vertice[] = new double[2];
        vertice[0] = raio*Math.cos(ang + iv*2*Math.PI/3);
        vertice[1] = raio*Math.sin(ang + iv*2*Math.PI/3);
        return vertice;
    }

    
     
}
