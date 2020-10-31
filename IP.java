import java.nio.ByteBuffer;

public class IP {

    private byte[] versionEThlen = new byte[1];
    private byte[] tos = new byte[1];
    private short totalLength;
    private short ipID;
    private byte[] flagEToffset = new byte[2];
    private byte[] ttl = new byte[1];
    private byte[] protocol4 = new byte[1];
    private byte[] cheksum = new byte[2];
    private byte[] ip_src = new byte[4];
    private byte[] ip_dest = new byte[4];

    TCP tcp;
    UDP udp;

    public IP(ByteBuffer data) {
        data.get(versionEThlen);// osef skip version et hlen
        data.get(tos);
        totalLength = data.getShort();
        ipID = data.getShort();
        data.get(flagEToffset);// osef skip flag et d'offset
        data.get(ttl);
        data.get(protocol4);
        data.get(cheksum);
        data.get(ip_src);
        data.get(ip_dest);

        System.out.println("==================Packet==================");
        System.out.println("Protocole: IP(v4)");
        System.out.println("Total Length: "+String.format("%d", totalLength)+" bytes.");
        System.out.println("Identification (ID): "+ String.format("(%d)", ipID & 0xFFFF));
        displayHProtocole(protocol4);
        displayHex("checkSum", cheksum);
        System.out.print(String.format("IP source: %d.%d.%d.%d\n", ip_src[0] & 0xFF, ip_src[1] & 0xFF, ip_src[2] & 0xFF,
                ip_src[3] & 0xFF));
        System.out.print(String.format("IP dest: %d.%d.%d.%d\n", ip_dest[0] & 0xFF, ip_dest[1] & 0xFF,
                ip_dest[2] & 0xFF, ip_dest[3] & 0xFF));
        System.out.println("==================End of Packet==================");
        createNextPacket(protocol4, data);
    }

    private void displayHex(String label,byte[] array){
        System.out.print(label+": 0x");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.print("\n");
    }

    private void displayHProtocole(byte[] array){
            String protocole = String.format("%02X",array[0]);
            String display = "";
            switch (protocole) {
                case "06": //TCP
                    display = "TCP";
                    break;                
                case "11": //UDP
                display = "UDP";
                    break;
                    case "01": //ICMP
                    display = "ICMP";
                    break;            
                default:
                display = "Non supporté";
                    break;
            }
            
            
            System.out.println("Protocole de niveau supérieur: "+display);

    }

    private void createNextPacket(byte[] array, ByteBuffer data)
    {
        String protocole = String.format("%02X",array[0]);
        switch (protocole) {
            case "06": //TCP
                tcp = new TCP(data.slice());
                break;                
            case "11": //UDP
                udp = new UDP(data.slice());
                break;
                case "01": //ICMP
                break;            
            default:
                break;
        }
    }
}
