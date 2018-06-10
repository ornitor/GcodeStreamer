import java.text.DecimalFormat;

import java.util.concurrent.TimeUnit;


public class Lissajous {
    
    public static void main(String[] args) throws Exception {
        GcodeSender gcoder = new GcodeSender();
        gcoder.initialize();
        while(gcoder.fgTemDado){
                System.out.println(gcoder.getDado());

        }
        gcoder.configSandDrawing();
        double w1 = 11, w2 = 12;
        double x=0,y=0,dt=0.2/((w1+w2)/2);
        DecimalFormat d = new DecimalFormat("0.0");
        for(int i=1;i<3000;i++){
            x = 115 + 115*Math.sin(w1*i*dt + Math.PI/4 );
            y = 130 + 130*Math.sin(w2*i*dt - Math.PI/4 );
            String str = "G1 X"+ d.format(x).replace(",",".") + " Y" +  d.format(y).replace(",",".") + " F2000"+ "\n";
            gcoder.write(str);  
            while(!gcoder.fgTemDado)
                Thread.sleep(100);//TimeUnit.SECONDS.sleep(1);
            System.out.println(i+ ": "+str.trim()+ " " + gcoder.getDado());

        }
        gcoder.close();
    }
}


