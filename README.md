WiresharkLike

        /* Idée : une fois que le pcap (un gros byteBuffer) à été initialisé avec le contenu du fichier :
            On parse les 24 premier octects (car taille fixe)

            pour avoir tout ce qu'il faut pour creer un objet Global Header
            
            Récupérer la snaplen du Global Header (pour boucler < snaplen)


            BOUCLE SUR SNAPLEN 

            On parse les 16 prochains octects (car taille fixe) pour creer un objet PacketHeader

            
            Récupérer la inc_len du PacketHeader

            creer un objet PacketData () 
                NB --> avec la snaplen du PacketHeader on peut boucler pour init le PacketData

            en fonction de l'Ethertype du PacketData:

                creer un objet <type de protocole> (en lui passant le champ data (byteBuffer) du PacketData)
                init correctement l'objet <type de protocole>
                le print

            NB : comment boucler sur le pcap ?
            NB: Ok de parser dans le main, affecter a des variables et les passer dans le construct ?
            ( en gros (7 + 4 + 4) variables dans le main, systématiqument remis a jour )
            NB : On est d'accord que c'est suffisant (même si un peu dégueu ?) ? (voir constucteurs et attributs)
        */