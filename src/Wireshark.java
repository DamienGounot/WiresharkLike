import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wireshark
{
    GlobalHeader globalHeader;
    PacketHeader packetHeader;
    // ARP arp;
    // IP ip;
    Ethernet ethernet;
    private byte[] bytepcap;
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

    public static String filter ="";


    public Wireshark(String[] args) throws IOException
    {
        try {
            filter = args[1];
        } catch (Exception e) {
            System.out.println("Error: you need a filter value !");
            System.out.println("Usage is: java Wireshark <pcapfile> <filter>");
            System.out.println("Available filter are: <all> <ARP> <IP> <ICMP> <TCP> <UDP> <DHCP>");
            System.exit(0);
        }
        try {
             bytepcap = Files.readAllBytes(Paths.get(args[0]));
        } catch (Exception e) {
            System.out.println("Error: you need a valid file path !");
            System.out.println("Usage is: java Wireshark <pcapfile> <filter>");
            System.out.println("Available filter are: <all> <ARP> <IP> <ICMP> <TCP> <UDP> <DHCP>");
            System.exit(0);           
        }
        
        pcap = ByteBuffer.wrap(bytepcap);
        try {
            run(args);
        } catch (Exception e) {
            e.getStackTrace();
        }
        
    }

	public static void main(String[] args) throws IOException{	
        Wireshark wireshark = new Wireshark(args);
    }


    public void run(String[] args)
    {
        
        magic_number = pcap.getInt();

        
        if(String.format("%08X", magic_number).equals("A1B2C3D4"))
        {
            pcap.order(ByteOrder.BIG_ENDIAN); 
        }
        else
        {
            pcap.order(ByteOrder.LITTLE_ENDIAN); 
        }
              
        version_major = pcap.getShort();
        version_minor = pcap.getShort();
        thiszone = pcap.getInt();
        sigfigs = pcap.getInt();
        snaplen = pcap.getInt();
        network = pcap.getInt();

        
        globalHeader = new GlobalHeader(magic_number, version_major, version_minor, thiszone, sigfigs, snaplen, network);

        int i = 24;

        while (pcap.hasRemaining()) // parcours du fichier pcap
        {   
                
                if(String.format("%08X", magic_number).equals("A1B2C3D4"))
                {
                    pcap.order(ByteOrder.BIG_ENDIAN);
                }
                else
                {
                    pcap.order(ByteOrder.LITTLE_ENDIAN);
                }

                // recuperation header packet                
                ts_sec = pcap.getInt();
                ts_usec = pcap.getInt();
                incl_len = pcap.getInt();
                orig_len = pcap.getInt();
                packetHeader = new PacketHeader(ts_sec,ts_usec,incl_len,orig_len);
                incl_len = packetHeader.getIncl_len();
                
                pcap.order(ByteOrder.BIG_ENDIAN);

                ethernet = new Ethernet(pcap.slice());

              i += (16 + incl_len); // (16 octets pour le packet Header)
              pcap.position(i);
              //System.out.println(pcap);
        }
    }
}