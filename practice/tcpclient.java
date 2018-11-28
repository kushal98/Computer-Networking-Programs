import java.io.*;
import java.util.*;
import java.net.*;

public class tcpclient{
  public static void main(String args[]) throws Exception{
    InetAddress ClientIP=InetAddress.getLocalHost();
    Socket sock=new Socket(ClientIP,6000);
    System.out.println("Enter the file name");
    BufferedReader bwr = new BufferedReader(new InputStreamReader(System.in));
    String fname=bwr.readLine();
    OutputStream ostream = sock.getOutputStream();
    PrintWriter pwrite= new PrintWriter(ostream,true);
    pwrite.println(fname);

    InputStream istream= sock.getInputStream();
    BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
    String str;
    while((str=socketRead.readLine())!=null){
      System.out.println(str);
    }
    socketRead.close();
    bwr.close();
    pwrite.close();
    sock.close();
  }
}
