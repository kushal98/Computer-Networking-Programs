import java.io.*;
import java.util.*;
import java.net.*;

public class udpserver{
  public static void main(String args[]) throws Exception{
    int i=5800;
    while (true){
      InetAddress ClientIP=InetAddress.getLocalHost();
      Scanner sc=new Scanner(System.in);
      int ClientPort=i;
      byte buf[]=new byte[1024];
      DatagramSocket ds = new DatagramSocket();
      System.out.println("Server is ready enter message:");
      String str1=new String(sc.nextLine());
      if(str1=="exit")
      {
        ds.close();
        break;
      }
      else{
        buf=str1.getBytes();
        DatagramPacket dp = new DatagramPacket(buf,str1.length(),ClientIP,ClientPort);
        ds.send(dp);
        i=i+1;
      }

    }
  }
}
