import java.io.*;
import java.math.*;
import java.util.*;

public  class rsa{
  BigInteger p,q,N,phi,e,d;
  private int bitlength=1024;
  private Random r;

  public rsa(){
    r=new Random();
    p=BigInteger.probablePrime(bitlength/2,r);
    q=BigInteger.probablePrime(bitlength/2,r);
    phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    N=p.multiply(q);
    e=BigInteger.probablePrime(bitlength/2,r );
    while(e.gcd(phi).compareTo(BigInteger.ONE)>0)
    {
      e=e.add(BigInteger.ONE);
      System.out.println(e);
    }
    d=e.modInverse(phi);

  }

  public static void main(String args[]) throws Exception{
    rsa RSA=new rsa();
    DataInputStream in = new DataInputStream(System.in);
    System.out.println("Enter a string");
    String str=in.readLine();
    System.out.println("Encrypting String: "+str);
    System.out.println("String in bytes: "+BytesToString(str.getBytes()));
    byte[] encrypted= RSA.encrypt(str.getBytes());
    System.out.println("Encrypted string: "+encrypted);

    byte[] decrypted= RSA.decrypt(encrypted);
    System.out.println("Decrypting:"+decrypted);
    System.out.println("Decrypting bytes:"+BytesToString(decrypted));
    System.out.println("Decryted string: "+new String(decrypted));
  }

  static String BytesToString(byte[] encrypted)
  {
    String s="";
    for(byte b:encrypted)
    {
      s+=Byte.toString(b);
    }
    return s;
  }

  byte[] encrypt(byte[] message)
  {
    return(new BigInteger(message).modPow(e,N).toByteArray());
  }

  byte[] decrypt(byte[] message)
  {
    return(new BigInteger(message).modPow(d,N).toByteArray());
  }
}
