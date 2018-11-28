import java.io.*;
import java.util.*;

class leakybucket
{
	public static int b_size,que_data,trate,time,inp[],drec[],ddrop[],dleft[];
	public static void input()throws IOException
	{
    Scanner sc=new Scanner(System.in);
		System.out.println("Enter bucket size");
		b_size=sc.nextInt();
		System.out.println("Enter the transmission rate");
		trate=sc.nextInt();
		System.out.println("Enter the transmission time");
		time=sc.nextInt();
		inp=new int[100];
		// BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		// System.out.println("Enter bucket size");
		// b_size=Integer.parseInt(ob.readLine());
		// System.out.println("Enter the transmission rate");
		// trate=Integer.parseInt(ob.readLine());
		// System.out.println("Enter the transmission time");
		// time=Integer.parseInt(ob.readLine());
		// inp=new int[100];
		for(int i=0;i<time;i++)
		{
			System.out.println("Enter the data in "+i+" sec:");
			inp[i]=sc.nextInt();
		}
		drec=new int[100];
		ddrop=new int[100];
		dleft=new int[100];
		for(int i=0;i<=time;i++)
		{
			drec[i]=0;
			ddrop[i]=0;
			dleft[i]=0;
		}
	}
	public static void calc()
	{
		que_data=0;
		for(int i=0;i<=time;i++)
		{
			que_data=que_data+inp[i];
			if(que_data<=trate)
			{
				drec[i]=que_data;
				ddrop[i]=0;
				dleft[i]=0;
				que_data=0;
			}
			else if (que_data<=b_size)
			{
				drec[i]=trate;
				ddrop[i]=0;
				dleft[i]=que_data-trate;
				que_data=que_data-trate;
			}
			else
			{
				drec[i]=trate;
				ddrop[i]=que_data-b_size;
				dleft[i]=b_size-trate;
				que_data=b_size-trate;
			}
		}
		int i=time;
		while(dleft[i]!=0)
		{
			if((dleft[i]>trate)&&(b_size>dleft[i]))
			{	i++;
				drec[i]=trate;
				ddrop[i]=0;
				dleft[i]=dleft[i-1]-trate;
			}
			else if((dleft[i]>trate)&&(b_size<dleft[i]))
			{	i++;
				drec[i]=trate;
				ddrop[i]=dleft[i-1]-b_size;
				dleft[i]=b_size-trate;
			}
			else
			{
				i++;
				drec[i]=dleft[i-1];
				ddrop[i]=0;
				dleft[i]=0;
			}
		}
	}
	public static void display()
	{
		System.out.println("Time\tData Sent\tData Received\tData Left\tData Dropped");
		for(int i=0;i<=time;i++)
		{
			System.out.println(i+"\t\t"+inp[i]+"\t\t"+drec[i]+"\t\t"+dleft[i]+"\t\t"+ddrop[i]);
		}
		int i=time;
		while(dleft[i]!=0)
		{	i++;

			System.out.println(i+"\t\t"+inp[i]+"\t\t"+drec[i]+"\t\t"+dleft[i]+"\t\t"+ddrop[i]);
		}

	}
	public static void main(String []args)throws IOException
	{
		input();
		calc();
		display();
	}
}
