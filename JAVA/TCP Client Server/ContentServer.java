import java.net.*;
import java.io.*;
public class ContentServer
	{
		public static void main(String args[]) throws Exception
		{
			ServerSocket sersock = new ServerSocket(6000);
			System.out.println("Server Ready for Connection");
			Socket sock = sersock.accept();
			System.out.println("Connection Successful and waiting for Chatting!!!!");
			InputStream istream = sock.getInputStream();
			BufferedReader nameread = new BufferedReader(new InputStreamReader(istream));
			String fname = nameread.readLine();

			OutputStream ostream = sock.getOutputStream();
			PrintWriter pwrite  = new PrintWriter(ostream,true);
			File f = new File(fname);
      if(f.exists())
      {
      	String str;
		  	BufferedReader contentRead = new BufferedReader(new FileReader(fname));
		  	while((str = contentRead.readLine())!= null)
		  	{
		  		pwrite.println(str);
		  	}
				contentRead.close();
			}
		  else
			{
				pwrite.println("File Does Not Exist");

      }
			sock.close();
   		sersock.close();
			pwrite.close();
			nameread.close();

		}
	}
