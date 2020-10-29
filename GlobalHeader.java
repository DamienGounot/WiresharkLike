import java.nio.ByteBuffer;

public class GlobalHeader {

    private int magic_number;   /* magic number */ // On garde
    private short version_major;  /* major version number */ //osef
    private short version_minor;  /* minor version number */ //osef 
    private int thiszone;       /* GMT to local correction */ // osef
    private int sigfigs;        /* accuracy of timestamps */ //osef
    private int snaplen;        /* max length of captured packets, in octets */
    private int network;        /* data link type */


    public GlobalHeader(int magic_number, short version_major, short version_minor, int thiszone, int sigfigs, int snaplen, int network) {
        this.magic_number = magic_number;
        this.version_major = version_major;
        this.version_minor = version_minor;
        this.thiszone = thiszone;
        this.sigfigs = sigfigs;
        this.snaplen = snaplen;
        this.network = network;
        printImportantInfos();
    }


    public int getMagicNumer()
    {
        return this.magic_number;
    }

    public int getMagic_number() {
        return this.magic_number;
    }

    public short getVersion_major() {
        return this.version_major;
    }

    public short getVersion_minor() {
        return this.version_minor;
    }

    public int getThiszone() {
        return this.thiszone;
    }

    public int getSigfigs() {
        return this.sigfigs;
    }

    public int getNetwork() {
        return this.network;
    }

    public int getSnaplen()
    {
        return this.snaplen;
    }

    public void printImportantInfos()
    {
        System.out.println("Magic Number : <"+String.format("%08X", getMagicNumer())+">");
        System.out.println("Major Version : <"+getVersion_major()+">");
        System.out.println("Minor Version : <"+getVersion_minor()+">");
        System.out.println("Zone : <"+getThiszone()+">");
        System.out.println("Sigfigs : <"+getSigfigs()+">");
        System.out.println("SnapLen : <"+getSnaplen()+">");
        System.out.println("Network : <"+getNetwork()+">");
    }

}
