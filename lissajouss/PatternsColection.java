
/**
 * Write a description of class PatternsColection here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PatternsColection
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class PatternsColection
     */
    public PatternsColection()
    {
        // initialise instance variables
        x = 0;
    }


    public int sampleMethod(int y)
    {
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
        
        public static double triangleX(double ang, int n)
        {
            
        }
}
