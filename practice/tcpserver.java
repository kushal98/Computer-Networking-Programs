import java.io.*;
import java.net.*;

public class tcpserver{
  public static void main(String args[]) throws Exception{
    ServerSocket sersock = new ServerSocket(6000);
    System.out.println("Server is ready for connection!");
    Socket sock = sersock.accept();
    System.out.println("Connection Established start chatting!");
    InputStream istream= sock.getInputStream();
    BufferedReader readName=new BufferedReader(new InputStreamReader(istream));
    String fname = readName.readLine();

    File f = new File(fname);
    OutputStream ostream = sock.getOutputStream();
    PrintWriter pwrite=new PrintWriter(ostream,true);
    String str;
    if(f.exists()){
      BufferedReader contentRead=new BufferedReader(new FileReader(fname));
      while((str=contentRead.readLine())!=null)
      {
        pwrite.println(str);
      }
      contentRead.close();
    }
    else{
      pwrite.println("File Does Not Exist!");
    }

    sersock.close();
    sock.close();
    readName.close();

  }
}
