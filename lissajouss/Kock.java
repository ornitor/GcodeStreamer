import java.text.DecimalFormat;

public class Kock
{

    public double x;
    public double y;
    public GcodeSender gcoder;    
    
   public static void main(String[] args) throws Exception 
   {
       Kock k = new Kock();
       k.gcoder = new GcodeSender();
       k.gcoder.initialize(k.gcoder.getList()[0]);
       k.polyKock();
       k.gcoder.close();
   }
    
   public  void polyKock(){
        for(int k=0;k<10;k++){
            Kock(220.*(10.- k)/10.);
             }
    }
        
  public  void polyKockInv(){
        for(int k=9;k>=0;k--){
            Kock(220.*(10.- k)/10.);
             }
    }
        
    public void Kock( double lado)
    {
       x = -lado/2+10;
       y = -lado/3;
       gcoder.sendG1(x, y,8000);
       for(int j=0;j<3;j++){
           ladoKock(lado, j*2.*Math.PI/3.);
            
       }
    }
    
    public void ladoKock(double tamanho,double ang)
    {
        if(tamanho > 20){
            ladoKock(tamanho/3,ang);
            ladoKock(tamanho/3,ang-Math.PI/3);
            ladoKock(tamanho/3,ang+Math.PI/3);
            ladoKock(tamanho/3,ang);
        } else {
            x = x + tamanho*Math.cos(ang);
            y = y + tamanho*Math.sin(ang);
            gcoder.sendG1(x,y,8000);
        }
    }
    
    

    
}
