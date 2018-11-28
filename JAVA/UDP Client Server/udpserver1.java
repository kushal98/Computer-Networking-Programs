import java.io.*;
import java.net.*;
import java.util.*;
class udpserver1 {
	public static void main(String[] args) throws Exception {
		int i=5800;
		String foostring=new String("exit");
		while(true) {
			InetAddress clientIP=InetAddress.getLocalHost();
			//int clientIP=10:2:20:85;
			int clientPort=i;
			byte buf[]=new byte[1024];
			DatagramSocket ds=new DatagramSocket();
			// BufferedReader dis=new BufferedReader(new InputStreamReader(System.in));
			Scanner sc=new Scanner(System.in);
			System.out.println("Server is running... Enter you message.");
			// String str1=new String(dis.readLine());
			String str1=new String(sc.nextLine());
			if(str1.equals(foostring)) {
				ds.close();
				break;
			}
			else {
				buf=str1.getBytes();
				DatagramPacket packet=new DatagramPacket(buf,str1.length(),clientIP,clientPort);
				ds.send(packet);
				i=i+1;
			}
		}
	}
}
