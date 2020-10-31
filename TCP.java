import java.nio.ByteBuffer;

public class TCP {
    private short portSrc;
    private short portDest;
    private byte[] sequenceNumber = new byte[4];
    private byte[] aquitNumber = new byte[4];
    private int pourSkipint; // osef
    private byte[] checksum = new byte[2];
    private short pourSkipshort;// osef

    public TCP(ByteBuffer data)
    {
        portSrc = data.getShort();
        portDest = data.getShort();
        data.get(sequenceNumber);
        data.get(aquitNumber);
        pourSkipint = data.getInt();
        data.get(checksum);
        pourSkipshort = data.getShort();
        pourSkipint = data.getInt();

        System.out.println("==================Packet==================");
        System.out.println("Protocole: TCP");
        System.out.println("Source port: "+String.format("%d", portSrc));
        System.out.println("Destination port: "+String.format("%d", portSrc));
        displayHex("Sequence Number", sequenceNumber);
        displayHex("Acknowledgment Number", aquitNumber);
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
