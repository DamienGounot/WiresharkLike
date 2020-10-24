public class PacketHeader {


    private int ts_sec;         /* timestamp seconds */
    private int ts_usec;        /* timestamp microseconds */
    private int incl_len;       /* number of octets of packet saved in file */
    private int orig_len;       /* actual length of packet */

    /*NB incl_len de doit jamais etre > orig_len ou > GlobalHeader.snaplen*/
    
    public PacketHeader(){

    }
    
    
    public int getIncl_en(){
        return this.incl_len;
    }
}
