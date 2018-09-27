import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
 
  BufferedReader reader;
  final String IV = "AAAAAAAAAAAAAAAA";
 
  public static void main(String args[]) {
    AES aes = new AES();
    aes.go();
  }
 
  public AES() {
    reader = new BufferedReader
        (new InputStreamReader(System.in));
  }
 
  void go() {
    StringBuffer message = new StringBuffer();
    try {
      System.out.print("Enter Message: ");
      message.append(reader.readLine());
     
      while (message.length() % 16 != 0)
        message.append('\u0000');
     
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
   
    StringBuffer key = new StringBuffer();
    try {
      System.out.print("Enter Key: ");
      key.append(reader.readLine());
     
      while (key.length() % 16 != 0)
        key.append('\u0000');
     
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
   
    // Encryption
    byte[] cipher =
        encrypt(message.toString(), key.toString());
   
    System.out.print("Cipher: ");
    for (int i=0; i<cipher.length; i++)
      System.out.print((int)cipher[i]);
    System.out.println();
   
    // Invalid key
    //key.replace(0, 6, "ABCDEF");
   
    // Decryption
    String decrypted =
        decrypt(cipher, key.toString());
   
    System.out.println("Decrypted message: " + decrypted);
  }
 
  byte[] encrypt
    (String plain, String key) {
   
    byte[] encrypted = null;
   
    try {
      Cipher cipher =
          Cipher.getInstance
            ("AES/CBC/NoPadding", "SunJCE");
     
      SecretKeySpec sks =
          new SecretKeySpec(key.getBytes("UTF-8"), "AES");
     
      AlgorithmParameterSpec params;
      params = new IvParameterSpec(IV.getBytes("UTF-8"));
      cipher.init(Cipher.ENCRYPT_MODE, sks, params);
   
      encrypted = cipher.doFinal(plain.getBytes("UTF-8"));
    } catch (Exception e) {
      e.printStackTrace();
    }
   
    return encrypted;
  }
 
  String decrypt(byte[] ct, String key) {
    StringBuffer decrypted = new StringBuffer();
    try {
      Cipher cipher =
          Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
     
      SecretKeySpec sks =
          new SecretKeySpec(key.getBytes("UTF-8"), "AES");
     

      AlgorithmParameterSpec params;
      params = new IvParameterSpec(IV.getBytes("UTF-8"));
      cipher.init(Cipher.DECRYPT_MODE, sks, params);
     
      String s = new String(cipher.doFinal(ct), "UTF-8");
      decrypted.append(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
   
    for (int i=decrypted.length()-1; i>0; i--) {
      if (decrypted.charAt(i) == '\u0000')
        decrypted.deleteCharAt(i);
      else break;
    }
   
    return decrypted.toString();
  }
}