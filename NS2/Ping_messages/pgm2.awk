BEGIN{
#include <stdio.h>
count=0;
}
{
if( $1 == "d" )
        count++
}
END{
printf("The total no. of pkts dropped due to congestion: %d",count);
}
