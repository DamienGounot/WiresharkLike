import java.nio.ByteBuffer;
import java.util.BitSet;

public class IP {

    private byte[] versionEThlen = new byte[1];
    private BitSet version = new BitSet(4);
    private BitSet hlen = new BitSet(4);
    private byte[] tos = new byte[1];
    private byte[] totalLength = new byte[2];
    private byte[] ipID = new byte[2];
    private BitSet flag = new BitSet(3);
    private BitSet offset = new BitSet(13);
    private byte[] ttl = new byte[1];
    private byte[] protocol4 = new byte[1];
    private byte[] cheksum = new byte[2];
    private byte[] ip_src = new byte[4];
    private byte[] ip_dest = new byte[4];


    public IP(ByteBuffer data)
    {
        data.get(versionEThlen);//osef skip version et hlen
        data.get(tos); 
        data.get(totalLength);
        data.get(ipID);
        data.get(versionEThlen);//osef skip flag et un bout d'offset
        data.get(versionEThlen); // osef offset
        data.get(ttl);
        data.get(protocol4);
        data.get(cheksum);
        data.get(ip_src);
        data.get(ip_dest);

        System.out.println("==================Packet==================");
        System.out.println("Protocole: IP");
        System.out.println("Length: "+String.format("%02d%02d", totalLength[0]& 0xFF,totalLength[1]& 0xFF));
        displayField("Length: ",totalLength);
        displayField("Identification: ",ipID);
        displayField("protocole: ",protocol4);
        displayField("checkSum: ",totalLength);
        System.out.print(String.format("IP source: %d.%d.%d.%d\n",ip_src[0]& 0xFF,ip_src[1]& 0xFF,ip_src[2]& 0xFF,ip_src[3]& 0xFF));
        System.out.print(String.format("IP dest: %d.%d.%d.%d\n",ip_dest[0]& 0xFF,ip_dest[1]& 0xFF,ip_dest[2]& 0xFF,ip_dest[3]& 0xFF));
        System.out.println("==================End of Packet==================");  
    }


    private void displayField(String label,byte[] array){
        System.out.print(label+": ");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.println("\n");
    }



}
