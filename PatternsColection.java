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
       PatternsColection e = new PatternsColection();
       Kock k = new Kock();
       pt.gcoder = new GcodeSender();
       pt.gcoder.initialize();
       pt.gcoder.centerHoming();
       e.gcoder = pt.gcoder;
       k.gcoder = pt.gcoder;
           //pt.drawPolyTriangle();
       for(int i=0;i<20;i++){
           e.espiralInv();
           k.polyKock();
           k.polyKockInv();
           pt.drawPolyTriangle();
       }
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
   public void quadradal(){
       x = 0;
       y = 0;
       gcoder.sendG1(x, y,8000);
       for(int k=0;k<30;k++){
           x = -115.*((k+1)/30.);
           y = 115.*(k/29.);
           gcoder.sendG1(x,y,8000);
           y = -115.*(k/29.);
           gcoder.sendG1(x,y,8000);
           x = +115.*((k+1.)/30);
           gcoder.sendG1(x,y,8000);
           y = 115.*(k/29.);
           gcoder.sendG1(x,y,8000);          
       }
  }
    public void quadradalInv(){
       x = 0;
       y = 0;
       gcoder.sendG1(x, y,8000);
       for(int k=0;k<30;k++){
           x = -115.*((k+1)/30.);
           y = -115.*(k/29.);
           gcoder.sendG1(x,y,8000);
           x = +115.*((k+1)/30.);
           gcoder.sendG1(x,y,8000);
           y = +115.*(k/29.);
           gcoder.sendG1(x,y,8000);
           x = -115.*((k+1)/30.);
           gcoder.sendG1(x,y,8000);  
           y = -115.*(k/29.);
           gcoder.sendG1(x,y,8000);  
  
       }
  }
 public void espiralInv(){
       x = 0;
       y = 0;
       gcoder.sendG1(x, y,8000);

       for(int k=0;k<20;k++){
            for(int i=0;i<50;i++){
                        x = -115.*((50*(k+1) + (i+1))/1050.)*Math.cos(2.*Math.PI*i/50.);
                        y = -115.*((50*(k+1) + (i+1))/1050.)*Math.sin(2.*Math.PI*i/50.);
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
