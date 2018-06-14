import java.text.DecimalFormat;

import java.util.concurrent.TimeUnit;


public class Lissajous {
    
    static DecimalFormat d = new DecimalFormat("0.0");
    static double w1 = 11, w2 = 12;
    static double x=0,y=0,dt=0.2/((w1+w2)/2);
    
    public static void main(String[] args) throws Exception {
        GcodeSender gcoder = new GcodeSender();
        gcoder.initialize();
        for(int i=1;i<3000;i++){
            x = 115*Math.sin(w1*i*dt + Math.PI/4 );
            y = 130*Math.sin(w2*i*dt - Math.PI/4 ); 
            gcoder.sendG1(x, y,8000);
        }
        gcoder.close();
    }
}


