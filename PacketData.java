public class PacketData {

    private byte data[];
    
    public PacketData(PacketHeader packetHeader){
        data = new byte[packetHeader.getIncl_en()];
    }
}
