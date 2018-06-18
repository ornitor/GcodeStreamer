import java.text.DecimalFormat;

public class PatternsColection
{
    

    public double x;
    public double y;
    static double L = 80;
    GcodeSender gcoder;  
        
    
   public static void main(String[] args) throws Exception 
   {
       PolyTriangle pt = new PolyTriangle();
       pt.gcoder = new GcodeSender();
       pt.gcoder.initialize();
       //pt.drawPolyTriangle();
       PatternsColection e = new PatternsColection();
       e.gcoder = pt.gcoder;
       e.quadrado(115);
       e.circulo(115);
       e.espiral();
       Kock k = new Kock();
       k.gcoder = pt.gcoder;
       k.polyKock();
       pt.gcoder.close();
   }
    
   public void espiral(){
       x = -115;
       y = 0;
       gcoder.sendG1(x, y,8000);

       for(int k=0;k<20;k++){
            for(int i=0;i<50;i++){
                        x = -115.*((50*(20.-k) + (50.-i))/1050.)*Math.cos(2.*Math.PI*i/50.);
                        y = -115.*((50*(20.-k) + (50.-i))/1050.)*Math.sin(2.*Math.PI*i/50.);
                        gcoder.sendG1(x,y,8000);
                    }
       }
  }
        
  public void quadrado(double lado){
      gcoder.sendG1(-lado, lado,8000);
      gcoder.sendG1(-lado, -lado,8000);
      gcoder.sendG1(lado, -lado,8000);
      gcoder.sendG1(lado, lado,8000);
      gcoder.sendG1(-lado, lado,8000);    
  }
  
    public void circulo(double lado){
       x = -lado;
       y = 0;
       gcoder.sendG1(x, y,8000);    
       for(int i=0;i<50;i++){
                        x = -lado*Math.cos(2.*Math.PI*i/50.);
                        y = -lado*Math.sin(2.*Math.PI*i/50.);
                        gcoder.sendG1(x,y,8000);
                    }
   
  }
  

}
