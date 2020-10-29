import java.nio.ByteBuffer;

public class PacketData {

    private byte[] adresse_dest = new byte[6];
    private byte[] adresse_source = new byte[6];
    private int etherType;
    private ByteBuffer data;

    public PacketData(byte[] adresse_dest, byte[] adresse_source, int etherType, ByteBuffer data) {
        this.adresse_dest = adresse_dest;
        this.adresse_source = adresse_source;
        this.etherType = etherType;
        this.data = data;
    }

    public byte[] getAdresse_dest() {
        return this.adresse_dest;
    }

    public byte[] getAdresse_source() {
        return this.adresse_source;
    }

    public int getEtherType() {
        return this.etherType;
    }

    public ByteBuffer getData() {
        return this.data;
    }

}
