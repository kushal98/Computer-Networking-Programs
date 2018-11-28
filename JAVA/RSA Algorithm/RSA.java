import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA
{
	private BigInteger p,q,N,phi,e,d;
	private int bitlength = 1024;
	private Random r;

	public RSA()
	{
		r = new Random();
		p = BigInteger.probablePrime(bitlength/2,r);
		q = BigInteger.probablePrime(bitlength/2,r);
		phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		N = p.multiply(q);
		e = BigInteger.probablePrime(bitlength/2,r);
		while(phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi)>0);
		{
			e.add(BigInteger.ONE);
			System.out.println("e: "+e);
		}
		d = e.modInverse(phi);
		System.out.println("p: "+p);
		System.out.println("q: "+q);
		System.out.println("d: "+d);
	}

	// public RSA(BigInteger e, BigInteger d, BigInteger N)
	// {
	// 	this.e = e;
	// 	System.out.println("e: "+e);
	// 	this.d = d;
	// 	System.out.println("d: "+d);
	// 	this.N = N;
	// 	System.out.println("N: "+N);
	// }

	public static void main(String [] args) throws Exception
	{
		RSA rsa = new RSA();
		DataInputStream in = new DataInputStream(System.in);
		String testString;
		System.out.println("Enter the plain text : ");
		testString = in.readLine();
		System.out.println("Encrypting String : "+testString);
		System.out.println("String in bytes: " +byteToString(testString.getBytes()));
		//ENCRYPT
		byte[] encrypted = rsa.encrypt(testString.getBytes());
		System.out.println("Encrypted text: "+encrypted);
		//DECRYPT
		byte[] decrypted = rsa.decrypt(encrypted);
		System.out.println("Decrypted text: "+decrypted);
		System.out.println("Decrypting Bytes: "+byteToString(decrypted));
		System.out.println("Decrypted String : "+ new String(decrypted));
		System.out.println("********************************************");
	}

	public static String byteToString(byte[] encrypted)
	{
		String test = "";
		for(byte b : encrypted)
			test += Byte.toString(b);
		return test;
	}

	public byte[] encrypt (byte[] message)
	{
		return(new BigInteger(message).modPow(e,N).toByteArray());
	}

	public byte[] decrypt (byte[] message)
	{
		return(new BigInteger(message).modPow(d,N).toByteArray());
	}
}
