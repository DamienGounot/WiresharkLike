import java.nio.ByteBuffer;

public class UDP {

    DHCP dhcp;
    
    private short portSrc;
    private short portDest;
    private short size;
    private byte[] checksum = new byte[2];

    private byte[] op = new byte[1];

    public UDP(ByteBuffer data)
    {
        portSrc = data.getShort();
        portDest = data.getShort();
        size = data.getShort();
        data.get(checksum);
        data.get(op);
        System.out.println("==================Packet==================");
        System.out.println("Protocole: UDP");
        System.out.println("Source port: "+String.format("%d", portSrc & 0xFFFF));
        System.out.println("Destination port: "+String.format("%d", portDest & 0xFFFF));
        displayHex("Checksum", checksum);
        System.out.println("==================End of Packet===========");
        createNextPacket(op, data);
    }

    private void displayHex(String label,byte[] array){
        System.out.print(label+": 0x");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.print("\n");
    }


    private void createNextPacket(byte[] array, ByteBuffer data)
    {
        String protocole = String.format("%02X",array[0]);
        switch (protocole) {
            case "01": //DHCP request
                dhcp = new DHCP(data.slice(),1);
                break;                
            case "02": //DHCP reply
                dhcp = new DHCP(data.slice(),2);
                break;
            default:
                break;
        }
    }
}
