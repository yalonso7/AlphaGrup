package ss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;


public class RSA {
 
  BufferedReader reader;

  BigInteger p, q, N;
  BigInteger phi, e, d;
  final int BIT_LENGTH = 1024;
  final int BLOCK_SIZE = 256;
  Random random;
 
  public RSA() {
    InputStreamReader isr;
    isr = new InputStreamReader(System.in);
    reader = new BufferedReader(isr);
    random = new SecureRandom();
  }

  void generateKeys() {
    p = BigInteger.probablePrime
        (BIT_LENGTH, random);
    q = BigInteger.probablePrime
        (BIT_LENGTH, random);
   
    N = p.multiply(q);

    phi = p.subtract(BigInteger.ONE).
        multiply(q.subtract(BigInteger.ONE));
   
    e = BigInteger.probablePrime
        (BIT_LENGTH/2, random);

    while (phi.gcd(e).
        compareTo(BigInteger.ONE) > 0
        && e.compareTo(phi) < 0 ) {
      e.add(BigInteger.ONE);
    }
   
    d = e.modInverse(phi);
  }

  String getMessage() {
    String msg = null;
    try {
      msg = reader.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return msg;
  }
 
  public byte[] encrypt(byte[] message) {
    return (new BigInteger(message)).
        modPow(e, N).toByteArray();
  }

  public byte[] decrypt(byte[] message) {
    return (new BigInteger(message)).
        modPow(d, N).toByteArray();
  }
 
  String encBytesToString(byte[] encrypted) {
    String s = "";
    for (byte b : encrypted) {
      s += Byte.toString(b);
    }
    return s;
  }
 
  String decBytesToString(byte[] decrypted) {
    String s = "";
    for (byte b : decrypted) {
      char c = (char)b;
      s += c;
    }
    return s;
  }
 
  public static void main (String[] args) {
    RSA rsa = new RSA();
    rsa.generateKeys();
   
    System.out.print("Enter message: ");
    String msg = rsa.getMessage();
    System.out.println();
   
    byte[] encrypted;
    encrypted = rsa.encrypt(msg.getBytes());
    System.out.println("Encrypted: "
        + rsa.encBytesToString(encrypted));
    System.out.println();
   
    byte[] decrypted;
    decrypted = rsa.decrypt(encrypted);
    System.out.println("Decrypted: "
        + rsa.decBytesToString(decrypted));
    System.out.println();
  }
}