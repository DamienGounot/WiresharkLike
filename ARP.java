import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ARP {
    
    private byte[] hardwareType = new byte[2];
    private byte[] protocolType= new byte[2];
    private byte[] taille_MAC= new byte[1];
    private byte[] taille_IP= new byte[1];
    private byte[] codeARP= new byte[2];
    private byte[] MAC_src= new byte[6];
    private byte[] IP_src= new byte[4];
    private byte[] MAC_dest= new byte[6];
    private byte[] IP_dest= new byte[4];



    public ARP(ByteBuffer data) {
        data.get(hardwareType);
        data.get(protocolType);
        data.get(taille_MAC);
        data.get(taille_IP);
        data.get(codeARP);
        data.get(MAC_src);
        data.get(IP_src);
        data.get(MAC_dest);
        data.get(IP_dest);
        System.out.println("==================Packet==================");
        System.out.println("Protocole: ARP");
        displayMAC("MAC_src",MAC_src);
        displayIP("IP source",IP_src);
        displayMAC("MAC_dest",MAC_dest);
        displayIP("IP destination",IP_dest);
        System.out.println("==================End of Packet==================");        

    }

    public byte[] getMAC_src() {
        return this.MAC_src;
    }

    public byte[] getIP_src() {
        return this.IP_src;
    }

    public byte[] getMAC_dest() {
        return this.MAC_dest;
    }

    public byte[] getIP_dest() {
        return this.IP_dest;
    }

    private void displayField(String label,byte[] array){
        System.out.print(label+": <");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.println("\n");
    }
    private void displayIP(String label,byte[] array){
        System.out.print(String.format(label+" %d.%d.%d.%d\n",array[0]& 0xFF,array[1]& 0xFF,array[2]& 0xFF,array[3]& 0xFF));
    }
    private void displayMAC(String label,byte[] array){
        System.out.print(String.format(label+" %02X:%02X:%02X:%02X\n",array[0],array[1],array[2],array[3]));
    }    
}
