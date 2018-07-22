import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;


import java.io.IOException;



import java.util.concurrent.TimeUnit;


public class SerialTest implements SerialPortEventListener {
    
    
    SerialPort serialPort;

    static String perfil = 
            "G1 X100 Y200,\n" +
            "S3 L4 F300 F300\n" +
            "S3 L5 F400 F300\n";

    

    private BufferedReader input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 115200; // 9600; //250000; // 250000;
    
    boolean fgGo = true;
    boolean fgTemDado = false;
    String  dado = "";

        public void initialize( String portName){
            System.out.println("Abrindo "+ portName);
            CommPortIdentifier portId = null;
            Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
            while( portEnum.hasMoreElements() ){
                portId  = portEnum.nextElement();
                if (portId.getName().equals(portName))
                    break;
            }
            if (portId == null) {
                System.out.println("Could not find COM port " + portName);
                return;
            }
            try {
                serialPort = (SerialPort) portId.open("Java Gcode Sender",TIME_OUT);
                input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                output = serialPort.getOutputStream();
                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
                System.out.println("===================================");
                System.out.println(portId.getCurrentOwner());
                System.out.println(portId.getName());
                System.out.println(portId.getPortType());
                System.out.println("===================================");
                serialPort.setSerialPortParams(DATA_RATE,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                
            } catch (Exception e) {
                System.err.println(e.toString());
                e.printStackTrace();
    
            }
    }

    
    public String[] getList()
    {
            
            CommPortIdentifier portId = null;
            int i;
            Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
            for( i = 0; portEnum.hasMoreElements();i++) {
                portId = portEnum.nextElement();
            }       
            String ports[] = new String[i];
            portEnum = CommPortIdentifier.getPortIdentifiers();
            for(i = 0; portEnum.hasMoreElements() && i < ports.length;i++) {
                portId = portEnum.nextElement();
                ports[i] = portId.getName();
            }
            return ports;
    }
    
    public static void printList(String l[])
    {
        for(int i=0;i<l.length;i++)
            System.out.println("Serial " + i + ":" + l[i]);
    }

    public void write(String str)
    {
        try{
            output.write(str.getBytes());
        }catch(IOException err){
                err.printStackTrace();
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public String getDado()
    {
        String str = dado;
        dado = "";
        fgTemDado = false;
        return str.trim();
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                dado = dado + "\n" + inputLine;
                fgTemDado = true;
            } catch (Exception e) {
                System.err.println(e.toString());
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) throws Exception {
        SerialTest xmain = new SerialTest();
        String portas[] = xmain.getList();
        printList(portas);
        xmain.initialize(portas[0]);
        xmain.output.write("G0 X100 Y200\n".getBytes());        
        while(!xmain.fgTemDado)
                TimeUnit.SECONDS.sleep(1);
        System.out.println(xmain.dado);
        xmain.fgTemDado = false;
        xmain.close();
    }
    
}
