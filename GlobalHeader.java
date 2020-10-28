public class GlobalHeader {

    private String magic_number;   /* magic number */ // On garde
    private int version_major;  /* major version number */ //osef
    private int version_minor;  /* minor version number */ //osef 
    private int  thiszone;       /* GMT to local correction */ // osef
    private int sigfigs;        /* accuracy of timestamps */ //osef
    private String snaplen;        /* max length of captured packets, in octets */
    private int network;        /* data link type */

    public GlobalHeader(byte[] magic_number,byte[] snaplen)
    {
        this.magic_number = byteArrayToHexa(magic_number);
        this.snaplen = byteArrayToHexa(snaplen);
        printGlobalHeaderInfos();
    }

    public String getMagicNumer()
    {
        return this.magic_number;
    }

    public String getSnaplen()
    {
        return this.snaplen;
    }

    public void printGlobalHeaderInfos()
    {
        System.out.println("Magic Number : <"+getMagicNumer()+">");
        System.out.println("SnapLen : <"+getSnaplen()+">");
    }

    public String byteArrayToHexa(byte[] bytes)
    { 
        String st = "";
        
        for (byte b : bytes) {
            st += String.format("%02X", b);
        }
        return st;
    }

}
