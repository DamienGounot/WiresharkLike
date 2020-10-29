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
        displayField("hardwareType",hardwareType);
        displayField("protocolType",protocolType);
        displayField("taille_MAC",taille_MAC);
        displayField("taille_IP",taille_IP);
        displayField("codeARP",codeARP);
        displayField("MAC_src",MAC_src);
        displayField("IP_src",IP_src);
        displayField("MAC_dest",MAC_dest);
        displayField("IP_dest",IP_dest);
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
         System.out.println(">\n");
    }
    
}
