import java.nio.ByteBuffer;

public class DHCP {
    
    private byte htype;
    private byte hlen;
    private byte hops;
    private int xid;
    private short secs;
    private short flags;

    private byte[] ci_addr = new byte[4];
    private byte[] yi_addr = new byte[4];
    private byte[] next_server_i_addr = new byte[4];
    private byte[] relay_agent_i_addr = new byte[4];
    private byte[] ch_addr = new byte[16];
    private byte[] sname = new byte[64];
    

    public DHCP(ByteBuffer data, int i)
    {
        System.out.println("==================Packet==================");

        if (i == 1) {
            System.out.println("Protocole: DHCP (Request)");
        }else
        {
            System.out.println("Protocole: DHCP (Reply)");
        }

        htype = data.get();
        hlen = data.get();
        hops = data.get();
        xid = data.getInt();
        secs = data.getShort();
        flags = data.getShort();
        data.get(ci_addr);
        data.get(yi_addr);
        data.get(next_server_i_addr);
        data.get(relay_agent_i_addr);
        data.get(ch_addr);
        data.get(sname);

        System.out.println("Transaction ID: "+String.format("%d", xid & 0xFFFF));
        System.out.print(String.format("Client IP adress: %d.%d.%d.%d\n", yi_addr[0] & 0xFF, yi_addr[1] & 0xFF, yi_addr[2] & 0xFF,yi_addr[3] & 0xFF));
        displayMAC("Client MAC adress:", ch_addr);
        System.out.println("==================End of Packet===========");
    }

    private void displayMAC(String label,byte[] array){
        System.out.print(String.format(label+" %02X:%02X:%02X:%02X\n",array[0],array[1],array[2],array[3]));
    }  


}
