#!/usr/bin/awk-f

BEGIN{
        PktRcvd=0.0;
        Throughput=0.0;
}

{
        if(($1=="r")&&($3=="_3_")&&($4=="AGT")&&($7=="tcp")&&($8>1000))
        {
                PktRcvd++;
        }

        Throughput=(PktRcvd*1000*8)/(95*1000000);
}

END{
        printf("No of packets recieved = %f\n",PktRcvd);
        printf("Throughput = %f\n",Throughput);
}
