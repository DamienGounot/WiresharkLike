public class PacketHeader {


    private int ts_sec;         /* timestamp seconds */
    private int ts_usec;        /* timestamp microseconds */
    private int incl_len;       /* number of octets of packet saved in file */
    private int orig_len;       /* actual length of packet */

    /*NB incl_len de doit jamais etre > orig_len ou > GlobalHeader.snaplen*/
    
    public PacketHeader(int ts_sec, int ts_usec, int incl_len, int orig_len) {
        this.ts_sec = ts_sec;
        this.ts_usec = ts_usec;
        this.incl_len = incl_len;
        this.orig_len = orig_len;
        printImportantInfos();
    }


    public int getTs_sec() {
        return this.ts_sec;
    }

    public int getTs_usec() {
        return this.ts_usec;
    }

    public int getIncl_len() {
        return this.incl_len;
    }

    public int getOrig_len() {
        return this.orig_len;
    }

    private void printImportantInfos()
    {
        System.out.println("==================Packet Header==================");  
        System.out.println("ts_sec : <"+getTs_sec()+">");
        System.out.println("ts_usec : <"+getTs_usec()+">");
        System.out.println("incl_len : <"+getIncl_len()+">");
        System.out.println("orig_len : <"+getOrig_len()+">");
        System.out.println("==================End of Packet Header==================");

    }

}
