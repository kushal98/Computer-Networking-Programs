import java.net.*;
import java.io.*;

public class ContentClient
{
public static void main(String args[])throws Exception
  {
  InetAddress ClientIP=InetAddress.getLocalHost();
	Socket sock=new Socket(ClientIP,6000);
	System.out.println("enter the file name");
	BufferedReader nameRead=new BufferedReader(new InputStreamReader(System.in));
	String fname= nameRead.readLine();
	OutputStream ostream=sock.getOutputStream();
	PrintWriter pwrite=new PrintWriter(ostream,true);
	pwrite.println(fname);
	InputStream istream=sock.getInputStream();
	BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream));
	String str;
	while((str=socketRead.readLine())!=null)
	{
		System.out.println(str);
	}
	pwrite.close();
	socketRead.close();
	nameRead.close();
  }
}
