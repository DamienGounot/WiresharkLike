import java.nio.ByteBuffer;

public class ICMP {
    
    private byte[] typeMsg = new byte[1];
    private byte[] codeErreur = new byte[1];
    private byte[] checksum = new byte[2];


    public ICMP(ByteBuffer data)
    {
        
        data.get(typeMsg);
        data.get(codeErreur);
        data.get(checksum);
        System.out.println("==================Packet==================");
        System.out.println("Protocole: ICMP");
        displayType(typeMsg);
        displayCode(codeErreur,typeMsg);
        displayHex("Checksum", checksum);
        System.out.println("==================End of Packet===========");
    }

    private void displayHex(String label,byte[] array){
        System.out.print(label+": 0x");
        for(int i=0; i< array.length ; i++) {
            
            System.out.print(String.format("%02X",array[i]));
         }
         System.out.print("\n");
    }

    private void displayType(byte[] array) // display que pour les plus courants
    {
        String string = "";

        switch (array[0]) {
            case 0:
                string = " (Echo Reply)";
                break;
            case 3:
                string = " (Destination Unreachable)";
                break;
            case 5:
                string = " (Redirect Message)";
                break; 
            case 8:
                string = " (Echo Request)";
                break;                      
            default:
                string = "";
                break;
        }
        System.out.println("Type: "+String.format("%d", typeMsg[0] & 0xFF)+string);
    }

    private void displayCode(byte[] code,byte[] type) // display que pour les plus courants
    {
        String string = "";

        switch (type[0]) {
            case 3:
                    switch (code[0]) {
                        case 0:
                            string = " (Destination network unreachable)";
                            break;
                        case 1:
                            string = " (Destination host unreachable)";
                            break;
                        case 2:
                            string = " (Destination protocol unreachable)";
                            break; 
                            case 3:
                            string = " (Destination port unreachable)";
                            break;
                        case 5:
                            string = " (Source route failed)";
                            break;
                        case 6:
                            string = " (Destination network unknown)";
                            break;
                        case 7:
                            string = " (Destination host unknown)";
                            break;                                                            
                        default:
                            string = "";
                            break;
                    }
                break;
            case 5:
                    switch (code[0]) {
                        case 0:
                            string = " (Redirect Datagram for the Network)";
                            break;
                        case 1:
                            string = " (Redirect Datagram for the Host)";
                            break;
                        case 2:
                            string = " (Redirect Datagram for the ToS & network)";
                            break; 
                        case 3:
                            string = " (Redirect Datagram for the ToS & host)";
                            break;                                       
                        default:
                            string = "";
                            break;
                    }
                break;                   
            default:
                string = "";
                break;
        }
        System.out.println("Code: "+String.format("%d", codeErreur[0] & 0xFF)+string);
    }
}
