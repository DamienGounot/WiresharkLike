WiresharkLike

java 15.0.1 2020-10-20
Java(TM) SE Runtime Environment (build 15.0.1+9-18)
Java HotSpot(TM) 64-Bit Server VM (build 15.0.1+9-18, mixed mode, sharing)

Compilation : 
cd src/
javac *.java

Launch: 
java Wireshark [pcapfile] [filter] \
NB: Available filter are: "all" "ARP" "IP" "ICMP" "TCP" "UDP" "DHCP" \
example: java Wireshark ../Captures/all.pcap ARP \

The Capture folder contains some .pcap files \

 \
├── Captures \
│   ├── all.pcap \
│   ├── ARP.pcap \
│   ├── DHCP.pcap \
│   ├── ICMP.pcap \
│   └── IP.pcap \
├── README.md \
└── src\
    ├── ARP.java \
    ├── DHCP.java \
    ├── Ethernet.java \
    ├── GlobalHeader.java \
    ├── ICMP.java \
    ├── IP.java \
    ├── PacketData.java \
    ├── PacketHeader.java \
    ├── TCP.java \
    ├── UDP.java \
    └── Wireshark.java \

-------------------------
Avancement : 
Parsing des protocoles (Ethernet,ARP,IP,TCP,UDP,ICMP,DHCP) à partir d'un fichier pcap \
Affichage des informations importantes condensées. \
Pour une trame, affichage successif des paquets encapsulés. \
exemple: pour un ping, affichage des infos IP, puis ICMP (les infos de la trame ethernet \
Filtrage basique, exemple; si filtre TCP appliqué, affiche les informations des couches < TCP. \
