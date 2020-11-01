import java.nio.ByteBuffer;

public class Ethernet {
    ARP arp;
    IP ip;

    private byte[] adresse_dest = new byte[6];
    private byte[] adresse_source = new byte[6];
    private short etherType;

    public Ethernet(ByteBuffer pcap)
    {
        pcap.get(adresse_dest); // on recupere 6 octets pour @dest
        pcap.get(adresse_source); // on recupere 6 octets pour @source
        etherType = pcap.getShort();

        System.out.print("\n\n\n\n");
        System.out.println("==================Frame==================");
        System.out.println("Protocole: Ethernet");
        displayMAC("Adresse Destination",adresse_dest);
        displayMAC("Adresse Source",adresse_source);
        displayHProtocole(etherType);
        System.out.println("==================End of Frame===========");


        switch (etherType) {
            case (short)0x0806: // si ARP 
                    if(Wireshark.filter.equalsIgnoreCase("ARP") || Wireshark.filter.equalsIgnoreCase("all"))
                        arp = new ARP(pcap.slice());         
                    
                break;
            case (short)0x0800: // si IPv4
            if(Wireshark.filter.equalsIgnoreCase("IP") || Wireshark.filter.equalsIgnoreCase("ICMP") || Wireshark.filter.equalsIgnoreCase("TCP") || Wireshark.filter.equalsIgnoreCase("UDP") || Wireshark.filter.equalsIgnoreCase("DHCP") || Wireshark.filter.equalsIgnoreCase("all"))
            ip = new IP(pcap.slice());
            break;
            default:
            System.out.println("Protocole non supporté !");
                break;
        }
    }

    private void displayMAC(String label,byte[] array){
        System.out.print(String.format(label+" %02X:%02X:%02X:%02X\n",array[0],array[1],array[2],array[3]));
    }

    private void displayHProtocole(short type)
    {
        String display = "";

        switch (type) {
            
            case (short)0x0806: // si ARP
                display = "ARP";
                break;                
                case (short)0x0800: // si IPv4
                display = "IPv4";
                break;
            default:
            System.out.println("Protocole non supporté !");
                break;
        }
        
        
        System.out.println("Protocole de niveau supérieur: "+display);
    }   
}
