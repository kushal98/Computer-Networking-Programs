#!/usr/bin/awk-f
BEGIN{
cbrPkt=0;
tcpPkt=0;
}
{
if(($1== "d")&&($5== "cbr")) {cbrPkt++;}
if(($1== "d")&&($5== "tcp")) {tcpPkt++;}
}
END{
printf("No. of CBR Packets Dropped %d\n",cbrPkt);
printf("No. of TCP Packets Dropped %d\n",tcpPkt);
}
