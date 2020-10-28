import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wireshark
{
    GlobalHeader globalHeader;
    byte[] pcap;

    public Wireshark(String[] args) throws IOException
    {
        pcap = Files.readAllBytes(Paths.get(args[0]));
        run(args);
    }

	public static void main(String[] args) throws IOException{	
        Wireshark wireshark = new Wireshark(args);
    }


    public void run(String[] args)
    {
        byte[] magicNumber = getMagicNumber(this.pcap);
        byte[] snaplen = getSnapLen(this.pcap);
        this.globalHeader = new GlobalHeader(magicNumber,snaplen);
        this.pcap = checkMagicNumber(globalHeader);

    }

    private byte[] checkMagicNumber(GlobalHeader globalHeader)
    {
        String magicNumber = globalHeader.byteArrayToHexa(getMagicNumber(this.pcap));
        if (magicNumber.compareTo("D4C3B2A1") == 0) {
            // reverse le pcap
        }
    
        return this.pcap;
    }

    public static String convertToString(byte[] array) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte val : array) {
            strBuilder.append(String.format("%02x", val & 0xff));
        }
        return strBuilder.toString();
    }

    public static byte[] getMagicNumber(byte[] pcap)
    {
        byte[] magicNumber = new byte[4];
        for (int i = 0; i < 4; i++) {
            magicNumber[i] = pcap[i];
        }
        return magicNumber;
    }

    public static byte[] getSnapLen(byte[] pcap)
    {
        byte[] snapLen = new byte[4];;
        for (int i = 16; i < 20; i++) {
            snapLen[i%4] = pcap[i];
        }
        return snapLen;
    }

}