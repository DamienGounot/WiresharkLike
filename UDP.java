import java.nio.ByteBuffer;

public class UDP {
    
    private short portSrc;
    private short portDest;
    private short size;
    private byte[] checksum = new byte[2];

    public UDP(ByteBuffer data)
    {
        portSrc = data.getShort();
        portDest = data.getShort();
        size = data.getShort();
        data.get(checksum);

        System.out.println("==================Packet==================");
        System.out.println("Protocole: UDP");
        System.out.println("Source port: "+String.format("%d", portSrc & 0xFFFF));
        System.out.println("Destination port: "+String.format("%d", portDest & 0xFFFF));
        displayHex("Checksum", checksum);
        System.out.println("==================End of Packet==================");
    }

    private void displayHex(String label,byte[] array){
        System.out.print(label+": 0x");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.print("\n");
    }
}
