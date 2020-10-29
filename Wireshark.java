import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wireshark
{
    GlobalHeader globalHeader;
    PacketHeader packetHeader;
    ARP arp;

    public ByteBuffer pcap;

    private int magic_number;   /* magic number */ // On garde
    private short version_major;  /* major version number */ //osef
    private short version_minor;  /* minor version number */ //osef 
    private int thiszone;       /* GMT to local correction */ // osef
    private int sigfigs;        /* accuracy of timestamps */ //osef
    private int snaplen;        /* max length of captured packets, in octets */
    private int network;        /* data link type */

    private int ts_sec;         /* timestamp seconds */
    private int ts_usec;        /* timestamp microseconds */
    private int incl_len;       /* number of octets of packet saved in file */
    private int orig_len;       /* actual length of packet */

    private byte[] adresse_dest = new byte[6];
    private byte[] adresse_source = new byte[6];
    private short etherType;


    public Wireshark(String[] args) throws IOException
    {
        byte[] bytepcap = Files.readAllBytes(Paths.get(args[0]));
        System.out.println(bytepcap.length);
        pcap = ByteBuffer.wrap(bytepcap);
        run(args);
    }

	public static void main(String[] args) throws IOException{	
        Wireshark wireshark = new Wireshark(args);
    }


    public void run(String[] args)
    {
        
        magic_number = pcap.getInt();
        if(String.format("%08X", magic_number).equals("A1B2C3D4"))
        {
            pcap.order(ByteOrder.BIG_ENDIAN); // a gerer
        }
        else
        {
            pcap.order(ByteOrder.LITTLE_ENDIAN); // a gerer
        }
              
        version_major = pcap.getShort();
        version_minor = pcap.getShort();
        thiszone = pcap.getInt();
        sigfigs = pcap.getInt();
        snaplen = pcap.getInt();
        network = pcap.getInt();
        System.out.println("==================Global Header==================");  
        globalHeader = new GlobalHeader(magic_number, version_major, version_minor, thiszone, sigfigs, snaplen, network);
        System.out.println("==================End of Global Header==================");

        int i = 24;
        while (pcap.hasRemaining())
        {   // parcour du fichier pcap mettre un pcap.remaining
                
                if(String.format("%08X", magic_number).equals("A1B2C3D4"))
                {
                    pcap.order(ByteOrder.BIG_ENDIAN); // a gerer
                }
                else
                {
                    pcap.order(ByteOrder.LITTLE_ENDIAN); // a gerer
                }
                // recuperation header packet                
                ts_sec = pcap.getInt();
                ts_usec = pcap.getInt();
                incl_len = pcap.getInt();
                orig_len = pcap.getInt();
                System.out.println("==================Packet Header==================");  
                packetHeader = new PacketHeader(ts_sec,ts_usec,incl_len,orig_len);
                System.out.println("==================End of Packet Header==================");
                incl_len = packetHeader.getIncl_len();
                
                pcap.order(ByteOrder.BIG_ENDIAN); // a gerer

                pcap.get(adresse_dest); // on recupere 6 octets pour @dest
                pcap.get(adresse_source); // on recupere 6 octets pour @source
                etherType = pcap.getShort();

                // en fonction du protocole

                    switch (etherType) {
                        case (short)0x0806: // si ARP
                        System.out.println("==================Packet==================");  
                                arp = new ARP(pcap.slice());
                        System.out.println("==================End of Packet==================");        
                            break;
                        default:
                        System.out.println("Protocole non supporté !");
                            break;
                    }

              i += (16 + incl_len); // (16 octets pour le packet Header)
              pcap.position(i);
              System.out.println(pcap);
        }
    }
}