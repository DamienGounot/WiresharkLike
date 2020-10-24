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
        String st = convertToString(this.pcap);
        String magicNumber = getMagicNumber(st);
        String snaplen = getSnapLen(st);
        this.globalHeader = new GlobalHeader(magicNumber, Integer.parseInt(snaplen));
        this.globalHeader.printGlobalHeaderInfos();
        // NB: getSnaplen me retourne 400 au lieu de 00000400
    }



    public static String convertToString(byte[] array) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte val : array) {
            strBuilder.append(String.format("%02x", val & 0xff));
        }
        return strBuilder.toString();
    }

    public static String getMagicNumber(String st)
    {
        String magicNumber = st.substring(0,8);
        return magicNumber;
    }

    public static String getSnapLen(String st)
    {
        String snapLen = st.substring(32,40);
        return snapLen;
    }

}