public class GlobalHeader {

    private String magic_number;   /* magic number */ // On garde
    private int version_major;  /* major version number */ //osef
    private int version_minor;  /* minor version number */ //osef 
    private int  thiszone;       /* GMT to local correction */ // osef
    private int sigfigs;        /* accuracy of timestamps */ //osef
    private int snaplen;        /* max length of captured packets, in octets */
    private int network;        /* data link type */

    public GlobalHeader(String magic_number,int snaplen)
    {
        this.magic_number = magic_number;
        this.snaplen = snaplen;
    }

    public String getMagicNumer()
    {
        return this.magic_number;
    }

    public int getSnaplen()
    {
        return snaplen;
    }

    public void printGlobalHeaderInfos()
    {
        System.out.println("Magic Number : <"+this.getMagicNumer()+">");
        System.out.println("SnapLen : <"+this.getSnaplen()+">");
    }

}
